package objectLists;

public class CorequisiteList {
	private String course; //ClassList - classCode  (subject acronym with course number)
	private String corequisite; //the course that be taken concurrently with course.
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getCorequisite() {
		return corequisite;
	}
	public void setCorequisite(String corequisite) {
		this.corequisite = corequisite;
	}
}
