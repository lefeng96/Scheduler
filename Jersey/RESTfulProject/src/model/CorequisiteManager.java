package model;

import java.sql.Connection;
import java.util.ArrayList;

import accessObjects.Database;
import accessObjects.CorequisiteInfo;
import objectLists.CorequisiteList;

//Only admins have access to these call. 
public class CorequisiteManager {
	
	//Get all corequisites
	public ArrayList<CorequisiteList> getCoreq() throws Exception {
		ArrayList<CorequisiteList> feeds = null;
		try {
			//Create a connection
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			//Get the database information that will be returned
			CorequisiteInfo project = new CorequisiteInfo();
			feeds = project.getCoreq(connection);
			
			//close the connection
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Get corequisites from a specific class.
	public ArrayList<CorequisiteList> getCoreqByCourse(String course) throws Exception {
		ArrayList<CorequisiteList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			CorequisiteInfo project = new CorequisiteInfo();
			feeds = project.getCoreqByCourse(connection,course);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Add corequisites to a course
	public Boolean postCoreq(String course, String corequisite) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			CorequisiteInfo project = new CorequisiteInfo();
			feeds = project.postCoreq(connection,course,corequisite);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	
	//delete a corequisite from a course.
	public Boolean deleteCoreq(String course, String corequisite) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			CorequisiteInfo project = new CorequisiteInfo();
			feeds = project.deleteCoreq(connection,course,corequisite);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

}
