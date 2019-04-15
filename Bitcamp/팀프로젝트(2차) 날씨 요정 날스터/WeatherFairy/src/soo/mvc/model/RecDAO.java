package soo.mvc.model;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import soo.mvc.model.RecDTO;

import java.sql.*;
import java.util.ArrayList;

class RecDAO {
	DataSource ds;
	RecDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("DB/itit");
		}catch(NamingException ne) {ne.printStackTrace();}
	}
	
	ArrayList<RecDTO> mycheck(String MEM_NUM, String DEGREEC) {
		System.out.println("DAO mycheck 접속 성공");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecDTO> list = new ArrayList<RecDTO>();
		String sql = "select * from CLOTHING_MATCH where DEGREEC<=?+1 and DEGREEC >=?-1 and MEM_NUM=?";

		try {
			con = ds.getConnection();
			System.out.println("dao connection 접속 성공");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, DEGREEC);
			System.out.println("dao DEGREEC1 주입 성공");
			pstmt.setString(2, DEGREEC);
			System.out.println("dao DEGREEC2 주입 성공");
			pstmt.setString(3, MEM_NUM);
			System.out.println("dao MEM_NUM 주입 성공");
			System.out.println(pstmt);
			rs = pstmt.executeQuery();
			System.out.println("dao query 접속 성공");
			int i = 0;
			while(rs.next()) {
				if(i>=3) break;
				System.out.println("dao rs next 내부 진입");
				String TOP = rs.getString("TOP");
				System.out.println("dao top 접속 성공");
				String BOTTOM = rs.getString("BOTTOM");
				String TOUTER = rs.getString("TOUTER");
				String ETC = rs.getString("ETC");
				String FEELING = rs.getString("FEELING");
				Date TDATE = rs.getDate("TDATE");
				list.add(new RecDTO(TDATE, TOP, BOTTOM, TOUTER, ETC, FEELING));
				System.out.println("dao list 접속 성공");
				i++;
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
	
	ArrayList<RecDTO> othercheck(String MEM_NUM, String DEGREEC) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecDTO> list = new ArrayList<RecDTO>();
		String sql = "select * from CLOTHING_MATCH where DEGREEC<=?+1 and DEGREEC >=?-1 and MEM_NUM!=?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, DEGREEC);
			pstmt.setString(2, DEGREEC);
			pstmt.setString(3, MEM_NUM);
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				if(i>=3) break;
				String TOP = rs.getString("TOP");
				String BOTTOM = rs.getString("BOTTOM");
				String TOUTER = rs.getString("TOUTER");
				String ETC = rs.getString("ETC");
				String FEELING = rs.getString("FEELING");
				Date TDATE = rs.getDate("TDATE");
				list.add(new RecDTO(TDATE, TOP, BOTTOM, TOUTER, ETC, FEELING));
				i++;
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

}
