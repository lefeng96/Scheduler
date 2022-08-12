package objectLists;

public class LoginList {
	private int cin;//The students CIN
	private String password;//The students password
	private String email;//The students email address
	
	//whether the student is an admin.
	//By default new students are not admins, only admins can create other admins.
	//Refer to roles.txt
	private boolean isAdmin;

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
