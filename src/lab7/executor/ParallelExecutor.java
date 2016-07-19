package lab7.executor;

import interfaces.task7.executor.Executor;
import interfaces.task7.executor.Task;
import interfaces.task7.executor.TasksStorage;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParallelExecutor implements Executor{

    private TasksStorage storage;
    private AtomicBoolean isStarted = new AtomicBoolean(false);
    private AtomicBoolean tryToStop = new AtomicBoolean(false);

    @Override
    public void setStorage(TasksStorage tasksStorage) {
        this.storage = tasksStorage;
    }

    @Override
    public TasksStorage getStorage() {
        return this.storage;
    }

    @Override
    public void start() {

        if (this.storage == null) throw new NullPointerException();
        if (isStarted.get()) throw new IllegalStateException("Cannot be started twice");
        this.isStarted.set(true);

        Thread thread = new Thread(() -> {


            while (!this.tryToStop.get()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Task task;
                synchronized (this.storage) {
                    if (this.storage.count() == 0) break;
                    task = this.storage.get();
                }

                try {
                    boolean isSuccessful = false;
                    try {
                        isSuccessful = task.execute();
                    } catch (Exception e){
                        isSuccessful = false;
                    }

                    if (!isSuccessful) {
                        task.incTryCount();

                        if (task.getTryCount() == 5) continue;

                        this.storage.add(task);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            this.isStarted.set(false);
            this.tryToStop.set(false);
        });

        thread.start();

    }

    @Override
    public void stop() {
        if (!isStarted.get()) throw new IllegalStateException("Executor cannot be stopped before it was started");

        this.tryToStop.set(true);
    }
}
