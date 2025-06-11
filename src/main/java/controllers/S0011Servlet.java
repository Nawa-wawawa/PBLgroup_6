package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.sales;
import services.Accounts;
import services.Categories;
import services.Sales;
import utils.Db;

/**
 * Servlet implementation class S0011Servlet
 */
@WebServlet("/S0011.html")
public class S0011Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public S0011Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int staffId = 0;
		int categoryId = 0;

		String categoryname = "";
		String accountname = "";

		// TODO Auto-generated method stub
		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			staffId = (Integer) session.getAttribute("staff_id");
			categoryId = (Integer) session.getAttribute("category_id");
		} else {
			System.out.println("セッションが存在しません。");
		}

		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			categoryname = ct.getCategoryname(categoryId);
			Accounts ac = new Accounts();
			accountname = ac.getAccountname(staffId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		request.setAttribute("categoryname", categoryname);
		request.setAttribute("accountname", accountname);

		request.getRequestDispatcher("/WEB-INF/jsp/S0011.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Date sale_date = null;
		int staff = 0;
		int category = 0;
		String product_name = "";
		int unit_price = 0;
		int quantity = 0;
		String remarks = "";

		HttpSession session = request.getSession(false); // セッションがなければ null を返す
		if (session != null) {
			// 例：int型IDとして使いたい場合（Integer型にキャスト）
			sale_date =(Date) session.getAttribute("sale_date");
			staff = (Integer) session.getAttribute("staff_id");
			category = (Integer) session.getAttribute("category_id");
			product_name =(String) session.getAttribute("product_name");
			unit_price =(Integer) session.getAttribute("unit_price");
			quantity =(Integer) session.getAttribute("staff_id");
			remarks = (String)session.getAttribute("remarks");
		} else {
			System.out.println("セッションが存在しません。");
		}
		session.invalidate(); 
		try (Connection con = Db.open()) {

			Sales sl = new Sales();

			sales Newsale = new sales(sale_date, staff, category, product_name, unit_price, quantity, remarks);
			
			sl.insert(Newsale);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + "/S0010.html");
	}

}
