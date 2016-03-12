package persistencia;

import edu.uci.ics.jung.visualization.VisualizationImageServer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class that manages the saving a JUNG formatted Graph to disk as a PNG Image.
 * @author Oriol Munoz Princep
 */
public abstract class GraphToImageController {

    /**
     * Saves the graph inside a VisualizationViewer as a PNG image to disk.
     * @param outputfilename the absolutepath of the file where it will be saved (including the name), not null
     * @param vv JUNG's VisualizationViewer of the graph that will be saved, not null
     * @param paintTransformer JUNG's VertexFillPaintTransformer of the graph that will be saved, not null
     * @param tslVertex JUNG's ToStringLabeller of the vertices, not null
     * @param tslEdge JUNG's ToStringLabeller of the edges, not null
     * @throws IOException
     * @see presentation.PNGButton
     */
    public static void saveAsImage(String outputfilename,
                            VisualizationViewer vv, Transformer<String, Paint> paintTransformer,
                            ToStringLabeller tslVertex, ToStringLabeller tslEdge) throws IOException{
        //Irrelevant warnings
        VisualizationImageServer vis = new VisualizationImageServer(vv.getGraphLayout(), vv.getGraphLayout().getSize());
        vis.setBackground(Color.WHITE);
        vis.getRenderContext().setEdgeLabelTransformer(tslVertex);
        vis.getRenderContext().setVertexLabelTransformer(tslEdge);
        vis.getRenderContext().setVertexFillPaintTransformer(paintTransformer);


        // Create the buffered image
        BufferedImage image = (BufferedImage) vis.getImage(
                new Point2D.Double(vv.getGraphLayout().getSize().getWidth() / 2,
                        vv.getGraphLayout().getSize().getHeight() / 2),
                new Dimension(vv.getGraphLayout().getSize()));

        //Ensure the extension is there
        if (!outputfilename.endsWith(".png")) outputfilename = outputfilename + ".png";

        //Write image to png file
        File outputfile = new File(outputfilename);
        ImageIO.write(image, "png", outputfile);
    }

}
