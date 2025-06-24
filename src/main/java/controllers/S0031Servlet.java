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
import froms.InsertAccountform;
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

		InsertAccountform form = new InsertAccountform(request);

		String[] roles = form.roles;
		String name = form.name;
		String mail = form.mail;
		String password = form.password;

		//System.out.println(roles + name + mail + password);

		AccountService ac = new AccountService();
		byte authority = ac.authorityConvert(roles);

		accounts account = new accounts(name, mail, password, authority);

		Map<String, String> fieldErrors = new HashMap<>();

		Accountcheck acc = new Accountcheck();

		fieldErrors = acc.useCheck(form);

		if (!fieldErrors.isEmpty()) {
			// エラーがある → 入力画面に戻す
			request.setAttribute("fieldErrors", fieldErrors);
			request.setAttribute("account", account);
			request.setAttribute("isSubmitted", true);
			request.getRequestDispatcher("/WEB-INF/jsp/S0030.jsp").forward(request, response);
			return;
		}

		// 確認画面のOKが押されたときにDB登録
		AccountService service = new AccountService();

		service.insert(account, request, response);
		// 登録成功 → 入力画面へリダイレクト（必要に応じて変更）
		response.sendRedirect("S0030.html");
	}
}
