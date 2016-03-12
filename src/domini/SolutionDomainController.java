package domini;

import persistencia.SolutionDataController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Controller of the class Solution from package Domain
 * @author Ruben Marias
 */

public abstract class SolutionDomainController {
    private static Solution solution = new Solution();

    /**
     *
     * @param S Solution to be converted into an array of strings
     * @return Array of strings which contain the conversion of @param
     */
    public static ArrayList<ArrayList<String>> formatSolution(Solution S) {
        ArrayList<ArrayList<String>> text = new ArrayList<>();
        ArrayList<String> line = new ArrayList<>();
        String id = S.getId();
        String time = String.valueOf(S.getTime());
        String alg = String.valueOf(S.getAlg());
        line.add(id); text.add(line); line = new ArrayList<>();
        line.add(time); text.add(line); line = new ArrayList<>();
        line.add(alg); text.add(line); line = new ArrayList<>();

        ArrayList<Community> AC = S.getCommunities();
        for (int i = 0; i < S.getNumCommunities(); ++i) {
            Community com = AC.get(i);
            List<Node> cats = com.getCommunity();
            for (int j = 0; j < com.getNumberOfNodes(); ++j) {
                Node cat = cats.get(j);
                String s = cat.getId();
                line.add(s);
            }
            text.add(line);
            line = new ArrayList<>();
        }
        return text;
    }

    /**
     * @param sol Array of strings to be converted into a Solution
     * @return Solution which contains the conversion of @param
     */
    public static Solution unformatSolution(ArrayList<ArrayList<String>> sol) {
        String id = sol.get(0).get(0);
        double time = Double.parseDouble(sol.get(1).get(0));
        char alg = sol.get(2).get(0).charAt(0);

        Solution S = new Solution();
        S.setId(id);
        S.setTime(time);
        S.setAlg(alg);
        for (int i = 3; i < sol.size(); ++i) {
            Community comm = new Community();
            ArrayList<String> cats = sol.get(i);
            for (String s : cats) {
                Category C = new Category(s);
                comm.addNode(C);
            }
            S.addCommunity(comm);
        }
        return S;
    }

    // ----- METODES SOLUTION

    /**
     * @return New solution
     */
    public static Solution getSolution() {
        return solution;
    }

    /**
     * @param S Assign working solution = S
     */
    public static void setSolution (Solution S) {
        solution = S;
    }

    /**
     * @return The ID of the solution
     */
    public static String getSolutionId() {
        return solution.getId();
    }

    /**
     * @return The ID of the solution
     */
    public static void setSolutionId(String id) {
        solution.setId(id);
    }

    /**
     * @return The algorithm of the solution
     */
    public static char getSolutionAlg() {
        return solution.getAlg();
    }

    /**
     * @return The time of the solution
     */
    public static double getSolutionTime() {
        return solution.getTime();
    }

    /**
     * @param alg Algorithm to be set as the solution algorithm
     */
    public static void setSolutionAlg(char alg) {
        solution.setAlg(alg);
    }

    /**
     * @param time Time to be set as the solution time
     */
    public static void setSolutionTime(double time) {
        solution.setTime(time);
    }

    /**
     * @return The number of communities that form the solution
     */
    public static int getNumSolutionCommunities() {
        return solution.getNumCommunities();
    }

    /**
     * @return The communities that form the solution
     */
    public static ArrayList<Community> getSolutionCommunities() {
        return solution.getCommunities();
    }

    public static ArrayList<ArrayList<String>> getSolutionNameCommunities() {
        ArrayList<ArrayList<String>> ret = new ArrayList<>();
        for (Community c : solution.getCommunities()) {
            ArrayList<String> inter = new ArrayList<>();
            for (Node n : c.getCommunity()) inter.add(n.getId());
            ret.add(inter);
        }
        return ret;
    }

    /**
     * @param C remove Community C from the solution
     */
    public static void removeCommunity(Community C) {
        solution.removeCommunity(C);
    }

    /**
     * @param C Add C to the solution
     */
    public static void addCommunity(Community C) {
        solution.addCommunity(C);
    }


    public static int getNumNodesSolution() {
        return solution.getNumNodes();
    }

    // ----- FI METODES SOLUTION

    /**
     * @param id id of Category to be removed from Comm
     * @param index Comm from which Category will be removed
     */
    public static void removeCat(String id, int index) {
        Community com = solution.getCommunities().get(index);
        com.deleteNode(id);
        if (com.getNumberOfNodes() == 0) solution.removeCommunity(com);
    }

    /**
     * @param id id of Category to be added to Comm
     * @param index index of Community to which Cat will be added
     */
    public static void addCategory(String id, int index) {
        Community com = solution.getCommunities().get(index);
        Category cat = new Category(id);
        com.addNode(cat);
    }

    /**
     * Create new solution
     */
    public static void newSolution() {
        solution = new Solution();
    }

    /**
     * @param S Solution to be saved
     */
    public static void saveSolution(Solution S) {
        try {
            ArrayList<ArrayList<String>> sol = formatSolution(S);
            //filename format = S_ALG_ID
            SolutionDataController.writeSolution(sol, String.valueOf(S.getAlg()), S.getId());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param S Solution to be deleted
     * @throws IllegalArgumentException
     */
    public static boolean deleteSolution(Solution S) {
        boolean deleted = false;
        try {
            String id = S.getId();
            deleted = SolutionDataController.deleteSolution(id);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    /**
     *
     * @param idS ID of the Solution to be deleted
     * @return A boolean that indicates wether the solution was successfully deleted
     */
    public static boolean deleteSolution(String idS) {
        boolean deleted = false;
        try {
            deleted = SolutionDataController.deleteSolution(idS);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return deleted;
    }

    /**
     * @return All the names of the files that contain the stored solutions
     */
    public static ArrayList<String> getSolutionIds() {
        ArrayList<String> S = new ArrayList<>();
        try {
            S = SolutionDataController.solutionIds();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return S;
    }

    /**
     *
     * @param path Path to load the solution from
     * @return An array of Strings representing the solution
     * @throws IOException Something went bad
     */
    public static ArrayList<ArrayList<String>> getSolutionFromFile(String path) throws IOException {
        ArrayList<ArrayList<String>> sol = SolutionDataController.readSolutionFromFile(path);
        return sol;
    }

    /**
     *
     * @param oldId ID of the solution to be renamed
     * @param newId New id of the solution
     */

    public static void renameSolution(String oldId, String newId) {
        try {
            if (SolutionDataController.solutionIds().contains(oldId) && !SolutionDataController.solutionIds().contains(newId)) {
                Solution S = SolutionDomainController.loadSolution(oldId);
                S.setId(newId);
                SolutionDomainController.saveSolution(S);
                SolutionDomainController.deleteSolution(oldId);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @return The amount of solutions stored
     */
    public static int numSolutions() {
        int num = 0;
        try {
            num = SolutionDataController.numSolutions();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }

    /**
     * @param id Id of the solution to be loaded
     * @return The solution which has identifier = id
     */
    public static Solution loadSolution(String id) {
        Solution S = new Solution();
        try {
            S = unformatSolution(SolutionDataController.readSolution(id));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return S;
    }

    /**
     * @param id Identifier of the solution to be loaded
     * @return The solution in basic data format
     * @throws IOException
     */
    public static ArrayList<ArrayList<String>> getSolution(String id) throws IOException {
        ArrayList<ArrayList<String>> IDS = new ArrayList<>();
        try {
            IDS = SolutionDataController.readSolution(id);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return IDS;
    }

    /**
     * @return The list of stored solutions
     */
    public static ArrayList<Solution> loadSolutions() {
        ArrayList<Solution> sols = new ArrayList<>();
        try {
            ArrayList<String> ids = SolutionDataController.solutionIds();
            for (String s : ids) {
                Solution S = loadSolution(s);
                sols.add(S);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sols;
    }
}