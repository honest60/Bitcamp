package login.ctr;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import login.model.LoginMgr;

public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			if(m.equals("form")) {
				form(request, response);
			}else if(m.equals("out")) {
				out(request, response);
			}else if(m.equals("join")) {
				join(request, response);
			}else if(m.equals("check")) {
				check(request, response);
			}else if(m.equals("joincheck")) {
				joincheck(request, response);
			}else if(m.equals("error")) {
				error(request, response);
			}else {
				response.sendRedirect("index.do");
			}
		}else {
			response.sendRedirect("index.do");
		}
	}
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		rd.forward(request, response);
	}
	private void out(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		
		response.sendRedirect("index.do");
	}
	private void join(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("join.jsp");
		rd.forward(request, response);
	}
	private void check(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		LoginMgr mgr = LoginMgr.getInstance();	
		boolean flag = mgr.checkM(request);
		if(flag) {
			request.setAttribute("email",request.getParameter("email"));
			request.setAttribute("MEM_NUM",request.getParameter("mem_num"));
			RequestDispatcher rd = request.getRequestDispatcher("login/loginOk.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("email",request.getParameter("email"));
			RequestDispatcher rd = request.getRequestDispatcher("login/false.jsp");
			rd.forward(request, response);
		}
	}
	private void joincheck(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		LoginMgr mgr = LoginMgr.getInstance();	
		boolean flag = mgr.joincheckM(request);
		if(flag) {
			System.out.println("없는 아이디");
			LoginMgr mgr2 = LoginMgr.getInstance();
			mgr2.insertM(request);
			
			RequestDispatcher rd = request.getRequestDispatcher("login/joinok.jsp");
			rd.forward(request, response);
		}else {
			System.out.println("중복된 아이디");
			RequestDispatcher rd = request.getRequestDispatcher("login/joinfalse.jsp");
			rd.forward(request, response);
		}
	}
	private void error(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("login/error.jsp");
		rd.forward(request, response);
	}
}
