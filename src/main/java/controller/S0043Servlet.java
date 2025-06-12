package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import services.AccountService;

@WebServlet("/S0043Servlet")
public class S0043Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0043Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GETは使わない想定 → 入力画面に戻す
        response.sendRedirect("S0042Servlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("update".equals(action)) {
            // 更新処理

            // idをパラメータから取得
            String idStr = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                // ID不正時は入力画面に戻すなどの処理
                request.setAttribute("error", "不正なIDです。");
                request.getRequestDispatcher("/jsp/S0042.jsp").forward(request, response);
                return;
            }

            String name = request.getParameter("name");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
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

            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "パスワードが一致しません");
                accounts account = new accounts(id, name, mail, password, authority);
                request.setAttribute("account", account);
                request.getRequestDispatcher("/jsp/S0042.jsp").forward(request, response);
                return;
            }

            accounts account = new accounts(id, name, mail, password, authority);
            AccountService service = new AccountService();

            try {
                service.update(account);  // updateメソッドでDBの更新を実施
                // 更新成功：一覧画面や完了画面にリダイレクト
                response.sendRedirect("S0030Servlet"); 
            } catch (Exception e) {
                request.setAttribute("error", "更新に失敗しました: " + e.getMessage());
                request.setAttribute("account", account);
                request.getRequestDispatcher("/jsp/S0043.jsp").forward(request, response);
            }
        } else {
            // 確認画面に遷移する処理（新規登録や編集の確認）

            String name = request.getParameter("name");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
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

            accounts account = new accounts(0, name, mail, password, authority);

            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "パスワードが一致しません");
                request.setAttribute("account", account);
                request.getRequestDispatcher("/jsp/S0030.jsp").forward(request, response);
                return;
            }

            // 確認画面へ
            request.setAttribute("account", account);
            request.getRequestDispatcher("/jsp/S0043.jsp").forward(request, response);
        }
    }
}
