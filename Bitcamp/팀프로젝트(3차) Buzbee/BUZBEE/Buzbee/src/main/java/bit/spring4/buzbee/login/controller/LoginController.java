package bit.spring4.buzbee.login.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import bit.spring4.buzbee.login.model.service.LoginService;
import bit.spring4.buzbee.model.Member;


@Controller
public class LoginController {
		
	@Autowired
	LoginService service;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	// *** 회원가입 페이지(view)  *** 
	@RequestMapping("/m/join")
	public String joinPage() {
		return "login/join";
	}
	
	// *** 회원가입 시 이메일 중복체크 *** 
	@ResponseBody
	@RequestMapping(value="/m/checkEmail", method = RequestMethod.POST)
	public void eCheck(String m_email, HttpServletResponse response) throws Exception {		
		service.emailService(m_email, response);
	}
	
	// *** 회원가입 및 권한 테이블 생성   *** 
	@RequestMapping(value="/m/join", method=RequestMethod.POST)
	public String joinPOST(Member member,RedirectAttributes rttr, String div, HttpServletResponse response) throws Exception {		
		String inputPass = member.getM_password();
		String pass = passwordEncoder.encode(inputPass);
		member.setM_password(pass);

		service.insertService(member, div, response);
		
		
		long auth = service.selectEmailService(member.getM_email());
		service.memberAuthService(auth);
		return "login/loginApproval";
	}
	
	// *** 이메일 재전송 *** 
	@RequestMapping(value = "/", method=RequestMethod.POST)
	public String mailApproval(Member member, String div, HttpServletResponse response) throws Exception{
		service.resend_mail(member, div, response);
		return "redirect:/";
		
	}
	
	// *** 회원 메일 인증  *** 
	@RequestMapping(value = "m/join/approval_member", method = RequestMethod.POST)
	public void approval_member(@ModelAttribute Member member, HttpServletResponse response) throws Exception{
		service.approval_member(member, response);
		
	}
	
	// *** 로그인 페이지 (view) *** 
	@RequestMapping("/m/login")
	public String login() {
		return "login/login";
	}

	// *** 비밀번호 찾기 페이지 (view)   *** 
	@RequestMapping(value = "/m/findPw")
	public String find_pw_form() throws Exception{
		return "login/findPw";
	}
	
	// *** 비밀번호 찾기 메일 발송  *** 
	@RequestMapping(value="/m/find_pw", method=RequestMethod.POST)
	public void find_pw(Member member, HttpServletResponse response) throws Exception{
		service.find_pw(response, member);
	}
	
	// *** 로그아웃  *** 
	@RequestMapping(value = "/m/logout", method=RequestMethod.GET)
	public String userLogout() { 
		System.out.println("로그아웃");
		return "login/join";
	}
	
	@RequestMapping(value = "/ajax/checkId", method=RequestMethod.GET)
	@ResponseBody
	public boolean checkId(String m_id) { 
		Member member = service.checkIdService(m_id);
		if(member != null) return true;
		return false;
	}
}
