/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.agv.charger.device.taitan;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsRequest;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.ReadInputRegistersRequest;
import com.serotonin.modbus4j.msg.ReadInputRegistersResponse;

public class Modbus4jReader {
    //��ȡmaster
    //private static ModbusMaster master = TcpMaster.getMaster();
    private ModbusMaster master = null;
    public Modbus4jReader(ModbusMaster master) {
        this.master = master;
    }
    /**
     * ������Ȧ������������
     *
     * @param slaveId slaveId
     * @param offset  λ��
     * @return ��ȡֵ
     */
    public boolean[] readCoilStatus(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
 
        ReadCoilsRequest request = new ReadCoilsRequest(slaveId, offset, numberOfBits);
        ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);
        boolean[] booleans = response.getBooleanData();
        return valueRegroup(numberOfBits, booleans);
    }
 
    /**
     * �������� ��ȡ��Χ�豸����Ŀ�����
     */
    public boolean[] readInputStatus(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, offset, numberOfBits);
        ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
        boolean[] booleans = response.getBooleanData();
        return valueRegroup(numberOfBits, booleans);
    }
 
    /**
     * ��ȡ���ּĴ�������
     *
     * @param slaveId slave Id
     * @param offset  λ��
     */
    public byte [] readHoldingRegister(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, offset, numberOfBits);
        ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master.send(request);
        return response.getData();
    }
 
    /**
     * ��ȡ��Χ�豸���������
     *
     * @param slaveId slaveId
     * @param offset  λ��
     */
    public short[] readInputRegisters(int slaveId, int offset, int numberOfBits)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
 
        ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, offset, numberOfBits);
        ReadInputRegistersResponse response = (ReadInputRegistersResponse) master.send(request);
        return response.getShortData();
    }
 
    /**
     * ������ȡ ����������ȡ��ͬ�Ĵ���������
     */
    public void batchRead() throws ModbusTransportException, ErrorResponseException, ModbusInitException {
 
        BatchRead<Integer> batch = new BatchRead<Integer>();
        batch.addLocator(0, BaseLocator.holdingRegister(1, 1, DataType.TWO_BYTE_INT_SIGNED));
        batch.addLocator(1, BaseLocator.inputStatus(1, 0));
        batch.setContiguousRequests(true);
        BatchResults<Integer> results = master.send(batch);
        System.out.println("batchRead:" + results.getValue(0));
        System.out.println("batchRead:" + results.getValue(1));
    }
 
    private boolean[] valueRegroup(int numberOfBits, boolean[] values) {
        boolean[] bs = new boolean[numberOfBits];
        int temp = 1;
        for (boolean b : values) {
            bs[temp - 1] = b;
            temp++;
            if (temp > numberOfBits)
                break;
        }
        return bs;
    }
}