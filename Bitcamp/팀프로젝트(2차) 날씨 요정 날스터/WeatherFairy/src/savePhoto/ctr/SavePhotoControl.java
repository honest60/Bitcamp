package savePhoto.ctr;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FileRenamePolicy;

import savePhoto.model.SavePhotoDTO;
import savePhoto.model.SavePhotoMgr;

@SuppressWarnings("serial")
public class SavePhotoControl extends HttpServlet{		
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String m = request.getParameter("m");
		if(m != null) {
			if(m.equals("insert")) {
				insert(request, response);
			}else {
				form(request, response);
			} 
		}else {
			form(request, response);
		}
	}		

	String degreeC;
	String wind;
	String trait;
	String reg_name;
	private void form(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		degreeC = request.getParameter("DEGREEC");
		wind = request.getParameter("WIND");
		trait = request.getParameter("TRAIT");
		reg_name = request.getParameter("REGNAME");
		double floatWind = Double.parseDouble(wind);
		int intWind = (int)floatWind;
		System.out.println(intWind);
		wind = String.valueOf(intWind);		
		
		RequestDispatcher rd = request.getRequestDispatcher("savePhoto/savePhoto.jsp");
		rd.forward(request, response);
	}	
	
	File f;
	String realSaveDir = "C:\\NAA";
	int maxPostSize = 1*1024*1024; 
	String encoding = "utf-8";
	FileRenamePolicy policy = new DefaultFileRenamePolicy();	
	int refer;
	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			MultipartRequest mr = new MultipartRequest (request, realSaveDir,
					maxPostSize, encoding, policy);		
			///////////////////// 기온, 풍속, 특성, 시군구명, 회원번호 받아야 함
			//한번에 여러값을 받아야 함
			String top = "";
			String bottom = "";
			String touter = "";
			String etc = "";	
			String feeling="";
			String photoName = null;
			String MEM_NUM = mr.getParameter("MEM_NUM");
			String degreeC = mr.getParameter("DEGREEC");
			System.out.println(degreeC);
			String[] arrayTop = mr.getParameterValues("up");		
			String[] arrayBottom = mr.getParameterValues("down");
			String[] arrayTouter = mr.getParameterValues("outer");
			String[] arrayEtc = mr.getParameterValues("etc");
			String[] arrayFeeling = mr.getParameterValues("feeling");			
			photoName = mr.getFilesystemName("filename");		
			for(int i=0; i<arrayTop.length; i++) {
				if(i==0) {
					top = arrayTop[i];
				}else if(i>0) {
					top =  top + " " + arrayTop[i];
				}				
			}
			for(int i=0; i<arrayBottom.length; i++) {
				if(i==0) {
					bottom = arrayBottom[i];
				}else {
					bottom =  bottom + " " + arrayBottom[i];
				}				
			}
			for(int i=0; i<arrayTouter.length; i++) {
				if(i==0) {
					touter = arrayTouter[i];
				}else {
					touter =  touter + " " + arrayTouter[i];
				}				
			}
			for(int i=0; i<arrayEtc.length; i++) {
				if(i==0) {
					etc = arrayEtc[i];
				}else {
					etc =  etc + " " + arrayEtc[i];
				}				
			}
			for(int i=0; i<arrayFeeling.length; i++) {
				if(i==0) {
					feeling = arrayFeeling[i];
				}else {
					feeling =  feeling + " " + arrayFeeling[i];
				}				
			}
			f = new File(realSaveDir+"/"+photoName);		
			SavePhotoMgr mgr = SavePhotoMgr.getInstance();
			// 기온, 풍속, 특성, 날짜, 시군구명, 회원번호(현재 1번으로 함)			
			System.out.println("컨트롤 부분 degreeC: " + degreeC + ", wind: "+ wind + ", trait: " + trait +
					", reg_name: " + reg_name+  ", top: " + top + ", bottom: " + bottom +
					", touter: " + touter + ",etc: " + etc + ",feeling: " + feeling + 
					",photoName: " + photoName + "////////");
			SavePhotoDTO dto = new SavePhotoDTO(degreeC, wind, trait, null, reg_name, MEM_NUM, top,
			bottom, touter, etc, feeling, photoName);		
			mgr.insertM(dto);
			System.out.println("실행완료");
			response.sendRedirect("index.do");			
			
		}catch(IOException se) {	
			System.out.println("여기오류"+se);
		}					
	}
	
} 
