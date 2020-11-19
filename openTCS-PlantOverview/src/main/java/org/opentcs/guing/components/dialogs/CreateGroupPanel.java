/*
 * openTCS copyright information:
 * Copyright (c) 2013 Fraunhofer IML
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.guing.components.dialogs;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import javax.inject.Inject;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import org.opentcs.guing.application.OpenTCSView;
import org.opentcs.guing.model.AbstractModelComponent;
import org.opentcs.guing.model.ModelComponent;
import org.opentcs.guing.model.SystemModel;
import org.opentcs.guing.model.elements.LocationModel;
import org.opentcs.guing.model.elements.PathModel;
import org.opentcs.guing.model.elements.PointModel;
import org.opentcs.guing.persistence.ModelManager;
import org.opentcs.guing.util.Comparators;
import org.opentcs.guing.util.I18nPlantOverview;
import org.opentcs.guing.util.ResourceBundleUtil;
import org.opentcs.util.gui.Icons;

/**
 * A panel to create a group.
 *
 * @author Philipp Seifert (Fraunhofer IML)
 */
public class CreateGroupPanel
    extends javax.swing.JDialog {

  /**
   * The application's main view.
   */
  private final OpenTCSView view;
  /**
   * Provides the current system model.
   */
  private final ModelManager modelManager;
  private List<PointModel> points;
  private List<PathModel> paths;
  private List<LocationModel> locations;

  /**
   * Creates new form CreateGroupPanel.
   *
   * @param view The application's main view.
   * @param modelManager Provides the current system model.
   */
  @Inject
  public CreateGroupPanel(OpenTCSView view, ModelManager modelManager) {
    this.view = requireNonNull(view, "view");
    this.modelManager = requireNonNull(modelManager, "modelManager");

    initComponents();

    setIconImages(Icons.getOpenTCSIcons());
    setTitle(ResourceBundleUtil.getBundle(I18nPlantOverview.CREATEGROUP_PATH)
        .getString("createGroupPanel.title"));
    initLists();
  }

  /**
   * Initializes all lists with values.
   */
  private void initLists() {
    SystemModel systemModel = modelManager.getModel();

    points = systemModel.getPointModels();
    Collections.sort(points, Comparators.modelComponentsByName());

    paths = systemModel.getPathModels();
    Collections.sort(paths, Comparators.modelComponentsByName());

    locations = systemModel.getLocationModels();
    Collections.sort(locations, Comparators.modelComponentsByName());

    DefaultListModel<PointModel> pointListModel = new DefaultListModel<>();
    for (PointModel model : points) {
      pointListModel.addElement(model);
    }

    pointList.setCellRenderer(new ModelCellRenderer());
    pointList.setModel(pointListModel);

    DefaultListModel<PathModel> pathListModel = new DefaultListModel<>();

    for (PathModel model : paths) {
      pathListModel.addElement(model);
    }

    pathList.setCellRenderer(new ModelCellRenderer());
    pathList.setModel(pathListModel);

    DefaultListModel<LocationModel> locationListModel = new DefaultListModel<>();

    for (LocationModel model : locations) {
      locationListModel.addElement(model);
    }

    locationList.setCellRenderer(new ModelCellRenderer());
    locationList.setModel(locationListModel);

    elementsList.setCellRenderer(new ModelCellRenderer());
    elementsList.setModel(new DefaultListModel<>());

    revalidate();
  }

  /**
   * Checks if the addButton should be enabled or disabled.
   */
  private void evaluateAddButtonStatus() {
    if (!pointList.isSelectionEmpty()) {
      addButton.setEnabled(true);
    }
    else if (!pathList.isSelectionEmpty()) {
      addButton.setEnabled(true);
    }
    else if (!locationList.isSelectionEmpty()) {
      addButton.setEnabled(true);
    }
    else {
      addButton.setEnabled(false);
    }
  }

  // CHECKSTYLE:OFF
  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        elementsScrollPane = new javax.swing.JScrollPane();
        elementsList = new javax.swing.JList<>();
        selectLabel = new javax.swing.JLabel();
        selectedLabel = new javax.swing.JLabel();
        listPanel = new javax.swing.JPanel();
        pointsLabel = new javax.swing.JLabel();
        pathLabel = new javax.swing.JLabel();
        locationLabel = new javax.swing.JLabel();
        pointScrollPane = new javax.swing.JScrollPane();
        pointList = new javax.swing.JList<>();
        pathScrollPane = new javax.swing.JScrollPane();
        pathList = new javax.swing.JList<>();
        locationScrollPane = new javax.swing.JScrollPane();
        locationList = new javax.swing.JList<>();
        buttonPanel = new javax.swing.JPanel();
        createGroupButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(600, 400));
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setPreferredSize(new java.awt.Dimension(600, 400));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("i18n/org/opentcs/plantoverview/dialogs/createGroup"); // NOI18N
        addButton.setText(bundle.getString("createGroupPanel.button_addElements.text")); // NOI18N
        addButton.setEnabled(false);
        addButton.setName("addButton"); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        getContentPane().add(addButton, gridBagConstraints);

        removeButton.setText(bundle.getString("createGroupPanel.button_removeElements.text")); // NOI18N
        removeButton.setEnabled(false);
        removeButton.setName("removeButton"); // NOI18N
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        getContentPane().add(removeButton, gridBagConstraints);

        elementsList.setName("elementsList"); // NOI18N
        elementsList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                elementsListValueChanged(evt);
            }
        });
        elementsScrollPane.setViewportView(elementsList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        getContentPane().add(elementsScrollPane, gridBagConstraints);

        selectLabel.setText(bundle.getString("createGroupPanel.label_selectMembers.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        getContentPane().add(selectLabel, gridBagConstraints);

        selectedLabel.setText(bundle.getString("createGroupPanel.label_selectedMembers.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 2, 0);
        getContentPane().add(selectedLabel, gridBagConstraints);

        listPanel.setName("listPanel"); // NOI18N
        listPanel.setLayout(new java.awt.GridBagLayout());

        pointsLabel.setText(bundle.getString("createGroupPanel.label_points.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 1, 0, 0);
        listPanel.add(pointsLabel, gridBagConstraints);

        pathLabel.setText(bundle.getString("createGroupPanel.label_paths.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        listPanel.add(pathLabel, gridBagConstraints);

        locationLabel.setText(bundle.getString("createGroupPanel.label_locations.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 0);
        listPanel.add(locationLabel, gridBagConstraints);

        pointScrollPane.setPreferredSize(new java.awt.Dimension(200, 130));

        pointList.setName("pointList"); // NOI18N
        pointList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                pointListValueChanged(evt);
            }
        });
        pointScrollPane.setViewportView(pointList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        listPanel.add(pointScrollPane, gridBagConstraints);

        pathScrollPane.setPreferredSize(new java.awt.Dimension(200, 130));

        pathList.setName("pathList"); // NOI18N
        pathList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                pathListValueChanged(evt);
            }
        });
        pathScrollPane.setViewportView(pathList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 0, 3);
        listPanel.add(pathScrollPane, gridBagConstraints);

        locationScrollPane.setPreferredSize(new java.awt.Dimension(200, 130));

        locationList.setName("locationList"); // NOI18N
        locationList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                locationListValueChanged(evt);
            }
        });
        locationScrollPane.setViewportView(locationList);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        listPanel.add(locationScrollPane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(listPanel, gridBagConstraints);

        buttonPanel.setLayout(new java.awt.GridBagLayout());

        createGroupButton.setText(bundle.getString("createGroupPanel.button_createGroup.text")); // NOI18N
        createGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createGroupButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        buttonPanel.add(createGroupButton, gridBagConstraints);

        cancelButton.setText(bundle.getString("createGroupPanel.button_cancel.text")); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        buttonPanel.add(cancelButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 1, 0);
        getContentPane().add(buttonPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
  // CHECKSTYLE:ON

  private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
    DefaultListModel<AbstractModelComponent> listModel = new DefaultListModel<>();
    List<AbstractModelComponent> mergedList = new ArrayList<>();
    Enumeration<AbstractModelComponent> baseValues
        = ((DefaultListModel<AbstractModelComponent>) elementsList.getModel()).elements();

    while (baseValues.hasMoreElements()) {
      mergedList.add(baseValues.nextElement());
    }

    for (PointModel model : pointList.getSelectedValuesList()) {
      if (!mergedList.contains(model)) {
        mergedList.add(model);
      }
    }

    pointList.clearSelection();

    for (PathModel model : pathList.getSelectedValuesList()) {
      if (!mergedList.contains(model)) {
        mergedList.add(model);
      }
    }

    pathList.clearSelection();

    for (LocationModel model : locationList.getSelectedValuesList()) {
      if (!mergedList.contains(model)) {
        mergedList.add(model);
      }
    }

    Collections.sort(mergedList, Comparators.modelComponentsByName());

    for (AbstractModelComponent o : mergedList) {
      listModel.addElement(o);
    }

    locationList.clearSelection();
    elementsList.setModel(listModel);
    revalidate();
  }//GEN-LAST:event_addButtonActionPerformed

  private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
    DefaultListModel<AbstractModelComponent> listModel
        = (DefaultListModel<AbstractModelComponent>) elementsList.getModel();
    List<AbstractModelComponent> selectedObjects = new ArrayList<>();

    for (int i : elementsList.getSelectedIndices()) {
      selectedObjects.add(listModel.get(i));
    }

    for (AbstractModelComponent o : selectedObjects) {
      listModel.removeElement(o);
    }

    revalidate();
  }//GEN-LAST:event_removeButtonActionPerformed

  private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    this.dispose();
  }//GEN-LAST:event_cancelButtonActionPerformed

  private void createGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createGroupButtonActionPerformed
    Set<ModelComponent> elements = new HashSet<>();
    DefaultListModel<AbstractModelComponent> listModel
        = (DefaultListModel<AbstractModelComponent>) elementsList.getModel();

    for (Object o : listModel.toArray()) {
      ModelComponent comp = (ModelComponent) o;
      elements.add(comp);
    }

    if (elements.isEmpty()) {
      JOptionPane.showMessageDialog(
          this, ResourceBundleUtil.getBundle(I18nPlantOverview.CREATEGROUP_PATH)
              .getString("createGroupPanel.optionPane_noElementsSelected.message"));
      return;
    }

    view.createGroup(elements);
    this.dispose();
  }//GEN-LAST:event_createGroupButtonActionPerformed

  private void elementsListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_elementsListValueChanged
    removeButton.setEnabled(!elementsList.isSelectionEmpty());
  }//GEN-LAST:event_elementsListValueChanged

  private void pointListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_pointListValueChanged
    evaluateAddButtonStatus();
  }//GEN-LAST:event_pointListValueChanged

  private void pathListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_pathListValueChanged
    evaluateAddButtonStatus();
  }//GEN-LAST:event_pathListValueChanged

  private void locationListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_locationListValueChanged
    evaluateAddButtonStatus();
  }//GEN-LAST:event_locationListValueChanged

  // CHECKSTYLE:OFF
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton createGroupButton;
    private javax.swing.JList<AbstractModelComponent> elementsList;
    private javax.swing.JScrollPane elementsScrollPane;
    private javax.swing.JPanel listPanel;
    private javax.swing.JLabel locationLabel;
    private javax.swing.JList<LocationModel> locationList;
    private javax.swing.JScrollPane locationScrollPane;
    private javax.swing.JLabel pathLabel;
    private javax.swing.JList<PathModel> pathList;
    private javax.swing.JScrollPane pathScrollPane;
    private javax.swing.JList<PointModel> pointList;
    private javax.swing.JScrollPane pointScrollPane;
    private javax.swing.JLabel pointsLabel;
    private javax.swing.JButton removeButton;
    private javax.swing.JLabel selectLabel;
    private javax.swing.JLabel selectedLabel;
    // End of variables declaration//GEN-END:variables
  // CHECKSTYLE:ON

  private class ModelCellRenderer
      extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list,
                                                  Object value,
                                                  int index,
                                                  boolean isSelected,
                                                  boolean cellHasFocus) {
      JLabel label = (JLabel) super.getListCellRendererComponent(list,
                                                                 value,
                                                                 index,
                                                                 isSelected,
                                                                 cellHasFocus);
      label.setText(((ModelComponent) value).getName());

      return label;
    }
  }
}
