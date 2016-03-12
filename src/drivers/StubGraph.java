package drivers;

import domini.Edge;

import java.util.*;

/**
 * Created by pau on 19/04/15.
 */
public class StubGraph {

    // SPECIFIED

    private HashMap<StubNode, HashMap<StubNode, StubEdge>> graph;

    public Set GetAdjacentNodesTo (StubNode node) {
        return graph.keySet();
    }

    /**
     * Add a new disconnected node (without edges to any node)
     * @param node The node to be added
     */
    public void AddNode(StubNode node) {

    }

    // TO BE SPECIFIED

    public ArrayList<Edge> getAllEdges() {
        return new ArrayList<Edge>();
    };

    /* Pre: true.
       Post:
         All edges of the graph have been removed.
         The graph is now the same size as before
         but no node is adjacent to any other one
    */
    public void removeAllEdges() {
    }

    /* Pre: the nodes of e belong to the graph.
       Post: e has been inserted to the graph.
     */
    public void addEdge(StubEdge e) {
    }
}
