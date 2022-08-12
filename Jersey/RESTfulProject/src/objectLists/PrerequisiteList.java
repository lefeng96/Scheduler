package objectLists;

public class PrerequisiteList {

	private String course; //ClassList - classCode  (subject acronym with course number)
	private String prerequisite; //the course that be taken before taking this course.
	
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getPrerequisite() {
		return prerequisite;
	}
	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}
}
