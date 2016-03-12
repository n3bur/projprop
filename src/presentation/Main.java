package presentation;

/**
 * Main class of the program
 * @author Aleix Pellisa Cortiella
 */
public class Main {
    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (
            new Runnable() {
                public void run() {
                    PresentationController presentationCtrl = new PresentationController();
                    presentationCtrl.initializePresentation();
                }
            }
        );
    }
}
