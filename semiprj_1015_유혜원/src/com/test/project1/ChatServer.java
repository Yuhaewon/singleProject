package com.test.project1;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ChatServer extends JFrame implements ActionListener {
	JButton btnExit;
	JTextArea ta;
	Vector vChatList;
	ServerSocket ss;
	Socket sockClient;

	public ChatServer() {
		setTitle("Chat Server v1.1");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});// ������ Ŭ�ξ�
		vChatList = new Vector();
		btnExit = new JButton("��������");
		btnExit.addActionListener(this);
		ta = new JTextArea();
		add(ta, BorderLayout.CENTER);
		add(btnExit, BorderLayout.SOUTH);
		setBounds(250, 250, 250, 200);
		setVisible(false);
		chatStart();// ä�� �޼ҵ� ����

	}

	public void chatStart() {
		try {
			ss = new ServerSocket(5005);
			while (true) {
				sockClient = ss.accept();// Ŭ���̾�Ʈ ���Ӷ����� ���ϻ���
				ta.append(sockClient.getInetAddress().getHostAddress() + " ���ӵ�\n");
				ChatHandle threadChat = new ChatHandle();//
				vChatList.add(threadChat);
				threadChat.start();// ������ ����
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	 public static void main(String[] args) {
	 new ChatServer();
	 }
	class ChatHandle extends Thread {

		BufferedReader br = null;// �Է�
		PrintWriter pw = null;// ���

		public ChatHandle() {
			try {
				InputStream is = sockClient.getInputStream();// �Է�
				br = new BufferedReader(new InputStreamReader(is));

				OutputStream os = sockClient.getOutputStream();// ���
				pw = new PrintWriter(new OutputStreamWriter(os));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void sendAllClient(String msg) {// ��� ������� ä�ó����� ����
			try {
				int size = vChatList.size();// ä�� ����� ���� ���
				for (int i = 0; i < size; i++) {
					// ä�� ���� ������
					ChatHandle chr = (ChatHandle) vChatList.elementAt(i);
					chr.pw.println(msg);// ����� �ѻ�� �ѻ�� ���� ����
					chr.pw.flush();// �����ǳ����� ���� ��� ����
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				String name = br.readLine();
				sendAllClient(name + " �� ����");
				while (true) {
					String msg = br.readLine();// ä�� ���� �ޱ�
					String str = sockClient.getInetAddress().getHostName();
					ta.append(msg + "\n");// ä�� ������ ta�� ����
					// ����
					if (msg.equals("@@Exit")) {
						break;
					} else {
						sendAllClient(name + " : " + msg);
					}
				} // while

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				vChatList.remove(this);
				try {
					br.close();
					pw.close();
					sockClient.close();
				} catch (Exception e) {
				}
			} // ����� �г���
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}