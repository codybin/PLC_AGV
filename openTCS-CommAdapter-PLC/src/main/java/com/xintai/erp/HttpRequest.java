/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xintai.erp;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Http�����װ�࣬��װ���õ�Get��Post���󷽷�
 * @author ֣����
 *
 */
public class HttpRequest {
	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	public static String sendGet(String url, String param) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			// connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

			byte[] bresult = result.getBytes();
			result = new String(bresult, "utf-8");
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @param param
	 *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ��
	 * @param encoding
	 *            ������Ӧ��Ϣ�ı����ʽ����utf-8
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	public static String sendGet(String url, String param, String encoding) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url + "?" + param;
			URL realUrl = new URL(urlNameString);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();
			// ��ȡ������Ӧͷ�ֶ�
			Map<String, List<String>> map = connection.getHeaderFields();
			// �������е���Ӧͷ�ֶ�
			// for (String key : map.keySet()) {
			// System.out.println(key + "--->" + map.get(key));
			// }
			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}

			byte[] bresult = result.getBytes();
			result = new String(bresult, encoding);
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return result;
	}

	/**
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param param
	 *            ����������������Ӧ����Json��ʽ�ַ�������ʽ��
	 * @return ������Զ����Դ����Ӧ���
	 */
	public static String sendPost(String url, String jsonData) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection con = realUrl.openConnection();
			HttpURLConnection conn = (HttpURLConnection) con;
			// ����ͨ�õ���������
			conn.setRequestMethod("POST"); // ����Post����
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded"); // ������������
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(param.length())); //���ó���
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(new OutputStreamWriter(
					conn.getOutputStream(), "utf-8"));
			// �����������
			// out.print(param);
			out.write(jsonData);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			byte[] bresult = result.getBytes();
			result = new String(bresult, "utf-8");
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public static String sendPost(String url, String jsonData, String encoding, String authorization, String postmanToken)
	{
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection con = realUrl.openConnection();
			HttpURLConnection conn = (HttpURLConnection) con;
			// ����ͨ�õ���������
			conn.setRequestMethod("POST"); // ����Post����
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded"); // ������������
			conn.setRequestProperty("authorization", authorization);
			conn.setRequestProperty("postman-token", postmanToken);
			
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(param.length())); //���ó���
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(new OutputStreamWriter(
					conn.getOutputStream(), encoding));
			// �����������
			// out.print(param);
			out.write(jsonData);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			byte[] bresult = result.getBytes();
			result = new String(bresult, encoding);
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ��ָ�� URL ����POST����������
	 * 
	 * @param url
	 *            ��������� URL
	 * @param jsonData
	 *            ����������������Ӧ����Json��ʽ�ַ�������ʽ��
	 * @param encoding
	 *            ������Ӧ��Ϣ�ı����ʽ����utf-8
	 * @return ������Զ����Դ����Ӧ���
	 */
	public static String sendPost(String url, String jsonData, String encoding) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection con = realUrl.openConnection();
			HttpURLConnection conn = (HttpURLConnection) con;
			// ����ͨ�õ���������
			conn.setRequestMethod("POST"); // ����Post����
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			conn.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded"); // ������������
			// conn.setRequestProperty("Content-Length",
			// String.valueOf(param.length())); //���ó���
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(new OutputStreamWriter(
					conn.getOutputStream(), encoding));
			// �����������
			// out.print(param);
			out.write(jsonData);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			byte[] bresult = result.getBytes();
			result = new String(bresult, encoding);
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	

	/**
	 * �ϴ�ý���ļ�
	 * @param url
	 * 				�ϴ�ý���ļ���΢�Ź���ƽ̨�ӿڵ�ַ
	 * @param file
	 * 				Ҫ�ϴ���ý���ļ�����
	 * @return
	 * 				�����ϴ�����Ӧ���������ο�΢�Ź���ƽ̨�����߽ӿ��ĵ�
	 */
	public static String uploadPost(String url, File file) {
		DataOutputStream dos = null;
		FileInputStream fis = null;
		DataInputStream dis = null;
		BufferedReader in = null;
		String result = "";
		String end = "\r\n";
		String twoHyphens = "--"; // ����ƴ��
		String boundary = "*****"; // ����ƴ�� ���Զ���
		try {
			URL realUrl = new URL(url);
			// �򿪺�URL֮�������
			URLConnection con = realUrl.openConnection();
			HttpURLConnection conn = (HttpURLConnection) con;
			// ����ͨ�õ���������
			conn.setRequestMethod("POST"); // ����Post����
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setConnectTimeout(5 * 1000);
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary=" + boundary); // ������������
			
			// ��ȡURLConnection�����Ӧ�������
			dos = new DataOutputStream(conn.getOutputStream());
			//1��д��ý��ͷ����
			StringBuilder sb = new StringBuilder();
			sb.append(twoHyphens).append(boundary).append(end);
			sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"").append(end);
			sb.append("Content-Type:application/octet-stream").append(end).append(end);
			byte[] head = sb.toString().getBytes("utf-8");
			dos.write(head);
			
			//2��д��ý�����Ĳ��֣� ���ļ����д���
			fis = new FileInputStream(file);
			dis = new DataInputStream(fis);
			byte[] buffer = new byte[8192]; // 8K
			int count = 0;
			while ((count = dis.read(buffer)) != -1) {
				dos.write(buffer, 0, count);
			}
			
			//3��д��ý���β���֡�
			byte[] foot = (end + twoHyphens + boundary + twoHyphens + end).getBytes("utf-8");
			dos.write(foot);
			dos.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			byte[] bresult = result.getBytes();
			result = new String(bresult, "utf-8");
		} catch (Exception e) {
			System.out.println("���� POST ��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (dos != null) {
					dos.close();
				}
				if (dis != null) {
					dis.close();
				}
				if (fis != null) {
					fis.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
}
