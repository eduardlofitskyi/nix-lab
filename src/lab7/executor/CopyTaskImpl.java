package lab7.executor;

import interfaces.task7.executor.CopyTask;

import java.io.File;
import java.nio.file.*;

public class CopyTaskImpl extends AbstractTask implements CopyTask{

    private String source;
    private String destination;

    @Override
    public void setSource(String source) {
        File file = new File(source);

        if (!file.exists() || !file.canRead()) throw new IllegalArgumentException("Source path doesn't exist", new NoSuchFileException("Not such file"));
        if (Files.notExists(Paths.get(source))) throw new  IllegalArgumentException("Source path doesn't exist",new NoSuchFileException("Not such file"));
        this.source = source;
    }

    @Override
    public void setDest(String dest) {
        if (dest == null) throw new NullPointerException();
        this.destination = dest;
    }

    @Override
    public boolean execute() throws Exception {
        Path srcPath = Paths.get(this.source);
        Path destPath = Paths.get(this.destination);

        try {
            Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e){
            return false;
        }
        return true;
    }
}
