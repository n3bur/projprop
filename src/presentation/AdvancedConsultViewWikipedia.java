package presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Consult class for advanced searches in Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class AdvancedConsultViewWikipedia {

    /** Presentation controller */
    private PresentationController iPresentationController;

    private JPanel panelContent;
    private JButton buttonObtainAllCategories;
    private JButton buttonFindCategory;
    private JButton buttonObtainAllPagesInWikipedia;
    private JButton buttonObtainAllPagesInCategory;
    private JButton buttonFindPageInWikipedia;
    private JButton buttonFindPageInCategory;
    private JButton buttonObtainAllSubsInWikipedia;
    private JButton buttonObtainAllSubsInCategory;
    private JButton buttonFindSubInWikipedia;
    private JButton buttonFindSubInCategory;
    private JButton buttonObtainAllSupersInWikipedia;
    private JButton buttonObtainAllSupersInCategory;
    private JButton buttonFindSuperInWikipedia;
    private JButton buttonFindSuperInCategory;
    private JButton buttonSortir;

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public AdvancedConsultViewWikipedia(PresentationController pPresentationController) {
        // Crea i visualitza el dialeg
        iPresentationController = pPresentationController;
        initializeComponents();

    }

    /**
     * @return return the panel of this view
     */
    public JPanel getPanel(){
        return panelContent;
    }

    /**
     * @param event Event that triggered Obtenir totes les categories en la Wikipedia button
     */
    public void actionPerformed_buttonObtainAllCategories(ActionEvent event) {
        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
        vistaConsulta.setDialogCategory(iPresentationController.callWikiDomainObtainSortedSetCategories(
                iPresentationController.callWikiDomainObtainCategories()), "Totes les categories en la Wikipedia");
    }

    /**
     * @param event Event that triggered Buscar una categoria en la Wikipedia button
     */
    public void actionPerformed_buttonFindCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Busca una categoria en la Wikipedia",
                "Introdueix el ID de la categoria a buscar: ");
    }

    /**
     * @param event Event that triggered Obtenir totes les pagines en la Wikipedia button
     */
    public void actionPerformed_buttonObtainAllPagesInWikipedia(ActionEvent event) {
        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
        vistaConsulta.setDialogPage(iPresentationController.callWikiDomainObtainSortedSetPages(
                iPresentationController.callWikiDomainObtainPagesInWiki()), "Totes les pagines en la Wikipedia");
    }

    /**
     * @param event Event that triggered Obtenir totes les pagines en una Categoria button
     */
    public void actionPerformed_buttonObtainAllPagesInCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Obte totes les pagines en una categoria",
                "Introdueix el ID de la categoria: ");
    }

    /**
     * @param event Event that triggered Buscar una pagina en la Wikipedia button
     */
    public void actionPerformed_buttonFindPageInWikipedia(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Busca una pagina en la Wikipedia",
                "Introdueix el ID de la pagina a buscar: ");
    }

    /**
     * @param event Event that triggered Buscar una pagina en una Categoria button
     */
    public void actionPerformed_buttonFindPageInCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia2Op("Busca una pagina en una categoria",
                "Introdueix el ID de la categoria en la que vols buscar-la: ",
                "Introdueix el ID de la pagina a buscar: ");
    }

    /**
     * @param event Event that triggered Obtenir totes les subcategories en la Wikipedia button
     */
    public void actionPerformed_buttonObtainAllSubsInWikipedia(ActionEvent event) {
        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
        vistaConsulta.setDialogCategory(iPresentationController.callWikiDomainObtainSortedSetCategories(
                        iPresentationController.callWikiDomainObtainSubCategoriesInWiki()),
                "Totes les subcategories en la Wikipedia");
    }

    /**
     * @param event Event that triggered Obtenir totes les subcategories en una Categoria button
     */
    public void actionPerformed_buttonObtainAllSubsInCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Obte totes les subcategories en una categoria",
                "Introdueix el ID de la categoria: ");
    }

    /**
     * @param event Event that triggered Buscar una subcategoria en la Wikipedia button
     */
    public void actionPerformed_buttonFindSubInWikipedia(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Busca una subcategoria en la Wikipedia",
                "Introdueix el ID de la subcategoria a buscar: ");
    }

    /**
     * @param event Event that triggered Buscar una subcategoria en una Categoria button
     */
    public void actionPerformed_buttonFindSubInCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia2Op("Busca una subcategoria en una categoria",
                "Introdueix el ID de la categoria en la que vols buscar-la: ",
                "Introdueix el ID de la subcategoria a buscar: ");
    }

    /**
     * @param event Event that triggered Obtenir totes les supercategories en la Wikipedia button
     */
    public void actionPerformed_buttonObtainAllSupersInWikipedia(ActionEvent event) {
        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
        vistaConsulta.setDialogCategory(iPresentationController.callWikiDomainObtainSortedSetCategories(
                        iPresentationController.callWikiDomainObtainSuperCategoriesInWiki()),
                "Totes les supercategories en la Wikipedia");
    }

    /**
     * @param event Event that triggered Obtenir totes les supercategories en una Categoria button
     */
    public void actionPerformed_buttonObtainAllSupersInCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Obte totes les supercategories en una categoria",
                "Introdueix el ID de la categoria: ");
    }

    /**
     * @param event Event that triggered Buscar una supercategoria en la Wikipedia button
     */
    public void actionPerformed_buttonFindSuperInWikipedia(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia1Op("Busca una supercategoria en la Wikipedia",
                "Introdueix el ID de la supercategoria a buscar: ");
    }

    /**
     * @param event Event that triggered Buscar una supercategoria en una Categoria button
     */
    public void actionPerformed_buttonFindSuperInCategory(ActionEvent event) {
        iPresentationController.synToDialogViewWikipedia2Op("Busca una supercategoria en una categoria",
                "Introdueix el ID de la categoria en la que vols buscar-la: ",
                "Introdueix el ID de la supercategoria a buscar: ");
    }

    /**
     * @param event Event that triggered Tornar button
     */
    public void actionPerformed_buttonSortir(ActionEvent event) {
        iPresentationController.synToWikipediaManagementView();
    }

    /**
     * Method to initialize the components of the view
     */
    private void initializeComponents() {
        assignListeners();
    }

    /**
     * Method to assign Listeners to the components
     */
    private void assignListeners() {

        buttonObtainAllCategories.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllCategories(event);
                    }
                });

        buttonFindCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindCategory(event);
                    }
                });

        buttonObtainAllPagesInWikipedia.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllPagesInWikipedia(event);
                    }
                });

        buttonObtainAllPagesInCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllPagesInCategory(event);
                    }
                });

        buttonFindPageInWikipedia.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindPageInWikipedia(event);
                    }
                });

        buttonFindPageInCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindPageInCategory(event);
                    }
                });

        buttonObtainAllSubsInWikipedia.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllSubsInWikipedia(event);
                    }
                });

        buttonObtainAllSubsInCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllSubsInCategory(event);
                    }
                });

        buttonFindSubInWikipedia.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindSubInWikipedia(event);
                    }
                });

        buttonFindSubInCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindSubInCategory(event);
                    }
                });

        buttonObtainAllSupersInWikipedia.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllSupersInWikipedia(event);
                    }
                });

        buttonObtainAllSupersInCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonObtainAllSupersInCategory(event);
                    }
                });

        buttonFindSuperInWikipedia.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindSuperInWikipedia(event);
                    }
                });

        buttonFindSuperInCategory.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonFindSuperInCategory(event);
                    }
                });

        buttonSortir.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonSortir(event);
                    }
                });
    }
}
