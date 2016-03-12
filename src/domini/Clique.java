package domini;

import java.util.*;

/**
 * This class implements de Clique algorithm to find communitities in graphs.
 * @author Pau Oliver
 * @see Algorithm
 */
public class Clique extends Algorithm{
    private float preWeight, currWeight;
    private boolean end;
    private int pMax, pSecMax, sMax, sSecMax;

    /**
     *
     * @param g the graph where communities are to be searched
     * @return the solution containing the communities found
     * @throws Exception
     */
    public Solution getSolution(Graph g) throws Exception {
        long before = System.nanoTime();
        pMax = pSecMax = sMax = sSecMax = -1;
        preWeight = currWeight = -1;
        end = false;

        Solution s;
        DJSets ds = new DJSets(0);
        HashMap<Edge, Integer> edgeIndexes = new HashMap<>();

        ArrayList<Pair<Edge, Pair<? extends Node, ? extends Node>>> edgesInfo = new ArrayList<>();
        long b = System.nanoTime();
        Set<Edge> edges = g.getAllEdges();
        for (Edge e : edges) {
            edgesInfo.add(new Pair<Edge, Pair<? extends Node, ? extends Node>>(e, g.getNodesConnectedBy(e)));
        }
        g.removeAllEdges();
        Collections.sort(edgesInfo, new Comparator<Pair<Edge, Pair<? extends Node, ? extends Node>>>() {
            @Override
            public int compare(Pair<Edge, Pair<? extends Node, ? extends Node>> edgePairPair, Pair<Edge, Pair<? extends Node, ? extends Node>> t1) {
                return -Float.compare(edgePairPair.getFirst().getWeight(), t1.getFirst().getWeight());
            }
        });
        phase1(g, edgesInfo, ds, edgeIndexes);
        s = buildSolution(edgeIndexes, ds, g);
        s.setTime(System.nanoTime() - before);
        s.setAlg('C');
        return s;
    }

    /**
     * First phase of the algorithm, where cliques are found.
     * @param g the graph being processed
     * @param edges the list of edges and the nodes they are joining together
     * @param ds a disjoint-sets structure identifying where each set corresponds to a community found
     * @param edgeIndexes the translation from an edge to its index in ds
     * @throws Exception
     */
    private void phase1(Graph<Node> g, ArrayList<Pair<Edge, Pair<? extends Node, ? extends Node>>> edges, DJSets ds, HashMap<Edge, Integer> edgeIndexes) throws Exception {
        int k = 3;
        for (Pair<Edge, Pair<? extends Node, ? extends Node>> p : edges) {
            preWeight = currWeight;
            currWeight = p.getFirst().getWeight();
            int sizeMax = sMax;
            int sizeSecMax = sSecMax;
            if (!end) {
                end = ((sizeSecMax != -1) && (sizeMax != -1) &&
                        (sizeMax > 2.1*sizeSecMax) && (g.getAllEdges().size() >= 0.04*edges.size()) &&
                        (preWeight != -1) && (preWeight != currWeight));

            }
            Edge e = p.getFirst();
            Pair<? extends Node, ? extends Node> nodes = p.getSecond();
            if (e == null) throw new Exception("Phase 1 found a null edge.");
            else {
                Node v1 = nodes.getFirst();
                Node v2 = nodes.getSecond();
                g.addEdge(v1, v2, e);
                if ((!end) && (g.getAdjacentNodesTo(v1).size() >= k - 1 && g.getAdjacentNodesTo(v2).size() >= k - 1)) {
                    /**
                     *  The added edge may release a clique. Let's find common neighbours of its nodes.
                     *  Collect all neighbours of both v1 and v2.
                     */
                    Set<Node> NV1 = g.getAdjacentNodesTo(v1);
                    Set<Node> NV2 = g.getAdjacentNodesTo(v2);
                    Set<Node> commonNeighbours = new HashSet<Node>();
                    for (Node n : NV1) {
                        if (NV2.contains(n)) {
                            // n is a common neighbour
                            commonNeighbours.add(n);
                        }
                    }
                    // "We found " + commonNeighbours.size() + " common neighbours.\n"
                    for (Node n : commonNeighbours) {
                        // We take advantage of k being 3.
                        phase2(g, new Node[] {n, v1, v2}, ds, edgeIndexes);
                    }
                }
            }
        }
    }

    /**
     * Second phase, where the found cliques join the same community
     * @param g the graph being processed
     * @param threeClique the clique found in phase1
     * @param ds a disjoint-sets structure identifying where each set corresponds to a community found
     * @param edgeIndexes the translation from an edge to its index in ds
     * @throws Exception
     */
    private void phase2(Graph<Node> g, Node[] threeClique, DJSets ds, HashMap<Edge, Integer> edgeIndexes) throws Exception {
        int k = 3;
        if(threeClique.length != k) throw new IllegalArgumentException("Phase 2 received a non 3-clique.");
        Edge[] edges = new Edge[k];
        edges[0] = g.getEdge(threeClique[0], threeClique[1]);
        edges[1] = g.getEdge(threeClique[0], threeClique[2]);
        edges[2] = g.getEdge(threeClique[1], threeClique[2]);

        if (!edgeIndexes.containsKey(edges[0])) { edgeIndexes.put(edges[0], edgeIndexes.size()); ds.add(); }
        if (!edgeIndexes.containsKey(edges[1])) { edgeIndexes.put(edges[1], edgeIndexes.size()); ds.add(); }
        if (!edgeIndexes.containsKey(edges[2])) { edgeIndexes.put(edges[2], edgeIndexes.size()); ds.add(); }
        if (ds.size() != edgeIndexes.size()) {
            throw new Exception("Inconsistency: DJSets of different size than the edgeIndexes.");
        }

        int newSize = -1;
        // Every (2)-clique (there are 3) belongs to the same community, so we merge them
        int rootA = ds.find((edgeIndexes.get(edges[0])));
        int rootB = ds.find((edgeIndexes.get(edges[1])));
        if (rootA != rootB) newSize = ds.union(rootA, rootB);

        rootA = ds.find((edgeIndexes.get(edges[0])));
        rootB = ds.find(edgeIndexes.get(edges[2]));
        if (rootA != rootB) newSize = ds.union(rootA, rootB);
        int root = ds.find(edgeIndexes.get(edges[0]));

        if (newSize >= sMax) {
            // if it is a new community, we update the second largest community
            if (pMax == -1 || ds.find(root) != ds.find(pMax)) {
                if (pSecMax == -1 || ds.find(pSecMax) != ds.find(pMax)) {
                    sSecMax = sMax;
                    pSecMax = pMax;
                }
            }
            pMax = root;
            sMax = newSize;
        } else if (newSize > sSecMax) {
            pSecMax = root;
            sSecMax = newSize;
        }
        // If the 2 largest communitites have merged in one, we correct the SecMax
        if (pSecMax != -1 && pMax != -1 && ds.find(pSecMax) == ds.find(pMax)) {
            sMax = sSecMax = pMax = pSecMax = -1;
            for (int i = 0; i < ds.size(); ++i) {
                int s = ds.sizeOf(i);
                if (s > sMax) {
                    sSecMax = sMax;
                    sMax = s;
                    pSecMax = pMax;
                    pMax = i;
                }
                else if (s > sSecMax) {
                    if (pSecMax == -1 || ds.find(pSecMax) != ds.find(i)) {
                        sSecMax = s;
                        pSecMax = i;
                    }
                }
            }
        }
    }

    /**
     * Builds a Solution from a disjoint-sets
     * @param edgeIndexes the translation from edges to their index in ds
     * @param ds a disjoint-sets structure where each set corresponds to a community found
     * @param g the graph being processed
     * @return the solution built
     */
    private Solution buildSolution(HashMap<Edge, Integer> edgeIndexes, DJSets ds, Graph g) {
        Solution s = new Solution();
        HashMap<Integer, Community> comms = new HashMap<>();
        for (Edge e : edgeIndexes.keySet()) {
            Community c;
            int commNum = ds.find(edgeIndexes.get(e));
            if (comms.containsKey(commNum)) c = comms.get(commNum);
            else { c = new Community(); comms.put(commNum, c); }
            c.addNode((Node) g.getNodesConnectedBy(e).getFirst());
            c.addNode((Node) g.getNodesConnectedBy(e).getSecond());
        }
        for (Community c : comms.values()) {
            s.addCommunity(c);
        }
        return s;
    }
}
