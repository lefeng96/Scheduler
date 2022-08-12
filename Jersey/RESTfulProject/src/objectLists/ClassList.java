package objectLists;

import java.util.List;

public class ClassList {
	private String subject;//Subject of the class
	private String classCode;//Subject acronym with the class number
	private String className;//Name of the class
	private int unit;//Number of units
	private String description;//A brief description of what the class is
	
	//certain endpoints can not use these because when they are called on from the prepared 
	//statement- the are retrieved in a list and we dont want multiple entries.
	//only Classes/classCode/{classCode} is the only endpoint to properly display the following
	//three values.
	private List<OfferedList> offered;//When the class is offered.
	private List<CorequisiteList> coreq;//A list of courses that can be taken concurrently
	private List<PrerequisiteList> prereq;//A list of courses that need to be taken before the class.
	

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode= classCode;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<OfferedList> getOffered() {
		return offered;
	}

	public void setOffered(List<OfferedList> offered) {
		this.offered = offered;
	}

	public List<CorequisiteList> getCoreq() {
		return coreq;
	}

	public void setCoreq(List<CorequisiteList> coreq) {
		this.coreq = coreq;
	}

	public List<PrerequisiteList> getPrereq() {
		return prereq;
	}

	public void setPrereq(List<PrerequisiteList> prereq) {
		this.prereq = prereq;
	}
	
}
