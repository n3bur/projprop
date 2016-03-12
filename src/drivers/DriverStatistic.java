package drivers;

import domini.*;

import java.util.ArrayList;

/**
 * @author Rubén Marías Pérez
 */

public class DriverStatistic {
    public static void main(String args[]) {
        Statistic C = new Statistic();

        Solution Sol1 = new Solution();
        Sol1.setAlg('C');
        Sol1.setTime(3500);
        Community C1 = new Community();
        C1.addNode(new Category("Cat1"));
        C1.addNode(new Category("Cat2"));
        C1.addNode(new Category("Cat3"));
        Community C2 = new Community();
        C2.addNode(new Category("Cat4"));
        C2.addNode(new Category("Cat5"));
        Community C3 = new Community();
        C1.addNode(new Category("Cat6"));
        C1.addNode(new Category("Cat7"));
        C1.addNode(new Category("Cat8"));
        Sol1.addCommunity(C1);
        Sol1.addCommunity(C2);
        Sol1.addCommunity(C3);
        C.addSolution(Sol1);

        Solution Sol2 = new Solution();
        Sol2.setAlg('C');
        Sol2.setTime(500);
        Community C4 = new Community();
        C4.addNode(new Category("Cat1"));
        Community C5 = new Community();
        C5.addNode(new Category("Cat4"));
        C5.addNode(new Category("Cat5"));
        Community C6 = new Community();
        C6.addNode(new Category("Cat6"));
        C6.addNode(new Category("Cat7"));
        C6.addNode(new Category("Cat8"));
        Sol2.addCommunity(C4);
        Sol2.addCommunity(C5);
        Sol2.addCommunity(C6);
        C.addSolution(Sol2);
        System.out.println("Avg NumNodes: " + C.getNumNodes());
        System.out.println("Avg Time: " + C.getTime());
        System.out.println("NumSols: " + C.getNumSolutions());
        System.out.println("SD: " + C.getSD());
        ArrayList<Pair<Integer, Double>> points = C.getChartData();

        for (Pair<Integer, Double> p : points) {
            System.out.println(p.getFirst() + " " + p.getSecond());
        }

        C.deleteSolution(Sol1);
        System.out.println("Avg NumNodes: " + C.getNumNodes());
        System.out.println("Avg Time: " + C.getTime());
        System.out.println("NumSols: " + C.getNumSolutions());
        System.out.println("SD: " + C.getSD());
        points = C.getChartData();
        for (Pair<Integer, Double> p : points) {
            System.out.println(p.getFirst() + " " + p.getSecond());
        }

        C.deleteSolution(Sol2);
        System.out.println("Avg NumNodes: " + C.getNumNodes());
        System.out.println("Avg Time: " + C.getTime());
        System.out.println("NumSols: " + C.getNumSolutions());
        System.out.println("SD: " + C.getSD());
        points = C.getChartData();
        for (Pair<Integer, Double> p : points) {
            System.out.println(p.getFirst() + " " + p.getSecond());
        }
    }

}
