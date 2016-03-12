package drivers;

import persistencia.IncorrectFormatException;
import persistencia.WikipediaDataController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Oriol Munoz Princep
 */
public class DriverWikipediaDataController {

    private static final String DEFAULT_PATH = "data/wikipedia/";

    private static ArrayList<ArrayList<String>> wiki = null;

    private static void writeMenu() {
        System.out.println("Driver de WikipediaDataController");
        System.out.println("Tria una opció:");
        System.out.println("1 - Veure llistat de Wikipedies en disc");
        System.out.println("2 - Veure nombre de Wikipedies en disc");
        System.out.println("3 - Llegir una Wikipedia");
        System.out.println("4 - Escriure una Wikipedia");
        System.out.println("5 - Esborrar una Wikipedia");
        System.out.println("0 - Sortir");
    }

    public static void main(String[] args) {
        writeMenu();
        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();
        while (option != 0) {
            switch (option) {
                case (1):
                    testGetListOfWikipediasInDisk();
                    break;
                case (2):
                    testGetNumOfWikipediasInDisk();
                    break;
                case (3):
                    System.out.println("Introdueix el nom de la Wikipedia");
                    String name = scan.next();
                    testReadWikipedia(name, scan);
                    break;
                case (4):
                    testWriteWikipedia(scan);
                    break;
                case(5):
                    System.out.println("Introdueix el nom de la Wikipedia");
                    String name2 = scan.next();
                    testDeleteWikipedia(name2);
                    break;
            }
            System.out.println("Tria una altra opció o 0 per a sortir");
            option = scan.nextInt();
        }
        scan.close();
    }

    private static void testGetListOfWikipediasInDisk() {
        for (String s : WikipediaDataController.getListOfWikipediasInDisk()) {
            System.out.println(s);
        }
    }

    private static void testGetNumOfWikipediasInDisk() {
        System.out.println(WikipediaDataController.getNumberOfWikipediasInDisk());
    }

    private static void testReadWikipedia(String name, Scanner scan) {
        try {
            wiki = WikipediaDataController.readWikipedia(DEFAULT_PATH+name);
            System.out.println("Wikipedia llegida correctament. Vols veure-la? 1: sí, 2: no");
            int option2 = scan.nextInt();
            if (option2 == 1) {
                for (ArrayList<String> as : wiki) {
                    for (String s : as) {
                        System.out.print(s+" ");
                    }
                    System.out.println();
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxer no trobat.");
        } catch (IOException ex) {
            System.out.println("Error d'Entrada/Sortida");
        } catch (IncorrectFormatException ex) {
            System.out.println("El format del fitxer no és correcte.");
        }
    }

    private static void testWriteWikipedia(Scanner scan) {
        if (wiki != null) {
            System.out.println("Sembla que tens una Wikipedia carregada. Vols escriure-la?");
            System.out.println("1: sí; 2: no");
            int option2 = scan.nextInt();
            if (option2 == 1) {
                System.out.println("Quin nom tindrà el fitxer?");
                String name = scan.next();
                try {
                    WikipediaDataController.writeWikipedia(wiki, DEFAULT_PATH+name);
                }
                catch(FileAlreadyExistsException ex) {
                    System.out.println("Ja hi ha un fitxer amb aquest nom.");
                }
                catch (IOException ex) {
                    System.out.println("Error d'Entrada/Sortida");
                }
            }
        } else {
            System.out.println("No has carregat cap Wikipedia amb l'opció 3");
        }
    }

    private static void testDeleteWikipedia(String name) {
        try {
            WikipediaDataController.deleteWikipedia(DEFAULT_PATH+name);
        } catch (FileNotFoundException ex) {
            System.out.println("Fitxer no trobat.");
        } catch (IOException ex) {
            System.out.println("Error d'Entrada/Sortida");
        }
    }
}
