package presentation;

import domini.*;
import persistencia.IncorrectFormatException;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

/**
 * Controller class for the presentation
 * @author Aleix Pellisa Cortiella
 */
public class PresentationController {

    /** First View */
    private FirstView firstView;

    /** Help View */
    private HelpView helpView;

    /** Wikipedia Controller */
    private WikipediaDomainController wikiCtrl;

    /**
     * Method to initialize the presentation
     */
    public void initializePresentation() {
        firstView = new FirstView(this);
        firstView.makeVisible();
    }

    private boolean working = false;
    private boolean first = false;
    public void setWorking(boolean b) {working= b;}
    public boolean getWorking() {return working;}
    public Boolean getFirst(){
        return first;
    }

    public WikipediaDomainController getWikiCtrl() {
        return wikiCtrl;
    }

    /**
     * Method to enable First View
     */
    public void enableFirstView(){
        firstView.enable();
    }

    /** Synchronize Methods */

    /**
     * Method to synchronize the First View with WikipediaManagementView
     */
    public void synToWikipediaManagementView() {
        firstView.initialize_WikipediaManagementView();
    }

    /**
     * Method to synchronize the First View with MainView
     */
    public void synToMainView() {
        firstView.initialize_MainView();
    }

    /**
     * Method to synchronize the First View with ModificationViewWikipedia
     */
    public void synToModificationViewWikipedia() {
        firstView.initialize_ModificationViewWikipedia();
    }

    /**
     * Method to synchronize the First View with AdvancedConsultViewWikipedia
     */
    public void synToAdvancedConsultViewWikipedia() {
        firstView.initialize_AdvancedConsultViewWikipedia();
    }

    /**
     * Method to synchronize the First View with ConsultViewWikipedia
     */
    public void synToConsultViewWikipedia(){
        firstView.initialize_ConsultViewWikipedia();
    }

    /**
     * Method to synchronize the First View with ConsultSolutionView
     */
    public void synToConsultSolutionView() {
        firstView.initialize_ConsultSolutionView();
    }

    /**
     * Method to synchronize the First View with SearchPanel
     */
    public void synToSearchPanel() {
        firstView.initialize_SearchPanel();
    }

    /**
     * Method to synchronize the First View with ModifySolutionView
     * @param id Solution Id
     */
    public void synToModifySolutionView(String id) {
        firstView.initialize_ModifySolutionView(id);
    }

    /**
     * Method to synchronize the First View with FileViewWikipedia
     * @param strTitul Title of the view
     * @param strText Text of the view
     */
    public void synToFileViewWikipedia(String strTitul, String strText) {
        firstView.initialize_FileViewWikipedia(strTitul, strText);
    }

    /**
     * Method to synchronize the First View with DialogViewWikipedia1Op
     * @param strTitul Title of the view
     * @param strText Text of the view
     */
    public void synToDialogViewWikipedia1Op(String strTitul, String strText) {
        firstView.initialize_DialogViewWikipedia1Op(strTitul, strText);
    }

    /**
     * Method to synchronize the First View with DialogViewWikipedia2Op
     * @param strTitul Title of the view
     * @param strText1 First text of the view
     * @param strText2 Second text of the view
     */
    public void synToDialogViewWikipedia2Op(String strTitul, String strText1, String strText2) {
        firstView.initialize_DialogViewWikipedia2Op(strTitul, strText1, strText2);
    }

    /**
     * Method to synchronize the First View with StatisticsView
     */
    public void synToStatisticsView() {
        firstView.initialize_StatisticsView();
    }

    /**
     * Method to synchronize the First View with HelpView
     */
    public void synToHelpView() {
        if (helpView == null) helpView = new HelpView();
        helpView.makeVisible();
    }

    /** Domain Controller Calls */

    /**
     * Call the Wikipedia Domain Controller to create a new Wikipedia
     * @param id Id of the new Wikipedia
     */
    public void callWikiDomainCreate(String id){
        wikiCtrl = new WikipediaDomainController();
        wikiCtrl.loadNewWikipedia(id);
        first = true;
        firstView.changeTitle(id);
    }

    /**
     * Call the Wikipedia Domain Controller to load a Wikipedia using a Wikipedia with an Id from the disk
     * @param id Id of the Wikipedia from the disk
     * @param path The absolute path where is saved the Wikipedia
     * @throws IOException If there is not a Wikipedia with the Id in disk
     * @throws IncorrectFormatException If the Wikipedia with the Id in disk has incorrect format
     */
    public void callWikiDomainLoad(String id, String path) throws IOException, IncorrectFormatException {
        if (first) {
            WikipediaDomainController wikiCtrl2 = new WikipediaDomainController();
            wikiCtrl2.loadImportedWikipedia(id,path);
            if (wikiCtrl2.consultWikipedia() != null){
                wikiCtrl = wikiCtrl2;
                firstView.changeTitle(id);
            }
        }
        else {
            wikiCtrl = new WikipediaDomainController();
            wikiCtrl.loadImportedWikipedia(id,path);
            first = (wikiCtrl.consultWikipedia() != null);
            if (first) firstView.changeTitle(id);
        }
    }

    /**
     * Call the Wikipedia Domain Controller to modify the current Wikipedia setting modifications
     * between origin node and destiny node (if needed)
     * @param idO Id of the origin node
     * @param typeM Type of modification
     * @param idD Id of the destiny node
     * @throws WikipediaException If there is an invalid modification
     */
    public void callWikiDomainModifyManually(String idO, String typeM, String idD) throws WikipediaException {
        wikiCtrl.modifyWikipedia(idO, typeM, idD);
    }

    /**
     * Call the Wikipedia Domain Controller to modify the current Wikipedia using a Wikipedia with an Id from the disk
     * @param s Id of the Wikipedia from the disk
     * @param path The absolute path where is saved the Wikipedia
     * @throws IOException If there is not a Wikipedia with the Id in disk
     * @throws IncorrectFormatException If the Wikipedia with the Id in disk has incorrect format
     */
    public void callWikiDomainModifyWithFile(String s, String path) throws IOException, IncorrectFormatException{
        wikiCtrl.loadImportedWikipedia(s, path);
    }

    /**
     * Call the Wikipedia Domain Controller to get the Id of the current Wikipedia
     * @return returns the Id of the current Wikipedia
     */
    public String callWikiDomainGetId() {
        return wikiCtrl.getIdWikipedia();
    }

    /**
     * Call the Wikipedia Domain Controller to change the Id of the current Wikipedia
     * @param id The new Id od the current Wikipedia
     */
    public void callWikiDomainSetId(String id) {
        wikiCtrl.setIdWikipedia(id);
        firstView.changeTitle(id);
    }

    /**
     * Call the Wikipedia Domain Controller to delete a Wikipedia with an Id in disk
     * @param path The absolute path where is saved the Wikipedia
     * @throws IOException If there is not a Wikipedia with the Id in disk
     */
    public void callWikiDomainDelete(String path) throws IOException{
        if (wikiCtrl == null) wikiCtrl = new WikipediaDomainController();
        wikiCtrl.deleteWikipedia(path);
    }

    /**
     * Call the Wikipedia Domain Controller to save the current Wikipedia in disk
     * @param path The absolute path where the Wikipedia will be saved
     * @throws IOException If there is a Wikipedia saved with the same Id
     */
    public void callWikiDomainSave(String path) throws IOException{
        wikiCtrl.saveWikipedia(path);
    }

    /** Domain Controller Calls for Advanced Search */

    /**
     * @return returns a set of the categories in the Wikipedia
     */
    public Set<Category> callWikiDomainObtainCategories(){
        return wikiCtrl.obtainCategories();
    }

    /**
     * @param set A Set of categories
     * @return returns a SortedSet of these categories
     */
    public SortedSet<String> callWikiDomainObtainSortedSetCategories(Set<Category> set){
        return wikiCtrl.obtainSortedSetCategories(set);
    }

    /**
     * @param set A Set of pages
     * @return returns a SortedSet of these pages
     */
    public SortedSet<String> callWikiDomainObtainSortedSetPages(Set<Page> set){
        return wikiCtrl.obtainSortedSetPages(set);
    }

    /**
     * @param id Id of the Category to be found
     * @return returns a boolean depending on whether the Wikipedia contains the Category
     */
    public boolean callWikiDomainFindCategory(String id){
        return wikiCtrl.findCategory(id);
    }

    /**
     * @return returns a set of the pages in the Wikipedia
     */
    public Set<Page> callWikiDomainObtainPagesInWiki(){
        return wikiCtrl.obtainPagesInWiki();
    }

    /**
     * @param id Id of the Category to get the pages
     * @return returns a set of pages which are in the Category
     */
    public Set<Page> callWikiDomainObtainPagesInCategory(String id){
        return wikiCtrl.obtainPagesInCategory(id);
    }

    /**
     * @param id Id of the Page to get the categories
     * @return returns a set of categories which have the Page
     */
    public Set<Category> callWikiDomainObtainCategoriesInPage(String id){
        return wikiCtrl.obtainCategoriesInPage(id);
    }

    /**
     * @param id Id of the Page to found
     * @return returns a boolean depending on whether the Wikipedia contains the Page
     */
    public boolean callWikiDomainFindPageInWiki(String id){
        return wikiCtrl.findPageInWiki(id);
    }

    /**
     * @param idCategory Id of the Category to found the Page
     * @param idPage Id of the Page to found
     * @return returns a boolean depending on whether the Category contains the Page
     */
    public boolean callWikiDomainFindPageInCategory(String idCategory, String idPage){
        return wikiCtrl.findPageInCategory(idCategory, idPage);
    }

    /**
     * @return returns a set of the subcategories in the Wikipedia
     */
    public Set<Category> callWikiDomainObtainSubCategoriesInWiki(){
        return wikiCtrl.obtainSubCategoriesInWiki();
    }

    /**
     * @param id Id of the Category to get the subcategories
     * @return returns a set of subcategories which are in the Category
     */
    public Set<Category> callWikiDomainObtainSubCategoriesInCategory(String id){
        return wikiCtrl.obtainSubCategoriesInCategory(id);
    }

    /**
     * @param id Id of the Subcategory to get the categories
     * @return returns a set of categories which have the Subcategory
     */
    public Set<Category> callWikiDomainObtainCategoriesInSubCategory(String id){
        return wikiCtrl.obtainCategoriesInSubCategory(id);
    }

    /**
     * @param id Id of the Subcategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Subcategory
     */
    public boolean callWikiDomainFindSubCategoryInWiki(String id){
        return wikiCtrl.findSubCategoryInWiki(id);
    }

    /**
     * @param idCategory Id of the Category to found the Subcategory
     * @param idSubCategory Id of the Subcategory to found
     * @return returns a boolean depending on whether the Category contains the Subcategory
     */
    public boolean callWikiDomainFindSubCategoryInCategory(String idCategory,String idSubCategory){
        return wikiCtrl.findSubCategoryInCategory(idCategory, idSubCategory);
    }

    /**
     * @return returns a set of the supercategories in the Wikipedia
     */
    public Set<Category> callWikiDomainObtainSuperCategoriesInWiki(){
        return wikiCtrl.obtainSuperCategoriesInWiki();
    }

    /**
     * @param id Id of the Category to get the sueprcategories
     * @return returns a set of supercategories which are in the Category
     */
    public Set<Category> callWikiDomainObtainSuperCategoriesInCategory(String id){
        return wikiCtrl.obtainSuperCategoriesInCategory(id);
    }

    /**
     * @param id Id of the Supercategory to get the categories
     * @return returns a set of categories which have the Supercategory
     */
    public Set<Category> callWikiDomainObtainCategoriesInSuperCategory(String id){
        return wikiCtrl.obtainCategoriesInSuperCategory(id);
    }

    /**
     * @param id Id of the Supercategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Supercategory
     */
    public boolean callWikiDomainFindSuperCategoryInWiki(String id){
        return wikiCtrl.findSuperCategoryInWiki(id);
    }

    /**
     * @param idCategory Id of the Category to found the Supercategory
     * @param idSuperCategory Id of the Supercategory to found
     * @return returns a boolean depending on whether the Category contains the Supercategory
     */
    public boolean callWikiDomainFindSuperCategoryInCategory(String idCategory,String idSuperCategory){
        return wikiCtrl.findSuperCategoryInCategory(idCategory, idSuperCategory);
    }

    public boolean searching() {
        if (working) JOptionPane.showMessageDialog(null, "No pots fer modificacions mentre estàs executant una cerca!", "Error", JOptionPane.CANCEL_OPTION);
        return working;
    }
}
