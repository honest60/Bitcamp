package bit.spring4.buzbee.model;

import lombok.Data;

@Data
public class Board {
	private long b_no;
	private long m_no;
	private String b_content;
	private long b_rebuz;
	private long b_like;
	private long b_ref;
	private String b_rdate;
	
	public Board() {}

	public Board(long b_no, long m_no, String b_content, long b_rebuz, long b_like, long b_ref,
			String b_rdate) {
		this.b_no = b_no;
		this.m_no = m_no;
		this.b_content = b_content;
		this.b_rebuz = b_rebuz;
		this.b_like = b_like;
		this.b_ref = b_ref;
		this.b_rdate = b_rdate;
	}
}
