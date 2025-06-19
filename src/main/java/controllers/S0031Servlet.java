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

@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0031Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// GETは使わない想定 → 入力画面に戻す
		response.sendRedirect("S0030.html");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String action = request.getParameter("action");

		// 入力値取得
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String[] roles = request.getParameterValues("role");

		byte authority = 0;
		if (roles != null) {
			for (String role : roles) {
				if ("0".equals(role)) {
					authority |= 1; // 売上登録
				} else if ("update".equals(role)) {
					authority |= 2; // アカウント登録
				}
			}
		}

		accounts account = new accounts(0, name, mail, password, authority);

		// バリデーション これは30の内容なので30のサーブレットで書かないとウラルが31でエラー表示される。
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
