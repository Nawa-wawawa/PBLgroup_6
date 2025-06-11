package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import Bean.sales;
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
	public void insert(sales sale) {

		String sql = "INSERT INTO sales ( sale_date, account_id, category_id, trade_name, unit_price, sale_number, note) VALUES (?,?,?,?,?,?,?)";


		try (Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql,
						java.sql.Statement.RETURN_GENERATED_KEYS);) {
			
			ps.setDate(1,sale.getSale_date());
			ps.setInt(2,sale.getAccount_id());
			ps.setInt(3,sale.getCategory_id());
			ps.setString(4,sale.getTrade_name());					
			ps.setInt(5,sale.getUnit_price());
			ps.setInt(6,sale.getSale_number());
			ps.setString(7,sale.getNote());
			
			
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
			
			
			ps.setDate(1,sale.getSale_date());
			ps.setInt(2,sale.getAccount_id());
			ps.setInt(3,sale.getCategory_id());
			ps.setString(4,sale.getTrade_name());					
			ps.setInt(5,sale.getUnit_price());
			ps.setInt(6,sale.getSale_number());
			ps.setString(7,sale.getNote());
			ps.setInt(8,id);
			
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

	
	
}
