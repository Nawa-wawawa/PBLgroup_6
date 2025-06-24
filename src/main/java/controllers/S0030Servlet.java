
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

import beans.accounts;
import froms.InsertAccountform;
import services.AccountService;
import services.Accountcheck;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0030Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		InsertAccountform form = new InsertAccountform(request);

		String[] roles = form.roles;
		String name = form.name;
		String mail = form.mail;
		String password = form.password;

		//System.out.println(roles + name + mail + password);

		//これを呼び出しにする。

		AccountService ac = new AccountService();
		byte authority = ac.authorityConvert(roles);

		accounts account = new accounts(name, mail, password, authority);

		Map<String, String> fieldErrors = new HashMap<>();

		Accountcheck acc = new Accountcheck();

		fieldErrors = acc.useCheck(form);

		if (!fieldErrors.isEmpty()) {
			// エラーがある → 入力画面に戻す
			request.setAttribute("fieldErrors", fieldErrors);
			request.setAttribute("account", account);
			request.setAttribute("isSubmitted", true);
			request.getRequestDispatcher("/WEB-INF/jsp/S0030.jsp").forward(request, response);
			return;
		}

		HttpSession session = request.getSession();

		session.setAttribute("account", account);
		session.setAttribute("roles", roles);
		session.setAttribute("canRegisterSales", (account.getAuthority() & 1) != 0);
		session.setAttribute("canRegisterAccounts", (account.getAuthority() & 2) != 0);

		response.sendRedirect(request.getContextPath() + "/S0031.html");
	}

}
