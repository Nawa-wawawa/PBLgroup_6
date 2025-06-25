
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

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0031Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		accounts account = (accounts) session.getAttribute("account");
		
		if (account == null) {

			response.sendRedirect("S0030.html");
			return;

		}

		request.getRequestDispatcher("/WEB-INF/jsp/S0031.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		accounts account = (accounts) session.getAttribute("account");
		// 確認画面のOKが押されたときにDB登録
		AccountService service = new AccountService();

		service.insert(account, request, response);
		
		session.removeAttribute("account");
		// 登録成功 → 入力画面へリダイレクト（必要に応じて変更）
		response.sendRedirect("S0030.html");
	}
}
