package domini;

import java.util.ArrayList;

/**
 * Controller of the Statistic from Domain
 * @author Ruben Marias
 */
public abstract class StatisticDomainController {
    private static Statistic C = new Statistic();
    private static Statistic G = new Statistic();
    private static Statistic L = new Statistic();

    /**
     * Initialization of the controller
     */
    public static void initialize() {
        C = new Statistic();
        G = new Statistic();
        L = new Statistic();
        ArrayList<Solution> Sols = SolutionDomainController.loadSolutions();
        for (Solution s : Sols) {
            char alg = s.getAlg();
            switch (alg) {
                case 'C':
                    C.addSolution(s);
                    break;
                case 'L':
                    L.addSolution(s);
                    break;
                case 'G':
                    G.addSolution(s);
                    break;
            }
        }
    }

    /**
     *
     * @return An array of chart points to be plotted
     */
    public static ArrayList<Pair<Integer, Double>> getChartPointsLouvain() {
        return L.getChartData();
    }

    /**
     *
     * @return An array of chart points to be plotted
     */
    public static ArrayList<Pair<Integer, Double>> getChartPointsGirvan() {
        return G.getChartData();
    }

    /**
     *
     * @return An array of chart points to be plotted
     */
    public static ArrayList<Pair<Integer, Double>> getChartPointsClique() {
        return C.getChartData();
    }

    /**
     *
     * @return Standard deviation of the times taken to obtain solutions with Louvain algorithm
     */
    public static double getSDLouvain() {
        return L.getSD();
    }

    /**
     *
     * @return Standard deviation of the times taken to obtain solutions with Girvan-Newman algorithm
     */
    public static double getSDGirvan() {
        return G.getSD();
    }

    /**
     *
     * @return Standard deviation of the times taken to obtain solutions with Clique algorithm
     */
    public static double getSDClique() {
        return C.getSD();
    }

    /**
     *
     * @return Average generation time of the Louvain Solutions
     */
    public static double getTimeLouvain() {
        return L.getTime();
    }

    /**
     *
     * @return Average generation time of the Girvan-Newman Solutions
     */
    public static double getTimeGirvan() {
        return G.getTime();
    }

    /**
     *
     * @return Average generation time of the Clique Solutions
     */
    public static double getTimeClique() {
        return C.getTime();
    }

    /**
     *
     * @return Average number of communities of the Louvain Solutions
     */
    public static int getCommsLouvain() {
        return L.getNumCommunities();
    }

    /**
     *
     * @return Average number of communities of the Girvan Solutions
     */
    public static int getCommsGirvan() {
        return G.getNumCommunities();
    }

    /**
     *
     * @return Average number of communities of the Clique Solutions
     */
    public static int getCommsClique() {
        return C.getNumCommunities();
    }

    /**
     *
     * @return Average number of nodes of the Louvain Solutions
     */
    public static int getNodesLouvain() {
        return L.getNumNodes();
    }

    /**
     *
     * @return Average number of nodes of the Girvan Solutions
     */
    public static int getNodesGirvan() {
        return G.getNumNodes();
    }

    /**
     *
     * @return Average number of nodes of the Clique Solutions
     */
    public static int getNodesClique() {
        return C.getNumNodes();
    }

    /**
     *
     * @return Number of solutions of the Louvain algorithm
     */
    public static int getSolsLouvain() {
        return L.getNumSolutions();
    }

    /**
     *
     * @return Number of solutions of the Girvan algorithm
     */
    public static int getSolssGirvan() {
        return G.getNumSolutions();
    }

    /**
     *
     * @return Number of solutions of the Clique algorithm
     */
    public static int getSolsClique() {
        return C.getNumSolutions();
    }
}
