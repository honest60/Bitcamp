package bit.spring4.buzbee.board.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import bit.spring4.buzbee.board.model.service.BoardService;
import bit.spring4.buzbee.login.model.service.LoginService;
import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndBoard;
import bit.spring4.buzbee.model.MemberEtc;
import bit.spring4.buzbee.model.UserCustom;
import bit.spring4.buzbee.util.Path;

@Controller
public class BoardController {
	@Autowired
	private BoardService service;
	@Autowired
	private LoginService loginService; 
	
	@RequestMapping("/")
	public ModelAndView board(Principal principal) {
		ModelAndView mv = new ModelAndView();
					
		if(principal == null) {
			mv.setViewName("redirect:m/join");
			return mv;
		}
		
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!loginDTO.getAuthorities().iterator().next().toString().equals("ROLE_USER")) {
			mv.addObject("member", loginDTO);
			mv.setViewName("login/loginApproval");
			return mv;
		}
		
		MemberEtc etc = service.MemberEtcService(loginDTO.getM_no());
		List<MemberAndBoard> buzzing = service.selectByNoService(loginDTO.getM_no(), 1, 0);
		List<Member> recommend = service.recommendService(loginDTO.getM_no());
		for(int i = 0; i < buzzing.size(); i++) {
			List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
			if(saveFiles != null) {
				buzzing.get(i).setF_saveFiles(saveFiles);
			}
			buzzing.get(i).setImg_profile(service.selectSavenameByFNoService(buzzing.get(i).getM_profile()));
			buzzing.get(i).setImg_header(service.selectSavenameByFNoService(buzzing.get(i).getM_header()));
		}
		String profile = service.selectSavenameByFNoService(loginDTO.getM_profile());
		String header = service.selectSavenameByFNoService(loginDTO.getM_header());
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		
		mv.setViewName("board/board");
		mv.addObject("buzzing", buzzing);
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("recommend", recommend);
		mv.addObject("etc", etc);
		mv.addObject("trends", service.selectTrendService());
		return mv;
	}
	
	@RequestMapping("/{id}")
	public ModelAndView board(@PathVariable String id, Principal principal) { 
		ModelAndView mv = new ModelAndView();
		long m_no = -1;
		MemberEtc etc = null;
		System.out.println("id의 값은 " + id);
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("loginDTO.getM_no()의 값은 " + loginDTO.getM_no());
		if(id.equals("buzbee")) {
			etc = service.MemberEtcService(loginDTO.getM_no());
		} else {
			m_no = service.pickM_noService(id);
			etc = service.MemberEtcService(m_no);
		}
		
		List<MemberAndBoard> buzzing = null;
		if(id == loginDTO.getUsername()) {
			 buzzing = service.selectByIdMemberService(id, loginDTO.getM_no(), 1, 0);
		} else {
			 buzzing = service.selectByYourIdMemberService(id, 1, 0);
		}
		
		for(int i = 0; i < buzzing.size(); i++) {
			List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
			if(saveFiles != null) {
				buzzing.get(i).setF_saveFiles(saveFiles);
			}
		}
		try {
			m_no = loginService.selectM_NOByIdService(id);
			if(m_no == -1) {
				mv.setViewName("redirect:/");
				return mv;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Member member = loginService.selectByIdService(id);
		List<Member> recommend = service.recommendService(m_no);
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		String profile = service.selectSavenameByFNoService(member.getM_profile());
		String header = service.selectSavenameByFNoService(member.getM_header());
		mv.setViewName("board/profile");
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("buzzing", buzzing);
		mv.addObject("etc", etc);
		mv.addObject("recommend", recommend);
		mv.addObject("member", member);
		mv.addObject("trends", service.selectTrendService());
		return mv;		
	}
	
	@RequestMapping("/board/like")
	public ModelAndView like(Principal principal) {
		ModelAndView mv = new ModelAndView();
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long m_no = -1;
		
		List<MemberAndBoard> buzzing = service.selectLikesService(loginDTO.getM_no());
		for(int i = 0; i < buzzing.size(); i++) {
			System.out.println(buzzing.get(i));
			List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
			if(saveFiles != null) {
				buzzing.get(i).setF_saveFiles(saveFiles);
			}
		}
		
		try {
			m_no = loginService.selectM_NOByIdService(loginDTO.getUsername());
			if(m_no == -1) {
				mv.setViewName("redirect:/");
				return mv;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		MemberEtc etc = service.MemberEtcService(m_no);
		Member member = loginService.selectByIdService(loginDTO.getUsername());
		List<Member> recommend = service.recommendService(m_no);
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		
		String profile = service.selectSavenameByFNoService(member.getM_profile());
		String header = service.selectSavenameByFNoService(member.getM_header());

		mv.setViewName("board/like");
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("buzzing", buzzing);
		mv.addObject("etc", etc);
		mv.addObject("recommend", recommend);
		mv.addObject("member", member);
		mv.addObject("trends", service.selectTrendService());
		return mv;
	}
	
	@RequestMapping("board/following")
	public ModelAndView follwoingPage() {
		ModelAndView mv = new ModelAndView();
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long m_no = -1;
		
		List<MemberAndBoard> buzzing = service.selectLikesService(loginDTO.getM_no());
		for(int i = 0; i < buzzing.size(); i++) {
			List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
			if(saveFiles != null) {
				buzzing.get(i).setF_saveFiles(saveFiles);
			}
		}
		
		try {
			m_no = loginService.selectM_NOByIdService(loginDTO.getUsername());
			if(m_no == -1) {
				mv.setViewName("redirect:/");
				return mv;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		MemberEtc etc = service.MemberEtcService(m_no);
		Member member = loginService.selectByIdService(loginDTO.getUsername());
		List<Member> recommend = service.recommendService(m_no);
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		

		List<Member> list = service.selectFollowing(m_no);
		
		String profile = service.selectSavenameByFNoService(member.getM_profile());
		String header = service.selectSavenameByFNoService(member.getM_header());

		mv.setViewName("board/following");
		mv.addObject("list", list);
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("buzzing", buzzing);
		mv.addObject("etc", etc);
		mv.addObject("recommend", recommend);
		mv.addObject("member", member);
		mv.addObject("trends", service.selectTrendService());
		return mv;
	}
	
	// follow
    @RequestMapping("board/follow")
    public ModelAndView followPage() {
       ModelAndView mv = new ModelAndView();
       UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       long m_no = -1;
       
       List<MemberAndBoard> buzzing = service.selectLikesService(loginDTO.getM_no());
       for(int i = 0; i < buzzing.size(); i++) {
          List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
          if(saveFiles != null) {
             buzzing.get(i).setF_saveFiles(saveFiles);
          }
       }
       
       try {
          m_no = loginService.selectM_NOByIdService(loginDTO.getUsername());
          if(m_no == -1) {
             mv.setViewName("redirect:/");
             return mv;
          }
       } catch(Exception e) {
          e.printStackTrace();
       }
       
       MemberEtc etc = service.MemberEtcService(m_no);
       Member member = loginService.selectByIdService(loginDTO.getUsername());
       List<Member> recommend = service.recommendService(m_no);
       
       for(int i = 0; i < recommend.size(); i++) {
          String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
          recommend.get(i).setProfile_name(member_profile);
       }
       

       List<Member> list = service.selectFollow(m_no);
       
       String profile = service.selectSavenameByFNoService(member.getM_profile());
       String header = service.selectSavenameByFNoService(member.getM_header());

       mv.setViewName("board/follow");
       mv.addObject("list", list);
       mv.addObject("profile", profile);
       mv.addObject("headerImg", header);
       mv.addObject("buzzing", buzzing);
       mv.addObject("etc", etc);
       mv.addObject("recommend", recommend);
       mv.addObject("member", member);
       mv.addObject("trends", service.selectTrendService());
       return mv;
    }
	
	@RequestMapping("/board/notice")
	public ModelAndView notice(Principal principal) {
		ModelAndView mv = new ModelAndView();
					
		if(principal == null) {
			mv.setViewName("redirect:m/join");
			return mv;
		}
		
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!loginDTO.getAuthorities().iterator().next().toString().equals("ROLE_USER")) {
			mv.addObject("member", loginDTO);
			mv.setViewName("login/loginApproval");
			return mv;
		}
		
		MemberEtc etc = service.MemberEtcService(loginDTO.getM_no());
		List<MemberAndBoard> buzzing = service.selectByNoNoticeService(loginDTO.getM_no(), 1, 0);
		List<Member> recommend = service.recommendService(loginDTO.getM_no());
		for(int i = 0; i < buzzing.size(); i++) {
			List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
			if(saveFiles != null) {
				buzzing.get(i).setF_saveFiles(saveFiles);
			}
			buzzing.get(i).setImg_profile(service.selectSavenameByFNoService(buzzing.get(i).getM_profile()));
			buzzing.get(i).setImg_header(service.selectSavenameByFNoService(buzzing.get(i).getM_header()));
		}
		String profile = service.selectSavenameByFNoService(loginDTO.getM_profile());
		String header = service.selectSavenameByFNoService(loginDTO.getM_header());
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		
		mv.setViewName("board/notice");
		mv.addObject("buzzing", buzzing);
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("recommend", recommend);
		mv.addObject("etc", etc);
		mv.addObject("trends", service.selectTrendService());
		return mv;
	}
	
	@RequestMapping("/board/setting")
	public ModelAndView setting(Principal principal) {
		ModelAndView mv = new ModelAndView();
		
		if(principal == null) {
			mv.setViewName("redirect:m/join");
			return mv;
		}
		
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(!loginDTO.getAuthorities().iterator().next().toString().equals("ROLE_USER")) {
			mv.addObject("member", loginDTO);
			mv.setViewName("login/loginApproval");
			return mv;
		}
		
		MemberEtc etc = service.MemberEtcService(loginDTO.getM_no());
		List<MemberAndBoard> buzzing = service.selectByNoNoticeService(loginDTO.getM_no(), 1, 0);
		List<Member> recommend = service.recommendService(loginDTO.getM_no());
		for(int i = 0; i < buzzing.size(); i++) {
			List<String> saveFiles = service.selectFileByBNoService(buzzing.get(i).getB_no());
			if(saveFiles != null) {
				buzzing.get(i).setF_saveFiles(saveFiles);
			}
			buzzing.get(i).setImg_profile(service.selectSavenameByFNoService(buzzing.get(i).getM_profile()));
			buzzing.get(i).setImg_header(service.selectSavenameByFNoService(buzzing.get(i).getM_header()));
		}
		String profile = service.selectSavenameByFNoService(loginDTO.getM_profile());
		String header = service.selectSavenameByFNoService(loginDTO.getM_header());
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		
		mv.setViewName("board/setting");
		mv.addObject("buzzing", buzzing);
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("recommend", recommend);
		mv.addObject("etc", etc);
		mv.addObject("trends", service.selectTrendService());
		return mv;
	}
	
	@RequestMapping("/board/setting-confirm")
	public String setting_comfirm(MultipartFile profileImg, MultipartFile headerImg, String m_id, String m_content) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long m_no = loginDTO.getM_no();
		
		if(!profileImg.getOriginalFilename().equals("")) {
			String fileName = profileImg.getOriginalFilename();
	    	int idx = fileName.lastIndexOf(".");
	    	String fName = fileName.substring(0, idx);
	    	String fExt = fileName.substring(idx+1);
	    	
	    	File file = new File(Path.IMG_STORE + fileName);

	        if(file.exists()) file = new File(Path.IMG_STORE + fName + "_" + System.currentTimeMillis() + "." + fExt);
	        
			try {
				profileImg.transferTo(file);
			} catch (IOException ie) {ie.printStackTrace();}
			
			service.insertFileService(fileName, file.getName(), fExt, file.length());
			long f_no = service.selectFileNoBySaveNameService(file.getName());
			service.updateProfileService(m_no, f_no);
			loginDTO.setM_profile(f_no);
		}

		if(!headerImg.getOriginalFilename().equals("")) {
			String fileName = headerImg.getOriginalFilename();
	    	int idx = fileName.lastIndexOf(".");
	    	String fName = fileName.substring(0, idx);
	    	String fExt = fileName.substring(idx+1);
	    	
	    	File file = new File(Path.IMG_STORE + fileName);

	        if(file.exists()) file = new File(Path.IMG_STORE + fName + "_" + System.currentTimeMillis() + "." + fExt);
	        
			try {
				headerImg.transferTo(file);
			} catch (IOException ie) {ie.printStackTrace();}
			
			service.insertFileService(fileName, file.getName(), fExt, file.length());
			long f_no = service.selectFileNoBySaveNameService(file.getName());
			service.updateHeaderService(m_no, f_no);
			loginDTO.setM_header(f_no);
		}
		
		System.out.println(m_id);
		System.out.println(m_content);
		
		if(!m_id.equals("")) {
			service.updateIdService(m_no, m_id);
			loginDTO.setM_id(m_id);
		}
		
		if(!m_content.equals("")) {
			service.updateContentService(m_no, m_content);
			loginDTO.setM_pr(m_content);
		}
		
		return "redirect:/";
	}
	
	@RequestMapping("/search") 
	public ModelAndView search(Principal principal, HttpServletRequest request) { 
		String search = request.getParameter("search");
		System.out.println("search의 값: " + search);
		ModelAndView mv = new ModelAndView();
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberEtc etc = service.MemberEtcService(loginDTO.getM_no());
		System.out.println("loginDTO.getUsername()의 값: " + loginDTO.getUsername());
		long m_no = -1;
		System.out.println(4);
		if(search == "" | search == null) {
			System.out.println(5);
			String noSearch = "입력값 없음";
			System.out.println("search의 값: " + search);
			mv.addObject("noSearch", noSearch);
		} else {
			System.out.println(6);					
			List<Member> list = service.selectSearchUserService(search, 1, 0);
			System.out.println(7);
			if(list.size() == 0) {
				System.out.println(8);
				String noUser = "찾는 유저 없음";
				System.out.println(9);
				mv.addObject("noUser", noUser);
				System.out.println(10);
			}else {
				System.out.println(11);
				mv.addObject("list", list);
				System.out.println(12);
			}
		}
		System.out.println(13);
		try {
			m_no = loginService.selectM_NOByIdService(loginDTO.getUsername());
			if(m_no == -1) {
				mv.setViewName("redirect:/");
				return mv;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		Member member = loginService.selectByIdService(loginDTO.getUsername());
		List<Member> recommend = service.recommendService(m_no);
		
		for(int i = 0; i < recommend.size(); i++) {
			String member_profile = service.selectSavenameByFNoService(recommend.get(i).getM_profile());
			recommend.get(i).setProfile_name(member_profile);
		}
		String profile = service.selectSavenameByFNoService(member.getM_profile());
		String header = service.selectSavenameByFNoService(member.getM_header());
		mv.setViewName("board/search");
		mv.addObject("profile", profile);
		mv.addObject("headerImg", header);
		mv.addObject("etc", etc);
		mv.addObject("recommend", recommend);
		mv.addObject("member", member);
		mv.addObject("search", search);
		return mv;		
	}
	
	// for Ajax
	
	@RequestMapping("/ajax/likes")
	@ResponseBody
	public boolean likes(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.insertLikesService(loginDTO.getM_no(), b_no);
	}
	
	@RequestMapping("/ajax/content")
	@ResponseBody
	public List<MemberAndBoard> content(long b_no) {
		
		List<MemberAndBoard> contents = service.contentsService(b_no);
		for(MemberAndBoard content : contents) {
			List<String> saveFiles = service.selectFileByBNoService(content.getB_no());
			String profile = service.selectProfileByMIdService(content.getM_id());

			content.setImg_profile(profile);
			content.setF_saveFiles(saveFiles);
			System.out.println(content);
		}
		return contents;
	}
	
	@RequestMapping("/ajax/uploadFiles")
	@ResponseBody
	public long uploadFiles(@RequestParam("files") MultipartFile files) {
    	String fileName = files.getOriginalFilename();
    	int idx = fileName.lastIndexOf(".");
    	String fName = fileName.substring(0, idx);
    	String fExt = fileName.substring(idx+1);

    	File file = new File(Path.FILE_STORE + fileName);

        if(file.exists()) file = new File(Path.FILE_STORE + fName + "_" + System.currentTimeMillis() + "." + fExt);
        
		try {
			files.transferTo(file);
		} catch (IOException ie) {ie.printStackTrace();}
		
		service.insertFileService(fileName, file.getName(), fExt, file.length());
		long f_no = service.selectFileNoBySaveNameService(file.getName());
		
		return f_no;
	}
	
	@RequestMapping("/ajax/board")
	@ResponseBody
	public List<MemberAndBoard> updateList(Principal principal, long page, long newBuzzings) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberAndBoard> board = service.selectByNoService(loginDTO.getM_no(), page, newBuzzings);
		for(MemberAndBoard buzzing : board) {
			buzzing.setImg_profile(service.selectProfileByMIdService(buzzing.getM_id()));
		}
		return board;
	}
	
	@RequestMapping("/ajax/notice")
	@ResponseBody
	public List<MemberAndBoard> updateNotice(Principal principal, long page, long newBuzzings) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberAndBoard> board = service.selectByNoNoticeService(loginDTO.getM_no(), page, newBuzzings);
		for(MemberAndBoard buzzing : board) {
			buzzing.setImg_profile(service.selectProfileByMIdService(buzzing.getM_id()));
		}
		return board;
	}
	
	@RequestMapping(value = "/ajax/getProfile",  produces = "application/text; charset=utf8")
	@ResponseBody
	public String profile(String m_id) {
		return service.selectProfileByMIdService(m_id);
	}
	
	@RequestMapping("/ajax/rebuz")
	@ResponseBody
	public boolean rebuz(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return service.insertRebuzService(loginDTO.getM_no(), b_no);
	}
	
	@RequestMapping("/ajax/following")
	@ResponseBody
	public void following(long b_no) {
		service.followingService(b_no);
	}
	
	@RequestMapping("/ajax/followingProfile")
	@ResponseBody
	public int followingProfile(long m_no) {
		int i = service.followingProfileService(m_no);
		return i;		
	}
	
	@RequestMapping("/ajax/followingRecommend")
	@ResponseBody
	public void followingRecommend(long m_no) {
		service.followingRecommendService(m_no);
	}
		
	@RequestMapping("/ajax/unfollowing")
	@ResponseBody
	public void unfollowing(long b_no) {
		service.unFollowingService(b_no);
	}
	
	@RequestMapping("/ajax/deleteBoard")
	@ResponseBody
	public void deleteBoard(long b_no) {
		service.deleteBoardService(b_no);
	}
	
	@RequestMapping("/ajax/blockUser")
	@ResponseBody
	public void blockUser(String m_id, long m_no) {
		if(m_no == 0) service.blockUserService(loginService.selectByIdService(m_id).getM_no());
		else service.blockUserService(m_no);
	}
}
