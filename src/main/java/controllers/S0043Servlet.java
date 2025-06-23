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
		request.getRequestDispatcher("/WEB-INF/jsp/S0043.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		// 更新処理
		HttpSession session = request.getSession(false);
		accounts account = (accounts) session.getAttribute("account");

		//S0041でgetしたときにチェックを入れているので、ここは必要ないかもしれない。

		//		// idをパラメータから取得
		//		String idStr = account.getAccount_id();
		//		int id = 0;
		//		try {
		//			id = Integer.parseInt(idStr);
		//		} catch (NumberFormatException e) {
		//			// ID不正時は入力画面に戻すなどの処理
		//			request.setAttribute("error", "不正なIDです。");
		//			request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
		//			return;
		//		}

		AccountService service = new AccountService();

		//これも前にチェックを済ませているので、必要なのか？
		//		Map<String, String> fieldErrors = new HashMap<>();
		//
		//		Accountcheck acc = new Accountcheck();
		//
		//		fieldErrors = acc.useCheck(form);
		//
		//		if (!fieldErrors.isEmpty()) {
		//			// エラーがある → 入力画面に戻す
		//			request.setAttribute("fieldErrors", fieldErrors);
		//			request.setAttribute("account", account);
		//			request.setAttribute("hasSales", (account.getAuthority() & 1) != 0);
		//			request.setAttribute("hasAccountReg", (account.getAuthority() & 2) != 0);
		//			request.setAttribute("isSubmitted", true);
		//			request.getRequestDispatcher("/WEB-INF/jsp/S0043.jsp").forward(request, response);
		//			return;
		//		}

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

	}
}