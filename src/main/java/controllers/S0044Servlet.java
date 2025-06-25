package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import services.AccountService;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0044Servlet() {
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

		request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession(false);

		accounts account = (accounts) session.getAttribute("account");
		if (account == null) {

			response.sendRedirect("S0041.html");
			return;

		}
		AccountService service = new AccountService();
		service.delete(account.getAccount_id(), request, response); // 削除処理

		session.removeAttribute("account");
		
		response.sendRedirect("S0041.html"); // 一覧画面へ
	}
}