package tests;

import domini.Category;
import domini.Community;
import domini.Node;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pau
 */
public class TestCommunity extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void test1() {
        Community community = new Community();
        System.out.print("Community created.\n");

        Category a = new Category("a");
        assertTrue(community.addNode(a));
        System.out.print("Category 'a' added.\n");

        assertFalse(community.addNode(a));
        System.out.print("Category 'a' not added.\n");

        Category b = new Category("b");
        Category c = new Category("c");
        assertTrue(community.addNode(b));
        assertTrue(community.addNode(c));
        System.out.print("Category 'b' and 'c' added.\n");

        assertTrue(community.deleteNode("a"));
        System.out.print("Category 'a' removed from the community.\n");

        assertFalse(community.deleteNode("a"));
        System.out.print("Category 'a' not removed from the community.\n");

        assertFalse(community.deleteNode("x"));
        System.out.print("Category 'x' not removed from the community.\n");


        assertFalse(community.checkNode("a"));
        System.out.print("Category 'a' does not belong to the community.\n");

        assertTrue(community.checkNode("b"));
        System.out.print("Category 'a' belongs to the community.\n");

        List<Node> com = community.getCommunity();
        List<Node> expected = new ArrayList<>();
        expected.add(b); expected.add(c);
        assertTrue(com.containsAll(expected) && expected.containsAll(com));
        assertEquals(community.getNumberOfNodes(), 2);
        System.out.print("The community contains only 'b' and 'c'.\n");

        System.out.print("Test finished.\n");

    }

}
