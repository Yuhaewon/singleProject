package com.test.project1;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatClient1 extends JFrame implements ActionListener, Runnable {

	JButton btn_exit; // ���� ��ư
	JButton btn_send,btn_join; // ���� ��ư

	JTextArea txt_list; // ä�� ���� �����ִ� �κ�

	JTextField txt_input; // ä�� �Է� �ؽ�Ʈ�ʵ�

	Socket client; // ����
	BufferedReader br; // �Է�
	PrintWriter pw; // ���
	String server_ip; // ������ IP �ּ�
	final int port = 5005; // ������ ��Ʈ ��ȣ
	JLabel txt_name;
	CardLayout cl;

	public ChatClient1() {
		setTitle("ä�� Ŭ���̾�Ʈ "); // ����
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
		cl=new CardLayout();
		setLayout(cl);
		Panel connect = new Panel();
		connect.setLayout(new BorderLayout());
		btn_join=new JButton("ä�� ����");
		btn_join.addActionListener(this);
		connect.add(btn_join);
		
		
		JPanel chat = new JPanel();
		chat.setLayout(new BorderLayout());
		txt_list = new JTextArea(); // ä�� ���� �����ִ� �κ�
		txt_input = new JTextField("", 15);// ä�� �Է�
		txt_name = new JLabel("�̿���");
		btn_exit = new JButton("����"); // ���� ��ư ����
		btn_send = new JButton("����");
		btn_exit.addActionListener(this); // ���� ��ư�� ������ ���̱�
		btn_send.addActionListener(this); // ä�� ���� ��ư�� ������ ���̱�
		txt_input.addActionListener(this); // ä�� �Է¶��� ������ ���̱�

		JPanel chat_sub = new JPanel(); // ä��â ���� �г�
		chat_sub.add(txt_input);
		chat_sub.add(btn_send);
		chat_sub.add(btn_exit);
		
		JLabel lblChatTitle = new JLabel("ä�� ���α׷� v 1", JLabel.CENTER);
		chat.add(lblChatTitle, BorderLayout.NORTH);
		chat.add(txt_list, BorderLayout.CENTER);
		chat.add(chat_sub, BorderLayout.SOUTH);
		add(chat);
		setBounds(250, 250, 350, 300);
		setVisible(true); // ���̱�
	}

	public void run() {
		try {
			client = new Socket(server_ip, port); // ���� ����(������ ����)
			InputStream is = client.getInputStream(); // �Է�
			OutputStream os = client.getOutputStream(); // ���
			br = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(new OutputStreamWriter(os));
			String msg1 = txt_name.getText();// ��ȭ�� ���
			pw.println(msg1);// ��ȭ�� ����
			pw.flush(); // ��� ����
			txt_input.requestFocus();
			while (true) {
				msg1 = br.readLine();
				txt_list.append(msg1 + "\n");
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		} // catch
	}// run
	//server_ip = "localhost";
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Object obj = e.getSource();
			if (obj == btn_join) {
				Thread th = new Thread(this);
				th.start();
				cl.show(this, "ä��â");
			} else if (obj == btn_exit) {
				dispose(); // ���α׷� ����
			} else if (obj == btn_send || obj == txt_input) {
				String msg = txt_input.getText();// ä�� �Է�
				pw.println(msg);// ä�� ���� ����
				pw.flush();// ��� ����
				txt_input.setText("");
				txt_input.requestFocus();
			} // else if...
		} catch (Exception ex) {
			ex.printStackTrace();
		} // catch
	}// actionPerformed
	}

