package bit.spring4.buzbee.login.model.service;

import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndAuth;


public interface LoginService {
	void insertService(Member member,String div, HttpServletResponse response) throws Exception;
	void emailService(String m_email, HttpServletResponse response) throws Exception;
	long selectEmailService(String m_email);
	MemberAndAuth selectUserService(String username);
	
	// *** 이메일 인증 *** 
	String create_key() throws Exception;
	void send_mail(Member member, String div,  HttpServletResponse response) throws Exception;
	void approval_member(Member member, HttpServletResponse response) throws Exception;
	
	// *** 이메일 재인증 ***
	void resend_mail(Member member, String div, HttpServletResponse response) throws Exception;
 	
	// *** 비밀번호 찾기 ***
	void find_pw(HttpServletResponse response, Member member) throws Exception;
		
	
	Member selectByNoService(long m_no);
	Member selectByEmailService(String email);
	Member selectByIdService(String id);
	Member selectByIdService(Principal principal);
	long selectM_NOByIdService(String id);
	void memberAuthService(long m_no);
	
	
	Member checkIdService(String m_id);
}
