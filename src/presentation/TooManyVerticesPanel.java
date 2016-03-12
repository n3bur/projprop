package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * JPanel with a warning that a JUNG Graph contains too many vertices, so it might be too costly to draw.
 * @author Oriol Munoz Princep
 */
public class TooManyVerticesPanel extends JPanel{

    private JButton tooManyNodesButton = new JButton("Mostra");
    private Label tooManyNodesLabel = new Label("Hi ha massa nodes com per generar el graf de forma ràpida.");

    private JungPanel jungPanel;

    /**
     * Creates a new instance of TooManyVerticesPanel.
     * @param jungPanel the JungPanel that will contain this instance
     */
    public TooManyVerticesPanel(final JungPanel jungPanel) {
        this.jungPanel = jungPanel;
        tooManyNodesButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jungPanel.renderGraph();
            }
        });
        addButtons();
    }

    /**
     * Helper method that adds all buttons this panel needs. Basically, a text and a button.
     */
    private void addButtons() {
        removeAll();
        add(tooManyNodesLabel);
        add(tooManyNodesButton);
    }
}
