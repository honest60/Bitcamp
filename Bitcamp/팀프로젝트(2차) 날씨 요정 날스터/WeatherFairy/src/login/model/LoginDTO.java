package login.model;

public class LoginDTO {
	private String mem_num;
	private String mem_region;
	private String age;
	private String email;
	private String nickname;
	private String pwd;
	private String gender;
	private String tendency;
	private LoginDTO(String mem_num, String mem_region, String age, String email, String nickname, String pwd,
			String gender, String tendency) {
		super();
		this.mem_num = mem_num;
		this.mem_region = mem_region;
		this.age = age;
		this.email = email;
		this.nickname = nickname;
		this.pwd = pwd;
		this.gender = gender;
		this.tendency = tendency;
	}
	public String getMem_num() {
		return mem_num;
	}
	public void setMem_num(String mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_region() {
		return mem_region;
	}
	public void setMem_region(String mem_region) {
		this.mem_region = mem_region;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getTendency() {
		return tendency;
	}
	public void setTendency(String tendency) {
		this.tendency = tendency;
	}
	
}
