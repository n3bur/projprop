package tests;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import domini.Solution;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseGraph;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import presentation.GraphPanel.FloatNotEqual;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by koma on 19/05/15 for prop
 */
public class FastWikiTestJung {

    private static ArrayList<Color> colors = new ArrayList<>();
    private static ArrayList<ArrayList<String>> communityList = new ArrayList<>();

    public static void main(String[] args) {
        drawGraph(null);
    }

    public static void drawGraph(Solution s) {
//        Graph<String, FloatNotEqual> g = Grapher.getTranslation();

        //Graph<String, FloatNotEqual> g = translateGraph(orig);
        /*for (Community c : s.getCommunities()) {
            Random rand = new Random();
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            colors.add(new Color(r,g,b));
            communityList.add(new ArrayList<String>());
            for (Node n : c.getCommunity()) {
                communityList.get(communityList.size()-1).add(n.getId());
            }
        }*/
        /*Layout<Integer, String> layout = new CircleLayout(g);
        layout.setSize(new Dimension(300,300)); // sets the initial size of the space
        // The BasicVisualizationServer<V,E> is parametrized by the edge types
        BasicVisualizationServer<Integer,String> vv =
                new BasicVisualizationServer<Integer,String>(layout);
        vv.setPreferredSize(new Dimension(768,768)); //Sets the viewing area size
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
        vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

        JFrame frame = new JFrame("Simple Graph View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.pack();
        frame.setVisible(true);*/
        // I create the graph in the following...
        // Layout<V, E>, VisualizationComponent<V,E>

        Graph<String, FloatNotEqual> g = new SparseGraph<>();
        g.addVertex("a");
        g.addVertex("b");
        g.addVertex("c");
        g.addEdge(new FloatNotEqual(1), "a", "b");
        g.addEdge(new FloatNotEqual(1), "b", "c");
        g.addEdge(new FloatNotEqual(1), "c", "a");

        g.addVertex("d");
        g.addVertex("e");
        g.addVertex("f");
        g.addEdge(new FloatNotEqual(1), "d", "e");
        g.addEdge(new FloatNotEqual(1), "e", "f");
        g.addEdge(new FloatNotEqual(1), "f", "d");

        Layout<String, FloatNotEqual> layout = new KKLayout<>(g);
        layout.setSize(new Dimension(700,700));
        VisualizationViewer<String, FloatNotEqual> vv =
                new VisualizationViewer<>(layout);
        vv.setPreferredSize(new Dimension(700,700));
        // Show vertex and edge labels
        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<FloatNotEqual>());
        vv.getRenderContext().setVertexFillPaintTransformer(new Transformer<String, Paint>() {
            @Override
            public Paint transform(String string) {
                for (int i = 0; i < communityList.size(); ++i) {
                    if (communityList.get(i).contains(string)) {
                        return colors.get(i);
                    }
                }
                return Color.PINK;
            }
        });
        // Create a graph mouse and add it to the visualization component
        DefaultModalGraphMouse gm = new DefaultModalGraphMouse();
        gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
        vv.setGraphMouse(gm);
        vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setScale(1, 1, vv.getCenter());
        vv.setDoubleBuffered(true);

        JFrame frame = new JFrame("Interactive Graph View 1");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(vv);
        frame.setBounds(50, 100,       // Set position
                500, 500);
        //frame.getContentPane().add(new JLabel(new ImageIcon("asdf.jpg")));
        frame.pack();
        frame.setVisible(true);
        //writeJPEGImage(vv, "asdf.jpg");
    }

    private static void writeJPEGImage(VisualizationViewer vv, String filename) {
        vv.setDoubleBuffered(false);
        Dimension size = vv.getSize();
        BufferedImage myImage =
                new BufferedImage(size.width, size.height,
                        BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = myImage.createGraphics();
        vv.paint(g2);
        try {
            OutputStream out = new FileOutputStream(filename);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            encoder.encode(myImage);
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        vv.setDoubleBuffered(true);
    }
}
