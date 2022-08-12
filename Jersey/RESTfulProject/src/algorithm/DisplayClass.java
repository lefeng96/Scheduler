package algorithm;


import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class DisplayClass {

	List<ClassInfo> ClassList;
	List<String> classesTaken;
	Scanner in = new Scanner(System.in);
	List<List<SemesterCourses>> listOfPaths;
	int maxUnits;
	boolean constraint;
	List<String> classes;

	//overloaded constraint constructor
	public DisplayClass(List<ClassInfo> list, List<String> classesTaken, boolean constraint, List<String> classes, int maxUnits) {
		this.ClassList = list;
		this.classesTaken = classesTaken;
		this.maxUnits = maxUnits;
		this.constraint = constraint;
		this.classes = classes;
	}
	
	//regular constructor
	public DisplayClass(List<ClassInfo> list, List<String> classesTaken, int maxUnits) {
		this.ClassList = list;
		this.classesTaken = classesTaken;
		this.maxUnits = maxUnits;
	}
	
	public List<SemesterCourses> Display(int unitsMax) {
		
		HashMap<String, ClassInfo> map = new HashMap<>();
		
		//store the classinfo in the hashmap
		for(int i = 0; i < ClassList.size(); i++){
			map.put(ClassList.get(i).getName(), ClassList.get(i));
		}
		
		//create tree
		MakeTree mt = new MakeTree();
		
		if(constraint){
			mt.start(classesTaken, map, maxUnits, constraint, classes);
			List<SemesterCourses> sc = mt.start(classesTaken, map, maxUnits, constraint);
			listOfPaths = mt.getListOfPaths();
			return sc;
			
		}else{
			List<SemesterCourses> sc = mt.start(classesTaken, map, maxUnits, constraint);
			listOfPaths = mt.getListOfPaths();
			return sc;
		}
		
	}
	
	public List<List<SemesterCourses>> getListOfPaths() {
		return listOfPaths;
	}
}