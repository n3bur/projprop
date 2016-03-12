package presentation;

import domini.WikipediaException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Modification class to modify manually the Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class ModificationViewWikipedia {

    /** Presentation controller */
    private PresentationController iPresentationController;

    private JPanel panelContent;
    private JPanel panelModification;
    private JPanel panelOriginNode;
    private JPanel panelDestinyNode;
    private JLabel labelPanelModification;
    private JLabel labelPanelOriginNode;
    private JLabel labelPanelDestinyNode;
    private JPanel panelButtons;
    private JButton buttonAcceptar;
    private JButton buttonSortir;
    private JComboBox combobox;
    private JTextField fieldOriginNode;
    private JTextField fieldDestinyNode;
    private JLabel labelTitle;

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public ModificationViewWikipedia(PresentationController pPresentationController) {
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
     * @param event Event that triggered combobox
     */
    public void actionPerformed_selectionCombobox (ActionEvent event){
        if (!combobox.getModel().getSelectedItem().equals("addC") &&
                !combobox.getModel().getSelectedItem().equals("removeC")){
            fieldDestinyNode.setEditable(true);
        }
        else {
            fieldDestinyNode.setText("");
            fieldDestinyNode.setEditable(false);
        }
    }

    /**
     * @param event Event that triggered Acceptar button
     */
    public void actionPerformed_buttonAcceptar (ActionEvent event){
        String idO = fieldOriginNode.getText();
        String typeM = (String) combobox.getModel().getSelectedItem();
        String idD = fieldDestinyNode.getText();

        if (idO.equals("pizza")){
            NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
            vistaSuccess.setSuccess("Modificacio en la Wikipedia", "Has afegit una pizza correctament");
        }

        if (!idO.isEmpty()) {
            if (!idO.contains(" ")) {
                if (!typeM.equals("addC") && !typeM.equals("removeC")) {
                    if (!idD.isEmpty()) {
                        if (!idD.contains(" ")) {
                            iPresentationController.callWikiDomainModifyManually(idO, typeM, idD);
                            NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                            vistaSuccess.setSuccess("Modificacio en la Wikipedia", "La modificacio s'ha realitzat correctament");
                        }
                        else {
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Modifica Wikipedia", "El node desti no pot tenir espais");
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error Modifica Wikipedia", "Afegeix un ID per al node desti");
                    }
                } else {
                    iPresentationController.callWikiDomainModifyManually(idO, typeM, idD);
                    NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                    vistaSuccess.setSuccess("Modificacio en la Wikipedia", "La modificacio s'ha realitzat correctament");
                }
            }
            else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error Modifica Wikipedia", "El node origen no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error Modifica Wikipedia", "Afegeix un ID per al node origen");
        }
    }

    /**
     * @param event Event that triggered Sortir button
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

        buttonAcceptar.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        try {
                            actionPerformed_buttonAcceptar(event);
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    }
                });

        buttonSortir.addActionListener
                (new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        actionPerformed_buttonSortir(event);
                    }
                });

        combobox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                actionPerformed_selectionCombobox(event);
            }
        });
    }
}
