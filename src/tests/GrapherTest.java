package tests;

import domini.Category;
import domini.Graph;
import domini.Grapher;
import domini.Page;
import domini.Wikipedia;
import junit.framework.TestCase;

/**
 * @author Oriol Munoz Princep
 */
public class GrapherTest extends TestCase{
    
    private static final float DEFAULT_WEIGHT = 20f;

    public GrapherTest(String name) {
        super(name);
    }

    public static void main (String[] args) {
        junit.textui.TestRunner.run(GrapherTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        //Default values in case they're modified
        Grapher.setPesNumPagComu(DEFAULT_WEIGHT);
        Grapher.setPesNumSubCatComu(DEFAULT_WEIGHT);
        Grapher.setPesNumSuperCatComu(DEFAULT_WEIGHT);
        Grapher.setPesSimiliaritatNoms(DEFAULT_WEIGHT);
        Grapher.setPesSerSubOSuper(DEFAULT_WEIGHT);
    }

    public void testEmptyGraph() {
        Graph<Category> empty = Grapher.createGraph(new Wikipedia("empty"));
        //empty.print(); //shouldn't output anything
        assertTrue("Empty graph is not empty", empty.getAllNodes().isEmpty());
    }

    public void testGraphWith2NonConnectedNodes() {
        Wikipedia w = new Wikipedia("2nonconnected");
        Category c1 = new Category("1");
        Category c2 = new Category("2");
        w.addCategory(c1);
        w.addCategory(c2);
        Graph<Category> twonc = Grapher.createGraph(w);
        //twonc.print();
        assertFalse("Graph is empty", twonc.getAllNodes().isEmpty());
        assertTrue("There are edges in the non-connected graph", twonc.getAllEdges().isEmpty());
        assertTrue("Node c1 connects to something", twonc.getAdjacentNodesTo(c1).isEmpty());
        assertTrue("Node c2 connects to something", twonc.getAdjacentNodesTo(c2).isEmpty());
    }

    public void testGraphWith2NodesWithSimilarName() {
        Wikipedia w = new Wikipedia("2similarname");
        Category c1 = new Category("democracy");
        Category c2 = new Category("anti-democratic");
        w.addCategory(c1);
        w.addCategory(c2);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertFalse("There are no edges", two.getAllEdges().isEmpty());
        assertFalse("Node c1 has no edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertFalse("Node c2 has no edges", two.getAdjacentNodesTo(c2).isEmpty());
        assertEquals(DEFAULT_WEIGHT, two.getEdge(c1, c2).getWeight()); //They're only similar in name
    }

    public void testGraphWith2NodesWithSimilarNameModifiedWeight() {
        Grapher.setPesSimiliaritatNoms(0f);
        assertEquals(0f, Grapher.getPesSimiliaritatNoms());

        Wikipedia w = new Wikipedia("2similarnameMOD");
        Category c1 = new Category("democracy");
        Category c2 = new Category("anti-democratic");
        w.addCategory(c1);
        w.addCategory(c2);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertTrue("There shouldn't be edges: weight of the criteria is 0", two.getAllEdges().isEmpty());
        assertTrue("Node c1 has edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertTrue("Node c2 has edges", two.getAdjacentNodesTo(c2).isEmpty());
    }

    public void testGraphWith2NodesWithSamePage() {
        Wikipedia w = new Wikipedia("2samepage");
        Category c1 = new Category("testo");
        Category c2 = new Category("zetta");
        Page p = new Page("page");
        w.addCategory(c1);
        w.addCategory(c2);
        w.addPage(c1, p);
        w.addPage(c2, p);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertFalse("There are no edges", two.getAllEdges().isEmpty());
        assertFalse("Node c1 has no edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertFalse("Node c2 has no edges", two.getAdjacentNodesTo(c2).isEmpty());
        assertEquals(DEFAULT_WEIGHT, two.getEdge(c1, c2).getWeight()); //They're only similar in one page (100% of pages)
    }

    public void testGraphWith2NodesWithSamePageWithModifiedWeight() {
        Grapher.setPesNumPagComu(0f);
        assertEquals(0f, Grapher.getPesNumPagComu());

        Wikipedia w = new Wikipedia("2samepageMOD");
        Category c1 = new Category("testo");
        Category c2 = new Category("zetta");
        Page p = new Page("page");
        w.addCategory(c1);
        w.addCategory(c2);
        w.addPage(c1, p);
        w.addPage(c2, p);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertTrue("There shouldn't be edges", two.getAllEdges().isEmpty());
        assertTrue("Node c1 has edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertTrue("Node c2 has edges", two.getAdjacentNodesTo(c2).isEmpty());
    }

    public void testGraphWith2NodesWithSameSuper() {
        Wikipedia w = new Wikipedia("2sameSuper");
        Category c1 = new Category("testo");
        Category c2 = new Category("zetta");
        Category supercat = new Category("supercat");
        w.addCategory(c1);
        w.addCategory(c2);
        w.addCategory(supercat);
        w.addSuper(c1, supercat);
        w.addSuper(c2, supercat);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertFalse("There are no edges", two.getAllEdges().isEmpty());
        assertFalse("Node c1 has no edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertFalse("Node c2 has no edges", two.getAdjacentNodesTo(c2).isEmpty());
        assertEquals(DEFAULT_WEIGHT, two.getEdge(c1, c2).getWeight()); //They're only similar in one supercategory
    }

    public void testGraphWith2NodesWithSameSub() {
        Wikipedia w = new Wikipedia("2samesub");
        Category c1 = new Category("testo");
        Category c2 = new Category("zetta");
        Category subcat = new Category("subcat");
        w.addCategory(c1);
        w.addCategory(c2);
        w.addCategory(subcat);
        w.addSub(c1, subcat);
        w.addSub(c2, subcat);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertFalse("There are no edges", two.getAllEdges().isEmpty());
        assertFalse("Node c1 has no edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertFalse("Node c2 has no edges", two.getAdjacentNodesTo(c2).isEmpty());
        assertEquals(DEFAULT_WEIGHT, two.getEdge(c1, c2).getWeight()); //They're only similar in one subcategory
    }

    public void testGraphWith2NodesOneSubOneSuper() {
        Wikipedia w = new Wikipedia("1sub1super");
        Category c1 = new Category("super");
        Category c2 = new Category("sub");
        w.addCategory(c1);
        w.addCategory(c2);
        w.addSuper(c1, c2);
        w.addSub(c2, c1);
        Graph<Category> two = Grapher.createGraph(w);
        assertFalse("Graph is empty", two.getAllNodes().isEmpty());
        assertFalse("There are no edges", two.getAllEdges().isEmpty());
        assertFalse("Node c1 has no edges", two.getAdjacentNodesTo(c1).isEmpty());
        assertFalse("Node c2 has no edges", two.getAdjacentNodesTo(c2).isEmpty());
        assertEquals(DEFAULT_WEIGHT, two.getEdge(c1, c2).getWeight());
    }

    public void testExtendedGraph() {
        Wikipedia w = new Wikipedia("Extended");
        Category c1 = new Category("dogmatic");
        Category c2 = new Category("dogmatism");
        Category c3 = new Category("tonsopages");
        Category c4 = new Category("actinobacteria");
        Category c5 = new Category("estraphilococus");
        Category c6 = new Category("superbacteria");
        //Category c7 = new Category("alone");
        w.addCategory(c1); w.addCategory(c2); w.addCategory(c3);
        w.addCategory(c4); w.addCategory(c5); w.addCategory(c6);
        //w.addCategory(c7);
        //Add pages
        Page[] pages = new Page[5];
        for (int i = 0; i < 5; ++i) {
            pages[i] = new Page(String.valueOf(i));
            w.addPage(c3, pages[i]);
        }
        w.addPage(c4, pages[3]);
        w.addPage(c4, pages[2]);
        w.addPage(c5, pages[2]);
        w.addPage(c1, pages[2]);
        w.addPage(c1, pages[1]);
        w.addPage(c2, pages[4]);
        //Make supers
        w.addSuper(c5, c4);
        w.addSuper(c6, c4);
        //Make 1 sub
        w.addSub(c6, c5);

        //Create graph
        Graph<Category> g = Grapher.createGraph(w);
        //g.print();
        assertFalse("Graph is empty", g.getAllNodes().isEmpty());
        assertFalse("There are no edges", g.getAllEdges().isEmpty());
    }
}