package lab6;

import interfaces.task6.IOUtils;

import java.io.*;

public class Main {

    private static final String PATH_TO_START_DIR = "/home/NIX/lofitskyi/IdeaProjects/lab1classes";
    private static final String PATH_TO_IMG = "/home/NIX/lofitskyi/Downloads/NIX_Screensaver.jpg";
    private static final String PATH_TO_IMG_DEST1 = "/home/NIX/lofitskyi/Pictures/picture1.jpg";
    private static final String PATH_TO_IMG_DEST2 = "/home/NIX/lofitskyi/Pictures/picture2.jpg";
    private static final String PATH_TO_IMG_DEST3 = "/home/NIX/lofitskyi/Pictures/picture3.jpg";

    public static void main(String[] args) {
        IOUtils fileUtils = new IOUtilsImpl();

        Reader reader = new StringReader("abcde12345");
        Writer writer = new StringWriter();
        fileUtils.replaceChars(reader, writer, "14c", "YZ0");
        System.out.println(writer.toString());

        String[] paths = fileUtils.findFiles(PATH_TO_START_DIR);
        for (String path: paths){
            System.out.println(path);
        }

        System.out.println("\n----------------------------------------------------------\n");

        String[] pathsExtension = fileUtils.findFiles(PATH_TO_START_DIR, ".log");
        for (String path: pathsExtension){
            System.out.println(path);
        }

        long start;
        long finish;

        start = System.currentTimeMillis();
        fileUtils.copyFile(PATH_TO_IMG, PATH_TO_IMG_DEST1);
        finish = System.currentTimeMillis();
        System.out.printf("Ordinary copying took %d ms\n", finish - start);

        start = System.currentTimeMillis();
        fileUtils.copyFileBuffered(new File(PATH_TO_IMG), new File(PATH_TO_IMG_DEST2));
        finish = System.currentTimeMillis();
        System.out.printf("Buffered copying took %d ms\n", finish - start);

        start = System.currentTimeMillis();
        fileUtils.copyFileBest(PATH_TO_IMG, PATH_TO_IMG_DEST3);
        finish = System.currentTimeMillis();
        System.out.printf("JAVA NIO2 approach to copying took %d ms\n", finish - start);


    }
}
