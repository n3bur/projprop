package tests;

import domini.GrapherUtils;
import junit.framework.TestCase;

/**
 * @author Oriol Munoz Princep
 */
public class GrapherUtilsTest extends TestCase{

    public GrapherUtilsTest(String name) {
        super(name);
    }

    public static void main (String[] args) {
        junit.textui.TestRunner.run(GrapherUtilsTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testAreSimilar() {
        assertTrue(GrapherUtils.areSimilar("testo", "testo")); //same
        assertFalse(GrapherUtils.areSimilar("Aleix", "Godlike")); //not even close
        assertTrue(GrapherUtils.areSimilar("testo", "testodesu")); //1st in 2nd
        assertTrue(GrapherUtils.areSimilar("actinobacteria", "bacteria")); //2nd in 1st
        assertFalse(GrapherUtils.areSimilar("srt", "srtExtremelyLong")); //1st.length < 4
        assertTrue(GrapherUtils.areSimilar("random1", "random2")); //close by 1
        assertTrue(GrapherUtils.areSimilar("medicine", "medicina")); //close by 1
        assertTrue(GrapherUtils.areSimilar("medicinal", "medicine")); //close by 2
        assertTrue(GrapherUtils.areSimilar("aaaaaaaaaa", "bbaaaaaaaa")); //80% T
        assertTrue(GrapherUtils.areSimilar("aaaaaaaaaa", "bbbaaaaaaa")); //70% T
        assertFalse(GrapherUtils.areSimilar("bbbbaaaaaa", "aaaaaaaaaaa")); //60% F
        assertTrue(GrapherUtils.areSimilar("dogmatism", "dogmatically")); //same root
        assertTrue(GrapherUtils.areSimilar("democracy", "anti-democratic")); //same root
        assertFalse(GrapherUtils.areSimilar("GrapherUtils", "slitUrehparG")); //inversed
        assertTrue(GrapherUtils.areSimilar("MAJUSCULES", "nomajuscules")); //different case
        assertFalse(GrapherUtils.areSimilar("dogerino", "kapparino")); //somewhat close
    }
}