package presentation;

import domini.SolutionDomainController;
import persistencia.SolutionDataController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * Class that controls the view of the consultation of a solution
 * @author Rubén
 */
public class ConsultSolutionView extends javax.swing.JPanel {


    private PresentationController iPresentationController;

    private boolean reading;

    /**
     * Creates new form NewJPanel
     */
    public ConsultSolutionView() {
        initComponents();
    }

    public ConsultSolutionView(PresentationController pPresentationController) {
        initComponents();
        iPresentationController = pPresentationController;
    }

    /**
     * Initlialize view components
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        titleLabel = new javax.swing.JLabel();
        algUsedLabel = new javax.swing.JLabel();
        solutionIdLabel = new javax.swing.JLabel();
        comNumberLabel = new javax.swing.JLabel();
        nodeNumberLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        veureGrafButton = new javax.swing.JButton();
        eliminarSolucioButton = new javax.swing.JButton();
        tornarButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        canviNomButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        solutionIdsList = new javax.swing.JList();
        importSolutionBtn = new javax.swing.JButton();
        modificarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        comList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        catList = new javax.swing.JList();
        comLabel = new javax.swing.JLabel();
        catLabel = new javax.swing.JLabel();

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        titleLabel.setText("Gestió de Solucions");

        algUsedLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        algUsedLabel.setText("Alg. Utilitzat: ");

        solutionIdLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        solutionIdLabel.setText("Nom: ");

        comNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comNumberLabel.setText("Nº de comunitats: ");

        nodeNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nodeNumberLabel.setText("Nº de nodes: ");

        timeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timeLabel.setText("Temps emprat (s):");

        veureGrafButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        veureGrafButton.setText("Veure Graf");
        veureGrafButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veureGrafButtonActionPerformed(evt);
            }
        });

        eliminarSolucioButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        eliminarSolucioButton.setText("Eliminar");
        eliminarSolucioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarSolucioButtonActionPerformed(evt);
            }
        });

        tornarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tornarButton.setText("Tornar");
        tornarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tornarButtonActionPerformed(evt);
            }
        });

        final DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        df.setMinimumFractionDigits(4);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        canviNomButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        canviNomButton.setText("Canviar Nom");
        canviNomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                canviNomButtonActionPerformed(evt);
            }
        });

        solutionIdsList.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        DefaultListModel model = new DefaultListModel();
        ArrayList<String> sIds = SolutionDomainController.getSolutionIds();
        for (String s : sIds) model.addElement(s);
        solutionIdsList.setModel(model);
        jScrollPane2.setViewportView(solutionIdsList);

        // TODO codi pel listener

      ListSelectionListener listSelectionListener = new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent listSelectionEvent) {
          if (!reading) {
              String selectedId = solutionIdsList.getSelectedValue().toString();
              SolutionDomainController.setSolution(SolutionDomainController.loadSolution(selectedId));
              String alg = new String();
              char a = SolutionDomainController.getSolutionAlg();
              if (a == 'L') alg = "Louvain";
              else if (a == 'G') alg = "Girvan-Newman";
              else alg = "Clique";
              algUsedLabel.setText("Alg. Utilitzat:  " + alg);
              solutionIdLabel.setText("Nom:  " + selectedId);
              comNumberLabel.setText("Nº de comunitats:  " + SolutionDomainController.getNumSolutionCommunities());
              nodeNumberLabel.setText("Nº de nodes:  " + SolutionDomainController.getNumNodesSolution());
              timeLabel.setText("Temps emprat (s):  " + df.format(SolutionDomainController.getSolutionTime() / 1E9));
              DefaultListModel model2 = new DefaultListModel();
              for (int i = 0; i < SolutionDomainController.getNumSolutionCommunities(); ++i)
                  model2.addElement("Comunitat " + i);
              comList.setModel(model2);

              //clear catList
              DefaultListModel model3 = new DefaultListModel();
              catList.setModel(model3);
          }
      }
    };
    solutionIdsList.addListSelectionListener(listSelectionListener);

        importSolutionBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        importSolutionBtn.setText("Importar Solucio");
        importSolutionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importSolutionBtnActionPerformed(evt);
            }
        });

        modificarBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        modificarBtn.setText("Modificar");
        modificarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(comList);
        final ListSelectionListener comListListener = new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                try {
                    String id = solutionIdsList.getSelectedValue().toString();
                    String com = comList.getSelectedValue().toString();
                    SolutionDomainController.setSolution(SolutionDomainController.loadSolution(id));
                    int selected = Integer.valueOf(comList.getSelectedValue().toString().substring(10));
                    DefaultListModel model2 = new DefaultListModel();
                    for (String s : SolutionDomainController.getSolutionNameCommunities().get(selected)) model2.addElement(s);
                    //for (Node n : SolutionDomainController.getSolutionCommunities().get(selected).getCommunity()) model2.addElement(n.getId());
                    catList.setModel(model2);
                } catch (Exception e) {

                }
            }
        };

        comList.addListSelectionListener(comListListener);

        jScrollPane3.setViewportView(catList);

        comLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comLabel.setText("Comunitats");

        catLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        catLabel.setText("Categories ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(31, 31, 31)
                                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tornarButton)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(importSolutionBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(modificarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(comNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                                                                                .addComponent(algUsedLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(nodeNumberLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                                                                                                .addComponent(solutionIdLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                                        .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                                .addComponent(eliminarSolucioButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(veureGrafButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                                .addComponent(canviNomButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(67, 67, 67)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(comLabel))
                                                                .addGap(124, 124, 124)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(catLabel)
                                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addContainerGap(176, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                                .addComponent(solutionIdLabel)
                                                                                .addGap(23, 23, 23)
                                                                                .addComponent(algUsedLabel)
                                                                                .addGap(23, 23, 23)
                                                                                .addComponent(comNumberLabel)
                                                                                .addGap(23, 23, 23)
                                                                                .addComponent(nodeNumberLabel)
                                                                                .addGap(23, 23, 23)
                                                                                .addComponent(timeLabel)
                                                                                .addGap(28, 28, 28)
                                                                                .addComponent(canviNomButton)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(veureGrafButton)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(eliminarSolucioButton)))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(modificarBtn))
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addComponent(importSolutionBtn))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(comLabel)
                                                        .addComponent(catLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 479, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(63, 63, 63)
                                .addComponent(tornarButton)
                                .addGap(40, 40, 40))
        );
    }// </editor-fold>

    /**
     *
     * @param evt Event that triggered modificarBtn Button
     */
    private void modificarBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            iPresentationController.synToModifySolutionView(solutionIdsList.getSelectedValue().toString());
        } catch (Exception e) {

        }
    }

    /**
     *
     * @param evt Event that triggered veureGraf Button
     */
    private void veureGrafButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        try {
            String id = solutionIdsList.getSelectedValue().toString();
            SolutionPanel.showWindow(id);
        } catch (Exception e) {

        }
    }

    /**
     *
     * @param evt Event that triggered tornarButton
     */
    private void tornarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        iPresentationController.synToMainView();
    }

    /**
     *
     * @param evt Event that triggered importSolutionBtn
     */
    private void importSolutionBtnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String path = new String();
            JFileChooser chooser = new JFileChooser();
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                path = chooser.getSelectedFile().getAbsolutePath();
                if (!chooser.getSelectedFile().getName().startsWith("S_")) JOptionPane.showMessageDialog(null, "Aquest fitxer no es una solucio", "Error", JOptionPane.ERROR_MESSAGE);
                else {
                    SolutionDomainController.newSolution();
                    SolutionDomainController.setSolution(SolutionDomainController.unformatSolution(SolutionDomainController.getSolutionFromFile(path)));
                    String nom = chooser.getSelectedFile().getName().toString().substring(4);
                    boolean close = false;
                    if (SolutionDomainController.getSolutionIds().contains(nom)) {
                        JOptionPane.showMessageDialog(null, "Aquest nom ja esta en us", "Error", JOptionPane.WARNING_MESSAGE);
                        boolean ok = false;
                        String inputValue = new String();
                        while (!ok && !close) {
                            inputValue = JOptionPane.showInputDialog("Introduiu el nou nom: ");
                            if (inputValue == null) close = true;
                            else {
                                if (inputValue.equals(null) || inputValue.isEmpty() || inputValue.contains("/") || inputValue.contains("'") || inputValue.contains(".") || inputValue.contains(",") || inputValue.contains(";") || inputValue.contains("-") || inputValue.contains(" "))
                                    JOptionPane.showMessageDialog(null, "El nom unicament pot tenir caracters alfanumerics", "Error", JOptionPane.WARNING_MESSAGE);
                                else if (SolutionDomainController.getSolutionIds().contains(inputValue))
                                    JOptionPane.showMessageDialog(null, "Aquest nom ja esta en us", "Error", JOptionPane.WARNING_MESSAGE);
                                else {
                                    ok = true;
                                    nom = inputValue;
                                }
                            }
                        }
                        SolutionDomainController.setSolutionId(nom);
                    }
                    //tenim la solucio amb el nom correcte
                    if (!close) SolutionDomainController.saveSolution(SolutionDomainController.getSolution());
                }
                //update view
                reading = true;
                DefaultListModel model = new DefaultListModel();
                ArrayList<String> sIds = SolutionDomainController.getSolutionIds();
                for (String s : sIds) model.addElement(s);
                solutionIdsList.setModel(model);
                reading = false;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al importar la solucio", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     *
     * @param evt Event that triggered eliminarSolucioBtn
     */
    private void eliminarSolucioButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String selectedId = solutionIdsList.getSelectedValue().toString();
            SolutionDomainController.deleteSolution(selectedId);
            DefaultListModel model = new DefaultListModel();
            ArrayList<String> sIds = SolutionDomainController.getSolutionIds();
            for (String s : sIds) model.addElement(s);
            DefaultListModel model1 = new DefaultListModel();
            DefaultListModel model2 = new DefaultListModel();
            catList.setModel(model1); comList.setModel(model2);
            algUsedLabel.setText("Alg. Utilitzat: ");
            solutionIdLabel.setText("Nom: ");
            comNumberLabel.setText("Nº de comunitats: ");
            nodeNumberLabel.setText("Nº de nodes: ");
            timeLabel.setText("Temps emprat (s):");
            solutionIdsList.setModel(model);


        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Cal seleccionar una solucio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     *
     * @param evt Event that triggered canviNomButton
     */
    private void canviNomButtonActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            String selectedId = solutionIdsList.getSelectedValue().toString();
            /*
            FINESTRETA PER INTRODUIR NOM
            COMPROVAR QUE NOM NO TE CARACTERS ILEGALS, error altrament
            COMPROVAR QUE NOM ESTA DISPONIBLE, error altrament
            SI NO ERROR; CANVIAR NOM
            VOILA
             */
            boolean ok = false;
            String inputValue = new String();
            while (!ok) {
                inputValue = JOptionPane.showInputDialog("Introduiu el nou nom: ");
                if (inputValue.isEmpty() || inputValue.contains("/") || inputValue.contains("'") || inputValue.contains(".") || inputValue.contains(",") || inputValue.contains(";") || inputValue.contains("-") || inputValue.contains(" "))
                    JOptionPane.showMessageDialog(null, "El nom unicament pot tenir caracters alfanumerics", "Error", JOptionPane.WARNING_MESSAGE);
                else if (inputValue.equals(null) || SolutionDomainController.getSolutionIds().contains(inputValue))
                    JOptionPane.showMessageDialog(null, "Aquest nom ja esta en us", "Error", JOptionPane.WARNING_MESSAGE);
                else ok = true;
            }
            if (ok) SolutionDomainController.renameSolution(selectedId, inputValue);
            //update Jlist
            DefaultListModel model = new DefaultListModel();
            ArrayList<String> sIds = SolutionDomainController.getSolutionIds();
            for (String s : sIds) model.addElement(s);
            solutionIdsList.setModel(model);
        }
        catch (Exception e) {
            //JOptionPane.showMessageDialog(null, "Cal seleccionar una solucio", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Variables declaration - do not modify
    private javax.swing.JLabel algUsedLabel;
    private javax.swing.JButton canviNomButton;
    private javax.swing.JLabel comNumberLabel;
    private javax.swing.JButton eliminarSolucioButton;
    private javax.swing.JButton importSolutionBtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel nodeNumberLabel;
    private javax.swing.JLabel solutionIdLabel;
    private javax.swing.JList solutionIdsList;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton tornarButton;
    private javax.swing.JButton veureGrafButton;
    private javax.swing.JButton modificarBtn;
    private javax.swing.JLabel catLabel;
    private javax.swing.JList catList;
    private javax.swing.JLabel comLabel;
    private javax.swing.JList comList;
    // End of variables declaration

}
