package bit.spring4.buzbee.login.model.dao;


import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import bit.spring4.buzbee.model.Member;
import bit.spring4.buzbee.model.MemberAndAuth;

@Repository
public class LoginDAOImpl implements LoginDAO {
	@Autowired
	private SqlSession sqlSession;
	private String ns = "bit.spring4.buzbee.model.Member";
	
	// *** 회원 가입  ***
	@Override
	public void insertP(Member member){
		//System.out.println(member.toString());
		sqlSession.insert(ns +".insertP", member);
	}
	
	// *** 이메일 메일 인증 *** 
	@Override
	public int approval_member(Member member) throws Exception{
		System.out.println(member.getM_email());
		return sqlSession.update(ns+".approval_member", member);
	}
		
	// *** 이메일 중복 체크 *** 
	@Override
	public int checkEmail(String m_email) {
		return sqlSession.selectOne(ns+".checkEmail", m_email);
	}
		
	
	@Override
	public long selectEmail(String m_email) {
		return sqlSession.selectOne(ns+".selectEmail", m_email);
	}
	
	@Override
	public String selectId(String m_id) {
		return sqlSession.selectOne(ns+".selectId", m_id);
	}
	
	// *** 로그인 (시큐리티) ***
	@Override
	public MemberAndAuth selectUser(String username){
		return sqlSession.selectOne(ns+".customLogin", username);
	}
	
	
	// *** 비밀번호 찾기 ***
	@Override
	public int update_pw(Member member) throws Exception{
		return sqlSession.update(ns+".update_pw", member);
	}
	
	
	
	@Override
	public Member selectByNo(long m_no) {
		return sqlSession.selectOne(ns + ".selectByNo", m_no);
	}
	
	@Override
	public Member selectById(String id) {
		return sqlSession.selectOne(ns + ".selectById", id);
	}

	@Override
	public Member selectByEmail(String email) {
		return null;
	}
	
	@Override
	public long selectM_NOById(String id) {
		try {
			return sqlSession.selectOne(ns + ".selectM_NOById", id);
		} catch (Exception e) {
			return -1;
		}
	}
	
	@Override
	public void memberAuth(long m_no) {
		sqlSession.insert(ns+".memberAuth", m_no);
	}
	
	
	@Override
	public Member checkId(String m_id) {
		return sqlSession.selectOne(ns + ".checkId", m_id);
	}
}
