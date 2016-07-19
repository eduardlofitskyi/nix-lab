package lab7.executor;

import interfaces.task7.executor.Task;

public abstract class AbstractTask implements Task{

    private int tryCount = 0;

    @Override
    public int getTryCount() {
        return this.tryCount;
    }

    @Override
    public void incTryCount() {
        this.tryCount++;
    }
}
