package tests;

import domini.Pair;
import domini.StatisticDomainController;

/**
 * Created by Rubén on 25/05/2015.
 */
public class StatisticDomainControllerTest {
    public static void main (String args[]) {
        StatisticDomainController.initialize();
        for (Pair<Integer, Double> p : StatisticDomainController.getChartPointsLouvain()) System.out.println(p.getFirst()+" "+p.getSecond());
    }
}
