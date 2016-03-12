package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;

/**
 * Controller that saves and loads Wikipedias from disk.
 *  @author Oriol Munoz Princep
 */
public abstract class WikipediaDataController {

    /**
     * The default path to use.
     */
    private static final String DEFAULT_PATH = "data/wikipedia";

    /**
     * Method that reads a wikipedia and returns it as a collection (ArrayList) of collection (ArrayList) of String.
     * @param path the absolute path of the file to read, not null
     * @return the Wikipedia in the specified format.
     * @throws IOException if there was an error when reading (be it the file does not exist, or a disk error)
     * @throws IncorrectFormatException when the file loaded does not correspond to a Wikipedia
     */
    public static ArrayList<ArrayList<String>> readWikipedia(String path) throws IOException, IncorrectFormatException{
        ArrayList<ArrayList<String>> wiki = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String linia;
            int i = 0;
            while ((linia = bf.readLine()) != null) {
                String[] alinia = linia.split("\\s+");
                ensureProperFormat(alinia);
                //wiki.set(i, new ArrayList<String>());
                wiki.add(new ArrayList<String>());
                for (String anAlinia : alinia) {
                    wiki.get(i).add(anAlinia);
                }
                ++i;
            }
            return wiki;
        }
    }

    /**
     * Checks whether or not a line of Strings corresponds to a properly formatted Wikipedia.
     * If the format is not correct, an IncorrectFormatException will be propagated.
     * @param alinia the Array that will be checked, not null
     * @throws IncorrectFormatException when the format is not correct
     */
    private static void ensureProperFormat(String[] alinia) throws IncorrectFormatException{
        //Invalid strings
        if (alinia.length != 5) throw new IncorrectFormatException();
        if (!alinia[1].equals("page") && !alinia[1].equals("cat")) throw new IncorrectFormatException();
        if (!alinia[4].equals("page") && !alinia[4].equals("cat")) throw new IncorrectFormatException();
        //Invalid Relations
        if (alinia[1].equals("page") && !alinia[2].equals("PC")) throw new IncorrectFormatException();
        if (alinia[1].equals("cat") && alinia[2].equals("CP") && !alinia[4].equals("page"))
            throw new IncorrectFormatException();
        if (alinia[1].equals("cat") && (alinia[2].equals("CsubC") || alinia[2].equals("CsupC")) &&
            !alinia[4].equals("cat")) throw new IncorrectFormatException();
    }

    /**
     * Writes the Wikipedia in a proper format (it will be readable afterwards).
     * @param wiki the Wikipedia in an ArrayList of ArrayList of String format, not null
     * @param path the absolute path where the Wikipedia should be saved, not null
     * @throws IOException
     */
    public static void writeWikipedia(ArrayList<ArrayList<String>> wiki, String path) throws IOException{
        File f = new File(path);
        if (f.exists()) throw new FileAlreadyExistsException(path);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (ArrayList<String> a : wiki) {
                for (String s : a) {
                    bw.write(s + " ");
                }
                bw.newLine();
            }
        }
    }

    /**
     * @return Number of files inside the {@link #DEFAULT_PATH} folder inside the project. Only used in tests.
     */
    public static int getNumberOfWikipediasInDisk() {
        return FileUtils.getNumOfFiles(DEFAULT_PATH);
    }

    /**
     * @return The list of files inside the {@link #DEFAULT_PATH} folder inside the project. Only used in tests.
     */
    public static ArrayList<String> getListOfWikipediasInDisk() {
        return FileUtils.getListOfFiles(DEFAULT_PATH);
    }

    /**
     * Deletes the file inside path.
     * @param path the absolute path of the file to be deleted.
     * @throws IOException if there was an error when deleting, e.g. the path was incorrect.
     */
    public static void deleteWikipedia(String path) throws IOException{
        FileUtils.deleteFile(path);
    }
}
