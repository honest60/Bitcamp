package bit.spring4.buzbee.board.model.dao;

import java.util.List;
import java.util.Map;
import bit.spring4.buzbee.model.Board;
import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndBoard;
import bit.spring4.buzbee.model.TrendDTO;

public interface BoardDAO {
	List<MemberAndBoard> selectByIdMember(String id);
	List<MemberAndBoard> selectById(String id);
	List<MemberAndBoard> selectByNo(Map<String, Long> map);
	List<MemberAndBoard> selectByNoNotice(Map<String, Object> map);
	long insert(Board board);
	long insertReply(Board board);
	long countBuzzes(long m_no);
	long countFollower(long m_no);
	long countFollowing(long m_no);
	long countLikes(long m_no);
	List<String> followerOnline(long m_no);
	List<Member> recommend(long m_no);
	List<Member> recommend_user(long m_no);
	List<Long> board_like(long m_no);
	long board_reply(long b_no);
	List<MemberAndBoard> selectLikes(long m_no);
	List<Member> selectFollowing(long m_no);
	List<Member> selectFollow(long m_no);
	List<MemberAndBoard> selectByIdMember(Map<String, Long> map);
	void updateProfile(Map<String, Long> map);
	void updateHeader(Map<String, Long> map);
	void updateId(Map<String, Object> map);
	void updateContent(Map<String, Object> map);
	List<Member> selectSearchUser(Map<String, Object> map);
	List<MemberAndBoard> selectByYourIdMember(Map<String, Object> map);
	long pickM_no(String m_id);
	
	// for Ajax
	boolean insertLikes(Map<String, Long> map);
	boolean insertRebuz(Map<String, Long> map);
	boolean insertMention(Map<String, Long> map);
	List<MemberAndBoard> contents(long b_no);
	MemberAndBoard content(long b_no);
	void followingRecommend(Map<String, Long> map);
	void following(Map<String, Long> map);
	void unFollowing(Map<String, Long> map);
	int followingProfile(Map<String, Long> map);
	void deleteBoard(long b_no);
	void blockUser(Map<String, Long> map);
	
	// for Files
	long selectFileNoBySaveName(String f_savename);
	boolean insertFile(Map<String, Object> map);
	boolean insertBoardFile(Map<String, Long> map);
	List<String> selectFileByBNo(long b_no);
	String selectSavenameByFNo(long f_no);
	String selectProfileByMId(String m_id);
	
	// for Trend
	List<TrendDTO> selectTrend();
}
