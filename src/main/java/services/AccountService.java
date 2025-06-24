package services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import utils.Db;

public class AccountService {

	public ArrayList<accounts> select() {
		ArrayList<accounts> list = new ArrayList<>();
		String sql = "SELECT * FROM accounts";

		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				accounts a = new accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getByte("authority"));
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void insert(accounts a, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "INSERT INTO accounts (name, mail, password, authority) VALUES (?, ?, ?, ?)";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getMail());
			pstmt.setString(3, a.getPassword());
			pstmt.setInt(4, a.getAuthority());

			pstmt.executeUpdate();
		} catch (Exception e) {
			request.setAttribute("error", "登録に失敗しました: " + e.getMessage());
			request.setAttribute("account", a);
			request.getRequestDispatcher("/WEB-INF/jsp/S0031.jsp").forward(request, response);
		}
	}

	public void update(accounts a, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "UPDATE accounts SET name = ?, mail = ?, password = ?, authority = ? WHERE account_id = ?";

		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, a.getName());
			pstmt.setString(2, a.getMail());
			pstmt.setString(3, a.getPassword());
			pstmt.setInt(4, a.getAuthority());
			pstmt.setInt(5, a.getAccount_id()); // WHERE句の条件（更新対象）

			pstmt.executeUpdate();

		} catch (SQLException | NamingException e) {
			request.setAttribute("error", "更新に失敗しました: " + e.getMessage());
			request.setAttribute("hasSales", (a.getAuthority() & 1) != 0);
			request.setAttribute("hasAccountReg", (a.getAuthority() & 2) != 0);
			request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
		}
	}

	public void delete(accounts a) {
		String sql = "DELETE FROM accounts WHERE id = ?";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, a.getAccount_id());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(int id, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String sql = "DELETE FROM accounts WHERE account_id = ?";
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			request.setAttribute("error", "削除に失敗しました: " + e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
		}
	}

	public accounts findById(int account_id) {

		String sql = "SELECT * FROM accounts WHERE account_id = ?";

		accounts accounts = null;

		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, account_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				accounts = new accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getByte("authority"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public ArrayList<accounts> findByAccount(String name, String mail, byte authority) {

		StringBuilder sql = new StringBuilder("SELECT * FROM accounts WHERE 1=1 ");
		ArrayList<Object> params = new ArrayList<>();
		ArrayList<accounts> accounts = new ArrayList<>();

		if (name != "") {

			sql.append(" AND name LIKE ?");

			params.add("%" + name + "%");

		}
		if (mail != "") {

			sql.append(" AND mail = ?");

			params.add(mail);

		}
		if (authority != 0) {

			sql.append(" AND authority = ?");

			byte hikizan = 1;
			authority -= hikizan;

			params.add(authority);

		}
		try (Connection conn = Db.open();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			for (int i = 0; i < params.size(); i++) {

				Object param = params.get(i);

				if (param instanceof String) {
					pstmt.setString(i + 1, (String) param);
				} else if (param instanceof Byte) {
					pstmt.setByte(i + 1, (Byte) param);
				}
			}
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				accounts account = new accounts(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getByte("authority"));
				accounts.add(account);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(sql);
		return accounts;
	}

	public byte authorityConvert(String[] n) {
		byte authority = 0;
		if (n != null) {
			for (String role : n) {
				if ("salesregister".equals(role)) {
					authority |= 1; // 売上登録
				} else if ("accountregister".equals(role)) {
					authority |= 2; // アカウント登録
				}
			}
		}
		return authority;
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
