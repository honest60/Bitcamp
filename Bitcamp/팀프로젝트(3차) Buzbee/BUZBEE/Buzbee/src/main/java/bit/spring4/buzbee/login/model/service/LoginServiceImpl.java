package bit.spring4.buzbee.login.model.service;

import java.io.PrintWriter;
import java.security.Principal;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import bit.spring4.buzbee.login.model.dao.LoginDAO;
import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndAuth;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginDAO dao;

	// *** 이메일 인증 ***
	@Override
	public String create_key() throws Exception{
		String key = "";
		Random rd = new Random();

		for (int i = 0; i < 8; i++) {
			key += rd.nextInt(10);
		}
		return key;
	}
		
	@Override
	public void approval_member(Member member, HttpServletResponse response) throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (dao.approval_member(member) == 0) { // 이메일 인증에 실패하였을 경우
			out.println("<script>");
			out.println("alert('잘못된 접근입니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		} else { // 이메일 인증을 성공하였을 경우
			out.println("<script>");
			out.println("alert('인증이 완료되었습니다. 로그인 후 이용하세요.');");
			out.println("location.href='../login';");
			out.println("</script>");
			out.close();
		}
	}
	
	// *** 회원가입 *** 
	@Override
	public void insertService(Member member, String div, HttpServletResponse response) throws Exception{
		member.setM_approval(create_key());
		dao.insertP(member);
		System.out.println("div" +div);
		System.out.println("************메일 보내기 처리중 *************");
		send_mail(member, "join", response);
	}
	
	// *** 이메일 재전송 ***
	@Override
	public 	void resend_mail(Member member, String div, HttpServletResponse response) throws Exception{
		System.out.println("이메일 재전송");
		send_mail(member, "join", response);
	}
	
	// *** 이메일 발송 ***
	@Override
	public void send_mail(Member member, String div, HttpServletResponse response) throws Exception{
		String charSet = "utf-8";
		String hostSMTP = "smtp.gmail.com";
		String hostSMTPid = "buzbee0829@gmail.com";
		String hostSMTPpwd = "java0829";
		
	// 보내는 사람 EMail, 제목, 내용
		String fromEmail="buzbee0829@gmail.com";
		String fromName = "버즈비(BUZBEE)";
		String subject = "";
		String msg = "";
		
	// 회원가입 메일 내용
		if(div.equals("join")) {
			subject = "[BUZBEE] 회원가입 인증 메일입니다.";
			msg += "<table width='636' cellpadding='0' cellspacing='0'border='0'>";
			msg += "<tbody><tr>";
			msg += "<td style='font:12px 돋움; color:#555;'>";
			msg += "<table width='628' style='vertical-align:top;'>";
			msg += "<tbody><tr>";
			msg += "<td width='50%'>";
			msg += "<a href='http://buzbee.ml:8080/buzbee/' target='_blank' rel='noopener noreferrer'>";
			msg += "<img src='https://t1.daumcdn.net/cfile/tistory/99AA6B4D5C6829DE0D?original' alt='버즈비[BUZBEE]' border='0' style='vertical-align:top;'></a>";
			msg += "</td>";
			msg += "<td style='text-align:right; vertical-align:bottom;'>";
			msg += "</td>";
			msg += "</tr>";
			msg += "</tbody></table>";
			msg += "<p style='margin:0 20px 20px 20px; padding:0;'>";
			msg += "<img src='https://t1.daumcdn.net/cfile/tistory/9990B34A5C68286718?original' alt='버즈비[BUZBEE] 회원가입 안내 > 이메일인증'></p>";
			msg += "<p style ='margin:0 40px 20px 40px; font:12px 돋움; line-height:20px; color:#555;'>";
			msg += "안녕하세요? 버즈비[BUZBEE]입니다.<br>";
			msg += "본 메일은 회원가입 진행 시 필요한 이메일인증 절차로, 입력하신 이메일 주소에 따라 발송되었습니다.";
			msg += "</p>";
			msg += "</td>";
			msg += "</tr>";
			msg += "</tbody></table>";
			msg += "<table width='628' style='vertical-align:top;'>";
			msg += "<td style='width:71%; margin: 40px 0 10px 0; font: 12px 돋움; line-height: 20px; color: #555; text-align: center;'>";
			msg += "<strong style='font-size: 14px; letter-spacing: -1px;'>아래 버튼을 누르시면 이메일인증이 완료됩니다.</strong><br>인증이 완료되면 로그인 페이지로 이동합니다.<br>";
			msg += "<form method='post' action='http://buzbee.ml:8080/buzbee/m/join/approval_member' target='_blank'>";
			msg += "<input type='hidden' name='m_email' value='" + member.getM_email() + "'>";
			msg += "<input type='hidden' name='m_approval' value='" + member.getM_approval() + "'>";
			msg += "<input type='image' src='https://t1.daumcdn.net/cfile/tistory/99EE583C5C6828670A?original'name='submit' width='336' height='50' alt='메일 인증하기' border='0' style='margin:10px 0 0 0;'></form><br/></td></table>";
			msg += "<table cellpadding='0' cellspacing='0' border='0' style='margin:0 20px 0 40px;'>";
			msg += "<tbody><tr>";
			msg += "<td style='font:12px 돋움; color:#266DAD; padding:40px 5px 10px 0;'>";
			msg += "이 메일은 발신 전용 메일 입니다.";
			msg += "</td>";
			msg += "</td>";
			msg += "</tr>";
			msg += "</tbody></table>";
			msg += "</td>";
			msg += "</tr>";
			msg += "</tbody></table>";	
		}else if(div.equals("find_pw")) {
			subject = "[BUZBEE] 임시 비밀번호 입니다.";
			msg += "<table width='636' cellpadding='0' cellspacing='0'border='0'>";
			msg += "<tbody><tr>";
			msg += "<td style='font:12px 돋움; color:#555;'>";
			msg += "<table width='628' style='vertical-align:top;";
			msg += "<tbody><tr>";
			msg += "<td width='50%'>";
			msg += "<a href='http://buzbee.ml:8080/buzbee/' target='_blank' rel='noopener noreferrer'>";
			msg += "<img src='https://t1.daumcdn.net/cfile/tistory/99AA6B4D5C6829DE0D?original' alt='버즈비[BUZBEE]' border='0' style='vertical-align:top;'></a>";
			msg += "</td>";
			msg += "<td style='text-align:right; vertical-align:bottom;'>";
			msg += "</td>";
			msg += "</tr>";
			msg += "</tbody></table>";
			msg += "<p style='margin:0 20px 20px 20px; padding:0;'>";
			msg += "<img src='https://t1.daumcdn.net/cfile/tistory/990BAF385C68FA230D?original' alt='버즈비[BUZBEE] 임시비밀번호 안내'></p>";
			msg += "</td></tr></tbody></table>";
			msg += "<table width='628' style='vertical-align:top;'>";
			msg += "<td style='width:71%; margin: 40px 0 10px 0; font: 12px 돋움; line-height: 20px; color: #555; text-align: center;'>";
			msg += "<strong style='font-size: 16px; letter-spacing: -1px;'>" + member.getM_id()+ "님의 임시 비밀번호 입니다.</strong>";
			msg += "<p style='font-size:14px; color:red;'> 임시비밀번호 : " + member.getM_password() + "</p> ";
			msg += "<br> 요청을 하지 않은 경우 이 이메일은 무시해주세요.<br>";
			msg += "</td></table>";
			msg += "</td>";
			msg += "</tr>";
			msg += "</tbody></table>";
		}

		// 받는 사람 E-Mail 주소
				String mail = member.getM_email();
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				try {
					HtmlEmail email = new HtmlEmail();
					email.setDebug(true);
					email.setCharset(charSet);
					email.setSSL(true);
					email.setHostName(hostSMTP);
					email.setSmtpPort(587);

					email.setAuthentication(hostSMTPid, hostSMTPpwd);
					email.setTLS(true);
					email.addTo(mail, member.getM_name());
					email.setFrom(fromEmail, fromName, charSet);
					email.setSubject(subject);
					email.setHtmlMsg(msg);
					email.send();
					
					if(div.equals("join")) {
						out.println("<script>");
						out.println("alert('인증 메일 발송완료');");
						out.println("location.href='/buzbee';");
						out.println("</script>");
						out.close();
					}
				} catch (Exception e) {
					System.out.println("메일발송 실패 : " + e);
					out.println("<script>");
					out.println("alert('인증 메일 발송실패');");
					out.println("location.href='/buzbee';");
					out.println("</script>");
					out.close();
				}		
	}
	
	// *** 비밀번호 재생성 및 메일 발송 ***
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public void find_pw(HttpServletResponse response, Member member) throws Exception{
		System.out.println("*************************1");
		response.setContentType("text/html;charset=utf-8");
		System.out.println(member);
		PrintWriter out = response.getWriter();
		// 아이디가 없으면
		if(dao.selectId(member.getM_id()) == null) {
			out.print("아이디가 없습니다.");
			out.close();
		}
		// 가입에 사용한 이메일이 아니면
		else if(!member.getM_email().equals(dao.selectUser(member.getM_id()).getM_email())) {
			out.print("잘못된 이메일 입니다.");
			out.close();
		}else {
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			
			member.setM_password(pw);
			// 비밀번호 변경
			String inputPass = member.getM_password();
			String pass = passwordEncoder.encode(inputPass);
			member.setM_password(pass);
			
			dao.update_pw(member);
			
			// 비밀번호 변경 메일 발송
			member.setM_password(pw);
			send_mail(member, "find_pw", response);
			
			out.print("이메일로 임시 비밀번호를 발송하였습니다.");
			out.close();
		}
	}
	
	@Override
	public long selectEmailService(String m_email) {
		return dao.selectEmail(m_email);
	}
	
	@Override
	public MemberAndAuth selectUserService(String username) {
		
		return dao.selectUser(username);
	}
	
	
	@Override
	public void emailService(String m_email, HttpServletResponse response) throws Exception {
		PrintWriter out = response.getWriter();
		out.println(dao.checkEmail(m_email));
		out.close();
	}
	
	@Override
	public Member selectByNoService(long m_no) {
		return dao.selectByNo(m_no);
	}
	
	
	@Override
	public Member selectByIdService(String id) {
		return dao.selectById(id);
	}
	
	
	@Override
	public Member selectByEmailService(String email) {
		return null;
	}
	
	
	@Override
	public Member selectByIdService(Principal principal) {
		String id = principal.getName();
		return dao.selectById(id);
	}
	
	@Override
	public long selectM_NOByIdService(String id) {
		return dao.selectM_NOById(id);
	}
	
	
	@Override
	public void memberAuthService(long m_no) {
		dao.memberAuth(m_no);
	}
	
	@Override
	public Member checkIdService(String m_id) {
		return dao.checkId(m_id);
	}
}
