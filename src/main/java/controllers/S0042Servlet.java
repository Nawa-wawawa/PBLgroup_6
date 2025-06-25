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
import services.Accountcheck;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0042Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		accounts account = (accounts) session.getAttribute("account");
		if (account == null) {

			response.sendRedirect("S0041.html");
			return;
		}

		request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		InsertAccountform form = new InsertAccountform(request);

		HttpSession session = request.getSession(false);
		accounts pickedaccount = (accounts) session.getAttribute("account");

		accounts account = new accounts(pickedaccount.getAccount_id(), form);

		Map<String, String> fieldErrors = new HashMap<>();

		Accountcheck acc = new Accountcheck();

		fieldErrors = acc.useCheck(form);

		if (!fieldErrors.isEmpty()) {
			// エラーがある → 入力画面に戻す
			request.setAttribute("fieldErrors", fieldErrors);
			request.setAttribute("account", account);
			request.setAttribute("isSubmitted", true);
			request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
			return;
		}

		// 確認画面へ
		session.setAttribute("account", account);
		response.sendRedirect(request.getContextPath() + "/S0043.html");
	}
}