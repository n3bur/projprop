package tests;

import domini.Category;
import domini.Clique;
import domini.GirvanNewman;
import domini.Graph;
import domini.Grapher;
import domini.Louvain;
import domini.Page;
import domini.Solution;
import domini.SolutionDomainController;
import domini.Wikipedia;
import presentation.GraphPanel;

import javax.swing.*;
import java.util.Scanner;

/**
 * @author Oriol Munoz Princep
 */
public class FastWikiTest {
    public static void main(String[] args) throws Exception{
        Wikipedia w = new Wikipedia("GNVictorTest");
        w.setLog(false);
        String[] entrades = new String[5];
        System.out.println("Execution" + (3 - 2));
        Scanner scan = new Scanner(System.in);
        entrades[0] = scan.next();
        while (!entrades[0].equals("stopplease")) {
            for (int i = 1; i < 5; ++i) {
                entrades[i] = scan.next();
            }
            if (entrades[1].equals("cat")) {
                Category c1 = new Category(entrades[0]);
                w.addCategory(c1);
                switch (entrades[2]) {
                    case "CP":
                        Page p = new Page(entrades[3]);
                        w.addPage(c1, p);
                        break;
                    case "CsubC": {
                        Category c2 = new Category(entrades[3]);
                        w.addCategory(c2);
                        w.addSub(c1, c2);
                        break;
                    }
                    case "CsupC": {
                        Category c2 = new Category(entrades[3]);
                        w.addCategory(c2);
                        w.addSuper(c1, c2);
                        break;
                    }
                    default:
                        throw new Exception("not CP CsubC nor CsupC");
                }
            }
            else {
                Page p1 = new Page(entrades[0]);
                if (entrades[2].equals("PC")) {
                    Category c = new Category(entrades[3]);
                    w.addCategory(c);
                    w.addPage(c, p1);
                }
                else throw new Exception("Page -> not PC ????");
            }
            entrades[0] = scan.next();
            System.out.println("scanned " + entrades[0]);
        }
        System.out.print("I finished building the Wiki\n");
        /*Grapher.setPesSerSubOSuper(0f);
        Grapher.setPesNumPagComu(0f);
        Grapher.setPesNumSubCatComu(0f);
        Grapher.setPesNumSuperCatComu(0f);
        Grapher.setPesSimiliaritatNoms(0f);*/
        //SO SLOW
        long before = System.nanoTime();
        Graph<Category> g = Grapher.createGraph(w);
        long after = System.nanoTime();
        System.out.println("Grapher took: " + (after - before));
        //g.print();
        //long before2 = System.nanoTime();
        //FastWikiTestJGraph.res(g);
        //FastWikiTestJGraphT.drawGraph(g);
        SolutionDomainController.setSolution((new Clique()).getSolution(g));
        GraphPanel.showWindow();
        /*System.out.println("Clique took: " + s.getTime()/1000000000);
        System.out.println("Clique gave: " + s.getNumCommunities() + " communities with a total of " + s.getNumNodes() + " nodes");
        //FastWikiTestJung.drawGraph(s);
        JFrame frame = new JFrame("Interactive Graph View 1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GraphPanel());
        frame.setBounds(50, 100,       // Set position
                500, 500);
        //frame.getContentPane().add(new JLabel(new ImageIcon("asdf.jpg")));
        frame.pack();
        frame.setVisible(true);*/
        //ArrayList<Set<Category>> coms = GirvanNewman.GetSolution(g, 10);
        /*Solution sol = Clique.getSolution(g);
        long after2 = System.nanoTime();

        //System.out.println("GN took: " + (before2 - after2));
        //System.out.println("GN gave: " + coms.size() + " communities with " + coms.get(0).size() + " nodes each");

        System.out.println("Clique took: " + (before2/1000000 - after2/1000000));
        System.out.println("Clique gave: " + sol.getNumCommunities() + " communities with a total of " + sol.getNumNodes() + " nodes");*/
    }
}
