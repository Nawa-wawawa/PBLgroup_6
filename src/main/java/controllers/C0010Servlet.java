package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import services.LoginService;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/C0010Servlet")
public class C0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C0010Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		
		try {
			LoginService ls = new LoginService();
			accounts accounts = ls.authenticate(mail,password);
			if (accounts != null) {
				request.getRequestDispatcher("/WEB-INF/jsp/C0020.jsp").forward(request, response);
				//セッションに保存（未実装）
			} else {
				request.setAttribute("error", "メールアドレスまたはパスワードが間違っています");
				request.getRequestDispatcher("/WEB-INF/jsp/C0010.jsp").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
