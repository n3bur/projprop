package presentation;

import domini.SolutionDomainController;
import edu.uci.ics.jung.algorithms.layout.ISOMLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import presentation.SolutionPanel.StringNotEqual;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * JPanel that contains a JUNG Graph (which could be a simplified version of a solution or a full graph).
 * @author Oriol Munoz Princep
 */
public abstract class JungPanel<E> extends JPanel{

    /**
     * The colors of the vertices of the JUNG Graph.
     */
    private ArrayList<Color> colors = new ArrayList<>();
    /**
     * The list of communities of the Graph.
     */
    private ArrayList<ArrayList<String>> communityList;
    /**
     * The JUNG Graph.
     */
    private Graph<String, E> graph = new SparseGraph<>();
    /**
     * The JUNG Graph's VisualizationViewer.
     */
    private VisualizationViewer<String, E> vv;
    /**
     * The JUNG Graph's Transformer to change the vertices' color.
     */
    private Transformer<String, Paint> paintTransformer;

    /**
     * Threshold for which a TooManyVerticesPanel should be shown.
     */
    private static final int TOO_MANY_VERTICES = 300;

    /**
     * Constructor of a JungPanel for a full graph.
     * @param graph the JUNG Graph, not null
     * @param communityList the list of communities, not null
     */
    public JungPanel(Graph<String, E> graph,
                     ArrayList<ArrayList<String>> communityList) {
        this.graph = graph;
        if (graph.getVertexCount() > 0) {
            removeInformationFromSolution(communityList);
            this.communityList = communityList;
            Layout<String, E> layout = new ISOMLayout<>(graph);
            layout.setSize(new Dimension(1152, 768 - 50));
            this.vv = new VisualizationViewer<>(layout);
            colorForEachCommunity(communityList);
            paintTransformer = new RandomColorTransformer();
            showGraph();
        }
        else noVertices();
    }

    /**
     * Constructor of a JungPanel for a simplified version of a graph depending only on solutions.
     * @param id the id of a valid solution, not null
     */
    public JungPanel(String id) {
        try {
            communityList = SolutionDomainController.getSolution(id);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("WRONG ID!?");
        }

        removeInformationFromSolution(communityList);

        //Make communities a circle
        Graph<String, StringNotEqual> graph = new SparseGraph<>();
        for (ArrayList<String> al : communityList) {
            graph.addVertex(al.get(0));
            for (int i = 1; i < al.size(); ++i) {
                graph.addVertex(al.get(i));
                graph.addEdge(new StringNotEqual(""), al.get(i-1), al.get(i));
            }
            graph.addEdge(new StringNotEqual(""), al.get(0), al.get(al.size()-1));
        }
        if (graph.getVertexCount() > 0) {
            //noinspection unchecked
            Layout<String, E> layout = (Layout<String, E>) new ISOMLayout<>(graph);
            this.vv = new VisualizationViewer<>(layout);
            //noinspection unchecked
            this.graph = (Graph<String, E>) graph;
            colorForEachCommunity(communityList);
            paintTransformer = new RandomColorTransformer();
            showGraph();
        }
        else noVertices();
    }

    /**
     * Helper method that shows either the graph or a TooManyVerticesPanel depending on the size of the JUNG Graph.
     */
    private void showGraph() {
        int size = graph.getVertexCount();
        if (size >= TOO_MANY_VERTICES) {
            tooManyVertices();
        }
        else {
            renderGraph();
        }
    }

    /**
     * Helper method that renders the JUNG Graph on screen and adds a PNGButton at the bottom.
     * This method should also be called from a TooManyVerticesPanel Show Button.
     */
    public void renderGraph() {
        removeAll();

        ToStringLabeller<String> tslVertex = new ToStringLabeller<>();
        ToStringLabeller<E> tslEdge = new ToStringLabeller<>();
        vv.getRenderContext().setVertexLabelTransformer(tslVertex);
        vv.getRenderContext().setEdgeLabelTransformer(tslEdge);
        vv.getRenderContext().setVertexFillPaintTransformer(paintTransformer);
        vv.setVertexToolTipTransformer(new CommunityVertexTooltipTransformer());
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setScale(1, 1, vv.getCenter());
        vv.setDoubleBuffered(true);

        setLayout(new BorderLayout());
        add(BorderLayout.CENTER, vv);
        PNGButton pngButton = new PNGButton(vv, paintTransformer, tslVertex, tslEdge);
        add(BorderLayout.SOUTH, pngButton);
        vv.setPreferredSize(new Dimension(1152, 768-75));
        pngButton.setPreferredSize(new Dimension(1152, 25));
        JFrame frame = (JFrame)getTopLevelAncestor();
        if (frame != null) {
            frame.pack();
        }
    }

    /**
     * Helper method that sets a different random color for each community inside the graph. There's obviously
     * a chance that two communities might have the same color.
     * @param s the list of communities of the graph, not null
     */
    private void colorForEachCommunity(ArrayList<ArrayList<String>> s) {
        //Random color for each community
        for (ArrayList<String> ignored : s) {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colors.add(new Color(r,g,b));
        }
    }

    /**
     * Helper method that shows on screen a JLabel saying there are no vertices inside the graph, so there's nothing
     * to be shown.
     */
    private void noVertices() {
        removeAll();
        add(new JLabel("No hi ha res al graf."));
    }

    /**
     * Helper method when the graph has more or equal number of vertices than {@link #TOO_MANY_VERTICES}.
     */
    private void tooManyVertices() {
        removeAll();
        add(new TooManyVerticesPanel(this));
    }

    /**
     * Helper method that strips the first three rows of a solution, since they contain irrelevant information
     * for the showing of a graph.
     * @param s the list of communities, not null
     */
    private static void removeInformationFromSolution(ArrayList<ArrayList<String>> s) {
        //Delete all information about id, time and algorithm
        for (int i = 0; i < 3; ++i) {
            s.remove(0);
        }
    }

    /**
     * Shows a new window with a JUNG Graph inside.
     * @param panel the panel where the graph is.
     */
    protected static void showWindow(JungPanel panel) {
        JFrame frame = new JFrame(panel.getClass().getSimpleName()); //Insignificant performance hit, very readable.
        frame.setSize(1152, 768-25);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(BorderLayout.CENTER, panel);
        frame.setVisible(true);
    }

    /**
     * Transformer that assigns each Vertex a different color according to {@link #colors}.
     * If a vertex is not inside a community, it returns the {@link #DEFAULT_COLOR}.
     */
    protected class RandomColorTransformer implements Transformer<String, Paint> {

        private final Color DEFAULT_COLOR = Color.PINK;

        @Override
        public Paint transform(String string) {
            for (int i = 0; i < communityList.size(); ++i) {
                if (communityList.get(i).contains(string)) {
                    return colors.get(i);
                }
            }
            return DEFAULT_COLOR;
        }
    }

    /**
     * Transformer that tells the user how many communities contain the node which has the mouse hovering over.
     * This class is specially helpful for Clique algorithm, since a node doesn't have to pertain to a community
     * and it can also pertain to more than one. Plus, it looks nice.
     */
    protected class CommunityVertexTooltipTransformer implements Transformer<String, String> {

        @Override
        public String transform(String s) {
            int ncommunities = 0;
            for (ArrayList<String> al : communityList) {
                if (al.contains(s)) ++ncommunities;
            }
            return "Aquest node pertany a " + ncommunities + " comunitats.";
        }
    }
}