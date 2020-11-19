/**
 * Copyright (c) Fraunhofer IML
 */
package example.virtualvehicle;;

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
  void sendTelegram(Request request);
}
