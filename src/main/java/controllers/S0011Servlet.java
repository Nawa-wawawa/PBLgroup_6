package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

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
 * Servlet implementation class S0011Servlet
 */
@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0011Servlet() {
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
			sales salesData = (sales) session.getAttribute("salesData");
			staffId = salesData.getAccount_id();
			categoryId = salesData.getCategory_id();
		} else {
			System.out.println("セッションが存在しません。");
		}

		categoryName = Sales.getCategoryNameById(categoryId);
		accountName = Sales.getAccountNameById(staffId);

		request.setAttribute("categoryName", categoryName);
		request.setAttribute("accountName", accountName);

		request.getRequestDispatcher("/WEB-INF/jsp/S0011.jsp").forward(request, response);
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
		sales salesData = null;

		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			salesData = (sales) session.getAttribute("salesData");
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

		//1-14
		//1-15

		//二回チェックしてもいいが、これも呼び出し。
		Map<String, String> errors = new LinkedHashMap<>();

		errors = check.useCheck(staff, category);

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
			return;
		}

		try (Connection con = Db.open()) {

			Sales sl = new Sales();

			sl.insert(salesData);
			session.removeAttribute("salesData");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/S0010.html");

	}

}
