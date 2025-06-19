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

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0042Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	HttpSession session = request.getSession(false);
    	
    	if(session == null) {
    		
    		response.sendRedirect("S0041.html");
    		return;
    		
    	}
    	
    	Integer idItg = (Integer) session.getAttribute("id");    	
    	
        if (idItg != null) {
            int accountId = idItg;
            AccountService service = new AccountService();
            accounts account = service.findById(accountId);
            
            if (account != null) {
                request.setAttribute("account", account);
                request.setAttribute("hasSalesRole", (account.getAuthority() & 1) != 0);
                request.setAttribute("hasAccountRole", (account.getAuthority() & 2) != 0);
            } else {
                // accountがnullの時の処理（例：エラーメッセージをセット）
                request.setAttribute("error", "指定されたアカウントが存在しません。");
            }
        } else {
            request.setAttribute("error", "アカウントIDが指定されていません。");
        }
        request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String[] roles = request.getParameterValues("role");
        
        //ここの０とupdateはさすがに変える。
        byte authority = 0;
        if (roles != null) {
            for (String role : roles) {
                if ("0".equals(role)) {
                    authority |= 1;
                } else if ("update".equals(role)) {
                    authority |= 2;
                }
            }
        }

        accounts a = new accounts(id, name, mail, password, authority);
        //sessionを省略しない。
        request.getSession().setAttribute("confirmAccount", a);
        response.sendRedirect("S0043.html");
    }
}