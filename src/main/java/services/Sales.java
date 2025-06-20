package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import jakarta.servlet.ServletRequest;

import beans.accounts;
import beans.categories;
import beans.sales;
import beans.salescondition;
import utils.Db;

public class Sales {

	public ArrayList<sales> select() {
		ArrayList<sales> salelist = new ArrayList<>();
		String sql = "SELECT sales.*, categories.category_name FROM sales JOIN categories ON sales.category_id = categories.category_id;";
		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql);

				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				sales sale = new sales(
						rs.getInt("sale_id"),
						rs.getDate("sale_date"),
						rs.getInt("account_id"),
						rs.getInt("category_id"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"));
				salelist.add(sale);
				System.out.println(rs.getString("categories.category_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return salelist;
	}

	public ArrayList<sales> select(salescondition serch) {
		ArrayList<sales> salelist = new ArrayList<>();
		String sql = "SELECT * FROM sales WHERE 1=1";
		List<Object> params = new ArrayList<>();

		if (serch.getStart_date() != null) {
			sql += " AND sale_date >= ?";
			params.add(serch.getStart_date());
		}
		if (serch.getEnd_date() != null) {
			sql += " AND sale_date <= ?";
			params.add(serch.getEnd_date());
		}
		if (serch.getAccount_id() != 0) {
			sql += " AND account_id = ?";
			params.add(serch.getAccount_id());
		}
		if (serch.getCategory_id() != 0) {
			sql += " AND category_id = ?";
			params.add(serch.getCategory_id());
		}
		if (serch.getTrade_name() != null && !serch.getTrade_name().isEmpty()) {
			sql += " AND trade_name LIKE ?";
			params.add("%" + serch.getTrade_name() + "%");
		}
		if (serch.getNote() != null && !serch.getNote().isEmpty()) {
			sql += " AND note LIKE ?";
			params.add("%" + serch.getNote() + "%");
		}

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql)) {
			// パラメータを順にバインド
			for (int i = 0; i < params.size(); i++) {
				Object param = params.get(i);
				if (param instanceof Date) {
					ps.setDate(i + 1, (Date) param);
				} else if (param instanceof Integer) {
					ps.setInt(i + 1, (Integer) param);
				} else if (param instanceof String) {
					ps.setString(i + 1, (String) param);
				}
			}

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					sales sale = new sales(
							rs.getInt("sale_id"),
							rs.getDate("sale_date"),
							rs.getInt("account_id"),
							rs.getInt("category_id"),
							rs.getString("trade_name"),
							rs.getInt("unit_price"),
							rs.getInt("sale_number"),
							rs.getString("note"));
					salelist.add(sale);
				}
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}

		return salelist;
	}

	public void insert(sales sale) {

		String sql = "INSERT INTO sales ( sale_date, account_id, category_id, trade_name, unit_price, sale_number, note) VALUES (?,?,?,?,?,?,?)";

		try (Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql,
						java.sql.Statement.RETURN_GENERATED_KEYS);) {

			ps.setDate(1, sale.getSale_date());
			ps.setInt(2, sale.getAccount_id());
			ps.setInt(3, sale.getCategory_id());
			ps.setString(4, sale.getTrade_name());
			ps.setInt(5, sale.getUnit_price());
			ps.setInt(6, sale.getSale_number());
			ps.setString(7, sale.getNote());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		//return id;
	}

	public void update(sales sale, int id) {
		String sql = "UPDATE sales 	SET sale_date = ?, account_id = ?, category_id = ?, trade_name = ?, unit_price = ?, sale_number = ?, note = ?WHERE sale_id = ?";
		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql);) {

			ps.setDate(1, sale.getSale_date());
			ps.setInt(2, sale.getAccount_id());
			ps.setInt(3, sale.getCategory_id());
			ps.setString(4, sale.getTrade_name());
			ps.setInt(5, sale.getUnit_price());
			ps.setInt(6, sale.getSale_number());
			ps.setString(7, sale.getNote());
			ps.setInt(8, id);

			ps.executeUpdate();
			System.out.println("更新の完了");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
	}

	public void delete(int id) {
		String sql = "DELETE  FROM sales WHERE sale_id = ?";

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql);) {
			ps.setInt(1, id);
			int result = ps.executeUpdate();
			System.out.println(result);
			System.out.println("削除の完了");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

	}

	public String getUsername(int id) {

		String userName = "";
		String sql = "SELECT name FROM sales WHERE id = ?";

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				userName = rs.getString("name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return userName;
	}

	public static void loadAccountAndCategory(ServletRequest request) {
		ArrayList<accounts> accountslist = null;
		ArrayList<categories> categorylist = null;

		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			categorylist = ct.select();
			Accounts ac = new Accounts();
			accountslist = ac.select();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}

		request.setAttribute("accountslist", accountslist);
		request.setAttribute("categorylist", categorylist);
	}

	public static String getCategoryNameById(int categoryId) {
		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			return ct.getCategoryname(categoryId);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getAccountNameById(int staffId) {
		try (Connection con = Db.open()) {
			Accounts ac = new Accounts();
			return ac.getAccountname(staffId);
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
			return "";
		}
	}

}
