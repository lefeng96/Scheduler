package algorithm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AvailableClasses {

	private List<String> current;
	private int year;
	List<String> classes;
	
	//overloaded constraint constructor
	public AvailableClasses(List<String> classtaken, int year, List<String> classes){
		this.current = classtaken;
		this.year = year;
		this.classes = classes;
	}
	
	//regular constructor
	public AvailableClasses(List<String> classtaken) {
		this.current = classtaken;
	}
	
	//This method takes in the arraylist of ClassInfo to remove classes that the student has already taken from an arraylist of all classes
	public List<String> checkAvailableClasses(List<String> allClasses, Map<String, ClassInfo> allClassInfo, String currSemester, int electiveUnits, boolean constraint) {
		//creates a set of all classes
		Set<String> setOfAvailableClasses = new HashSet<String>(allClasses);
		
		setOfAvailableClasses.removeAll(current);//removes all classes taken from the list of classes to have it only have available classes for student to take
		
		List<String> availableClasses = new ArrayList<>();
		//if student hasn't taken any classes, add classes that has no prerequisites into available class arraylist
		if(current.size() == 0) {
			for(String className: setOfAvailableClasses){
				if(allClassInfo.containsKey(className)) {
					ClassInfo classInfo = allClassInfo.get(className);
					if(classInfo.getSemester().contains(currSemester)) {
						if(classInfo.getPrerequisites() == null){
							availableClasses.add(className);
						}
					}
				}
			}
			
			//makes sure that if its not the right year and semester, then the constraint class is not in availibleClasses
			if(constraint){
				for(int i = 0; i < classes.size(); i = i + 3){
					if(!currSemester.equals(classes.get(i + 1)) || !Integer.toString(year).equals(classes.get(i + 2))){
						if(availableClasses.contains(classes.get(i))){
							availableClasses.remove(classes.get(i));
						}
					}
				}
			}
			return availableClasses;
		}
		//if student has taken classes
		for(String className: setOfAvailableClasses){
			if(allClassInfo.containsKey(className)) {
				ClassInfo classInfo = allClassInfo.get(className);
				if(classInfo.getSemester().contains(currSemester)) {
					if(classInfo.getPrerequisites() == null){
						availableClasses.add(className);
					}
					//if there's a prerequisite, check to see if the student has taken the classes needed to add class to available classes that user can take
					else if(classInfo.getPrerequisites() != null && current.containsAll(classInfo.getPrerequisites())) { 
						if(classInfo.isElective() && electiveUnits < 18){
							availableClasses.add(className);
						}
						else if(!classInfo.isElective()){
							availableClasses.add(className);
						}	
					}
				}
			}
		}
		
		//makes sure that if its not the right year and semester, then the constraint class is not in availibleClasses
		if(constraint){
			for(int i = 0; i < classes.size(); i = i + 3){
				if(!currSemester.equals(classes.get(i + 1)) || !Integer.toString(year).equals(classes.get(i + 2))){
					if(availableClasses.contains(classes.get(i))){
						availableClasses.remove(classes.get(i));
					}
				}
			}
		}
		
		return availableClasses;
	}	
}