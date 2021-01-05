/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.erp;

 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
 
/**
 * @Description: //TODO  �򵥵�HTTP�������� ��ʵ���ǽ���HTTP���󣬲���������
 * @Author: sunfch
 * @Date: 2019/1/3 16:35
 * @Param:
 * @Return:
 */
public class HttpServer {
 
    public static void main(String[] args) {
        try {
 
            /*�����˿ںţ�ֻҪ��8888���ܽ��յ�*/
            ServerSocket ss = new ServerSocket(8888);
 
            while (true) {
                /*ʵ�����ͻ��ˣ��̶���·��ͨ������˽��ܵĶ���������Ӧ�Ŀͻ���ʵ��*/
                Socket socket = ss.accept();
                /*��ȡ�ͻ�����������������������Ļ�����Ϣ������ͷ�����з���������*/
                BufferedReader bd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
                /**
                 * ����HTTP���󣬲���������
                 */
                String requestHeader;
                int contentLength = 0;
                while ((requestHeader = bd.readLine()) != null && !requestHeader.isEmpty()) {
                    System.out.println(requestHeader);
                    /**
                     * ���GET����
                     */
                    if (requestHeader.startsWith("GET")) {
                        int begin = requestHeader.indexOf("/?") + 2;
                        int end = requestHeader.indexOf("HTTP/");
                        String condition = requestHeader.substring(begin, end);
                        System.out.println("GET�����ǣ�" + condition);
                    }
                    /**
                     * ���POST����
                     * 1.��ȡ�������ݳ���
                     */
                    if (requestHeader.startsWith("Content-Length")) {
                        int begin = requestHeader.indexOf("Content-Lengh:") + "Content-Length:".length();
                        String postParamterLength = requestHeader.substring(begin+1).trim();
                        contentLength = Integer.parseInt(postParamterLength);
                        System.out.println("POST���������ǣ�" + Integer.parseInt(postParamterLength));
                    }
                }
                StringBuffer sb = new StringBuffer();
                if (contentLength > 0) {
                    for (int i = 0; i < contentLength; i++) {
                        sb.append((char) bd.read());
                    }
                    System.out.println("POST�����ǣ�" + sb.toString());
                }
                /*���ͻ�ִ*/
                PrintWriter pw = new PrintWriter(socket.getOutputStream());
 
                pw.println("HTTP/1.1 200 OK");
                pw.println("Content-type:text/html");
                pw.println();
                pw.println("successfull");
 
                pw.flush();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
