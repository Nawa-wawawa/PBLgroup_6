package controllers;

import java.io.IOException;
import java.sql.Connection;
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

		sales salesData = null;

		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			salesData = (sales) session.getAttribute("picksale");
			staffId = salesData.getAccount_id();
			categoryId = salesData.getCategory_id();
		} else {
			System.out.println("セッションが存在しません。");
		}

		categoryName = Sales.getCategoryNameById(categoryId);
		accountName = Sales.getAccountNameById(staffId);

		request.setAttribute("categoryName", categoryName);
		request.setAttribute("accountName", accountName);

		request.getRequestDispatcher("/WEB-INF/jsp/S0024.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int staff = 0;
		int category = 0;
		int saleId = 0;
		sales salesData = null;

		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			salesData = (sales) session.getAttribute("picksale");
			saleId = (int) session.getAttribute("saleId");
			staff = salesData.getAccount_id();
			category = salesData.getCategory_id();

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
			if (function instanceof Predicate<?>) {
				if (value instanceof String str) {
					@SuppressWarnings("unchecked")
					Predicate<String> predicate = (Predicate<String>) function;
					isError = predicate.test(str);
				}
			} else if (function instanceof IntPredicate) {
				if (value instanceof Integer i) {
					isError = ((IntPredicate) function).test(i);
				}
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

				sales Newsale = salesData;

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
