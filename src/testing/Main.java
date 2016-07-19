package testing;

import interfaces.junit.JunitTester;
import junit.framework.TestSuite;

public class Main {
    public static void main(String[] args) {
        JunitTester tester = new CollectionTestSuit();
        TestSuite suite = tester.suite();
        System.out.println(suite.countTestCases());
    }
}
