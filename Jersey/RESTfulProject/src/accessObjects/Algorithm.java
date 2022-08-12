package accessObjects;
import java.util.*;

import algorithm.SemesterCourses;
import model.PathManager;
import objectLists.Semester;

//Used to test adding a list of courses at the same time would have been used to save roadmap.
//Used test data since but would have moved to using consume json data that would have been
//parsed into a list of course objects.
public class Algorithm {
	
	public Boolean insertClasses(String cin) throws Exception{
		Boolean check;
		try {
		//test data starts here
		List<Semester> semesters= new ArrayList<Semester>();
		Semester semester = new Semester();
		semester.setClassCode("CS 4961");
		semester.setSemester("Fall");
		semester.setYear("2019");
		semesters.add(semester);
		
		Semester semester1 = new Semester();
		semester1.setClassCode("CS 4962");
		semester1.setSemester("Spring");
		semester1.setYear("2020");
		
		semesters.add(semester1);
		//test data stops here
		
		
		//would have used a for loop to insert class data into the paths table.
		//this would reduce the time since we are making only one connection to the database instead of 2 - one from info and the other from manager classes.
		for(int i = 0; i<semesters.size();i++) {
//			System.out.println(semesters.size());// console TEST
			
			//holds the course information that will be added to the database.
			Semester holder = (Semester)semesters.get(i);
			
			//call the appropriate method from the path manager class
			PathManager projectManager = new PathManager();
//			System.out.println(holder.getClassCode()); //console TEST
			
			//send in the class that will be added and retrieve the status of insertion.
			//Change to a list and check so the user can know which courses were added and which had problems.
	        check = projectManager.newPath(cin,holder.getClassCode(),holder.getSemester(),holder.getYear());
		}
		
		}catch(Exception e){
			throw e;
		}
		if(check = true) {
			return check;
		}
		else {
			return false;
		}
	}
}