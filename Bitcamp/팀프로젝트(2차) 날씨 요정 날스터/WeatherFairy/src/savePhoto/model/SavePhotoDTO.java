package savePhoto.model;

import java.sql.Date;

public class SavePhotoDTO {	
	private String degreec;
	private String wind;
	private String trait;
	private Date tDate;
	private String reg_name;
	private String mem_num;
	private String top;
	private String bottom;
	private String touter;
	private String etc;
	private String feeling;
	private String photoName;
	public SavePhotoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SavePhotoDTO(String degreec, String wind, String trait, Date tDate, String reg_name, String mem_num, String top,
			String bottom, String touter, String etc, String feeling, String photoName) {
		super();
		this.degreec = degreec;
		this.wind = wind;
		this.trait = trait;
		this.tDate = tDate;
		this.reg_name = reg_name;
		this.mem_num = mem_num;
		this.top = top;
		this.bottom = bottom;
		this.touter = touter;
		this.etc = etc;
		this.feeling = feeling;
		this.photoName = photoName;
	}
	public String getDegreec() {
		return degreec;
	}
	public void setDegreec(String degreec) {
		this.degreec = degreec;
	}
	public String getWind() {
		return wind;
	}
	public void setWind(String wind) {
		this.wind = wind;
	}
	public String getTrait() {
		return trait;
	}
	public void setTrait(String trait) {
		this.trait = trait;
	}
	public Date gettDate() {
		return tDate;
	}
	public void settDate(Date tDate) {
		this.tDate = tDate;
	}
	public String getReg_name() {
		return reg_name;
	}
	public void setReg_name(String reg_name) {
		this.reg_name = reg_name;
	}
	public String getMem_num() {
		return mem_num;
	}
	public void setMem_num(String mem_num) {
		this.mem_num = mem_num;
	}
	public String getTop() {
		return top;
	}
	public void setTop(String top) {
		this.top = top;
	}
	public String getBottom() {
		return bottom;
	}
	public void setBottom(String bottom) {
		this.bottom = bottom;
	}
	public String getTouter() {
		return touter;
	}
	public void setTouter(String touter) {
		this.touter = touter;
	}
	public String getEtc() {
		return etc;
	}
	public void setEtc(String etc) {
		this.etc = etc;
	}
	public String getFeeling() {
		return feeling;
	}
	public void setFeeling(String feeling) {
		this.feeling = feeling;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	
	
}
	