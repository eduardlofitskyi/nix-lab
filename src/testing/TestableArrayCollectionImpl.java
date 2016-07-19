package testing;


import interfaces.task5.ArrayCollection;
import interfaces.task5.ArrayIterator;

import java.util.*;
import java.util.stream.Collectors;

public class TestableArrayCollectionImpl<T> implements ArrayCollection<T>, Iterable<T>{

    public static final int DEFAULT_CAPACITY = 5;
    public static final int CAPACITY_MULTIPLIER = 2;

    private T[] repository;
    private int capacity;
    private int size = 0;

    public TestableArrayCollectionImpl() {
        this(DEFAULT_CAPACITY);
    }

    public TestableArrayCollectionImpl(int capacity) {
        repository = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    @Override
    public Object[] getArray() {
        return Arrays.copyOfRange(repository, 0, size);
    }

    @Override
    public void setArray(T[] ts) {
        if (ts == null) throw new NullPointerException();

        this.repository = ts;
        this.capacity = repository.length;
        this.size = repository.length;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public boolean contains(Object o) {

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
        return new IndexArrayIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOfRange(this.repository, 0, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
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
    }

    @Override
    public boolean remove(Object o) {
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

        if (this.equals(c)) throw new IllegalArgumentException("Cannot add all itself");
        if (c.size() == 0) return false;

        c.forEach(this::add);
        return true;
    }

    @Override
    public void clear() {
        this.size = 0;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
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
        if (c == null) throw new NullPointerException();

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
        if (c == null) throw new NullPointerException();

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

    private class IndexArrayIterator implements ArrayIterator<T>{

        private int pointer = 0;

        @Override
        public Object[] getArray() {
            return Arrays.copyOfRange(repository, 0, size);
        }

        @Override
        public boolean hasNext() {
            return pointer < size;
        }

        @Override
        public T next() {
            T elem;

            try {
                elem = repository[pointer++];
            } catch (ArrayIndexOutOfBoundsException e){
                throw new NoSuchElementException();
            }

            return elem;
        }
    }

}
