package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Combinations {
	
	int maxUnit = 0;
	List<String> classes;
	boolean constraint = false;
	
	//overloaded constraint constructor
	public Combinations(List<String> classes) {
		this.classes = classes;
		constraint = true;
	}
	
	//regular constructor
	public Combinations() {
		
	}

	public List<Node> findCombination(HashMap<String, ClassInfo> listOfClasses, List<String> available, int maxUnits, int numOfElectives) {
		
		maxUnit = maxUnits;
		List<Node> combClasses = new ArrayList<Node>();
		
		//goes through the size of the available list to see what possible combinations there are
		//ex. if avail = a, b, c, d then it has to find comb with size = 1: a, b, c, d. size = 2: ab, ac, ad, bc, ... 
		//size = 3: abc, acd, bcd, ... size = 4: abcd
		for(int i = 0; i < available.size(); i++){
			int sizeOfavailList = available.size();
			int sizeOfNextCombo = i+1;
			
			printCombination(listOfClasses, available, sizeOfavailList, sizeOfNextCombo, combClasses, numOfElectives);
		}
		
		//make sure the only classes in combclass include the constraint class
        if(constraint){
        	for(int i = 0; i < classes.size(); i = i + 3){
        		if(available.contains(classes.get(i))){
        			for(int j = 0; j < combClasses.size(); j++){
                		if(combClasses.get(j).getData().contains(classes.get(i))){
                			
                		}else{
                			combClasses.remove(j);
                			j--;
                		}
                    }
				}
        	}
        }
        
        //reverse the order of the list so the larger groupings of classes are first
        List<Node> classInfo2 = new ArrayList<>();
        for(int i = combClasses.size() - 1; i >= 0; i--){
        	classInfo2.add(combClasses.get(i));
        }
		
		return classInfo2;
	}

	//this method creates a new temp ArrayList to store the new combinations in
	public void printCombination(HashMap<String, ClassInfo> listOfClasses, List<String> available, int sizeOfavailList, int sizeOfNextCombo, List<Node> combClasses, int numOfElectives){
	
		List<String> tempCombo = new ArrayList<String>(sizeOfNextCombo);
    	for(int i = 0; i < sizeOfNextCombo; i++) {
    		//with out this line we get an index out of bounce exception
    		tempCombo.add(null);
    	}
    	
		//check to see if combo should be added to list
		storeCombinations(listOfClasses, available, tempCombo, 0, sizeOfavailList - 1, 0, sizeOfNextCombo, combClasses, numOfElectives);
	}
	
	public void storeCombinations(HashMap<String, ClassInfo> listOfClasses, List<String> available, List<String> tempCombo, int start, int end, int index, int sizeOfNextCombo,  List<Node> combClasses, int numOfElectives){
		
		//will start to add combinations to combClasses
		if (index == sizeOfNextCombo){
			
        	String temp = null;
        	List<String> tempList = new ArrayList<String>();
        	List <ClassInfo> classInfo = new ArrayList<ClassInfo>();
        	
            for (int j = 0; j < sizeOfNextCombo; j++){
               temp = tempCombo.get(j);
               tempList.add(temp);
   				classInfo.add(listOfClasses.get(temp));
            }
            
            //check to see if the combo fits in the desired unit preference
            int totalUnits = 0;
            for (int j = 0; j < classInfo.size(); j++){
            	if(classInfo.get(j).isElective()) {
            		numOfElectives += classInfo.get(j).getUnits();
            	}
            	
            	totalUnits += classInfo.get(j).getUnits();
            }
            
            if(maxUnit >= totalUnits && numOfElectives <= 18){
            	Node node = new Node(tempList);
            	node.addNumOfElectiveUnits(numOfElectives);
            	combClasses.add(node);
			}
            
            //lock CS-1010 and MATH-2110 into the first semester
            if(available.contains("CS-1010") && available.contains("MATH-2110")){
                for(int i = 0; i < combClasses.size(); i++){
                    if(combClasses.get(i).getData().contains("CS-1010") && combClasses.get(i).getData().contains("MATH-2110")){

                    }
                    else{
                        combClasses.remove(i);
                        i--;
                    }
                }
            }
            else if(available.contains("CS-1010")){
                for(int i = 0; i < combClasses.size(); i++){
                    if(combClasses.get(i).getData().contains("CS-1010")){

                    }
                    else{
                        combClasses.remove(i);
                        i--;
                    }
                }
            }
            else if(available.contains("MATH-2110")){
                for(int i = 0; i < combClasses.size(); i++){
                    if(combClasses.get(i).getData().contains("MATH-2110")){

                    }
                    else{
                        combClasses.remove(i);
                        i--;
                    }
                }
            }
            
            
            
            //keep top combinations. 60 is a good number. Anything less and we dont have enough to get a 4 year list. You could do more but it takes longer and uses up more memory
            for(int i = 0; combClasses.size() > 60;){
            	combClasses.remove(i);
            }

            return;
		}

		for (int i = start; i <= end; i++){
            tempCombo.set(index, available.get(i));
            //index + 1 replaces index with all possible elements
            storeCombinations(listOfClasses, available, tempCombo, i+1, end, index+1, sizeOfNextCombo, combClasses, numOfElectives);
        }
	}
}