/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.erp;

/**
 *
 * @author Lenovo
 */
public class ReportCarPostionTOERP {
  
  /***
   * 
   */
  private String ordername;
  private String ordertype;
  private String loadstate;
  private String postion;
  private String  toexuteaction ;
  /***
   * 
   * @param ordername  �������֣��������е���Ϣ���������Ϊ��
   * @param ordertype  �������ͣ����ܵ�ֵΪ��ж�����װ�ᡣ
   * @param loadstate  ����״̬��������ݳ����Ĵ����������͸��ġ�
   * @param postion  ������λ�ã������ĵ�ǰλ�á�
   * @param toexuteaction  ���������λ��Ҫִ�еĶ�����
   */
  public ReportCarPostionTOERP(String ordername, String ordertype, String loadstate, String postion,
                               String toexuteaction) {
    this.ordername = ordername;
    this.ordertype = ordertype;
    this.loadstate = loadstate;
    this.postion = postion;
    this.toexuteaction = toexuteaction;
  }


  public String getOrdername() {
    return ordername;
  }

  public void setOrdername(String ordername) {
    this.ordername = ordername;
  }

  public String getOrdertype() {
    return ordertype;
  }

  public void setOrdertype(String ordertype) {
    this.ordertype = ordertype;
  }

  public String getLoadstate() {
    return loadstate;
  }

  public void setLoadstate(String loadstate) {
    this.loadstate = loadstate;
  }

  public String getPostion() {
    return postion;
  }

  public void setPostion(String postion) {
    this.postion = postion;
  }

  public String getToexuteaction() {
    return toexuteaction;
  }

  public void setToexuteaction(String toexuteaction) {
    this.toexuteaction = toexuteaction;
  }
  
}
