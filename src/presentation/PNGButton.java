package presentation;

import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import org.apache.commons.collections15.Transformer;
import persistencia.GraphToImageController;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * @author Oriol Munoz Princep
 * @see JungPanel
 */
public class PNGButton extends JButton {

    /**
     * Default extension for all files.
     */
    private static final String EXTENSION = ".png";

    private VisualizationViewer vv;
    private Transformer<String, Paint> paintTransformer;
    private ToStringLabeller tslVertex;
    private ToStringLabeller tslEdge;

    /**
     * Constructor of a PNGButton.
     * @param vv JUNG's VisualizationViewer of the graph that will be saved, not null
     * @param paintTransformer JUNG's VertexFillPaintTransformer of the graph that will be saved, not null
     * @param tslVertex JUNG's ToStringLabeller of the vertices, not null
     * @param tslEdge JUNG's ToStringLabeller of the edges, not null
     * @see GraphToImageController
     */
    public PNGButton(VisualizationViewer vv, Transformer<String, Paint> paintTransformer,
                     ToStringLabeller tslVertex, ToStringLabeller tslEdge) {
        super("Desa com a PNG");
        this.vv = vv;
        this.paintTransformer = paintTransformer;
        this.tslVertex = tslVertex;
        this.tslEdge = tslEdge;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                writeToPNG();
            }
        });
    }

    /**
     * Writes the graph that shares panel with this button to disk as a PNG image.
     */
    private void writeToPNG() {
        //Select file
        String outputfilename = "image";
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Desar imatge");
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "PNG Images", "png");
        chooser.setFileFilter(filter);
        chooser.setSelectedFile(new File(outputfilename+EXTENSION));
        chooser.setMultiSelectionEnabled(false);
        int returnVal = chooser.showOpenDialog(getParent());
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            outputfilename = chooser.getSelectedFile().getAbsolutePath();
            try {
                GraphToImageController.saveAsImage(outputfilename, vv, paintTransformer, tslVertex, tslEdge);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                        "Hi ha hagut algun error desant el fitxer.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
