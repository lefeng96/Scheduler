package objectLists;

//This class was going to be used for consuming json.
//This is how the json from the frontend should be recieved.
//To be used for editing and passing class info to the algorithm and
//used to save roadmap info to paths.
public class Roadmap {
	
	private String semester; //The Semester of the list Spring or Fall
	private int year; //The year for the list 
	private String[] classes; //The courses from that semester and year.
	
	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	public String[] getClasses() {
		return classes;
	}

	public void setClasses(String[] classes) {
		this.classes = classes;
	}
	
	//For testing purposes to see if the consuming json was parsable in this format and see if 
	//the information was correctly placed in the right format.
	@Override
	public String toString() {
		String courses= "";
		for(int i = 0;i<classes.length;i++) {
			courses += classes[i] +" / ";
		}
		return "Semester and Year: "+ semester +" "+ year + " taking classes " + courses;
	}
}
