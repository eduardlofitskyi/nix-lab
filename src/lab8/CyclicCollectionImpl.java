package lab8;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CyclicCollectionImpl implements CyclicCollection, Serializable{

    private List<CyclicItem> items = new LinkedList<>();

    @Override
    public boolean add(CyclicItem cyclicItem) {
        if (cyclicItem == null) throw new NullPointerException();
        if (items.contains(cyclicItem)) throw new IllegalArgumentException("This CyclicItem already exist in given collection");
        return items.add(cyclicItem);
    }

    @Override
    public void insertAfter(CyclicItem prevItem, CyclicItem newItem) {
        if (prevItem == null || newItem == null) throw new NullPointerException();
        if (items.contains(newItem)) throw new IllegalArgumentException("Given new CyclicItem already exist in collection");
        if (!items.contains(prevItem)) throw new IllegalArgumentException("Given CyclicItem doesn't present in collection");

        int i = 0;
        for (CyclicItem item: items){
            if (item.equals(prevItem)) {
                items.add(i+1, newItem);
                break;
            }

            i++;
        }
    }

    @Override
    public CyclicItem getFirst() {
        if (items.size() == 0) return null;
        return items.get(0);
    }

    @Override
    public boolean remove(CyclicItem cyclicItem) {
        if (cyclicItem == null) throw new NullPointerException();
        return items.remove(cyclicItem);
    }

    @Override
    public int size() {
        return items.size();
    }
}
