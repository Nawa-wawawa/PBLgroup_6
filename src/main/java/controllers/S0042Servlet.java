package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.accounts;
import services.AccountService;
import services.Accountcheck;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0042Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String accountIdStr = request.getParameter("accountId");
        System.out.println("accountIdStr = "+ accountIdStr);
        
        if (accountIdStr != null) {
        	
            int accountId = Integer.parseInt(accountIdStr);
            AccountService service = new AccountService();
            accounts account = service.findById(accountId);
            request.setAttribute("account", account);

            // JSPでEL使用のため、booleanをセット
            request.setAttribute("hasSalesRole", (account.getAuthority() & 1) != 0);
            request.setAttribute("hasAccountRole", (account.getAuthority() & 2) != 0);
        }
        request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        
        String idStr = request.getParameter("id");

        int id = Integer.parseInt(idStr);
        
        
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
        
//        //===============S0043->S0042 引っ張ってきた====================
        
     // バリデーション
        accounts account = new accounts(id, name, mail, password, authority);
        Accountcheck checker = new Accountcheck();
        Map<String, String> fieldErrors = new HashMap<>();
        String confirmPassword = request.getParameter("confirmPassword");

        // ■氏名
        if (name == null || name.trim().isEmpty()) {
            fieldErrors.put("name", "氏名を入力してください。");
        } else if (!name.matches("^[\\p{L} 　\\-\\ー]+$")) { // 例：日本語・英字・スペース・ハイフンだけ許可
            fieldErrors.put("name", "氏名の形式が正しくありません。");
        } else if (checker.nameCheck(name)) {
            fieldErrors.put("name", "氏名は20文字以内で入力してください。");
        }

        // ■メールアドレス
        if (mail == null || mail.trim().isEmpty()) {
            fieldErrors.put("mail", "メールアドレスを入力してください。");
        } else if (!checker.isValidEmailFormat(mail)) {
            fieldErrors.put("mail", "メールアドレスの形式が正しくありません。");
        } else if (checker.mailCheck(mail)) {
            fieldErrors.put("mail", "メールアドレスは100文字以内で入力してください。");
        }

        // ■パスワード
        if (password == null || password.trim().isEmpty()) {
            fieldErrors.put("password", "パスワードを入力してください。");
        } else if (checker.passwordCheck(password)) {
            fieldErrors.put("password", "パスワードは30文字以内で入力してください。");
        }

        // ■パスワード（確認）
        if (confirmPassword == null || confirmPassword.trim().isEmpty()) {
            fieldErrors.put("confirmPassword", "確認用パスワードを入力してください。");
        } else if (!confirmPassword.equals(password)) {
            fieldErrors.put("confirmPassword", "パスワードとパスワード（確認）の入力内容が異なっています。");
        }

        // ■権限（必須チェック）
        if (roles == null || roles.length == 0) {
            fieldErrors.put("role", "権限を1つ以上選択してください。");
        }

        if (!fieldErrors.isEmpty()) {
            // エラーがある → 入力画面に戻す
            request.setAttribute("fieldErrors", fieldErrors);
            request.setAttribute("account", account);
            request.setAttribute("isSubmitted", true);
            request.getRequestDispatcher("/WEB-INF/jsp/S0042.jsp").forward(request, response);
            return;
        }
        
//        //====================ここまで======================
//        

        accounts a = new accounts(id, name, mail, password, authority);
        
        System.out.println("confirmAccount id = " + a.getId());

        request.getSession().setAttribute("confirmAccount", a);
        response.sendRedirect("S0043.html");

    }
}
