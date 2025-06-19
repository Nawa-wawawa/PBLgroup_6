package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import beans.categories;
import services.Accounts;
import services.Categories;
import services.Salescheck;
import utils.Db;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0010Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String today = LocalDate.now().toString();
		ArrayList<accounts> accountslist = null;
		ArrayList<categories> categorylist = null;

		//別のサービスとして作って呼び出しにしたい。
		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			categorylist = ct.select();
			Accounts ac = new Accounts();
			accountslist = ac.select();
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}

		request.setAttribute("today", today);
		request.setAttribute("categorylist", categorylist);
		request.setAttribute("accountslist", accountslist);

		request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String saleDateStr = request.getParameter("sale_date");
		String staffStr = request.getParameter("staff");
		String categoryStr = request.getParameter("category");
		String product_name = request.getParameter("product_name");
		String unitPriceStr = request.getParameter("unit_price");
		String quantityStr = request.getParameter("quantity");
		String remarks = request.getParameter("remarks");

		
		//サービスにして再度使うとこは呼び出しできるように。
		Map<String, String> errors = new LinkedHashMap<>();
		Salescheck check = new Salescheck();

		// --- 1. 必須チェック & 型変換 ---
		Date sale_date = null;
		int staff = 0;
		int category = 0;
		int unit_price = 0;
		int quantity = 0;

		// 販売日チェック（簡易）
		if (saleDateStr == null || saleDateStr.isEmpty()) {
			errors.put("error_sale_date_required", "販売日を入力してください。");
		} else {
			try {
				sale_date = Date.valueOf(saleDateStr);
			} catch (IllegalArgumentException e) {
				errors.put("error_sale_date_format", "販売日を正しく入力してください。");
			}
		}

		// 担当者必須チェック
		if (staffStr == null || staffStr.isEmpty()) {
			errors.put("error_staff_required", "担当者が未選択です。");
		} else {
			try {
				staff = Integer.parseInt(staffStr);
			} catch (NumberFormatException e) {
				errors.put("error_staff_required", "担当者の値が不正です。");
			}
		}

		// カテゴリ必須チェック
		if (categoryStr == null || categoryStr.isEmpty()) {
			errors.put("error_category_required", "商品カテゴリが未選択です。");
		} else {
			try {
				category = Integer.parseInt(categoryStr);
			} catch (NumberFormatException e) {
				errors.put("error_category_required", "カテゴリの値が不正です。");
			}
		}
		
		//商品名必須入力チェック
		if (product_name == null || product_name.isEmpty()) {
			errors.put("error_product_name_required", "商品名を入力してください。");
		} else if (check.productCheck(product_name)) {
			errors.put("error_name", "商品名が長すぎます。");
		}

		// 単価チェック
		// 1. 未入力エラーのチェック
		if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
		    errors.put("error_unit_price_format", "単価を入力して下さい。");
		} 
		// 2. 形式エラーのチェック
		else if (!unitPriceStr.matches("^[0-9]+$")) {
		    errors.put("error_unit_price_format", "単価を正しく入力して下さい。");
		} 
		// 3. 価格長さチェック
		else {
		    try {
		        unit_price = Integer.parseInt(unitPriceStr);
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
		if (quantityStr == null || quantityStr.trim().isEmpty()) {
		    errors.put("error_quantity_format", "個数を入力してください。");
		} 
		// 2. 形式エラーのチェック
		else if (!quantityStr.matches("^[0-9]+$")) {
		    errors.put("error_quantity_format", "個数を正しく入力してください。");
		} 
		// 3. 個数長さチェック
		else {
		    try {
		        quantity = Integer.parseInt(quantityStr);
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
		if (remarks != null && check.remarksCheck(remarks)) {
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

		// --- 4. エラー時処理 ---

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);

			// 入力値を保持
			request.setAttribute("today", saleDateStr);
			// アカウントとカテゴリリストもセット（doGet同様）
			try {
				request.setAttribute("accountslist", new Accounts().select());
				request.setAttribute("categorylist", new Categories().select());
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 入力値をparamに渡すために
			request.setAttribute("param", request.getParameterMap());

			request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
			return;
		}

		// --- 5. エラーなし時の処理（セッションにセット） ---

		
		//まとめる！！！
		HttpSession session = request.getSession();
		session.setAttribute("sale_date", sale_date);
		session.setAttribute("staff_id", staff);
		session.setAttribute("category_id", category);
		session.setAttribute("product_name", product_name);
		session.setAttribute("unit_price", unit_price);
		session.setAttribute("quantity", quantity);
		session.setAttribute("remarks", remarks);

		response.sendRedirect(request.getContextPath() + "/S0011.html");
	}

}