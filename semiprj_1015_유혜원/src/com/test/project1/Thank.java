package com.test.project1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Thank extends JFrame implements ActionListener{
	JPanel t,b_t;
	JLabel t_you;
	JButton ok;
	
	public Thank() {
		setLayout(new BorderLayout());
		setSize(500, 100);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		t=new JPanel();
		t_you=new JLabel("♡♥즐거운 시간되셧나요? 다음에 또 이용해주세요♥♡");
		ok=new JButton("확인");
		ok.addActionListener(this);
		
		t.add(t_you);
		b_t=new JPanel();
		b_t.add(ok);
		
		add(t, BorderLayout.CENTER);
		add(b_t, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==ok) {
			System.exit(0);
		}
	}
}
