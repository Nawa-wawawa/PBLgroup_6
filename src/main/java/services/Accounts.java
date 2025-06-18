package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.accounts;
import utils.Db;

public class Accounts {
	public ArrayList<accounts> select() {
		ArrayList<accounts> list = new ArrayList<>();
		String sql = "SELECT * FROM accounts";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				accounts ac = new accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getByte("authority"));
				list.add(ac);			
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public String getAccountname(int id) {

		String accountName = "";
		String sql = "SELECT name FROM accounts WHERE account_id = ?";

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				accountName = rs.getString("name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return accountName;
	}
	
	public boolean exists(int accountId) {
        String sql = "SELECT COUNT(*) FROM accounts WHERE account_id = ?";
        try (Connection con = Db.open();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
			e.printStackTrace();
		}
        return false;
    }
}
