package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import services.Sales;
import services.Salescheck;

/**
 * Servlet implementation class S0023Servlet
 */
@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0023Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Sales.loadAccountAndCategory(request);
		request.getRequestDispatcher("/WEB-INF/jsp/S0023.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    int action = Integer.parseInt(request.getParameter("action"));

	    if (action == 1) {

	        String saleDateStr = request.getParameter("sale_date");
	        String staffStr = request.getParameter("staff");
	        String categoryStr = request.getParameter("category");
	        String product_name = request.getParameter("product_name");
	        String unitPriceStr = request.getParameter("unit_price");
	        String quantityStr = request.getParameter("quantity");
	        String remarks = request.getParameter("remarks");

	        
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

	        // 商品名必須チェック & 長さチェック
	        if (product_name == null || product_name.isEmpty()) {
	            errors.put("error_product_name_required", "商品名を入力してください。");
	        } else if (check.productCheck(product_name)) {
	            errors.put("error_name", "商品名が長すぎます。");
	        }

	        // 単価チェック
	        if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
	            errors.put("error_unit_price_format", "単価を入力して下さい。");
	        } else if (!unitPriceStr.matches("^[0-9]+$")) {
	            errors.put("error_unit_price_format", "単価を正しく入力して下さい。");
	        } else {
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
	        if (quantityStr == null || quantityStr.trim().isEmpty()) {
	            errors.put("error_quantity_format", "個数を入力してください。");
	        } else if (!quantityStr.matches("^[0-9]+$")) {
	            errors.put("error_quantity_format", "個数を正しく入力してください。");
	        } else {
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

	        // 備考長さチェック
	        if (remarks != null && check.remarksCheck(remarks)) {
	            errors.put("error_remarks", "備考が長すぎます。");
	        }

	        // --- 2. 存在チェック ---
	        try {
	            services.Accounts ac = new services.Accounts();
	            if (!ac.exists(staff)) {
	                errors.put("error_staff_not_found", "アカウントテーブルに存在しません。");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            errors.put("error_staff_not_found", "担当者の確認時にエラーが発生しました。");
	        }

	        try {
	            services.Categories ct = new services.Categories();
	            if (!ct.exists(category)) {
	                errors.put("error_category_not_found", "商品カテゴリテーブルに存在しません。");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            errors.put("error_category_not_found", "カテゴリの確認時にエラーが発生しました。");
	        }

	        
	        
	        
	        // --- エラー時処理 ---
	        if (!errors.isEmpty()) {
	            request.setAttribute("errors", errors);

	            request.getRequestDispatcher("/WEB-INF/jsp/S0023.jsp").forward(request, response);
	            return;
	        }

	        // --- エラーなし時 ---
	        
	        sales salesData = new sales(sale_date,staff ,category ,product_name , unit_price, quantity, remarks);

	        
	        HttpSession session = request.getSession();
	        
	        session.setAttribute("picksale",salesData);


	        response.sendRedirect(request.getContextPath() + "/S0024.html");

	    } else {
	        response.sendRedirect(request.getContextPath() + "/S0022.html");
	    }
	}

}