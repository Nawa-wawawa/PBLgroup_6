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

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0043Servlet() {
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

		request.getRequestDispatcher("/WEB-INF/jsp/S0043.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 更新処理
		HttpSession session = request.getSession(false);
		accounts account = (accounts) session.getAttribute("account");
		AccountService service = new AccountService();

		service.update(account, request, response); // updateメソッドでDBの更新を実施
		
		session.removeAttribute("account");
		
		// 更新成功：一覧画面や完了画面にリダイレクト
		response.sendRedirect("S0041.html");
	}
}