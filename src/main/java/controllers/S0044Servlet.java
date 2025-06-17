package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import services.AccountService;

@WebServlet("/S0044.html")
public class S0044Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0044Servlet() {
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
        request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            // アカウント削除処理
            String idStr = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "不正なIDです。");
                request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
                return;
            }

            AccountService service = new AccountService();
            try {
                service.delete(id);  // deleteメソッドを呼び出す（DBから削除）
                response.sendRedirect("S0030.html");  // 成功：一覧画面にリダイレクト
            } catch (Exception e) {
                request.setAttribute("error", "削除に失敗しました: " + e.getMessage());
                request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
            }

        } else {
            // 想定外のアクション
            response.sendRedirect("S0030.html");
        }
    }
}
