package domini;

import java.util.*;

/**
 * Class used to represent the Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class Wikipedia {
    private String id;
    private boolean log = true;
    //Per cada Categoria, les seves pagines
    private HashMap<Category,HashSet<Page>> pages = new HashMap<>();
    //Per cada Categoria, les seves subs
    private HashMap<Category,HashSet<Category>> subs = new HashMap<>();
    //Per cada Categoria, les seves supers
    private HashMap<Category,HashSet<Category>> supers = new HashMap<>();
    //Per cada Pagina, les categories a les que esta
    private HashMap<Page,HashSet<Category>> pageCats = new HashMap<>();
    //Per cada Categoria, de qui es ella sub
    private HashMap<Category,HashSet<Category>> isSub = new HashMap<>();
    //Per cada Categoria, de qui es ella super
    private HashMap<Category,HashSet<Category>> isSuper = new HashMap<>();

    /**
     * Constructor class
     * @param id Id of the Wikipedia
     */
    public Wikipedia(String id) {
        this.id = id;
    }

    public void setLog(boolean b){
        log = b;
    }

    /**
     * @return returns de id of the Category
     */
    public String getId() {
        return id;
    }

    /**
     * @param id set an id to the Wikipedia
     */
    public void setId(String id){
        (this).id = id;
    }

    /**
     * @param set a Set of categories
     * @return returns a SortedSet of these categories
     */
    public SortedSet<String> sortSetCategories(Set<Category> set){
        SortedSet<String> sortedSet = new TreeSet<>();
        if (set != null){
            for (Category c : set){
                sortedSet.add(c.getId());
            }
        }
        else {
            sortedSet.add("");
        }
        return sortedSet;
    }

    /**
     * @param set a Set of pages
     * @return returns a SortedSet of these pages
     */
    public SortedSet<String> sortSetPages(Set<Page> set){
        SortedSet<String> sortedSet = new TreeSet<>();
        if (set != null){
            for (Page p : set){
                sortedSet.add(p.getId());
            }
        }
        else {
            sortedSet.add("");
        }
        return sortedSet;
    }

    /**
     *  Categories
     */

    /**
     * @return returns a set of the categories in the Wikipedia
     */
    public Set<Category> getCategories(){
        return pages.keySet();
    }

    /**
     * @param c Category to be added
     * @throws WikipediaException If the Category is in the Wikipedia
     */
    public void addCategory(Category c) throws WikipediaException {
        if (!wikiContainsCategory(c)) {
            pages.put(c, new HashSet<Page>());
            subs.put(c, new HashSet<Category>());
            supers.put(c, new HashSet<Category>());
        }
        else if (log) throw new WikipediaException("Ja existeix la Categoria: "+c.getId());
    }

    /**
     * @param id Id of the category to be added
     * @throws WikipediaException If the Category is in the Wikipedia
     */
    public void addCategory(String id) throws WikipediaException{
        addCategory(new Category(id));
    }

    /**
     * @param c Category to be removed
     * @throws WikipediaException If the Category is not the Wikipedia
     */
    public void removeCategory(Category c) throws WikipediaException {
        if (wikiContainsCategory(c)) {
            pages.remove(c);
            subs.remove(c);
            supers.remove(c);
            ArrayList<Page> l = new ArrayList<>();
            for (Page p : pageCats.keySet()) {
                if (pageCats.get(p).contains(c)) {
                    pageCats.get(p).remove(c);
                    if (pageCats.get(p).isEmpty()){
                        l.add(p);
                    }
                }
            }
            for (Page p : l) {
                pageCats.remove(p);
            }
            ArrayList<Category> l2 = new ArrayList<>();
            for (Category cat : isSub.keySet()) {
                if (isSub.get(cat).contains(c)) {
                    isSub.get(cat).remove(c);
                    if (isSub.get(cat).isEmpty()){
                        l2.add(cat);
                    }
                }
            }
            for (Category cat : l2) {
                isSub.remove(cat);
            }
            ArrayList<Category> l3 = new ArrayList<>();
            for (Category cat : isSuper.keySet()) {
                if (isSuper.get(cat).contains(c)) {
                    isSuper.get(cat).remove(c);
                    if (isSuper.get(cat).isEmpty()){
                        l3.add(cat);
                    }
                }
            }
            for (Category cat : l3) {
                isSuper.remove(cat);
            }
        }
        else if (log) throw new WikipediaException("No existeix la Categoria: "+c.getId());
    }

    /**
     * @param id Id of the Category to be removed
     * @throws WikipediaException If the Category is not the Wikipedia
     */
    public void removeCategory(String id) throws WikipediaException {
        removeCategory(new Category(id));
    }

    /**
     * @return returns the number of categories in the Wikipedia
     */
    public Integer getNCategories() {
        return pages.keySet().size();
    }

    /**
     * @return returns a boolean depending on whether the Wikipedia has categories
     */
    public Boolean hasCategories() { return !pages.isEmpty(); }

    /**
     * @param c Category to be found
     * @return returns a boolean depending on whether the Wikipedia contains the Category
     */
    public Boolean wikiContainsCategory(Category c) { return pages.containsKey(c); }

    /**
     * @param id Id of the Category to be found
     * @return returns a boolean depending on whether the Wikipedia contains the Category
     */
    public Boolean wikiContainsCategory(String id) {
        return pages.containsKey(new Category(id));
    }

    /**
     *  Pages
     */

    /**
     * @param c Category to get the pages
     * @return returns a set of pages which are in the Category
     */
    public Set<Page> getPages(Category c) {
        return pages.get(c);
    }

    /**
     * @param id Id of the Category to get the pages
     * @return returns a set of pages which are in the Category
     */
    public Set<Page> getPages(String id) {
        return pages.get(new Category(id));
    }

    /**
     * @param p Page to get the categories
     * @return returns a set of categories which have the Page
     */
    public Set<Category> getPageCats(Page p){
        return pageCats.get(p);
    }

    /**
     * @param id Id of the Page to get the categories
     * @return returns a set of categories which have the Page
     */
    public Set<Category> getPageCats(String id){
        return pageCats.get(new Page(id));
    }

    /**
     * @return returns a set of the pages in the Wikipedia
     */
    public Set<Page> getTotalPages() { return pageCats.keySet(); }

    /**
     * @param c Category to add the page
     * @param p Page to be added
     * @throws WikipediaException If the Category already contains the Page or if the Category is not in the Wikipedia
     */
    public void addPage(Category c,Page p) throws WikipediaException{
        if (wikiContainsCategory(c)) {
            if (!catContainsPage(c, p)) {
                pages.get(c).add(p);
                if (!wikiContainsPage(p)) {
                    HashSet<Category> hsc = new HashSet<>();
                    hsc.add(c);
                    pageCats.put(p, hsc);
                } else {
                    pageCats.get(p).add(c);
                }
            }
            else if (log) throw new WikipediaException("La categoria: "+c.getId()+" ja conte la pagina: "+p.getId());
        }
        else if (log) throw new WikipediaException("No pots afegir la pagina: "+p.getId()+" perque no existeix la categoria: " +c.getId());
    }

    /**
     * @param idCategory Id of the Category to add the page
     * @param idPage Id of the Page to be added
     * @throws WikipediaException If the Category already contains the Page or if the Category is not in the Wikipedia
     */
    public void addPage(String idCategory, String idPage) throws WikipediaException{
        addPage(new Category(idCategory), new Page(idPage));
    }

    /**
     * @param c Category to remove the page
     * @param p Page to be removed
     * @throws WikipediaException If the Category does not contains the Page, if the Category is not in the Wikipedia
     * or if the Page is not in the Wikipedia
     */
    public void removePage(Category c, Page p) throws WikipediaException{
        if (wikiContainsPage(p)) {
            if (wikiContainsCategory(c)) {
                if (catContainsPage(c, p)) {
                    pages.get(c).remove(p);
                    pageCats.get(p).remove(c);
                    //Si una pagina deixa de estar a categories, desapareix
                    if (pageCats.get(p).isEmpty()) {
                        pageCats.remove(p);
                    }
                }
                else if (log) throw new WikipediaException("La categoria: "+c.getId()+" no conte la pagina: "+p.getId());
            }
            else if (log) throw new WikipediaException("No pots traure la pagina "+p.getId()+" perque no exiteix la categoria: "+c.getId());
        }
        else if (log) throw new WikipediaException("No pots traure la pagina "+p.getId()+" perque no hi es en la Wikipedia");
    }

    /**
     * @param idCategory Id of the Category to remove the page
     * @param idPage Id of the Page to be removed
     * @throws WikipediaException If the Category does not contains the Page, if the Category is not in the Wikipedia
     * or if the Page is not in the Wikipedia
     */
    public void removePage(String idCategory, String idPage) throws WikipediaException{
        removePage(new Category(idCategory), new Page(idPage));
    }


    /**
     * @return returns the number of pages in the Wikipedia
     */
    public Integer getNPages() {
        return pageCats.keySet().size();
    }

    /**
     * @return returns a boolean depending on whether the Wikipedia has pages
     */
    public Boolean hasPages() { return !pageCats.isEmpty(); }

    /**
     * @param p Page to found
     * @return returns a boolean depending on whether the Wikipedia contains the Page
     */
    public Boolean wikiContainsPage(Page p) {
        return pageCats.containsKey(p);
    }

    /**
     * @param id Id of the Page to found
     * @return returns a boolean depending on whether the Wikipedia contains the Page
     */
    public Boolean wikiContainsPage(String id) {
        return wikiContainsPage(new Page(id));
    }

    /**
     * @param c Category to found the Page
     * @param p Page to found
     * @return returns a boolean depending on whether the Category contains the Page
     */
    public Boolean catContainsPage(Category c, Page p){
        String id = c.getId();
        if (wikiContainsPage(p)) {
            for (Category cat : pageCats.get(p)) {
                if (cat.getId().equals(id)) return true;
            }
        }
        return false;
    }

    /**
     * @param idCategory Id of the Category to found the Page
     * @param idPage Id of the Page to found
     * @return returns a boolean depending on whether the Category contains the Page
     */
    public Boolean catContainsPage(String idCategory, String idPage){
        Page p = null;
        Boolean b = false;
        Set<Page> s = pageCats.keySet();
        Iterator<Page> it = s.iterator();
        while (it.hasNext() && !b){
            p = it.next();
            if (p.getId().equals(idPage)) b = true;
        }
        if (b) {
            for (Category cat : pageCats.get(p)) {
                if (cat.getId().equals(idCategory)) return true;
            }
        }
        return false;
    }


    /**
     *  Subs
     */

    /**
     * @param c Category to get the subcategories
     * @return returns a set of subcategories which are in the Category
     */
    public Set<Category> getSubs(Category c) {
        return subs.get(c);
    }

    /**
     * @param id Id of the Category to get the subcategories
     * @return returns a set of subcategories which are in the Category
     */
    public Set<Category> getSubs(String id) {
        return subs.get(new Category(id));
    }

    /**
     * @param c Subcategory to get the categories
     * @return returns a set of categories which have the Subcategory
     */
    public Set<Category> getIsSub(Category c){
        return isSub.get(c);
    }

    /**
     * @param id Id of the Subcategory to get the categories
     * @return returns a set of categories which have the Subcategory
     */
    public Set<Category> getIsSub(String id){
        return isSub.get(new Category(id));
    }

    /**
     * @return returns a set of the subcategories in the Wikipedia
     */
    public Set<Category> getTotalSubs() { return isSub.keySet(); }

    /**
     * @param c Category to add the Subcategory
     * @param cs Subcategory to be added
     * @throws WikipediaException If the Category already contains the Subcategory, if the Subcategory already is the
     * Supercategory of the Category, if the Subcategory is not in the Wikipedia, if the Category is not in the Wikipedia
     * or if the Category and the Subcategory are the same
     */
    public void addSub(Category c,Category cs) throws WikipediaException{
        if (!c.getId().equals(cs.getId())) {
            if (wikiContainsCategory(c)) {
                if (wikiContainsCategory(cs)) {
                    if (!catContainsSuper(c, cs)) {
                        if (!catContainsSub(c, cs)) {
                            subs.get(c).add(cs);
                            if (!wikiContainsSub(cs)) {
                                HashSet<Category> hsc = new HashSet<>();
                                hsc.add(c);
                                isSub.put(cs, hsc);
                            } else {
                                isSub.get(cs).add(c);
                            }
                        }
                        else if (log) throw new WikipediaException("La categoria: " +c.getId()+ " ja conte la subcategoria: " + cs.getId());
                    }
                    else if (log) throw new WikipediaException("La categoria: " + cs.getId() + " no pot ser subcategoria de la categoria: " + c.getId() + " perque ja es la supercategoria d'aquesta");
                }
                else if (log) throw new WikipediaException("No pots afegir la subcategoria: " +cs.getId()+ " perque no existeix a la Wikipedia");
            }
            else if (log) throw new WikipediaException("No pots afegir la subcategoria: "+cs.getId()+" perque no existeix la categoria: "+c.getId());
        }
        else if (log) throw new WikipediaException("La categoria: "+c.getId()+ " no pot ser sub d'ella mateixa");
    }

    /**
     * @param idCategory Id of the Category to add the Subcategory
     * @param idSub Id of the Subcategory to be added
     * @throws WikipediaException If the Category already contains the Subcategory, if the Subcategory already is the
     * Supercategory of the Category, if the Subcategory is not in the Wikipedia, if the Category is not in the Wikipedia
     * or if the Category and the Subcategory are the same
     */
    public void addSub(String idCategory, String idSub) throws WikipediaException{
        addSub(new Category(idCategory),new Category(idSub));
    }

    /**
     * @param c Category to remove the Subcategory
     * @param cs Subcategory to be removed
     * @throws WikipediaException If the Category does not contains the Subcategory, if the Category is not in the Wikipedia
     * or if the Subcategory is not in the Wikipedia
     */
    public void removeSub(Category c, Category cs) throws WikipediaException{
        if (wikiContainsCategory(cs)) {
            if (wikiContainsCategory(c)) {
                if (catContainsSub(c, cs)) {
                    subs.get(c).remove(cs);
                    isSub.get(cs).remove(c);
                    //Si una categoria deixa de estar a subcategories, desapareix de la llista de subcategories
                    if (isSub.get(cs).isEmpty()) {
                        isSub.remove(cs);
                    }
                }
                else if (log) throw new WikipediaException("La categoria: "+c.getId()+" no conte la subcategoria: "+cs.getId());
            }
            else if (log) throw new WikipediaException("No pots traure la subcategoria "+cs.getId()+" perque no exiteix la categoria: "+c.getId());
        }
        else if (log) throw new WikipediaException("No pots traure la subcategoria "+cs.getId()+" perque no hi es en la Wikipedia");
    }

    /**
     * @param idCategory Id of the Category to remove the Subcategory
     * @param idSub Id of the Subcategory to be removed
     * @throws WikipediaException If the Category does not contains the Subcategory, if the Category is not in the Wikipedia
     * or if the Subcategory is not in the Wikipedia
     */
    public void removeSub(String idCategory, String idSub) throws WikipediaException{
        removeSub(new Category(idCategory),new Category(idSub));
    }

    /**
     * @return returns the number of subcategories in the Wikipedia
     */
    public Integer getNSubs() {
        return isSub.keySet().size();
    }

    /**
     * @return returns a boolean depending on whether the Wikipedia has subcategories
     */
    public Boolean hasSubs() { return !isSub.isEmpty(); }

    /**
     * @param cs Subcategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Subcategory
     */
    public Boolean wikiContainsSub(Category cs) {
        return isSub.containsKey(cs);
    }

    /**
     * @param id Id of the Subcategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Subcategory
     */
    public Boolean wikiContainsSub(String id) {
        return wikiContainsSub(new Category(id));
    }

    /**
     * @param c Category to found the Subcategory
     * @param cs Subcategory to found
     * @return returns a boolean depending on whether the Category contains the Subcategory
     */
    public Boolean catContainsSub(Category c, Category cs){
        String id = c.getId();
        if (isSub.containsKey(cs)) {
            for (Category cat : isSub.get(cs)) {
                if (cat.getId().equals(id)) return true;
            }
        }
        return false;
    }

    /**
     * @param idCategory Id of the Category to found the Subcategory
     * @param idSub Id of the Subcategory to found
     * @return returns a boolean depending on whether the Category contains the Subcategory
     */
    public Boolean catContainsSub(String idCategory, String idSub){
        Category cs = null;
        Boolean b = false;
        Set<Category> s = isSub.keySet();
        Iterator<Category> it = s.iterator();
        while (it.hasNext() && !b){
            cs = it.next();
            if (cs.getId().equals(idSub)) b = true;
        }
        if (b) {
            for (Category cat : isSub.get(cs)) {
                if (cat.getId().equals(idCategory)) return true;
            }
        }
        return false;
    }

    /**
     * Supers
     */

    /**
     * @param c Category to get the supercategories
     * @return returns a set of supercategories which are in the Category
     */
    public Set<Category> getSupers(Category c) {
        return supers.get(c);
    }

    /**
     * @param id Id of the Category to get the sueprcategories
     * @return returns a set of supercategories which are in the Category
     */
    public Set<Category> getSupers(String id) {
        return supers.get(new Category(id));
    }

    /**
     * @param c Supercategory to get the categories
     * @return returns a set of categories which have the Supercategory
     */
    public Set<Category> getIsSuper(Category c){
        return isSuper.get(c);
    }

    /**
     * @param id Id of the Supercategory to get the categories
     * @return returns a set of categories which have the Supercategory
     */
    public Set<Category> getIsSuper(String id){
        return isSuper.get(new Category(id));
    }

    /**
     * @return returns a set of the supercategories in the Wikipedia
     */
    public Set<Category> getTotalSupers() { return isSuper.keySet(); }

    /**
     * @param c Category to add the Supercategory
     * @param cs Supercategory to be added
     * @throws WikipediaException If the Category already contains the Supercategory, if the Supercategory already is the
     * Subcategory of the Category, if the Supercategory is not in the Wikipedia, if the Category is not in the Wikipedia
     * or if the Category and the Supercategory are the same
     */
    public void addSuper(Category c,Category cs) throws WikipediaException{
        if (!c.getId().equals(cs.getId())) {
            if (wikiContainsCategory(c)) {
                if (wikiContainsCategory(cs)) {
                    if (!catContainsSub(c, cs)) {
                        if (!catContainsSuper(c, cs)) {
                            supers.get(c).add(cs);
                            if (!wikiContainsSuper(cs)) {
                                HashSet<Category> hsc = new HashSet<>();
                                hsc.add(c);
                                isSuper.put(cs, hsc);
                            } else {
                                isSuper.get(cs).add(c);
                            }
                        }
                        else if (log) throw new WikipediaException("La categoria: " +c.getId()+ " ja conte la supercategoria: " + cs.getId());
                    }
                    else if (log) throw new WikipediaException("La categoria: " + cs.getId() + " no pot ser supercategoria de la categoria: " + c.getId() + " perque ja es la subcategoria d'aquesta");
                }
                else if (log) throw new WikipediaException("No pots afegir la supercategoria: " +cs.getId()+ " perque no existeix a la Wikipedia");
            }
            else if (log) throw new WikipediaException("No pots afegir la supercategoria: "+cs.getId()+" perque no existeix la categoria: "+c.getId());
        }
        else if (log) throw new WikipediaException("La categoria: "+c.getId()+ " no pot ser super d'ella mateixa");
    }

    /**
     * @param idCategory Id of the Category to add the Supercategory
     * @param idSuper Id of the Supercategory to be added
     * @throws WikipediaException If the Category already contains the Supercategory, if the Supercategory already is the
     * Subcategory of the Category, if the Supercategory is not in the Wikipedia, if the Category is not in the Wikipedia
     * or if the Category and the Supercategory are the same
     */
    public void addSuper(String idCategory, String idSuper) throws WikipediaException{
        addSuper(new Category(idCategory),new Category(idSuper));
    }

    /**
     * @param c Category to remove the Supercategory
     * @param cs Supercategory to be removed
     * @throws WikipediaException If the Category does not contains the Supercategory, if the Category is not in the Wikipedia
     * or if the Supercategory is not in the Wikipedia
     */
    public void removeSuper(Category c, Category cs) throws WikipediaException {
        if (wikiContainsCategory(cs)) {
            if (wikiContainsCategory(c)) {
                if (catContainsSuper(c, cs)) {
                    supers.get(c).remove(cs);
                    isSuper.get(cs).remove(c);
                    //Si una categoria deixa de estar a supercategories, desapareix de la llista de supercategories
                    if (isSuper.get(cs).isEmpty()) {
                        isSuper.remove(cs);
                    }
                }
                else if (log) throw new WikipediaException("La categoria: "+c.getId()+" no conte la supercategoria: "+cs.getId());
            }
            else if (log) throw new WikipediaException("No pots traure la supercategoria "+cs.getId()+" perque no exiteix la categoria: "+c.getId());
        }
        else if (log) throw new WikipediaException("No pots traure la supercategoria "+cs.getId()+" perque no hi es en la Wikipedia");
    }

    /**
     * @param idCategory Id of the Category to remove the Supercategory
     * @param idSuper Id of the Supercategory to be removed
     * @throws WikipediaException If the Category does not contains the Supercategory, if the Category is not in the Wikipedia
     * or if the Supercategory is not in the Wikipedia
     */
    public void removeSuper(String idCategory, String idSuper) throws WikipediaException{
        removeSuper(new Category(idCategory), new Category(idSuper));
    }

    /**
     * @return returns the number of supercategories in the Wikipedia
     */
    public Integer getNSupers() {
        return isSuper.keySet().size();
    }

    /**
     * @return returns a boolean depending on whether the Wikipedia has supercategories
     */
    public Boolean hasSupers() { return !isSuper.isEmpty(); }

    /**
     * @param cs Supercategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Supercategory
     */
    public Boolean wikiContainsSuper(Category cs) {
        return isSuper.containsKey(cs);
    }

    /**
     * @param id Id of the Supercategory to found
     * @return returns a boolean depending on whether the Wikipedia contains the Supercategory
     */
    public Boolean wikiContainsSuper(String id) {
        return wikiContainsSuper(new Category(id));
    }

    /**
     * @param c Category to found the Supercategory
     * @param cs Supercategory to found
     * @return returns a boolean depending on whether the Category contains the Supercategory
     */
    public Boolean catContainsSuper(Category c, Category cs){
        String id = c.getId();
        if (isSuper.containsKey(cs)) {
            for (Category cat : isSuper.get(cs)) {
                if (cat.getId().equals(id)) return true;
            }
        }
        return false;
    }

    /**
     * @param idCategory Id of the Category to found the Supercategory
     * @param idSuper Id of the Supercategory to found
     * @return returns a boolean depending on whether the Category contains the Supercategory
     */
    public Boolean catContainsSuper(String idCategory, String idSuper){
        Category cs = null;
        Boolean b = false;
        Set<Category> s = isSuper.keySet();
        Iterator<Category> it = s.iterator();
        while (it.hasNext() && !b){
            cs = it.next();
            if (cs.getId().equals(idSuper)) b = true;
        }
        if (b) {
            for (Category cat : isSuper.get(cs)) {
                if (cat.getId().equals(idCategory)) return true;
            }
        }
        return false;
    }
}
