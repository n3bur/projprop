package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog class to the options that need to scan one parameter:
 *  - Crear nova Wikipedia en blanc
 *  - Canvia ID a la Wikipedia
 *  - Busca una categoria en la Wikipedia
 *  - Obte totes les pagines en una categoria
 *  - Busca una pàgina en la Wikipedia
 *  - Obte totes les subcategories en una categoria
 *  - Busca una subcategoria en la Wikipedia
 *  - Obte totes les supercategories en una categoria
 *  - Busca una supercategoria en la Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class DialogViewWikipedia1Op {

    /** Presentation controller */
    private PresentationController iPresentationController;

    private JFrame frameView = new JFrame();
    private JPanel panelContent = new JPanel();
    private JPanel panelWriteText = new JPanel();
    private JLabel labelPanelWriteText = new JLabel();
    private JPanel panelButtons = new JPanel();
    private JButton buttonAcceptar = new JButton("Acceptar");
    private JButton buttonSortir = new JButton("Cancelar");
    private JTextField fieldWriteText = new JTextField(10);

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public DialogViewWikipedia1Op(PresentationController pPresentationController) {
        iPresentationController = pPresentationController;
    }

    /**
     * Method used to make visible the dialog when it is opened
     */
    public void makeVisible() {
        frameView.pack();
        frameView.setVisible(true);
    }

    /**
     * Method used to make invisible the dialog when it is closed
     */
    public void makeInvisible() {
        frameView.setVisible(false);
    }

    /**
     * @param event Event that triggered Acceptar button
     */
    public void actionPerformed_buttonAcceptar (ActionEvent event){
        String s = fieldWriteText.getText();
        switch (frameView.getTitle()) {
            case ("Crear nova Wikipedia en blanc"):
                if (!s.isEmpty()) {
                    if (!iPresentationController.getFirst()) {
                        iPresentationController.callWikiDomainCreate(s);
                        iPresentationController.enableFirstView();
                        makeInvisible();
                    } else {
                        AdviceViewWikipedia adviceViewWikipedia = new AdviceViewWikipedia(iPresentationController);
                        adviceViewWikipedia.setDialog("Wikipedia ja existent",
                                "Estas segur que vols crear una nova Wikipedia?", s, null);
                        iPresentationController.enableFirstView();
                        makeInvisible();
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error Crea Wikipedia", "Afegeix un ID per a la Wikipedia");
                }
                break;
            case ("Canvia ID a la Wikipedia"):
                if (!s.isEmpty()) {
                    iPresentationController.callWikiDomainSetId(s);
                    iPresentationController.enableFirstView();
                    makeInvisible();
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error canvia ID a la Wikipedia", "Afegeix un ID");
                }
                break;

            /**Consultes avançades*/

            case ("Busca una categoria en la Wikipedia"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainFindCategory(s)){
                        NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                        vistaSuccess.setSuccess("Categoria en la Wikipedia", "La categoria amb ID: " + s + " hi es");
                    }
                    else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Categoria en la Wikipedia", "La categoria amb ID: "+s+" no hi es");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error buscar categoria", "Afegeix un ID");
                }
                break;
            case ("Obte totes les pagines en una categoria"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainObtainPagesInCategory(s) != null){
                        iPresentationController.enableFirstView();
                        makeInvisible();
                        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
                        vistaConsulta.setDialogPage(iPresentationController.callWikiDomainObtainSortedSetPages(
                                        iPresentationController.callWikiDomainObtainPagesInCategory(s)),
                                "Totes les pagines en la categoria amb ID: " + s);
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error obtenir pagines en categoria", "No existeix la categoria amb ID: "+s);
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error obtenir pagines en categoria", "Afegeix un ID");
                }
                break;
            case ("Busca una pàgina en la Wikipedia"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainFindPageInWiki(s)){
                        NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                        vistaSuccess.setSuccess("Pàgina en la Wikipedia", "La pàgina amb ID: " + s + " hi es");
                    }
                    else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Pàgina en la Wikipedia", "La pàgina amb ID: "+s+" no hi es");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error buscar pàgina", "Afegeix un ID");
                }
                break;
            case("Obte totes les subcategories en una categoria"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainObtainSubCategoriesInCategory(s) != null){
                        iPresentationController.enableFirstView();
                        makeInvisible();
                        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
                        vistaConsulta.setDialogCategory(iPresentationController.callWikiDomainObtainSortedSetCategories(
                                        iPresentationController.callWikiDomainObtainSubCategoriesInCategory(s)),
                                "Totes les subcategories en la categoria amb ID: " + s);
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error obtenir subcategories en categoria", "No existeix la categoria amb ID: "+s);
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error obtenir subcategories en categoria", "Afegeix un ID");
                }
                break;
            case("Busca una subcategoria en la Wikipedia"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainFindSubCategoryInWiki(s)){
                        NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                        vistaSuccess.setSuccess("Subcategoria en la Wikipedia", "La subcategoria amb ID: " + s + " hi es");
                    }
                    else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Subcategoria en la Wikipedia", "La subcategoria amb ID: "+s+" no hi es");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error buscar subcategoria", "Afegeix un ID");
                }
                break;
            case("Obte totes les supercategories en una categoria"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainObtainSuperCategoriesInCategory(s) != null){
                        iPresentationController.enableFirstView();
                        makeInvisible();
                        ConsultViewWikipediaForAdvancedOptions vistaConsulta = new ConsultViewWikipediaForAdvancedOptions();
                        vistaConsulta.setDialogCategory(iPresentationController.callWikiDomainObtainSortedSetCategories(
                                iPresentationController.callWikiDomainObtainSuperCategoriesInCategory(s)),
                                "Totes les supercategories en la categoria amb ID: " + s);
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error obtenir supercategories en categoria", "No existeix la categoria amb ID: "+s);
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error obtenir supercategories en categoria", "Afegeix un ID");
                }
                break;
            case("Busca una supercategoria en la Wikipedia"):
                if (!s.isEmpty()) {
                    if (iPresentationController.callWikiDomainFindSuperCategoryInWiki(s)){
                        NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                        vistaSuccess.setSuccess("Supercategoria en la Wikipedia", "La supercategoria amb ID: " + s + " hi es");
                    }
                    else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Supercategoria en la Wikipedia", "La supercategoria amb ID: "+s+" no hi es");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error buscar supercategoria", "Afegeix un ID");
                }
                break;
        }
    }

    /**
     * @param event Event that triggered Sortir button
     */
    public void actionPerformed_buttonSortir(ActionEvent event) {
        iPresentationController.enableFirstView();
        makeInvisible();
    }


    /**
     * Method to personalize the dialog
     * @param strTitul Title to the dialog
     * @param strText Text to the dialog
     */
    public void setDialog
            (String strTitul, String strText) {

        // Crea i visualitza el dialeg
        initializeComponents(strTitul, strText);
        makeVisible();
    }

    /**
     * Method to initialize the components of the dialog
     * @param strTitul Title to the dialog
     * @param strText Text to the dialog
     */
    private void initializeComponents(String strTitul, String strText) {
        initializeFrameView(strTitul);
        initializePanelContent();
        initializePanelOriginNode(strText);
        initializePanelButtons();
        frameView.add(panelContent);
        assignListeners();
    }

    /**
     * Method to initialize the frame of the dialog
     * @param strTitul Title to the dialog
     */
    private void initializeFrameView(String strTitul) {
        frameView.setMinimumSize(new Dimension(590, 160));
        frameView.setPreferredSize(frameView.getMinimumSize());
        frameView.setLocationRelativeTo(null);
        frameView.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        frameView.setTitle(strTitul);
    }

    /**
     * Method to initialize the content panel of the dialog
     */
    private void initializePanelContent() {
        // Layout
        panelContent.setLayout(new BorderLayout());
        // Panels
        panelContent.add(panelWriteText, BorderLayout.CENTER);
        panelContent.add(panelButtons,BorderLayout.SOUTH);
    }

    /**
     * Method to initialize the origin node panel of the dialog
     */
    private void initializePanelOriginNode(String strText) {
        // Layout
        labelPanelWriteText.setFont(new java.awt.Font("Tahoma", 0, 24));
        labelPanelWriteText.setText(strText);
        panelWriteText.setLayout(new FlowLayout());
        fieldWriteText.setFont(new java.awt.Font("Tahoma", 0, 24));
        panelWriteText.add(labelPanelWriteText);
        panelWriteText.add(fieldWriteText);
    }

    /**
     * Method to initialize the button panel of the dialog
     */
    private void initializePanelButtons() {
        // Layout
        panelButtons.setLayout(new FlowLayout());
        // Botons
        panelButtons.add(buttonAcceptar);
        panelButtons.add(buttonSortir);
    }

    /**
     * Method to assign Listeners to the components
     */
    private void assignListeners() {

        // Listeners per als botons

        buttonAcceptar.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        String text = ((JButton) event.getSource()).getText();
                        actionPerformed_buttonAcceptar(event);
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