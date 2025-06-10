package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
	
	public User authenticate(String mail,String password)throws SQLException{
		String sql ="select * from user where mail=? and password=?";
		
		try(
				Connection conn =Db.open();//まだDb作ってない
				PreparedStatemant ps =conn.prepareStatement(sql);
				){
			ps.setString(1,mail);
			ps.setString(2,password);
			
			try(ResultSet rs =ps.executeQuery()){
				
				if(rs.next()) {
					User user = new User(
							rs.getString("mail"),
							rs.getString("password"));
					return user;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
	}

}
