package controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

@WebServlet("/S0031Servlet")
public class S0031Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0031Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // GETは使わない想定 → 入力画面に戻す
        response.sendRedirect("S0030Servlet");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("register".equals(action)) {
            // 「確認画面のOK」ボタンが押されたとき

            String name = request.getParameter("name");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String[] roles = request.getParameterValues("role");

            int authority = 0;
            if (roles != null) {
                for (String role : roles) {
                    if ("0".equals(role)) {
                        authority |= 1; // 売上登録
                    } else if ("update".equals(role)) {
                        authority |= 2; // アカウント登録
                    }
                }
            }

            // パスワードチェック
            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "パスワードが一致しません");
                request.setAttribute("account", new Account(0, name, mail, password, authority));
                request.getRequestDispatcher("/jsp/S0031.jsp").forward(request, response);
                return;
            }

            // 登録
            Account account = new Account(0, name, mail, password, authority);
            AccountService service = new AccountService();

            try {
                service.insert(account);
                // 成功：入力画面にリダイレクト
                response.sendRedirect("S0030Servlet");
            } catch (Exception e) {
                request.setAttribute("error", "登録に失敗しました: " + e.getMessage());
                request.setAttribute("account", account);
                request.getRequestDispatcher("/jsp/S0031.jsp").forward(request, response);
            }

        } else {
            // S0030から最初に確認画面へ遷移する場合

            String name = request.getParameter("name");
            String mail = request.getParameter("mail");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String[] roles = request.getParameterValues("role");

            int authority = 0;
            if (roles != null) {
                for (String role : roles) {
                    if ("0".equals(role)) {
                        authority |= 1;
                    } else if ("update".equals(role)) {
                        authority |= 2;
                    }
                }
            }

            Account account = new Account(0, name, mail, password, authority);

            if (!password.equals(confirmPassword)) {
                request.setAttribute("error", "パスワードが一致しません");
                request.setAttribute("account", account);
                request.getRequestDispatcher("/jsp/S0030.jsp").forward(request, response);
                return;
            }

            // 確認画面へ渡す
            request.setAttribute("account", account);
            request.getRequestDispatcher("/jsp/S0031.jsp").forward(request, response);
        }
    }
}
