package tests;

import domini.*;
import junit.framework.TestCase;
import persistencia.SolutionDataController;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author Rubén Marías Pérez
 */
public class SolutionDomainControllerTest extends TestCase {

    public SolutionDomainControllerTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testLoadSolution() {
        System.out.println("Testing loadSolution()");
        try {
            //Getting a generic solution
            ArrayList<ArrayList<String>> sol = new ArrayList<>();
            ArrayList<String> line = new ArrayList<>();
            line.add("1111111111111"); sol.add(line); line = new ArrayList<>();
            line.add("15"); sol.add(line); line = new ArrayList<>();
            line.add("C"); sol.add(line); line = new ArrayList<>();
            line.add("com1cat1"); line.add("com1cat2"); line.add("com1cat3"); sol.add(line); line = new ArrayList<>();
            line.add("com2cat1"); line.add("com2cat2"); line.add("com2cat3"); sol.add(line); line = new ArrayList<>();
            line.add("com3cat1"); line.add("com3cat2"); line.add("com3cat3");
            sol.add(line);
            SolutionDataController.writeSolution(sol, "C", "1111111111111");
            Solution S = SolutionDomainController.loadSolution("1111111111111");

            assertEquals("1111111111111", S.getId());
            assertEquals((double)15, S.getTime());
            assertEquals('C', S.getAlg());

            ArrayList<Community> comms = S.getCommunities();
            Community c1 = comms.get(0);
            //checking ids of the first community
            ArrayList<String> noms = new ArrayList<>(); noms.add("com1cat1"); noms.add("com1cat2"); noms.add("com1cat3");
            assertEquals(true, noms.contains(c1.getCommunity().get(0).getId()));
            assertEquals(true, noms.contains(c1.getCommunity().get(1).getId()));
            assertEquals(true, noms.contains(c1.getCommunity().get(2).getId()));

            //checking ids of the second community
            Community c2 = comms.get(1);
            noms = new ArrayList<>(); noms.add("com2cat1"); noms.add("com2cat2"); noms.add("com2cat3");
            assertEquals(true, noms.contains(c2.getCommunity().get(0).getId()));
            assertEquals(true, noms.contains(c2.getCommunity().get(1).getId()));
            assertEquals(true, noms.contains(c2.getCommunity().get(2).getId()));

            //checking ids of the third community
            Community c3 = comms.get(2);
            noms = new ArrayList<>(); noms.add("com3cat1"); noms.add("com3cat2"); noms.add("com3cat3");
            assertEquals(true, noms.contains(c3.getCommunity().get(0).getId()));
            assertEquals(true, noms.contains(c3.getCommunity().get(1).getId()));
            assertEquals(true, noms.contains(c3.getCommunity().get(2).getId()));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //clean solutions folder
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testGetSolution() {
        System.out.println("Testing getSolution()");
        try {
            //Getting a generic solution
            ArrayList<ArrayList<String>> sol = new ArrayList<>();
            ArrayList<String> line = new ArrayList<>();
            line.add("1111111111111"); sol.add(line); line = new ArrayList<>();
            line.add("15"); sol.add(line); line = new ArrayList<>();
            line.add("C"); sol.add(line); line = new ArrayList<>();
            line.add("com1cat1"); line.add("com1cat2"); line.add("com1cat3"); sol.add(line); line = new ArrayList<>();
            line.add("com2cat1"); line.add("com2cat2"); line.add("com2cat3"); sol.add(line); line = new ArrayList<>();
            line.add("com3cat1"); line.add("com3cat2"); line.add("com3cat3");
            sol.add(line);
            SolutionDataController.writeSolution(sol, "C", "1111111111111");
            assertEquals(sol, SolutionDomainController.getSolution("1111111111111"));

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //clean solutions folder
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testNumSolutions() {
        System.out.println("Testing numSolution");
        try {
            //Empty folder IDS
            assertEquals(0, SolutionDomainController.numSolutions());

            //Folder with one solution
            ArrayList<ArrayList<String>> mySolution = new ArrayList<>();
            String id = "0000000000000";
            SolutionDataController.writeSolution(mySolution, "C", id);
            assertEquals(1, SolutionDomainController.numSolutions());

            //Folder with 10 solutions
            for (int i = 2; i < 10; ++i) {
                id = String.valueOf(i*1111111)+String.valueOf(i*111111);
                SolutionDataController.writeSolution(mySolution, "C", id);
                assertEquals(i, SolutionDomainController.numSolutions());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //clean solutions folder
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testGetSolutionIds() {
        System.out.println("Testing getSolutionIds");
        try {
            //Empty folder IDS
            ArrayList<String> myArray = new ArrayList<>();
            assertEquals(myArray, SolutionDomainController.getSolutionIds());

            //Folder with one solution
            ArrayList<ArrayList<String>> mySolution = new ArrayList<>();
            String id = "0000000000000";
            SolutionDataController.writeSolution(mySolution, "C", id);
            myArray.add(id);
            assertEquals(myArray, SolutionDomainController.getSolutionIds());

            //Folder with 10 solutions
            for (int i = 2; i < 10; ++i) {
                id = String.valueOf(i*1111111)+String.valueOf(i*111111);
                SolutionDataController.writeSolution(mySolution, "C", id);
                myArray.add(id);
                assertEquals(myArray, SolutionDomainController.getSolutionIds());
            }
        } catch (IOException e) {
              e.printStackTrace();
        } finally {
               //clean solutions folder
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testSaveSolution() throws IOException {
        System.out.println("Testing saveSolution()");
        try {
            //Setting up a new Solution to be stored
            Solution S = new Solution();
            S.setId("1111111111111");
            S.setTime((double) 20);
            S.setAlg('C');
            SolutionDomainController.saveSolution(S);

            S = SolutionDomainController.loadSolution("1111111111111");
            assertEquals("1111111111111", S.getId());
            assertEquals('C', S.getAlg());
            assertEquals((double)20, S.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //clean solutions folder
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main (String[] args) {

    }
}
