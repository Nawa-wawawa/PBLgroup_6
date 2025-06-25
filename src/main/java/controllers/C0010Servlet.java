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
import services.Accountcheck;
import services.LoginService;

@WebServlet("/C0010.html")
public class C0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C0010Servlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ログイン画面へフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		//入力情報が想定の範囲内かをチェックする。
		Map<String, String> fieldErrors = new HashMap<>();
		Accountcheck acc = new Accountcheck();

		fieldErrors = acc.loginInputcheck(mail, password);
		
		if (!fieldErrors.isEmpty()) {
			// エラーがある → 入力画面に戻す
			request.setAttribute("fieldErrors", fieldErrors);
			request.setAttribute("isSubmitted", true);
			request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
			return;
		}

		try {
			LoginService ls = new LoginService();
			accounts account = ls.authenticate(mail, password);
			if (account != null) {
				// 認証成功 → セッションにユーザー情報を保存
				HttpSession session = request.getSession();
				session.setAttribute("user", account);

				// 次画面へフォワード
				request.getRequestDispatcher("/C0020.html").forward(request, response);
			} else {
				// 認証失敗 → エラーメッセージを設定してログイン画面へ戻す
				request.setAttribute("error", "メールアドレス、パスワードを正しく入力して下さい。");
				request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();

			// 例外が発生した場合もエラーとして扱う
			request.setAttribute("error", "システムエラーが発生しました。管理者に連絡してください。");
			request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
		}
	}
}
