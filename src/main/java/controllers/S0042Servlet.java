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

import beans.AccountSearchCondition;
import beans.accounts;
import froms.InsertAccountform;
import services.AccountService;
import services.Accountcheck;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0042Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		AccountSearchCondition asc = (AccountSearchCondition) session.getAttribute("search_condition");

		if (asc == null) {

			response.sendRedirect("S0040.html");
			return;

		}

		accounts account = (accounts) session.getAttribute("account");
		//
		//		if (idItg != null) {
		//			int accountId = idItg;
		//			AccountService service = new AccountService();
		//			accounts account = service.findById(accountId);
		//
		//			if (account != null) {
		//		request.setAttribute("account", account);
		request.setAttribute("hasSalesRole", (account.getAuthority() & 1) != 0);
		request.setAttribute("hasAccountRole", (account.getAuthority() & 2) != 0);
		//			} else {
		//				// accountがnullの時の処理（例：エラーメッセージをセット）
		//				request.setAttribute("error", "指定されたアカウントが存在しません。");
		//			}
		//		} else {
		//			request.setAttribute("error", "アカウントIDが指定されていません。");
		//		}
		request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		InsertAccountform form = new InsertAccountform(request);

		String[] roles = form.roles;
		String name = form.name;
		String mail = form.mail;
		String password = form.password;

		HttpSession session = request.getSession(false);
		Integer idItg = (Integer) session.getAttribute("id");

		AccountService ac = new AccountService();
		byte authority = ac.authorityConvert(roles);

		accounts account = new accounts(idItg, name, mail, password, authority);

		Map<String, String> fieldErrors = new HashMap<>();

		Accountcheck acc = new Accountcheck();

		fieldErrors = acc.useCheck(form);

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
		session.setAttribute("account", account);
		session.setAttribute("hasSales", (account.getAuthority() & 1) != 0);
		session.setAttribute("hasAccountReg", (account.getAuthority() & 2) != 0);
		response.sendRedirect(request.getContextPath() + "/S0043.html");
	}
}