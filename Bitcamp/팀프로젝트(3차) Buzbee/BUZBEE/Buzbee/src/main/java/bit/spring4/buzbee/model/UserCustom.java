package bit.spring4.buzbee.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserCustom extends User {
	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private long m_no;
	private String m_id;
	private String m_name;
	private String m_email;
	private String m_password;
	private long m_profile;
	private long m_header;
	private int m_delete;
	private String m_rdate;
	private String m_pr;
	private int m_approval;
	
	public UserCustom(String m_id, String m_pasword
            , boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked
            , Collection<? extends GrantedAuthority> authorities, long m_no, String m_name, String m_email,  long m_profile, long m_header, int m_delete, String m_rdate, String m_pr, int m_approval) {
		super(m_id, m_pasword, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.m_no = m_no;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_profile = m_profile;
		this.m_header = m_header;
		this.m_delete = m_delete;
		this.m_rdate = m_rdate;
		this.m_approval = m_approval;
		this.m_pr = m_pr;
	}
}
