package presentation;

import domini.Grapher;
import domini.SolutionDomainController;
import presentation.GraphPanel.FloatNotEqual;

/**
 * JungPanel with FloatNotEqual as its edges.
 * @author Oriol Munoz Princep
 * @see JungPanel
 * @see FloatNotEqual
 */
public class GraphPanel extends JungPanel<FloatNotEqual>{

    /**
     * Creates a GraphPanel with the graph from {@link Grapher#getTranslation()}.
     */
    public GraphPanel() {
        super(Grapher.getTranslation(),
                SolutionDomainController.formatSolution(SolutionDomainController.getSolution()));
    }

    /**
     * Shows a new window with a JUNG Graph inside.
     */
    public static void showWindow() {
        showWindow(new GraphPanel());
    }

    /**
     * This is a class necessary for JUNG, since it doesn't allow the usage of Float as edges.
     * This is in essence a Float class without the equals method, so that JUNG doesn't complain and with
     * an added toString method to be able to use a ToStringLabeller properly.
     */
    public static class FloatNotEqual {
        float value;

        public FloatNotEqual(float value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return String.valueOf(value);
        }
    }
}