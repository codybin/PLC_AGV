/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.device;

import java.util.List;

/**
 *
 * @author Lenovo
 */
public class NewMain {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    // TODO code application logic here
     DestinationLocationService destinationLocationService=new DestinationLocationService();
 DestinationsLocations destinationsM=   destinationLocationService.findDestinationsMByOrderType("PTRU");
  destinationsM.getDestinations().getDestinations().forEach((e)->{System.out.println(e.toString());});
    /*
    jingBianMysqlService jinBianMysqlService=new jingBianMysqlService();
    jingBian_Device jingBian_Device1=   jinBianMysqlService.findDeviceByID(1);
    System.out.println(jingBian_Device1.toString());
    jinBianMysqlService.clearDeviceID(1);
    jinBianMysqlService.setDeviceID(1, 2);*/
  }
}
