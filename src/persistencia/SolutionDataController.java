package persistencia;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
/**
 * Controller of the data managing from solution
 * @author Ruben Marias
 */

public abstract class SolutionDataController {

    /**
     *
     * @param name ID of the solution whose existance we want to check
     * @return A boolean that tells weteher a solution with id = name exists
     */
    private static boolean existsSolution(String name) {
        File f = new File("./data/solutions/"+name);
        if(f.exists() && !f.isDirectory()) return true;
        return false;
    }

    /**
     * @param id id of the solution to be read
     * @return Array of all the lines in the file as strins. If there is no soultion with id = @param, an empty array is returned
     * @throws IOException
     */
    public static ArrayList<ArrayList<String>> readSolution(String id) throws FileNotFoundException, IOException {
        String defname ="not_my_real_name";
        String name1 = "S_C_"+id;
        String name2 = "S_G_"+id;
        String name3 = "S_L_"+id;
        boolean found = false;
        if (existsSolution(name1)) {
            defname = name1;
            found = true;
        }
        else if (existsSolution(name2)) {
            defname = name2;
            found = true;
        }
        else if (existsSolution(name3)) {
            defname = name3;
            found = true;
        }
        ArrayList<ArrayList<String>> sol = new ArrayList<>();
        if (found) {
            try (BufferedReader bf = new BufferedReader(new FileReader("./data/solutions/" + defname))) {
                String linia = null;
                int i = 0;
                while ((linia = bf.readLine()) != null) {
                    String[] alinia = linia.split("\\s+");
                    sol.add(new ArrayList<String>());
                    for (int j = 0; j < alinia.length; ++j) {
                        sol.get(i).add(alinia[j]);
                    }
                    ++i;
                }
            }
        } else throw new FileNotFoundException("Error when reading, There is no solution with such id");
        return sol;
    }

    /**
     *
     * @param path Path to be read from
     * @return An array of strings containing the solution
     * @throws IOException Shall the read not be completed correctly
     */
    public static ArrayList<ArrayList<String>> readSolutionFromFile(String path) throws IOException {
        ArrayList<ArrayList<String>> sol = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String linia = null;
            int i = 0;
            while ((linia = bf.readLine()) != null) {
                String[] alinia = linia.split("\\s+");
                sol.add(new ArrayList<String>());
                for (int j = 0; j < alinia.length; ++j) {
                    sol.get(i).add(alinia[j]);
                }
                ++i;
            }
        }
        return sol;
    }

    /**
     * @param sol Transformed solution to be written
     * @param id Identifier of the solution to be written
     * @param alg Algorithm used to obtain the solution to be written
     * @throws IOException
     */
    public static void writeSolution(ArrayList<ArrayList<String>> sol, String alg, String id) throws IOException {
        //TODO: codi per comprovar si el directori existeix i crearlo si cal

        File theDir = new File("./data/solutions");
        if (!theDir.exists()) theDir.mkdir();


        if (existsSolution("S_C_"+id)) throw new FileAlreadyExistsException("Ja hi ha una solucio amb aquest identificador.");
        if (existsSolution("S_L_"+id)) throw new FileAlreadyExistsException("Ja hi ha una solucio amb aquest identificador.");
        if (existsSolution("S_G_"+id)) throw new FileAlreadyExistsException("Ja hi ha una solucio amb aquest identificador.");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./data/solutions/S_"+alg+"_"+id))) {
            for (ArrayList<String> a : sol) {
                for (String s : a) {
                    bw.write(s + " ");
                }
                bw.newLine();
            }
        }
    }

    /**
     * @param id Identifier of the solution to be deleted
     * @return Boolean that indicates wheter a solution was deleted
     */
    public static boolean deleteSolution(String id) throws FileNotFoundException {
        String path1 = "./data/solutions/S_C_"+id;
        String path2 = "./data/solutions/S_G_"+id;
        String path3 = "./data/solutions/S_L_"+id;

        boolean deleted = true;

        File f = new File(path1);
        if (!f.delete()) {
            f = new File(path2);
            if (!f.delete()) {
                f =  new File(path3);
                if (!f.delete()) deleted = false;
            }
        }
        if (!deleted) throw new FileNotFoundException("Error when Deleting, There is no solution with such ID");
        return deleted;
    }

    /**
     * @return The amount of stored solutions
     */
    public static int numSolutions() throws IOException {
        File f = new File("./data/solutions");
        int count = 0;
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                count++;
            }
        }
        return count;
    }

    /**
     * @return A list containing the identifiers of all the solutions stored in the correct name format
     */
    public static ArrayList<String> solutionIds() throws Exception {
        ArrayList<String> names = new ArrayList<>();

        File theDir = new File("./data/solutions");
        if (!theDir.exists()) theDir.mkdir();

        String path = "./data/solutions";
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                names.add(files.substring(4));
            }
        }
        return names;
    }
}
