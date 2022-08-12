package model;

import java.sql.Connection;
import java.util.ArrayList;

import accessObjects.PrerequisiteInfo;
import accessObjects.Database;
import objectLists.PrerequisiteList;

public class PrerequisiteManager {

	// Get all courses and its prerequisites
	public ArrayList<PrerequisiteList> getPrereq() throws Exception {
		ArrayList<PrerequisiteList> feeds = null;
		try {
			//create a connection
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			//Retrive the data from the database
			PrerequisiteInfo project = new PrerequisiteInfo();
			feeds = project.getPrereq(connection);
			
			//close the connection
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

	// Get all prerequisites for a specific course
	public ArrayList<PrerequisiteList> getPrereqByCourse(String course) throws Exception {
		ArrayList<PrerequisiteList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			PrerequisiteInfo project = new PrerequisiteInfo();
			feeds = project.getPrereqByCourse(connection, course);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

	//insert  prerequisite to an existing course
	public Boolean postPrereq(String course, String Prerequisite) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			PrerequisiteInfo project = new PrerequisiteInfo();
			feeds = project.postPrereq(connection, course, Prerequisite);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}

	// delete a course's prerequisite
	public Boolean deletePrereq(String course, String Prerequisite) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			PrerequisiteInfo project = new PrerequisiteInfo();
			feeds = project.deletePrereq(connection, course, Prerequisite);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
}
