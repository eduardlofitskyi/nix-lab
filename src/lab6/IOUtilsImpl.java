package lab6;

import interfaces.task6.IOUtils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class IOUtilsImpl implements IOUtils{

    private static List<String> paths = new ArrayList<>();

    @Override
    public void replaceChars(Reader reader, Writer writer, String model, String substitute) {


        if (reader == null || writer == null) throw new NullPointerException();
        if (model == null) model = "";
        if (substitute == null) substitute = "";
        if (model.length() != substitute.length()) throw new IllegalArgumentException("Size of model doesn't correspond to size of substitute");

        char[] modelArray = model.toCharArray();
        char[] substituteArray = substitute.toCharArray();

        int characterNum;
        try {
            while ((characterNum = reader.read()) != -1){
                for (int i = 0; i < modelArray.length; i++){
                    if (modelArray[i] == characterNum){
                        characterNum = substituteArray[i];
                        break;
                    }
                }
                writer.write(characterNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void copyFile(String srcStr, String destStr) {
        File file = new File(srcStr);
        File file1 = new File(destStr);

        if (!file.exists()) throw new IllegalArgumentException("Source file doesn't exist");

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file1))){

            int i;
            while ((i = is.read()) != -1){
                os.write(i);
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public void copyFileBuffered(File file, File file1) {
        if (!file.exists()) throw new IllegalArgumentException("Source file doesn't exist");

        try (BufferedInputStream is = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(file1))){

            byte[] buffer = new byte[256];
            int length;
            while ((length = is.read(buffer)) > 0){
                os.write(buffer, 0, length);
            }

        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }

    }

    @Override
    public void copyFileBest(String srcStr, String destStr) {
        File file1 = new File(srcStr);
        File file2 = new File(destStr);

        if (!file1.exists() || !file1.canRead()) throw new IllegalArgumentException("Source file doesn't exist");

        Path src = file1.toPath();
        Path dest = file2.toPath();

        //if (Files.notExists(src)) throw new IllegalArgumentException("Source file doesn't exist");

        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
            //e.printStackTrace();
        }
    }

    @Override
    public String[] findFiles(String s) {
        return this.findFiles(s, null);
    }

//    public String[] findFilesNio(String s, String extension) {
//
//        if (s.equals("")) throw new IllegalArgumentException("Path to dir cannot be empty");
//
//        Path startingDir = Paths.get(s);
//
//        if (Files.notExists(startingDir)) throw new IllegalArgumentException("No such file");
//
//        try {
//            Files.walkFileTree(startingDir, new FindJavaVisitor(extension, startingDir));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //return convertToStringArray(paths);
//
//        return null;
//    }

    @Override
    public String[] findFiles(String s, String extension) {
        paths.clear();

        if (s.equals("")) throw new IllegalArgumentException("Path to dir cannot be empty");

        Path startingDir = Paths.get(s);

        if (Files.notExists(startingDir)) throw new IllegalArgumentException("No such file");

        next(s,extension);

        return convertListToStringArray(paths);
    }

    public void next(String path, String ext){

        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                next(f.getAbsolutePath(), ext);
            }
            else {
                if (ext == null) paths.add(f.getAbsolutePath());
                else if (f.toString().endsWith(ext)) {
                    paths.add(f.getAbsolutePath());
                }
            }
        }
    }

//    private String[] convertToStringArray(List<Path> paths) {
//        String[] pathsStr = new String[paths.size()];
//        for (int i = 0; i < pathsStr.length; i++){
//            pathsStr[i] = paths.get(i).toAbsolutePath().toString();
//        }
//
//        return pathsStr;
//    }

    private String[] convertListToStringArray(List<String> paths) {
        String[] pathsStr = new String[paths.size()];
        for (int i = 0; i < pathsStr.length; i++){
            pathsStr[i] = paths.get(i);
        }

        return pathsStr;
    }
//
//    private static class FindJavaVisitor extends SimpleFileVisitor<Path>
//    {
//
//        private final String extension;
//        private final Path parent;
//
//        FindJavaVisitor(String extension, Path parent){
//            paths.clear();
//
//            if (extension != null && extension.equals("")) this.extension = null;
//            else this.extension = extension;
//            this.parent = parent;
//        }
//
//        @Override
//        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
//        {
//            if (extension == null){
//                if (!Files.isDirectory(file)) {
//                    paths.add(parent.relativize(file));
//                }
//            }else if (file.toString().endsWith(extension)) {
//                paths.add(parent.relativize(file));
//            }
//            return FileVisitResult.CONTINUE;
//        }
//    }
}
