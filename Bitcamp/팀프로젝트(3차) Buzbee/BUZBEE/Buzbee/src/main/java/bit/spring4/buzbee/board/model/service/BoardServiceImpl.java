package bit.spring4.buzbee.board.model.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import bit.spring4.buzbee.board.model.dao.BoardDAO;
import bit.spring4.buzbee.login.model.dao.LoginDAO;
import bit.spring4.buzbee.model.Board;
import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndBoard;
import bit.spring4.buzbee.model.MemberEtc;
import bit.spring4.buzbee.model.TrendDTO;
import bit.spring4.buzbee.model.UserCustom;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardDAO dao;
	@Autowired
	private LoginDAO loginDao;
	private String baseUrl = "http://buzbee.ml:8080/buzbee/";
	
	@Override
	public List<MemberAndBoard> selectByIdMemberService(String m_id, long my_no, long page, long newBuzzings) {
		int ps = 50;		
		long startRow = (page - 1) * ps + 1 + newBuzzings;
		long endRow = ps * page + newBuzzings;		
		Map<String, Long> map = new HashMap<String, Long>();
		
		map.put("my_no", my_no);
		map.put("startRow", startRow);
		map.put("endRow", endRow);		
		List<MemberAndBoard> list = dao.selectByIdMember(map);
		
		long m_no = loginDao.selectM_NOById(m_id);
		
		href(list);
		for(MemberAndBoard board : list) likes(board, m_no);
		
		return list;
	}
	
	@Override
	public List<MemberAndBoard> selectByIdMemberService(String m_id) {
		List<MemberAndBoard> list = dao.selectByIdMember(m_id);
		long m_no = loginDao.selectM_NOById(m_id);
		
		href(list);
		for(MemberAndBoard board : list) likes(board, m_no);
		
		return list;
	}
	
	@Override
	public List<MemberAndBoard> selectByIdService(String m_id) {
		return dao.selectById(m_id);
	}
	
	@Override
	public List<MemberAndBoard> selectByNoService(long m_no, long page, long newBuzzings) {
		int ps = 20;
		long startRow = (page - 1) * ps + 1 + newBuzzings;
		long endRow = ps * page + newBuzzings;
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", m_no);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<MemberAndBoard> list = dao.selectByNo(map);
		for(MemberAndBoard board : list) likes(board, m_no);
		
		href(list);
		return list;
	}
	
	@Override
	public List<MemberAndBoard> selectByNoNoticeService(long m_no, long page, long newBuzzings) {
		int ps = 20;
		long startRow = (page - 1) * ps + 1 + newBuzzings;
		long endRow = ps * page + newBuzzings;
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String m_id = "%" + loginDTO.getUsername() + "%";
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", m_no);
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<MemberAndBoard> list = dao.selectByNoNotice(map);
		for(MemberAndBoard board : list) likes(board, m_no);
		
		href(list);
		return list;
	}
	
	@Override
	public long insertService(long m_no, String b_content) {
		Board board = new Board();
		board.setM_no(m_no);
		board.setB_content(b_content);
		return dao.insert(board);
	}
	
	@Override
	public long insertReplyService(long m_no, long b_ref, String b_content) {
		Board board = new Board();
		board.setM_no(m_no);
		board.setB_content(b_content);
		board.setB_ref(b_ref);
		return dao.insertReply(board);
	}
	
	@Override
	public MemberEtc MemberEtcService(long m_no) {
		long buzzes = dao.countBuzzes(m_no);
		long follower = dao.countFollower(m_no);
		long following = dao.countFollowing(m_no);
		long likes = dao.countLikes(m_no);
		
		MemberEtc memberEtc = new MemberEtc(buzzes, follower, following, likes);
		return memberEtc;
	}
	
	@Override
	public List<String> followerOnlineService(long m_no) {
		return dao.followerOnline(m_no);
	}
	
	@Override
	public List<Member> recommendService(long m_no) {
		List<Member> list = dao.recommend(m_no);
		if(list.size() == 0) {
			list = dao.recommend_user(m_no);
		}
		return list;
	}
	
	@Override
	public List<MemberAndBoard> selectLikesService(long m_no) {
		List<MemberAndBoard> boards = dao.selectLikes(m_no);
		for(MemberAndBoard board : boards) {
			href(board);
			likes(board, board.getM_no());
		}
		return boards;
	}
	
	@Override
	public void updateProfileService(long m_no, long m_profile) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", m_no);
		map.put("m_profile", m_profile);
		dao.updateProfile(map);
	}
	
	@Override
	public void updateHeaderService(long m_no, long m_header) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", m_no);
		map.put("m_header", m_header);
		dao.updateHeader(map);
	}
	
	@Override
	public void updateIdService(long m_no, String m_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", m_no);
		map.put("m_id", m_id);
		dao.updateId(map);
	}
	
	@Override
	public void updateContentService(long m_no, String m_content) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("m_no", m_no);
		map.put("m_content", m_content);
		dao.updateContent(map);
	}
	
	// Following
	@Override
	public List<Member> selectFollowing(long m_no) {
		List<Member> list = dao.selectFollowing(m_no);
		int i = 1;
		
		for(Member lists : list) {
			String profile = dao.selectSavenameByFNo(lists.getM_profile());
			String header = dao.selectSavenameByFNo(lists.getM_header());
			String m_pr = lists.getM_pr();
			
			if(m_pr != null) {
				if(m_pr.length() > 50) {
					m_pr = m_pr.substring(0, 50);
					m_pr += "...";
					lists.setM_pr(m_pr);
				}
			}
			
			lists.setCount(i);
			lists.setProfile_name(profile);
			lists.setHeader_name(header);
			i++;
			if(i==4) i=1;
		}
		return list;
	}
	
	//follow
   @Override
   public List<Member> selectFollow(long m_no) {
      List<Member> list = dao.selectFollow(m_no);
      int i = 1;
      
      for(Member lists : list) {
         String profile = dao.selectSavenameByFNo(lists.getM_profile());
         String header = dao.selectSavenameByFNo(lists.getM_header());
         lists.setCount(i);
         lists.setProfile_name(profile);
         lists.setHeader_name(header);
         i++;
         if(i==4) i=1;
      }
      return list;
   }
	
	// for Ajax
	@Override
	public boolean insertLikesService(long m_no, long b_no) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("b_no", b_no);
		map.put("m_no", m_no);
		return dao.insertLikes(map);
	}
	
	@Override
	public boolean insertRebuzService(long m_no, long b_no) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("b_no", b_no);
		map.put("m_no", m_no);
		return dao.insertRebuz(map);
	}
	
	@Override
	public boolean insertMentionService(long b_no, long m_no) {
		HashMap<String, Long> map = new HashMap<String, Long>();
		map.put("b_no",  b_no);
		map.put("m_no",  m_no);
		return dao.insertMention(map);
	}
	
	@Override
	public List<MemberAndBoard> contentsService(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberAndBoard> boards = dao.contents(b_no);
		for(MemberAndBoard board : boards) {
			href(board);
			likes(board, loginDTO.getM_no());
		}
		return boards;
	}
	
	@Override
	public void followingService(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberAndBoard board = dao.content(b_no);
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", loginDTO.getM_no());
		map.put("m_no2", board.getM_no());
		
		dao.following(map);
	}
	
	@Override
	public void unFollowingService(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberAndBoard board = dao.content(b_no);
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", loginDTO.getM_no());
		map.put("m_no2", board.getM_no());
		
		dao.unFollowing(map);
	}
	
	@Override
	public void followingRecommendService(long m_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", loginDTO.getM_no());
		map.put("m_no2", m_no);
		
		dao.followingRecommend(map);
	}
	
	@Override
	public int followingProfileService(long m_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long my_no = loginDTO.getM_no();
		
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("my_no", my_no);
		map.put("m_no", m_no);
		
		return dao.followingProfile(map);
	}
	
	@Override
	public void deleteBoardService(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(loginDTO.getUsername().equals(dao.content(b_no).getM_id())) dao.deleteBoard(b_no);
	}
	
	@Override
	public void blockUserService(long m_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", loginDTO.getM_no());
		map.put("m_no2", m_no);
		dao.blockUser(map);
	}
	
	// for Files
	@Override
	public long selectFileNoBySaveNameService(String f_savename) {
		return dao.selectFileNoBySaveName(f_savename);
	}
	
	@Override
	public boolean insertFileService(String f_name, String f_savename, String f_type, long f_size) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("f_name", f_name);
		map.put("f_savename", f_savename);
		map.put("f_type", f_type);
		map.put("f_size", f_size);
		
		return dao.insertFile(map);
	}
	
	@Override
	public boolean insertBoardFileService(long b_no, long f_no) {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("b_no", b_no);
		map.put("f_no", f_no);
		return dao.insertBoardFile(map);
	}
	
	@Override
	public List<String> selectFileByBNoService(long b_no) {
		return dao.selectFileByBNo(b_no);
	}
	
	@Override
	public String selectSavenameByFNoService(long f_no) {
		if(f_no == 0) return "64x64.png";
		return dao.selectSavenameByFNo(f_no);
	}
	
	@Override
	public String selectProfileByMIdService(String m_id) {
		String profile = dao.selectProfileByMId(m_id);
		if(profile == null) return "64x64.png";
		return profile;
	}
	
	@Override
	public List<Member> selectSearchUserService(String search, long page, long newBuzzings) {
		int ps = 50;		
		long startRow = (page - 1) * ps + 1 + newBuzzings;
		long endRow = ps * page + newBuzzings;		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("search", search);
		map.put("startRow", startRow);
		map.put("endRow", endRow);		
		List<Member> list = dao.selectSearchUser(map);
		
		//long m_no = loginDao.selectM_NOById(m_id);
		
		//href(list);
		//for(MemberAndBoard board : list) likes(board, m_no);
		
		return list;
		
		//return dao.selectSearchUser(m_name);
	}
	
	@Override
	public List<MemberAndBoard> selectByYourIdMemberService(String m_id, long page, long newBuzzings) {
		int ps = 50;		
		long startRow = (page - 1) * ps + 1 + newBuzzings;
		long endRow = ps * page + newBuzzings;		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);		
		List<MemberAndBoard> list = dao.selectByYourIdMember(map);
		
		long m_no = loginDao.selectM_NOById(m_id);
		
		href(list);
		for(MemberAndBoard board : list) likes(board, m_no);
		
		return list;
	}
	
	@Override
	public long pickM_noService(String m_id) {
		return dao.pickM_no(m_id);
	}
	
	// for Trend
	
	@Override
	public List<TrendDTO> selectTrendService() {
		return dao.selectTrend();
	}
	
	// additonal 
	
	private void href(List<MemberAndBoard> list) {
		for(int j = 0; j < list.size(); j++) {
			String contentOrg = list.get(j).getB_content();
			if(contentOrg == null) contentOrg="";
			if(contentOrg.contains("@")) {
				String[] ids = contentOrg.split("@");
				for(int i = 1; i < ids.length; i++) {
					String id = null;
					int idx = ids[i].indexOf(" ");
					
					if(idx == -1) id = ids[i].substring(0);
					else id = ids[i].substring(0, idx);
					
					Member member = loginDao.selectById(id);
					if(member != null) list.get(j).setB_content(list.get(j).getB_content().replace("@" + id, "<a href='"+ baseUrl + id+"'>@"+id+"</a>"));
				}
			}
		}
	}
	
	private void href(MemberAndBoard board) {
		String contentOrg = board.getB_content();
		if(contentOrg.contains("@")) {
			String[] ids = contentOrg.split("@");
			for(int i = 1; i < ids.length; i++) {
				String id = null;
				int idx = ids[i].indexOf(" ");
				if(idx == -1) id = ids[i].substring(0);
				else id = ids[i].substring(0, idx);
				
				Member member = loginDao.selectById(id);
				if(member != null) board.setB_content(board.getB_content().replace("@" + id, "<a href='"+ baseUrl + id+"'>@"+id+"</a>"));
			}
		}
	}
	
	private void likes(MemberAndBoard board, long m_no) {
		List<Long> likes = dao.board_like(m_no);
		long countReply = dao.board_reply(board.getB_no());
		if(likes.contains(board.getB_no())) board.setBoard_like(true);
		board.setReply_count(countReply);
	}
}