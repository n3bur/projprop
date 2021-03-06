package presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import domini.StatisticDomainController;

import java.text.DecimalFormat;



/**
 * Class that controls the view of statistics
 * @author Rubén
 */
public class StatisticsView extends javax.swing.JPanel {

    private PresentationController iPresentationController;

    /**
     * Creates new form NewJPanel
     */
    public StatisticsView() {
        initComponents();
    }

    public StatisticsView(PresentationController pPresentationController) {
        StatisticDomainController.initialize();
        initComponents();
        iPresentationController = pPresentationController;
        ChartDrawer.setPanel(graficPanel);
        ChartDrawer.main(null);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        titleLabel = new javax.swing.JLabel();
        tornarButton = new javax.swing.JButton();
        graficPanel = new javax.swing.JPanel();
        cliqueCheck = new javax.swing.JCheckBox();
        numSolsCliqueLabel = new javax.swing.JLabel();
        comsCliqueLabel = new javax.swing.JLabel();
        NodesCliqueLabel = new javax.swing.JLabel();
        timeCliqueLabel = new javax.swing.JLabel();
        SDTimeClique = new javax.swing.JLabel();
        GNCheck = new javax.swing.JCheckBox();
        NumGNLabel = new javax.swing.JLabel();
        ComsGNLabel = new javax.swing.JLabel();
        NodesGNLabel = new javax.swing.JLabel();
        TimeGNLabel = new javax.swing.JLabel();
        SDGNLabel = new javax.swing.JLabel();
        LouvainCheck = new javax.swing.JCheckBox();
        NumLouvainLabel = new javax.swing.JLabel();
        ComsLouvainLabel = new javax.swing.JLabel();
        NodesLouvainLabel = new javax.swing.JLabel();
        TimeLouvainLabel = new javax.swing.JLabel();
        SDLouvainLabel = new javax.swing.JLabel();
        ChartDrawer.setPanel(graficPanel);
        ChartDrawer.main(null);
        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        titleLabel.setText("Estadístiques");

        tornarButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tornarButton.setText("Tornar");
        tornarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tornarButtonActionPerformed(evt);
            }
        });

        graficPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        graficPanel.setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout graficPanelLayout = new javax.swing.GroupLayout(graficPanel);
        graficPanel.setLayout(graficPanelLayout);
        graficPanelLayout.setHorizontalGroup(
                graficPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 833, Short.MAX_VALUE)
        );
        graficPanelLayout.setVerticalGroup(
                graficPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 549, Short.MAX_VALUE)
        );

        cliqueCheck.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        cliqueCheck.setText("Clique ");
        cliqueCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cliqueCheckActionPerformed(evt);
            }
        });

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        df.setMinimumFractionDigits(4);

        numSolsCliqueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        numSolsCliqueLabel.setText("Numero de Solucions:  " + StatisticDomainController.getSolsClique());

        comsCliqueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        comsCliqueLabel.setText("Comunitats per Solució:  " + StatisticDomainController.getCommsClique());

        NodesCliqueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NodesCliqueLabel.setText("Nodes per Solució:  " + StatisticDomainController.getNodesClique());

        timeCliqueLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        timeCliqueLabel.setText("Temps per Solució (s):  " + df.format(StatisticDomainController.getTimeClique()/1E9));

        SDTimeClique.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SDTimeClique.setText("SD dels temps:  " + df.format(StatisticDomainController.getSDClique()/1E9));

        GNCheck.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        GNCheck.setText("Girvan-Newman");
        GNCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GNCheckActionPerformed(evt);
            }
        });

        NumGNLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NumGNLabel.setText("Número de Solucions:  " + StatisticDomainController.getSolssGirvan());

        ComsGNLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComsGNLabel.setText("Comunitats per Solució:  " + StatisticDomainController.getCommsGirvan());

        NodesGNLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NodesGNLabel.setText("Nodes per Solució:  " + StatisticDomainController.getNodesGirvan());

        TimeGNLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TimeGNLabel.setText("Temps per Solució (s):  " + df.format(StatisticDomainController.getTimeGirvan()/1E9));

        SDGNLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SDGNLabel.setText("SD dels temps:  "+ df.format(StatisticDomainController.getSDGirvan()/1E9));

        LouvainCheck.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        LouvainCheck.setText("Louvain");
        LouvainCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LouvainCheckActionPerformed(evt);
            }
        });

        NumLouvainLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NumLouvainLabel.setText("Número de Solucions:  "+StatisticDomainController.getSolsLouvain());

        ComsLouvainLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ComsLouvainLabel.setText("Comunitats per Solució:  "+StatisticDomainController.getCommsLouvain());

        NodesLouvainLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        NodesLouvainLabel.setText("Nodes per Solució:  "+StatisticDomainController.getNodesLouvain());

        TimeLouvainLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        TimeLouvainLabel.setText("Temps per Solució (s):  "+df.format(StatisticDomainController.getTimeLouvain()/1E9));

        SDLouvainLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        SDLouvainLabel.setText("SD dels temps:  "+df.format(StatisticDomainController.getSDLouvain()/1E9));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(cliqueCheck)
                                                        .addComponent(GNCheck)
                                                        .addComponent(LouvainCheck)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(comsCliqueLabel)
                                                                        .addComponent(numSolsCliqueLabel)
                                                                        .addComponent(NodesCliqueLabel)
                                                                        .addComponent(timeCliqueLabel)
                                                                        .addComponent(SDTimeClique)
                                                                        .addComponent(NumGNLabel)
                                                                        .addComponent(ComsGNLabel)
                                                                        .addComponent(NodesGNLabel)
                                                                        .addComponent(TimeGNLabel)
                                                                        .addComponent(SDGNLabel)
                                                                        .addComponent(NumLouvainLabel)
                                                                        .addComponent(ComsLouvainLabel)
                                                                        .addComponent(NodesLouvainLabel)
                                                                        .addComponent(TimeLouvainLabel)
                                                                        .addComponent(SDLouvainLabel))))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                                .addComponent(graficPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(46, 46, 46))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tornarButton)
                                                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(cliqueCheck)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(numSolsCliqueLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(comsCliqueLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NodesCliqueLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(timeCliqueLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(SDTimeClique)
                                                .addGap(18, 18, 18)
                                                .addComponent(GNCheck)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NumGNLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ComsGNLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NodesGNLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TimeGNLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(SDGNLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(LouvainCheck)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NumLouvainLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(ComsLouvainLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(NodesLouvainLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(TimeLouvainLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(SDLouvainLabel))
                                        .addComponent(graficPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tornarButton)
                                .addContainerGap(60, Short.MAX_VALUE))
        );
    }// </editor-fold>

    /**
     *
     * @param evt Checking or unchecking the box
     */
    private void cliqueCheckActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            graficPanel.removeAll();
            ChartDrawer.setClique(cliqueCheck.isSelected());
            ChartDrawer.setLouvain(LouvainCheck.isSelected());
            ChartDrawer.setGirvan(GNCheck.isSelected());
            ChartDrawer.setP1(StatisticDomainController.getChartPointsClique());
            ChartDrawer.setP2(StatisticDomainController.getChartPointsLouvain());
            ChartDrawer.setP3(StatisticDomainController.getChartPointsGirvan());
            ChartDrawer.setPanel(graficPanel);
            ChartDrawer.main(null);
        } catch (Exception e) {

        }
    }

    /**
     *
     * @param evt Checking or unchecking the box
     */
    private void LouvainCheckActionPerformed(java.awt.event.ActionEvent evt) {
        graficPanel.removeAll();
        try {
            ChartDrawer.setClique(cliqueCheck.isSelected());
            ChartDrawer.setLouvain(LouvainCheck.isSelected());
            ChartDrawer.setGirvan(GNCheck.isSelected());
            ChartDrawer.setP1(StatisticDomainController.getChartPointsClique());
            ChartDrawer.setP2(StatisticDomainController.getChartPointsLouvain());
            ChartDrawer.setP3(StatisticDomainController.getChartPointsGirvan());
            ChartDrawer.setPanel(graficPanel);
            ChartDrawer.main(null);
        } catch (Exception e) {

        }
    }

    /**
     *
     * @param evt Checking or unchecking the box
     */
    private void GNCheckActionPerformed(java.awt.event.ActionEvent evt) {
        graficPanel.removeAll();
        try {
            ChartDrawer.setClique(cliqueCheck.isSelected());
            ChartDrawer.setLouvain(LouvainCheck.isSelected());
            ChartDrawer.setGirvan(GNCheck.isSelected());
            ChartDrawer.setP1(StatisticDomainController.getChartPointsClique());
            ChartDrawer.setP2(StatisticDomainController.getChartPointsLouvain());
            ChartDrawer.setP3(StatisticDomainController.getChartPointsGirvan());
            ChartDrawer.setPanel(graficPanel);
            ChartDrawer.main(null);
        } catch (Exception e) {

        }
    }

    /**
     *
     * @param evt Event that triggered tornarButton
     */
    private void tornarButtonActionPerformed(java.awt.event.ActionEvent evt) {
        ChartDrawer.setClique(false);
        ChartDrawer.setGirvan(false);
        ChartDrawer.setLouvain(false);
        iPresentationController.synToMainView();
    }

    // Variables declaration - do not modify
    private javax.swing.JLabel ComsGNLabel;
    private javax.swing.JLabel ComsLouvainLabel;
    private javax.swing.JCheckBox GNCheck;
    private javax.swing.JCheckBox LouvainCheck;
    private javax.swing.JLabel NodesCliqueLabel;
    private javax.swing.JLabel NodesGNLabel;
    private javax.swing.JLabel NodesLouvainLabel;
    private javax.swing.JLabel NumGNLabel;
    private javax.swing.JLabel NumLouvainLabel;
    private javax.swing.JLabel SDGNLabel;
    private javax.swing.JLabel SDLouvainLabel;
    private javax.swing.JLabel SDTimeClique;
    private javax.swing.JLabel TimeGNLabel;
    private javax.swing.JLabel TimeLouvainLabel;
    private javax.swing.JCheckBox cliqueCheck;
    private javax.swing.JLabel comsCliqueLabel;
    private javax.swing.JPanel graficPanel;
    private javax.swing.JLabel numSolsCliqueLabel;
    private javax.swing.JLabel timeCliqueLabel;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton tornarButton;
    // End of variables declaration
}
