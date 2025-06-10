package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
	static Connection conn = null;
	
	public static Connection open() throws SQLException {


			try {
				Class.forName("org.mariadb.jdbc.Driver");				

			} catch (Exception e) {

				e.printStackTrace();
			}
			return DriverManager.getConnection("jdbc:mariadb://localhost/pbl6", "root", "root");

		} 

	static void close(Connection c) {
		if (conn != null) {

			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
