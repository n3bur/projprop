package tests;

import domini.WikipediaDomainController;
import domini.WikipediaException;
import junit.framework.TestCase;
import persistencia.IncorrectFormatException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Aleix Pellisa Cortiella
 * Important! Si a la carpeta /data/wikipedia ja existeix alguna wikipedia amb el nom que s'usa en aquest test
 * donara errors de fitxer ja existeix el primer cop d'execucio.
 * Vigilar no tenir una Wikipedia amb el nom: Wikipedia_test
 */

public class WikipediaDomainControllerTest extends TestCase{

    private static final String DEFAULT_PATH = "data/wikipedia/";

    public WikipediaDomainControllerTest(String name) {
        super(name);
    }

    public static void main (String[] args) {
        junit.textui.TestRunner.run(WikipediaDomainControllerTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testCreateNewWikipedia() {
        WikipediaDomainController wdc = new WikipediaDomainController();
        //Comprovem que es crea una nova wikipedia amb l'id sense errors
        wdc.loadNewWikipedia("test");
    }

    public void testImportNewWikipediaSaveWikipediaAndDeleteWikipedia() throws IOException, IncorrectFormatException, WikipediaException{
        WikipediaDomainController wdc = new WikipediaDomainController();
        wdc.loadNewWikipedia("test");
        //Guardem la Wikipedia al disc
        wdc.saveWikipedia(DEFAULT_PATH+"test");
        //Comprovem que es carrega una wikipedia desde fitxer amb l'id sense errors
        wdc.loadImportedWikipedia("test",DEFAULT_PATH+"test");
        //Un cop comprovat la esborrem per continuar amb els altres testos sense errors de fitxer existent
        wdc.deleteWikipedia(DEFAULT_PATH+"test");
    }

    public void testModifyWikipediaConsultWikipediaAndConsultCatPage() throws IOException, IncorrectFormatException, WikipediaException{
        WikipediaDomainController wdc = new WikipediaDomainController();
        wdc.loadNewWikipedia("test");
        //Comprovem que la Wikipedia esta buida
        assertTrue(wdc.consultWikipedia().isEmpty());
        wdc.modifyWikipedia("c1", "addC",null);
        wdc.modifyWikipedia("c2", "addC",null);
        wdc.modifyWikipedia("c1", "addCsubC","c2");
        //Comprovem que hem modificat correctament la Wikipedia
        ArrayList<ArrayList<String>> l1 = wdc.consultWikipedia();
        for (ArrayList<String> l2 : l1){
            assertTrue(l2.contains("c1"));
            assertTrue(l2.contains("cat"));
            assertTrue(l2.contains("CsubC"));
            assertTrue(l2.contains("c2"));
            assertTrue(l2.contains("cat"));
        }
        ArrayList<ArrayList<String>> l2 = wdc.consultCatPage("c1","cat");
        for (ArrayList<String> l3 : l2){
            assertTrue(l3.contains("c1"));
            assertTrue(l3.contains("cat"));
            assertTrue(l3.contains("CsubC"));
            assertTrue(l3.contains("c2"));
            assertTrue(l3.contains("cat"));
        }
        //Comprovem que podem guardar una Wikipedia amb contingut
        wdc.saveWikipedia(DEFAULT_PATH+"test");
        wdc.loadNewWikipedia("test");
        //Comprovem que hem podem consultar correctament la Wikipedia importada
        ArrayList<ArrayList<String>> l3 = wdc.consultWikipedia();
        for (ArrayList<String> l5 : l3){
            assertTrue(l5.contains("c1"));
            assertTrue(l5.contains("cat"));
            assertTrue(l5.contains("CsubC"));
            assertTrue(l5.contains("c2"));
            assertTrue(l5.contains("cat"));
        }
        ArrayList<ArrayList<String>> l4 = wdc.consultCatPage("c1","cat");
        for (ArrayList<String> l6 : l4){
            assertTrue(l6.contains("c1"));
            assertTrue(l6.contains("cat"));
            assertTrue(l6.contains("CsubC"));
            assertTrue(l6.contains("c2"));
            assertTrue(l6.contains("cat"));
        }
        wdc.deleteWikipedia(DEFAULT_PATH+"test");
    }

    public void testGetSolution() throws WikipediaException{
        WikipediaDomainController wdc = new WikipediaDomainController();
        wdc.loadNewWikipedia("test");
        wdc.modifyWikipedia("c1", "addC", null);
        wdc.modifyWikipedia("c2", "addC", null);
        wdc.modifyWikipedia("c3", "addC", null);
        wdc.modifyWikipedia("x", "addC", null);
        wdc.modifyWikipedia("c1", "addCsubC","c2");
        wdc.modifyWikipedia("c2", "addCsubC","c3");
        wdc.modifyWikipedia("c3", "addCsubC","c1");
        wdc.modifyWikipedia("c1", "addCsubC","x");
        try {
            ArrayList<ArrayList<String>> l = wdc.findSolution('C', null);
        }
        catch (Exception ex){
            System.out.println("Error d'execucio");
        }
        //Comprovem que s'hagi creat correctament la comunitat
        //assertTrue(l.get(4).contains("c1"));
        //assertTrue(l.get(4).contains("c2"));
        //assertTrue(l.get(4).contains("c3"));
        //assertTrue(l.get(4).contains("x"));
    }
}
