package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import services.LoginService;

@WebServlet("/C0010Servlet")
public class C0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C0010Servlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン画面へフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");

		try {
			LoginService ls = new LoginService();
			accounts account = ls.authenticate(mail, password);

			if (account != null) {
				// 認証成功 → セッションにユーザー情報を保存
				HttpSession session = request.getSession();
				session.setAttribute("user", account);

				// 次画面へフォワード
				request.getRequestDispatcher("/WEB-INF/jsp/C0020.jsp").forward(request, response);
			} else {
				// 認証失敗 → エラーメッセージを設定してログイン画面へ戻す
				request.setAttribute("error", "メールアドレスまたはパスワードが間違っています");
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
