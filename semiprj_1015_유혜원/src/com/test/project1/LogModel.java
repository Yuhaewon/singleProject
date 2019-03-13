package com.test.project1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class LogModel {
	Connection con;
	boolean check;
	boolean info;
	
	public LogModel() throws Exception {
		con=DBcon.getConnection();
	}

	public void joMember(Join j) throws Exception {
		con.setAutoCommit(false);
		
		String sql="insert into member(m_name,m_id,m_pass,m_num,m_tell,m_mail) "
				+ "values(?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, j.m_name);
		ps.setString(2, j.m_id);
		ps.setString(3, j.m_pass);
		ps.setString(4, j.m_num);
		ps.setString(5, j.m_tell);
		ps.setString(6, j.m_mail);
		
		int r1=ps.executeUpdate();
		if (r1!=1) {
			con.rollback();
		}
		con.commit();
		ps.close();
		con.setAutoCommit(true);
	}

	public boolean checkUser(Join j, String id, String pass) throws Exception {
		System.out.println("check신호");
		String sql="select m_id,m_pass from member where m_id=? and m_pass=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setString(1, j.getM_id());
		ps.setString(2, j.getM_pass());
		System.out.println("select : "+sql);
		System.out.println("ID : " + id);  //신호확인용 문구
	    System.out.println("PW : " + pass); //신호확인용 문구
	    
	    ResultSet rs=ps.executeQuery();
	    if (rs.next()) {
			check=true;
		}else {
			check=false;
		}
		System.out.println("c2"+check);
		ps.close();
		return check;
	}

	public Join useInfo(Join j) throws Exception {
		Date date=new Date();
		String sql="select m_name,m_id from member where m_id="+j.getM_id();
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			j.setM_name(rs.getString("m_name"));
			j.setM_Stime(""+date);
		}
		ps.close();
		rs.close();
		return j;
	}

	public boolean checkUser2(Loging lg, String card) throws Exception {
		System.out.println("check신호");
		String sql="select card from unmember where card=?";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setString(1, lg.getU_card());
		System.out.println("select : "+sql);
		System.out.println("card : " + card);  //신호확인용 문구
	    
	    ResultSet rs=ps.executeQuery();
	    if (rs.next()) {
			check=true;
		}else {
			check=false;
		}
		System.out.println("c2"+check);
		ps.close();
		return check;
	}

	public Loging useInfo1(Loging lg1) throws Exception {
		Date date=new Date();
		String sql="select card from unmember where card="+lg1.getU_card();
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		while (rs.next()) {
			lg1.setU_card(rs.getString("card"));
			lg1.setU_Stime(""+date);
		}
		ps.close();
		rs.close();
		return lg1;
	}
}
