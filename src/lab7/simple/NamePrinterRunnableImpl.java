package lab7.simple;

import interfaces.task7.simple.NamePrinterRunnable;

import java.io.PrintStream;

public class NamePrinterRunnableImpl implements NamePrinterRunnable{

    private String name;
    private long interval;
    private int count;
    private PrintStream stream;

    @Override
    public void setPrintName(String s) {
        if (s.length() == 0) throw new IllegalArgumentException("Name must be at least one character size");
        this.name = s;
    }

    @Override
    public void setStream(PrintStream printStream) {
        if (printStream == null) throw new NullPointerException();
        this.stream = printStream;
    }

    @Override
    public void setInterval(long l) {
        if (l <= 0) throw new IllegalArgumentException("Interval must be at least 1 ms");
        this.interval = l;
    }

    @Override
    public void setCount(int i) {
        if (i <= 0) throw new IllegalArgumentException("Count must be at least 1");
        this.count = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                this.stream.println(this.name);
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
