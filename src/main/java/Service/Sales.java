package Service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.NamingException;

import Bean.sales;
import beans.Todo;
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

	public ArrayList<Todo> select(int i) {
		ArrayList<Todo> Todolist = new ArrayList<>();
		String sql = "SELECT tasklist.*, loglist.name ,loglist.id FROM tasklist JOIN loglist ON tasklist.user_id = logl.id;";
		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				if (i == rs.getInt("loglist.id")) {
					Todo todo = new Todo(
							rs.getInt("id"),
							rs.getString("status"),
							rs.getString("task"),
							LocalDate.parse(rs.getString("deadline")),
							rs.getString("loglist.name"));
					Todolist.add(todo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return Todolist;
	}

	public void insert(Todo todo, int id) {

		String sql = "INSERT INTO tasklist (status,task,deadline,user_id) VALUES (?,?,?,?)";
		//		int id = 0;

		try (Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql,
						java.sql.Statement.RETURN_GENERATED_KEYS);) {

			ps.setString(1, todo.getStatus());
			ps.setString(2, todo.getTask());
			ps.setDate(3, Date.valueOf(todo.getDeadline()));
			ps.setInt(4, id);

			//			ResultSet res = ps.getGeneratedKeys();

			ps.executeUpdate();

			//	System.out.println(todo.getStatus() + "service");
			//			if (res.next()) {
			//				id = res.getInt(1);
			//			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		//return id;
	}

	public void update(Todo todo, int id) {
		String sql = "UPDATE tasklist SET status = ?, task = ?, deadline = ?, user_id = ? WHERE id = ?";
		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql);) {
			ps.setString(1, todo.getStatus());
			ps.setString(2, todo.getTask());
			ps.setDate(3, Date.valueOf(todo.getDeadline()));
			ps.setInt(4, id);
			ps.setInt(5, todo.getId());
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
		String sql = "DELETE  FROM tasklist WHERE id = ?";

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
		String sql = "SELECT name FROM loglist WHERE id = ?";

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
