package presentation;

import domini.WikipediaException;
import persistencia.IncorrectFormatException;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Advice class to confirm and cancel operations
 * @author Aleix Pellisa Cortiella
 */

public class AdviceViewWikipedia {

    /** Presentation controller */
    private PresentationController iPresentationController;

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public AdviceViewWikipedia(PresentationController pPresentationController) {
        iPresentationController = pPresentationController;
    }

    /**
     * Method to personalize the dialog
     * @param strTitul Title to the dialog
     * @param strText Text to the dialog
     * @param s Optionally used to confirm or cancel operations with String needed (like Ids)
     * @param path Optionally used to confirm or cancel operations with path needed
     */
    public void setDialog
            (String strTitul, String strText, String s, String path) {

        // Crea i visualitza el dialeg

        String[] strBotons = {"Acceptar","Cancelar"};
        JFrame frame = new JFrame();
        Font font = new Font("Lucida", Font.PLAIN, 90);
        frame.setFont(font);

        JOptionPane optionPane = new JOptionPane(strText,JOptionPane.WARNING_MESSAGE);
        optionPane.setOptions(strBotons);
        optionPane.setFont(font);
        JDialog dialogOptionPane = optionPane.createDialog(frame,strTitul);
        dialogOptionPane.setFont(font);
        dialogOptionPane.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialogOptionPane.pack();
        dialogOptionPane.setVisible(true);


        // Captura la opcio escollida
        String vsel = (String) optionPane.getValue();
        if (vsel.equals("Acceptar")) {
            switch (strTitul) {
                case ("Wikipedia ja existent"):
                    if (strText.equals("Estas segur que vols crear una nova Wikipedia?")) {
                        iPresentationController.callWikiDomainCreate(s);
                        iPresentationController.synToWikipediaManagementView();
                    }
                    else {
                        try {
                            iPresentationController.callWikiDomainLoad(s,path);
                            iPresentationController.synToWikipediaManagementView();
                        }
                        catch (WikipediaException name){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", name.getMessage());
                        }
                        catch (IOException name){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Carrega Wikipedia", "Nom de Wikipedia no existeix");
                        }
                        catch (IncorrectFormatException name){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Carrega Wikipedia", "Format de la Wikipedia incorrecte");
                        }
                    }
                    break;
                case ("Wikipedia ja guardada"):
                    try {
                        iPresentationController.callWikiDomainDelete(path);
                        iPresentationController.callWikiDomainSave(path);
                        NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                        vistaSuccess.setSuccess("Wikipedia guardada", "La wikipedia amb ID: "
                                + iPresentationController.callWikiDomainGetId() + " s'ha guardat correctament");
                    }
                    catch (Exception ex) {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error guardar Wikipedia", "La Wikipedia no s'ha pogut guardar");
                    }
                    break;
                case ("Wikipedia amb diferent ID"):
                    iPresentationController.callWikiDomainSetId(s);
                    try {
                        iPresentationController.callWikiDomainSave(path);
                        NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                        vistaSuccess.setSuccess("Wikipedia guardada", "La wikipedia amb ID: "
                                + iPresentationController.callWikiDomainGetId() + " s'ha guardat correctament");
                        iPresentationController.synToWikipediaManagementView();
                    } catch (IOException ex) {
                        AdviceViewWikipedia adviceViewWikipedia = new AdviceViewWikipedia(iPresentationController);
                        adviceViewWikipedia.setDialog("Wikipedia ja guardada",
                                "Estas segur que vols sobreescriure la Wikipedia?",
                                iPresentationController.callWikiDomainGetId(), path);
                    }
                    break;
            }
        }
    }
}
