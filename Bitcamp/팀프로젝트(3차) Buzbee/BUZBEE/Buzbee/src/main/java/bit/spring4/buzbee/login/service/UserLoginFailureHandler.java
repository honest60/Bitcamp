package bit.spring4.buzbee.login.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

// 로그인이 실패했을 때 처리 
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Override
	   public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
	         AuthenticationException exception) throws IOException, ServletException {
	      System.out.println("실패");
	      //request 영역에 변수 저장 
	      //forward
	      response.setContentType("text/html;charset=utf-8");
	      PrintWriter out = response.getWriter();
	      out.println("error");
	      out.flush();
	      out.close();
	      
	      //response.sendRedirect("login");
	   }
	
}
