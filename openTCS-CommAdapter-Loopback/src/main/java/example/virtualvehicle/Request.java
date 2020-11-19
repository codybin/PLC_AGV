/**
 * Copyright (c) Fraunhofer IML
 */
package example.virtualvehicle;;

/**
 * A request represents a telegram sent from the control system to vehicle control and expects
 * a response with the same id to match.
 * һ���������һ�����ģ�����ϵͳ���͸ñ��ĸ��������ƣ����ҿ���ϵͳϣ������һ����ͬid�Ļ�Ӧ
 *�ӿ���ϵͳ�������ı���
 * @author Mats Wilhelm (Fraunhofer IML)
 */
public abstract class Request
    extends Telegram {

  /**
   * Creates a new instance.
   *
   * @param telegramLength The request's length.
   */
  public Request(int telegramLength) {
    super(telegramLength);
  }

  /**
   * Updates the content of the request to include the given id.
   *�������������
   * @param telegramId The request's new id.
   */
  public abstract void updateRequestContent(int telegramId);
}
