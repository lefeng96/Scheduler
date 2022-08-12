package model;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import accessObjects.Database;
import accessObjects.PathInfo;
import algorithm.SemesterCourses;
import objectLists.PathList;

public class PathManager {

	//To get all paths
	public ArrayList<PathList> GetPaths() throws Exception {
		ArrayList<PathList> feeds = null;
		try { 
			//Create a connection
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			//Retrieve info from database
			PathInfo project = new PathInfo();
			feeds = project.GetPaths(connection);
			
			//Close the conection
			connection.close();
			//System.out.println("Inside Path manager\n" +feeds.toString()); //console TEST
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	//To get Paths by CIN
	public ArrayList<PathList> StudentPath(int Sid) throws Exception {
		ArrayList<PathList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			PathInfo project = new PathInfo();
			feeds = project.StudentPath(connection,Sid);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//To get Paths by Class Code
    public ArrayList<PathList> ClassPath(String ClassCode) throws Exception {
        ArrayList<PathList> feeds = null;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            feeds = project.ClassPath(connection,ClassCode);
            
            connection.close();
        } catch (Exception e) {
            throw e;
        }
        return feeds;
    }
    
    //Gets Paths by the Semester and Year
    public ArrayList<PathList> SemesterPath(String semester,String year) throws Exception {
        ArrayList<PathList> feeds = null;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            feeds = project.SemesterPath(connection,semester,year);
            
            connection.close();
        } catch (Exception e) {
            throw e;
        }
        return feeds;
    }
    
    
    //Gets Paths by the Semester and Year for a specific student
    public ArrayList<PathList> StudentSemesterPath(String cin,String semester,String year) throws Exception {
        ArrayList<PathList> feeds = null;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            feeds = project.StudentSemesterPath(connection,cin,semester,year);
            
            connection.close();
        } catch (Exception e) {
            throw e;
        }
        return feeds;
    }
    
    //Get the classes taken or has a grade.
    public ArrayList<PathList> TakenClasses(String cin)throws Exception{
    	ArrayList<PathList> feeds = null;
    	try{
    		Database database = new Database();
    		Connection connection = database.Get_Connection();
    		
    		PathInfo path = new PathInfo();
    		feeds = path.getTakenClasses(connection, cin);
    		
    		connection.close();
    		
    	}catch(Exception e) {
    		throw e;
    	}
    	return feeds;
    }
    
    //This gets a list of courses the student has taken - has a grade.
    public List<String> taken(String cin)throws Exception{
		ArrayList<String> took = new ArrayList<String>();
		try{
    		Database database = new Database();
    		Connection connection = database.Get_Connection();
    		
    		PathInfo path = new PathInfo();
    		took = (ArrayList<String>) path.getTaken(connection, cin);
    		
    		connection.close();
    		
    	}catch(Exception e) {
    		throw e;
    	}
    	return took;
    }
    
    //Get the classes not taken- does not have a grade for them.
    public ArrayList<PathList> NotFinished(String cin)throws Exception{
    	ArrayList<PathList> feeds = null;
    	try{
    		Database database = new Database();
    		Connection connection = database.Get_Connection();
    		
    		PathInfo path = new PathInfo();
    		feeds = path.getNotFinished(connection, cin);
    		
    		connection.close();
    		
    	}catch(Exception e) {
    		throw e;
    	}
    	return feeds;
    }
    //Creates a new path for the student
    public boolean newPath(String cin,String classCode,String semester,String year) throws Exception {
        boolean inserted;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            inserted = (boolean) project.newPath(connection,cin,classCode,semester,year);
            
            connection.close();
            
            
            if(inserted == true) {
//            	System.out.print("Insert was successful."); //console TEST
            	return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
    
  //Creates a new path for the student
    public boolean newPathGrade(String sid,String classCode,String semester,String year,String grade) throws Exception {
        boolean inserted;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            inserted = (boolean) project.newPathGrade(connection,sid,classCode,semester,year,grade);
            
            connection.close();
            if(inserted == true) {
//            	System.out.print("Insert was successful."); //console TEST
            	return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
    
  //Creates a new path for the student from the algorithm
    public boolean newPaths(String cin,List<SemesterCourses> courses) throws Exception {
        boolean inserted;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            inserted = (boolean) project.newPaths(connection,cin,courses);
            
            connection.close();
            if(inserted == true) {
//            	System.out.print("Insert was successful."); // console TEST
            	return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }
    
    //Edit a past taken class to insert a grade.
    public boolean editGrade(String cin,String classCode, String semester,String year, String grade) throws Exception{
    	boolean inserted;
    	try {
    		Database database = new Database();
    		Connection connection = database.Get_Connection();
    		
    		PathInfo project = new PathInfo();
    		inserted =(boolean) project.editGrade(connection, cin, classCode, semester,year, grade);
    		
    		connection.close();
    		if(inserted == true) {
//    			System.out.print("We have successfully edited the class you have taken with the new grade."); //console TEST
    			return true;
    		}
    	}catch(Exception e) {
    		throw e;
    	}
    	return false;
    }
    
    //Deletes an existing Path for the student
    public boolean deletePath(String sid,String classCode,String semester,String year) throws Exception {
        boolean deleted;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            PathInfo project = new PathInfo();
            deleted = (boolean) project.deletePath(connection,sid,classCode,semester, year);
            
            connection.close();
            if(deleted == true) {
//            	System.out.print("Delete was successful."); //console TEST
            	return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
    }

}