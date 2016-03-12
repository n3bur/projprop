package tests;

import domini.Category;
import domini.Community;
import domini.Solution;
import junit.framework.TestCase;
import java.lang.Exception;

/**
 * @author Ruben Marias
 */

public class SolutionTest extends TestCase {

    public SolutionTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testsetTime() {
        System.out.println("Testing setTime() i getTime()");
        try {
            Solution Sol1 = new Solution();
            Sol1.setTime(3.5d);
            assertEquals(3.5d, Sol1.getTime());
            Sol1.setTime(0); //EXCEPCIO
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION: Time must be positive!");
        } finally {
            System.out.println();
        }
    }

    public void testsetAlg() {
        System.out.println("Testing setAlg() i getAlg()");
        Solution Sol1 = new Solution();
        Sol1.setAlg('C');
        assertEquals('C', Sol1.getAlg());
    }

    public void testSetId() {
        System.out.println("Testing setId() i getId()");
        Solution Sol1 = new Solution();
        Sol1.setId("1111111111111");
        assertEquals("1111111111111", Sol1.getId());
    }

    public void testGetNumCommunities() {
        System.out.println("Testing getNumCommunities() i addCommunity()");
        Solution Sol1 = new Solution();
        for (int i = 0; i < 20; ++i) {
            assertEquals(i, Sol1.getNumCommunities());
            Community S = new Community();
            Sol1.addCommunity(S);
        }
        System.out.println();
    }

    public void testGetNumNodes() {
        System.out.println("Testing getNumNodes() i addNode()");
        Solution Sol = new Solution();
        for (int i = 0; i < 20; ++i) {
            assertEquals(5*i, Sol.getNumNodes());
            Community S = new Community();
            //Add categories to the community so that it does not remain empty
            for (int j = 0; j < 5; ++j) {
                Category C = new Category("Test"+j);
                S.addNode(C);
            }
            Sol.addCommunity(S);
        }
        System.out.println();
    }
}