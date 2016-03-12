package presentation;

import presentation.SolutionPanel.StringNotEqual;

import javax.swing.*;

/**
 * JungPanel with FloatNotEqual as its edges.
 * @author Oriol Munoz Princep
 * @see JungPanel
 * @see StringNotEqual
 */
public class SolutionPanel extends JungPanel<StringNotEqual>{

    /**
     * Creates a JungPanel with a simplified graph from a solution.
     * @param id the id of the solution to show, not null
     */
    public SolutionPanel(String id) {
        super(id);
    }

    /**
     * Shows a new window with a JUNG Graph inside.
     */
    public static void showWindow(String id) {
        showWindow(new SolutionPanel(id));
    }

    /**
     * This is a class necessary for JUNG, since it doesn't allow the usage of String as edges.
     * This is in essence a String class without the equals method, so that JUNG doesn't complain and with
     * an added toString method to be able to use a ToStringLabeller properly (it really just returns the String).
     */
    public static class StringNotEqual {
        String value;

        public StringNotEqual(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
}
