package accessObjects;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import algorithm.SemesterCourses;
import objectLists.PathList;

public class PathInfo {
	//To get all available paths that each student is taking or going to take.
	public ArrayList<PathList> GetPaths(Connection connection) throws Exception {
		ArrayList<PathList> feedData = new ArrayList<PathList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths");
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Path holder
				PathList feedObject = new PathList();
				
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
				
				//save it to the list
				feedData.add(feedObject);
			}
			
			//close the connections and return
			rs.close();
			ps.close();
			return feedData;

		}catch (Exception e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}

	//Show all paths for a specific student using their CIN.
	public ArrayList<PathList> StudentPath(Connection connection, int cin) throws Exception {
		ArrayList<PathList> feedData = new ArrayList<PathList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where cin = ?");
			//load the data to the statement
			ps.setInt(1, cin);
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Path holder
				PathList feedObject = new PathList();
				
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
				
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

	//Show which students are taking this class for the present and future.
	public ArrayList<PathList> ClassPath(Connection connection, String ClassCode) throws Exception {
		ArrayList<PathList> feedData = new ArrayList<PathList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where classCode = ?");
			//load the data to the statement
			ps.setString(1, ClassCode);
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Path holder
				PathList feedObject = new PathList();
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
				
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
	
	//Get paths for a given semester and year.
	public ArrayList<PathList> SemesterPath(Connection connection, String semester,String year) throws Exception {
		ArrayList<PathList> feedData = new ArrayList<PathList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where semester = ? and year = ?");
//			System.out.println(semester+" "+ year); //console TEST
			//load the data to the statement
			ps.setString(1, semester);
			ps.setString(2,year);
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Paths holder
				PathList feedObject = new PathList();
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
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
	
	
	//Get paths for a given semester and year.
	public ArrayList<PathList> StudentSemesterPath(Connection connection,String cin,String semester,String year) throws Exception {
		ArrayList<PathList> feedData = new ArrayList<PathList>();

		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where cin = ? and semester= ? and year = ?");
			//load the data to the statement
			ps.setString(1, cin);
			ps.setString(2, semester);
			ps.setString(3, year);
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Paths holder
				PathList feedObject = new PathList();
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
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
	
	//Get all classes that the student has taken.
	public ArrayList<PathList> getTakenClasses(Connection connection, String cin)throws Exception{
		ArrayList<PathList> feedData = new ArrayList<PathList>();
		
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where cin = ? and grade not like ?");
			//load the data to the statement
			ps.setString(1, cin);
			ps.setString(2, "-");;
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//Paths holder
				PathList feedObject = new PathList();
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
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
	
	public List<String> getTaken(Connection connection, String cin)throws Exception{
		ArrayList<String> feedData = new ArrayList<String>();
		
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where cin = ? and grade not like ?");
			//load the data to the statement
			ps.setString(1, cin);
			ps.setString(2, "-");;
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//String holder for class Codes
				String course = (rs.getString("ClassCode"));
				//change the space to - since algorithm's csv file has classes with dashes
				course = course.replaceAll("\\s","-");
				//System.out.println(course); //console TEST
				//save it to the list
				feedData.add(course);
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
	
	//Get all classes that the student has not taken.
	public ArrayList<PathList> getNotFinished(Connection connection, String cin)throws Exception{
		ArrayList<PathList> feedData = new ArrayList<PathList>();
		//System.out.println("made it to the getTakenClasses in Info. Before the sql call.");
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select * from paths where cin = ? and grade like ?");
			//load the data to the statement
			ps.setString(1, cin);
			ps.setString(2, "-");;
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//paths holder
				PathList feedObject = new PathList();
				//load info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setClassCode(rs.getString("ClassCode"));
				feedObject.setSemester(rs.getString("semester"));
				feedObject.setYear(rs.getString("year"));
				feedObject.setGrade(rs.getString("grade"));
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
	
	//Create a new path for a student
	public boolean newPath(Connection connection,String sid, String classCode,String semester,String year) throws Exception{
		//ArrayList<PathList> feedData = new ArrayList<PathList>();
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("insert into paths(cin,classCode,semester,year) values(?,?,?,?)");
			//load the data to the statement
			ps.setString(1, sid);
			ps.setString(2, classCode);
			ps.setString(3, semester);
			ps.setString(4, year);
			//execute the query
			int i = ps.executeUpdate();
			
			//validate the return from the database
			if(i==1) { 
				//close the connections and return
				ps.close();
				return true;
			}
			//close the connections and return
			ps.close();
			return false;

		} catch (SQLException e) {
			throw e;
		}finally {
			//close the database connection
			connection.close();
		}
	}
	
	//Create a new path for a student
		public boolean newPaths(Connection connection,String sid, List<SemesterCourses> courses) throws Exception{
			//ArrayList<PathList> feedData = new ArrayList<PathList>();
			try {
				//create the statement and connection
				PreparedStatement ps = connection.prepareStatement("insert into paths(cin,classCode,semester,year) values(?,?,?,?)");
				//load the data to the statement
				ps.setString(1, sid);
				//execute the query
				int i = ps.executeUpdate();
				
				//validate the return from the database
				if(i==1) { 
					//close the connections and return
					ps.close();
					return true;
				}
				//close the connections and return
				ps.close();
				return false;

			} catch (SQLException e) {
				throw e;
			}finally {
				//close the database connection
				connection.close();
			}
		}
	
	//Create a new path for a student with a grade they got
		public boolean newPathGrade(Connection connection,String cin, String classCode,String semester,String year,String grade) throws Exception{
			try {
				//create the statement and connection
				PreparedStatement ps = connection.prepareStatement("insert into paths values(?,?,?,?,?)");
				//load the data to the statement
				ps.setString(1,cin);
				ps.setString(2, classCode);
				ps.setString(3, semester);
				ps.setString(4, year);
				ps.setString(5, grade);
				//execute the query
				int i = ps.executeUpdate();
				//validate the return from the database
				if(i==1) {
					//close the connections and return
					ps.close();
					return true;
				}
				//close the connections and return
				ps.close();
				return false;

			} catch (SQLException e) {
				throw e;
			}finally {
				//close the database connection
				connection.close();
			}
		}
		
		//Edit a class taken with the grade they got in the end of the semester.
		public boolean editGrade(Connection connection,String cin, String classCode, String semester,String year,String grade)throws Exception{
			try {
				//create the statement and connection
				PreparedStatement ps = connection.prepareStatement("Update paths set grade = ? where cin = ? and classCode = ? and semester =? and year =?");
				//load the data to the statement
				ps.setString(1,grade);
				ps.setString(2, cin);
				ps.setString(3, classCode);
				ps.setString(4, semester);
				ps.setString(5, year);
				//execute the query
				int i = ps.executeUpdate();
				
				//validate the return from the database
				if(i==1) {
					//close the connections and return
					ps.close();
					return true;
				}
				//close the connections and return
				ps.close();
				return false;
			}catch(SQLException e){
				throw e;
			}finally {
				//close the database connection
				connection.close();
			}
		}
	
	//deletes one of the paths for a student
	public boolean deletePath(Connection connection,String cin, String classCode,String semester,String year) throws Exception{
		try {
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("delete from paths where cin = ? and classCode = ? and semester = ? and year = ?");
			//load the data to the statement
			ps.setString(1, cin);
			ps.setString(2, classCode);
			ps.setString(3, semester);
			ps.setString(4,year);
//			System.out.println(ps); //console TEST
			//execute the query
			int i = ps.executeUpdate();
			
			//validate the return from the database
			if(i==1) {
				//close the connections and return
				ps.close();
				return true;
			}
			//close the connections and return
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