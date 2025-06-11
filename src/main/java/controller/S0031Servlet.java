package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;

/**
 * Servlet implementation class CheckNewAccountServlet
 */
@WebServlet("/S0031Servlet")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0031Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.getRequestDispatcher("/S0031.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// S0031Servlet.java
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    // 入力値取得
	    String name = request.getParameter("name");
	    String mail = request.getParameter("mail");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirmPassword");
	    
	    // 権限の処理（例として簡易版）
	    String[] roles = request.getParameterValues("role");
	    int authority = 0;
	    if (roles != null) {
	        for (String role : roles) {
	            if ("read".equals(role)) authority |= 1;
	            if ("update".equals(role)) authority |= 2;
	        }
	    }

	    // パスワード確認などの簡単なバリデーション
	    if (!password.equals(confirmPassword)) {
	        request.setAttribute("error", "パスワードが一致しません");
	        request.getRequestDispatcher("/jsp/S0030.jsp").forward(request, response);
	        return;
	    }

	    // Accountインスタンスを作成（idは未登録なので0など）
	    Account account = new Account(0, name, mail, password, authority);

	    // 確認画面に渡す
	    request.setAttribute("account", account);

	    // 確認画面へ
	    request.getRequestDispatcher("/jsp/S0031.jsp").forward(request, response);
	}


}
