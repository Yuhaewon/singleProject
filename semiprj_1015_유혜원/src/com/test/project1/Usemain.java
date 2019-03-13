package com.test.project1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Usemain extends JFrame implements ActionListener {
	JPanel textUpanel, buttonUpanel, textGetPanel;
	JLabel u_name, u_id, u_Stime, u_Ptime, u_Rtime;
	JTextField tf_name, tf_id, tf_Stime, tf_Ptime, tf_Rtime;
	JButton b_logout, b_postpay, b_message;
	

	 
	public Usemain(Join j) {
		
		setLayout(new BorderLayout());
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textUpanel = new JPanel();
		textGetPanel = new JPanel();
		textUpanel.setLayout(new GridLayout(5, 0));
		textGetPanel.setLayout(new GridLayout(5, 0));

		u_name = new JLabel("          ȸ���̸�");
		u_id = new JLabel("          ID");
		u_Stime = new JLabel("          ���۽ð�");
		u_Ptime = new JLabel("          ���ð�");
		u_Rtime = new JLabel("          �ܿ��ð�");

		tf_name = new JTextField(20);
		tf_id = new JTextField(20);
		tf_Stime = new JTextField(20);
		tf_Ptime = new JTextField(20);
		tf_Rtime = new JTextField(20);

		tf_name.setText(j.getM_name());
		tf_name.setEditable(false);
		tf_id.setText(j.getM_id());
		tf_id.setEditable(false);
		tf_Stime.setText(j.getM_Stime());
		tf_Stime.setEditable(false);

		Runnable task1 = () -> {
			int j2 = 0;
			for (int i = 0; i <= 1000; i++) {
				for (j2 = 1; j2 <= 59; j2++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tf_Ptime.setText(i + "��" + j2 + "��");
				}
			}
		};
		new Thread(task1).start();
		
		tf_Ptime.setEditable(false);
		tf_Rtime.setEditable(false);
		Runnable task2 = () -> {
			int j2 = 0;
			for (int i = 1; i >= 0; i--) {
				for (j2 = 59; j2 >= 0; j2--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tf_Rtime.setText(i + "��" + j2 + "��");
					if (i==0) {
						tf_Rtime.setForeground(Color.red);
					}
				}
			}
			JOptionPane.showMessageDialog(null, "���ð��� ����Ǿ� �ڵ����� �α׾ƿ��˴ϴ�.");
			System.exit(1);
		};
		new Thread(task2).start();

		b_logout = new JButton("�α׾ƿ�");
		b_postpay = new JButton("�ĺ���ȯ");
		b_message = new JButton("ȣ�� �� �޼���");
		b_logout.addActionListener(this);
		b_postpay.addActionListener(this);
		b_message.addActionListener(this);

		textUpanel.add(u_name);
		textUpanel.add(u_id);
		textUpanel.add(u_Stime);
		textUpanel.add(u_Ptime);
		textUpanel.add(u_Rtime);

		textGetPanel.add(tf_name);
		textGetPanel.add(tf_id);
		textGetPanel.add(tf_Stime);
		textGetPanel.add(tf_Ptime);
		textGetPanel.add(tf_Rtime);

		buttonUpanel = new JPanel();
		buttonUpanel.add(b_logout);
		buttonUpanel.add(b_postpay);
		buttonUpanel.add(b_message);

		add(textUpanel, BorderLayout.CENTER);
		add(textGetPanel, BorderLayout.EAST);
		add(buttonUpanel, BorderLayout.SOUTH);

	}
	public Usemain(Loging lg) {
		setLayout(new BorderLayout());
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		textUpanel = new JPanel();
		textGetPanel = new JPanel();
		textUpanel.setLayout(new GridLayout(5, 0));
		textGetPanel.setLayout(new GridLayout(5, 0));

		u_name = new JLabel("ȸ���̸�");
		u_name.setHorizontalAlignment(SwingConstants.CENTER);
		u_id = new JLabel("ID");
		u_id.setHorizontalAlignment(SwingConstants.CENTER);
		u_Stime = new JLabel("���۽ð�");
		u_Stime.setHorizontalAlignment(SwingConstants.CENTER);
		u_Ptime = new JLabel("���ð�");
		u_Ptime.setHorizontalAlignment(SwingConstants.CENTER);
		u_Rtime = new JLabel("�ܿ��ð�");
		u_Rtime.setHorizontalAlignment(SwingConstants.CENTER);

		tf_name = new JTextField(20);
		tf_id = new JTextField(20);
		tf_Stime = new JTextField(20);
		tf_Ptime = new JTextField(20);
		tf_Rtime = new JTextField(20);

		tf_name.setText("��ȸ��");
		tf_name.setEditable(false);
		tf_id.setText("ī���ȣ : "+lg.getU_card());
		tf_id.setEditable(false);
		tf_Stime.setText(lg.getU_Stime());
		tf_Stime.setEditable(false);

		Runnable task1 = () -> {
			int j2 = 0;
			for (int i = 0; i <= 1000; i++) {
				for (j2 = 1; j2 <= 59; j2++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tf_Ptime.setText(i + "��" + j2 + "��");
				}
			}
		};
		new Thread(task1).start();
		
		tf_Ptime.setEditable(false);
		tf_Rtime.setEditable(false);
		Runnable task2 = () -> {
			int j2 = 0;
			for (int i = 1; i >= 0; i--) {
				for (j2 = 59; j2 >= 0; j2--) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					tf_Rtime.setText(i + "��" + j2 + "��");
					if (i==0) {
						tf_Rtime.setForeground(Color.red);
					}
				}
			}
			JOptionPane.showMessageDialog(null, "���ð��� ����Ǿ� �ڵ����� �α׾ƿ��˴ϴ�.");
			System.exit(1);
		};
		new Thread(task2).start();

		b_logout = new JButton("�α׾ƿ�");
		b_postpay = new JButton("�ĺ���ȯ");
		b_message = new JButton("ȣ�� �� �޼���");
		b_logout.addActionListener(this);
		b_postpay.addActionListener(this);
		b_message.addActionListener(this);

		textUpanel.add(u_name);
		textUpanel.add(u_id);
		textUpanel.add(u_Stime);
		textUpanel.add(u_Ptime);
		textUpanel.add(u_Rtime);

		textGetPanel.add(tf_name);
		textGetPanel.add(tf_id);
		textGetPanel.add(tf_Stime);
		textGetPanel.add(tf_Ptime);
		textGetPanel.add(tf_Rtime);

		buttonUpanel = new JPanel();
		buttonUpanel.add(b_logout);
		buttonUpanel.add(b_postpay);
		buttonUpanel.add(b_message);

		add(textUpanel, BorderLayout.CENTER);
		add(textGetPanel, BorderLayout.EAST);
		add(buttonUpanel, BorderLayout.SOUTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b_logout) {
			Thank t = new Thank();
		} else if (e.getSource() == b_postpay) {
			Postpay pp = new Postpay();
		} else if (e.getSource() == b_message) {
			 ChatClient cc=new ChatClient(tf_name.getText());
		}
	}
}


