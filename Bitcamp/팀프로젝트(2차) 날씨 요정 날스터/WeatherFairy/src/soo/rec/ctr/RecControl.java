package soo.rec.ctr;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;
import soo.mvc.model.RecDTO;
import soo.mvc.model.RecMgr;

@SuppressWarnings("serial")
public class RecControl extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			if(m.equals("check")) {
				System.out.println("check�Ѿ");
				check(request, response);
			}else if(m.equals("form")){
				System.out.println("form�Ѿ");
				check(request, response);
			}else {
				response.sendRedirect("index.do");///////���κ�����??????
			}
		} else {
			response.sendRedirect("index.do");///////���κ�����??????
		}
	}
	private void check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String MEM_NUM = request.getParameter("MEM_NUM");
		String REG_NAME = request.getParameter("REGNAME");
		System.out.println("MEM_NUM : "+MEM_NUM);
		String DEGREEC = request.getParameter("DEGREEC");
		System.out.println("DEGREEC : "+DEGREEC);
		RecMgr mgr = RecMgr.getInstance();
		ArrayList<RecDTO> list = mgr.checkM(MEM_NUM, DEGREEC);
		ArrayList<RecDTO> list2 = mgr.otherM(MEM_NUM, DEGREEC);
		HttpSession session = request.getSession();
		session.setAttribute("MEM_NUM", MEM_NUM);
		request.setAttribute("REG_NAME", REG_NAME);
		request.setAttribute("DEGREEC", DEGREEC);
		request.setAttribute("list", list);
		request.setAttribute("list2", list2);
		System.out.println("�ٽ� ���ƿͼ� list����");
		//System.out.println(MEM_NUM+", "+DEGREEC);
		
		RequestDispatcher rd = request.getRequestDispatcher("cody.jsp");
		rd.forward(request, response);
	}
/*	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("cody.jsp");
		rd.forward(request, response);
	}*/
}
