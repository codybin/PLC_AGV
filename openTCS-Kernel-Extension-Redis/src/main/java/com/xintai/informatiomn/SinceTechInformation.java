/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.informatiomn;

/**
 *��Ӳ����࣬���������http��ʽ�����Ϣ������ͨ�������ķ�ʽ
 * @author Lenovo
 */
public class SinceTechInformation {
public SinceTechInformation(String depart,String contactString,String dataString)
{ this.contact=contact;
this.datatime=dataString;
this.departname=depart;
}
  /**
   * @return the departname
   */
  public String getDepartname() {
    return departname;
  }

  /**
   * @param departname the departname to set
   */
  public void setDepartname(String departname) {
    this.departname = departname;
  }

  /**
   * @return the contact
   */
  public String getContact() {
    return contact;
  }

  /**
   * @param contact the contact to set
   */
  public void setContact(String contact) {
    this.contact = contact;
  }

  /**
   * @return the datatime
   */
  public String getDatatime() {
    return datatime;
  }

  /**
   * @param datatime the datatime to set
   */
  public void setDatatime(String datatime) {
    this.datatime = datatime;
  }
  private String departname;
  private String contact;
  private String datatime;
}
