package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import services.AccountService;

/**
 * Servlet implementation class NewAccountServlet
 */
@WebServlet("/S0030.html")
public class S0030Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0030Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
//		String confirmPassword = request.getParameter("confirmPassword");
		String[] roles = request.getParameterValues("role");

//		Map<String, String> fieldErrors = new HashMap<>();
//		Accountcheck checker = new Accountcheck();
//
//		 // 氏名チェック
//        if (name == null || name.isEmpty()) {
//            fieldErrors.put("name", "氏名を入力してください。");
//        } else if (checker.nameCheck(name)) {
//            fieldErrors.put("name", "氏名が長すぎます。");
//        }
//
//        // メールチェック
//        if (mail == null || mail.isEmpty()) {
//            fieldErrors.put("mail", "メールアドレスを入力してください。");
//        } else if (!checker.isValidEmailFormat(mail)) {
//            fieldErrors.put("mail", "メールアドレスを正しく入力して下さい。");
//        } else if (checker.mailCheck(mail)) {
//            fieldErrors.put("mail", "メールアドレスが長すぎます。");
//        }
//
//        // パスワードチェック
//        if (password == null || password.isEmpty()) {
//            fieldErrors.put("password", "パスワードを入力して下さい。");
//        } else if (checker.passwordCheck(password)) {
//            fieldErrors.put("password", "パスワードが長すぎます。");
//        }
//
//        // パスワード確認チェック
//        if (confirmPassword == null || confirmPassword.isEmpty()) {
//            fieldErrors.put("confirmPassword", "パスワード（確認）を入力して下さい。");
//        } else if (checker.confirmpasswordCheck(password, confirmPassword)) {
//            fieldErrors.put("confirmPassword", "パスワードとパスワード（確認）の入力内容が異なっています。");
//        }
//
//        // エラーがあれば戻す
//        if (!fieldErrors.isEmpty()) {
//            request.setAttribute("fieldErrors", fieldErrors);
//            request.setAttribute("isSubmitted", true);
//            request.setAttribute("name", name);
//            request.setAttribute("mail", mail);
//            request.setAttribute("password", password);
//            request.setAttribute("confirmPassword", confirmPassword);
//            request.setAttribute("roles", roles);
//            request.getRequestDispatcher("/jsp/S0030.jsp").forward(request, response);
//            return;
//        }

		// 登録処理（正常時）
		byte authority = 0;
		if (roles != null) {
			for (String role : roles) {
				if ("0".equals(role))
					authority |= 1;
				if ("update".equals(role))
					authority |= 2;
			}
		}

		accounts a = new accounts(0, name, mail, password, authority);
		new AccountService().insert(a);
		response.sendRedirect("S0031.html");
	}

}
