package bit.spring4.buzbee.util;

import java.util.*;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import bit.spring4.buzbee.model.MemberAndAuth;
import bit.spring4.buzbee.model.UserCustom;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private SqlSession sqlSession;

    boolean enabled = true;
    boolean accountNonExpired = true;
    boolean credentialsNonExpired = true;
    boolean accountNonLocked = true;
    
    public CustomUserDetailService() {}
    	  
    public CustomUserDetailService(SqlSessionTemplate sqlSession) {
    	this.sqlSession = sqlSession;
	}
  
	@Override
	public UserDetails loadUserByUsername(String username)
	   throws UsernameNotFoundException {
		MemberAndAuth member = null;
		
		if(username.contains("@")) {
			member = sqlSession.selectOne("bit.spring4.buzbee.model.Member.customLoginEmail", username);
		} else {
			member = sqlSession.selectOne("bit.spring4.buzbee.model.Member.customLogin", username);
		}
	    if(member == null) throw new UsernameNotFoundException(username);
	 
	    List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
	    
	    // if(member.getAuthority().equals("null")) throw new AuthenticationCredentialsNotFoundException("권한 부족");
	    
	    gas.add(new SimpleGrantedAuthority(member.getAuthority()));
	    return new UserCustom(member.getUsername()
                , member.getPassword()
                , enabled, accountNonExpired, credentialsNonExpired, accountNonLocked
                , gas
                , member.getM_no()
                , member.getM_name()
                , member.getM_email()
                , member.getM_profile()
                , member.getM_header()
                , member.getM_delete()
                , member.getM_rdate()
                , member.getM_pr()
                , member.getM_approval()
        );
	 }

	}
	 

