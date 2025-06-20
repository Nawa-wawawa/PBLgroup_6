package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import beans.getAccountRequest;
import services.AccountService;
import services.Accountcheck;

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0031Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/S0031.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		getAccountRequest form = new getAccountRequest(request);

		String[] roles = form.roles;
		String name = form.name;
		String mail = form.mail;
		String password = form.password;

		byte authority = 0;
		if (roles != null) {
			for (String role : roles) {
				if ("salesregister".equals(role)) {
					authority |= 1; // 売上登録
				} else if ("accountregister".equals(role)) {
					authority |= 2; // アカウント登録
				}
			}
		}

		accounts account = new accounts(name, mail, password, authority);

		Map<String, String> fieldErrors = new HashMap<>();

		Accountcheck acc = new Accountcheck();

		acc.useCheck(form);
		if (!fieldErrors.isEmpty()) {
			// エラーがある → 入力画面に戻す
			request.setAttribute("fieldErrors", fieldErrors);
			request.setAttribute("account", account);
			request.setAttribute("isSubmitted", true);
			request.getRequestDispatcher("/WEB-INF/jsp/S0030.jsp").forward(request, response);
			return;
		}

		if ("register".equals(action)) {
			// 確認画面のOKが押されたときにDB登録
			AccountService service = new AccountService();
			try {
				service.insert(account);
				// 登録成功 → 入力画面へリダイレクト（必要に応じて変更）
				response.sendRedirect("S0030.html");
			} catch (Exception e) {
				request.setAttribute("error", "登録に失敗しました: " + e.getMessage());
				request.setAttribute("account", account);
				request.getRequestDispatcher("/WEB-INF/jsp/S0031.jsp").forward(request, response);
			}
		} else {
			// 確認画面へ遷移

			request.setAttribute("account", account);
			// ここでrolesもセットしておくとjspで使いやすいです（任意）
			request.setAttribute("roles", roles);

			// ↓ 追加：権限ビットに応じてチェックボックス表示用のフラグをセット
			request.setAttribute("canRegisterSales", (account.getAuthority() & 1) != 0);
			request.setAttribute("canRegisterAccounts", (account.getAuthority() & 2) != 0);

			request.getRequestDispatcher("/WEB-INF/jsp/S0031.jsp").forward(request, response);
		}
	}
}
