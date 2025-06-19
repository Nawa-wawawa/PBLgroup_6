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

            if (account != null) {
                request.setAttribute("account", account);

                // ✅ JSPと同じ変数名で設定すること！
                request.setAttribute("hasSalesAuthority", (account.getAuthority() & 1) != 0);
                request.setAttribute("hasAccountAuthority", (account.getAuthority() & 2) != 0);
            } else {
                request.setAttribute("error", "アカウントが見つかりませんでした。");
            }
        } else {
            request.setAttribute("error", "アカウントIDが指定されていません。");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
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
                service.delete(id);  // 削除処理
                response.sendRedirect("S0041.html");  // 一覧画面へ
            } catch (Exception e) {
                request.setAttribute("error", "削除に失敗しました: " + e.getMessage());

                // 再取得して表示用にセット（削除失敗時にも再表示が必要）
                accounts account = service.findById(id);
                request.setAttribute("account", account);
                request.setAttribute("hasSalesAuthority", (account.getAuthority() & 1) != 0);
                request.setAttribute("hasAccountAuthority", (account.getAuthority() & 2) != 0);

                request.getRequestDispatcher("/WEB-INF/jsp/S0044.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("S0030.html");
        }
    }
}