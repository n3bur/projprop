package tests;

import domini.*;

import java.util.Scanner;

/**
 * Created by pau on 03/05/15.
 */
public class TestCliqueCustom {

    public static void main(String[] args) {
        System.out.print("Insert pairs of nodes and the weight of its edge. ");
        System.out.println("Set weight -1 to finish input and begin execution. Example:");
        System.out.println("categoryA categoryB 0,5");
        System.out.println("TreesOfNicaragua ClassicTurnOfEvents 0,45");
        System.out.println("TreesOfNicaragua categoryA 0,45");
        System.out.println("randName anotherRandName -1");
        Scanner scan = new Scanner(System.in);
        Graph<Node> g = new Graph<>();
        String a = scan.next();
        String b = scan.next();
        float w = scan.nextFloat();
        while (w != -1) {
            Category c1 = new Category(a);
            Category c2 = new Category(b);
            if (!g.getAllNodes().contains(c1)) g.addNode(c1);
            if (!g.getAllNodes().contains(c2)) g.addNode(c2);
            if (!g.getAdjacentNodesTo(c1).contains(c2)) g.addEdge(c1, c2, new Edge(w));
            a = scan.next();
            b = scan.next();
            w = scan.nextFloat();
        }
        System.out.println("Nodes graph original: " + g.getAllNodes().size());
        System.out.println("Edges graph original: " + g.getAllEdges().size());
        Solution s = new Solution();

        try {
            s = new Clique().getSolution(g);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("We found " + s.getNumCommunities() + " communities in " + s.getTime()/1000000 + "ms.");
        System.out.println("Nodes graph en acabar: " + g.getAllNodes().size());
        System.out.println("Edges graph en acabar: " + g.getAllEdges().size());

        for (Community c : s.getCommunities()) {
            System.out.println("The following nodes form a community:");
            for (Node cat : c.getCommunity()) System.out.print(cat.getId() + " ");
            System.out.println();
        }
        System.out.println("End of this custom test.");
        System.out.println("----------\n");
    }
}
