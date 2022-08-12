package objectLists;

public class StudentList {
	private String fname; //Students first name
	private String lname; //Students last name
	private int cin; //Students CIN
	
	//Is the student an admin. By MySQL default all students are not.
	//Admins are special students.
	private boolean admin;

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
}
