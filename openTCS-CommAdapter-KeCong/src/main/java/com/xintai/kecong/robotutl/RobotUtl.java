/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.kecong.robotutl;

import com.xintai.kecong.mesaage.adapter.OpentcsPointToKeCongPoint;
import org.opentcs.data.model.Point;
import org.opentcs.drivers.vehicle.MovementCommand;

/**
 *
 * @author Lenovo
 */
public class RobotUtl {
  public static int pointmaptoint(MovementCommand movementCommand) {
        //�������link���б��������������������������У����Կ�����Ҫͬ����
        Object selectedItem = movementCommand.getStep().getDestinationPoint().getName();
        String destinationIdString = selectedItem instanceof Point
                ? ((Point) selectedItem).getName() : selectedItem.toString();
        int destinationid=new  OpentcsPointToKeCongPoint(destinationIdString).getIntPoint();
      return destinationid;
  }
}
