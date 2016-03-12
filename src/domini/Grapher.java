package domini;

import edu.uci.ics.jung.graph.SparseGraph;
import presentation.GraphPanel;

import java.util.Set;

/**
 * This class contains helper methods for WikipediaDomainController to obtain graphs from
 * a Wikipedia.
 * @author Oriol Munoz Princep
 * @see WikipediaDomainController
 */
public abstract class Grapher {

    /**
     * The translation of the graph to JUNG format, so that it can be showed on screen.
     * @see presentation.JungPanel
     */
    private static edu.uci.ics.jung.graph.Graph<String, GraphPanel.FloatNotEqual> translation;

    private static final int NUMBER_OF_WEIGHT_TYPES = 5; //1 for each field below, remember to update!
    private static final float MIN_WEIGHT = 0f; //no hi ha aresta per a aquest valor
    private static final float MAX_WEIGHT = 100f;

    /**Minimum percentage of coincidences to set the weight as the weight of the criteria*/
    private static final float PERCENTAGE_OF_FULL_CONSIDERATION = 0.50f;

    //default values
    private static float pesSimiliaritatNoms = MAX_WEIGHT/NUMBER_OF_WEIGHT_TYPES;
    private static float pesNumSuperCatComu = MAX_WEIGHT/NUMBER_OF_WEIGHT_TYPES;
    private static float pesNumSubCatComu = MAX_WEIGHT/NUMBER_OF_WEIGHT_TYPES;
    private static float pesNumPagComu = MAX_WEIGHT/NUMBER_OF_WEIGHT_TYPES;
    private static float pesSerSubOSuper = MAX_WEIGHT/NUMBER_OF_WEIGHT_TYPES;

    //Pre: g is empty
    //Post: g is the corresponding graph to w

    /**
     * Method for obtaining a Graph from a Wikipedia.
     * @param w Wikipedia to convert to graph
     * @return The graph corresponding to the Wikipedia w
     */
    public static Graph<Category> createGraph(Wikipedia w) {
        translation = new SparseGraph<>();
        Graph<Category> g = new Graph<>();
        createNodesFromCategories(w, g);
        return g;
    }

    /**
     * Helper method that creates nodes from the categories of a Wikipedia and adds them to a graph.
     * @param w Wikipedia that contains the categories, not null
     * @param g Graph that will receive the nodes, not null
     */
    private static void createNodesFromCategories(Wikipedia w, Graph<Category> g) {
        for (Category c1 : w.getCategories()) {
            g.addNode(c1);
            translation.addVertex(c1.getId());
            //Mira els anteriorment afegits i afegeix arestes amb c1 si cal
            for (Category c2 : g.getAllNodes()) {
                if (!c2.equals(c1)) {
                    float weight = calculateWeight(c1, c2, w);
                    if (weight > 0.0f) {
                        g.addEdge(c1, c2, new Edge(weight));
                        translation.addEdge(new GraphPanel.FloatNotEqual(weight), c1.getId(), c2.getId());
                    }
                }
            }
        }
    }

    /**
     * Helper method that calculates the weight of the edge between two category nodes.
     * The minimum weight of the edge will be equals to {@link #MIN_WEIGHT} and the maximum
     * weight of the edge will be equals to {@link #MAX_WEIGHT}.
     * @param c1 , not null
     * @param c2 , not null
     * @param w Wikipedia that contains c1, c2 and its pages, subcategories and supercategories, not null
     * @return The weight of the edge between c1 and c2
     */
    private static float calculateWeight(Category c1, Category c2, Wikipedia w) {
        float weight = 0.0f;
        //weights <= MIN_WEIGHT must not be considered
        if (pesSimiliaritatNoms > MIN_WEIGHT) {
            if(GrapherUtils.areSimilar(c1.getId(), c2.getId())) weight += pesSimiliaritatNoms;
        }
        //Is one sub/super of the other?
        if (pesSerSubOSuper > MIN_WEIGHT) {
            if(w.getSupers(c2).contains(c1) || w.getSupers(c1).contains(c2) ||
               w.getSubs(c1).contains(c2)   || w.getSubs(c2).contains(c1))
                weight += pesSerSubOSuper;
        }
        if (pesNumPagComu > MIN_WEIGHT) {
            weight += weightFromSets(w.getPages(c1), w.getPages(c2), pesNumPagComu);
        }
        if (pesNumSubCatComu > MIN_WEIGHT) {
            weight += weightFromSets(w.getSubs(c1), w.getSubs(c2), pesNumSubCatComu);
        }
        if (pesNumSuperCatComu > MIN_WEIGHT) {
            weight += weightFromSets(w.getSupers(c1), w.getSupers(c2), pesNumSuperCatComu);
        }
        return weight;
    }

    /**
     * Helper method that calculates the partial weight corresponding to the coincidences between the two sets
     * passed as parameters, up to the maximum specified by criteria.
     * <p>
     * This is based in piecewise functions with ratio = coincidences/maxElements (being elements pages,
     * subcategories or subcategories).
     * <p>
     * returnedWeight(y) = slope*ratiocoincidences(x) IF ratiocoincidences < {@link #PERCENTAGE_OF_FULL_CONSIDERATION}
     * <p>
     * returnedWeight(y) = criteria IF ratiocoincidences >= {@link #PERCENTAGE_OF_FULL_CONSIDERATION}
     * <p>
     * When the X axis is equal to {@link #PERCENTAGE_OF_FULL_CONSIDERATION}, the returned weight (Y) should be
     * exactly the weight of the criteria.
     * From this, we get the slope, which is equals to the weight of the criteria divided by the
     * {@link #PERCENTAGE_OF_FULL_CONSIDERATION}, so the weight to be returned will be equal to the slope multiplied
     * by the ratio of coincidences.
     * @param set1 , not null
     * @param set2 , not null
     * @param criteria Weight of the criteria to calculate
     * @return the partial weight of the criteria for the given sets
     */
    private static float weightFromSets(Set set1, Set set2, float criteria) {
        float weight = 0;
        if (!set1.isEmpty() && !set2.isEmpty()) {
            int coincidences = 0;
            int maxElements = Math.max(set1.size(), set2.size());
            //Count coincidences
            for (Object o : set1) {
                if (set2.contains(o)) ++coincidences;
            }
            float ratioCoincidences = (float) coincidences / maxElements;
            if (ratioCoincidences >= PERCENTAGE_OF_FULL_CONSIDERATION) weight += criteria;
            else {
                float pendent = criteria / PERCENTAGE_OF_FULL_CONSIDERATION;
                weight += pendent * ratioCoincidences;
            }
        }
        return weight;
    }

    public static float getPesSimiliaritatNoms() {
        return pesSimiliaritatNoms;
    }

    public static void setPesSimiliaritatNoms(float pesSimiliaritatNoms) {
        Grapher.pesSimiliaritatNoms = pesSimiliaritatNoms;
    }

    public static float getPesNumSuperCatComu() {
        return pesNumSuperCatComu;
    }

    public static void setPesNumSuperCatComu(float pesNumSuperCatComu) {
        Grapher.pesNumSuperCatComu = pesNumSuperCatComu;
    }

    public static float getPesNumSubCatComu() {
        return pesNumSubCatComu;
    }

    public static void setPesNumSubCatComu(float pesNumSubCatComu) {
        Grapher.pesNumSubCatComu = pesNumSubCatComu;
    }

    public static float getPesNumPagComu() {
        return pesNumPagComu;
    }

    public static void setPesNumPagComu(float pesNumPagComu) {
        Grapher.pesNumPagComu = pesNumPagComu;
    }

    public static float getPesSerSubOSuper() {
        return pesSerSubOSuper;
    }

    public static void setPesSerSubOSuper(float pesSerSubOSuper) {
        Grapher.pesSerSubOSuper = pesSerSubOSuper;
    }

    public static edu.uci.ics.jung.graph.Graph<String, GraphPanel.FloatNotEqual> getTranslation() {
        return translation;
    }
}
