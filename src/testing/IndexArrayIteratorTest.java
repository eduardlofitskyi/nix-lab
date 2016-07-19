package testing;

import interfaces.task5.ArrayCollection;
import junit.framework.TestCase;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexArrayIteratorTest extends TestCase {

    public static final Integer[] VALUES = {1, 2, 3};

    private ArrayCollection<Integer> collection;
    private Iterator<Integer> iterator;

    public void setUp() throws Exception {
        super.setUp();
        collection = new TestableArrayCollectionImpl<>();
        collection.setArray(VALUES);
        iterator = collection.iterator();
    }

    public void testHasNext() throws Exception {
        assertTrue(iterator.hasNext());
        iterator.next();
        iterator.next();
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    public void testNext() throws Exception {
        assertEquals(VALUES[0], iterator.next());
        assertEquals(VALUES[1], iterator.next());
        assertEquals(VALUES[2], iterator.next());
    }

    public void testNextOutOfBound() throws Exception {
        try {
            iterator.next();
            iterator.next();
            iterator.next();
            iterator.next();

            fail("Missing exception");
        } catch (NoSuchElementException e){
            //NOP
        }
    }

}