package login.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

public class LoginDAO {
	DataSource ds;
	LoginDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}
	}
	boolean check(HttpServletRequest request) {
		ResultSet rs = null;
		Connection con=null;
		PreparedStatement pstmt = null;
		try{
			HttpSession session = request.getSession();
	    	con = ds.getConnection();
	    	pstmt = con.prepareStatement(LoginSQL.CHECK);
	    	String email = request.getParameter("email");
	    	String pwd = request.getParameter("pwd");	
			pstmt.setString(1,email);
			rs = pstmt.executeQuery();	
				
			if(rs.next()) {
				if(rs.getString("pwd").equals(pwd)) {
					session.setAttribute("email",email);
					String MEM_NUM = rs.getString("mem_num");
					session.setAttribute("MEM_NUM",MEM_NUM);
					return true;
				}else return false;
			}else {
				System.out.println("불일치");
				return false;
			}
	    }catch(SQLException se) {
	    	System.out.println(se);
	    	return false;
		}finally {
			try {
				if(rs != null) rs.close();
				if(con !=null) con.close();
			}catch(SQLException se) {}
		}
	}
	boolean joincheck(HttpServletRequest request) {
		ResultSet rs = null;
		Connection con=null;
		PreparedStatement pstmt = null;
		try{
			HttpSession session = request.getSession();
	    	con = ds.getConnection();
	    	pstmt = con.prepareStatement(LoginSQL.JOINCHECK);
	    	String email = request.getParameter("email");
			pstmt.setString(1,email);
			rs = pstmt.executeQuery();	
			if(rs.next()) {
				System.out.println("중복된 아이디");
				return false;
			}else {
				System.out.println("아이디 중복안됨");
				return true;
			}
	    }catch(SQLException se) {
	    	System.out.println(se);
	    	return false;
		}finally {
			try {
				if(rs != null) rs.close();
				if(con !=null) con.close();
			}catch(SQLException se) {}
		}
	}
	void insert(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			System.out.println(request.getParameter("mem_region"));
			System.out.println(request.getParameter("age"));
			System.out.println(request.getParameter("email"));
			System.out.println(request.getParameter("nickname"));
			System.out.println(request.getParameter("pwd"));
			System.out.println(request.getParameter("gender"));
			System.out.println(request.getParameter("tendency"));
			
			con = ds.getConnection();
			pstmt = con.prepareStatement(LoginSQL.INSERT);
			pstmt.setString(1, request.getParameter("mem_region"));
			pstmt.setString(2, request.getParameter("age"));
			pstmt.setString(3, request.getParameter("email"));
			pstmt.setString(4, request.getParameter("nickname"));
			pstmt.setString(5, request.getParameter("pwd"));
			pstmt.setString(6, request.getParameter("gender"));
			pstmt.setString(7, request.getParameter("tendency"));
			pstmt.executeUpdate();
			
		}catch(SQLException se){
			System.out.println("insert:"+se);
		}finally{
			try{
			    if(pstmt != null) pstmt.close();
			    if(con != null) con.close();
			}catch(SQLException se){}
		}
	}
}
