package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;

@WebServlet("/C0020Servlet")
public class C0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		if (session == null) {
			response.sendRedirect("C0010Servlet");
			return;
		}

		accounts user = (accounts) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect("C0010Servlet");
			return;
		}

		byte authority = user.getAuthority();

		// 売上登録権限（bit 1）
		boolean canSales = (authority & 1) == 1;

		// アカウント登録権限（bit 2）
		boolean canAccount = (authority & 2) == 2;

		// JSPに渡す
		request.setAttribute("canSales", canSales);
		request.setAttribute("canAccount", canAccount);

		request.getRequestDispatcher("/WEB-INF/jsp/C0020.jsp").forward(request, response);
	}
}
