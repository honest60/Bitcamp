package login.model;

import javax.servlet.http.HttpServletRequest;

public class LoginMgr {
	private LoginDAO dao;
	private static final LoginMgr instance = new LoginMgr(); //Singleton Object Model
	private LoginMgr(){
		dao = new LoginDAO();
	}
	public static LoginMgr getInstance() {
		return instance;
	}
	public boolean checkM(HttpServletRequest request) {
		return dao.check(request);		
	}
	public boolean joincheckM(HttpServletRequest request) {
		return dao.joincheck(request);		
	}
	public void insertM(HttpServletRequest request) {
		dao.insert(request);
	}
}
