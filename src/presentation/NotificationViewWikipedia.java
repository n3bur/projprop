package presentation;

import javax.swing.*;

/**
 * Dialog class to report an error or success during the program
 * @author Aleix Pellisa Cortiella
 */
public class NotificationViewWikipedia {

    private String[] strBotons = {"Tornar"};
    private JOptionPane optionPane;

    /**
     * Method to personalize the dialog
     * @param strTitul Title to the dialog
     * @param strText Text to the dialog
     */
    public void setError
            (String strTitul, String strText) {
        // Crea i visualiza el dialeg
        optionPane = new JOptionPane(strText,JOptionPane.ERROR_MESSAGE);
        initialize(strTitul);

    }

    /**
     * Method to personalize the dialog
     * @param strTitul Title to the dialog
     * @param strText Text to the dialog
     */
    public void setSuccess
            (String strTitul, String strText) {
        // Crea i visualiza el dialeg
        optionPane = new JOptionPane(strText,JOptionPane.INFORMATION_MESSAGE);
        initialize(strTitul);
    }

    /**
     * Initialize the components of the view
     * @param strTitul The title of the view
     */
    private void initialize(String strTitul){
        optionPane.setOptions(strBotons);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(),strTitul);
        dialogOptionPane.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);
    }
}
