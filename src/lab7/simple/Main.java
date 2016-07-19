package lab7.simple;

import interfaces.task7.simple.NamePrinterRunnable;
import interfaces.task7.simple.NamePrinterThread;

public class Main {
    public static void main(String[] args) {
        NamePrinterRunnable runnable1 = new NamePrinterRunnableImpl();
        runnable1.setPrintName("RUNNABLE");
        runnable1.setInterval(1_000);
        runnable1.setCount(10);
        runnable1.setStream(System.out);

        NamePrinterRunnable runnable2 = new NamePrinterRunnableImpl();
        runnable2.setPrintName("RUNNABLE_SHORT");
        runnable2.setInterval(200);
        runnable2.setCount(15);
        runnable2.setStream(System.out);

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);
        t1.start();
        t2.start();

        NamePrinterThread thread1 = new NamePrinterThreadImpl();
        thread1.setPrintName("THREAD");
        thread1.setInterval(1_000);
        thread1.setCount(10);
        thread1.setStream(System.out);

        NamePrinterThread thread2 = new NamePrinterThreadImpl();
        thread2.setPrintName("THREAD_SHORT");
        thread2.setInterval(200);
        thread2.setCount(15);
        thread2.setStream(System.out);

        thread1.start();
        thread2.start();
    }
}
