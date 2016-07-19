package lab7.executor;

import interfaces.task7.executor.*;

public class Main {

    private static final String PATH_TO_IMG = "/home/NIX/lofitskyi/Downloads/NIX_Screensaver.jpg";
    private static final String PATH_TO_TAR = "/home/NIX/lofitskyi/Downloads/ideaIC-2016.1.3.tar.gz";
    private static final String PATH_TO_IMG_DEST1 = "/home/NIX/lofitskyi/Pictures/picture1.jpg";
    private static final String PATH_TO_IMG_DEST2 = "/home/NIX/lofitskyi/Pictures/picture2.jpg";
    private static final String PATH_TO_TAR_DEST = "/home/NIX/lofitskyi/Pictures/ideaIC-2016.1.3.tar.gz";

    public static void main(String[] args){
        TasksStorage storage = new QueueTaskStorage();

        Executor executor1 = new ParallelExecutor();
        Executor executor2 = new ParallelExecutor();
        Executor executor3 = new ParallelExecutor();
        executor1.setStorage(storage);
        executor2.setStorage(storage);
        executor3.setStorage(storage);

        CopyTask copyTask1 = new CopyTaskImpl();
        CopyTask copyTask2 = new CopyTaskImpl();
        CopyTask copyTask3 = new CopyTaskImpl();
        copyTask1.setSource(PATH_TO_IMG);
        copyTask2.setSource(PATH_TO_IMG);
        copyTask3.setSource(PATH_TO_TAR);
        copyTask1.setDest(PATH_TO_IMG_DEST1);
        copyTask2.setDest(PATH_TO_IMG_DEST2);
        copyTask3.setDest(PATH_TO_TAR_DEST);

        SumTask sumTask1 = new SumTaskImpl();
        SumTask sumTask2 = new SumTaskImpl();
        SumTask sumTask3 = new SumTaskImpl();
        SumTask sumTask4 = new SumTaskImpl();
        SumTask sumTask5 = new SumTaskImpl();
        SumTask sumTask6 = new SumTaskImpl();
        sumTask1.setCount(1_000_000);
        sumTask2.setCount(1_000_000);
        sumTask3.setCount(1_000_000);
        sumTask4.setCount(1_000_000);
        sumTask5.setCount(1_000_000);
        sumTask6.setCount(1_000_000);
        sumTask1.setMax(3_000_000);
        sumTask2.setMax(4_000_000);
        sumTask3.setMax(5_000_000);
        sumTask4.setMax(6_000_000);
        sumTask5.setMax(7_000_000);
        sumTask6.setMax(8_000_000);

        storage.add(copyTask1);
        storage.add(sumTask1);
        storage.add(sumTask2);
        storage.add(sumTask3);
        storage.add(copyTask2);
        storage.add(sumTask4);
        storage.add(sumTask6);
        storage.add(copyTask3);
        storage.add(sumTask6);

        executor1.start();
        executor2.start();
        executor3.start();

    }
}
