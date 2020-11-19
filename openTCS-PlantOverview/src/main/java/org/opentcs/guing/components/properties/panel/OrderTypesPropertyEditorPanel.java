/*
 * openTCS copyright information:
 * Copyright (c) 2005-2011 ifak e.V.
 * Copyright (c) 2012 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.properties.panel;

import java.util.Enumeration;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import java.util.TreeSet;
import javax.inject.Inject;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListModel;
import org.opentcs.guing.components.dialogs.DetailsDialogContent;
import org.opentcs.guing.components.properties.type.OrderTypesProperty;
import org.opentcs.guing.components.properties.type.Property;
import org.opentcs.guing.transport.OrderTypeSuggestionsPool;
import org.opentcs.guing.util.I18nPlantOverview;
import org.opentcs.guing.util.ResourceBundleUtil;

/**
 * User interface to edit a set of order type strings.
 *
 * @author Martin Grzenia (Fraunhofer IML)
 */
public class OrderTypesPropertyEditorPanel
    extends JPanel
    implements DetailsDialogContent {

  /**
   * The bundle to be used.
   */
  private final ResourceBundleUtil bundle = ResourceBundleUtil.getBundle(I18nPlantOverview.PROPERTIES_PATH);
  /**
   * The pool of types to suggest.
   */
  private final OrderTypeSuggestionsPool typeSuggestionsPool;
  /**
   * The property to edit.
   */
  private OrderTypesProperty fProperty;

  /**
   * Creates a new instance.
   *
   * @param typeSuggestionsPool The pool of types to suggest.
   */
  @Inject
  public OrderTypesPropertyEditorPanel(OrderTypeSuggestionsPool typeSuggestionsPool) {
    this.typeSuggestionsPool = requireNonNull(typeSuggestionsPool, "typeSuggestionsPool");
    initComponents();
    initCategoryCombobox();
  }

  @Override
  public void setProperty(Property property) {
    fProperty = (OrderTypesProperty) property;
    DefaultListModel<String> model = new DefaultListModel<>();

    for (String item : fProperty.getItems()) {
      model.addElement(item);
    }

    itemsList.setModel(model);
  }

  @Override
  public void updateValues() {
    Set<String> items = new TreeSet<>();
    ListModel<String> model = itemsList.getModel();
    int size = model.getSize();

    for (int i = 0; i < size; i++) {
      items.add(model.getElementAt(i));
    }

    fProperty.setItems(items);
  }

  @Override
  public String getTitle() {
    return bundle.getString("orderTypesPropertyEditorPanel.title");
  }

  @Override
  public Property getProperty() {
    return fProperty;
  }

  /**
   * Adds a new entry. Also adds the category to the pool.
   */
  protected void add() {
    DefaultListModel<String> model = (DefaultListModel<String>) itemsList.getModel();
    String category = typeComboBox.getSelectedItem().toString();

    // Check for already added categories
    Enumeration<String> entries = model.elements();
    while (entries.hasMoreElements()) {
      String entry = entries.nextElement();
      if (entry.equals(category)) {
        JOptionPane.showMessageDialog(this,
                                      bundle.getString("orderTypesPropertyEditorPanel.optionPane_typeAlreadyPresentError.message"));
        return;
      }
    }

    model.addElement(category);

    typeSuggestionsPool.addTypeSuggestion(category);
    // Re-initialize the combo box since there may be a new entry
    initCategoryCombobox();
  }

  /**
   * Returns the list with the values.
   *
   * @return The list with the values.
   */
  protected JList<String> getItemsList() {
    return itemsList;
  }

  private void initCategoryCombobox() {
    typeComboBox.removeAllItems();
    for (String suggestion : typeSuggestionsPool.getTypeSuggestions()) {
      typeComboBox.addItem(suggestion);
    }
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    itemsScrollPane = new javax.swing.JScrollPane();
    itemsList = new javax.swing.JList<>();
    typeComboBox = new javax.swing.JComboBox<>();
    addButton = new javax.swing.JButton();
    removeButton = new javax.swing.JButton();

    setPreferredSize(new java.awt.Dimension(300, 250));
    setLayout(new java.awt.GridBagLayout());

    itemsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    itemsScrollPane.setViewportView(itemsList);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(itemsScrollPane, gridBagConstraints);

    typeComboBox.setEditable(true);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
    add(typeComboBox, gridBagConstraints);

    addButton.setFont(addButton.getFont());
    java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/panels/propertyEditing"); // NOI18N
    addButton.setText(bundle.getString("orderTypesPropertyEditorPanel.button_add.text")); // NOI18N
    addButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 0);
    add(addButton, gridBagConstraints);

    removeButton.setFont(removeButton.getFont());
    removeButton.setText(bundle.getString("orderTypesPropertyEditorPanel.button_remove.text")); // NOI18N
    removeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        removeButtonActionPerformed(evt);
      }
    });
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
    gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
    add(removeButton, gridBagConstraints);
  }// </editor-fold>//GEN-END:initComponents

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
      String value = itemsList.getSelectedValue();

      if (value == null) {
        return;
      }

      DefaultListModel<String> model = (DefaultListModel<String>) itemsList.getModel();
      model.removeElement(value);
    }//GEN-LAST:event_removeButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
      add();
    }//GEN-LAST:event_addButtonActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addButton;
  private javax.swing.JList<String> itemsList;
  private javax.swing.JScrollPane itemsScrollPane;
  private javax.swing.JButton removeButton;
  private javax.swing.JComboBox<String> typeComboBox;
  // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON
}
