package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import beans.categories;
import utils.Db;

public class Categories {

	public ArrayList<categories> select() {
		ArrayList<categories> categorylist = new ArrayList<>();
		String sql = "SELECT * FROM categories;";
		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				categories cate = new categories(

						rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));

				categorylist.add(cate);
			//	System.out.println(rs.getString("category_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		return categorylist;
	}
	

	public String getCategoryname(int id) {

		String categoryName = "";
		String sql = "SELECT category_name FROM categories WHERE category_id = ?";

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql)) {

			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				categoryName = rs.getString("category_name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return categoryName;
	}

}
