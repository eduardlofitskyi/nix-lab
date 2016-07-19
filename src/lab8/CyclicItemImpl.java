package lab8;

import interfaces.task8.CyclicItem;

import java.io.Serializable;

public class CyclicItemImpl implements CyclicItem, Serializable{

    private Object value;
    private transient Object tmpObject;
    private CyclicItem next = this;

    @Override
    public void setValue(Object o) {
        this.value = o;
    }

    @Override
    public Object getValue() {
        return this.value;
    }

    @Override
    public void setTemp(Object o) {
        this.tmpObject = o;
    }

    @Override
    public Object getTemp() {
        return this.tmpObject;
    }

    @Override
    public CyclicItem nextItem() {
        return this.next;
    }

    @Override
    public void setNextItem(CyclicItem nextCyclicItem) {
        this.next = nextCyclicItem;
    }
}
