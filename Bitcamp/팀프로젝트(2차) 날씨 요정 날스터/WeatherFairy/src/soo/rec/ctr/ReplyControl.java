package soo.rec.ctr;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;


import soo.mvc.model.ReplyDTO;
import soo.mvc.model.ReplyMgr;

@SuppressWarnings("serial")
public class ReplyControl extends HttpServlet {
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			if(m.equals("form")) {
				System.out.println("check�Ѿ");
				check(request, response);
			}else {
				story(request, response);///////���κ�����??????
			}
		} else {
			story(request, response);
			//response.sendRedirect("index.do");///////���κ�����??????
		}
	}
	private void check(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String MEM_NUM = request.getParameter("MEM_NUM");
		String REG_NAME = request.getParameter("REGNAME");
		System.out.println("MEM_NUM : "+MEM_NUM);
		ReplyMgr mgr = ReplyMgr.getInstance();
		ArrayList<ReplyDTO> list = mgr.checkM(MEM_NUM, REG_NAME);
		HttpSession session = request.getSession();
		session.setAttribute("MEM_NUM", MEM_NUM);
		request.setAttribute("REG_NAME", REG_NAME);
		request.setAttribute("list", list);
		System.out.println("�ٽ� ���ƿͼ� list����");
		//System.out.println(MEM_NUM+", "+DEGREEC);
		
		RequestDispatcher rd = request.getRequestDispatcher("reply.jsp");
		rd.forward(request, response);
	}
	private void story(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("story�� ����");
		String story = request.getParameter("story");
		String MEM_NUM = request.getParameter("MEM_NUM");
		String REG_NAME = request.getParameter("REGNAME");
		System.out.println("story : "+story+", MEM_NUM : "+MEM_NUM+", REG_NAME : "+REG_NAME);
		ReplyMgr mgr = ReplyMgr.getInstance();
		System.out.println("ReplyMgr�ν��Ͻ�������");
		mgr.storyM(story, MEM_NUM, REG_NAME);
		System.out.println("ReplyMgr storyM�ϰ� �ٽ� ���ƿ�");
		HttpSession session = request.getSession();
		session.setAttribute("story", story);
		request.setAttribute("MEM_NUM", MEM_NUM);
		request.setAttribute("REG_NAME", REG_NAME);
		//request.setAttribute("list", list);
		check(request, response);
		//RequestDispatcher rd = request.getRequestDispatcher("reply.jsp");
		//rd.forward(request, response);
	}

}
