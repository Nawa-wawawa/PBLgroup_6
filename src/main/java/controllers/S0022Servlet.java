package controllers;

import java.io.IOException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;

/**
 * Servlet implementation class S0022Servlet
 */
@WebServlet("/S0022.html")
public class S0022Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0022Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ArrayList<sales> saleslist = new ArrayList<>();
		int saleId = 0;

		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			saleslist = (ArrayList<sales>) session.getAttribute("saleslist");
		} else {
			System.out.println("セッションが存在しません。");
		}
		saleId = (int) session.getAttribute("saleId");

		sales picksale = null;
		for (sales sl : saleslist) {
			if (sl.getSale_id() == saleId) {
				picksale = sl;
			}
		}
		

		request.setAttribute("picksale", picksale);

		request.getRequestDispatcher("/WEB-INF/jsp/S0022.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int action = Integer.parseInt(request.getParameter("action"));

		if (action == 0)
			//System.out.println("aaa");	
		response.sendRedirect(request.getContextPath() + "/S0021.html");
		else if (action == 1)
			response.sendRedirect(request.getContextPath() + "/S0025.html");
		else if (action == 2)
			response.sendRedirect(request.getContextPath() + "/S0023.html");
		else
			response.sendRedirect(request.getContextPath() + "/S0022.html");
	}

}
