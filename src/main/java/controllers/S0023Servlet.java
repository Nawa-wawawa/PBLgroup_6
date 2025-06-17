package controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

		request.getRequestDispatcher("/WEB-INF/jsp/S0023.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("action"));

		if (action == 1) {

			Date sale_date = Date.valueOf(request.getParameter("sale_date"));
			int staff = Integer.parseInt(request.getParameter("staff"));
			int category = Integer.parseInt(request.getParameter("category"));
			String product_name = request.getParameter("product_name");
			int unit_price = Integer.parseInt(request.getParameter("unit_price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			String remarks = request.getParameter("remarks");

			Salescheck check = new Salescheck();
			boolean hasError = false;

			Map<String, String> errors = new LinkedHashMap<>();
			// Map<キー, {関数, 値, エラーメッセージ}>
			Map<String, Object[]> validations = new LinkedHashMap<>();
			//1-6
			validations.put("error_name",
					new Object[] { (Predicate<String>) check::productCheck, product_name, "エラーメッセージ：商品名が長すぎます。" });
			//1-8
			validations.put("error_price",
					new Object[] { (IntPredicate) check::priceCheck, unit_price, "エラーメッセージ：単価が長すぎます。" });
			//1-11
			validations.put("error_quantity",
					new Object[] { (IntPredicate) check::quantityCheck, quantity, "エラーメッセージ：個数が長すぎます。" });
			//1-13
			validations.put("error_remarks",
					new Object[] { (Predicate<String>) check::remarksCheck, remarks, "エラーメッセージ：備考が長すぎます。" });

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
				request.setAttribute("errors", errors); // JSPへ渡す
			}
			if (hasError) {
				request.getRequestDispatcher("/WEB-INF/jsp/S0023.jsp").forward(request, response);
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("sale_date", sale_date);
				session.setAttribute("staff_id", staff);
				session.setAttribute("category_id", category);
				session.setAttribute("product_name", product_name);
				session.setAttribute("unit_price", unit_price);
				session.setAttribute("quantity", quantity);
				session.setAttribute("remarks", remarks);
				//編集の前に編集権限があるのかをログイン中のアカウント権限と参照
				response.sendRedirect(request.getContextPath() + "/S0024.html");
			}

			

		} else {
			response.sendRedirect(request.getContextPath() + "/S0022.html");
		}

	}
}
