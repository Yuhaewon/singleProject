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

	JButton btn_exit; // 종료 버튼
	JButton btn_send,btn_join; // 전송 버튼

	JTextArea txt_list; // 채팅 내용 보여주는 부분

	JTextField txt_input; // 채팅 입력 텍스트필드

	Socket client; // 소켓
	BufferedReader br; // 입력
	PrintWriter pw; // 출력
	String server_ip; // 서버의 IP 주소
	final int port = 5005; // 서버의 포트 번호
	JLabel txt_name;
	CardLayout cl;

	public ChatClient1() {
		setTitle("채팅 클라이언트 "); // 제목
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}// windowClosing
		});
		cl=new CardLayout();
		setLayout(cl);
		Panel connect = new Panel();
		connect.setLayout(new BorderLayout());
		btn_join=new JButton("채팅 접속");
		btn_join.addActionListener(this);
		connect.add(btn_join);
		
		
		JPanel chat = new JPanel();
		chat.setLayout(new BorderLayout());
		txt_list = new JTextArea(); // 채팅 내용 보여주는 부분
		txt_input = new JTextField("", 15);// 채팅 입력
		txt_name = new JLabel("이용자");
		btn_exit = new JButton("종료"); // 종료 버튼 생성
		btn_send = new JButton("전송");
		btn_exit.addActionListener(this); // 종료 버튼에 감지기 붙이기
		btn_send.addActionListener(this); // 채팅 전송 버튼에 감지기 붙이기
		txt_input.addActionListener(this); // 채팅 입력란에 감지기 붙이기

		JPanel chat_sub = new JPanel(); // 채팅창 하위 패널
		chat_sub.add(txt_input);
		chat_sub.add(btn_send);
		chat_sub.add(btn_exit);
		
		JLabel lblChatTitle = new JLabel("채팅 프로그램 v 1", JLabel.CENTER);
		chat.add(lblChatTitle, BorderLayout.NORTH);
		chat.add(txt_list, BorderLayout.CENTER);
		chat.add(chat_sub, BorderLayout.SOUTH);
		add(chat);
		setBounds(250, 250, 350, 300);
		setVisible(true); // 보이기
	}

	public void run() {
		try {
			client = new Socket(server_ip, port); // 소켓 생성(서버로 접속)
			InputStream is = client.getInputStream(); // 입력
			OutputStream os = client.getOutputStream(); // 출력
			br = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(new OutputStreamWriter(os));
			String msg1 = txt_name.getText();// 대화명 얻기
			pw.println(msg1);// 대화명 전송
			pw.flush(); // 즉시 전송
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
				cl.show(this, "채팅창");
			} else if (obj == btn_exit) {
				dispose(); // 프로그램 종료
			} else if (obj == btn_send || obj == txt_input) {
				String msg = txt_input.getText();// 채팅 입력
				pw.println(msg);// 채팅 내용 전송
				pw.flush();// 즉시 전송
				txt_input.setText("");
				txt_input.requestFocus();
			} // else if...
		} catch (Exception ex) {
			ex.printStackTrace();
		} // catch
	}// actionPerformed
	}

