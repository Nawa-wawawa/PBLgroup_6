package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import beans.salescondition;
import services.SalesService;

/**
 * Servlet implementation class S0025Servlet
 */
@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0025Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sales salesData = null;

		HttpSession session = request.getSession(false);

		salesData = (sales) session.getAttribute("picksale");
		salescondition serch_condition = (salescondition) session.getAttribute("serch_condition");
		if (serch_condition == null || salesData == null) {

			response.sendRedirect("S0020.html");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/S0025.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("action"));

		if (action == 1) {
			HttpSession session = request.getSession(false);
			int saleId = (int) session.getAttribute("saleId");
			sales salesData = null;
			salesData = (sales) session.getAttribute("picksale");

			if (salesData == null) {

				response.sendRedirect("S0020.html");
				return;
			}

			SalesService delete = new SalesService();
			//削除の前に削除権限があるのかをログイン中のアカウント権限と参照
			delete.delete(saleId, request, response);

			session.removeAttribute("saleslist");
			session.removeAttribute("saleId");
			session.removeAttribute("aName");
			session.removeAttribute("cName");
			session.removeAttribute("picksale");

			response.sendRedirect(request.getContextPath() + "/S0021.html");
		} else {
			response.sendRedirect(request.getContextPath() + "/S0022.html");
		}
	}
}