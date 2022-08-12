package accessObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import objectLists.ClassList;
import objectLists.CorequisiteList;
import objectLists.OfferedList;
import objectLists.PrerequisiteList;

public class ClassInfo {
	//Get all classes from the database
	public ArrayList<ClassList> GetFeeds(Connection connection) throws Exception {
		ArrayList<ClassList> feedData = new ArrayList<ClassList>();

		try {
			//Prepared statement connection
			PreparedStatement ps = connection.prepareStatement("select * from classes");
			//Resultset connection
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//create a holder for each class.
				ClassList feedObject = new ClassList();
				
				//load the info for each class into the holder
				feedObject.setSubject(rs.getString("subject"));
				feedObject.setClassCode(rs.getString("classCode"));
				feedObject.setClassName(rs.getString("className"));
				feedObject.setUnit(rs.getInt("unit"));
				feedObject.setDescription(rs.getString("description"));
				
				//save the class into the list
				feedData.add(feedObject);
			}
			
			//close the connections
			rs.close();
			ps.close();
			
			//return the list
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//Get a class by the subject from the database
	public ArrayList<ClassList> ClassSubject(Connection connection, String subject) throws Exception {
		ArrayList<ClassList> feedData = new ArrayList<ClassList>();

		try {
			//Create the connections
			PreparedStatement ps = connection.prepareStatement("select * from classes where subject = ?");
			
			//load the data to the prepared statement
			ps.setString(1, subject);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//class holder
				ClassList feedObject = new ClassList();
				//load info to holder
				feedObject.setSubject(rs.getString("subject"));
				feedObject.setClassCode(rs.getString("classCode"));
				feedObject.setClassName(rs.getString("className"));
				feedObject.setUnit(rs.getInt("unit"));
				feedObject.setDescription(rs.getString("description"));
				
				//save class holder into a list
				feedData.add(feedObject);
			}
			//close the connections 
			rs.close();
			ps.close();
			
			//return the list
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//get a class by the class code
	//The only method that gets co/pre requisites and offered.
	public ArrayList<ClassList> ClassCode(Connection connection, String classCode) throws Exception {
		
		//list place holders
		ArrayList<ClassList> feedData = new ArrayList<ClassList>();
		List<OfferedList> offered= new ArrayList<OfferedList>();
		List<CorequisiteList> co= new ArrayList<CorequisiteList>();
		List<PrerequisiteList> pre= new ArrayList<PrerequisiteList>();

		
		try {
			//Getting the list of when the class is offered during what term to insert into the overall list that will be returned
			//creating connections for offered
			PreparedStatement ls = connection.prepareStatement("select * from offered where course = ?");
			
			//load data to statement
			ls.setString(1,classCode);
			ResultSet ds = ls.executeQuery();
			
			while(ds.next()) {
				//offered holder
				OfferedList holder = new OfferedList();
				
				//load offered data
				holder.setCourse(ds.getString("course"));
				holder.setTermOffered(ds.getString("offered"));
				
				//save that offered data
				offered.add(holder);
			}
			
//			System.out.println("After the while next()"); //console TEST
			//close offered connections
			ds.close();
			ls.close();
			

			//Getting the list of corequisite classes to insert into the overall list that will be returned
			//creating the connections
			PreparedStatement qs = connection.prepareStatement("select * from corequisite where course = ?");
			
			//load the data into statement
			qs.setString(1,classCode);
			ResultSet gs = qs.executeQuery();
			
//			System.out.println(gs.wasNull()); // console TEST
			
			while(gs.next()) {
				//Coreq holder
				CorequisiteList coreq = new CorequisiteList();
				
				//load the info to holder
				coreq.setCourse(gs.getString("course"));
				coreq.setCorequisite(gs.getString("corequisite"));
				
				//save it to the list
				co.add(coreq);
			}
			
			System.out.println("After the while next() in coreq");// console TEST 
			
			//close the connections
			gs.close();
			qs.close();
			
			
			//Getting the list of prerequisite classes to insert into the overall list that will be returned
			//create the connections
			PreparedStatement bs = connection.prepareStatement("select * from prerequisite where course = ?");
			//load the data to the statement
			bs.setString(1,classCode);
			ResultSet cs = bs.executeQuery();
			
			while(cs.next()) {
				//Prereq holder
				PrerequisiteList prereq = new PrerequisiteList();
				
				//load the info to holder
				prereq.setCourse(cs.getString("course"));
				prereq.setPrerequisite(cs.getString("prerequisite"));
				
				//save it to the list
				pre.add(prereq);
			}
			System.out.println("After the while next() prereq"); //console TEST
			
			//close the connections
			cs.close();
			bs.close();
			
			//Get the class information
			//create the connections
			PreparedStatement ps = connection.prepareStatement("select * from classes where classCode = ?");
			//load the data into statement
			ps.setString(1, classCode);
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()) {
				//class holder
				ClassList feedObject = new ClassList();
				
				//load the info to the holder
				feedObject.setSubject(rs.getString("subject"));
				feedObject.setClassCode(rs.getString("classCode"));
				feedObject.setClassName(rs.getString("className"));
				feedObject.setUnit(rs.getInt("unit"));
				feedObject.setDescription(rs.getString("description"));
				feedObject.setOffered(offered);
				feedObject.setPrereq(pre);
				feedObject.setCoreq(co);
				
				//save it to the list
				feedData.add(feedObject);
			}

			//close teh connections
			rs.close();
			ps.close();
			
//			System.out.println(feedData);//console TEST print out the end result of this method
			
			//return the lis
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//get a class by the name of the class
	public ArrayList<ClassList> ClassName(Connection connection, String className) throws Exception {
		ArrayList<ClassList> feedData = new ArrayList<ClassList>();

		try {

			//create connections
			PreparedStatement ps = connection.prepareStatement("select * from classes where className = ?");
			//load data into statement
			ps.setString(1, className);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//class holder
				ClassList feedObject = new ClassList();
				
				//load info into holder
				feedObject.setSubject(rs.getString("subject"));
				feedObject.setClassCode(rs.getString("classCode"));
				feedObject.setClassName(rs.getString("className"));
				feedObject.setUnit(rs.getInt("unit"));
				feedObject.setDescription(rs.getString("description"));
				
				//save it to list
				feedData.add(feedObject);
			}
			
			//close connections
			rs.close();
			ps.close();
			//return list
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close database connection
			connection.close();
		}
	}
	
	
	//get all classes by the units 
	public ArrayList<ClassList> ClassUnit(Connection connection, int unit) throws Exception {
		ArrayList<ClassList> feedData = new ArrayList<ClassList>();

		try {
			//create connections
			PreparedStatement ps = connection.prepareStatement("select * from classes where unit = ?");
			//load data into statement
			ps.setInt(1, unit);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//class holder
				ClassList feedObject = new ClassList();
				
				//load info into holder
				feedObject.setSubject(rs.getString("subject"));
				feedObject.setClassCode(rs.getString("classCode"));
				feedObject.setClassName(rs.getString("className"));
				feedObject.setUnit(rs.getInt("unit"));
				feedObject.setDescription(rs.getString("description"));
				
				//save it to list
				feedData.add(feedObject);
			}
			
			//close connections
			rs.close();
			ps.close();
			
			//return list
			return feedData;

		} catch (Exception e) {
			throw e;
		}finally {
			//close database connection
			connection.close();
		}
	}
	
	//add a class
	public Boolean AddClass(Connection connection, String subject, String classCode, String className, int unit,String description) throws Exception {
		int check; //MySQL returns an int for insertion as a boolean.
		try {
			
			//create connections
			PreparedStatement rm = connection.prepareStatement("INSERT INTO classes VALUES (?,?,?,?,?)");
			//load the data into the statement
			rm.setString(1, subject);
			rm.setString(2, classCode);
			rm.setString(3, className);
			rm.setInt(4, unit);
			rm.setString(5, description);
			//execute the statement
			check = rm.executeUpdate();
			
			//validate the value of check
			if(check>=1) {
				//insertion was a great success and close the connection
				rm.close();
				return true;
			}
//			close the connection
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
	
	//update the information of the class
	public Boolean UpdateClass(Connection connection, String subject, String classCode, String className, int unit,String description) throws Exception {
		int check;
		try {
			//create the connection and statement
			PreparedStatement rm = connection.prepareStatement("UPDATE classes SET subject = ?, classCode = ?, ClassName = ?, unit = ?,"
					+ " description = ? WHERE classCode = ?");
			
			//load the data to the statement
			rm.setString(1, subject);
			rm.setString(2, classCode);
			rm.setString(3, className);
			rm.setInt(4, unit);
			rm.setString(5, description);
			rm.setString(6, classCode);
			
			//execute the statement
			check = rm.executeUpdate();
			
			//validate the return from the database
			if(check>=1) {
				//the update was a success and close the connection
				rm.close();
				return true;
			}
			//close the connection
			rm.close();
			return false;
			}
		catch (Exception e) {
			throw e;
		}finally {
			//close the database connection.
			connection.close();
		}
	}
	
	//Delete a class 
	public Boolean DeleteClass(Connection connection, String classCode) throws Exception {
		int check;
		try {
			//create the statement and connection
			PreparedStatement rm = connection.prepareStatement("DELETE from classes where classCode = ?");
			//load the data to the statement
			rm.setString(1, classCode);
			
			//execute the quesry
			check = rm.executeUpdate();
			
			//validate the return from the query
			if(check>=1) {
				//delete was successful and close the connection
				rm.close();
				return true;
			}
			
			//close connection
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