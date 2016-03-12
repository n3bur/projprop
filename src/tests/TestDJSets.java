package tests;

import domini.DJSets;
import junit.framework.TestCase;

/**
 * Created by pau on 03/05/15.
 */
public class TestDJSets extends TestCase {

    public void test() {
        int NumElements = 10;

        DJSets s = new DJSets(NumElements);

        DJSets s1 = new DJSets(0);
        assertEquals(s1.size(), 0);
        for (int i = 0; i < 200; ++i) s1.add();
        assertEquals(s1.size(), 200);

        for (int i = 0; i < 10; ++i) s.add();
        assertEquals(s.size(), 20);
        for (int i = 0; i < s.size(); ++i) assertEquals(s.find(i), i);

        for (int i = 1; i < 5; ++i) {
            s.union(s.find(i), s.find(0));
            //for (int j = 0; j < 10; ++j) System.out.print("("+s.find(j)+","+s.getParent(j)+") ");
        }
        for (int i = 0; i < 5; ++i) assertEquals(s.find(i), s.find(0));
        for (int i = 5; i < s.size(); ++i) assertNotSame(s.find(i), s.find(0));

        s.add();
        s.union(s.find(0), s.find(s.size() - 1));
        assertEquals(s.find(0), s.find(s.size()-1));
        for (int i = 6; i < s.size()-1; ++i) assertNotSame(s.find(0), s.find(i));

        for (int i = 1; i < s.size()-1; ++i) {
            if (s.find(0) != s.find(i)) s.union(s.find(0), s.find(i));
            assertEquals(s.find(i), s.find(0));
        }
    }
}
