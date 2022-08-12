package model;

import java.sql.Connection;
import java.util.ArrayList;

import accessObjects.ClassInfo;
import accessObjects.Database;
import objectLists.ClassList;

public class ClassManager {

	//Get all Classes
	public ArrayList<ClassList> GetFeeds() throws Exception {
		ArrayList<ClassList> feeds = null;
		try {
			//Create a connection
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			//Get the database information that will be returned
			ClassInfo project = new ClassInfo();
			feeds = project.GetFeeds(connection);
			
			//close the connection
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Get classes by Subject
	public ArrayList<ClassList> ClassSubject(String subject) throws Exception {
		ArrayList<ClassList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.ClassSubject(connection,subject);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Get a specific class by the class Code
	public ArrayList<ClassList> ClassCode(String classCode) throws Exception {
		ArrayList<ClassList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.ClassCode(connection, classCode);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Get class by the name of the class
	public ArrayList<ClassList> ClassName(String className) throws Exception {
		ArrayList<ClassList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.ClassName(connection,className);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//get classes by the units
	public ArrayList<ClassList> ClassUnit(int unit) throws Exception {
		ArrayList<ClassList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.ClassUnit(connection,unit);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Add a class
	public Boolean AddClass( String subject, String classCode, String className, int unit,String description) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.AddClass(connection, subject, classCode, className, unit,description);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Update class information
	public Boolean UpdateClass( String subject, String classCode, String className, int unit,String description) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.UpdateClass(connection, subject, classCode, className, unit,description);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//delete a class
	public Boolean DeleteClass(String classCode) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			ClassInfo project = new ClassInfo();
			feeds = project.DeleteClass(connection,classCode);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

}