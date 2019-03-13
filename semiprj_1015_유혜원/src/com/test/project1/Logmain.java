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
		addLayout();// ȭ�鱸��
		evenProc();// �׼Ǹ�����
		connectDB();// db����Ȯ��
	}

	private void connectDB() {
		try {
			lmodel = new LogModel();
			System.out.println("���� DB ���Ἲ��");
		} catch (Exception e) {
			System.out.println("���� DB �������");
			e.printStackTrace();
		}

	}

	private void evenProc() {
		b_login.addActionListener(this);
		b_join.addActionListener(this);
	}

	private void addLayout() {
		setLayout(new BorderLayout());
		setTitle("HaeWon PC��");
		setSize(400, 400);

		textpanel = new JPanel();
		textpanel.setLayout(new GridLayout(0, 2));

		l_card = new JLabel("                     ��ȸ��ī��");
		l_id = new JLabel("                     ID");
		l_pass = new JLabel("                     ��й�ȣ");

		tf_card = new JTextField();
		tf_id = new JTextField();
		tf_pass = new JPasswordField();
		tf_pass.setEchoChar('*');

		b_login = new JButton("�α���");
		b_join = new JButton("ȸ������");

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
		System.out.println("Ȯ�� 0" + tf_id.getText());
		String str = tf_id.getText();
		String str2 = tf_card.getText();

		if (evt == b_login) {
			if (tf_card.getText().equals("")) {
				flag = logcheck();
				if (flag == true) {
					JOptionPane.showMessageDialog(null, "�α����̵Ǿ����ϴ�");
					System.out.println("Ȯ�� 1" + str);
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
					JOptionPane.showMessageDialog(null, "���̵�� �н����尡 ��ġ�����ʽ��ϴ�");
				}

			} else if (!(tf_card.getText().equals(""))) {
				flag2 = logcheck2();
				if (flag2 == true) {
					JOptionPane.showMessageDialog(null, "�α����̵Ǿ����ϴ�");
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
					JOptionPane.showMessageDialog(null, "�������� �ʴ� ī���Դϴ�.(1~20��)");
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

	// ��ȸ��
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

	// �α���
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
