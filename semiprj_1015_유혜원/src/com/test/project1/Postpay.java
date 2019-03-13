
package com.test.project1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Postpay extends JFrame implements ActionListener{
	JPanel p,b_p;
	JLabel p_pay;
	JButton ok;
	
	public Postpay() {
		setLayout(new BorderLayout());
		setSize(500, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		p=new JPanel();
		p_pay=new JLabel("�ĺҷ� ��ȯ�Ǿ����ϴ�.�����Ƕ� ī���Ϳ��� �����ٶ��ϴ�");
		ok=new JButton("Ȯ��");
		ok.addActionListener(this);
		
		p.add(p_pay);
		b_p=new JPanel();
		b_p.add(ok);
		
		add(p,BorderLayout.CENTER);
		add(b_p, BorderLayout.SOUTH);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==ok) {
			setVisible(false);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					dispose();
				}
			});
		}
		
	}
}
