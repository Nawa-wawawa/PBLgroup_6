package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import services.Sales;
import services.Salescheck;
import utils.Db;

/**
 * Servlet implementation class S0024Servlet
 */
@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0024Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int staffId = 0;
		int categoryId = 0;

		String categoryName = "";
		String accountName = "";
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			staffId = (Integer) session.getAttribute("staff_id");
			categoryId = (Integer) session.getAttribute("category_id");
		} else {
			System.out.println("セッションが存在しません。");
		}

		categoryName = Sales.getCategoryNameById(categoryId);
		accountName = Sales.getAccountNameById(staffId);

		request.setAttribute("categoryname", categoryName);
		request.setAttribute("accountname", accountName);

		request.getRequestDispatcher("/WEB-INF/jsp/S0024.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date sale_date = null;
		int staff = 0;
		int category = 0;
		String product_name = "";
		int unit_price = 0;
		int quantity = 0;
		String remarks = "";

		int saleId = 0;

		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			sales salesData = (sales) session.getAttribute("salesData");
			sale_date = salesData.getSale_date();
			staff = salesData.getAccount_id();
			category = salesData.getCategory_id();
			product_name = salesData.getTrade_name();
			unit_price = salesData.getUnit_price();
			quantity = salesData.getSale_number();
			remarks = salesData.getNote();
		} else {
			System.out.println("セッションが存在しません。");
		}

		Salescheck check = new Salescheck();
		boolean hasError = false;

		//1-14
		//1-15
		Map<String, String> errors = new LinkedHashMap<>();
		// Map<キー, {関数, 値, エラーメッセージ}>
		Map<String, Object[]> validations = new LinkedHashMap<>();
		validations.put("error_name",
				new Object[] { (IntPredicate) check::accountCheck, staff, "エラーメッセージ：アカウントテーブルに存在しません。" });
		validations.put("error_price",
				new Object[] { (IntPredicate) check::categoryCheck, category, "エラーメッセージ：商品カテゴリーテーブルに存在しません。" });

		// 共通ループでチェック
		for (Map.Entry<String, Object[]> entry : validations.entrySet()) {
			String key = entry.getKey();
			Object[] valueArray = entry.getValue();

			Object function = valueArray[0];
			Object value = valueArray[1];
			String message = (String) valueArray[2];

			boolean isError = false;
			if (function instanceof Predicate) {
				isError = ((Predicate<String>) function).test((String) value);
			} else if (function instanceof IntPredicate) {
				isError = ((IntPredicate) function).test((int) value);
			}

			if (isError) {
				errors.put(key, message);
				hasError = true;
				System.out.println(message);
			}
		}
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
		}

		if (hasError) {
			request.getRequestDispatcher("/WEB-INF/jsp/S0024.jsp").forward(request, response);
		} else {

			try (Connection con = Db.open()) {

				Sales sl = new Sales();

				sales Newsale = new sales(sale_date, staff, category, product_name, unit_price, quantity, remarks);

				sl.update(Newsale, saleId);

			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}
			response.sendRedirect(request.getContextPath() + "/S0021.html");
		}

	}
}
