package soo.mvc.model;

import java.sql.*;

public class RecDTO {
	private int DEGREEC;
	private int WIND;
	private String TRAIT;
	private Date TDATE;
	private String REG_NAME;
	private int MEM_NUM;
	private String TOP;
	private String BOTTOM;
	private String TOUTER;
	private String ETC;
	private String FEELING;
	
	public RecDTO() {}
	
	public RecDTO(Date TDATE, String TOP, String BOTTOM, String TOUTER, String ETC, String FEELING) {
		this.TDATE = TDATE;
		this.TOP = TOP;
		this.BOTTOM = BOTTOM;
		this.TOUTER = TOUTER;
		this.ETC = ETC;
		this.FEELING = FEELING;
	}
	
	public RecDTO(int dEGREEC, int wIND, String tRAIT, Date tDATE, String rEG_NAME, int mEM_NUM, String tOP,
			String bOTTOM, String tOUTER, String eTC, String fEELING) {
		super();
		DEGREEC = dEGREEC;
		WIND = wIND;
		TRAIT = tRAIT;
		TDATE = tDATE;
		REG_NAME = rEG_NAME;
		MEM_NUM = mEM_NUM;
		TOP = tOP;
		BOTTOM = bOTTOM;
		TOUTER = tOUTER;
		ETC = eTC;
		FEELING = fEELING;
	}
	
	public int getDEGREEC() {
		return DEGREEC;
	}
	public void setDEGREEC(int dEGREEC) {
		DEGREEC = dEGREEC;
	}
	public int getWIND() {
		return WIND;
	}
	public void setWIND(int wIND) {
		WIND = wIND;
	}
	public String getTRAIT() {
		return TRAIT;
	}
	public void setTRAIT(String tRAIT) {
		TRAIT = tRAIT;
	}
	public Date getTDATE() {
		return TDATE;
	}
	public void setTDATE(Date tDATE) {
		TDATE = tDATE;
	}
	public String getREG_NAME() {
		return REG_NAME;
	}
	public void setREG_NAME(String rEG_NAME) {
		REG_NAME = rEG_NAME;
	}
	public int getMEM_NUM() {
		return MEM_NUM;
	}
	public void setMEM_NUM(int mEM_NUM) {
		MEM_NUM = mEM_NUM;
	}
	public String getTOP() {
		return TOP;
	}
	public void setTOP(String tOP) {
		TOP = tOP;
	}
	public String getBOTTOM() {
		return BOTTOM;
	}
	public void setBOTTOM(String bOTTOM) {
		BOTTOM = bOTTOM;
	}
	public String getTOUTER() {
		return TOUTER;
	}
	public void setTOUTER(String tOUTER) {
		TOUTER = tOUTER;
	}
	public String getETC() {
		return ETC;
	}
	public void setETC(String eTC) {
		ETC = eTC;
	}
	public String getFEELING() {
		return FEELING;
	}
	public void setFEELING(String fEELING) {
		FEELING = fEELING;
	}
}
