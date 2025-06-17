package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import services.AccountService;

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
		    HttpSession  session = request.getSession(false);
		    if(session!= null) {
		    	List<accounts> accountList = (List<accounts>)session.getAttribute("accountList");
		    	if(accountList != null) {
		    		request.setAttribute("account", accountList);		    		
		    	}
		    }request.getRequestDispatcher("/WEB-INF/jsp/S0041.jsp").forward(request, response);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action=request.getParameter("action");
		String IdStr=request.getParameter("id");
		int id=Integer.parseInt(IdStr);
		System.out.println(action);
		System.out.println(IdStr);
		
		
		if(IdStr == null) {
			response.sendRedirect("S0041.html");
			return;
		}
		
		AccountService service = new AccountService();
		accounts account = service.findById(id);		
		
		HttpSession session = request.getSession(false);
		session.setAttribute("account",account);
		
		if(session != null) {
			
			if("edit".equals(action)) {
				
				response.sendRedirect("S0042Servlet?accountId=" + id);
				System.out.println(account);

				
			}else if("delete".equals(action)) {
				
				response.sendRedirect("S0044Servlet?accountId=" + id);
				
			}else {
				response.sendRedirect("S0041.html");
			}
			
			System.out.println(account);
			
		}
		
	}

}
