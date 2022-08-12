package model;

import java.sql.Connection;
import java.util.ArrayList;

import accessObjects.Database;

import accessObjects.StudentInfo;
import objectLists.StudentList;

public class StudentManager {

	//Get all student info
	public ArrayList<StudentList> GetFeeds() throws Exception {
		ArrayList<StudentList> feeds = null;
		try {
			//create the connection
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			//Retrive data from database
			StudentInfo project = new StudentInfo();
			feeds = project.GetFeeds(connection);
			
			//close the connection
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Get student information by their first name 
	public ArrayList<StudentList> fname(String fname) throws Exception {
		ArrayList<StudentList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			StudentInfo project = new StudentInfo();
			feeds = project.fname(connection,fname);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Get student information by their last name
	public ArrayList<StudentList> lname(String lname) throws Exception {
		ArrayList<StudentList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			StudentInfo project = new StudentInfo();
			feeds = project.lname(connection,lname);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	
	//Get student information by their CIN.
	public ArrayList<StudentList> cin(int cin) throws Exception {
		ArrayList<StudentList> feeds = null;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			StudentInfo project = new StudentInfo();
			feeds = project.cin(connection,cin);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Create new Student
	public Boolean newStudent(String fname, String lname,int cin) throws Exception {
		Boolean inserted;
		try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            StudentInfo project = new StudentInfo();
            inserted = (boolean) project.newStudent(connection,fname,lname,cin);

            connection.close();
            if(inserted == true) {
//            	System.out.print("Insertion was successful."); //console TEST
            	return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
	}
	//create a new Admin 
	public Boolean newAdmin(String first, String last,int cin) throws Exception {
		Boolean inserted;
		try {
			Database database =new Database();
			Connection connection = database.Get_Connection();
			
			StudentInfo addition = new StudentInfo();
			inserted= (boolean) addition.newAdmin(connection, first, last, cin);
			
			connection.close();
			
			if(inserted == true) {
				return true;
			}
		}catch(Exception e) {
			throw e;
		}
		return false;
	}
	
	
	//Edit a students information
	public Boolean editStudent(String fname, String lname,int cin) throws Exception {
		Boolean edit;
		try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            StudentInfo project = new StudentInfo();
            edit = (boolean) project.editStudent(connection,fname,lname,cin);
            
            connection.close();
            
            if(edit == true) {
//            	System.out.print("Edit was successful."); //console TEST
            	return true;
            }
        } catch (Exception e) {
            throw e;
        }
        return false;
	}
	
	//Delete the student from the database
	public Boolean deleteStudent(int cin) throws Exception {
		Boolean deleted;
		try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            StudentInfo project = new StudentInfo();
            deleted = (boolean) project.deleteStudent(connection,cin);
            
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