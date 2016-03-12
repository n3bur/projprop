package drivers;

import domini.Category;
import domini.Page;
import domini.Wikipedia;
import domini.WikipediaException;
import tests.WikipediaTest;

import java.util.Scanner;
import java.util.Set;

/**
 * @author Aleix Pellisa Cortiella
 */

public class DriverWikipedia {

    private static Wikipedia w = new Wikipedia("w");

    private static void writeMenu() {
        System.out.println("------------------");
        System.out.println("Driver de Wikipedia");
        System.out.println("Tria una opcio:");
        System.out.println("1 - GetId");
        System.out.println("2 - AfegirCategoria");
        System.out.println("3 - EsborrarCategoria");
        System.out.println("4 - ObtenirCategoriesAWikipedia");
        System.out.println("5 - BuscarCategoria");
        System.out.println("6 - AfegirPagina");
        System.out.println("7 - EsborrarPagina");
        System.out.println("8 - ObtenirPaginesAWikipedia");
        System.out.println("9 - ObtenirPaginesACategoria");
        System.out.println("10 - BuscarPaginaPerWikipedia");
        System.out.println("11 - BuscarPaginaPerCategoria");
        System.out.println("12 - AfegirEnllaçSubCategoria");
        System.out.println("13 - EsborrarEnllaçSubCategoria");
        System.out.println("14 - ObtenirSubCategoriesAWikipedia");
        System.out.println("15 - ObtenirSubCategoriesACategoria");
        System.out.println("16 - BuscarSubCategoriaPerWikipedia");
        System.out.println("17 - BuscarSubCategoriaPerCategoria");
        System.out.println("18 - AfegirEnllaçSuperCategoria");
        System.out.println("19 - EsborrarEnllaçSuperCategoria");
        System.out.println("20 - ObtenirSuperCategoriesAWikipedia");
        System.out.println("21 - ObtenirSuperCategoriesACategoria");
        System.out.println("22 - BuscarSuperCategoriaPerWikipedia");
        System.out.println("23 - BuscarSuperCategoriaPerCategoria");
        System.out.println("99 - Executartestos");
        System.out.println("0 - Sortir");
        System.out.println("------------------");
    }

    public static void main(String[] args) {
        writeMenu();
        Scanner scan = new Scanner(System.in);
        String option = scan.next();
        while (!option.equals("0")) {
            switch (option) {
                case ("1"):
                    testGetId();
                    break;
                case ("2"):
                    System.out.println("Introdueix el id de la categoria a afegir");
                    try {
                        testAddCategory(scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("3"):
                    System.out.println("Introdueix el id de la categoria a esborrar");
                    try {
                        testRemoveCategory(scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("4"):
                    testObtainCategories();
                    break;
                case ("5"):
                    System.out.println("Introdueix el id de la categoria a buscar");
                    testFindCategory(scan.next());
                    break;
                case ("6"):
                    System.out.println("Introdueix el id de la categoria a afegir la pagina");
                    String id6 = scan.next();
                    System.out.println("Introdueix el id de la pagina a afegir");
                    try {
                        testAddPage(id6,scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("7"):
                    System.out.println("Introdueix el id de la categoria a traure la pagina");
                    String id7 = scan.next();
                    System.out.println("Introdueix el id de la pagina a traure");
                    try {
                        testRemovePage(id7, scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("8"):
                    testObtainPagesInWiki();
                    break;
                case ("9"):
                    System.out.println("Introdueix el id de la categoria a buscar les pagines");
                    testObtainPagesInCategory(scan.next());
                    break;
                case ("10"):
                    System.out.println("Introdueix el id de la pagina a buscar");
                    testFindPageInWiki(scan.next());
                    break;
                case ("11"):
                    System.out.println("Introdueix el id de la categoria a la que vols buscar la pagina");
                    String id11 = scan.next();
                    System.out.println("Introdueix el id de la pagina que vols buscar");
                    testFindPageInCategory(id11, scan.next());
                    break;
                case ("12"):
                    System.out.println("Introdueix el id de la categoria a afegir la SubCategoria");
                    String id12 = scan.next();
                    System.out.println("Introdueix el id de la SubCategoria a afegir");
                    try {
                        testAddSubCategory(id12, scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("13"):
                    System.out.println("Introdueix el id de la categoria a traure la SubCategoria");
                    String id13 = scan.next();
                    System.out.println("Introdueix el id de la SubCategoria a traure");
                    try {
                        testRemoveSubCategory(id13, scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("14"):
                    testObtainSubCategoriesInWiki();
                    break;
                case ("15"):
                    System.out.println("Introdueix el id de la categoria a buscar les Subcategories");
                    testObtainSubCategoriesInCategory(scan.next());
                    break;
                case ("16"):
                    System.out.println("Introdueix el id de la SubCategoria a buscar");
                    testFindSubCategoryInWiki(scan.next());
                    break;
                case ("17"):
                    System.out.println("Introdueix el id de la categoria a la que vols buscar la SubCategoria");
                    String id17 = scan.next();
                    System.out.println("Introdueix el id de la SubCategoria que vols buscar");
                    testFindSubCategoryInCategory(id17, scan.next());
                    break;
                case ("18"):
                    System.out.println("Introdueix el id de la categoria a afegir la SuperCategoria");
                    String id18 = scan.next();
                    System.out.println("Introdueix el id de la SuperCategoria a afegir");
                    try {
                        testAddSuperCategory(id18, scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("19"):
                    System.out.println("Introdueix el id de la categoria a traure la SuperCategoria");
                    String id19 = scan.next();
                    System.out.println("Introdueix el id de la SuperCategoria a traure");
                    try {
                        testRemoveSuperCategory(id19, scan.next());
                    }
                    catch (WikipediaException ex){
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ("20"):
                    testObtainSuperCategoriesInWiki();
                    break;
                case ("21"):
                    System.out.println("Introdueix el id de la categoria a buscar les Supercategories");
                    testObtainSuperCategoriesInCategory(scan.next());
                    break;
                case ("22"):
                    System.out.println("Introdueix el id de la SuperCategoria a buscar");
                    testFindSuperCategoryInWiki(scan.next());
                    break;
                case ("23"):
                    System.out.println("Introdueix el id de la categoria a la que vols buscar la SuperCategoria");
                    String id23 = scan.next();
                    System.out.println("Introdueix el id de la SuperCategoria que vols buscar");
                    testFindSuperCategoryInCategory(id23, scan.next());
                    break;
                case("99"):
                    junit.textui.TestRunner.run(WikipediaTest.class);
                    break;
                default:
                    System.out.println("Introdueix una opcio valida");
                    break;
            }
            System.out.println("------------------");
            System.out.println("Tria una altra opcio o 0 per a sortir");
            System.out.println("------------------");
            option = scan.next();
        }
        scan.close();
    }

    private static void testGetId() {
        System.out.println("El id de la Wikipedia es: " + w.getId());
    }

    private static void testAddCategory(String id) throws WikipediaException{
        w.addCategory(id);
    }

    private static void testRemoveCategory(String id) throws WikipediaException{
        w.removeCategory(id);
    }

    private static void testObtainCategories(){
        Set<Category> set = w.getCategories();
        for (Category c : set){
            System.out.print(c.getId() + " ");
        }
        System.out.println();
    }

    private static void testFindCategory(String id){
        if (w.wikiContainsCategory(id)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }

    private static void testAddPage(String idCategory, String idPage) throws WikipediaException{
        w.addPage(idCategory, idPage);
    }

    private static void testRemovePage(String idCategory, String idPage) throws WikipediaException{
        w.removePage(idCategory, idPage);
    }

    private static void testObtainPagesInWiki(){
        Set<Page> set = w.getTotalPages();
        for (Page p : set){
            System.out.print(p.getId() + " ");
        }
        System.out.println();
    }

    private static void testObtainPagesInCategory(String id){
        Set<Page> set = w.getPages(id);
        if (set == null) {
            System.out.println("No existeix la Categoria: "+id);
            return;
        }
        for (Page p : set){
            System.out.print(p.getId()+" ");
        }
        System.out.println();
    }

    private static void testFindPageInWiki(String id){
        if (w.wikiContainsPage(id)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }

    private static void testFindPageInCategory(String idCategory, String idPage){
        if (w.catContainsPage(idCategory, idPage)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }

    private static void testAddSubCategory(String idCategory, String idSubCategory) throws WikipediaException{
        w.addSub(idCategory, idSubCategory);
    }

    private static void testRemoveSubCategory(String idCategory, String idSubCategory) throws WikipediaException{
        w.removeSub(idCategory, idSubCategory);
    }

    private static void testObtainSubCategoriesInWiki(){
        Set<Category> set = w.getTotalSubs();
        for (Category c : set){
            System.out.print(c.getId()+" ");
        }
        System.out.println();
    }

    private static void testObtainSubCategoriesInCategory(String id){
        Set<Category> set = w.getSubs(id);
        if (set == null) {
            System.out.println("No existeix la Categoria: "+id);
            return;
        }
        for (Category c : set){
            System.out.print(c.getId()+" ");
        }
        System.out.println();
    }

    private static void testFindSubCategoryInWiki(String id){
        if (w.wikiContainsSub(id)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }

    private static void testFindSubCategoryInCategory(String idCategory,String idSubCategory){
        if (w.catContainsSub(idCategory, idSubCategory)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }

    private static void testAddSuperCategory(String idCategory, String idSuperCategory) throws WikipediaException{
        w.addSuper(idCategory, idSuperCategory);
    }

    private static void testRemoveSuperCategory(String idCategory, String idSuperCategory) throws WikipediaException{
        w.removeSuper(idCategory, idSuperCategory);
    }

    private static void testObtainSuperCategoriesInWiki(){
        Set<Category> set = w.getTotalSupers();
        for (Category c : set){
            System.out.print(c.getId()+" ");
        }
        System.out.println();
    }

    private static void testObtainSuperCategoriesInCategory(String id){
        Set<Category> set = w.getSupers(id);
        if (set == null) {
            System.out.println("No existeix la Categoria: "+id);
            return;
        }
        for (Category c : set){
            System.out.print(c.getId()+" ");
        }
        System.out.println();
    }

    private static void testFindSuperCategoryInWiki(String id){
        if (w.wikiContainsSuper(id)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }

    private static void testFindSuperCategoryInCategory(String idCategory,String idSuperCategory){
        if (w.catContainsSuper(idCategory, idSuperCategory)) System.out.println("Hi es");
        else System.out.println("No hi es");
    }
}
