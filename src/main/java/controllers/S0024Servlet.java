package controllers;

import java.io.IOException;
import java.sql.Connection;
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

		//1-14
		//1-15

		Map<String, String> errors = new LinkedHashMap<>();

		errors = check.useCheck(staff, category);

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/WEB-INF/jsp/S0024.jsp").forward(request, response);
		}

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
