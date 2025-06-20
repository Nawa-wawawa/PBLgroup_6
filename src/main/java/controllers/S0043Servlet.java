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

@WebServlet("/S0043.html")
public class S0043Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0043Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/S0043.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		if ("update".equals(action)) {
			// 更新処理

			// idをパラメータから取得
			String idStr = request.getParameter("id");
			int id = 0;
			try {
				id = Integer.parseInt(idStr);
			} catch (NumberFormatException e) {
				// ID不正時は入力画面に戻すなどの処理
				request.setAttribute("error", "不正なIDです。");
				request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
				return;
			}

			getAccountRequest form = new getAccountRequest(request);

			String[] roles = form.roles;
			String name = form.name;
			String mail = form.mail;
			String password = form.password;

			int n = roles != null ? roles.length : 0;

			for (int i = 0; i < n; i++) {
				System.out.println(roles[i]);
			}

			byte authority = 0;
			if (roles != null) {
				for (String role : roles) {
					if ("salesregister".equals(role)) {
						authority |= 1;
					} else if ("accountregister".equals(role)) {
						authority |= 2;
					}
				}
			}

			accounts account = new accounts(id, name, mail, password, authority);
			AccountService service = new AccountService();

			Map<String, String> fieldErrors = new HashMap<>();

			Accountcheck acc = new Accountcheck();

			acc.useCheck(form);

			if (!fieldErrors.isEmpty()) {
				// エラーがある → 入力画面に戻す
				request.setAttribute("fieldErrors", fieldErrors);
				request.setAttribute("account", account);
				request.setAttribute("hasSales", (account.getAuthority() & 1) != 0);
				request.setAttribute("hasAccountReg", (account.getAuthority() & 2) != 0);
				request.setAttribute("isSubmitted", true);
				request.getRequestDispatcher("/WEB-INF/jsp/S0043.jsp").forward(request, response);
				return;
			}

			try {
				service.update(account); // updateメソッドでDBの更新を実施
				// 更新成功：一覧画面や完了画面にリダイレクト
				response.sendRedirect("S0041.html");
			} catch (Exception e) {
				request.setAttribute("error", "更新に失敗しました: " + e.getMessage());
				request.setAttribute("account", account);
				request.setAttribute("hasSales", (account.getAuthority() & 1) != 0);
				request.setAttribute("hasAccountReg", (account.getAuthority() & 2) != 0);
				request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
			}
		} else {
			// 確認画面に遷移する処理（新規登録や編集の確認）

			getAccountRequest form = new getAccountRequest(request);

			String[] roles = form.roles;
			String name = form.name;
			String mail = form.mail;
			String password = form.password;

			byte authority = 0;
			if (roles != null) {
				for (String role : roles) {
					if ("salesregister".equals(role)) {
						authority |= 1;
					} else if ("accountregister".equals(role)) {
						authority |= 2;
					}
				}
			}

			accounts account = new accounts(0, name, mail, password, authority);

			AccountService service = new AccountService();

			Map<String, String> fieldErrors = new HashMap<>();

			Accountcheck acc = new Accountcheck();

			acc.useCheck(form);

			if (!fieldErrors.isEmpty()) {
				// エラーがある → 入力画面に戻す
				request.setAttribute("fieldErrors", fieldErrors);
				request.setAttribute("account", account);
				request.setAttribute("hasSales", (account.getAuthority() & 1) != 0);
				request.setAttribute("hasAccountReg", (account.getAuthority() & 2) != 0);
				request.setAttribute("isSubmitted", true);
				request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
				return;
			}

			// 確認画面へ
			request.setAttribute("account", account);
			request.setAttribute("hasSales", (account.getAuthority() & 1) != 0);
			request.setAttribute("hasAccountReg", (account.getAuthority() & 2) != 0);
			request.getRequestDispatcher("/WEB-INF/jsp/S0043.jsp").forward(request, response);
		}
	}
}