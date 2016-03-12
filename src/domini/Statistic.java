package domini;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Class Statistic
 * @author Ruben Marias
 */

public class Statistic {
    private double time;
    private double sqtime;
    private ArrayList<Solution> solutionsArray;

    /**
     * Create Statistic
     */
    public Statistic() {
        time = 0;
        sqtime = 0;
        solutionsArray = new ArrayList<>();
    }

    /**
     * @param S Solution to be added to the statistic
     */
    public void addSolution(Solution S) {
        solutionsArray.add(S);
        time += S.getTime();
        sqtime += (S.getTime()*S.getTime());
        Collections.sort(solutionsArray, new Comparator<Solution>() {
            @Override
            public int compare(Solution o1, Solution o2) {
                return Integer.compare(o1.getNumNodes(), o2.getNumNodes());
            }
        });
    }

    /**
     *
     * @param S Solution to be deleted
     */

    public void deleteSolution(Solution S) {
        time -= S.getTime();
        sqtime -= (S.getTime()*S.getTime());
        solutionsArray.remove(S);
    }

    /**
     * @return For each number of nodes found in at least one solution, return
     * the avg taken to obtain such solutions. <Nnode, AvgTimes>
     */
    public ArrayList<Pair<Integer, Double>> getChartData() {
        //              numN    avgTime
        ArrayList<Pair<Integer, Double>> chartPoints = new ArrayList<>();
        if(solutionsArray.size() == 0) {
            Pair<Integer, Double> p = new Pair<>();
            p.setFirst(0); p.setSecond((double)0);
            chartPoints.add(p);
            return chartPoints;
        }
        double sumTime = 0;
        int nSols = 0;
        int nPrev = 0;
        boolean first = true;
        for (Solution s : solutionsArray) {
            if (first || s.getNumNodes() == nPrev) {
                first = false;
                ++nSols;
                sumTime += s.getTime();
            }
            else if (s.getNumNodes() != nPrev) {
                Pair<Integer, Double> p = new Pair<>();
                p.setFirst(nPrev);
                p.setSecond(sumTime/nSols);
                chartPoints.add(p);
                nSols = 1;
                sumTime = s.getTime();
            }
            nPrev = s.getNumNodes();
        }
        Pair<Integer, Double> p = new Pair<>();
        p.setFirst(nPrev);
        p.setSecond(sumTime/nSols);
        chartPoints.add(p);

        return chartPoints;
    }

    /**
     * @return Number of solutions that take part in the Statistic
     */
    public int getNumSolutions() {
        return solutionsArray.size();
    }

    /**
     * @return Average time taken to obtain the solutions
     */
    public double getTime() {
        if (solutionsArray.size() > 0) {
            return time / solutionsArray.size();
        }
        else return 0;
    }

    /**
     * @return Average number of the communities of the solutions
     */
    public int getNumCommunities() {
        int numSolutions = solutionsArray.size();
        if (solutionsArray.size() > 0) {
            int numC = 0;
            for (Solution s : solutionsArray) {
                numC += s.getNumCommunities();
            }
            return numC / numSolutions;
        }
        return 0;
    }

    /**
     * @return Average number of solutions per solution that take part in the Statistic
     */
    public int getNumNodes() {
        int numSolutions = solutionsArray.size();
        if (solutionsArray.size() > 0) {
            int numN = 0;
            for (Solution s : solutionsArray) {
                numN += s.getNumNodes();
            }
            return numN / numSolutions;
        }
        return 0;
    }

    /**
     * @return The standard deviation of the times taken to obtain the solutions added to this statistic
     */
    public double getSD() {
        int numSolutions = solutionsArray.size();
        if (numSolutions > 1) return Math.sqrt((sqtime-((time*time)/numSolutions))/(numSolutions-1));
        return 0;
    }
}
