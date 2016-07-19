package lab8;

import interfaces.task8.CyclicCollection;
import interfaces.task8.CyclicItem;
import interfaces.task8.SerializableUtils;

import java.io.*;

public class Main {

    public static CyclicCollection collection = new CyclicCollectionImpl();
    public static SerializableUtils util = new SerializableUtilsImpl();

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        CyclicItem item1 = new CyclicItemImpl();
        CyclicItem item2 = new CyclicItemImpl();
        CyclicItem item3 = new CyclicItemImpl();

        item1.setNextItem(item2);
        item2.setNextItem(item3);
        item1.setValue(item1);
        item2.setValue(item2);
        item3.setValue(item1);
        item1.setTemp(new Object());
        item2.setTemp(new Object());
        item3.setTemp(new Object());

        collection.add(item1);
        collection.add(item2);
        collection.insertAfter(item1, item3);

        System.out.printf("Collection size before serialization = %d\n", collection.size());

        util.serialize(new ObjectOutputStream(new FileOutputStream(new File("/home/NIX/lofitskyi/Documents/obj.txt"))), collection);
        collection = null;
        collection = (CyclicCollection) util.deserialize(new ObjectInputStream(new FileInputStream(new File("/home/NIX/lofitskyi/Documents/obj.txt"))));

        System.out.printf("Collection size size serialization = %d\n", collection.size());

        CyclicCollection anotherCollection = (CyclicCollection) util.deserialize(new ObjectInputStream(new FileInputStream(new File("/home/NIX/lofitskyi/Documents/obj.txt"))));

        System.out.printf("Equality two objects from the same bytecode is [%s]\n", collection.equals(anotherCollection));

    }
}
