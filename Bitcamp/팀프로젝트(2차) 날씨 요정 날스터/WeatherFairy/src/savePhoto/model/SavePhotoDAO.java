package savePhoto.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

class SavePhotoDAO {
	DataSource ds;
	
	SavePhotoDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		}catch(NamingException ne) {
		}		
	}
	
	void insert(SavePhotoDTO dto) {				
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(SavePhoto_SQL.insert);		
			System.out.println(dto.getDegreec());
			pstmt.setString(1, dto.getDegreec());
			pstmt.setString(2, dto.getWind());
			pstmt.setString(3, dto.getTrait());
			pstmt.setString(4, dto.getReg_name());
			pstmt.setString(5, dto.getMem_num());
			pstmt.setString(6, dto.getTop());			
			pstmt.setString(7, dto.getBottom());
			pstmt.setString(8, dto.getTouter());
			pstmt.setString(9, dto.getEtc());
			pstmt.setString(10, dto.getFeeling());			
			pstmt.setString(11, dto.getPhotoName());
			
			pstmt.executeUpdate();
		}catch(SQLException se) {
			System.out.println("#insert"+se);
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(con != null) con.close();
			}catch(SQLException se) {
				System.out.println("finally"+se);
			}
		}
	}

}
