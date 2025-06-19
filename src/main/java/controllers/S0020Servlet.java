package controllers;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.salescondition;
import services.Sales;

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

		Sales.loadAccountAndCategory(request);

		request.getRequestDispatcher("/WEB-INF/jsp/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date start_date = null;
		Date end_date = null;
		int staff = 0;
		int category = 0;
		boolean hasError = false;

		String startDateError = null;
		String endDateError = null;
		String noResultsError = null;

		// フォーマットチェック用
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setLenient(false); // 厳密にチェックする

		// --- 検索開始日 ---
		String startStr = request.getParameter("start_date");
		if (startStr != null && !startStr.isEmpty()) {
			try {
				java.util.Date parsed = sdf.parse(startStr);
				start_date = new Date(parsed.getTime());
			} catch (ParseException e) {
				startDateError = "販売日（検索開始日）を正しく入力して下さい。";
				hasError = true;
			}
		}

		// --- 検索終了日 ---
		String endStr = request.getParameter("end_date");
		if (endStr != null && !endStr.isEmpty()) {
			try {
				java.util.Date parsed = sdf.parse(endStr);
				end_date = new Date(parsed.getTime());
			} catch (ParseException e) {
				endDateError = "販売日（検索終了日）を正しく入力して下さい。";
				hasError = true;
			}
		}

		// 担当・カテゴリなど
		String staffStr = request.getParameter("staff");
		if (staffStr != null && !staffStr.isEmpty()) {
			try {
				staff = Integer.parseInt(staffStr);
			} catch (NumberFormatException e) {
				staff = 0;
			}
		}

		String categoryStr = request.getParameter("category");
		if (categoryStr != null && !categoryStr.isEmpty()) {
			try {
				category = Integer.parseInt(categoryStr);
			} catch (NumberFormatException e) {
				category = 0;
			}
		}

		String product_name = request.getParameter("product_name");
		String remarks = request.getParameter("remarks");

		// 条件保存
		salescondition serch_condition = new salescondition(start_date, end_date, staff, category, product_name, remarks);


		if (hasError) {
			// エラーがある場合は、JSPにフォワード
			request.setAttribute("startDateError", startDateError);
			request.setAttribute("endDateError", endDateError);
			request.setAttribute("noResultsError", noResultsError);
			Sales.loadAccountAndCategory(request);
			request.setAttribute("serch_condition", serch_condition);
			request.getRequestDispatcher("/WEB-INF/jsp/S0020.jsp").forward(request, response);
			return;
		}

		// 正常時
		HttpSession session = request.getSession();
		session.setAttribute("serch_condition", serch_condition);
		Sales.loadAccountAndCategory(request);
		response.sendRedirect(request.getContextPath() + "/S0021.html");
	}

}
