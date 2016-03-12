package drivers;

import domini.Wikipedia;
import domini.WikipediaDomainController;
import domini.WikipediaException;
import persistencia.IncorrectFormatException;
import tests.WikipediaDomainControllerTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author Aleix Pellisa Cortiella
 */

public class DriverWikipediaDomainController {

    private static WikipediaDomainController wdc;

    private static final String DEFAULT_PATH = "data/wikipedia/";

    private static void writeMenu1() {
        System.out.println("------------------");
        System.out.println("Driver de WikipediaDomainController");
        System.out.println("Tria una opcio:");
        System.out.println("1 - Crear una Wikipedia en blanc");
        System.out.println("2 - Carregar una Wikipedia desde fitxer");
        System.out.println("3 - Obtenir Wikipedies a disc");
        System.out.println("9 - Esborrar una Wikipedia guardada");
        System.out.println("99 - ExecutarTest");
        System.out.println("0 - Sortir");
        System.out.println("------------------");

    }

    private static void writeMenu2() {
        System.out.println("------------------");
        System.out.println("Tria una altra opcio:");
        System.out.println("1 - Crear una Wikipedia en blanc");
        System.out.println("2 - Carregar una Wikipedia desde fitxer");
        System.out.println("3 - Obtenir Wikipedies a disc");
        System.out.println("4 - Canviar id de la Wikipedia");
        System.out.println("5 - Modificar la Wikipedia manualment");
        System.out.println("6 - Modificar la Wikipedia des de fitxer");
        System.out.println("7 - Consultar la Wikipedia");
        System.out.println("8 - Consultar una pagina o categoria de la Wikipedia");
        System.out.println("9 - Esborrar una Wikipedia guardada");
        System.out.println("10 - Guardar la Wikipedia");
        System.out.println("11 - Obtenir Solucio");
        System.out.println("99 - ExecutarTest");
        System.out.println("0 - Sortir");
        System.out.println("------------------");
    }

    public static void main(String[] args) throws IOException{
        writeMenu1();
        Scanner scan = new Scanner(System.in);
        Boolean first = false;
        String option = scan.next();
        while (!option.equals("0")) {
            switch (option) {
                case ("1"):
                    System.out.println("Introdueix el id de la Wikipedia");
                    testCreateNewWikipedia(scan.next());
                    first = true;
                    break;
                case ("2"):
                    System.out.println("Introdueix el nom del fitxer");
                    try {
                        first = testImportNewWikipedia(scan.next(), first);
                    }
                    catch (IncorrectFormatException ex){
                        System.out.println(ex.getMessage());
                    }
                    catch (IOException ex){
                        System.out.println("No s'ha trobat el nom del fitxer");
                    }
                    break;
                case ("3"):
                    System.out.println("Les Wikipedies que hi ha a disc son:");
                    testGetListOfWikipediasInDisk();
                    break;
                case ("4"):
                    if (first) {
                        System.out.println("Introdueix el nou id de la Wikipedia");
                        testSetIdWikipedia(scan.next());
                    }
                    break;
                case ("5"):
                    if (first) {
                        System.out.println("------------------");
                        System.out.println("Existeixen aquests tipus de modificacio:");
                        System.out.println("-no hi ha node desti al afegir o traure categoria-");
                        System.out.println("addC, removeC");
                        System.out.println("-afegir i traure pagina de categoria on el node desti es una pagina-");
                        System.out.println("addCP, removeCP");
                        System.out.println("-els seguents tipus de modificacions necesiten node desti una categoria-");
                        System.out.println("addCsubC, removeCsubC, addCsupC, removeCsupC");
                        System.out.println("------------------");
                        System.out.println("Introdueix el id del node origen");
                        String idO = scan.next();
                        System.out.println("Introdueix el tipus de modificacio:");
                        String typeM = scan.next();
                        String idD = null;
                        if (!typeM.equals("addC") && !typeM.equals("removeC")) {
                            System.out.println("Introdueix el id del node desti");
                            idD = scan.next();
                        }
                        try {
                            testModifyWikipediaManually(idO, typeM, idD);
                        }
                        catch (WikipediaException ex){
                            System.out.println(ex.getMessage());
                        }
                    }
                    break;
                case ("6"):
                    if (first) {
                        System.out.println("Introdueix el nom del fitxer");
                        try {
                            testModifyWikipediaFromFile(scan.next());
                        }
                        catch (IncorrectFormatException ex){
                            System.out.println(ex.getMessage());
                        }
                        catch (IOException ex){
                            System.out.println("No s'ha trobat el nom del fitxer");
                        }
                    }
                    break;
                case ("7"):
                    if (first) {
                        System.out.println("Consulta de la Wikipedia amd id: "+testGetIdWikipedia());
                        testConsultWikipedia();
                    }
                    break;
                case ("8"):
                    if (first) {
                        System.out.println("Introdueix el id de la categoria o pagina");
                        String id5 = scan.next();
                        System.out.println("Especifica si es tracta d'una categoria o pagina");
                        System.out.println("Escriu cat o page");
                        String type = scan.next();
                        testConsultCatPage(id5, type);
                    }
                    break;
                case ("9"):
                    System.out.println("Introdueix el nom del fitxer");
                    String id6 = (scan.next());
                    testDeleteWikipedia(id6);
                    break;
                case ("10"):
                    if (first) {
                        testSaveWikipedia();
                    }
                    break;
                case("11"):
                    if (first) {
                        System.out.println("Introdueix el nom de l'algorisme a utilitzar");
                        System.out.println("-louvain, 3-clique, girvan-");
                        testGetSolution((char) scan.nextByte());
                    }
                    break;
                case ("99"):
                    junit.textui.TestRunner.run(WikipediaDomainControllerTest.class);
                    break;
                default:
                    System.out.println("Introdueix una opcio valida");
                    break;
            }
            if (first) writeMenu2();
            else writeMenu1();
            option = scan.next();
        }
        scan.close();
    }

    public static void testCreateNewWikipedia (String id){
        wdc = new WikipediaDomainController();
        wdc.loadNewWikipedia(id);
    }

    public static Boolean testImportNewWikipedia (String id, Boolean first) throws IncorrectFormatException, IOException, WikipediaException {
        if (first) {
            WikipediaDomainController wdc2 = new WikipediaDomainController();
            wdc2.loadImportedWikipedia(id,DEFAULT_PATH+id);
            if (wdc2.consultWikipedia() != null) {
                wdc = wdc2;
            }
            return true;
        } else {
            wdc = new WikipediaDomainController();
            wdc.loadImportedWikipedia(id,DEFAULT_PATH+id);
            if (wdc.consultWikipedia() != null) return true;
        }
        return false;
    }

    public static void testGetListOfWikipediasInDisk(){
        if (wdc == null) wdc = new WikipediaDomainController();
        wdc.getListOfWikipediasInDisk();
    }

    public static String testGetIdWikipedia(){
        return wdc.getIdWikipedia();
    }

    public static void testSetIdWikipedia(String id){
        wdc.setIdWikipedia(id);
    }

    public static void testModifyWikipediaManually(String idO, String typeM, String idD) throws WikipediaException{
        wdc.modifyWikipedia(idO, typeM, idD);
    }

    public static void testModifyWikipediaFromFile(String id) throws WikipediaException, IOException, IncorrectFormatException {
        wdc.loadImportedWikipedia(id,DEFAULT_PATH+id);
    }

    public static void testConsultWikipedia() {
        ArrayList<ArrayList<String>> l = wdc.consultWikipedia();
        if (l.isEmpty()){
            System.out.println("La Wikipedia esta buida");
        }
        else {
            for (ArrayList<String> list : l) {
                Iterator<String> it = list.iterator();
                System.out.println(it.next()+" "+it.next()+" "+it.next()+" "+it.next()+" "+it.next());
            }
        }
    }

    public static void testConsultCatPage(String id, String type) {
        ArrayList<ArrayList<String>> l = wdc.consultCatPage(id, type);
        if (l.isEmpty()){
            if (type.equals("cat"))System.out.println("La Wikipedia no conte la categoria: "+id);
            else System.out.println("La Wikipedia no conte la pagina: "+id);
        }
        else {
            for (ArrayList<String> list : l) {
                Iterator<String> it = list.iterator();
                System.out.println(it.next()+" "+it.next()+" "+it.next()+" "+it.next()+" "+it.next());
            }
        }
    }

    public static void testDeleteWikipedia(String id) {
        if (wdc == null) wdc = new WikipediaDomainController();
        try {
            wdc.deleteWikipedia(DEFAULT_PATH+id);
        }
        catch (IOException ex){
            System.out.println("File not found");
        }
    }

    public static void testSaveWikipedia(){
        try {
            wdc.saveWikipedia(DEFAULT_PATH+wdc.getIdWikipedia());
            System.out.println("Wikipedia guardada");
        }
        catch (IOException name){
            System.out.println("File Already Exists");
        }
    }

    public static void testGetSolution(char algorithm){
        try {
            ArrayList<ArrayList<String>> l = wdc.findSolution(algorithm, null);
            for (int i = 4; i < l.size(); i++) {
                System.out.println("Els seguents nodes formen una comunitat:");
                for (String n : l.get(i)) {
                    System.out.print(n + " ");
                }
                System.out.println();
            }
        }
        catch (Exception ex){
            System.out.println("Error de execucio");
        }
    }
}
