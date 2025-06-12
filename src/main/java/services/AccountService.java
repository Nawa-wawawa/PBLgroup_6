package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import beans.Account;
import utils.Db;

public class AccountService {
	public List<Account> select(String mail) {
		List<Account> list = new ArrayList<>();
				String sql = "SELECT * FROM accounts";
//				String sql = "SELECT * FROM accounts";
//				String sql = "SELECT * FROM accounts";
				try (Connection conn = Db.open();
			             PreparedStatement pstmt = conn.prepareStatement(sql)) {
			            
			            pstmt.setString(1, mail);
			            ResultSet rs = pstmt.executeQuery();

			            while (rs.next()) {
			                Account account = new Account(
			                    rs.getInt("account_id"),
			                    rs.getString("name"),
			                    rs.getString("mail"),
			                    rs.getString("password"),
			                    rs.getInt("authority")
			                );
			                list.add(account);
			            }
			        } catch (SQLException | NamingException e) {
			            e.printStackTrace();
			        }
			        return list;
	}
	
	public boolean insert(Account a) {
	    String sql = "INSERT INTO accounts (name, mail, password, authority) VALUES (?, ?, ?, ?)";
	    try (Connection conn = Db.open(); 
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setString(1, a.getName());
	        pstmt.setString(2, a.getMail());
	        pstmt.setString(3, a.getPassword());
	        pstmt.setInt(4, a.getAuthority());

	        int affectedRows = pstmt.executeUpdate();
	        return affectedRows > 0;
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	        return false;
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
		String sql = "DELETE FROM accounts WHERE account_id = ?";
        try (Connection conn = Db.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, a.getId());
            pstmt.executeUpdate();
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
    }
	public Account findById(int id) {
		String sql = "SELECT * FROM accounts WHERE account_id = ?";
        try (Connection conn = Db.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Account account = new Account(
                    rs.getInt("account_id"),
                    rs.getString("name"),
                    rs.getString("mail"),
                    rs.getString("password"),
                    rs.getInt("authority")
                );
                return account;
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public Account findByAccount(String name, String mail, int authority) {
	    String sql = "SELECT * FROM accounts WHERE name = ? AND mail = ? AND authority = ?";
	    try (Connection conn = Db.open();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, name);
	        pstmt.setString(2, mail);
	        pstmt.setInt(3, authority);

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return new Account(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("mail"),
	                rs.getString("password"),
	                rs.getInt("authority")
	            );
	        }
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
