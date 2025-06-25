package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class NavServlet
 */
@WebServlet("/Nav.html")
public class NavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NavServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		session.removeAttribute("account");
		session.removeAttribute("search_condition");
		session.removeAttribute("salesData");
		session.removeAttribute("saleslist");
		session.removeAttribute("saleId");
		session.removeAttribute("aName");
		session.removeAttribute("cName");
		session.removeAttribute("picksale");
		session.removeAttribute("serch_condition");

		String menuValue = request.getParameter("menu");
		switch (menuValue) {

		case "0":
			response.sendRedirect("C0020.html");
			break;

		case "1":
			response.sendRedirect("S0010.html");
			break;

		case "2":
			response.sendRedirect("S0020.html");
			break;

		case "3":
			response.sendRedirect("S0030.html");
			break;

		case "4":
			response.sendRedirect("S0040.html");
			break;
		}
	}

}
