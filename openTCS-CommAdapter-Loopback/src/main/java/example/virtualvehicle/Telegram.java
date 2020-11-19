/**
 * Copyright (c) Fraunhofer IML
 */
package example.virtualvehicle;

import java.io.Serializable;
import static java.util.Objects.requireNonNull;

/**
 * The base class for all telegram types used for communication with the vehicle.
 *�����ͳ�ͨѶ�ı��ĵĻ�����
 * @author Stefan Walter (Fraunhofer IML)
 */
public abstract class Telegram
    implements Serializable {

  /**
   * The default value for a telegram's id.
   * ����Ĭ�ϵı���id
   */
  public static final int ID_DEFAULT = 0;
  /**
   * The telegram's raw content as sent via the network.
   * ͨ�����緢�͵ı��ĵ�ԭʼ����
   */
  protected final byte[] rawContent;
  /**
   * The identifier for a specific telegram instance.
   */
  protected int id;

  /**
   * Creates a new instance.
   *
   * @param telegramLength The telegram's length
   */
  public Telegram(int telegramLength) {
    this.rawContent = new byte[telegramLength];
  }

  /**
   * Returns this telegram's actual raw content.
   *���ر��ĵ���ʵ��ԭʼ����
   * @return This telegram's actual raw content.
   */
  public byte[] getRawContent() {
    return rawContent;
  }

  /**
   * Returns the identifier for this specific telegram instance.
   *�������ָ���ı���ʵ����id
   * @return The identifier for this specific telegram instance.
   */
  public int getId() {
    return id;
  }

  // tag::documentation_checksumComp[]
  /**
   * Computes a checksum for the given raw content of a telegram.
   *������ڸ�����ԭʼ���ݵ�һ����У��
   * @param rawContent A telegram's raw content.
   * @return The checksum computed for the given raw content.
   */
  public static byte getCheckSum(byte[] rawContent) {
    requireNonNull(rawContent, "rawContent");

    int cs = 0;
    for (int i = 0; i < rawContent[1]; i++) {
      cs ^= rawContent[2 + i];
    }
    return (byte) cs;
  }
  // end::documentation_checksumComp[]
}
