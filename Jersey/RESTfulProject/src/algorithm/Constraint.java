package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Constraint {
	Scanner in = new Scanner(System.in);
	public List<List<SemesterCourses>> listOfPaths;
	
	public Constraint() {
		
	}
	
	@SuppressWarnings("unused")
	public Constraint(List<ClassInfo> classInfo, List<String> classesTaken, int maxUnits, List<String> lockedClasses){
		List<String> classes = new ArrayList<String>();
		//constraints are "name semester year" so we split them to make it easier 
		for(int i = 0; i < lockedClasses.size(); i++){
			String[] temp = lockedClasses.get(i).split(" ");
			classes.add(temp[0]);
			classes.add(temp[1]);
			classes.add(temp[2]);
		}
		
		//start the process by sending the data to displayClass
		DisplayClass DC = new DisplayClass(classInfo, classesTaken, true, classes, maxUnits);
		List<SemesterCourses> sc = DC.Display(maxUnits);
		listOfPaths = DC.getListOfPaths();
	}
	
	public List<List<SemesterCourses>> getList(){
		return listOfPaths;
	}
}