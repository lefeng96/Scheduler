package algorithm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainFile { 

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		//classes taken sent in
		List<String> classesTaken = new ArrayList<String>();
		//constraint classes or locked classes sent in
		List<String> constraintClasses = new ArrayList<String>();
		//auto set to false. front end would change
		boolean constraint = false;
		//set to 17 units to force 5 classes per semester. 14 for 4 classes and 11 for 3 classes
		int maxUnits = 17;
		
		//test data for taken classes
//		classesTaken.add("CS-1010");
//		classesTaken.add("ENGL-1010");
//		classesTaken.add("MATH-2110");
//		classesTaken.add("GE-D1");
//		classesTaken.add("GE-AMERICANINSTITUTION1");
		
		//test data for constraint classes
//		constraintClasses.add("ENGL-1010 Fall 2020");
//		constraintClasses.add("CS-4661 Spring 2022");
//		constraintClasses.add("GE-AMERICANINSTITUTION2 Fall 2018");
		
		
		//brings in the csv file and parses it in the FileInput Class
		FileInput f = new FileInput("Sample_Classes.csv");
		
		System.out.println("");
		
		if(!constraint){
			//sends the list of classes to the DisplayCLass Class
			DisplayClass DC = new DisplayClass(f.getListOfClassInfo(), classesTaken, maxUnits);
				
			//returns the list of semester courses
			List<SemesterCourses> sc = DC.Display(maxUnits);
			List<List<SemesterCourses>> listOfPaths = DC.getListOfPaths();
			
		}else{
			//ask if user would like to switch classes
			Constraint c = new Constraint(f.getListOfClassInfo(), classesTaken, maxUnits, constraintClasses);
			List<List<SemesterCourses>> listOfPaths = c.getList();
			
		}
	}
} 