package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import services.SalesService;
import services.Salescheck;

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

		categoryName = SalesService.getCategoryNameById(categoryId);
		accountName = SalesService.getAccountNameById(staffId);

		request.setAttribute("categoryName", categoryName);
		request.setAttribute("accountName", accountName);

		request.getRequestDispatcher("/WEB-INF/jsp/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int staff = 0;
		int category = 0;

		sales salesData = null;

		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			salesData = (sales) session.getAttribute("salesData");

			staff = salesData.getAccount_id();
			category = salesData.getCategory_id();

		} else {
			System.out.println("セッションが存在しません。");
		}

		Salescheck check = new Salescheck();

		//1-14
		//1-15

//		//二回チェックしてもいいが、これも呼び出し。
//		Map<String, String> errors = new LinkedHashMap<>();
//
//		errors = check.useCheck(staff, category);
//
//		if (!errors.isEmpty()) {
//			request.setAttribute("errors", errors);
//			request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
//			return;
//		}

		SalesService sl = new SalesService();
		sl.insert(salesData,request, response);
		session.removeAttribute("salesData");

		response.sendRedirect(request.getContextPath() + "/S0010.html");

	}

}
