package bit.spring4.buzbee.login.model.dao;


import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndAuth;

public interface LoginDAO {
	void insertP(Member member);
	int checkEmail(String m_email);
	long selectEmail(String m_email);
	String selectId(String m_id);
	int approval_member(Member member) throws Exception;
	int update_pw(Member member) throws Exception;
	MemberAndAuth selectUser(String username);

	
	Member selectByNo(long m_no);
	Member selectByEmail(String email);
	Member selectById(String id);
	long selectM_NOById(String id);
	void memberAuth(long m_no);
	
	
	Member checkId(String m_id);
}
