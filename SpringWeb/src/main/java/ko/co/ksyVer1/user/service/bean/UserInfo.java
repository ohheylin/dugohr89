package ko.co.ksyVer1.user.service.bean;


public class UserInfo {

	private String userid;
	private String password;
	private String name;
	private String email;
	private String role;

	public UserInfo() {
	}

	public UserInfo(VUserInfo vuserInfo) {
		this.userid = vuserInfo.getUserid();
		this.password = vuserInfo.getPassword();
		this.name = vuserInfo.getName();
		this.email = vuserInfo.getEmail();
		this.role = vuserInfo.getRole();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String eamil) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
