/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.interaction.erp;

/**
 *
 * @author Lenovo
 */
public class WMSTaskTable {
/***
 * 
 * @param tasknumber
 * @param tasktype
 * @param startstation
 * @param endstaion
 * @param AGVstate 
 */
  private String tasknumber;
  private String tasktype;
  private String startstation;
  private String endstaion;
  private String AGVstate;

  @Override
  public String toString() {
    return "WMSTaskTable{" + "tasknumber=" + tasknumber + ", tasktype=" + tasktype + ", startstation=" + startstation + ", endstaion=" + endstaion + ", AGVstate=" + AGVstate + '}';
  }

  public String getAGVstate() {
    return AGVstate;
  }

  public void setAGVstate(String AGVstate) {
    this.AGVstate = AGVstate;
  }
  public String getTasknumber() {
    return tasknumber;
  }

  public void setTasknumber(String tasknumber) {
    this.tasknumber = tasknumber;
  }

  public String getTasktype() {
    return tasktype;
  }

  public void setTasktype(String tasktype) {
    this.tasktype = tasktype;
  }

  public String getStartstation() {
    return startstation;
  }

  public void setStartstation(String startstation) {
    this.startstation = startstation;
  }

  public String getEndstaion() {
    return endstaion;
  }

  public void setEndstaion(String endstaion) {
    this.endstaion = endstaion;
  } 
}
