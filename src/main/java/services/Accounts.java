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
				
				System.out.println(rs.getInt("account_id"));
				System.out.println(rs.getString("name"));
				System.out.println(rs.getString("mail"));
				System.out.println(rs.getString("password"));
				System.out.println(rs.getByte("authority"));
				
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
}
