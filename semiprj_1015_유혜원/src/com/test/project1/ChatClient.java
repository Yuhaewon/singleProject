package com.test.project1;
import java.net.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class ChatClient extends Frame implements ActionListener, Runnable {
  Button btn_exit; // ���� ��ư
  Button btn_send; // ���� ��ư
  Button btn_connect; // ���� ���� ��ư
  TextArea txt_list; // ä�� ���� �����ִ� �κ�
  TextField txt_server_ip; // ���� IP �Է� �ؽ�Ʈ�ʵ�
  TextField txt_name; // ��ȭ�� �Է� �ؽ�Ʈ�ʵ�
  TextField txt_input; // ä�� �Է� �ؽ�Ʈ�ʵ�
  Socket client; // ����
  BufferedReader br; // �Է�
  PrintWriter pw; // ���
  static String server_ip; // ������ IP �ּ�
  final int port = 5005; // ������ ��Ʈ ��ȣ
  CardLayout cl; // ī�� ���̾ƿ� ����

  public ChatClient(String name) {
    setTitle("ä�� Ŭ���̾�Ʈ "); // ����
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        dispose(); 
      }// windowClosing
    });
    cl = new CardLayout();
    setLayout(cl); 
    Panel connect = new Panel(); 
    connect.setBackground(Color.LIGHT_GRAY);
    connect.setLayout(new BorderLayout());
    btn_connect = new Button("���� ����"); 
    btn_connect.addActionListener(this); 
    txt_server_ip = new TextField("127.0.0.1", 15);// �ڽ��� IP,
    txt_name = new TextField(name, 15);
    Panel connect_sub = new Panel(); 
    connect_sub.add(new Label("���� ������(IP) : "));
    connect_sub.add(txt_server_ip);
    connect_sub.add(new Label("��      ȭ      ��  :  "));
    connect_sub.add(txt_name);

    // ä�� ȭ��(�ǳ�) ����
    Panel chat = new Panel();
    chat.setLayout(new BorderLayout());
    Label lblChat = new Label("ä�� ���� ȭ��", Label.CENTER);
    connect.add(lblChat, BorderLayout.NORTH);
    connect.add(connect_sub, BorderLayout.CENTER);
    connect.add(btn_connect, BorderLayout.SOUTH); 
    // ä��â ȭ�� ����
    txt_list = new TextArea(); // ä�� ���� �����ִ� �κ�
    txt_input = new TextField("", 25);// ä�� �Է�
    btn_exit = new Button("����"); // ���� ��ư ����
    btn_send = new Button("����");
    btn_exit.addActionListener(this); // ���� ��ư�� ������ ���̱�
    btn_send.addActionListener(this); // ä�� ���� ��ư�� ������ ���̱�
    txt_input.addActionListener(this); // ä�� �Է¶��� ������ ���̱�
    Panel chat_sub = new Panel(); // ä��â ���� �г�
    chat_sub.add(txt_input);
    chat_sub.add(btn_send);
    chat_sub.add(btn_exit);
    Label lblChatTitle = new Label("ä�� ���α׷� v 1", Label.CENTER);
    chat.add(lblChatTitle, BorderLayout.NORTH);
    chat.add(txt_list, BorderLayout.CENTER);
    chat.add(chat_sub, BorderLayout.SOUTH);
    add(connect, "����â"); 
    add(chat, "ä��â"); 
    cl.show(this, "����â");
    setBounds(250, 250, 300, 300); // ũ�� ����
    setVisible(true); // ���̱�
  }// ������

  public void run() { 
    try {
      client = new Socket(server_ip, port); // ���� ����(������ ����)
      InputStream is = client.getInputStream(); // �Է�
      OutputStream os = client.getOutputStream(); // ���
      br = new BufferedReader(new InputStreamReader(is));
      pw = new PrintWriter(new OutputStreamWriter(os));
      String msg = txt_name.getText();// ��ȭ�� ���
      pw.println(msg);// ��ȭ�� ����
      pw.flush(); // ��� ����
      txt_input.requestFocus();
      while (true) {
        msg = br.readLine(); 
        txt_list.append(msg + "\n");
      }// while
    } catch (Exception e) {
      e.printStackTrace();
    }// catch
  }// run

  public void actionPerformed(ActionEvent e) { 
    try {
      Object obj = e.getSource(); 
      if (obj == btn_connect) { 
        server_ip = txt_server_ip.getText();
        Thread th = new Thread(this);
        th.start(); 
        cl.show(this, "ä��â");
      } else if (obj == btn_exit) { 
        System.exit(0); // ���α׷� ����
      } else if (obj == btn_send || obj == txt_input) {
        String msg = txt_input.getText();// ä�� �Է�
        pw.println(msg);// ä�� ���� ����
        pw.flush();// ��� ����
        txt_input.setText("");
        txt_input.requestFocus();
      }// else if...
    } catch (Exception ex) {
      ex.printStackTrace();
    }// catch
  }// actionPerformed

  public static void main(String[] args) {
    new ChatClient(server_ip);
  }// main
}// end