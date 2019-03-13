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
		});// 윈도우 클로씽
		vChatList = new Vector();
		btnExit = new JButton("서버종료");
		btnExit.addActionListener(this);
		ta = new JTextArea();
		add(ta, BorderLayout.CENTER);
		add(btnExit, BorderLayout.SOUTH);
		setBounds(250, 250, 250, 200);
		setVisible(false);
		chatStart();// 채팅 메소드 시작

	}

	public void chatStart() {
		try {
			ss = new ServerSocket(5005);
			while (true) {
				sockClient = ss.accept();// 클라이언트 접속때마다 소켓생성
				ta.append(sockClient.getInetAddress().getHostAddress() + " 접속됨\n");
				ChatHandle threadChat = new ChatHandle();//
				vChatList.add(threadChat);
				threadChat.start();// 쓰레드 시작
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

		BufferedReader br = null;// 입력
		PrintWriter pw = null;// 출력

		public ChatHandle() {
			try {
				InputStream is = sockClient.getInputStream();// 입력
				br = new BufferedReader(new InputStreamReader(is));

				OutputStream os = sockClient.getOutputStream();// 출력
				pw = new PrintWriter(new OutputStreamWriter(os));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void sendAllClient(String msg) {// 모든 사람에게 채팅내용을 전송
			try {
				int size = vChatList.size();// 채팅 사용자 숫자 얻기
				for (int i = 0; i < size; i++) {
					// 채팅 내용 보내기
					ChatHandle chr = (ChatHandle) vChatList.elementAt(i);
					chr.pw.println(msg);// 사용자 한사람 한사람 내용 전송
					chr.pw.flush();// 버퍼의내용을 비우며 즉시 전송
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				String name = br.readLine();
				sendAllClient(name + " 님 입장");
				while (true) {
					String msg = br.readLine();// 채팅 내용 받기
					String str = sockClient.getInetAddress().getHostName();
					ta.append(msg + "\n");// 채팅 내용을 ta서 보기
					// 종료
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
			} // 사용자 닉네임
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}