package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Management class to manage the Wikipedia
 * @author Aleix Pellisa Cortiella
 */

public class WikipediaManagementView extends javax.swing.JPanel {

    /** Presentation controller */
    private PresentationController iPresentationController;

    private JFrame frameView = new JFrame("Gestio Wikipedia");
    private javax.swing.JButton buttonCreaWikipedia;
    private javax.swing.JButton buttonCarregaWikipedia;
    private javax.swing.JButton buttonCanviaIdWikipedia;
    private javax.swing.JButton buttonEsborraWikipedia;
    private javax.swing.JButton buttonGuardaWikipedia;
    private javax.swing.JButton buttonSortir;
    private javax.swing.JButton buttonFile;
    private javax.swing.JButton buttonManual;
    private javax.swing.JButton buttonConsultarTota;
    private javax.swing.JButton buttonConsultaAvançada;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;

    private boolean modifyOptions = false;
    private boolean consultOptions = false;

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public WikipediaManagementView(PresentationController pPresentationController) {
        iPresentationController = pPresentationController;
        initComponents();
    }

    /**
     * Method to enable the view
     */
    public void enable() {
        frameView.setEnabled(true);
    }

    /**
     * Method to disable the view
     */
    public void disable() {
        frameView.setEnabled(false);
    }

    @SuppressWarnings("unchecked")

    /**
     * Initialize the components of the view
     */
    private void initComponents() {

        labelTitle = new JLabel();
        buttonCreaWikipedia = new JButton();
        jSeparator1 = new JSeparator();
        buttonCarregaWikipedia = new JButton();
        buttonManual = new JButton();
        buttonFile = new JButton();
        jSeparator2 = new JSeparator();
        buttonConsultarTota = new JButton();
        buttonConsultaAvançada = new JButton();
        jSeparator3 = new JSeparator();
        buttonEsborraWikipedia = new JButton();
        buttonGuardaWikipedia = new JButton();
        jSeparator4 = new JSeparator();
        buttonCanviaIdWikipedia = new JButton();
        buttonSortir = new JButton();

        labelTitle.setFont(new Font("Tahoma", 1, 36)); // NOI18N
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setText("Gestió de Wikipedia");

        buttonCreaWikipedia.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonCreaWikipedia.setText("Crea una Wikipedia nova en blanc");
        buttonCreaWikipedia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonCreaWikipedia(evt);
            }
        });

        buttonCarregaWikipedia.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonCarregaWikipedia.setText("Carrega una Wikipedia des de fitxer");
        buttonCarregaWikipedia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonCarregaWikipedia(evt);
            }
        });

        buttonManual.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonManual.setText("Modificar la Wikipedia manualment");
        buttonManual.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonManual(evt);
            }
        });

        buttonFile.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonFile.setText("Modificar la Wikipedia des de fitxer");
        buttonFile.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonFile(evt);
            }
        });

        buttonConsultarTota.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonConsultarTota.setText("Consultar tota la Wikipedia");
        buttonConsultarTota.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonConsultarTota(evt);
            }
        });

        buttonConsultaAvançada.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonConsultaAvançada.setText("Consulta avançada de la wikipedia");
        buttonConsultaAvançada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonConsultaAvançada(evt);
            }
        });

        buttonEsborraWikipedia.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonEsborraWikipedia.setText("Esborrar una Wikipedia de disc");
        buttonEsborraWikipedia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonEsborraWikipedia(evt);
            }
        });

        buttonGuardaWikipedia.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonGuardaWikipedia.setText("Guarda la Wikipedia");
        buttonGuardaWikipedia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonGuardaWikipedia(evt);
            }
        });

        buttonCanviaIdWikipedia.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonCanviaIdWikipedia.setText("Canviar el ID a la Wikipedia");
        buttonCanviaIdWikipedia.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonCanviaIdWikipedia(evt);
            }
        });

        buttonSortir.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        buttonSortir.setText("Tornar");
        buttonSortir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                actionPerformed_buttonSortir(evt);
            }
        });

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(392, 392, 392)
                                                .addComponent(labelTitle))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonManual)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(buttonFile, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(jSeparator1, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonCreaWikipedia, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE)
                                                                .addGap(108, 108, 108)
                                                                .addComponent(buttonCarregaWikipedia))
                                                        .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, 928, GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonEsborraWikipedia, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(buttonGuardaWikipedia, GroupLayout.PREFERRED_SIZE, 416, GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(buttonConsultarTota, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(buttonConsultaAvançada, GroupLayout.PREFERRED_SIZE, 418, GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap(100, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(buttonCanviaIdWikipedia, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
                                                .addGap(364, 364, 364))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(buttonSortir, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
                                                .addGap(367, 367, 367))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitle)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonCreaWikipedia, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonCarregaWikipedia, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addComponent(buttonCanviaIdWikipedia, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonFile, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonManual, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator2, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonConsultarTota, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonConsultaAvançada, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator3, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonGuardaWikipedia, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonEsborraWikipedia, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jSeparator4, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonSortir, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(93, Short.MAX_VALUE))
        );
    }

    /**
     * @param event Event that triggered Crea una Wikipedia nova en blanc button
     */
    public void actionPerformed_buttonCreaWikipedia (ActionEvent event) {
        if (iPresentationController.searching()) return;
        iPresentationController.synToDialogViewWikipedia1Op("Crear nova Wikipedia en blanc", "Introdueix el ID:");
    }

    /**
     * @param event Event that triggered Carrega una Wikipedia des de fitxer button
     */
    public void actionPerformed_buttonCarregaWikipedia (ActionEvent event) {
        if (iPresentationController.searching()) return;
        iPresentationController.synToFileViewWikipedia("Carregar una Wikipedia des de fitxer", "Obrir");
    }

    /**
     * @param event Event that triggered Canviar el ID a la Wikipedia button
     */
    public void actionPerformed_buttonCanviaIdWikipedia (ActionEvent event) {
        if (iPresentationController.searching()) return;
        if (iPresentationController.getFirst()) {
            iPresentationController.synToDialogViewWikipedia1Op("Canvia ID a la Wikipedia", "Introdueix el nou ID:");
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error canvia ID a la Wikipedia", "No pots canviar el ID a una Wikipedia si no n'hi ha cap");
        }
    }

    /**
     * @param event Event that triggered Modificar la Wikipedia manualment button
     */
    public void actionPerformed_buttonManual (ActionEvent event) {
        if (iPresentationController.searching()) return;
        if (iPresentationController.getFirst()) {
            iPresentationController.synToModificationViewWikipedia();
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error Modifica Wikipedia", "No pots modificar una Wikipedia si no n'hi ha cap");
        }
    }

    /**
     * @param event Event that triggered Modificar la Wikipedia des de fitxer button
     */
    public void actionPerformed_buttonFile (ActionEvent event) {
        if (iPresentationController.searching()) return;
        if (iPresentationController.getFirst()) {
            iPresentationController.synToFileViewWikipedia("Modificar una Wikipedia des de fitxer", "Modificar");
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error Modifica Wikipedia", "No pots modificar una Wikipedia si no n'hi ha cap");
        }
    }

    /**
     * @param event Event that triggered Consultar tota la Wikipedia button
     */
    public void actionPerformed_buttonConsultarTota (ActionEvent event) {
        if (iPresentationController.getFirst()) {
            iPresentationController.synToConsultViewWikipedia();
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error Consulta Wikipedia", "No pots consultar una Wikipedia si no n'hi ha cap");
        }
    }

    /**
     * @param event Event that triggered Consulta avançada de la Wikipedia button
     */
    public void actionPerformed_buttonConsultaAvançada (ActionEvent event) {
        if (iPresentationController.getFirst()) {
            iPresentationController.synToAdvancedConsultViewWikipedia();
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error Consulta Wikipedia", "No pots consultar una Wikipedia si no n'hi ha cap");
        }
    }

    /**
     * @param event Event that triggered Esborrar una Wikipedia de disc button
     */
    public void actionPerformed_buttonEsborraWikipedia (ActionEvent event) {
        if (!iPresentationController.searching())
            iPresentationController.synToFileViewWikipedia("Esborrar una Wikipedia de disc", "Esborrar");
    }

    /**
     * @param event Event that triggered Guardar la Wikipedia button
     */
    public void actionPerformed_buttonGuardaWikipedia (ActionEvent event) {
        if (iPresentationController.getFirst()) {
            iPresentationController.synToFileViewWikipedia("Guardar la Wikipedia", "Guardar");
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error Guarda Wikipedia", "No pots guardar una Wikipedia si no n'hi ha cap");
        }
    }

    /**
     * @param event Event that triggered Tornar button
     */
    public void actionPerformed_buttonSortir(ActionEvent event) {
        iPresentationController.synToMainView();
    }

}
