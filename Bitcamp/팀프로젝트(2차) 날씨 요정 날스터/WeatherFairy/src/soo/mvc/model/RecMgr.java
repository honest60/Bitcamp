package soo.mvc.model;

import java.util.*;


public class RecMgr {
	private RecDAO dao;
	private static final RecMgr INSTANCE = new RecMgr();
	
	private RecMgr() {
		dao = new RecDAO();
	}
	public static RecMgr getInstance() {
		return INSTANCE;
	}
	public ArrayList<RecDTO> checkM(String MEM_NUM, String DEGREEC) {
		System.out.println("mgr¿¡¼­ checkMµé¾î¿È");
		return dao.mycheck(MEM_NUM, DEGREEC);
	}
	public ArrayList<RecDTO> otherM(String MEM_NUM, String DEGREEC) {
		System.out.println("mgr¿¡¼­ otherMµé¾î¿È");
		return dao.othercheck(MEM_NUM, DEGREEC);
	}
}
