package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import froms.InsertSaleform;
import services.SalesService;
import services.Salescheck;

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

		SalesService.loadAccountAndCategory(request);

		request.setAttribute("today", today);

		request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		InsertSaleform form = new InsertSaleform(request);

		//サービスにして再度使うとこは呼び出しできるように。
		Map<String, String> errors = new LinkedHashMap<>();
		Salescheck check = new Salescheck();

		errors = check.useCheck(form);
		// --- 4. エラー時処理 ---

		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);

			SalesService.loadAccountAndCategory(request);

			request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
			return;
		}

		// --- 5. エラーなし時の処理（セッションにセット） ---
		sales salesData = new sales(form);

		HttpSession session = request.getSession();
		session.setAttribute("salesData", salesData);

		response.sendRedirect(request.getContextPath() + "/S0011.html");
	}

}