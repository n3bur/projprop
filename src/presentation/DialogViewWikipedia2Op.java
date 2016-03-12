package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog class to the options that need to scan two parameters:
 *  - Busca una pàgina en una categoria
 *  - Busca una subcategoria en una categoria
 *  - Busca una supercategoria en una categoria
 * @author Aleix Pellisa Cortiella
 */

public class DialogViewWikipedia2Op {

    /** Presentation controller */
    private PresentationController iPresentationController;

    private JFrame frameView = new JFrame();
    private JPanel panelContent = new JPanel();
    private JPanel panelWriteText = new JPanel();
    private JLabel labelPanelWriteText1 = new JLabel();
    private JLabel labelPanelWriteText2 = new JLabel();
    private JPanel panelButtons = new JPanel();
    private JButton buttonAcceptar = new JButton("Acceptar");
    private JButton buttonSortir = new JButton("Cancelar");
    private JTextField fieldWriteText1 = new JTextField(10);
    private JTextField fieldWriteText2 = new JTextField(10);

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public DialogViewWikipedia2Op(PresentationController pPresentationController) {
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
        String s1 = fieldWriteText1.getText();
        String s2 = fieldWriteText2.getText();
        switch (frameView.getTitle()) {
            case ("Busca una pàgina en una categoria"):
                if (!s1.isEmpty()) {
                    if (!s2.isEmpty()){
                        if (iPresentationController.callWikiDomainFindPageInCategory(s1,s2)){
                            NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                            vistaSuccess.setSuccess("Busca pàgina en categoria", "La pàgina amb ID: " + s2 + " hi es en la categoria " +s1);
                        } else {
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Busca pàgina en categoria", "La pàgina amb ID: " + s2 + " no hi es en la categoria " +s1);
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error busca pàgina en categoria", "Afegeix un ID per a la pàgina");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error Busca pàgina en categoria", "Afegeix un ID per a la categoria");
                }
                break;
            case ("Busca una subcategoria en una categoria"):
                if (!s1.isEmpty()) {
                    if (!s2.isEmpty()){
                        if (iPresentationController.callWikiDomainFindSubCategoryInCategory(s1, s2)){
                            NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                            vistaSuccess.setSuccess("Busca subcategoria en categoria", "La subcategoria amb ID: " + s2 + " hi es en la categoria " +s1);
                        } else {
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Busca subcategoria en categoria", "La subcategoria amb ID: " + s2 + " no hi es en la categoria " +s1);
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error busca subcategoria en categoria", "Afegeix un ID per a la subcategoria");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error Busca subcategoria en categoria", "Afegeix un ID per a la categoria");
                }
                break;
            case ("Busca una supercategoria en una categoria"):
                if (!s1.isEmpty()) {
                    if (!s2.isEmpty()){
                        if (iPresentationController.callWikiDomainFindSuperCategoryInCategory(s1, s2)){
                            NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                            vistaSuccess.setSuccess("Busca supercategoria en categoria", "La supercategoria amb ID: " + s2 + " hi es en la categoria " +s1);
                        } else {
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Busca supercategoria en categoria", "La supercategoria amb ID: " + s2 + " no hi es en la categoria " +s1);
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error busca supercategoria en categoria", "Afegeix un ID per a la supercategoria");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error Busca supercategoria en categoria", "Afegeix un ID per a la categoria");
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
     * @param strText1 First text to the dialog
     * @param strText2 Second text to the dialog
     */
    public void setDialog
            (String strTitul, String strText1, String strText2) {

        // Crea i visualitza el dialeg

        initializeComponents(strTitul, strText1, strText2);
        makeVisible();
    }

    /**
     * Method to initialize the components of the dialog
     * @param strTitul Title to the dialog
     * @param strText1 First text to the dialog
     * @param strText2 Second text to the dialog
     */
    private void initializeComponents(String strTitul, String strText1, String strText2) {
        initializeFrameView(strTitul);
        initializePanelContent();
        initializePanelOriginNode(strText1);
        initializePanelDestinyNode(strText2);
        initializePanelButtons();
        frameView.add(panelContent);
        assignListeners();
    }

    /**
     * Method to initialize the frame of the dialog
     * @param strTitul Title to the dialog
     */
    private void initializeFrameView(String strTitul) {
        frameView.setMinimumSize(new Dimension(650, 250));
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
        panelContent.add(panelWriteText,BorderLayout.CENTER);
        panelContent.add(panelButtons,BorderLayout.SOUTH);
    }

    /**
     * Method to initialize the origin node panel of the dialog
     */
    private void initializePanelOriginNode(String strText1) {
        // Layout
        labelPanelWriteText1.setFont(new java.awt.Font("Tahoma", 0, 24));
        labelPanelWriteText1.setText(strText1);
        panelWriteText.setLayout(new FlowLayout());
        fieldWriteText1.setFont(new java.awt.Font("Tahoma", 0, 24));
        panelWriteText.add(labelPanelWriteText1);
        panelWriteText.add(fieldWriteText1);
    }

    /**
     * Method to initialize the destiny node panel of the dialog
     */
    private void initializePanelDestinyNode(String strText2) {
        // Layout
        labelPanelWriteText2.setFont(new java.awt.Font("Tahoma", 0, 24));
        labelPanelWriteText2.setText(strText2);
        fieldWriteText2.setFont(new java.awt.Font("Tahoma", 0, 24));
        panelWriteText.add(labelPanelWriteText2);
        panelWriteText.add(fieldWriteText2);
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