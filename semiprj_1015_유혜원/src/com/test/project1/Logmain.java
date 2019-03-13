package com.test.project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Logmain extends JFrame implements ActionListener {

	public static void main(String[] args) {
		Logmain lm = new Logmain();
	}

	JPanel textpanel, buttonpanel;
	JLabel l_card, l_id, l_pass;
	JTextField tf_card, tf_id, tf_name, tf_Stime, tf_Ptime, tf_Rtime;
	JButton b_login, b_join;
	JPasswordField tf_pass;

	LogModel lmodel;
	boolean flag = false;
	boolean flag2 = false;

	public Logmain() {
		addLayout();// 화면구성
		evenProc();// 액션리스너
		connectDB();// db연결확인
	}

	private void connectDB() {
		try {
			lmodel = new LogModel();
			System.out.println("비디오 DB 연결성공");
		} catch (Exception e) {
			System.out.println("비디오 DB 연결실패");
			e.printStackTrace();
		}

	}

	private void evenProc() {
		b_login.addActionListener(this);
		b_join.addActionListener(this);
	}

	private void addLayout() {
		setLayout(new BorderLayout());
		setTitle("HaeWon PC방");
		setSize(400, 400);

		textpanel = new JPanel();
		textpanel.setLayout(new GridLayout(0, 2));

		l_card = new JLabel("                     비회원카드");
		l_id = new JLabel("                     ID");
		l_pass = new JLabel("                     비밀번호");

		tf_card = new JTextField();
		tf_id = new JTextField();
		tf_pass = new JPasswordField();
		tf_pass.setEchoChar('*');

		b_login = new JButton("로그인");
		b_join = new JButton("회원가입");

		textpanel.add(l_card);
		textpanel.add(tf_card);
		textpanel.add(l_id);
		textpanel.add(tf_id);
		textpanel.add(l_pass);
		textpanel.add(tf_pass);

		buttonpanel = new JPanel();
		buttonpanel.add(b_login);
		buttonpanel.add(b_join);

		add(textpanel, BorderLayout.CENTER);
		textpanel.setBackground(Color.PINK);
		add(buttonpanel, BorderLayout.SOUTH);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Join j = new Join();
		Loging lg = new Loging();

		Object evt = e.getSource();
		System.out.println("확인 0" + tf_id.getText());
		String str = tf_id.getText();
		String str2 = tf_card.getText();

		if (evt == b_login) {
			if (tf_card.getText().equals("")) {
				flag = logcheck();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "로그인이되었습니다");
					System.out.println("확인 1" + str);
					j.setM_id(str);
					try {
						Join j1 = lmodel.useInfo(j);
						useInfo(j1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					new Usemain(j);
					dispose();
				} else if (flag == false) {
					JOptionPane.showMessageDialog(null, "아이디와 패스워드가 일치하지않습니다");
				}

			} else if (!(tf_card.getText().equals(""))) {
				flag2 = logcheck2();
				if (flag2 == true) {
					JOptionPane.showMessageDialog(null, "로그인이되었습니다");
					lg.setU_card(str2);
					try {
						Loging lg1 = lmodel.useInfo1(lg);
						useInfo1(lg1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					new Usemain(lg);
					dispose();
				}else if (flag2 == false) {
					JOptionPane.showMessageDialog(null, "존재하지 않는 카드입니다.(1~20번)");
				}
			}
			insertLogin();
		} else if (evt == b_join) {
			insertJoin();
			Joinmain jm = new Joinmain();
		}
	}

	private void useInfo1(Loging lg1) {
		tf_card = new JTextField();

		tf_card.setText(lg1.getU_card());
	}

	// 비회원
	private boolean logcheck2() {
		boolean check = false;
		Loging lg = new Loging();

		lg.setU_card(tf_card.getText());

		try {
			lmodel = new LogModel();
			check = lmodel.checkUser2(lg, tf_card.getText());

			tf_card.setText(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	// 로그인
	private boolean logcheck() {
		boolean check = false;
		Join j = new Join();

		j.setM_id(tf_id.getText());
		j.setM_pass(tf_pass.getText());

		try {
			lmodel = new LogModel();
			check = lmodel.checkUser(j, tf_id.getText(), tf_pass.getText());

			tf_id.setText(null);
			tf_pass.setText(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return check;
	}

	private void useInfo(Join j1) {
		tf_name = new JTextField();
		tf_Stime = new JTextField();

		tf_name.setText(j1.getM_name());
		tf_Stime.setText(j1.getM_Stime());
	}

	public void insertJoin() {
		Join j = new Join();

	}

	public void insertLogin() {
		Loging lg = new Loging();

	}

}
