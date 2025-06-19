package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import beans.salescondition;
import services.Sales;

/**
 * Servlet implementation class S0021Servlrt
 */
@WebServlet("/S0021.html")
public class S0021Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0021Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		salescondition serch_condition =(salescondition)session.getAttribute("serch_condition");
		Sales select = new Sales();
		ArrayList<sales> saleslist = select.select(serch_condition);
		session.setAttribute("saleslist", saleslist);
		//ここに検索結果がない場合を記入。
		
		request.getRequestDispatcher("/WEB-INF/jsp/S0021.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int saleId = Integer.parseInt(request.getParameter("id"));
		String aName = request.getParameter("accountName");
		String cName = request.getParameter("categoryName");

		HttpSession session = request.getSession();
		session.setAttribute("saleId", saleId);
		session.setAttribute("aName", aName);
		session.setAttribute("cName", cName);

		response.sendRedirect(request.getContextPath() + "/S0022.html");
	}

}
