package soo.mvc.model;

import java.util.*;

public class ReplyMgr {
	private ReplyDAO dao;
	private static final ReplyMgr INSTANCE = new ReplyMgr();
	
	private ReplyMgr() {
		dao = new ReplyDAO();
	}
	public static ReplyMgr getInstance() {
		return INSTANCE;
	}
	public ArrayList<ReplyDTO> checkM(String MEM_NUM, String REG_NAME) {
		System.out.println("mgr¿¡¼­ checkMµé¾î¿È");
		return dao.check(MEM_NUM, REG_NAME);
	}
	public void storyM(String story, String MEM_NUM, String REG_NAME){
		dao.story(story, MEM_NUM, REG_NAME);
	}
}
