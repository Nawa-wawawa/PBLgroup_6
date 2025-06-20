package services;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;

import beans.request;
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

	public Map<String, String> useCheck(request form) {

		Map<String, String> errors = new LinkedHashMap<>();
		Salescheck check = new Salescheck();

		// --- 1. 必須チェック & 型変換 ---
		Date sale_date = null;
		int staff = 0;
		int category = 0;
		int unit_price = 0;
		int quantity = 0;
	
		// 販売日チェック（簡易）
		if (form.saleDate == null || form.saleDate.isEmpty()) {
			errors.put("error_sale_date_required", "販売日を入力してください。");
		} else {
			try {
				sale_date = Date.valueOf(form.saleDate);
			} catch (IllegalArgumentException e) {
				errors.put("error_sale_date_format", "販売日を正しく入力してください。");
			}
		}

		// 担当者必須チェック
		if (form.staff == null || form.staff.isEmpty()) {
			errors.put("error_staff_required", "担当者が未選択です。");
		} else {
			try {
				staff = Integer.parseInt(form.staff);
			} catch (NumberFormatException e) {
				errors.put("error_staff_required", "担当者の値が不正です。");
			}
		}

		// カテゴリ必須チェック
		if (form.category == null || form.category.isEmpty()) {
			errors.put("error_category_required", "商品カテゴリが未選択です。");
		} else {
			try {
				category = Integer.parseInt(form.category);
			} catch (NumberFormatException e) {
				errors.put("error_category_required", "カテゴリの値が不正です。");
			}
		}

		//商品名必須入力チェック
		if (form.productName == null || form.productName.isEmpty()) {
			errors.put("error_product_name_required", "商品名を入力してください。");
		} else if (check.productCheck(form.productName)) {
			errors.put("error_name", "商品名が長すぎます。");
		}

		// 単価チェック
		// 1. 未入力エラーのチェック
		if (form.unitPrice == null || form.unitPrice.trim().isEmpty()) {
			errors.put("error_unit_price_format", "単価を入力して下さい。");
		}
		// 2. 形式エラーのチェック
		else if (!form.unitPrice.matches("^[0-9]+$")) {
			errors.put("error_unit_price_format", "単価を正しく入力して下さい。");
		}
		// 3. 価格長さチェック
		else {
			try {
				unit_price = Integer.parseInt(form.unitPrice);
			} catch (NumberFormatException e) {
				errors.put("error_unit_price_format", "単価を入力して下さい。");
			}

			if (!errors.containsKey("error_unit_price_format")) {
				if (check.priceCheck(unit_price)) {
					errors.put("error_price", "単価が長すぎます。");
				}
			}
		}

		// 個数チェック
		// 1. 未入力エラーのチェック
		if (form.unitPrice == null || form.unitPrice.trim().isEmpty()) {
			errors.put("error_quantity_format", "個数を入力してください。");
		}
		// 2. 形式エラーのチェック
		else if (!form.unitPrice.matches("^[0-9]+$")) {
			errors.put("error_quantity_format", "個数を正しく入力してください。");
		}
		// 3. 個数長さチェック
		else {
			try {
				quantity = Integer.parseInt(form.unitPrice);
			} catch (NumberFormatException e) {
				errors.put("error_quantity_format", "個数を入力してください。");
			}

			if (!errors.containsKey("error_quantity_format")) {
				if (check.quantityCheck(quantity)) {
					errors.put("error_quantity", "個数が長すぎます。");
				}
			}
		}

		//備考長さチェック
		if (form.remarks != null && check.remarksCheck(form.remarks)) {
			errors.put("error_remarks", "備考が長すぎます。");
		}

		// --- 2. 存在チェック ---

		// 担当者存在チェック
		try {
			Accounts ac = new Accounts();
			if (!ac.exists(staff)) {
				errors.put("error_staff_not_found", "アカウントテーブルに存在しません。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.put("error_staff_not_found", "担当者の確認時にエラーが発生しました。");
		}

		// カテゴリ存在チェック
		try {
			Categories ct = new Categories();
			if (!ct.exists(category)) {
				errors.put("error_category_not_found", "商品カテゴリテーブルに存在しません。");
			}
		} catch (Exception e) {
			e.printStackTrace();
			errors.put("error_category_not_found", "カテゴリの確認時にエラーが発生しました。");
		}

		return errors;
	}

}
