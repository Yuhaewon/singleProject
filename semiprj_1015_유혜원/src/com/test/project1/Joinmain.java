package com.test.project1;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Joinmain extends JFrame implements ActionListener{
	JPanel textpanel,buttonpanel;
	JLabel l_name,l_id,l_pass,l_mail,l_num,l_tell;
	JTextField tf_name,tf_id,tf_pass,tf_mail,tf_num,tf_tell;
	JButton b_ok,b_can;
	
	LogModel lmodel;
	
	public Joinmain() {
		
		setLayout(new BorderLayout());
		setSize(400, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		textpanel=new JPanel();
		textpanel.setLayout(new GridLayout(0, 2));
		
		l_name=new JLabel("                        성명");
		l_id=new JLabel("                      아이디");
		l_pass=new JLabel("                    비밀번호");
		l_mail=new JLabel("                        E-mail");
		l_num=new JLabel("               주민번호 앞6자리");
		l_tell=new JLabel("                      전화번호");
		
		tf_name=new JTextField();
		tf_id=new JTextField();
		tf_pass=new JTextField();
		tf_mail=new JTextField();
		tf_num=new JTextField();
		tf_tell=new JTextField();
		
		b_ok=new JButton("OK");
		b_can=new JButton("Cancel");
		b_ok.addActionListener(this);
		b_can.addActionListener(this);
		
		textpanel.add(l_name);
		textpanel.add(tf_name);
		textpanel.add(l_id);
		textpanel.add(tf_id);
		textpanel.add(l_pass);
		textpanel.add(tf_pass);
		textpanel.add(l_mail);
		textpanel.add(tf_mail);
		textpanel.add(l_num);
		textpanel.add(tf_num);
		textpanel.add(l_tell);
		textpanel.add(tf_tell);
		
		buttonpanel=new JPanel();
		buttonpanel.add(b_ok);
		buttonpanel.add(b_can);
		
//		setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		
		add(textpanel, BorderLayout.CENTER);
		add(buttonpanel, BorderLayout.SOUTH);
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource()==b_ok) {
			try {
				inMember();
				setVisible(false);
				addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent arg0) {
						dispose();
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if (arg0.getSource()==b_can) {
			setVisible(false);
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent arg0) {
					dispose();
				}
			});
		}
		
	}
	private void inMember(){
		Join j=new Join();
		j.setM_name(tf_name.getText());
		j.setM_id(tf_id.getText());
		j.setM_pass(tf_pass.getText());
		j.setM_num(tf_num.getText());
		j.setM_mail(tf_mail.getText());
		j.setM_tell(tf_tell.getText());
		
		
		try {
			lmodel=new LogModel();
			lmodel.joMember(j);
			JOptionPane.showMessageDialog(null, "회원가입 완료");
			tf_name.setText(null);
			tf_id.setText(null);
			tf_pass.setText(null);
			tf_num.setText(null);
			tf_mail.setText(null);
			tf_tell.setText(null);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "회원가입 실패");
			e.printStackTrace();
		}
	}

}
