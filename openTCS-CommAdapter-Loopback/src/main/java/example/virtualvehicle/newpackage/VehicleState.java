/**
 * Copyright (c) Fraunhofer IML
 */
package example.virtualvehicle.newpackage;

import static com.google.common.base.Ascii.ETX;
import static com.google.common.base.Ascii.STX;
import com.google.common.primitives.Ints;
import example.virtualvehicle.OrderResponse;
import example.virtualvehicle.StateResponse;
import example.virtualvehicle.Telegram;

/**
 * Represents the state of a physical vehicle.
 * ������ʵ����״̬ 
 * Generally the content matches a {@link StateResponse}.
 *
 * @author Martin Grzenia (Fraunhofer IML)
 */
public class VehicleState {

  /**
   * The telegram counter to match request and response.
   * ƥ������� ���͵ı�������
   */
  private int telegramCounter;
  /**
   * The current operation mode of the vehicle.
   * �����ĵ�ǰ״̬
   * (M)oving, (A)cting, (I)dle, (C)harging, (E)rror.
   */
  private char operationState = 'M';
  /**
   * The load handling state of the vehicle.
   * �����ĸ���״̬
   * (E)mpty, (F)ull, (U)nknown.
   */
  private char loadState = 'E';
  /**
   * The id of the current position.
   * ��ǰλ�õ�ID
   */
  private int positionId;
  /**
   * The order id of the last finished order.
   * ��һ����ɵĶ�����ID
   */
  private int lastFinishedOrderId;
  /**
   * The order id of the currently executed order.
   */
  private int currOrderId;
  /**
   * The order id of the last received oder.
   * ��һ�����յ�ID
   */
  private int lastReceivedOrderId;

  public int getTelegramCounter() {
    return telegramCounter;
  }

  public void setTelegramCounter(int telegramCounter) {
    this.telegramCounter = telegramCounter;
  }

  public char getOperationState() {
    return operationState;
  }

  public void setOperationState(char operationState) {
    this.operationState = operationState;
  }

  public char getLoadState() {
    return loadState;
  }

  public void setLoadState(char loadState) {
    this.loadState = loadState;
  }

  public int getPositionId() {
    return positionId;
  }

  public void setPositionId(int positionId) {
    this.positionId = positionId;
  }

  public int getLastFinishedOrderId() {
    return lastFinishedOrderId;
  }

  public void setLastFinishedOrderId(int lastFinishedOrderId) {
    this.lastFinishedOrderId = lastFinishedOrderId;
  }

  public int getCurrOrderId() {
    return currOrderId;
  }

  public void setCurrOrderId(int currOrderId) {
    this.currOrderId = currOrderId;
  }

  public int getLastReceivedOrderId() {
    return lastReceivedOrderId;
  }

  public void setLastReceivedOrderId(int lastReceivedOrderId) {
    this.lastReceivedOrderId = lastReceivedOrderId;
  }

  /**
   * Creates a state response for the current vehicle state
   *��ǰ������״̬
   * @return A state response
   */
  public StateResponse toStateResponse() {
    byte[] telegramData = new byte[StateResponse.TELEGRAM_LENGTH];

    telegramData[0] = STX;
    telegramData[1] = StateResponse.PAYLOAD_LENGTH;
    telegramData[2] = StateResponse.TYPE;
    // set telegram counter
    byte[] tmp = Ints.toByteArray(getTelegramCounter());
    telegramData[3] = tmp[2];
    telegramData[4] = tmp[3];
    // set pos id
    tmp = Ints.toByteArray(getPositionId());
    telegramData[5] = tmp[2];
    telegramData[6] = tmp[3];
    // set op mode
    telegramData[7] = (byte) getOperationState();
    // set load state
    telegramData[8] = (byte) getLoadState();
    // set last received order id
    tmp = Ints.toByteArray(getLastReceivedOrderId());
    telegramData[9] = tmp[2];
    telegramData[10] = tmp[3];
    // set current order id
    tmp = Ints.toByteArray(getCurrOrderId());
    telegramData[11] = tmp[2];
    telegramData[12] = tmp[3];
    // set last finished order id
    tmp = Ints.toByteArray(getLastFinishedOrderId());
    telegramData[13] = tmp[2];
    telegramData[14] = tmp[3];
    // set checksum
    telegramData[StateResponse.CHECKSUM_POS] = Telegram.getCheckSum(telegramData);
    telegramData[StateResponse.TELEGRAM_LENGTH - 1] = ETX;

    telegramCounter++;

    return new StateResponse(telegramData);
  }

  /**
   * Creates an order response for the current vehicle state.
   *Ϊ��ǰ�ĳ���״̬����һ��������Ӧ
   * @return The order response
   */
  public OrderResponse toOrderResponse() {
    byte[] telegramData = new byte[OrderResponse.TELEGRAM_LENGTH];

    telegramData[0] = STX;
    telegramData[1] = OrderResponse.PAYLOAD_LENGTH;
    telegramData[2] = OrderResponse.TYPE;
    // set telegram counter
    byte[] tmp = Ints.toByteArray(getTelegramCounter());
    telegramData[3] = tmp[2];
    telegramData[4] = tmp[3];
    // set last received order id
    tmp = Ints.toByteArray(getLastReceivedOrderId());
    telegramData[5] = tmp[2];
    telegramData[6] = tmp[3];
    // set checksum
    telegramData[OrderResponse.CHECKSUM_POS] = Telegram.getCheckSum(telegramData);
    telegramData[OrderResponse.TELEGRAM_LENGTH - 1] = ETX;

    telegramCounter++;

    return new OrderResponse(telegramData);
  }
}
