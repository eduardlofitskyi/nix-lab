package testing;

import interfaces.task5.ArrayCollection;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TestableArrayCollectionImplTest {

    public static final Integer[] VALUES = {1, 2, 3, 4, 5};
    private ArrayCollection<Integer> collection;

    public TestableArrayCollectionImplTest() {
        super();
    }

    @Before
    public void setUp() throws Exception {
        this.collection = new TestableArrayCollectionImpl<>();
    }

    @Test
    public void shouldBeEmptyAfterCreate(){
        assertTrue(collection.isEmpty());
    }

    @Test
    public void shouldChangeCollectionAfterAdd(){
        assertTrue(collection.add(VALUES[0]));
    }

    @Test
    public void shouldIncrementSizeAfterAdd(){
        collection.add(VALUES[0]);

        assertEquals(1, collection.size());
    }

    @Test
    public void shouldChangeCollectionAfterAddAll(){
        assertTrue(collection.addAll(Arrays.asList(VALUES)));
    }

    @Test
    public void shouldCorrectlySetSizeAfterAddAll(){
        collection.addAll(Arrays.asList(VALUES));

        assertEquals(VALUES.length, collection.size());
    }

    @Test(expected = NullPointerException.class)
    public void shouldNotSetNullArray(){
        collection.setArray(null);
    }

    @Test
    public void shouldCorrectlySetArray(){
        collection.setArray(VALUES);

        assertEquals(VALUES.length, collection.size());
    }

    @Test
    public void shouldReturnArrayFromGetArray(){
        collection.setArray(VALUES);
        Object[] array = collection.getArray();

        assertArrayEquals(VALUES, array);
    }

    @Test
    public void shouldReturnArrayWithCorrectSizeFromToArray(){
        collection.setArray(VALUES);
        Object[] array = collection.toArray();

        assertArrayEquals(VALUES, array);
    }

    @Test
    public void shouldCleanUpCollection(){
        collection.setArray(VALUES);
        collection.clear();

        assertTrue(collection.isEmpty());
    }

    @Test
    public void shouldResizeCapacityWhenFull(){
        collection = new TestableArrayCollectionImpl<>(1);
        collection.addAll(Arrays.asList(VALUES));
    }

    @Test
    public void shouldDetermineNullInCollectionByContain(){
        collection.add(null);

        assertTrue(collection.contains(null));
    }

    @Test
    public void shouldCorrectlyDetermineValueInCollectionByContain(){
        collection.add(VALUES[0]);
        collection.add(VALUES[1]);

        assertTrue(collection.contains(VALUES[0]));
        assertTrue(collection.contains(VALUES[1]));
        assertFalse(collection.contains(VALUES[2]));
    }

    @Test
    public void shouldNotChangeCollectionWhenRemoveNotPresentedElement(){
        collection.setArray(VALUES);

        assertFalse(collection.remove(-VALUES[0]));
    }

    @Test
    public void shouldChangeCollectionWhenRemovePresentedElement(){
        collection.setArray(VALUES);

        assertTrue(collection.remove(VALUES[0]));
    }

    @Test
    public void shouldFillGivenArray(){
        Integer[] array = new Integer[5];
        collection.setArray(VALUES);

        collection.toArray(array);
        assertArrayEquals(collection.getArray(), array);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenExecuteRemoveAllWithNull(){
        collection.removeAll(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldFailWhenExecuteRetainAllWithNull(){
        collection.retainAll(null);
    }

    @Test
    public void shouldCorrectlyDetermineContainAll(){
        collection.setArray(VALUES);

        assertTrue(collection.containsAll(Arrays.asList(VALUES)));
        assertFalse(collection.containsAll(Arrays.asList(-VALUES[0])));
    }

    @Test
    public void shouldNotChangeCollectionWhenRemoveAllWithNotPresentedElements(){
        collection.setArray(VALUES);

        assertFalse(collection.removeAll(Arrays.asList(-VALUES[0])));
    }

    @Test
    public void shouldChangeCollectionWhenRemoveAllWithPresentedElements(){
        collection.setArray(VALUES);

        assertTrue(collection.removeAll(Arrays.asList(VALUES[0])));
    }

    @Test
    public void shouldNotChangeCollectionWhenRetainAllWithAllPresentedElements(){
        collection.setArray(VALUES);

        assertFalse(collection.retainAll(Arrays.asList(VALUES)));
    }

    @Test
    public void shouldChangeCollectionWhenRetainAllWithNotAllPresentedElements(){
        collection.setArray(VALUES);
        collection.add(-VALUES[0]);

        assertTrue(collection.retainAll(Arrays.asList(VALUES)));
    }

}