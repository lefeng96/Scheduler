package accessObjects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This holds the database connection credentials
//We previously had 3 login credentials here
//Localhost - for testing at home or remote.
//Digital Ocean servers
//1. testing server- this server was used to hold beta api for testing endpoints.
//2. live server - this server was used to hold the current and active api.
public class Database {

	public Connection Get_Connection() throws Exception {
		try {
			//For the use of the cs3 server
			//insert proper mysql database url.
			String connectionURL = "jdbc:mysql://" + "url" + "?useSSL=true";
			
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//cs3 server
			//change the username and password to proper cs3 credentials
			connection = DriverManager.getConnection(connectionURL, "username", "password");
			
			return connection;
		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

}
