/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.agv.charger.device.taitan;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;

/**
 *
 * @author Lenovo
 */
public class ModbusTcpMaster{
   private static ModbusFactory modbusFactory;
 
    static {
        if (modbusFactory == null) {
            modbusFactory = new ModbusFactory();
        }
    }
 
    /**
     * ��ȡmaster
     *
     * @return master
     */
    public static ModbusMaster getMasterRTUOverTcp(String ipAdd) {
        IpParameters params = new IpParameters();
        params.setHost(ipAdd);
        params.setPort(520);
        params.setEncapsulated(true);//�������ȷ����Э��֡�Ƿ���ͨ��tcp��װ��RTU�ṹ������modbus tcp/ipʱ��Ҫ��Ϊfalse, ����modbus rtu over tcp/ipʱ��Ҫ��Ϊtrue
        ModbusMaster master = modbusFactory.createTcpMaster(params, false);// TCP Э��
        try {
            //���ó�ʱʱ��
            master.setTimeout(1000);
            //������������
            master.setRetries(3);
            //��ʼ��
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return master;
    }


    /**
     * ��ȡmaster
     *
     * @return master
     */
    public static ModbusMaster getMasterTcp(String ipAdd) {
        IpParameters params = new IpParameters();
        params.setHost(ipAdd);
        params.setPort(502);
        params.setEncapsulated(false);
        ModbusMaster master = modbusFactory.createTcpMaster(params, false);// TCP Э��
        try {
            //���ó�ʱʱ��
            master.setTimeout(1000);
            //������������
            master.setRetries(3);
            //��ʼ��
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return master;
    }


}
