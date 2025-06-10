package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.NamingException;

import beans.Account;
import utils.Db;

public class AccountService {
	public void insert(Account a) {
		String sql = "INSERT INTO accounts (name, mail, password, authority) VALUES (?, ?, ?, ?)";
		try (Connection conn = Db.open(); 
	             PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            
	            pstmt.setString(1, a.getName());
	            pstmt.setString(2, a.getMail());
	            pstmt.setString(3, a.getPassword());
	            pstmt.setInt(4, a.getAuthority());

	            pstmt.executeUpdate();
	        } catch (SQLException | NamingException e) {
	            e.printStackTrace();
	        }
		}
	public void update(Account a) {
        String sql = "UPDATE accounts SET name = ?, mail = ?, password  = ? ";
        try (Connection conn = Db.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, a.getName());
            pstmt.setString(2, a.getMail());
            pstmt.setString(3, a.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
	public void delete(Account a) {
        String sql = "DELETE FROM account WHERE id = ?";
        try (Connection conn = Db.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, a.getId());
            pstmt.executeUpdate();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
}
