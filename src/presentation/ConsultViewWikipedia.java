package presentation;

import domini.WikipediaException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.*;

/**
 * Consult class to consult all the Wikipedia
 * @author Aleix Pellisa Cortiella
 */
public class ConsultViewWikipedia extends javax.swing.JPanel {

    /** Presentation controller */
    private PresentationController iPresentationController;
    private Color color = this.getBackground();

    /**
     * Constructor class
     * @param pPresentationController The presentation controller for communication
     */
    public ConsultViewWikipedia(PresentationController pPresentationController) {
        iPresentationController = pPresentationController;
        initComponents();
        initDoc();
    }

    /**
     * Initialize a Document needed for changes in the TextFields
     */
    private void initDoc(){
        jTextField1.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLog(e, "inserted into");
            }

            public void removeUpdate(DocumentEvent e) {
                updateLog(e, "removed from");
            }

            public void changedUpdate(DocumentEvent e) {
                //Plain text components do not fire these events
            }

            public void updateLog(DocumentEvent e, String action) {
                if (action.equals("inserted into")) {
                    jList1.setModel(changeList(jTextField1.getText(), jList1.getModel()));
                } else {
                    if (!jTextField2.getText().isEmpty() && !jTextField2.isEnabled()) {
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategoriesInPage(jTextField2.getText()));
                        for (String s : sortedSet) model.addElement(s);
                        jList1.setModel(restoreList(jTextField1.getText(), model));
                    } else if (!jTextField4.getText().isEmpty() && !jTextField4.isEnabled()) {
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategoriesInSubCategory(jTextField4.getText()));
                        for (String s : sortedSet) model.addElement(s);
                        jList1.setModel(restoreList(jTextField1.getText(), model));
                    } else if (!jTextField3.getText().isEmpty() && !jTextField3.isEnabled()) {
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategoriesInSuperCategory(jTextField3.getText()));
                        for (String s : sortedSet) model.addElement(s);
                        jList1.setModel(restoreList(jTextField1.getText(), model));
                    } else {
                        if (jTextField1.getText().isEmpty()){
                            jList2.setModel(new DefaultListModel());
                            jTextField2.setText("");
                            jList3.setModel(new DefaultListModel());
                            jTextField4.setText("");
                            jList4.setModel(new DefaultListModel());
                            jTextField3.setText("");
                        }
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategories());
                        for (String s : sortedSet) model.addElement(s);
                        jList1.setModel(restoreList(jTextField1.getText(), model));
                    }
                }
            }
        });

        jTextField2.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLog(e, "inserted into");
            }

            public void removeUpdate(DocumentEvent e) {
                updateLog(e, "removed from");
            }

            public void changedUpdate(DocumentEvent e) {
                //Plain text components do not fire these events
            }

            public void updateLog(DocumentEvent e, String action) {
                if (action.equals("inserted into")) {
                    jList2.setModel(changeList(jTextField2.getText(), jList2.getModel()));
                } else {
                    if (!jTextField1.getText().isEmpty()) {
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetPages(iPresentationController.callWikiDomainObtainPagesInCategory(jTextField1.getText()));
                        for (String s : sortedSet) model.addElement(s);
                        jList2.setModel(restoreList(jTextField2.getText(), model));
                    }
                }
            }
        });

        jTextField3.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLog(e, "inserted into");
            }

            public void removeUpdate(DocumentEvent e) {
                updateLog(e, "removed from");
            }

            public void changedUpdate(DocumentEvent e) {
                //Plain text components do not fire these events
            }

            public void updateLog(DocumentEvent e, String action) {
                if (action.equals("inserted into")) {
                    jList4.setModel(changeList(jTextField3.getText(), jList4.getModel()));
                } else {
                    if (!jTextField1.getText().isEmpty()) {
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainSuperCategoriesInCategory(jTextField1.getText()));
                        for (String s : sortedSet) model.addElement(s);
                        jList4.setModel(restoreList(jTextField3.getText(), model));
                    }
                }
            }
        });

        jTextField4.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateLog(e, "inserted into");
            }

            public void removeUpdate(DocumentEvent e) {
                updateLog(e, "removed from");
            }

            public void changedUpdate(DocumentEvent e) {
                //Plain text components do not fire these events
            }

            public void updateLog(DocumentEvent e, String action) {
                if (action.equals("inserted into")) {
                    jList3.setModel(changeList(jTextField4.getText(), jList3.getModel()));
                } else {
                    if (!jTextField1.getText().isEmpty()) {
                        DefaultListModel model = new DefaultListModel();
                        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainSubCategoriesInCategory(jTextField1.getText()));
                        for (String s : sortedSet) model.addElement(s);
                        jList3.setModel(restoreList(jTextField4.getText(), model));
                    }
                }
            }
        });
    }

    /**
     * Method used to update the lists when Page is selected
     * @param o Object used to reference the Page
     */
    private void putListFromPage(Object o){
        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategoriesInPage(o.toString()));
        for (String s: sortedSet) model.addElement(s);
        jList1.setModel(model);
        jTextField1.setText("");
    }

    /**
     * Method used to update the lists when Subcategory is selected
     * @param o Object used to reference the Subcategory
     */
    private void putListFromSub(Object o){
        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategoriesInSubCategory(o.toString()));
        for (String s: sortedSet) model.addElement(s);
        jList1.setModel(model);
        jTextField1.setText("");
    }

    /**
     * Method used to update the lists when Supercategory is selected
     * @param o Object used to reference the Supercategory
     */
    private void putListFromSuper(Object o){
        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategoriesInSuperCategory(o.toString()));
        for (String s: sortedSet) model.addElement(s);
        jList1.setModel(model);
        jTextField1.setText("");
    }

    /**
     * Method used to update the lists when Category is selected
     * This method updates the pages
     * @param o Object used to reference the Category
     */
    private void putListFromCat1(Object o){
        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetPages(iPresentationController.callWikiDomainObtainPagesInCategory(o.toString()));
        for (String s: sortedSet) model.addElement(s);
        jList2.setModel(model);
        jTextField2.setText("");
        jTextField2.setBackground(Color.WHITE);
        jTextField2.enable();
    }

    /**
     * Method used to update the lists when Category is selected
     * This method updates the subcategories
     * @param o Object used to reference the Category
     */
    private void putListFromCat2(Object o){
        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainSubCategoriesInCategory(o.toString()));
        for (String s: sortedSet) model.addElement(s);
        jList3.setModel(model);
        jTextField4.setText("");
        jTextField4.setBackground(Color.WHITE);
        jTextField4.enable();
    }

    /**
     * Method used to update the lists when Category is selected
     * This method updates the supercategories
     * @param o Object used to reference the Category
     */
    private void putListFromCat3(Object o){
        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainSuperCategoriesInCategory(o.toString()));
        for (String s: sortedSet) model.addElement(s);
        jList4.setModel(model);
        jTextField3.setText("");
        jTextField3.setBackground(Color.WHITE);
        jTextField3.enable();
    }

    /**
     * Method used to clean the Page list
     */
    private void cleanList2(){
        jList2.setModel(new DefaultListModel());
        jTextField2.setText("");
        jTextField2.setBackground(this.getBackground());
        jTextField2.disable();
    }

    /**
     * Method used to clean the Subcategory list
     */
    private void cleanList3(){
        jList3.setModel(new DefaultListModel());
        jTextField4.setText("");
        jTextField4.setBackground(this.getBackground());
        jTextField4.disable();
    }

    /**
     * Method used to clean the Supercategory list
     */
    private void cleanList4(){
        jList4.setModel(new DefaultListModel());
        jTextField3.setText("");
        jTextField3.setBackground(this.getBackground());
        jTextField3.disable();
    }

    /**
     * Update a list depending on the content of his TextField
     * @param s The content of his TextField
     * @param model The content of the List
     * @return returns the model of the list updated
     */
    private DefaultListModel changeList(String s, ListModel<String> model) {
        int sizeText = s.length();
        DefaultListModel modelNew = new DefaultListModel();
        int sizeModel = model.getSize();
        for (int i = 0; i < sizeModel; i++){
            String s2 = model.getElementAt(i);
            int sizeS = s2.length();
            boolean trobat = false;
            if (sizeText <= sizeS) {
                //Lletres de cada element de la llista
                int j = 0;
                while (!trobat && j < sizeText){
                    if (s2.charAt(j) != s.charAt(j)) trobat = true;
                    else j++;
                }
                trobat = (j == sizeText);
                if (trobat) modelNew.addElement(s2);
            }
        }
        return modelNew;
    }

    /**
     * Restore a list depending on the content of his TextField
     * @param s The content of his TextField
     * @param model The content of the List
     * @return returns the model of the list restored
     */
    private DefaultListModel restoreList(String s, DefaultListModel model) {
        int sizeText = s.length();
        DefaultListModel modelNew = new DefaultListModel();
        int sizeModel = model.getSize();
        for (int i = 0; i < sizeModel; i++){
            Object s2 = model.getElementAt(i);
            String s3 = s2.toString();
            int sizeS = s3.length();
            boolean trobat = false;
            if (sizeText <= sizeS) {
                //Lletres de cada element de la llista
                int j = 0;
                while (!trobat && j < sizeText){
                    if (s3.charAt(j) != s.charAt(j)) trobat = true;
                    else j++;
                }
                trobat = (j == sizeText);
                if (trobat) modelNew.addElement(s2);
            }
        }
        return modelNew;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">

    /**
     * Initialize the components of the view
     */
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jLabel8 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Consulta la Wikipedia");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Categories");

        jList1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        DefaultListModel model = new DefaultListModel();
        SortedSet<String> sortedSet = iPresentationController.callWikiDomainObtainSortedSetCategories(iPresentationController.callWikiDomainObtainCategories());
        for (String s: sortedSet) model.addElement(s);
        jList1.setModel(model);
        jList1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Buscar Categoria:");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Afegir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Traure");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setText("Pàgines");

        jList2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList2.setModel(new DefaultListModel());
        jList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList2);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Buscar Pàgina:");

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setText("Afegir");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton4.setText("Traure");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Buscar Supercategoria:");

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton5.setText("Traure");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton6.setText("Afegir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel7.setText("Subcategories");

        jList3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList3.setModel(new DefaultListModel());
        jList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jList3);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setText("Buscar Subcategoria:");

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setText("Traure");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton8.setText("Afegir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jList4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jList4.setModel(new DefaultListModel());
        jList4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane4.setViewportView(jList4);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel9.setText("Supercategories");

        jButton9.setBackground(new java.awt.Color(255, 0, 0));
        jButton9.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText("Sortir");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jButton10.setText("Mostra Tot");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        /** Assignar Listeners a llistes */

        ListSelectionModel listSelectionModel;
        listSelectionModel = jList1.getSelectionModel();
        listSelectionModel.addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (!lsm.isSelectionEmpty()) {
                            Object o = jList1.getSelectedValue();
                            jTextField1.setText(o.toString());
                            putListFromCat1(o);
                            putListFromCat2(o);
                            putListFromCat3(o);
                        }
                    }
                }
        );

        ListSelectionModel listSelectionModel2;
        listSelectionModel2 = jList2.getSelectionModel();
        listSelectionModel2.addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (!lsm.isSelectionEmpty()) {
                            Object o = jList2.getSelectedValue();
                            jTextField2.setText(o.toString());
                            jTextField2.disable();
                            jTextField2.setBackground(color);
                            putListFromPage(o);
                            cleanList3();
                            cleanList4();
                        }
                    }
                }
        );

        ListSelectionModel listSelectionModel3;
        listSelectionModel3 = jList3.getSelectionModel();
        listSelectionModel3.addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (!lsm.isSelectionEmpty()) {
                            Object o = jList3.getSelectedValue();
                            jTextField4.setText(o.toString());
                            jTextField4.disable();
                            jTextField4.setBackground(color);
                            putListFromSub(o);
                            cleanList2();
                            cleanList4();
                        }
                    }
                }
        );

        ListSelectionModel listSelectionModel4;
        listSelectionModel4 = jList4.getSelectionModel();
        listSelectionModel4.addListSelectionListener(
                new ListSelectionListener() {
                    public void valueChanged(ListSelectionEvent e) {
                        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                        if (!lsm.isSelectionEmpty()) {
                            Object o = jList4.getSelectedValue();
                            jTextField3.setText(o.toString());
                            jTextField3.disable();
                            jTextField3.setBackground(color);
                            putListFromSuper(o);
                            cleanList2();
                            cleanList3();
                        }
                    }
                }
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2))
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel1)
                                        .addComponent(jScrollPane1))
                                .addGap(71, 71, 71)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jButton10)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton5))
                                        .addComponent(jLabel7)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton4))
                                        .addComponent(jLabel8)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton7))
                                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel9)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(78, 78, 78))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(362, 362, 362))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addGap(9, 9, 9)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addGap(13, 13, 13)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(66, 66, 66)
                                                                .addComponent(jLabel4)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(27, 27, 27)))
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel5)
                                                .addGap(2, 2, 2)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addGap(13, 13, 13)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(61, 61, 61))
        );
    }// </editor-fold>

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {}

    /**
     * @param evt Event that triggered Afegir button from Categories
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s = jTextField1.getText();
        if (!s.isEmpty()) {
            if (!s.contains(" ")) {
                try {
                    iPresentationController.callWikiDomainModifyManually(s, "addC", null);
                    jTextField1.setText("");
                } catch (WikipediaException ex) {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error Wikipedia", ex.getMessage());
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error afegir categoria", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error afegir categoria", "Afegeix un ID");
        }
    }

    /**
     * @param evt Event that triggered Traure button from Categories
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s = jTextField1.getText();
        if (!s.isEmpty()) {
            if (!s.contains(" ")) {
                try {
                    iPresentationController.callWikiDomainModifyManually(s, "removeC", null);
                    jTextField1.setText("");
                }
                catch (WikipediaException ex){
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error Wikipedia", ex.getMessage());
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error traure categoria", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error traure categoria", "Afegeix un ID");
        }
    }

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {}

    /**
     * @param evt Event that triggered Afegir button from Pàgines
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s1 = jTextField1.getText();
        String s2 = jTextField2.getText();
        if (!s1.isEmpty()) {
            if (!s1.contains(" ")) {
                if (!s2.isEmpty()) {
                    if (!s2.contains(" ")) {
                        try {
                            iPresentationController.callWikiDomainModifyManually(s1, "addCP", s2);
                            jTextField2.setText("");
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error afegir pàgina", "La pàgina no pot tenir espais");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error afegir pàgina", "Afegeix un ID");
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error afegir pàgina", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error afegir pàgina", "Selecciona una categoria");
        }
    }

    /**
     * @param evt Event that triggered Traure button from Pagines
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s1 = jTextField1.getText();
        String s2 = jTextField2.getText();
        if (!s1.isEmpty()) {
            if (!s1.contains(" ")) {
                if (!s2.isEmpty()) {
                    if (!s2.contains(" ")) {
                        try {
                            iPresentationController.callWikiDomainModifyManually(s1, "removeCP", s2);
                            jTextField2.setText("");
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error traure pàgina", "La pàgina no pot tenir espais");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error traure pàgina", "Afegeix un ID");
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error traure pàgina", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error traure pàgina", "Selecciona una categoria");
        }
    }

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {}

    /**
     * @param evt Event that triggered Traure button from Supercategories
     */
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s1 = jTextField1.getText();
        String s2 = jTextField3.getText();
        if (!s1.isEmpty()) {
            if (!s1.contains(" ")) {
                if (!s2.isEmpty()) {
                    if (!s2.contains(" ")) {
                        try {
                            iPresentationController.callWikiDomainModifyManually(s1, "removeCsupC", s2);
                            jTextField3.setText("");
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error traure supercategoria", "La supercategoria no pot tenir espais");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error traure supercategoria", "Afegeix un ID");
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error traure supercategoria", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error traure supercategoria", "Selecciona una categoria");
        }
    }

    /**
     * @param evt Event that triggered Afegir button from Supercategoria
     */
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s1 = jTextField1.getText();
        String s2 = jTextField3.getText();
        if (!s1.isEmpty()) {
            if (!s1.contains(" ")) {
                if (!s2.isEmpty()) {
                    if (!s2.contains(" ")) {
                        try {
                            iPresentationController.callWikiDomainModifyManually(s1, "addCsupC", s2);
                            jTextField3.setText("");
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error afegir supercategoria", "La supercategoria no pot tenir espais");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error afegir supercategoria", "Afegeix un ID");
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error afegir supercategoria", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error afegir supercategoria", "Selecciona una categoria");
        }
    }

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {}

    /**
     * @param evt Event that triggered Traure button from Subcategories
     */
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s1 = jTextField1.getText();
        String s2 = jTextField4.getText();
        if (!s1.isEmpty()) {
            if (!s1.contains(" ")) {
                if (!s2.isEmpty()) {
                    if (!s2.contains(" ")) {
                        try {
                            iPresentationController.callWikiDomainModifyManually(s1, "removeCsubC", s2);
                            jTextField4.setText("");
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error traure subcategoria", "La subcategoria no pot tenir espais");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error traure subcategoria", "Afegeix un ID");
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error traure subcategoria", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error traure subcategoria", "Selecciona una categoria");
        }
    }

    /**
     * @param evt Event that triggered Afegir button from Subcategories
     */
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {
        if (iPresentationController.searching()) return;
        String s1 = jTextField1.getText();
        String s2 = jTextField4.getText();
        if (!s1.isEmpty()) {
            if (!s1.contains(" ")) {
                if (!s2.isEmpty()) {
                    if (!s2.contains(" ")) {
                        try {
                            iPresentationController.callWikiDomainModifyManually(s1, "addCsubC", s2);
                            jTextField4.setText("");
                        }
                        catch (WikipediaException ex){
                            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                            vistaError.setError("Error Wikipedia", ex.getMessage());
                        }
                    } else {
                        NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                        vistaError.setError("Error afegir subcategoria", "La subcategoria no pot tenir espais");
                    }
                } else {
                    NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                    vistaError.setError("Error afegir subcategoria", "Afegeix un ID");
                }
            } else {
                NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
                vistaError.setError("Error afegir subcategoria", "La categoria no pot tenir espais");
            }
        }
        else {
            NotificationViewWikipedia vistaError = new NotificationViewWikipedia();
            vistaError.setError("Error afegir subcategoria", "Selecciona una categoria");
        }
    }

    /**
     * @param evt Event that triggered Sortit button
     */
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {
        iPresentationController.synToWikipediaManagementView();
    }

    /**
     * @param evt Event that triggered Mostra Tot button
     */
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {
        iPresentationController.synToConsultViewWikipedia();
    }

    // Variables declaration
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration                   
}
