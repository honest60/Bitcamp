package bit.spring4.buzbee.board.model.dao;

import java.util.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import bit.spring4.buzbee.model.Board;
import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndBoard;
import bit.spring4.buzbee.model.TrendDTO;
import bit.spring4.buzbee.model.UserCustom;

@Repository
public class BoardDAOImpl implements BoardDAO {
	@Autowired
	private SqlSession sqlSession;
	private String ns = "bit.spring4.buzbee.model.Board";
	private String nm = "bit.spring4.buzbee.model.Member";
	
	@Override 
	public List<MemberAndBoard> selectByIdMember(Map<String, Long> map) {
		return sqlSession.selectList(ns + ".selectByIdMember", map); 
	}
	
	@Override
	public List<MemberAndBoard> selectByIdMember(String m_id) {
		return sqlSession.selectList(ns + ".selectByIdMember", m_id);
	}
	
	@Override
	public List<MemberAndBoard> selectById(String m_id) {
		return sqlSession.selectList(ns + ".selectById", m_id);
	}
	
	@Override
	public List<MemberAndBoard> selectByNo(Map<String, Long> map) {
		return sqlSession.selectList(ns + ".selectByNo", map);
	}
	
	@Override
	public List<MemberAndBoard> selectByNoNotice(Map<String, Object> map) {
		return sqlSession.selectList(ns + ".selectByNoNotice", map);
	}
	
	@Override
	public long insert(Board board) {
		if(board.getB_content().equals("")) board.setB_content(" ");
		sqlSession.insert(ns + ".insert", board);
		return sqlSession.selectOne(ns + ".maxBoardNo", board.getM_no());
	}
	
	@Override
	public long insertReply(Board board) {
		if(board.getB_content().equals("")) board.setB_content(" ");
		board.setB_ref(sqlSession.selectOne(ns + ".boardRef", board.getB_ref()));
		sqlSession.insert(ns + ".insertReply", board);
		return sqlSession.selectOne(ns + ".maxBoardNo", board.getM_no());
	}
	
	@Override
	public long countBuzzes(long m_no) {
		return sqlSession.selectOne(ns + ".countBuzzes", m_no);
	}
	
	@Override
	public long countFollower(long m_no) {
		return sqlSession.selectOne(ns + ".countFollower", m_no);
	}
	
	@Override
	public long countFollowing(long m_no) {
		return sqlSession.selectOne(ns + ".countFollowing", m_no);
	}
	
	@Override
	public long countLikes(long m_no) {
		return sqlSession.selectOne(ns + ".countLikes", m_no);
	}
	
	@Override
	public List<String> followerOnline(long m_no) {
		return sqlSession.selectList(ns + ".followerOnline", m_no);
	}
	
	@Override
	public List<Long> board_like(long m_no) {
		return sqlSession.selectList(ns + ".board_like", m_no);
	}
	
	@Override
	public List<MemberAndBoard> selectLikes(long m_no) {
		return sqlSession.selectList(ns + ".selectLikes", m_no);
	}
	
	@Override
	public long board_reply(long b_no) {
		return sqlSession.selectOne(ns + ".countReply", b_no);
	}
	
	@Override
	public void updateProfile(Map<String, Long> map) {
		sqlSession.update(ns + ".updateProfile", map);
	}
	
	@Override
	public void updateHeader(Map<String, Long> map) {
		sqlSession.update(ns + ".updateHeader", map);
	}
	
	@Override
	public void updateId(Map<String, Object> map) {
		sqlSession.update(ns + ".updateId", map);
	}
	
	@Override
	public void updateContent(Map<String, Object> map) {
		sqlSession.update(ns + ".updateContent", map);
	}
	
	@Override
	public List<Member> selectSearchUser(Map<String, Object> map) {
		return sqlSession.selectList(nm + ".selectSearchUser", map);
	}
	
	@Override 
	public List<MemberAndBoard> selectByYourIdMember(Map<String, Object> map) {
		return sqlSession.selectList(ns + ".selectByYourIdMember", map); 
	} 
	
	@Override
	public long pickM_no(String m_id) {
		return sqlSession.selectOne(ns + ".pickM_no", m_id);
	}
	
	// following
	@Override
	public List<Member> selectFollowing(long m_no) {
		return sqlSession.selectList(ns+".following", m_no);
	}
	// follow
   @Override
   public List<Member> selectFollow(long m_no) {
      return sqlSession.selectList(ns+".follow", m_no);
   }
	
	// for Ajax
	@Override
	public boolean insertLikes(Map<String, Long> map) {
		try {
			sqlSession.insert(ns + ".insertLikes", map);
			sqlSession.update(ns + ".plusLikes", map);
			return true;
		} catch (Exception e) {
			sqlSession.insert(ns + ".deleteLikes", map);
			sqlSession.update(ns + ".minusLikes", map);
			return false;
		}
	}
	
	@Override
	public boolean insertRebuz(Map<String, Long> map) {
		try {
			sqlSession.insert(ns + ".insertRebuz", map);
			sqlSession.update(ns + ".plusRebuz", map);
			return true;
		} catch (Exception e) {
			sqlSession.insert(ns + ".deleteRebuz", map);
			sqlSession.update(ns + ".minusRebuz", map);
			return false;
		}
	}
	
	@Override
	public boolean insertMention(Map<String, Long> map) {
		int i = sqlSession.insert(ns + ".insertMention", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public List<MemberAndBoard> contents(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("m_no", loginDTO.getM_no());
		
		long b_ref = sqlSession.selectOne(ns + ".boardRef", b_no);
		List<MemberAndBoard> contents = sqlSession.selectList(ns + ".contents", b_ref);
		for(MemberAndBoard content : contents) {
			map.remove("b_no");
			map.put("b_no", content.getB_no());
			MemberAndBoard isFollowing = sqlSession.selectOne(ns + ".isFollowing", map);
			System.out.println(isFollowing);
			if(isFollowing != null) content.setFollowing(true);
		}
		return contents;
	}
	
	@Override
	public MemberAndBoard content(long b_no) {
		UserCustom loginDTO = (UserCustom)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("b_no", b_no);
		map.put("m_no", loginDTO.getM_no());
		
		MemberAndBoard content = sqlSession.selectOne(ns + ".content", b_no);
		MemberAndBoard isFollowing = sqlSession.selectOne(ns + ".isFollowing", map);
		if(isFollowing != null) content.setFollowing(true);
		
		return content;
	}
	
	@Override
	public void following(Map<String, Long> map) {
		sqlSession.insert(ns + ".insertFollowing", map);
		sqlSession.insert(ns + ".insertFollower", map);
	}
	
	@Override
	public void unFollowing(Map<String, Long> map) {
		sqlSession.delete(ns + ".deleteFollowing", map);
		sqlSession.delete(ns + ".deleteFollower", map);
	}
	
	@Override
	public void followingRecommend(Map<String, Long> map) {
		sqlSession.insert(ns + ".insertFollowingRecommend", map);
		sqlSession.insert(ns + ".insertFollowerRecommend", map);
	}
	
	@Override
	public List<Member> recommend(long m_no) {
		return sqlSession.selectList(ns + ".recommend", m_no);
	}
	
	@Override
	public List<Member> recommend_user(long m_no) {
		return sqlSession.selectList(ns + ".recommend_user", m_no);
	}
	
	@Override
	public int followingProfile(Map<String, Long> map) {
		int i = sqlSession.selectOne(ns + ".followingProfile", map);
		return i;
	}
	
	@Override
	public void deleteBoard(long b_no) {
		int i = sqlSession.delete(ns + ".deleteBoard", b_no);
	}
	
	@Override
	public void blockUser(Map<String, Long> map) {
		sqlSession.insert(ns + ".blockUser", map);
	}
	
	// for Files
	@Override
	public long selectFileNoBySaveName(String f_savename) {
		return sqlSession.selectOne(ns + ".selectFileNoBySaveName", f_savename);
	}
	
	@Override
	public boolean insertFile(Map<String, Object> map) {
		int i = sqlSession.insert(ns + ".insertFile", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public boolean insertBoardFile(Map<String, Long> map) {
		int i = sqlSession.insert(ns + ".insertBoardFile", map);
		return i > 0 ? true : false;
	}
	
	@Override
	public List<String> selectFileByBNo(long b_no) {
		return sqlSession.selectList(ns + ".selectFileByBNo", b_no);
	}
	
	@Override
	public String selectSavenameByFNo(long f_no) {
		return sqlSession.selectOne(ns + ".selectSavenameByFNo", f_no);
	}
	
	@Override
	public String selectProfileByMId(String m_id) {
		return sqlSession.selectOne(ns + ".selectProfileByMId", m_id);
	}
	
	// for Trend
	@Override
	public List<TrendDTO> selectTrend() {
		List<TrendDTO> list = sqlSession.selectList(ns + ".selectTrend");
		return list;
	}
}
