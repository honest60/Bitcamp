package soo.mvc.model;

import java.sql.*;

public class ReplyDTO {
	private Date TDATE;
	private String MEM_NUM;
	private String REGION;
	private String COMMENTC;
	
	public ReplyDTO() {}
	public ReplyDTO(String COMMENTC, String MEM_NUM) {
		super();
		this.COMMENTC = COMMENTC;
		this.MEM_NUM = MEM_NUM;
	}
	
	public ReplyDTO(Date tDATE, String mEM_NUM, String rEGION, String cOMMENTC) {
		super();
		TDATE = tDATE;
		MEM_NUM = mEM_NUM;
		REGION = rEGION;
		COMMENTC = cOMMENTC;
	}
	
	public Date getTDATE() {
		return TDATE;
	}
	public void setTDATE(Date tDATE) {
		TDATE = tDATE;
	}
	public String getMEM_NUM() {
		return MEM_NUM;
	}
	public void setMEM_NUM(String mEM_NUM) {
		MEM_NUM = mEM_NUM;
	}
	public String getREGION() {
		return REGION;
	}
	public void setREGION(String rEGION) {
		REGION = rEGION;
	}
	public String getCOMMENTC() {
		return COMMENTC;
	}
	public void setCOMMENTC(String cOMMENTC) {
		COMMENTC = cOMMENTC;
	}	
}
