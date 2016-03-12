package tests;

import junit.framework.TestCase;
import persistencia.SolutionDataController;
import java.io.File;
import java.io.IOException;
import java.lang.Exception;
import java.util.ArrayList;

/**
 * @author Ruben Marias
 */

public class SolutionDataControllerTest extends TestCase {
    public SolutionDataControllerTest(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testNumSolutions() {
        try {
            //EMPTY FOLDER
            System.out.println("Testing numSolutions()");
            int num = SolutionDataController.numSolutions();
            assertEquals(0, num);

            //FOLDER WITH ONE SOLUTION
            String path = "./data/solutions/";
            String sol = "S_C_0000000000000";
            String p = path + sol;
            File f = new File(p);
            f.createNewFile();
            num = SolutionDataController.numSolutions();
            assertEquals(1, num);

            //FOLDER WITH 2 SOLUTIONS
            sol = "S_C_1111111111111";
            p = path + sol;
            f = new File(p);
            f.createNewFile();
            num = SolutionDataController.numSolutions();
            assertEquals(2, num);

            //FOLDER WITH 10 SOLUTIONS
            for (int i = 2; i < 10; ++i) {
                sol = "S_C_"+String.valueOf(i*1111111)+String.valueOf(i*111111);
                p = path + sol;
                f = new File(p);
                f.createNewFile();
                num = SolutionDataController.numSolutions();
                assertEquals(i+1, num);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //cleaning the folder
        finally {
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testSolutionIds() {
        try {
            //EMPTY FOLDER
            System.out.println("Testing solutionIds()");
            ArrayList<String> sids = SolutionDataController.solutionIds();
            ArrayList<String> check = new ArrayList<>();
            assertEquals(check, sids);

            //FOLDER WITH ONE SOLUTION
            String path = "./data/solutions/";
            String sol = "S_C_0000000000000";
            String p = path + sol;
            File f = new File(p);
            f.createNewFile();
            check.add(sol.substring(4));
            sids = SolutionDataController.solutionIds();
            assertEquals(true, sids.containsAll(check));

            //FOLDER WITH 2 SOLUTIONS
            sol = "S_C_1111111111111";
            p = path + sol;
            f = new File(p);
            f.createNewFile();
            check.add(sol.substring(4));
            sids = SolutionDataController.solutionIds();
            assertEquals(true, sids.containsAll(check));

            //FOLDER WITH 10  SOLUTIONS
            for (int i = 2; i < 10; ++i) {
                sol = "S_C_"+String.valueOf(i*1111111)+String.valueOf(i*111111);
                p = path + sol;
                f = new File(p);
                f.createNewFile();
                check.add(sol.substring(4));
                sids = SolutionDataController.solutionIds();
                assertEquals(true, sids.containsAll(check));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //cleaning the folder
        finally {
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testDeleteSolution() {
        try {
            //DELETING A SOLUTION THAT DOES NOT EXIST; MUST PRODUCE EXEPCTION
            System.out.println("Testing deleteSolution()");
            ArrayList<String> sids = SolutionDataController.solutionIds();
            assertEquals(false, SolutionDataController.deleteSolution("0000000000000"));

            //DELETING A SOLUTION IN A FOLDER WITH ONE SOLUTION
            String path = "./data/solutions/";
            String sol = "S_C_0000000000000";
            String p = path + sol;
            File f = new File(p);
            f.createNewFile();
            assertEquals(true, SolutionDataController.deleteSolution("0000000000000"));

            //DELETING A SOLUTION IN A FOLDER FOLDER WITH 2 SOLUTIONS
            sol = "S_C_1111111111111";
            p = path + sol;
            f = new File(p);
            f.createNewFile();
            sol = "S_C_2222222222222";
            p = path + sol;
            f = new File(p);
            f.createNewFile();
            assertEquals(true, SolutionDataController.deleteSolution("2222222222222"));
        }
        catch (Exception e) {
            e.getMessage();
        }
        //cleaning the folder
        finally {
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void testReadWriteSolution() {
        try {
            //WRITING A SOLUTION and READING IT
            String path = "./data/solutions/";
            String id = "0000000000000";
            String alg ="C";
            ArrayList<ArrayList<String>> mySolution = new ArrayList<>();
            ArrayList<String> line = new ArrayList<>();
            line.add(id); mySolution.add(line); line = new ArrayList<>();
            line.add(alg); mySolution.add(line); line = new ArrayList<>();
            line.add("15"); mySolution.add(line); line = new ArrayList<>();
            line.add("com1cat1"); line.add("com1cat2"); line.add("com1cat3"); mySolution.add(line); line = new ArrayList<>();
            line.add("com2cat1"); line.add("com2cat2"); line.add("com2cat3"); line.add("com2cat4"); line.add("com2cat5"); line.add("com2cat6"); mySolution.add(line); line = new ArrayList<>();
            line.add("com3cat1"); mySolution.add(line);
            SolutionDataController.writeSolution(mySolution,alg,id);
            assertEquals(mySolution, SolutionDataController.readSolution(id));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        //cleaning the folder
        finally {
            try {
                ArrayList<String> ids = SolutionDataController.solutionIds();
                for (String s : ids) SolutionDataController.deleteSolution(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

