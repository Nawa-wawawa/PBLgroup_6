package services;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import utils.Db;

public class Salescheck {

	//商品名入力時、 形式が異なればerror、の次に101バイト以上入力時はエラー。						

	public boolean productCheck(String name) {

		if (name == null)
			return true;
		try {
			return name.getBytes("UTF-8").length > 100;
		} catch (UnsupportedEncodingException e) {
			return true; // エラー時は保守的にエラーと扱う
		}
	}

	//単価を入力時、10桁以上入力時はエラー。

	public boolean priceCheck(int n) {
		if (n == 0)
			return true;
		return n > 1000000000;
	}

	//個数を入力時、10桁以上入力時はエラー。

	public boolean quantityCheck(int n) {
		if (n == 0)
			return true;
		return n > 1000000000;
	}

	//備考を入力時、400バイト以上入力時はエラー
	public boolean remarksCheck(String remark) {
		if (remark == null)
			return true;
		try {
			return remark.getBytes("UTF-8").length > 400;
		} catch (UnsupportedEncodingException e) {
			return true; // エラー時は保守的にエラーと扱う
		}
	}

	public boolean accountCheck(int account_id) {

		boolean checker = true;

		String sql = "SELECT name FROM accounts WHERE account_id = ?";

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql)) {

			ps.setInt(1, account_id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				checker = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return checker;
	}

	public boolean categoryCheck(int category_id) {

		boolean checker = true;
		String sql = "SELECT category_name FROM categories WHERE category_id = ?";

		try (
				Connection use_connection = Db.open();
				PreparedStatement ps = use_connection.prepareStatement(sql)) {

			ps.setInt(1, category_id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				checker = false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return checker;
	}

}
