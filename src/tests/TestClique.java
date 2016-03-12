package tests;

import domini.*;
import junit.framework.TestCase;


/**
 * Created by pau on 01/05/15.
 */
public class TestClique extends TestCase {

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testClique1 () {
        System.out.print("Running Test 1. 9 nodes, 2 communities expected.");
        Graph<Node> g = new Graph<Node>();
        Category a = new Category("a");
        Category b = new Category("b");
        Category c = new Category("c");
        Category d = new Category("d");
        Category e = new Category("e");
        Category f = new Category("f");
        Category x = new Category("x");
        Category y = new Category("y");
        Category z = new Category("z");

        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.addNode(d);
        g.addNode(e);
        g.addNode(f);
        g.addNode(x);
        g.addNode(y);
        g.addNode(z);

        g.addEdge(a, b, new Edge(5.0f));
        g.addEdge(a, c, new Edge(1.0f));
        g.addEdge(a, d, new Edge(1.0f));
        g.addEdge(a, e, new Edge(1.0f));
        g.addEdge(y, z, new Edge(1.0f));
        g.addEdge(a, f, new Edge(3.0f));
        g.addEdge(b, c, new Edge(1.0f));
        g.addEdge(b, d, new Edge(1.0f));
        g.addEdge(b, e, new Edge(1.0f));
        g.addEdge(x, y, new Edge(1.0f));
        g.addEdge(b, f, new Edge(2.0f));
        g.addEdge(c, d, new Edge(1.0f));
        g.addEdge(c, e, new Edge(1.0f));
        g.addEdge(c, f, new Edge(1.0f));
        g.addEdge(d, e, new Edge(1.0f));
        g.addEdge(d, f, new Edge(1.0f));
        g.addEdge(e, f, new Edge(1.0f));

        g.addEdge(c, x, new Edge(1.0f));
        g.addEdge(x, z, new Edge(1.0f));

        System.out.print("\nCalling Clique.getSolution(g)\n");

        Solution solution = new Solution();
        try {
            solution = new Clique().getSolution(g);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Solution expected = new Solution();

        // Expected communities:
        Community c1 = new Community();
        c1.addNode(a); c1.addNode(b); c1.addNode(c);
        c1.addNode(d); c1.addNode(e); c1.addNode(f);
        expected.addCommunity(c1);

        Community c2 = new Community();
        c2.addNode(x); c2.addNode(y); c2.addNode(z);
        expected.addCommunity(c2);

        writeResults(solution);

        boolean itWorks = compareSolutions(solution, expected);
        assertTrue(itWorks);
    }

    public void testClique2() {

        System.out.print("Running Test 2. Empty graph, zero nodes.");
        Graph<Node> g = new Graph<>();

        System.out.print("\nCalling Clique.getSolution(g)\n");

        Solution solution = new Solution();
        try {
            solution = new Clique().getSolution(g);
        } catch (Exception e1) {
            e1.printStackTrace();
        }        Solution expected = new Solution();

        writeResults(solution);

        boolean itWorks = compareSolutions(solution, expected);
        assertTrue(itWorks);
    }

    public void testClique3() {
        System.out.print("Running Test 3. 7 nodes, 2 connected components, 2 communitites expected.");
        Graph<Node> g = new Graph<>();
        Category a = new Category("a");
        Category b = new Category("b");
        Category c = new Category("c");
        Category w = new Category("w");
        Category x = new Category("x");
        Category y = new Category("y");
        Category z = new Category("z");

        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.addNode(w);
        g.addNode(x);
        g.addNode(y);
        g.addNode(z);

        /*
            a - b    w - x
             \ /     | \ |
              c      z - y

         */

        g.addEdge(a, b, new Edge(5.0f));
        g.addEdge(a, c, new Edge(1.0f));
        g.addEdge(b, c, new Edge(1.0f));

        g.addEdge(w, x, new Edge(1.0f));
        g.addEdge(x, y, new Edge(1.0f));
        g.addEdge(y, z, new Edge(1.0f));
        g.addEdge(z, w, new Edge(3.0f));
        g.addEdge(w, y, new Edge(1.0f));


        //System.out.print(g.getAllEdges().toString());
        System.out.print("\nCalling Clique.getSolution(g)\n");

        Solution solution = new Solution();
        try {
            solution = new Clique().getSolution(g);
        } catch (Exception e1) {
            e1.printStackTrace();
        }        Solution expected = new Solution();

        // Expected communities:
        Community c1 = new Community();
        c1.addNode(a); c1.addNode(b); c1.addNode(c);
        expected.addCommunity(c1);

        Community c2 = new Community();
        c2.addNode(w); c2.addNode(x); c2.addNode(y); c2.addNode(z);
        expected.addCommunity(c2);

        writeResults(solution);

        boolean itWorks = compareSolutions(solution, expected);
        assertTrue(itWorks);
    }

    public void testClique4() {
        System.out.print("Running Test 4. 7 nodes, 2 connected components, 2 communitites expected.");
        Graph<Node> g = new Graph<>();
        Category a = new Category("a");
        Category b = new Category("b");
        Category c = new Category("c");
        Category d = new Category("d");
        Category e = new Category("e");
        Category f = new Category("f");
        Category gg = new Category("g");
        Category h = new Category("h");
        Category i = new Category("i");
        Category j = new Category("j");
        Category k = new Category("k");
        Category l = new Category("l");
        Category m = new Category("m");
        Category n = new Category("n");
        Category o = new Category("o");
        Category p = new Category("p");
        Category q = new Category("q");
        Category r = new Category("r");
        Category s = new Category("s");
        Category t = new Category("t");
        Category u = new Category("u");
        Category v = new Category("v");
        Category w = new Category("w");
        Category x = new Category("x");
        Category y = new Category("y");
        Category z = new Category("z");

        g.addNode(a);
        g.addNode(b);
        g.addNode(c);
        g.addNode(d);
        g.addNode(e);
        g.addNode(f);
        g.addNode(gg);
        g.addNode(h);
        g.addNode(i);
        g.addNode(j);
        g.addNode(k);
        g.addNode(l);
        g.addNode(m);
        g.addNode(n);
        g.addNode(o);
        g.addNode(p);
        g.addNode(q);
        g.addNode(r);
        g.addNode(s);
        g.addNode(t);
        g.addNode(u);
        g.addNode(v);
        g.addNode(w);
        g.addNode(x);
        g.addNode(y);
        g.addNode(z);

        System.out.print("\nCalling Clique.getSolution(g)\n");

        Solution solution = new Solution();
        try {
            solution = new Clique().getSolution(g);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Solution expected = new Solution();

        writeResults(solution);

        boolean itWorks = compareSolutions(solution, expected);
        assertTrue(itWorks);

    }

    public void testClique5() {
        System.out.print("Running Test 5. 5 nodes, 2 connected components, 2 communitites expected, one node belongs to both communities.");
        Graph<Node> g = new Graph<>();
        Category a = new Category("a");
        Category b = new Category("b");
        Category c = new Category("c");
        Category d = new Category("d");
        Category e = new Category("e");
        g.addNode(a);
        g.addNode(b); g.addNode(c);
        g.addNode(d); g.addNode(e);

        g.addEdge(a, b, new Edge(1));
        g.addEdge(a, c, new Edge(1));
        g.addEdge(b, c, new Edge(1));
        g.addEdge(c, d, new Edge(1));
        g.addEdge(c, e, new Edge(1));
        g.addEdge(d, e, new Edge(1));

        Solution s = new Solution();
        try {
            s = new Clique().getSolution(g);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Solution expected = new Solution();
        Community abc = new Community();
        abc.addNode(a); abc.addNode(b); abc.addNode(c);
        Community cde = new Community();
        cde.addNode(c); cde.addNode(d); cde.addNode(e);
        expected.addCommunity(abc);
        expected.addCommunity(cde);

        writeResults(s);
        boolean itWorks = compareSolutions(s, expected);
        assertTrue(itWorks);
    }

    public void testClique6() {
        System.out.print("Running Test 6. 5 nodes, 2 connected components, 2 communitites expected, one node belongs to both communities.");
        Graph<Node> g = new Graph<>();
        Category a = new Category("a");
        Category b = new Category("b");
        Category c = new Category("c");
        Category d = new Category("d");
        Category e = new Category("e");
        Category f = new Category("f");
        g.addNode(a); g.addNode(f);
        g.addNode(b); g.addNode(c);
        g.addNode(d); g.addNode(e);
        g.addEdge(e, f, new Edge(1));

        for (Node cat : g.getAllNodes()) {
            for (Node cat2 : g.getAllNodes()) {
                if (cat != cat2 && (cat != e || cat2 != f) && (cat2 != e || cat != f)) g.addEdge(cat, cat2, new Edge(50));
            }
        }

        Category x = new Category("x");
        Category y = new Category("y");
        Category z = new Category("z");
        g.addNode(x); g.addNode(y); g.addNode(z);
        g.addEdge(x, y, new Edge(20));
        g.addEdge(x, z, new Edge(20));
        g.addEdge(y, z, new Edge(20));

        Solution s = new Solution();
        try {
            s = new Clique().getSolution(g);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        Solution expected = new Solution();
        Community abc = new Community();
        abc.addNode(a); abc.addNode(b); abc.addNode(c);
        abc.addNode(d); abc.addNode(e); abc.addNode(f);
        Community cde = new Community();
        cde.addNode(x); cde.addNode(y); cde.addNode(z);
        expected.addCommunity(abc);
        expected.addCommunity(cde);

        writeResults(s);
        boolean itWorks = compareSolutions(s, expected);
        assertTrue(itWorks);
    }

    private void writeResults(Solution solution) {
        System.out.print("We obtained a solution with " + solution.getNumCommunities() + " communities in " + solution.getTime()/1000000 + " ms.\n");
        System.out.print(solution.getNumNodes() + " nodes belong to some community.\n");

        for (Community com : solution.getCommunities()) {
            System.out.print("This nodes belong to the same community: \n");
            for (Node n : com.getCommunity()) System.out.print(n.getId() + " ");
            System.out.println();
        }
        System.out.print("End of this test.");
        System.out.println();
        System.out.print(" --------------- \n");
    }

    private boolean compareSolutions(Solution solution, Solution expected) {

        boolean areEqual = false;

        if (solution.getNumCommunities() == expected.getNumCommunities()) {
            if (solution.getNumCommunities() == 0) return true;
            for (Community com1 : solution.getCommunities()) {
                areEqual = false;
                for (Community com2 : expected.getCommunities()) {
                    if (com1.getCommunity().containsAll(com2.getCommunity()) &&
                            com2.getCommunity().containsAll(com1.getCommunity()))
                        areEqual = true;
                }
                if (!areEqual) return false;
            }
        }
        return areEqual;
    }
}
