package tests;

import domini.Category;
import domini.Clique;
import domini.Graph;
import domini.Grapher;
import domini.Page;
import domini.Solution;
import domini.SolutionDomainController;
import domini.Wikipedia;
import presentation.GraphPanel;

/**
 * Created by koma on 23/05/15 for prop
 */
public class GraphPanelTest {
    public static void main(String[] args) throws Exception {
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
        w.addCategory(new Category("alone"));

        //Create graph
        Graph<Category> g = Grapher.createGraph(w);

        Solution s = (new Clique()).getSolution(g);
        SolutionDomainController.setSolution(s);

        GraphPanel.showWindow();
    }
}
