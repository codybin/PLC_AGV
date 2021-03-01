/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.device;

/**
 *
 * @author Lenovo
 */
public class DestinationsLocations {

  @Override
  public String toString() {
    return "DestinationsM{" + "OrderType=" + OrderType + ", ID=" + ID + ", destinations=" + destinations + '}';
  }
  
  private String OrderType;
  private int ID;
  private Destinations destinations;

  public Destinations getDestinations() {
    return destinations;
  }

  public void setDestinations(Destinations destinations) {
    this.destinations = destinations;
  }
  public int getID() {
    return ID;
  }

  public void setID(int ID) {
    this.ID = ID;
  }
  public String getOrderType() {
    return OrderType;
  }

  public void setOrderType(String OrderType) {
    this.OrderType = OrderType;
  }
}
