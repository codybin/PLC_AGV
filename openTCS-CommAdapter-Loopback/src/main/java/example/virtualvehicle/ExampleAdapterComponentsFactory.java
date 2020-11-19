/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.virtualvehicle;

import java.awt.event.ActionListener;
import org.opentcs.data.model.Vehicle;

/**
 *
 * @author Lenovo
 */
public interface ExampleAdapterComponentsFactory {

  /**
   * Creates a new ExampleCommAdapter for the given vehicle.
   *Ϊ�����ĳ��������µ�������
   * @param vehicle The vehicle
   * @return A new ExampleCommAdapter for the given vehicle
   */
  ExampleCommAdapter createExampleCommAdapter(Vehicle vehicle);

  /**
   * Creates a new {@link RequestResponseMatcher}.
   *�����µ�ƥ��
   * @param telegramSender Sends telegrams/requests.
   * @return The created {@link RequestResponseMatcher}.
   */
  RequestResponseMatcher createRequestResponseMatcher(TelegramSender telegramSender);

  /**
   * Creates a new {@link StateRequesterTask}.
   *�����µ���������
   * @param stateRequestAction The actual action to be performed to enqueue requests.
   * @return The created {@link StateRequesterTask}.
   */
  StateRequesterTask createStateRequesterTask(ActionListener stateRequestAction);
}

