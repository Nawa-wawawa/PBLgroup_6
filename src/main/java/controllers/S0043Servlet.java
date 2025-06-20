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

			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			String[] roles = request.getParameterValues("role");

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

			// バリデーション
			Accountcheck checker = new Accountcheck();
			Map<String, String> fieldErrors = new HashMap<>();

			// ■氏名
			if (name == null || name.trim().isEmpty()) {
				fieldErrors.put("name", "氏名を入力してください。");
			} else if (!name.matches("^[\\p{L} 　\\-\\ー]+$")) { // 例：日本語・英字・スペース・ハイフンだけ許可
				fieldErrors.put("name", "氏名の形式が正しくありません。");
			} else if (checker.nameCheck(name)) {
				fieldErrors.put("name", "氏名は20文字以内で入力してください。");
			}

			// ■メールアドレス
			if (mail == null || mail.trim().isEmpty()) {
				fieldErrors.put("mail", "メールアドレスを入力してください。");
			} else if (!checker.isValidEmailFormat(mail)) {
				fieldErrors.put("mail", "メールアドレスの形式が正しくありません。");
			} else if (checker.mailCheck(mail)) {
				fieldErrors.put("mail", "メールアドレスは100文字以内で入力してください。");
			}

			// ■パスワード
			if (password == null || password.trim().isEmpty()) {
				fieldErrors.put("password", "パスワードを入力してください。");
			} else if (checker.passwordCheck(password)) {
				fieldErrors.put("password", "パスワードは30文字以内で入力してください。");
			}

			// ■パスワード（確認）
			if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
				fieldErrors.put("confirmPassword", "確認用パスワードを入力してください。");
			} else if (!confirmPassword.equals(password)) {
				fieldErrors.put("confirmPassword", "パスワードとパスワード（確認）の入力内容が異なっています。");
			}

			// ■権限（必須チェック）
//			if (roles == null || roles.length == 0) {
//				fieldErrors.put("role", "権限を1つ以上選択してください。");
//			}

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

			String name = request.getParameter("name");
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			String[] roles = request.getParameterValues("role");

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

			// バリデーション
			Accountcheck checker = new Accountcheck();
			Map<String, String> fieldErrors = new HashMap<>();

			// ■氏名
			if (name == null || name.trim().isEmpty()) {
				fieldErrors.put("name", "氏名を入力してください。");
			} else if (!name.matches("^[\\p{L} 　\\-\\ー]+$")) { // 例：日本語・英字・スペース・ハイフンだけ許可
				fieldErrors.put("name", "氏名の形式が正しくありません。");
			} else if (checker.nameCheck(name)) {
				fieldErrors.put("name", "氏名は20文字以内で入力してください。");
			}

			// ■メールアドレス
			if (mail == null || mail.trim().isEmpty()) {
				fieldErrors.put("mail", "メールアドレスを入力してください。");
			} else if (!checker.isValidEmailFormat(mail)) {
				fieldErrors.put("mail", "メールアドレスの形式が正しくありません。");
			} else if (checker.mailCheck(mail)) {
				fieldErrors.put("mail", "メールアドレスは100文字以内で入力してください。");
			}

			// ■パスワード
			if (password == null || password.trim().isEmpty()) {
				fieldErrors.put("password", "パスワードを入力してください。");
			} else if (checker.passwordCheck(password)) {
				fieldErrors.put("password", "パスワードは30文字以内で入力してください。");
			}

			// ■パスワード（確認）
			if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
				fieldErrors.put("confirmPassword", "確認用パスワードを入力してください。");
			} else if (!confirmPassword.equals(password)) {
				fieldErrors.put("confirmPassword", "パスワードとパスワード（確認）の入力内容が異なっています。");
			}

//			// ■権限（必須チェック）
//			if (roles == null || roles.length == 0) {
//				fieldErrors.put("role", "権限を1つ以上選択してください。");
//			}

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