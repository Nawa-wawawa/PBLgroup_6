package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import services.AccountService;

@WebServlet("/S0042Servlet")
public class S0042Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0042Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accountIdStr = request.getParameter("accountId");
        if (accountIdStr != null) {
            int accountId = Integer.parseInt(accountIdStr);
            AccountService service = new AccountService();
            accounts account = service.findById(accountId);
            request.setAttribute("account", account);

            // JSPでEL使用のため、booleanをセット
            request.setAttribute("hasSalesRole", (account.getAuthority() & 1) != 0);
            request.setAttribute("hasAccountRole", (account.getAuthority() & 2) != 0);
        }
        request.getRequestDispatcher("/jsp/S0042.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String[] roles = request.getParameterValues("role");

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

        request.getSession().setAttribute("confirmAccount", a);
        response.sendRedirect("S0043Servlet");
    }
}
