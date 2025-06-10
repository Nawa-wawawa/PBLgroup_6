package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Bean.accounts;
import util.Db;

public class LoginService {
	public accounts authenticate(String mail,String password) throws SQLException{
		String sql = "select * from pbl6  where mail=? and password=?";
		
		try (
			Connection conn =Db.open();
			PreparedStatement ps = conn.prepareStatement(sql);
				){
			ps.setString(1,mail);
			ps.setString(2, password);
			
			try (ResultSet rs = ps.executeQuery()){
				
				if(rs.next()) {
					accounts accounts = new accounts(
								rs.getString("mail"),
								rs.getString("password")
							);
					return accounts;
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	}
	
}
