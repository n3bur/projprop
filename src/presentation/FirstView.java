package presentation;

import javax.swing.*;
import java.awt.*;

/**
 * View class used to be the main frame of the presentation which receives panels to change the content
 * @author Aleix Pellisa Cortiella
 */

public class FirstView {

    /** Presentation controller */
    private PresentationController iPresentationController;

    private JFrame frameVista = new JFrame("Wikipedia");
    private JPanel panelContent = new JPanel();

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public FirstView (PresentationController pPresentationController) {
        iPresentationController = pPresentationController;
        frameVista.setMinimumSize(new Dimension(1152, 720));
        frameVista.setPreferredSize(frameVista.getMinimumSize());
        frameVista.setLocationRelativeTo(null);

        frameVista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initialize_MainView();
    }

    /**
     * Method to enable the view when it is disabled
     */
    public void enable(){
        frameVista.enable();
    }

    /**
     * Method to make visible the view when it is invisible
     */
    public void makeVisible() {
        frameVista.pack();
        frameVista.setVisible(true);
    }

    /**
     * Method used to change the title to the view
     * @param s Title to the view
     */
    public void changeTitle(String s){
        frameVista.setTitle("Wikipedia "+s);
    }

    /**
     * Method used to set the WelcomePanel to the current panel
     */
    public void initialize_MainView(){
        frameVista.remove(panelContent);
        panelContent = new WelcomePanel(iPresentationController);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the WikipediaManagementView to the current panel
     */
    public void initialize_WikipediaManagementView(){
        frameVista.remove(panelContent);
        panelContent = new WikipediaManagementView(iPresentationController);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the ModificationViewWikipedia to the current panel
     */
    public void initialize_ModificationViewWikipedia(){
        frameVista.remove(panelContent);
        ModificationViewWikipedia modificationViewWikipedia = new ModificationViewWikipedia(iPresentationController);
        panelContent = modificationViewWikipedia.getPanel();
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the AdvancedConsultViewWikipedia to the current panel
     */
    public void initialize_AdvancedConsultViewWikipedia(){
        frameVista.remove(panelContent);
        AdvancedConsultViewWikipedia advancedConsultViewWikipedia = new AdvancedConsultViewWikipedia(iPresentationController);
        panelContent = advancedConsultViewWikipedia.getPanel();
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the ConsultViewWikipedia to the current panel
     */
    public void initialize_ConsultViewWikipedia(){
        frameVista.remove(panelContent);
        panelContent = new ConsultViewWikipedia(iPresentationController);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the ConsultSolutionView to the current panel
     */
    public void initialize_ConsultSolutionView() {
        frameVista.remove(panelContent);
        panelContent = new ConsultSolutionView(iPresentationController);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the SearchPanel to the current panel
     */
    public void initialize_SearchPanel() {
        frameVista.remove(panelContent);
        panelContent = new SearchPanel(iPresentationController);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the ModifySolutionView to the current panel
     */
    public void initialize_ModifySolutionView(String id) {
        frameVista.remove(panelContent);
        ModifySolutionView modifySolutionView = new ModifySolutionView(iPresentationController, id);
        modifySolutionView.setSelectedSolutionId(id);
        panelContent = modifySolutionView;
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the FileViewWikipedia to the current panel
     */
    public void initialize_FileViewWikipedia(String strTitul, String strText){
        frameVista.remove(panelContent);
        panelContent = new FileViewWikipedia(iPresentationController,strTitul,strText);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }

    /**
     * Method used to set the DialogViewWikipedia1Op to the current panel
     */
    public void initialize_DialogViewWikipedia1Op(String strTitul, String strText) {
        frameVista.disable();
        DialogViewWikipedia1Op dialogViewWikipedia1Op = new DialogViewWikipedia1Op(iPresentationController);
        dialogViewWikipedia1Op.setDialog(strTitul, strText);
    }

    /**
     * Method used to set the DialogViewWikipedia2Op to the current panel
     */
    public void initialize_DialogViewWikipedia2Op(String strTitul, String strText1, String strText2) {
        frameVista.disable();
        DialogViewWikipedia2Op dialogViewWikipedia2Op = new DialogViewWikipedia2Op(iPresentationController);
        dialogViewWikipedia2Op.setDialog(strTitul, strText1, strText2);
    }

    /**
     * Method used to set the StatisticsView to the current panel
     */
    public void initialize_StatisticsView() {
        frameVista.remove(panelContent);
        panelContent = new StatisticsView(iPresentationController);
        frameVista.add(panelContent);
        frameVista.pack();
        frameVista.repaint();
    }
}
