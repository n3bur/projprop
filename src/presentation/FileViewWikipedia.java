package presentation;

import persistencia.IncorrectFormatException;

import java.io.IOException;

/**
 * File class to manage Wikipedia files
 * @author Aleix Pellisa Cortiella
 */
public class FileViewWikipedia extends javax.swing.JPanel {

    /** Presentation controller */
    private PresentationController iPresentationController;
    private String strText;

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     * @param strTitul Title to the view
     * @param strText Text to the view
     */
    public FileViewWikipedia(PresentationController pPresentationController, String strTitul, String strText) {
        iPresentationController = pPresentationController;
        this.strText = strText;
        initComponents(strTitul,strText);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    /**
     * Initialize the components of the view
     */
    private void initComponents(String strTitul, String strText) {

        jFileChooser1 = new javax.swing.JFileChooser();
        jLabel1 = new javax.swing.JLabel();
        jFileChooser1.setApproveButtonText(strText);
        jFileChooser1.setCurrentDirectory(new java.io.File("data/wikipedia"));
        jFileChooser1.setFileFilter(null);
        jFileChooser1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jFileChooser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooser1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(strTitul);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(83, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jFileChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(77, 77, 77))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGap(27, 27, 27)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                            .addComponent(jFileChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
    }// </editor-fold>

    /**
     * @param evt Event that triggered Approve button
     */
    private void jFileChooser1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getActionCommand().equals("CancelSelection")){
            iPresentationController.synToWikipediaManagementView();
        }
        if (evt.getActionCommand().equals("ApproveSelection")){
            String s = jFileChooser1.getSelectedFile().getName();
            switch(strText){
                case ("Obrir"):
                    if (!iPresentationController.getFirst()) {
                        try {
                            iPresentationController.callWikiDomainLoad(s,jFileChooser1.getSelectedFile().getAbsolutePath());
                            iPresentationController.synToWikipediaManagementView();
                        } catch (IOException name) {
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Carregar Wikipedia", "Nom de Wikipedia no existeix");
                        } catch (IncorrectFormatException name) {
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Carregar Wikipedia", "Format de la Wikipedia incorrecte");
                        }
                    } else {
                        AdviceViewWikipedia adviceViewWikipedia = new AdviceViewWikipedia(iPresentationController);
                        adviceViewWikipedia.setDialog("Wikipedia ja existent",
                                "Estas segur que vols carregar una nova Wikipedia?", s, jFileChooser1.getSelectedFile().getAbsolutePath());
                    }
                    break;
                case ("Modificar"):
                    try {
                        iPresentationController.callWikiDomainModifyWithFile(s,jFileChooser1.getSelectedFile().getAbsolutePath());
                        iPresentationController.synToWikipediaManagementView();
                    } catch (IOException name) {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error Modificar Wikipedia", "Nom de Wikipedia no existeix");
                    } catch (IncorrectFormatException name) {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error Modificar Wikipedia", "Format de la Wikipedia incorrecte");
                    }
                    break;
                case ("Guardar"):
                    if (!s.equals(iPresentationController.callWikiDomainGetId())) {
                        AdviceViewWikipedia adviceViewWikipedia = new AdviceViewWikipedia(iPresentationController);
                        adviceViewWikipedia.setDialog("Wikipedia amb diferent ID",
                                "La Wikipedia actual te l'ID: "+iPresentationController.callWikiDomainGetId()+
                                " i la intentes guardar amb ID: "+s+", vols canviar-li el ID?", s,
                                jFileChooser1.getSelectedFile().getAbsolutePath());
                    }
                    else {
                        try {
                            iPresentationController.callWikiDomainSave(jFileChooser1.getSelectedFile().getAbsolutePath());
                            NotificationViewWikipedia vistaSuccess = new NotificationViewWikipedia();
                            vistaSuccess.setSuccess("Wikipedia guardada", "La wikipedia amb ID: "
                                    + iPresentationController.callWikiDomainGetId() + " s'ha guardat correctament");
                            iPresentationController.synToWikipediaManagementView();
                        } catch (IOException ex) {
                            AdviceViewWikipedia adviceViewWikipedia = new AdviceViewWikipedia(iPresentationController);
                            adviceViewWikipedia.setDialog("Wikipedia ja guardada",
                                    "Estas segur que vols sobreescriure la Wikipedia?", iPresentationController.callWikiDomainGetId(),
                            jFileChooser1.getSelectedFile().getAbsolutePath());
                        }
                    }
                    break;
                case ("Esborrar"):
                    try {
                        iPresentationController.callWikiDomainDelete(jFileChooser1.getSelectedFile().getAbsolutePath());
                        iPresentationController.synToWikipediaManagementView();
                    } catch (IOException name) {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error Esborra Wikipedia", "No existeix cap Wikipedia amb aquest nom");
                    }
                    break;
            }
        }
    }


    // Variables declaration
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration
}
