package bit.spring4.buzbee.board.model.service;

import java.util.List;
import java.util.Map;

import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndBoard;
import bit.spring4.buzbee.model.MemberEtc;
import bit.spring4.buzbee.model.TrendDTO;

public interface BoardService {
	List<MemberAndBoard> selectByIdMemberService(String id);
	List<MemberAndBoard> selectByIdService(String id);
	List<MemberAndBoard> selectByNoService(long m_no, long page, long newBuzzings);
	List<MemberAndBoard> selectByNoNoticeService(long m_no, long page, long newBuzzings);
	long insertService(long m_no, String b_content);
	long insertReplyService(long m_no, long b_ref, String b_content);
	MemberEtc MemberEtcService(long m_no);
	List<String> followerOnlineService(long m_no);
	List<Member> recommendService(long m_no);
	List<MemberAndBoard> selectLikesService(long m_no);
	List<Member> selectFollowing(long m_no);
	List<Member> selectFollow(long m_no);
	List<MemberAndBoard> selectByIdMemberService(String m_id, long my_no, long page, long newBuzzings);
	void updateProfileService(long m_no, long m_profile);
	void updateHeaderService(long m_no, long m_header);
	void updateIdService(long m_no, String m_id);
	void updateContentService(long m_no, String m_content);
	List<Member> selectSearchUserService(String search, long page, long newBuzzings);
	List<MemberAndBoard> selectByYourIdMemberService(String m_id, long page, long newBuzzings);
	long pickM_noService(String m_id);
	
	// for Ajax
	boolean insertLikesService(long m_no, long b_no);
	boolean insertRebuzService(long m_no, long b_no);
	boolean insertMentionService(long b_no, long m_no);
	List<MemberAndBoard> contentsService(long b_no);
	void followingRecommendService(long m_no);
	void followingService(long b_no);
	void unFollowingService(long b_no);
	int followingProfileService(long m_no);
	void deleteBoardService(long b_no);
	void blockUserService(long m_no);
	
	// for Files
	long selectFileNoBySaveNameService(String f_savename);
	boolean insertFileService(String f_name, String f_savename, String f_type, long f_size);
	boolean insertBoardFileService(long b_no, long f_no);
	List<String> selectFileByBNoService(long b_no);
	String selectSavenameByFNoService(long f_no);
	String selectProfileByMIdService(String m_id);
	
	// for Trend
	List<TrendDTO> selectTrendService();
}
