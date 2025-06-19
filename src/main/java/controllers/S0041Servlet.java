
package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.AccountSearchCondition;
import beans.accounts;
import services.AccountService;

/**
 * Servlet implementation class S0041Servlet
 */
@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0041Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {

			response.sendRedirect("S0040.html");
			return;

		}

		AccountSearchCondition asc = (AccountSearchCondition) session.getAttribute("search_condition");

		if (asc == null) {

			response.sendRedirect("S0040.html");
			return;

		}

		AccountService service = new AccountService();
		ArrayList<accounts> accountList = service.findByAccount(asc.getName(), asc.getMail(), asc.getAuthority());

		System.out.println("取得件数: " + accountList.size());
		System.out.println("検索条件: " + asc.getName() + ", " + asc.getMail() + ", " + asc.getAuthority());

		request.setAttribute("accountList", accountList);
		request.getRequestDispatcher("/WEB-INF/jsp/S0041.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String IdStr = request.getParameter("id");
		int id = 0;
		System.out.println(action);
		System.out.println(IdStr);

		if (IdStr == null) {
			response.sendRedirect("S0041.html");
			return;
		}
		//ここにチェックを入れる
		if (IdStr != null && !IdStr.isEmpty()) {
			try {

				id = Integer.parseInt(IdStr);

			} catch (Exception e) {

				response.sendRedirect("S0041.html");

			}
		}

		System.out.println("id =" + id);

		AccountService service = new AccountService();
		accounts account = service.findById(id);

		HttpSession session = request.getSession(false);
		HttpSession newSession = request.getSession(true);
		HttpSession idSession = request.getSession(true);

		newSession.setAttribute("account", account);
		idSession.setAttribute("id", id);
		
		if (session != null) {

			session.removeAttribute("account");
			//idを送るからウラルに出る
			if ("edit".equals(action)) {				
				
				response.sendRedirect("S0042.html");

			} else if ("delete".equals(action)) {
				
				response.sendRedirect("S0044.html");

			} else {

				response.sendRedirect("S0041.html");

			}

		}

	}

}
