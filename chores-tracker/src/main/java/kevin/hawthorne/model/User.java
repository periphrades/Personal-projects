package kevin.hawthorne.model;

public class User {
	
	private int userid;
	
	private String name;
	
	private String status;
	
	private String password;
	
	
	public User(String status) {
		this.status = status;
	}

	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
