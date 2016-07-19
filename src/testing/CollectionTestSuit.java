package testing;

import interfaces.junit.JunitTester;
import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;

public class CollectionTestSuit implements JunitTester{

    private TestSuite suite = new TestSuite();

    @Override
    public TestSuite suite() {
        suite = new TestSuite(IndexArrayIteratorTest.class);
        suite.addTest(new JUnit4TestAdapter(TestableArrayCollectionImplTest.class));
        return suite;
    }
}
