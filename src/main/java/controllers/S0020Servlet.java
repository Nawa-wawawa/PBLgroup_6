package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.salescondition;
import froms.SerchSalesform;
import services.SalesService;
import services.Salescheck;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0020Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		SalesService.loadAccountAndCategory(request);

		request.getRequestDispatcher("/WEB-INF/jsp/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String startDateError = null;
		String endDateError = null;
		Salescheck sl = new Salescheck();

		SerchSalesform form = new SerchSalesform(request);

		// --- 検索開始日 ---
		startDateError = sl.useDaycheck(form.start_date, 0);
		// --- 検索終了日 ---
		endDateError = sl.useDaycheck(form.end_date, 1);

		form.setCategory(sl.Intcheck(form.category));
		form.setStaff(sl.Intcheck(form.staff));

		System.out.println(form.end_date);


		if (startDateError != null || endDateError != null) {
			// エラーがある場合は、JSPにフォワード
			request.setAttribute("startDateError", startDateError);
			request.setAttribute("endDateError", endDateError);
			SalesService.loadAccountAndCategory(request);
			request.getRequestDispatcher("/WEB-INF/jsp/S0020.jsp").forward(request, response);
			return;
		}
		
		// 条件保存
		salescondition serch_condition = new salescondition(form);
		
		// 正常時
		HttpSession session = request.getSession();
		session.setAttribute("serch_condition", serch_condition);
		SalesService.loadAccountAndCategory(request);
		response.sendRedirect(request.getContextPath() + "/S0021.html");
	}
}