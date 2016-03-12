package persistencia;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class contains helper methods for other data classes.
 * @author Oriol Munoz Princep
 */
abstract class FileUtils {
    /**
     * Method to get the list of files inside a directory.
     * @param path absolute path of the directory to list, not null
     * @return The list of files inside path.
     */
    static ArrayList<String> getListOfFiles(String path) {
        ArrayList<String> list = new ArrayList<>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File f : listOfFiles) {
                if (f.isFile()) {
                    list.add(f.getName());
                }
            }
        }
        return list;
    }

    /**
     * Method to get the number of files inside a directory-
     * @param path absolute path of the directory to list, not null
     * @return The number of files inside path.
     */
    static int getNumOfFiles(String path) {
        int num = 0;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File f : listOfFiles) {
                if (f.isFile()) ++num;
            }
        }
        return num;
    }

    /**
     * Method that deletes the file inside path with name name.
     * @param path absolute path of the directory of the file, not null
     * @throws IOException
     */
    static void deleteFile(String path) throws IOException{
        File f = new File(path);
        if (f.exists() && f.isFile()) {
            //noinspection ResultOfMethodCallIgnored
            f.delete();
        } else throw new FileNotFoundException();
    }
}
