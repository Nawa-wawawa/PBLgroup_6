package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

/**
 * Servlet implementation class S0042Servlet
 */
@WebServlet("/S0042Servlet")
public class S0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0042Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	request.getRequestDispatcher("/jsp/S0042.jsp").forward(request, response);
    }
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String action = request.getParameter("action");
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String[] roles = request.getParameterValues("role");
		byte authority = 0;

		if (roles != null) {
		    for (String role : roles) {
		        if ("0".equals(role)) {
		            authority |= 1; // 売上登録 → 1
		        } else if ("update".equals(role)) {
		            authority |= 2; // アカウント登録 → 2
		        }
		    }
		}

		Account a = new Account(0,name,mail,password,authority);
		AccountService as = new AccountService();
		as.update(a);
		
		response.sendRedirect("S0043Servlet");
	}

}
