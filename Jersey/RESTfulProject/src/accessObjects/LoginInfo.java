package accessObjects;

import java.sql.*;
import java.util.ArrayList;
import objectLists.LoginList;

public class LoginInfo {
	
	//get the login info from the student
	public ArrayList<LoginList> GetFeeds(Connection connection, int cin,String password) throws Exception {
		ArrayList<LoginList> feedData = new ArrayList<LoginList>();

		try {
			//This statement call the login and student info. 
			//create the statement and connection
			PreparedStatement ps = connection.prepareStatement("select l.cin as cin, l.email as email, l.password as password, s.admin as admin from login as l inner join students as s on ? = s.cin and l.password = ?");
			//load the data to the statement
			ps.setInt(1, cin);
			ps.setString(2, password);
			//execute the query
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				//login holder
				LoginList feedObject = new LoginList();
				
				//load the info to the holder
				feedObject.setCin(rs.getInt("cin"));
				feedObject.setEmail(rs.getString("email"));
				feedObject.setPassword(rs.getString("password"));
				feedObject.setAdmin(rs.getBoolean("admin"));
				
				//save it to the list
				feedData.add(feedObject);
			}
			//close connections and return
			rs.close();
			ps.close();
			return feedData;

		} catch (Exception e) {
			throw e;
		}
		finally {
			//close the database connection
			connection.close();
		}
	}
	
	public Boolean updateUser(Connection connection,String cin, String password ) throws Exception {
		int check;
		try {
			//create the connection and statement
			PreparedStatement rm = connection.prepareStatement("Update login set password = ? where cin = ?");
			//load data to statement
			rm.setString(1, password);
			rm.setString(2, cin);
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
	
	public boolean newLogin(Connection connection,String cin, String email,String password) throws Exception{
		try {
			//create statement and connection
			PreparedStatement ps = connection.prepareStatement("insert into login values(?,?,?)");
			//load values
			ps.setString(1, cin);
			ps.setString(2, email);
			ps.setString(3, password);
			// execute the query
			int i = ps.executeUpdate();
			if(i==1) {
				//close and return
				ps.close();
				return true;
			}
			//close and return
			ps.close();
			return false;

		} catch (SQLException e) {
			throw e;
		}
		finally {
			//close the database connection
			connection.close();
		}
	}
}
