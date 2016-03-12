package tests;

import domini.*;
import junit.framework.TestCase;

import java.util.Set;

/**
 * @author Aleix Pellisa Cortiella
 */

public class WikipediaTest extends TestCase {

    public WikipediaTest(String name) {
        super(name);
    }

    public static void main (String[] args) {
        junit.textui.TestRunner.run(WikipediaTest.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
    }

    public void testGetters() {
        Wikipedia w = new Wikipedia("1");
        assertNotNull(w);
        //Creem correctament la Wikipedia
        assertEquals("1", w.getId());
        Wikipedia w2 = new Wikipedia("9");
        assertNotNull(w2);
        //Podem comparar dues Wikipedies
        assertTrue(!w.getId().equals(w2.getId()));
        assertNotSame(w, w2);
    }

    public void testAddCategories() throws WikipediaException{
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        //La Wikipedia inicialment esta buida
        assertFalse(w.hasCategories());
        w.addCategory(a1);
        //La Wikipedia conte el nom i l'objecte Category
        assertTrue(w.wikiContainsCategory("a1"));
        assertTrue(w.wikiContainsCategory(a1));
        // Saltara una exception -> w.addCategory("a1");
        Integer n = 1;
        //No pots tornar a afegir categoria amb un nom ja usat
        assertEquals(n, w.getNCategories());
        Category a2 = new Category("a1");
        // Saltara una exception -> w.addCategory(a2);
        //No hi han categories repetides a la Wikipedia amb el mateix nom...
        assertEquals(n,w.getNCategories());
        //...pero si que conte l'objecte categoria repetit
        //perque el metode mira si la wikipedia conte l'id (**despres no suposa un problema)
        assertTrue(w.wikiContainsCategory(a2));
        Category a3 = new Category("a3");
        w.addCategory(a3);
        n = 2;
        //Augmenta el nombre de categories si li afegim categories
        assertEquals(n, w.getNCategories());
        //Comprovem que les categories que conte la Wikipedia son correctes
        assertTrue(w.wikiContainsCategory(a1));
        assertTrue(w.wikiContainsCategory("a1"));
        assertTrue(w.wikiContainsCategory(a2));
        assertFalse(w.wikiContainsCategory("a2"));
        assertTrue(w.wikiContainsCategory(a3));
        assertTrue(w.wikiContainsCategory("a3"));
    }

    public void testRemoveCategories() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        w.addCategory(a1);
        w.removeCategory(a1);
        //Treiem correctament la categoria de la Wikipedia
        assertFalse(w.hasCategories());
        assertFalse(w.wikiContainsCategory("a1"));
        assertFalse(w.wikiContainsCategory(a1));
        Category a2 = new Category("a2");
        // Saltara una exception -> w.removeCategory(a2);
        w.addCategory(a2);
        //L'ordre de remove i add es correspont al resultat esperat
        assertTrue(w.wikiContainsCategory(a2));
        // Saltara una exception -> w.removeCategory("a1");
        // Saltara una exception -> w.removeCategory(a1);
        //Les operacions que no l'afecten mantenen el contingut
        assertTrue(w.wikiContainsCategory(a2));
        w.removeCategory(a2);
        w.addCategory(a2);
        //Pots afegir i tornar a posar
        assertTrue(w.wikiContainsCategory(a2));
        assertTrue(w.wikiContainsCategory("a2"));
        Category a3 = new Category("a2");
        //Ara tenim dos categories objecte que estan contingudes dintre de Wikipedia
        assertTrue(w.wikiContainsCategory(a3));
        w.removeCategory(a3);
        //Si treiem un objecte en realitat expulsem el seu id de la Wikipedia
        //S'arregla el problema (**) del test addCategories
        assertFalse(w.wikiContainsCategory(a3));
        assertFalse(w.wikiContainsCategory(a2));
    }

    public void testAddPages1() throws WikipediaException { //Considerarem 1 Categoria
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Page p1 = new Page ("p1");
        w.addCategory(a1);
        //Inicialment no hi han pagines
        assertFalse(w.hasPages());
        w.addPage(a1, p1);
        //S'ha afegit correctament la pagina a la categoria
        assertTrue(w.wikiContainsPage(p1));
        assertTrue(w.wikiContainsPage("p1"));
        // Saltara una exception -> w.addPage(a1, p1);
        Integer n = 1;
        //No pots tornar a afegir la mateixa pagina per una mateixa categoria
        assertEquals(n, w.getNPages());
        Page p2 = new Page("p1");
        // Saltara una exception -> w.addPage(a1, p2);
        //No hi han pagines repetides a la Wikipedia amb el mateix nom...
        assertEquals(n, w.getNPages());
        //...pero si que conte l'objecte pagina repetit (mateix motiu que abans)
        assertTrue(w.wikiContainsPage(p2));
        Page p3 = new Page("p3");
        w.addPage(a1, p3);
        n = 2;
        //Augmenta el nombre de pagines si li afegim pagines
        assertEquals(n, w.getNPages());
        //Comprovem que les pagines que conte la Wikipedia son correctes
        assertTrue(w.wikiContainsPage(p1));
        assertTrue(w.wikiContainsPage("p1"));
        assertTrue(w.wikiContainsPage(p2));
        assertFalse(w.wikiContainsPage("p2"));
        assertTrue(w.wikiContainsPage(p3));
        assertTrue(w.wikiContainsPage("p3"));
        //Comprovem que les pagines que conte la Categoria son correctes
        assertTrue(w.catContainsPage(a1, p1));
        assertTrue(w.catContainsPage("a1", "p1"));
        assertTrue(w.catContainsPage(a1, p2));
        assertFalse(w.catContainsPage("a1", "p2"));
        assertTrue(w.catContainsPage(a1, p3));
        assertTrue(w.catContainsPage("a1", "p3"));
    }

    public void testAddPages2() throws WikipediaException { //Considerarem 2 Categories
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Page p1 = new Page ("p1");
        Page p2 = new Page ("p2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addPage(a1, p1);
        w.addPage(a2, p2);
        //S'ha afegit correctament la pagina a la categoria
        assertTrue(w.wikiContainsPage(p1));
        assertTrue(w.wikiContainsPage("p1"));
        assertTrue(w.wikiContainsPage(p2));
        assertTrue(w.wikiContainsPage("p2"));
        w.addPage(a1, p2);
        Integer n = 2;
        //Afegir pagina a una categoria que ja hi era en la Wiki no augmenta el nombre de pagines
        assertEquals(n, w.getNPages());
        //Comprovem que les pagines que conte la Categoria son correctes
        assertTrue(w.catContainsPage(a1, p1));
        assertTrue(w.catContainsPage("a1", "p1"));
        assertTrue(w.catContainsPage(a1,p2));
        assertTrue(w.catContainsPage("a1", "p2"));
        assertFalse(w.catContainsPage(a2, p1));
        assertFalse(w.catContainsPage("a2","p1"));
        assertTrue(w.catContainsPage(a2, p2));
        assertTrue(w.catContainsPage("a2", "p2"));
    }

    public void testRemovePages1() throws WikipediaException { //Considerem 1 Categoria
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Page p1 = new Page ("p1");
        Page p2 = new Page ("p2");
        w.addCategory(a1);
        w.addPage(a1, p1);
        w.removeCategory(a1);
        //Treiem correctament la categoria i la pagina perque es queda sense categories
        assertFalse(w.hasPages());
        assertFalse(w.wikiContainsPage("p1"));
        assertFalse(w.wikiContainsPage(p1));
        assertFalse(w.catContainsPage("a1","p1"));
        assertFalse(w.catContainsPage(a1, p1));
        // Saltara una exception -> w.removePage(a1, p2);
        // Saltara una exception -> w.addPage(a1,p2);
        //L'ordre de remove i add es correspont al resultat esperat
        assertFalse(w.wikiContainsCategory(a1));
        assertFalse(w.wikiContainsPage(p2));
        assertFalse(w.catContainsPage(a1, p2));
        //No pots afegir una pagina a la wikipedia si la categoria a la que esta
        // relacionada no esta a la wikipedia
        w.addCategory(a1);
        w.addPage(a1, p1);
        // Saltara una exception -> w.removePage(a1, p2);
        //Les operacions que no l'afecten mantenen el contingut
        assertTrue(w.wikiContainsPage(p1));
        assertTrue(w.wikiContainsPage("p1"));
        assertTrue(w.catContainsPage(a1, p1));
        assertTrue(w.catContainsPage("a1", "p1"));
        w.removePage(a1, p1);
        w.addPage(a1, p1);
        //Pots afegir i tornar a posar
        assertTrue(w.wikiContainsPage(p1));
        assertTrue(w.wikiContainsPage("p1"));
        Page p3 = new Page ("p1");
        //Ara tenim dos pages objecte que estan contingudes dintre de Wikipedia
        //i dintre de la categoria
        assertTrue(w.wikiContainsPage(p3));
        assertTrue(w.catContainsPage(a1, p3));
        w.removePage(a1, p3);
        //Si treiem un objecte en realitat expulsem el seu id de la Wikipedia
        //S'arregla el problema del test addPages
        assertFalse(w.wikiContainsPage(p3));
        assertFalse(w.wikiContainsPage(p1));
        assertFalse(w.catContainsPage(a1, p3));
        assertFalse(w.catContainsPage("a1", "p1"));
    }

    public void testRemovePages2() throws WikipediaException { //Considerem 2 Categories
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Page p1 = new Page ("p1");
        Page p2 = new Page ("p2");
        Page p3 = new Page ("p3");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addPage(a1, p1);
        w.addPage(a1, p2);
        w.addPage(a1, p3);
        w.addPage(a2,p1);
        w.addPage(a2, p3);
        w.removeCategory(a1);
        //Treiem correctament la categoria, la pagina es queda a l'altra categoria
        assertTrue(w.hasPages());
        assertTrue(w.wikiContainsPage("p1"));
        assertTrue(w.wikiContainsPage(p1));
        assertFalse(w.catContainsPage("a1", "p1"));
        assertFalse(w.catContainsPage(a1, p1));
        assertFalse(w.catContainsPage("a1", "p2"));
        assertFalse(w.catContainsPage(a1, p2));
        assertFalse(w.catContainsPage("a1", "p3"));
        assertFalse(w.catContainsPage(a1, p3));
        assertTrue(w.catContainsPage("a2", "p1"));
        assertTrue(w.catContainsPage(a2, p1));
        assertTrue(w.catContainsPage("a2", "p3"));
        assertTrue(w.catContainsPage(a2, p3));
    }

    public void testAddSubs1() throws WikipediaException { //Considerarem 1 Categoria
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category s1 = new Category ("s1");
        w.addCategory(a1);
        w.addCategory(s1);
        //Inicialment no hi han subs
        assertFalse(w.hasSubs());
        w.addSub(a1, s1);
        //S'ha afegit correctament la sub a la categoria
        assertTrue(w.wikiContainsSub(s1));
        assertTrue(w.wikiContainsSub("s1"));
        // Saltara una exception -> w.addSub(a1, s1);
        Integer n = 1;
        //No pots tornar a afegir la mateixa sub per una mateixa categoria
        assertEquals(n, w.getNSubs());
        Category s2 = new Category("s1");
        // Saltara una exception -> w.addCategory(s2);
        // Saltara una exception -> w.addSub(a1, s2);
        //No hi han subs repetides a la Wikipedia amb el mateix nom...
        assertEquals(n, w.getNSubs());
        //...pero si que conte l'objecte sub repetit (mateix motiu que abans)
        assertTrue(w.wikiContainsSub(s2));
        Category s3 = new Category("s3");
        // Saltara una exception -> w.addSub(a1,s3);
        //No s'haura afegit la relacio perque s3 no esta en la wikipedia
        assertEquals(n, w.getNSubs());
        w.addCategory(s3);
        w.addSub(a1, s3);
        n = 2;
        //Augmenta el nombre de subs si li afegim subs
        assertEquals(n, w.getNSubs());
        //Comprovem que els subs que conte la Wikipedia son correctes
        assertTrue(w.wikiContainsSub(s1));
        assertTrue(w.wikiContainsSub("s1"));
        assertTrue(w.wikiContainsSub(s2));
        assertFalse(w.wikiContainsSub("s2"));
        assertTrue(w.wikiContainsSub(s3));
        assertTrue(w.wikiContainsSub("s3"));
        //Comprovem que els subs que conte la Categoria son correctes
        assertTrue(w.catContainsSub(a1,s1));
        assertTrue(w.catContainsSub("a1", "s1"));
        assertTrue(w.catContainsSub(a1, s2));
        assertFalse(w.catContainsSub("a1", "s2"));
        assertTrue(w.catContainsSub(a1, s3));
        assertTrue(w.catContainsSub("a1", "s3"));
    }

    public void testAddSubs2() throws WikipediaException { //Considerarem 2 Categories
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category s1 = new Category("s1");
        Category s2 = new Category("s2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addSub(a1, s1);
        w.addSub(a2, s2);
        //S'ha afegit correctament el sub a la categoria
        assertTrue(w.wikiContainsSub(s1));
        assertTrue(w.wikiContainsSub("s1"));
        assertTrue(w.wikiContainsSub(s2));
        assertTrue(w.wikiContainsSub("s2"));
        w.addSub(a1, s2);
        Integer n = 2;
        //Afegir sub a una categoria que ja hi era en la Wiki no augmenta el nombre de subs
        assertEquals(n, w.getNSubs());
        //Comprovem que els subs que conte la Categoria son correctes
        assertTrue(w.catContainsSub(a1, s1));
        assertTrue(w.catContainsSub("a1", "s1"));
        assertTrue(w.catContainsSub(a1, s2));
        assertTrue(w.catContainsSub("a1", "s2"));
        assertFalse(w.catContainsSub(a2, s1));
        assertFalse(w.catContainsSub("a2", "s1"));
        assertTrue(w.catContainsSub(a2, s2));
        assertTrue(w.catContainsSub("a2", "s2"));
    }

    public void testRemoveSubs1() throws WikipediaException{ //Considerem 1 Categoria
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category s1 = new Category("s1");
        Category s2 = new Category("s2");
        w.addCategory(a1);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addSub(a1, s1);
        w.removeCategory(a1);
        //Treiem correctament la categoria i el sub perque es queda sense categories
        assertFalse(w.hasSubs());
        assertFalse(w.wikiContainsSub("s1"));
        assertFalse(w.wikiContainsSub(s1));
        assertFalse(w.catContainsSub("a1","p1"));
        assertFalse(w.catContainsSub(a1, s1));
        // Saltara una exception -> w.removeSub(a1, s2);
        // Saltara una exception -> w.addSub(a1, s2);
        //L'ordre de remove i add es correspont al resultat esperat
        assertFalse(w.wikiContainsCategory(a1));
        assertFalse(w.wikiContainsSub(s2));
        assertFalse(w.catContainsSub(a1, s2));
        //No pots afegir un sub a la wikipedia si la categoria a la que esta
        // relacionada no esta a la wikipedia
        w.addCategory(a1);
        w.addSub(a1, s1);
        // Saltara una exception -> w.removeSub(a1, s2);
        //Les operacions que no l'afecten mantenen el contingut
        assertTrue(w.wikiContainsSub(s1));
        assertTrue(w.wikiContainsSub("s1"));
        assertTrue(w.catContainsSub(a1, s1));
        assertTrue(w.catContainsSub("a1", "s1"));
        w.removeSub(a1, s1);
        w.addSub(a1, s1);
        //Pots afegir i tornar a posar
        assertTrue(w.wikiContainsSub(s1));
        assertTrue(w.wikiContainsSub("s1"));
        Category s3 = new Category("s1");
        // Saltara una exception -> w.addCategory(s3);
        //Ara tenim dos categories objecte que estan contingudes dintre de Wikipedia
        //i dintre de la categoria
        assertTrue(w.wikiContainsSub(s3));
        assertTrue(w.catContainsSub(a1, s3));
        w.removeSub(a1, s3);
        //Si treiem un objecte en realitat expulsem el seu id de la Wikipedia
        //S'arregla el problema del test addSubs
        assertFalse(w.wikiContainsSub(s3));
        assertFalse(w.wikiContainsSub(s1));
        assertFalse(w.catContainsSub(a1, s3));
        assertFalse(w.catContainsSub("a1", "s1"));
    }

    public void testRemoveSubs2() throws WikipediaException { //Considerem 2 Categories
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category s1 = new Category ("s1");
        Category s2 = new Category ("s2");
        Category s3 = new Category ("s3");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addCategory(s3);
        w.addSub(a1, s1);
        w.addSub(a1, s2);
        w.addSub(a1, s3);
        w.addSub(a2, s1);
        w.addSub(a2, s3);
        w.removeCategory(a1);
        //Treiem correctament la categoria, el sub es queda a l'altra categoria
        assertTrue(w.hasSubs());
        assertTrue(w.wikiContainsSub("s1"));
        assertTrue(w.wikiContainsSub(s1));
        assertFalse(w.catContainsSub("a1", "s1"));
        assertFalse(w.catContainsSub(a1, s1));
        assertFalse(w.catContainsSub("a1", "s2"));
        assertFalse(w.catContainsSub(a1, s2));
        assertFalse(w.catContainsSub("a1", "s3"));
        assertFalse(w.catContainsSub(a1, s3));
        assertTrue(w.catContainsSub("a2", "s1"));
        assertTrue(w.catContainsSub(a2, s1));
        assertTrue(w.catContainsSub("a2", "s3"));
        assertTrue(w.catContainsSub(a2, s3));
    }

    public void testRestrictionsSub() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        w.addCategory(a1);
        // Saltara una exception -> w.addSub(a1,a1);
        //Una categoria no pot ser sub d'ella mateixa
        assertFalse(w.hasSubs());
    }

    public void testAddSupers1() throws WikipediaException { //Considerarem 1 Categoria
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category s1 = new Category ("s1");
        w.addCategory(a1);
        w.addCategory(s1);
        //Inicialment no hi han supers
        assertFalse(w.hasSupers());
        w.addSuper(a1, s1);
        //S'ha afegit correctament la super a la categoria
        assertTrue(w.wikiContainsSuper(s1));
        assertTrue(w.wikiContainsSuper("s1"));
        // Saltara una exception -> w.addSuper(a1, s1);
        Integer n = 1;
        //No pots tornar a afegir la mateixa super per una mateixa categoria
        assertEquals(n, w.getNSupers());
        Category s2 = new Category("s1");
        // Saltara una exception -> w.addCategory(s1);
        // Saltara una exception -> w.addSuper(a1, s2);
        //No hi han supers repetides a la Wikipedia amb el mateix nom...
        assertEquals(n, w.getNSupers());
        //...pero si que conte l'objecte super repetit (mateix motiu que abans)
        assertTrue(w.wikiContainsSuper(s2));
        Category s3 = new Category("s3");
        // Saltara una exception -> w.addSuper(a1,s3);
        //No s'haura afegit la relacio perque s3 no esta en la wikipedia
        assertEquals(n, w.getNSupers());
        w.addCategory(s3);
        w.addSuper(a1, s3);
        n = 2;
        //Augmenta el nombre de supers si li afegim subs
        assertEquals(n, w.getNSupers());
        //Comprovem que els supers que conte la Wikipedia son correctes
        assertTrue(w.wikiContainsSuper(s1));
        assertTrue(w.wikiContainsSuper("s1"));
        assertTrue(w.wikiContainsSuper(s2));
        assertFalse(w.wikiContainsSuper("s2"));
        assertTrue(w.wikiContainsSuper(s3));
        assertTrue(w.wikiContainsSuper("s3"));
        //Comprovem que els supers que conte la Categoria son correctes
        assertTrue(w.catContainsSuper(a1,s1));
        assertTrue(w.catContainsSuper("a1","s1"));
        assertTrue(w.catContainsSuper(a1, s2));
        assertFalse(w.catContainsSuper("a1", "s2"));
        assertTrue(w.catContainsSuper(a1, s3));
        assertTrue(w.catContainsSuper("a1", "s3"));
    }

    public void testAddSupers2() throws WikipediaException { //Considerarem 2 Categories
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category s1 = new Category("s1");
        Category s2 = new Category("s2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addSuper(a1, s1);
        w.addSuper(a2, s2);
        //S'ha afegit correctament el super a la categoria
        assertTrue(w.wikiContainsSuper(s1));
        assertTrue(w.wikiContainsSuper("s1"));
        assertTrue(w.wikiContainsSuper(s2));
        assertTrue(w.wikiContainsSuper("s2"));
        w.addSuper(a1, s2);
        Integer n = 2;
        //Afegir super a una categoria que ja hi era en la Wiki no augmenta el nombre de super
        assertEquals(n, w.getNSupers());
        //Comprovem que els supers que conte la Categoria son correctes
        assertTrue(w.catContainsSuper(a1, s1));
        assertTrue(w.catContainsSuper("a1", "s1"));
        assertTrue(w.catContainsSuper(a1, s2));
        assertTrue(w.catContainsSuper("a1", "s2"));
        assertFalse(w.catContainsSuper(a2, s1));
        assertFalse(w.catContainsSuper("a2", "s1"));
        assertTrue(w.catContainsSuper(a2, s2));
        assertTrue(w.catContainsSuper("a2", "s2"));
    }

    public void testRemoveSupers1() throws WikipediaException { //Considerem 1 Categoria
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category s1 = new Category("s1");
        Category s2 = new Category("s2");
        w.addCategory(a1);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addSuper(a1,s1);
        w.removeCategory(a1);
        //Treiem correctament la categoria i el super perque es queda sense categories
        assertFalse(w.hasSupers());
        assertFalse(w.wikiContainsSuper("s1"));
        assertFalse(w.wikiContainsSuper(s1));
        assertFalse(w.catContainsSuper("a1", "p1"));
        assertFalse(w.catContainsSuper(a1,s1));
        // Saltara una exception -> w.removeSuper(a1, s2);
        // Saltara una exception -> w.addSuper(a1, s2);
        //L'ordre de remove i add es correspont al resultat esperat
        assertFalse(w.wikiContainsCategory(a1));
        assertFalse(w.wikiContainsSuper(s2));
        assertFalse(w.catContainsSuper(a1, s2));
        //No pots afegir un super a la wikipedia si la categoria a la que esta
        // relacionada no esta a la wikipedia
        w.addCategory(a1);
        w.addSuper(a1, s1);
        // Saltara una exception -> w.removeSuper(a1, s2);
        //Les operacions que no l'afecten mantenen el contingut
        assertTrue(w.wikiContainsSuper(s1));
        assertTrue(w.wikiContainsSuper("s1"));
        assertTrue(w.catContainsSuper(a1, s1));
        assertTrue(w.catContainsSuper("a1", "s1"));
        w.removeSuper(a1, s1);
        w.addSuper(a1, s1);
        //Pots afegir i tornar a posar
        assertTrue(w.wikiContainsSuper(s1));
        assertTrue(w.wikiContainsSuper("s1"));
        Category s3 = new Category("s1");
        // Saltara una exception -> w.addCategory(s3);
        //Ara tenim dos categories objecte que estan contingudes dintre de Wikipedia
        //i dintre de la categoria
        assertTrue(w.wikiContainsSuper(s3));
        assertTrue(w.catContainsSuper(a1, s3));
        w.removeSuper(a1, s3);
        //Si treiem un objecte en realitat expulsem el seu id de la Wikipedia
        //S'arregla el problema del test addSupers
        assertFalse(w.wikiContainsSuper(s3));
        assertFalse(w.wikiContainsSuper(s1));
        assertFalse(w.catContainsSuper(a1, s3));
        assertFalse(w.catContainsSuper("a1", "s1"));
    }

    public void testRemoveSupers2() throws WikipediaException { //Considerem 2 Categories
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category s1 = new Category ("s1");
        Category s2 = new Category ("s2");
        Category s3 = new Category ("s3");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addCategory(s3);
        w.addSuper(a1, s1);
        w.addSuper(a1, s2);
        w.addSuper(a1, s3);
        w.addSuper(a2, s1);
        w.addSuper(a2, s3);
        w.removeCategory(a1);
        //Treiem correctament la categoria, el super es queda a l'altra categoria
        assertTrue(w.hasSupers());
        assertTrue(w.wikiContainsSuper("s1"));
        assertTrue(w.wikiContainsSuper(s1));
        assertFalse(w.catContainsSuper("a1", "s1"));
        assertFalse(w.catContainsSuper(a1, s1));
        assertFalse(w.catContainsSuper("a1", "s2"));
        assertFalse(w.catContainsSuper(a1, s2));
        assertFalse(w.catContainsSuper("a1", "s3"));
        assertFalse(w.catContainsSuper(a1, s3));
        assertTrue(w.catContainsSuper("a2", "s1"));
        assertTrue(w.catContainsSuper(a2, s1));
        assertTrue(w.catContainsSuper("a2", "s3"));
        assertTrue(w.catContainsSuper(a2, s3));
    }

    public void testRestrictionsSuper() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        w.addCategory(a1);
        // Saltara una exception -> w.addSuper(a1,a1);
        //Una categoria no pot ser super d'ella mateixa
        assertFalse(w.hasSubs());
    }

    public void testRestrictionsSubSuper() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addSub(a1, a2);
        // Saltara una exception -> w.addSuper(a1, a2);
        //Una categoria no pot ser super  si es sub de una
        assertTrue(w.catContainsSub(a1, a2));
        assertFalse(w.catContainsSuper(a1, a2));
        w.addSuper(a2, a1);
        // Saltara una exception -> w.addSub(a2, a1);
        //Una categoria no pot ser sub  si es super de una
        assertTrue(w.catContainsSuper(a2,a1));
        assertFalse(w.catContainsSub(a2, a1));
    }

    public void testPageCats() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category a3 = new Category("a3");
        Page p1 = new Page ("p1");
        Page p2 = new Page ("p2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(a3);
        w.addPage(a1,p1);
        w.addPage(a2, p1);
        w.addPage(a3, p1);
        w.addPage(a1, p2);
        w.addPage(a3, p2);
        Set<Category> hsc = w.getPageCats(p1);
        //Comprovem que la pagina 1 estigui continguda en aquestes categories
        assertTrue(hsc.contains(a1));
        assertTrue(hsc.contains(a2));
        assertTrue(hsc.contains(a3));
        Set<Category> hsc2 = w.getPageCats("p2");
        //Comprovem que la pagina 2 estigui continguda en aquestes categories
        assertTrue(hsc2.contains(a1));
        assertFalse(hsc2.contains(a2));
        assertTrue(hsc2.contains(a3));
    }

    public void testIsSub() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category a3 = new Category("a3");
        Category s1 = new Category("s1");
        Category s2 = new Category("s2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(a3);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addSub(a1, s1);
        w.addSub(a2, s1);
        w.addSub(a3, s1);
        w.addSub(a1, s2);
        w.addSub(a3, s2);
        Set<Category> hsc = w.getIsSub(s1);
        //Comprovem que la SubCategoria 1 estigui continguda en aquestes categories
        assertTrue(hsc.contains(a1));
        assertTrue(hsc.contains(a2));
        assertTrue(hsc.contains(a3));
        Set<Category> hsc2 = w.getIsSub("s2");
        //Comprovem que la SubCategoria 2 estigui continguda en aquestes categories
        assertTrue(hsc2.contains(a1));
        assertFalse(hsc2.contains(a2));
        assertTrue(hsc2.contains(a3));
    }

    public void testIsSuper() throws WikipediaException {
        Wikipedia w = new Wikipedia("1");
        Category a1 = new Category("a1");
        Category a2 = new Category("a2");
        Category a3 = new Category("a3");
        Category s1 = new Category("s1");
        Category s2 = new Category("s2");
        w.addCategory(a1);
        w.addCategory(a2);
        w.addCategory(a3);
        w.addCategory(s1);
        w.addCategory(s2);
        w.addSuper(a1, s1);
        w.addSuper(a2, s1);
        w.addSuper(a3, s1);
        w.addSuper(a1, s2);
        w.addSuper(a3, s2);
        Set<Category> hsc = w.getIsSuper(s1);
        //Comprovem que la SuperCategoria 1 estigui continguda en aquestes categories
        assertTrue(hsc.contains(a1));
        assertTrue(hsc.contains(a2));
        assertTrue(hsc.contains(a3));
        Set<Category> hsc2 = w.getIsSuper("s2");
        //Comprovem que la SuperCategoria 2 estigui continguda en aquestes categories
        assertTrue(hsc2.contains(a1));
        assertFalse(hsc2.contains(a2));
        assertTrue(hsc2.contains(a3));
    }
}