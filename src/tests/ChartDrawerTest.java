package tests;

import domini.Pair;
import presentation.ChartDrawer;

import java.util.ArrayList;

/**
 * Created by Rubén on 22/05/2015.
 */
public class ChartDrawerTest {

    public static void main (String[] args) {
        ArrayList<Pair<Integer, Double>> points = new ArrayList<>();

        Pair<Integer, Double> p1 = new Pair<>();
        p1.setFirst(1);
        p1.setSecond(2.0);
        points.add(p1);

        Pair<Integer, Double> p2 = new Pair<>();
        p2.setFirst(5);
        p2.setSecond(10.0);
        points.add(p2);

        Pair<Integer, Double> p3 = new Pair<>();
        p3.setFirst(10);
        p3.setSecond(15.0);
        points.add(p3);

        ArrayList<Pair<Integer, Double>> points2 = new ArrayList<>();

        Pair<Integer, Double> p4 = new Pair<>();
        p4.setFirst(7);
        p4.setSecond(14.0);
        points2.add(p4);

        Pair<Integer, Double> p5 = new Pair<>();
        p5.setFirst(9);
        p5.setSecond(18.0);
        points2.add(p5);

        Pair<Integer, Double> p6 = new Pair<>();
        p6.setFirst(20);
        p6.setSecond(30.0);
        points2.add(p6);

        ArrayList<Pair<Integer, Double>> points3 = new ArrayList<>();

        Pair<Integer, Double> p7 = new Pair<>();
        p7.setFirst(6);
        p7.setSecond(12.0);
        points3.add(p7);

        Pair<Integer, Double> p8 = new Pair<>();
        p8.setFirst(4);
        p8.setSecond(8.0);
        points3.add(p8);

        Pair<Integer, Double> p9 = new Pair<>();
        p9.setFirst(7);
        p9.setSecond(15.0);
        points3.add(p9);

        ChartDrawer.setClique(true);
        ChartDrawer.setLouvain(true);
        ChartDrawer.setGirvan(true);
        ChartDrawer.setP1(points);
        ChartDrawer.setP2(points2);
        ChartDrawer.setP3(points3);
        ChartDrawer.main(null);
    }
}
