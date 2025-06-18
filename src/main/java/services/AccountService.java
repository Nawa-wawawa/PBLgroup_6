package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import beans.accounts;
import utils.Db;
public class AccountService {
	public List<accounts> select(String mail) {
		List<accounts> list = new ArrayList<>();
				String sql = "SELECT * FROM accounts";
//				String sql = "SELECT * FROM accounts";
//				String sql = "SELECT * FROM accounts";
				try (Connection conn = Db.open();
			             PreparedStatement pstmt = conn.prepareStatement(sql)) {
			            
			            pstmt.setString(1, mail);
			            ResultSet rs = pstmt.executeQuery();

			            while (rs.next()) {
			                accounts account = new accounts(
			                    rs.getInt("account_id"),
			                    rs.getString("name"),
			                    rs.getString("mail"),
			                    rs.getString("password"),
			                    rs.getByte("authority")
			                );
			                list.add(account);
			            }
			        } catch (SQLException | NamingException e) {
			            e.printStackTrace();
			        }
			        return list;
	}
	
	public boolean insert(accounts a) {
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

	public void update(accounts a) {
	    String sql = "UPDATE accounts SET name = ?, mail = ?, password = ?, authority = ? WHERE mail = ?";

	    try (Connection conn = Db.open();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, a.getName());
	        pstmt.setString(2, a.getMail());
	        pstmt.setString(3, a.getPassword());
	        pstmt.setInt(4, a.getAuthority());
	        pstmt.setString(5, a.getMail());  // WHERE句の条件（更新対象）

	        pstmt.executeUpdate();

	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    }
	}

	public void delete(int id) throws SQLException, NamingException {
	    String sql = "DELETE FROM accounts WHERE account_id = ?";

	    try (Connection conn = Db.open();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        
	        pstmt.setInt(1, id);
	        pstmt.executeUpdate();
	    }
	}

	public accounts findById(int id) {
		String sql = "SELECT * FROM accounts WHERE account_id = ?";
        try (Connection conn = Db.open();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                accounts account = new accounts(
                    rs.getInt("account_id"),
                    rs.getString("name"),
                    rs.getString("mail"),
                    rs.getString("password"),
                    rs.getByte("authority")
                );
                return account;
            }
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
        }
        return null;
    }
	
	public accounts findByAccount(String name, String mail, byte authority) {
	    String sql = "SELECT * FROM accounts WHERE name = ? AND mail = ? AND authority = ?";
	    try (Connection conn = Db.open();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setString(1, name);
	        pstmt.setString(2, mail);
	        pstmt.setByte(3, authority);

	        ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            return new accounts(
	                rs.getInt("id"),
	                rs.getString("name"),
	                rs.getString("mail"),
	                rs.getString("password"),
	                rs.getByte("authority")
	            );
	        }
	    } catch (SQLException | NamingException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	public accounts findByAccount(int id) {
        String sql = "SELECT id, name, mail, password, authority FROM accounts WHERE id = ?";

        try (Connection conn = Db.open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                accounts a = new accounts(
                rs.getInt("account_id"),
                rs.getString("name"),
                rs.getString("mail"),
                rs.getString("password"),
                rs.getByte("authority"));
                return a;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
