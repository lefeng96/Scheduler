package model;

import java.sql.Connection;
import java.util.ArrayList;

import accessObjects.LoginInfo;
import accessObjects.Database;
import objectLists.LoginList;

public class LoginManager {
	
	//Using the password that was provided at login, we check to see if it matches.
	public ArrayList<LoginList> GetFeeds(int cin,String password) throws Exception {
		ArrayList<LoginList> feeds = null;
		try {
			//Create a connection
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			//Get the database information that will be returned
			LoginInfo project = new LoginInfo();
			feeds = project.GetFeeds(connection, cin,password);
			
			//Close that connection
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Used to update a password. Not really implemented on the frontend. 
	public Boolean updateUser(String cin,String password) throws Exception {
		Boolean feeds = false;
		try {
			Database database = new Database();
			Connection connection = database.Get_Connection();
			
			LoginInfo project = new LoginInfo();
			feeds = project.updateUser(connection,cin, password);
			
			connection.close();
		} catch (Exception e) {
			throw e;
		}
		return feeds;
	}
	
	//Will be used for new users when they register.
	public boolean newLogin(String cin,String email,String password) throws Exception {
        boolean inserted;
        try {
            Database database = new Database();
            Connection connection = database.Get_Connection();
            
            LoginInfo login = new LoginInfo();
            inserted = (boolean) login.newLogin(connection,cin,email,password);
            
            connection.close();
            if(inserted == true) {
//            	System.out.print("Insert was successful."); //console TEST
            	return true;
            }
        } catch (Exception e) {
//        	System.out.println("Error in newLogin from manager."); //console TEST
            throw e;
        }
        return false;
    }
}
