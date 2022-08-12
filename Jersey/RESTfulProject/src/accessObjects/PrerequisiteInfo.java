package accessObjects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import objectLists.PrerequisiteList;

public class PrerequisiteInfo {

	// Get all prerequisites
	public ArrayList<PrerequisiteList> getPrereq(Connection connection) throws Exception {
		ArrayList<PrerequisiteList> feedData = new ArrayList<PrerequisiteList>();
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("SELECT * from prerequisite");
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Prereqs holder
				PrerequisiteList feedObject = new PrerequisiteList();
				//load info to the holder
				feedObject.setCourse(rs.getString("course"));
				feedObject.setPrerequisite(rs.getString("prerequisite"));
				//save it to the list
				feedData.add(feedObject);
			}
			//close the connections and return
			rs.close();
			ps.close();
			return feedData;

		} catch (Exception e) {
			throw e;
		} finally {
			//close the database connection
			connection.close();
		}
	}

	// Get all prerequisites from a specific course
	public ArrayList<PrerequisiteList> getPrereqByCourse(Connection connection, String course) throws Exception {
		ArrayList<PrerequisiteList> feedData = new ArrayList<PrerequisiteList>();
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("SELECT * from Prerequisite where course = ?");
			//load the data to the statement
			ps.setString(1, course);
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Prereq's holder
				PrerequisiteList feedObject = new PrerequisiteList();
				//load info to the holder
				feedObject.setCourse(rs.getString("course"));
				feedObject.setPrerequisite(rs.getString("Prerequisite"));
				//save it to the list
				feedData.add(feedObject);
			}
			//close the connections and return
			rs.close();
			ps.close();
			return feedData;

		} catch (Exception e) {
			throw e;
		} finally {
			//close the database connection
			connection.close();
		}
	}

	// add a prerequisite to an existing course
	public Boolean postPrereq(Connection connection, String course, String Prerequisite) throws Exception {
		int check;
		try {
			//create the statement and connection
			PreparedStatement rm = connection.prepareStatement("INSERT INTO Prerequisite VALUES (?,?)");
			//load the data to the statement
			rm.setString(1, course);
			rm.setString(2, Prerequisite);
			//execute the query
			check = rm.executeUpdate();
			

			//validate the return from the database
			if (check >= 1) {
				//close the connections and return
				rm.close();
				return true;
			}
			//close the connections and return
			rm.close();
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			//close the database connection
			connection.close();
		}
	}

	// Delete a class
	public Boolean deletePrereq(Connection connection, String course, String Prerequisite) throws Exception {
		int check;
		try {
			//create the statement and connection
			PreparedStatement rm = connection
					.prepareStatement("DELETE from Prerequisite where course = ? and Prerequisite = ?");
			//load the data to the statement
			rm.setString(1, course);
			rm.setString(2, Prerequisite);
			//execute the query
			check = rm.executeUpdate();
			if (check >= 1) {
				//close the connections and return
				rm.close();
				return true;
			}
			//close the connections and return
			rm.close();
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			//close the database connection
			connection.close();
		}
	}
}
