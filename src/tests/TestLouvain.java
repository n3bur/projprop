package tests;

import domini.*;

import java.util.Scanner;

/**
 * Created by pau on 13/05/15.
 */
public class TestLouvain {

     public static void main(String[] args) {
        System.out.print("Insert pairs of nodes and the weight of its edge. ");
        System.out.println("Set weight -1 to finish input and begin execution. Example:");
        System.out.println("categoryA categoryB 0,5");
        System.out.println("TreesOfNicaragua ClassicTurnOfEvents 0,45");
        System.out.println("TreesOfNicaragua categoryA 0,45");
        System.out.println("randName anotherRandName -1");
        Scanner scan = new Scanner(System.in);

        Graph<Node> g = new Graph<>();

        Category aa = new Category("a");
        Category bb = new Category("b");
        Category cc = new Category("c");
        Category dd = new Category("d");
        g.addNode(aa); g.addNode(bb); g.addNode(cc);g.addNode(dd);
        g.addEdge(aa, bb, new Edge(1));
        g.addEdge(bb, cc, new Edge(1));
        g.addEdge(aa, dd, new Edge(5));
        Category bbb = new Category("b");
        //g.addNode(bbb);
        System.out.println("nodes: " + g.getAllNodes().size());
        //g.addEdge(bbb, dd, new Affinity(5));
        /*
        String a = scan.next();
        String b = scan.next();
        float w = scan.nextFloat();
        while (w != -1) {
            Category c1 = new Category(a);
            Category c2 = new Category(b);
            if (!g.getAllNodes().contains(c1)) g.addNode(c1);
            if (!g.getAllNodes().contains(c2)) g.addNode(c2);
            if (!g.getAdjacentNodesTo(c1).contains(c2)) g.addEdge(c1, c2, new Affinity(w));
            a = scan.next();
            b = scan.next();
            w = scan.nextFloat();
        }*/
        Louvain l = new Louvain();
        Solution s = l.getSolution(g);

        System.out.println("We found " + s.getNumCommunities() + " communities."/* in " + s.getTime()/1000000 + "ms."*/);

        for (Community c : s.getCommunities()) {
            System.out.println("The following nodes form a community:");
            for (Node cat : c.getCommunity()) System.out.print(cat.getId() + " ");
            System.out.println();
        }
        System.out.println("End of this test.");
        System.out.println("----------\n");

         test2();
    }

    private static void test2() {
        Graph<Node> g = new Graph<>();

        Category aa = new Category("a");
        Category bb = new Category("b");
        Category cc = new Category("c");
        Category dd = new Category("d");
        Category ee = new Category("e");
        g.addNode(aa); g.addNode(bb); g.addNode(cc); g.addNode(dd); g.addNode(ee);
        g.addEdge(aa, bb, new Edge(1));
        g.addEdge(bb, cc, new Edge(1));
        g.addEdge(cc, dd, new Edge(5));
        g.addEdge(aa, dd, new Edge(5));
        System.out.println("nodes: " + g.getAllNodes().size());

        Louvain l = new Louvain();
        Solution s = l.getSolution(g);

        System.out.println("We found " + s.getNumCommunities() + " communities."/* in " + s.getTime()/1000000 + "ms."*/);

        for (Community c : s.getCommunities()) {
            System.out.println("The following nodes form a community:");
            for (Node cat : c.getCommunity()) System.out.print(cat.getId() + " ");
            System.out.println();
        }
        System.out.println("End of this test.");
        System.out.println("----------\n");
    }
}