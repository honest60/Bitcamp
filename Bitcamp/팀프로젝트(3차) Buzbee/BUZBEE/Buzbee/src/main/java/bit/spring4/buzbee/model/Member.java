package bit.spring4.buzbee.model;

import lombok.Data;

@Data
public class Member {
	private long m_no;
	private String m_id;
	private String m_name;
	private String m_email;
	private String m_password;
	private long m_profile;
	private long m_header;
	private int m_delete;
	private String m_rdate;
	private String m_approval;
	private String m_pr;
	//additional
	private String profile_name;
	private String header_name;
	private int count;
	
	public Member() {}


	public Member(long m_no, String m_id, String m_name, String m_email, String m_password,
			long m_profile, long m_header, int m_delete, String m_rdate, String m_approval, String profile_name,
			String header_name, String m_pr) {
		super();
		this.m_no = m_no;
		this.m_id = m_id;
		this.m_name = m_name;
		this.m_email = m_email;
		this.m_password = m_password;
		this.m_profile = m_profile;
		this.m_header = m_header;
		this.m_delete = m_delete;
		this.m_rdate = m_rdate;
		this.m_approval = m_approval;
		this.m_pr = m_pr;
		this.profile_name = profile_name;
		this.header_name = header_name;
	}

}
