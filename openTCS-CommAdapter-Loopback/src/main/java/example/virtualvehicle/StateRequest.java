/**
 * Copyright (c) Fraunhofer IML
 */
package example.virtualvehicle;

import static com.google.common.base.Ascii.ETX;
import static com.google.common.base.Ascii.STX;
import com.google.common.primitives.Ints;


/**
 * Represents a state request addressed to the vehicle.
 *代表一个状态的请求
 * @author Martin Grzenia (Fraunhofer IML)
 */
public class StateRequest
    extends Request {

  /**
   * The request type.
   * 请求类型
   */
  public static final byte TYPE = 1;
  /**
   * The expected length of a telegram of this type.
   * 这个请求的报文的程度
   */
  public static final int TELEGRAM_LENGTH = 7;
  /**
   * The size of the payload (the raw content, without STX, SIZE, CHECKSUM and ETX).
   * 数量
   */
  public static final int PAYLOAD_LENGTH = TELEGRAM_LENGTH - 4;
  /**
   * The position of the checksum byte.
   * 字节检验的位置
   */
  public static final int CHECKSUM_POS = TELEGRAM_LENGTH - 2;

  /**
   * Creates a new instance.
   *
   * @param requestId The request's id.
   */
  public StateRequest(int requestId) {
    super(TELEGRAM_LENGTH);
    this.id = requestId;

    encodeTelegramContent();
  }

  @Override
  public void updateRequestContent(int requestId) {
    id = requestId;
    encodeTelegramContent();
  }

  @Override
  public String toString() {
    return "StateRequest{" + "requestId=" + id + '}';
  }
//编码报文内容
  private void encodeTelegramContent() {
    rawContent[0] = STX;
    rawContent[1] = PAYLOAD_LENGTH;

    rawContent[2] = TYPE;

    byte[] tmpWord = Ints.toByteArray(id);
    rawContent[3] = tmpWord[2];
    rawContent[4] = tmpWord[3];

    rawContent[CHECKSUM_POS] = getCheckSum(rawContent);
    rawContent[TELEGRAM_LENGTH - 1] = ETX;
  }
}
