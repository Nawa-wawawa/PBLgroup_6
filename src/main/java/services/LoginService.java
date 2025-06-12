package services;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import beans.accounts;
import utils.Db;

public class LoginService {
	public accounts authenticate(String mail,String password) throws SQLException, NamingException{
		String sql = "select * from accounts  where mail=? and password=?";
		
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
	
	public boolean digitAlphabetCheck(String input) {//パスワードの形式チェック
		for(int i=0; i < input.length(); i++) {
			char c= input.charAt(i);
			if((c < '0'|| c > '9')&&
				(c < 'a'|| c > 'z')&&
				(c < 'A' || c > 'Z')
				) {
				return false;
			}
		}
		return true;
	}
	
}
