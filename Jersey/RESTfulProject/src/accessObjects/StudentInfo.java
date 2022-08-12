package accessObjects;

import java.sql.*;
import java.util.ArrayList;

import objectLists.ClassList;
import objectLists.PathList;
import objectLists.StudentList;

public class StudentInfo {
	//Get all students from the database
	public ArrayList<StudentList> GetFeeds(Connection connection) throws Exception {
		ArrayList<StudentList> feedData = new ArrayList<StudentList>();

		try {
			//execute the query
			PreparedStatement ps = connection.prepareStatement("select * from students");
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//students holder
				StudentList feedObject = new StudentList();
				//load info to the holder
				feedObject.setFname(rs.getString("fname"));
				feedObject.setLname(rs.getString("lname"));
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setAdmin(rs.getBoolean("admin"));
				//save it to the list
				feedData.add(feedObject);
			}
			//close the connections and return
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

	//Get a list of students by their first names from the database
	public ArrayList<StudentList> fname(Connection connection, String fname) throws Exception {
		ArrayList<StudentList> feedData = new ArrayList<StudentList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from students where fname = ?");
			//load the data to the statement
			ps.setString(1, fname);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Student holder
				StudentList feedObject = new StudentList();
				//load info to the holder
				feedObject.setFname(rs.getString("fname"));
				feedObject.setLname(rs.getString("lname"));
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setAdmin(rs.getBoolean("admin"));
				//save it to the list
				feedData.add(feedObject);
			}
			//close the database connection
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

	//Get a list of students by their last name from the database
	public ArrayList<StudentList> lname(Connection connection, String lname) throws Exception {
		ArrayList<StudentList> feedData = new ArrayList<StudentList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from students");
			//load the data to the statement
			ps.setString(1, lname);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Student holder
				StudentList feedObject = new StudentList();
				//load info to the holder
				feedObject.setFname(rs.getString("fname"));
				feedObject.setLname(rs.getString("lname"));
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setAdmin(rs.getBoolean("admin"));
				//save it to the list
				feedData.add(feedObject);
			}
			//close the database connection
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
	
	//Get students by CIN- matching only from the database
	public ArrayList<StudentList> cin(Connection connection, int cin) throws Exception {
		ArrayList<StudentList> feedData = new ArrayList<StudentList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from students where cin = ?");
			//load the data to the statement
			ps.setInt(1, cin);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Student holder
				StudentList feedObject = new StudentList();
				//load info to the holder
				feedObject.setFname(rs.getString("fname"));
				feedObject.setLname(rs.getString("lname"));
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setAdmin(rs.getBoolean("admin"));
				//save it to the list
				feedData.add(feedObject);
			}
			//close the database connection
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
	
	//Insert a new student into the database
	public boolean newStudent(Connection connection,String fname, String lname,int cin) throws Exception{
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("insert into students(fname,lname,cin) values(?,?,?)");
			//load the data to the statement
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, cin);
			//execute the query
			int i = ps.executeUpdate();
			
			//validate the return from the database
			if(i==1) {
				//close the connections and return
				ps.close();
				return true;
			}
			//close the database connection
			ps.close();
			return false;

		} catch (SQLException e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//inserting a new admin
	public boolean newAdmin(Connection connection, String first, String last, int cin)throws Exception{
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("Insert into students(fname,lname,cin,admin)values(?,?,?,true)");
			//load the data to the statement
			ps.setString(1, first);
			ps.setString(2, last);
			ps.setInt(3, cin);
			//execute the query
			int i = ps.executeUpdate();
			
			//validate the return from the database
			if(i == 1) {
				//close the connections and return
				ps.close();
				return true;
			}
			//close the database connection
			ps.close();
			return false;
		}catch(SQLException e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//edit a students information and insert new info into the database.
	public boolean editStudent(Connection connection,String fname, String lname,int cin) throws Exception{
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("update students set fname = ?, lname = ? where cin = ?");
			//load the data to the statement
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setInt(3, cin);
			//execute the query
			int i = ps.executeUpdate();
			
			//validate the return from the database
			if(i==1) {
				//close the connections and return
				ps.close();
				return true;
			}
			//close the database connection
			ps.close();
			return false;

		} catch (SQLException e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}

	
	//Delete a student from the database. 
	public Boolean deleteStudent(Connection connection, int cin) throws Exception {
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("delete from students where cin = ?");
			//load the data to the statement
			ps.setInt(1,cin);
			//execute the query
			int i = ps.executeUpdate();
			
			//validate the return from the database
			if (i == 1) {
				//close the connections and return
				ps.close();
				return true;
			}
			//close the database connection
			ps.close();
			return false;

		} catch (SQLException e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}

}