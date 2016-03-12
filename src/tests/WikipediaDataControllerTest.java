package tests;

import persistencia.WikipediaDataController;
import persistencia.IncorrectFormatException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Oriol Munoz Princep
 */
public class WikipediaDataControllerTest {
    public static void main(String[] args) throws IOException{
        try {
            ArrayList<ArrayList<String>> as = WikipediaDataController.readWikipedia("data/wikipedia/copia.txt");

            WikipediaDataController.writeWikipedia(as, "data/wikipedia/"+"copia2");
        } catch (IncorrectFormatException ex) {
            ex.printStackTrace();
            System.out.println(ex.getMessage());
        }
    }
}
