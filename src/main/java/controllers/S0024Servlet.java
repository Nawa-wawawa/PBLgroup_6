package controllers;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import beans.salescondition;
import services.SalesService;
import services.Salescheck;

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

		salescondition serch_condition = (salescondition) session.getAttribute("serch_condition");
		salesData = (sales) session.getAttribute("picksale");

		if (serch_condition == null || salesData == null) {

			response.sendRedirect("S0020.html");
			return;
		}

		// 例：int型IDとして使いたい場合（Integer型にキャスト）
		staffId = salesData.getAccount_id();
		categoryId = salesData.getCategory_id();

		categoryName = SalesService.getCategoryNameById(categoryId);
		accountName = SalesService.getAccountNameById(staffId);

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
		// 例：int型IDとして使いたい場合（Integer型にキャスト）
		salesData = (sales) session.getAttribute("picksale");

		if (salesData == null) {

			response.sendRedirect("S0020.html");
			return;
		}

		saleId = (int) session.getAttribute("saleId");
		staff = salesData.getAccount_id();
		category = salesData.getCategory_id();

		Salescheck check = new Salescheck();

		//1-14
		//1-15

		Map<String, String> errors = new LinkedHashMap<>();

		errors = check.useCheck(staff, category);

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			SalesService.loadAccountAndCategory(request);
			request.getRequestDispatcher("/WEB-INF/jsp/S0024.jsp").forward(request, response);
		}
		SalesService sl = new SalesService();
		sales Newsale = salesData;
		sl.update(Newsale, saleId, request, response);

		session.removeAttribute("saleslist");
		session.removeAttribute("saleId");
		session.removeAttribute("aName");
		session.removeAttribute("cName");
		session.removeAttribute("picksale");

		response.sendRedirect(request.getContextPath() + "/S0021.html");
	}
}
