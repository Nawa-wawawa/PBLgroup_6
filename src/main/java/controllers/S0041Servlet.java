package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class S0041Servlet
 */
@WebServlet("/S0041.html")
public class S0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0041Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		AccountService as = new AccountService();
//		request.getAttribute("accounts" ,as.select(???));
		request.getRequestDispatcher("/jsp/S0041.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		System.out.println(action);
		
		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			
			if("edit".equals(action)) {
				
//				accounts accounts = (beans.accounts) session.getAttribute("account");
//				request.getSession().setAttribute("account", accounts);
				response.sendRedirect("/S0042.html");
				
			}else if("delete".equals(action)) {
				
//				accounts accounts = (beans.accounts) session.getAttribute("account");
//				request.setAttribute("account", accounts);
				request.getRequestDispatcher("/jsp/S0043.jsp").forward(request, response);
				
			}else {
				return;
			}
			
			
			
		}
		
	}

}
