package bit.spring4.buzbee.model;

import lombok.Data;

@Data
public class MemberAndAuth {
	private long m_no;
	private String username;
	private String m_name;
	private String m_email;
	private String password;
	private long m_profile;
	private long m_header;
	private int m_delete;
	private String m_pr;
	private String m_rdate;
	// AUTHORITY
	private int enabled;
	private String authority;
	private int m_approval;
	
	public MemberAndAuth() {}

	public MemberAndAuth(long m_no, String m_id, String m_name, String m_email, String m_password,
			long m_profile, long m_header, int m_delete, String m_rdate, int m_enabled, String a_auth, int m_approval) {
		this.m_no = m_no;
		this.username = m_id;
		this.m_name = m_name;
		this.m_email = m_email;
		this.password = m_password;
		this.m_profile = m_profile;
		this.m_header = m_header;
		this.m_delete = m_delete;
		this.m_rdate = m_rdate;
		this.enabled = m_enabled;
		this.authority = a_auth;
		this.m_approval = m_approval;
	}
}
