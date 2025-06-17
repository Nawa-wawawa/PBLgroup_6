package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import beans.session;
import services.Sales;
import utils.Db;

/**
 * Servlet implementation class S0025Servlet
 */
@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0025Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/S0025.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("action"));

		if (action == 1) {

			HttpSession session = request.getSession(false);
			int saleId = (int) session.getAttribute("saleId");
			session serch_condition = (session) session.getAttribute("serch_condition");
			//削除を行って、セッションの更新？をしないと削除されたものが更新されない？
			//検索の条件がないと再度検索できない？
			try (Connection con = Db.open()) {

				Sales delete = new Sales();
				//削除の前に削除権限があるのかをログイン中のアカウント権限と参照
				delete.delete(saleId);

				ArrayList<sales> saleslist = delete.select(serch_condition.getStart_date(),
						serch_condition.getEnd_date(), serch_condition.getAccount_id(),
						serch_condition.getCategory_id(), serch_condition.getTrade_name(), serch_condition.getNote());
				session.setAttribute("saleslist", saleslist);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (NamingException e1) {
				// TODO 自動生成された catch ブロック
				e1.printStackTrace();
			}

			response.sendRedirect(request.getContextPath() + "/S0021.html");
		} else {
			response.sendRedirect(request.getContextPath() + "/S0022.html");
		}
	}
}
