package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import beans.categories;
import beans.sales;
import beans.session;
import services.Accounts;
import services.Categories;
import services.Sales;
import utils.Db;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0020Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<accounts> accountslist = null;
		ArrayList<categories> categorylist = null;

		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			categorylist = ct.select();
			Accounts ac = new Accounts();
			accountslist = ac.select();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		request.setAttribute("categorylist", categorylist);
		request.setAttribute("accountslist", accountslist);

		request.getRequestDispatcher("/WEB-INF/jsp/S0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date start_date;
		Date end_date;
		int staff = 0;
		int category = 0;

		try {
			String startStr = request.getParameter("start_date");
			if (startStr == null || startStr.isEmpty()) {
				start_date = null;
			} else {
				start_date = Date.valueOf(startStr);
			}

			String endStr = request.getParameter("end_date");
			if (endStr == null || endStr.isEmpty()) {
				end_date = null;
			} else {
				end_date = Date.valueOf(endStr);
			}

		} catch (IllegalArgumentException e) {
			throw new ServletException("日付の形式が正しくありません。", e);
		}

		String staffStr = request.getParameter("staff");
		if (staffStr != null && !staffStr.isEmpty()) {
			try {
				staff = Integer.parseInt(staffStr);
			} catch (NumberFormatException e) {
				staff = 0;
			}
		}

		String categoryStr = request.getParameter("category");
		if (categoryStr != null && !categoryStr.isEmpty()) {
			try {
				category = Integer.parseInt(categoryStr);
			} catch (NumberFormatException e) {
				category = 0;
			}
		}
		String product_name = request.getParameter("product_name");
		String remarks = request.getParameter("remarks");
		
		session serch_condition = new session(start_date, end_date, staff, category, product_name ,remarks);

		Sales select = new Sales();

		ArrayList<sales> saleslist = select.select(start_date, end_date, staff, category, product_name, remarks);

		if (saleslist.isEmpty()) {
			//検索結果がからの場合の処理。
		}

		ArrayList<accounts> accountslist = null;
		ArrayList<categories> categorylist = null;

		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			categorylist = ct.select();
			Accounts ac = new Accounts();
			accountslist = ac.select();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("serch_condition", serch_condition);
		session.setAttribute("saleslist", saleslist);
		session.setAttribute("categorylist", categorylist);
		session.setAttribute("accountslist", accountslist);
		response.sendRedirect(request.getContextPath() + "/S0021.html");
	}
}
