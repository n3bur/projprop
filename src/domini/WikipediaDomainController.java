package domini;

import persistencia.WikipediaDataController;
import persistencia.IncorrectFormatException;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

/**
 * Class used to control the Wikipedia domain
 * @author Aleix Pellisa Cortiella
 */

public class WikipediaDomainController {

    private Wikipedia w;

    /** Wikipedia Domain Controller Methods */

    /**
     * @param w Wikipedia to be exported
     * @return returns an array of arrays of strings which are all the links between the components in the Wikipedia
     */
    private ArrayList<ArrayList<String>> exportWikipedia(Wikipedia w){
        ArrayList<ArrayList<String>> wiki = new ArrayList<>();
        if (w != null) {
            for (Category c : w.getCategories()) {
                for (Page p : w.getPages(c)) {
                    wiki.add(new ArrayList<>(Arrays.asList(c.getId(), "cat", "CP", p.getId(), "page")));
                }
            }
            for (Category c : w.getCategories()) {
                for (Category cs : w.getSubs(c)) {
                    wiki.add(new ArrayList<>(Arrays.asList(c.getId(), "cat", "CsubC", cs.getId(), "cat")));
                }
            }
            for (Category c : w.getCategories()) {
                for (Category cs : w.getSupers(c)) {
                    wiki.add(new ArrayList<>(Arrays.asList(c.getId(), "cat", "CsupC", cs.getId(), "cat")));
                }
            }
            return wiki;
        }
        else return null;
    }

    /**
     * Save the current Wikipedia in disk
     * @param path The absolute path where the Wikipedia will be saved
     * @throws IOException If there is a Wikipedia saved with the same Id
     */
    public void saveWikipedia(String path) throws IOException{
        ArrayList<ArrayList<String>> wiki = exportWikipedia(w);
        WikipediaDataController.writeWikipedia(wiki, path);
    }

    /**
     * Delete a Wikipedia with an Id in disk
     * @param path The absolute path where is saved the Wikipedia
     * @throws IOException If there is not a Wikipedia with the Id in disk
     */
    public void deleteWikipedia(String path) throws IOException {
        WikipediaDataController.deleteWikipedia(path);
    }

    /**
     * Create a new Wikipedia with an Id
     * @param id Id of the new Wikipedia
     */
    public void loadNewWikipedia (String id) {
        if (w == null) {
            w = new Wikipedia(id);
        }
    }

    /**
     * Load (or modify the current Wikipedia if there is already created) a Wikipedia with an Id from the disk
     * @param id Id of the Wikipedia from the disk
     * @param path The absolute path where is saved the Wikipedia
     * @throws IOException If there is not a Wikipedia with the Id in disk
     * @throws IncorrectFormatException If the Wikipedia with the Id in disk has incorrect format
     */
    public void loadImportedWikipedia(String id, String path) throws IOException, IncorrectFormatException {
        ArrayList<ArrayList<String>> wikilist = WikipediaDataController.readWikipedia(path);
        if ((w == null)) {
            w = new Wikipedia(id);
        }
        w.setLog(false);
        for (ArrayList<String> l : wikilist) {
            Iterator<String> it = l.iterator();
            String idO = it.next();
            it.next();
            String typeR = it.next();
            String idD = it.next();
            it.next();
            switch (typeR) {
                case ("CsubC"):
                    w.addCategory(idD);
                    w.addCategory(idO);
                    w.addSub(idO, idD);
                    break;
                case ("CsupC"):
                    w.addCategory(idD);
                    w.addCategory(idO);
                    w.addSuper(idO, idD);
                    break;
                case ("CP"):
                    w.addCategory(idO);
                    w.addPage(idO, idD);
                    break;
                case ("PC"):
                    w.addCategory(idD);
                    w.addPage(idD, idO);
                    break;
            }
        }
        w.setLog(true);
    }

    /**
     * @return returns the Id of the current Wikipedia
     */
    public String getIdWikipedia(){
        return w.getId();
    }

    /**
     * Change the Id of the current Wikipedia
     * @param id The new Id od the current Wikipedia
     */
    public void setIdWikipedia(String id){
        if ((w != null)) w.setId(id);
    }

    /**
     * @return returns an array of arrays of strings which are all the links between the components in the Wikipedia
     */
    public ArrayList<ArrayList<String>> consultWikipedia() {
        return exportWikipedia(w);
    }

    /**
     * Consult the content of the node of the Wikipedia
     * @param id Id of the node to consult
     * @param type Type of the node to consult which has to be "cat" or "page"
     * @return returns an array of arrays of strings which are all the links between the node and his components
     * in the Wikipedia
     */
    public ArrayList<ArrayList<String>> consultCatPage(String id, String type){
        ArrayList<ArrayList<String>> wiki = new ArrayList<>();
        if (!w.getCategories().isEmpty()) {
            switch (type) {
                case ("cat"):
                    if (w.wikiContainsCategory(id)) {
                        for (Page p : w.getPages(id)) {
                            wiki.add(new ArrayList<>(Arrays.asList(id, "cat", "CP", p.getId(), "page")));
                        }
                        for (Category c1 : w.getSubs(id)) {
                            wiki.add(new ArrayList<>(Arrays.asList(id, "cat", "CsubC", c1.getId(), "cat")));
                        }
                        for (Category c2 : w.getSupers(id)) {
                            wiki.add(new ArrayList<>(Arrays.asList(id, "cat", "CsupC", c2.getId(), "cat")));
                        }
                    }
                    break;
                case ("page"):
                    if (w.wikiContainsPage(id)) {
                        for (Category c : w.getPageCats(id)) {
                            wiki.add(new ArrayList<>(Arrays.asList(c.getId(), "cat", "CP", id,"page")));
                        }
                    }
                    break;
            }
        }
        return wiki;
    }

    /**
     * Modify the current Wikipedia setting modifications between origin node and destiny node (if needed)
     * @param idO Id of the origin node
     * @param typeM Type of modification has to be:
     *              (there is no destiny node to add and remove Category)
     *              - addC (add Category)
     *              - removeC (remove Category)
     *              (in case to add or remove Page from Category the origin node is a Page)
     *              - addCP (add Page to Category)
     *              - removeCP (remove Page from Category)
     *              (in the other cases the origin node is a Category)
     *              - addCsubC (add Subcategory to Category)
     *              - removeCsubC (remove Subcategory from Category)
     *              - addCsupC (add Supercategory to Category)
     *              - removeCsupC (remove Supercategory from Category)
     * @param idD Id of the destiny node
     * @throws WikipediaException If there is an invalid modification
     */
    public void modifyWikipedia(String idO, String typeM, String idD) throws WikipediaException{
        switch (typeM) {
            case ("addC") :
                w.addCategory(idO);
                break;
            case ("removeC") :
                w.removeCategory(idO);
                break;
            case ("addCP") :
                w.addPage(idO,idD);
                break;
            case ("removeCP") :
                w.removePage(idO,idD);
                break;
            case ("addCsubC") :
                w.addSub(idO,idD);
                break;
            case ("removeCsubC") :
                w.removeSub(idO, idD);
                break;
            case ("addCsupC") :
                w.addSuper(idO,idD);
                break;
            case ("removeCsupC") :
                w.removeSuper(idO,idD);
                break;
            default:
                System.out.println("Wrong type parameter");
        }
    }

    /**
     * Find a solution using the given parameters.
     * @param algorithm The character id of the algorithm to be used.
     * @param crit The weights of the criteria to be taken into account.
     * @return the solution formated as Strings.
     * @throws Exception
     */
    public ArrayList<ArrayList<String>> findSolution(char algorithm, ArrayList<Float> crit) throws Exception {
        Solution s = new Solution();
        if (crit != null && crit.size() == 5) {
            Grapher.setPesSimiliaritatNoms(crit.get(0));
            Grapher.setPesNumSuperCatComu(crit.get(1));
            Grapher.setPesNumSubCatComu(crit.get(2));
            Grapher.setPesNumPagComu(crit.get(3));
            Grapher.setPesSerSubOSuper(crit.get(4));
        }
        Graph<Category> g = Grapher.createGraph(w);
        switch (algorithm) {
            case 'L':
                s = new Louvain().getSolution(g);
                break;
            case 'C':
                s = new Clique().getSolution(g);
                break;
            case 'G':
                s = new GirvanNewman().getSolution(g);
                break;
        }
        SolutionDomainController.setSolution(s);
        String solId = SolutionDomainController.getSolutionId();
        SolutionDomainController.setSolutionId(w.getId() + solId);
        SolutionDomainController.saveSolution(s);
        return SolutionDomainController.formatSolution(s);
    }

    /**
     * Show in the console all the wikipedias in disk
     */
    public void getListOfWikipediasInDisk(){
        ArrayList<String> l = WikipediaDataController.getListOfWikipediasInDisk();
        for (String s : l){
            System.out.print(s+" ");
        }
        System.out.println();
    }

    /** Advanced Consults */

    /**
     * @return returns a set of the categories in the Wikipedia
     */
    public Set<Category> obtainCategories(){
        return w.getCategories();
    }

    /**
     * @param set A Set of categories
     * @return returns a SortedSet of these categories
     */
    public SortedSet<String> obtainSortedSetCategories(Set<Category> set){
        return w.sortSetCategories(set);
    }

    /**
     * @param set A Set of pages
     * @return returns a SortedSet of these pages
     */
    public SortedSet<String> obtainSortedSetPages(Set<Page> set){
        return w.sortSetPages(set);
    }

    /**
     * @param id Id of the Category to be found
     * @return returns a boolean depending on whether the Wikipedia contains the Category
     */
    public boolean findCategory(String id){
        return (w.wikiContainsCategory(id));
    }

    /**
     * @param id Id of the Category to get the pages
     * @return returns a set of pages which are in the Category
     */
    public Set<Page> obtainPagesInCategory(String id){
        return w.getPages(id);
    }

    /**
     * @param id Id of the Page to get the categories
     * @return returns a set of categories which have the Page
     */
    public Set<Category> obtainCategoriesInPage(String id) { return w.getPageCats(id); }

    /**
     * @return returns a set of the pages in the Wikipedia
     */
    public Set<Page> obtainPagesInWiki(){
        return w.getTotalPages();
    }

    /**
     * @param id Id of the Page to found
     * @return returns a boolean depending on whether the Wikipedia contains the Page
     */
    public boolean findPageInWiki(String id){
        return w.wikiContainsPage(id);
    }

    /**
     * @param idCategory Id of the Category to found the Page
     * @param idPage Id of the Page to found
     * @return returns a boolean depending on whether the Category contains the Page
     */
    public boolean findPageInCategory(String idCategory, String idPage){
        return w.catContainsPage(idCategory, idPage);
    }

    /**
     * @param id Id of the Category to get the subcategories
     * @return returns a set of subcategories which are in the Category
     */
    public Set<Category> obtainSubCategoriesInCategory(String id){
        return w.getSubs(id);
    }

    /**
     * @param id Id of the Subcategory to get the categories
     * @return returns a set of categories which have the Subcategory
     */
    public Set<Category> obtainCategoriesInSubCategory(String id) { return w.getIsSub(id); }

    /**
     * @return returns a set of the subcategories in the Wikipedia
     */
    public Set<Category> obtainSubCategoriesInWiki(){
        return w.getTotalSubs();
    }

    /**
     * @param id Id of the Subcategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Subcategory
     */
    public boolean findSubCategoryInWiki(String id){
        return w.wikiContainsSub(id);
    }

    /**
     * @param idCategory Id of the Category to found the Subcategory
     * @param idSubCategory Id of the Subcategory to found
     * @return returns a boolean depending on whether the Category contains the Subcategory
     */
    public boolean findSubCategoryInCategory(String idCategory,String idSubCategory){
        return w.catContainsSub(idCategory, idSubCategory);
    }

    /**
     * @param id Id of the Category to get the sueprcategories
     * @return returns a set of supercategories which are in the Category
     */
    public Set<Category> obtainSuperCategoriesInCategory(String id){
        return w.getSupers(id);
    }

    /**
     * @param id Id of the Supercategory to get the categories
     * @return returns a set of categories which have the Supercategory
     */
    public Set<Category> obtainCategoriesInSuperCategory(String id) { return w.getIsSuper(id); }

    /**
     * @return returns a set of the supercategories in the Wikipedia
     */
    public Set<Category> obtainSuperCategoriesInWiki(){
        return w.getTotalSupers();
    }

    /**
     * @param id Id of the Supercategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Supercategory
     */
    public boolean findSuperCategoryInWiki(String id){
        return w.wikiContainsSuper(id);
    }

    /**
     * @param idCategory Id of the Category to found the Supercategory
     * @param idSuperCategory Id of the Supercategory to found
     * @return returns a boolean depending on whether the Category contains the Supercategory
     */
    public boolean findSuperCategoryInCategory(String idCategory,String idSuperCategory){
        return w.catContainsSuper(idCategory, idSuperCategory);
    }
}
