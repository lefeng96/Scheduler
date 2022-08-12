package objectLists;


public class PathList {
	private int cin;//students CIN
	private String classCode;//course subject acronymn with the course number.
	private String semester;//What semester the course will be taken.
	private String year;//what year was the course be taken.
	
	//If the student has taken the course then this field will hold the grade.
	//Otherwise, the grade will be default in MySQL as - for incomplete.
	private String grade;
	
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public String getClassCode() {
		return classCode;
	}
	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	//For testing purposes.
	@Override
	public String toString() {
		return "PathsList[ cin = "+ cin + ", ClassCode = "+classCode+", semester="+semester+", year = " + year+ " ]";
	}
}
