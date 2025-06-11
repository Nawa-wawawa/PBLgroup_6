package controller;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

/**
 * Servlet implementation class SerchAccountServlet
 */
@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0040Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/WEB-INF/jsp/S0040.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        int authority = Integer.parseInt(request.getParameter("authority"));

        AccountService service = new AccountService();
        ArrayList<Account> account = service.findByAccount(name, mail, authority); 

        if (account != null) {
            request.setAttribute("account", account);
            request.getRequestDispatcher("/WEB-INF/jsp/S0041.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "該当するアカウントが見つかりません。");
            request.getRequestDispatcher("/WEB-INF/jsp/S0040.jsp").forward(request, response);
        }
	    // ❌ ログイン失敗：エラーメッセージ付きでログイン画面に戻る
//	    request.setAttribute("error", "メールアドレスまたはパスワードが違います。");
//	    request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

}
