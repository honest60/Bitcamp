package soo.mvc.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

import java.util.Date;
import java.text.SimpleDateFormat;

class ReplyDAO {
	DataSource ds;
	ReplyDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {ne.printStackTrace();}
	}
	
	ArrayList<ReplyDTO> check(String MEM_NUM, String REG_NAME){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReplyDTO> list = new ArrayList<ReplyDTO>();
		
		String sql = "select COMMENTC, MEM_NUM from CHATBOARD where TDATE=? and REGION=? order by COMMENT_NUM";
		try {
			con = ds.getConnection();
			System.out.println("dao connection 받음");
			pstmt = con.prepareStatement(sql);
			String date = new SimpleDateFormat("yy-MM-dd").format(new Date());
			pstmt.setString(1, date);
			System.out.println("dao DEGREEC넣음");
			pstmt.setString(2, REG_NAME);
			System.out.println("dao REG_NAME 넣음");
			rs = pstmt.executeQuery();
			System.out.println("dao query실행함");
			
			while(rs.next()) {
				System.out.println("dao rs next 존재");
				String COMMENTC = rs.getString("COMMENTC");
				System.out.println("dao top 가져옴 존재");
				MEM_NUM = rs.getString("MEM_NUM");
				list.add(new ReplyDTO(COMMENTC, MEM_NUM));
				System.out.println("dao list에 넣음");
			}
			return list;
		}catch (SQLException se) {
			se.printStackTrace();
			return null;
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException se) {se.printStackTrace();}
		}
	}
	void story(String story,String MEM_NUM, String REG_NAME){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<ReplyDTO> list = new ArrayList<ReplyDTO>();
		String sql = "insert into CHATBOARD values(COMMENT_NUM_SEQ.nextval, TO_CHAR(SYSDATE, 'YYMMDD'), ?, ?, ?)";
		try {
			con = ds.getConnection();
			System.out.println("dao connection 받음");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, REG_NAME);
			pstmt.setString(2, story);
			pstmt.setString(3, MEM_NUM);
			System.out.println("dao REG_NAME 넣음");
			rs = pstmt.executeQuery();
			System.out.println("dao query실행함");
			
			/*
			while(rs.next()) {
				System.out.println("dao rs next 존재");
				String COMMENTC = rs.getString("COMMENTC");
				System.out.println("dao top 가져옴 존재");
				MEM_NUM = rs.getString("MEM_NUM");
				list.add(new ReplyDTO(COMMENTC, MEM_NUM));
				System.out.println("dao list에 넣음");
			}*/
		}catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			} catch (SQLException se) {se.printStackTrace();}
		}
	}
}
