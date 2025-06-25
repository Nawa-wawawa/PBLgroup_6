package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountSearchCondition;
import services.Accountcheck;

/**
 * Servlet implementation class SerchAccountServlet
 */

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0040Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.getRequestDispatcher("/WEB-INF/jsp/S0040.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String role = request.getParameter("role");
		byte authority = 0;
		authority = Byte.parseByte(role);

		Map<String, String> fieldErrors = new HashMap<>();

		Accountcheck acc = new Accountcheck();

		fieldErrors = acc.serchInputcheck(name, mail);

		if (!fieldErrors.isEmpty()) {

			request.setAttribute("fieldErrors", fieldErrors);
			request.setAttribute("isSubmitted", true);
			request.getRequestDispatcher("/WEB-INF/jsp/S0040.jsp").forward(request, response);
			return;

		}

		AccountSearchCondition asc = new AccountSearchCondition(name, mail, authority);

		HttpSession search_condition = request.getSession();
		search_condition.setAttribute("search_condition", asc);

		response.sendRedirect("S0041.html");

	}

}
