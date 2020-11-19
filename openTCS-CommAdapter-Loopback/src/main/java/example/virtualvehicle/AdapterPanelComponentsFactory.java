/**
 * Copyright (c) Fraunhofer IML
 */
package example.virtualvehicle;

import com.xintai.kecongcontrol.ui.KeCongControlForm;
import tryvehicle.Vehicle;
import org.opentcs.components.kernel.services.VehicleService;

/**
 * A factory for creating various comm adapter panel specific instances.
 *�������ͨѶ�������Ĺ���
 * @author Martin Grzenia (Fraunhofer IML)
 */
public interface AdapterPanelComponentsFactory {

  /**
   * Creates a {@link ControlPanel} representing the given process model's content.
   *
   * @param processModel The process model to represent.
   * @param vehicleService The vehicle service used for interaction with the comm adapter.
   * @return The control panel.
   */
  ControlPanel createControlPanel(ExampleProcessModelTO processModel,
                                  VehicleService vehicleService);
  
  /**
   * Creates a {@link StatusPanel} representing the given process model's content.
   *����������������ģ�����ݵ�״̬pannel
   * @param processModel The process model to represent.
   * @param vehicleService The vehicle service used for interaction with the comm adapter.
   * @return The status panel.
   */
  StatusPanel createStatusPanel(ExampleProcessModelTO processModel,
                                VehicleService vehicleService);
  
  /**
   *
   * @param processModel
   * @param vehicleService
   * @return
   */
  Vehicle createVehilePanel(ExampleProcessModelTO processModel,
                                  VehicleService vehicleService);
  /**
   * @param processModel
   * @param vehicleService
   * @return
   */
   //KeCongControlForm createKeCongControlFormPanel(ExampleProcessModelTO processModel,
     //                             VehicleService vehicleService);
}
