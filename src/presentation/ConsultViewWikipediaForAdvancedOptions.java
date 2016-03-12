package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.SortedSet;

/**
 * Class to show the consulted information in the advanced search
 * @author Aleix Pellisa Cortiella
 */

public class ConsultViewWikipediaForAdvancedOptions {

    private String[] strBotons = {"Ok"};
    TextArea textArea = new TextArea();

    /**
     * Method to personalize the dialog with categories
     * @param set SortedSet which contains the categories obtained in the advanced search
     * @param strTitul Title to the dialog
     */
    public void setDialogCategory(SortedSet<String> set, String strTitul) {
        for (String s : set) {
            textArea.append(s + "\n");
        }
        initialize(strTitul);
    }

    /**
     * Method to personalize the dialog with pages
     * @param set SortedSet which contains the pages obtained in the advanced search
     * @param strTitul Title to the dialog
     */
    public void setDialogPage(SortedSet<String> set, String strTitul) {
        for (String s : set) {
            textArea.append(s + "\n");
        }
        initialize(strTitul);
    }

    /**
     * Initialize the components of the view
     * @param strTitul The title of the view
     */
    private void initialize(String strTitul){
        JOptionPane optionPane =
                new JOptionPane(textArea,JOptionPane.INFORMATION_MESSAGE);
        optionPane.setOptions(strBotons);
        JDialog dialogOptionPane = optionPane.createDialog(new JFrame(),strTitul);
        dialogOptionPane.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);
    }
}
