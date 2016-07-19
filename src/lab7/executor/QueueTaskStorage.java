package lab7.executor;

import interfaces.task7.executor.Task;
import interfaces.task7.executor.TasksStorage;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueTaskStorage implements TasksStorage{

    private Queue<Task> queue = new ConcurrentLinkedQueue<>();

    @Override
    public void add(Task task) {
        if (task == null) throw new NullPointerException();
        queue.add(task);
    }

    @Override
    public Task get() {
        return queue.poll();
    }

    @Override
    public int count() {
        return queue.size();
    }
}
