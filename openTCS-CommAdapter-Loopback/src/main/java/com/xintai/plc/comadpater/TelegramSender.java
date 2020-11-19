/**
 * Copyright (c) Fraunhofer IML
 */
package com.xintai.plc.comadpater;

import com.xintai.vehicle.comadpter.*;
import com.xintai.kecong.message.KeCongRequestMessage;

/**
 * Declares methods for comm adapters capable of sending telegrams/requests.
 *�����ķ��������������͵ķ�����
 * @author Martin Grzenia (Fraunhofer IML)
 */
public interface TelegramSender {

  /**
   * Sends the given {@link Request}.
   *
   * @param request The {@link Request} to be sent.
   */
  void sendTelegram(KeCongRequestMessage request);
}
