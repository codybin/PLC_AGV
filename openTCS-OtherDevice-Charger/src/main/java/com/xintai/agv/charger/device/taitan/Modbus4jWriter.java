/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.agv.charger.device.taitan;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;
import com.serotonin.modbus4j.msg.WriteRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;

public class Modbus4jWriter {
    // ��ȡMaster
    //private static ModbusMaster tcpMaster = TcpMaster.getMaster();
    private ModbusMaster tcpMaster = null;
    public Modbus4jWriter(ModbusMaster master) {
        this.tcpMaster = master;
    } 
    /**
     * д��������Ȧ������������
     *
     * @param slaveId     slave��ID
     * @param writeOffset λ��
     * @param writeValue  ֵ
     * @return �Ƿ�д��ɹ�
     */
    public boolean writeCoil(int slaveId, int writeOffset, boolean writeValue)
            throws ModbusTransportException, ModbusInitException {
        // ��������
        WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        // �������󲢻�ȡ��Ӧ����
        WriteCoilResponse response = (WriteCoilResponse) tcpMaster.send(request);
        return !response.isException();
    }
 
    /**
     * д������������ݣ���Ȧ��
     *
     * @param slaveId     slaveId
     * @param startOffset ��ʼλ��
     * @param bdata       д�������
     * @return �Ƿ�д��ɹ�
     */
    public boolean writeCoils(int slaveId, int startOffset, boolean[] bdata)
            throws ModbusTransportException, ModbusInitException {
        // ��������
        WriteCoilsRequest request = new WriteCoilsRequest(slaveId, startOffset, bdata);
        // �������󲢻�ȡ��Ӧ����
        WriteCoilsResponse response = (WriteCoilsResponse) tcpMaster.send(request);
        return !response.isException();
 
    }
 
    /***
     *  ���ּĴ���д����
     *
     * @param slaveId slaveId
     * @param writeOffset ��ʼλ��
     * @param writeValue д�������
     */
    public boolean writeRegister(int slaveId, int writeOffset, short writeValue)
            throws ModbusTransportException, ModbusInitException {
        // �����������
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, writeOffset, writeValue);
        // �������󲢻�ȡ��Ӧ����
        WriteRegisterResponse response = (WriteRegisterResponse) tcpMaster.send(request);
        return !response.isException();
 
    }
 
    /**
     * ���ּĴ���д����ģ��������
     *
     * @param slaveId     modbus��slaveID
     * @param startOffset ��ʼλ��ƫ����ֵ
     * @param sdata       д�������
     * @return �����Ƿ�д��ɹ�
     */
    public boolean writeRegisters(int slaveId, int startOffset, short[] sdata)
            throws ModbusTransportException, ModbusInitException {
        // �����������
        WriteRegistersRequest request = new WriteRegistersRequest(slaveId, startOffset, sdata);
        // �������󲢻�ȡ��Ӧ����
        WriteRegistersResponse response = (WriteRegistersResponse) tcpMaster.send(request);
        return !response.isException();
    }
 
    /**
     * ��������д���ݣ���:д��Float���͵�ģ������Double����ģ��������������Short��Integer��Long��
     *
     * @param value    д��ֵ
     * @param dataType com.serotonin.modbus4j.code.DataType ���Ͷ�����com.serotonin.modbus4j.code.DataType����*/
    public void writeHoldingRegister(int slaveId, int offset, Number value, int dataType)
        throws ModbusTransportException, ErrorResponseException, ModbusInitException
    { // ���� 
      BaseLocator<Number> locator = BaseLocator.holdingRegister(slaveId, offset, dataType); 
      tcpMaster.setValue(locator, value); } }