package login.model;

public class LoginSQL {
	final static String CHECK = "select * from MEMBERT where email=?";
	final static String JOINCHECK = "select * from MEMBERT where email=?";
	final static String INSERT = "insert into MEMBERT values(MEMBERT_SEQ.nextval,?,?,?,?,?,?,?)";
}
