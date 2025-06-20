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

import beans.getRequest;
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

			getRequest form = new getRequest(request);

			Map<String, String> errors = new LinkedHashMap<>();
			Salescheck check = new Salescheck();

			errors = check.useCheck(form);

			// --- エラー時処理 ---
			if (!errors.isEmpty()) {
				request.setAttribute("errors", errors);
				Sales.loadAccountAndCategory(request);
				request.getRequestDispatcher("/WEB-INF/jsp/S0023.jsp").forward(request, response);
				return;
			}

			// --- エラーなし時 ---

			Date sale_date = Date.valueOf(form.saleDate);
			int staff = Integer.parseInt(form.staff);
			int category = Integer.parseInt(form.category);
			String product_name = form.productName;
			int unit_price = Integer.parseInt(form.unitPrice);
			int quantity = Integer.parseInt(form.quantity);
			String remarks = form.remarks;

			sales salesData = new sales(sale_date, staff, category, product_name, unit_price, quantity, remarks);

			HttpSession session = request.getSession();

			session.setAttribute("picksale", salesData);

			response.sendRedirect(request.getContextPath() + "/S0024.html");

		} else {
			//キャンセルボタンの処理
			response.sendRedirect(request.getContextPath() + "/S0022.html");
		}
	}

}