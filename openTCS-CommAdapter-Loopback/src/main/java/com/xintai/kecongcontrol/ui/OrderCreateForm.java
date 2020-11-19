/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.kecongcontrol.ui;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.xintai.vehicle.comadpter.KeCongProcessModelTO;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nonnull;

import org.opentcs.access.KernelServicePortal;
import org.opentcs.access.to.order.DestinationCreationTO;
import org.opentcs.access.to.order.TransportOrderCreationTO;
import org.opentcs.components.kernel.services.DispatcherService;
import org.opentcs.components.kernel.services.TransportOrderService;
import org.opentcs.components.kernel.services.VehicleService;
import org.opentcs.customizations.ServiceCallWrapper;
import org.opentcs.data.model.Location;
import org.opentcs.data.model.LocationType;
import org.opentcs.data.model.Point;
import org.opentcs.data.order.TransportOrder;
import org.opentcs.drivers.vehicle.management.VehicleCommAdapterPanel;
import org.opentcs.drivers.vehicle.management.VehicleProcessModelTO;
import org.opentcs.util.CallWrapper;
import org.opentcs.util.Comparators;

/**
 *
 * @author Lenovo
 */
public class OrderCreateForm
    extends VehicleCommAdapterPanel {

  /**
   * Creates new form NewJPanel
   */
  KernelServicePortal kernelServicePortal;
  private final VehicleService vehicleService;
  private final CallWrapper callWrapper;
  private final KeCongProcessModelTO processModel;
  @Inject
  public OrderCreateForm(@Assisted KeCongProcessModelTO processModel,
      @Nonnull KernelServicePortal kernelServicePortal,@Assisted VehicleService vehicleService,@ServiceCallWrapper CallWrapper callWrapper) {
    this.kernelServicePortal=kernelServicePortal;
    this.vehicleService=vehicleService;
    this.callWrapper=callWrapper;
    this.processModel=processModel;
    initComponents();
    initCombox();
  }
private void initCombox() 
{
 LocationComboBox.removeAll();
 //ActionComboBox.removeAll();
  try {
       callWrapper.call(() -> vehicleService.fetchObjects(Location.class)).stream()
          .sorted(Comparators.objectsByName())
          .forEach
    (
        location -> LocationComboBox.addItem(location.getName())
    );
    

  }
  catch (Exception e) {
    System.out.println("com.xintai.kecongcontrol.ui.OrderCreateForm.initCombox()");
  }
    
}
  @Override
  public void processModelChange(String attributeChanged, VehicleProcessModelTO processModel) {
//    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  /**
   * This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    jPanel5 = new javax.swing.JPanel();
    LocationComboBox = new javax.swing.JComboBox<>();
    jLabel24 = new javax.swing.JLabel();
    jLabel25 = new javax.swing.JLabel();
    ActionComboBox = new javax.swing.JComboBox<>();
    CreatOrderjb = new javax.swing.JButton();
    CancelOrder = new javax.swing.JButton();

    jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("订单命令"));

    jLabel24.setText("位置： ");

    jLabel25.setText("动作：");

    ActionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Load cargo", "NOP", "Unload cargo" }));

    CreatOrderjb.setText("创建订单");
    CreatOrderjb.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        CreatOrderjbActionPerformed(evt);
      }
    });

    CancelOrder.setText("取消订单");
    CancelOrder.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        CancelOrderActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel24)
              .addComponent(jLabel25))
            .addGap(32, 32, 32)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(ActionComboBox, 0, 106, Short.MAX_VALUE)
              .addComponent(LocationComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          .addGroup(jPanel5Layout.createSequentialGroup()
            .addComponent(CreatOrderjb)
            .addGap(66, 66, 66)
            .addComponent(CancelOrder)))
        .addContainerGap(105, Short.MAX_VALUE))
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(LocationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel24))
        .addGap(27, 27, 27)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(jLabel25)
          .addComponent(ActionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(CreatOrderjb)
          .addComponent(CancelOrder))
        .addGap(35, 35, 35))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 237, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addGap(0, 247, Short.MAX_VALUE))
    );

    getAccessibleContext().setAccessibleName("订单创建");
  }// </editor-fold>//GEN-END:initComponents

  private void CreatOrderjbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreatOrderjbActionPerformed
    // TODO add your handling code here:
     DispatcherService dispatcherService = kernelServicePortal.getDispatcherService();
    TransportOrderService transportOrderService = kernelServicePortal.getTransportOrderService();
    // A list of destinations the transport order the vehicle is supposed
    // to travel to:
    List<DestinationCreationTO> destinations = new LinkedList<>();
    // Create a new destination description and add it to the list.
    // Every destination is described by the name of the destination
    // location in the plant model and an operation the vehicle is supposed
    // to perform there:
    destinations.add(new DestinationCreationTO(LocationComboBox.getSelectedItem().toString(),ActionComboBox.getSelectedItem().toString()
    ));
    // Add as many destinations to the list like this as necessary. Then
    // create a transport order description with a name for the new transport
    // order and the list of destinations.
    // Note that the given name needs to be unique.
    TransportOrderCreationTO orderTO
    = new TransportOrderCreationTO("MyTransportOrder",
      destinations);
    // Optionally, express that the actual/full name of the order should be
    // generated by the kernel.
    orderTO = orderTO.withIncompleteName(true);
    // Optionally, assign a specific vehicle to the transport order:
      orderTO = orderTO.withIntendedVehicleName(processModel.getVehicleName());
    // Optionally, set a deadline for the transport order:
    orderTO = orderTO.withDeadline(Instant.now().plus(1, ChronoUnit.HOURS));

    // Create a new transport order for the given description:
     TransportOrder newOrder = transportOrderService.createTransportOrder(orderTO);
    // order.addItem(newOrder.getName());
    //  map.put(newOrder.getName(), newOrder);
    // Trigger the dispatch process for the created transport order.
     dispatcherService.dispatch();
  }//GEN-LAST:event_CreatOrderjbActionPerformed

  private void CancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelOrderActionPerformed
    // TODO add your handling code here:
   
  }//GEN-LAST:event_CancelOrderActionPerformed


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JComboBox<String> ActionComboBox;
  private javax.swing.JButton CancelOrder;
  private javax.swing.JButton CreatOrderjb;
  private javax.swing.JComboBox<String> LocationComboBox;
  private javax.swing.JLabel jLabel24;
  private javax.swing.JLabel jLabel25;
  private javax.swing.JPanel jPanel5;
  // End of variables declaration//GEN-END:variables
}
