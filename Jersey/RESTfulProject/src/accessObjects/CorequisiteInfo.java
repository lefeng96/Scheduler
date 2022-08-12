package accessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectLists.CorequisiteList;

public class CorequisiteInfo {
	
	//Get all corequisites from the database
	public ArrayList<CorequisiteList> getCoreq(Connection connection) throws Exception {
		ArrayList<CorequisiteList> feedData = new ArrayList<CorequisiteList>();
		try {
			//create the connection and statement 
			PreparedStatement ps = connection.prepareStatement("SELECT * from corequisite");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//coreq holder
				CorequisiteList feedObject = new CorequisiteList();
				
				//load info to the hodler
				feedObject.setCourse(rs.getString("course"));
				feedObject.setCorequisite(rs.getString("corequisite"));
				
				//save it to the list
				feedData.add(feedObject);
			}
			
			//close the connection and return the list
			rs.close();
			ps.close();
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//Get all corequesietes from a specific course
	public ArrayList<CorequisiteList> getCoreqByCourse(Connection connection,String course) throws Exception {
		ArrayList<CorequisiteList> feedData = new ArrayList<CorequisiteList>();
		try {
			//create the statement and connections
			PreparedStatement ps = connection.prepareStatement("SELECT * from corequisite where course = ?");
			//load the data into the statement
			ps.setString(1, course);
			//execute the statement
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//create a coreq holder
				CorequisiteList feedObject = new CorequisiteList();
				
				//load info into the holder
				feedObject.setCourse(rs.getString("course"));
				feedObject.setCorequisite(rs.getString("corequisite"));
				
				//save it to the list
				feedData.add(feedObject);
			}
			//close the connections and return the list
			rs.close();
			ps.close();
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	
	//add a corequisite to a course
	public Boolean postCoreq(Connection connection,String course,String corequisite) throws Exception {
		int check;
		try {
			//create the statement and the connection
			PreparedStatement rm = connection.prepareStatement("INSERT INTO corequisite VALUES (?,?)");
			//load the data into the statement
			rm.setString(1, course);
			rm.setString(2, corequisite);
			//execute the query
			check = rm.executeUpdate();
			
			//validate the return from the database
			if(check>=1) {
				//close and return
				rm.close();
				return true;
			}
			//close and return
			rm.close();
			return false;
			}
		catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//Delete a corequisite from a course
	public Boolean deleteCoreq(Connection connection, String course,String corequisite) throws Exception {
		int check;
		try {
			//create the statement and the connection
			PreparedStatement rm = connection.prepareStatement("DELETE from corequisite where course = ? and corequisite = ?");
			//load the data into the statement
			rm.setString(1, course);
			rm.setString(2, corequisite);
			//execute the query
			check = rm.executeUpdate();
			
			//validate the return from the database
			if(check>=1) {
				//close and return
				rm.close();
				return true;
			}
			//close and return
			rm.close();
			return false;
			}
		catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
}
