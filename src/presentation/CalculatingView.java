package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog to appear while searching a solution. It allows the user to cancel the execution too.
 * @author Pau Oliver
 */
public class CalculatingView extends JDialog {

    public static JLabel text;

    public CalculatingView() {
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        setBounds(600, 600, 400, 180);
        getContentPane().setLayout(null);

        text = new JLabel("Cercant categories similars...");
        text.setHorizontalAlignment(SwingConstants.LEFT);
        text.setFont(new Font(null, Font.PLAIN, 16));
        text.setBounds(77, 10, 300, 60);
        getContentPane().add(text);

        final JButton cancelButton = new JButton("Cancel·lar");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                cancelButton.setText("Cancel·lant...");
                close();
            }
        });

        cancelButton.setBounds(110, 80, 200, 30);
        getContentPane().add(cancelButton);

        setResizable(false);
        setVisible(true);
    }

    /**
     * Close the view.
     */
    public void close() {
        this.dispose();
    }

}
