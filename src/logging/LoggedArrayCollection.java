package logging;

import interfaces.logging.LoggingArrayCollection;
import interfaces.task5.ArrayIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class LoggedArrayCollection<T> implements LoggingArrayCollection<T>{

    private static final Logger logger = LoggerFactory.getLogger(LoggedArrayCollection.class);

    public static final int DEFAULT_CAPACITY = 5;
    public static final int CAPACITY_MULTIPLIER = 2;

    private T[] repository;
    private int capacity;
    private int size = 0;

    public LoggedArrayCollection() {
        this(DEFAULT_CAPACITY);
    }

    public LoggedArrayCollection(int capacity) {
        repository = (T[]) new Object[capacity];
        this.capacity = capacity;
        logger.trace("Collection has been created with size {}", capacity);
    }

    @Override
    public Object[] getArray() {
        logger.trace("Method getArray() has been executed");
        return Arrays.copyOfRange(repository, 0, size);
    }

    @Override
    public void setArray(T[] ts) {
        if (ts == null) {
            logger.error("Unsuccessful try to set null array");
            throw new NullPointerException();
        }

        this.repository = ts;
        this.capacity = repository.length;
        this.size = repository.length;
        logger.trace("New array has been set to collection: {}", Arrays.toString(ts));
    }

    @Override
    public int size() {
        logger.trace("Method size() has been executed");
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        logger.trace("Method isEmpty() has been executed");
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {

        logger.trace("Method contains(Object o) has been executed with param '{}'", o);

        if (o == null){
            for (int i = 0; i < this.size; i++){
                if (this.repository[i] == null) return true;
            }
            return false;
        }

        T element = (T) o;

        for (int i = 0; i < this.size; i++){
            if (element.equals(repository[i])) return true;
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        logger.trace("New iterator has been created");
        return new LoggedArrayCollection<T>.IndexArrayIterator();
    }

    @Override
    public Object[] toArray() {
        logger.trace("Method toArray() has been executed");
        return Arrays.copyOfRange(this.repository, 0, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        logger.trace("Method toArray(T[] t) has been executed");
        if (a.length >= this.size){
            System.arraycopy(this.repository, 0, a, 0, this.size);
        } else {
            a = (T[]) new Object[this.size];
            System.arraycopy(this.repository, 0, a, 0, this.size);
        }

        return a;
    }

    @Override
    public boolean add(T value){
        logger.trace("Method add(T value) has been executed with param '{}'", value);
        add(value, size);
        return true;
    }

    private void add(T value, int index){
        if (size==capacity){
            resize();
        }

        System.arraycopy(repository, index, repository, index+1, size-index);

        repository[index] = value;
        size++;
    }

    private void resize() {
        T[] newOne =(T[]) new Object[capacity*CAPACITY_MULTIPLIER];
        System.arraycopy(this.repository, 0, newOne, 0, size);

        this.repository = newOne;
        this.capacity*=CAPACITY_MULTIPLIER;
        logger.trace("Capacity has been changed to {}", this.capacity);
    }

    @Override
    public boolean remove(Object o) {
        logger.trace("Method remove(Object o) has been executed with param '{}'", o);

        if (!contains(o)) return false;

        T element = (T) o;

        for (int i = 0; i < this.size; i++){
            if (element.equals(repository[i])){
                System.arraycopy(this.repository, i+1, this.repository, i, size-i-1);
                size--;
                break;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        logger.trace("Method addAll(Collection c) has been executed with param '{}'", c);

        if (this.equals(c)) {
            logger.error("Method addAll(Collection c) has been executed with param reference to itself");
            throw new IllegalArgumentException("Cannot add all itself");
        }
        if (c.size() == 0) return false;

        c.forEach(this::add);
        return true;
    }

    @Override
    public void clear() {
        logger.trace("Method clear() has been executed");
        this.size = 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        logger.trace("Method containsAll(Collection c) has been executed with param '{}'", c);

        Set<T> set = new HashSet<T>();

        int counter = 0;
        for (T outerElem: this.repository){
            if (counter < size) {
                for (Object innerElem : c) {
                    if (outerElem.equals(innerElem)) set.add((T) innerElem);
                }
            }
            counter++;
        }

        return set.size() == c.size();
    }

    @Override
    public boolean removeAll(Collection<?> c) {

        logger.trace("Method removeAll(Collection c) has been executed with param '{}'", c);

        if (c == null) {
            logger.error("Method removeAll(Collection c) has been executed with NULL param");
            throw new NullPointerException();
        }

        Set<T> set = new HashSet<T>();

        int counter = 0;
        for (T outerElem: this.repository){
            if (counter >= size) break;
            set.addAll(c.stream().filter(outerElem::equals).map(innerElem -> (T) innerElem).collect(Collectors.toList()));
            counter++;
        }

        boolean isModified = false;

        for (T elem: set){
            while (this.remove(elem)){
                isModified = true;
            }
        }
        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {

        logger.trace("Method retainAll(Collection c) has been executed with param '{}'", c);

        if (c == null) {
            logger.error("Method retainAll(Collection c) has been executed with NULL param");
            throw new NullPointerException();
        }

        Set<T> set = new HashSet<T>();

        int counter = 0;
        for (T outerElem: this.repository){
            if (counter >= size) break;
            set.addAll(c.stream().filter(outerElem::equals).map(innerElem -> (T) innerElem).collect(Collectors.toList()));
            counter++;
        }

        int oldSize = this.size;

        int size = this.size;
        for (int i = 0; i < size;){
            if (!set.contains(this.repository[i])){
                this.remove(this.repository[i]);
                size--;
                continue;
            }
            i++;
        }

        return oldSize != this.size;
    }

    private class IndexArrayIterator implements ArrayIterator<T> {

        private int pointer = 0;

        @Override
        public Object[] getArray() {
            logger.trace("Method IndexArrayIterator.getArray() has been executed");
            return repository;
        }

        @Override
        public boolean hasNext() {
            logger.trace("Method IndexArrayIterator.hasNext() has been executed");
            return pointer < size;
        }

        @Override
        public T next() {
            T elem;

            try {
                elem = repository[pointer++];
            } catch (ArrayIndexOutOfBoundsException e){
                logger.error("Method IndexArrayIterator.next() go out of bound");
                throw new NoSuchElementException();
            }

            logger.trace("Method IndexArrayIterator.next() has been executed successfully");
            return elem;
        }
    }

    @Override
    public Logger getLogger() {
        logger.trace("Method getLogger() has been executed");
        return logger;
    }
}
