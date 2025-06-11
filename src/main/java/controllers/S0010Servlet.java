package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
import services.Accounts;
import services.Categories;
import utils.Db;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0010Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String today = LocalDate.now().toString();
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
		
	    request.setAttribute("today", today);
	    request.setAttribute("categorylist", categorylist);
	    request.setAttribute("accountslist", accountslist);
		
		request.getRequestDispatcher("/WEB-INF/jsp/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Date sale_date = Date.valueOf(request.getParameter("sale_date"));
		int staff = Integer.parseInt(request.getParameter("staff"));
		int category = Integer.parseInt(request.getParameter("category"));
		String product_name = request.getParameter("product_name");
		int unit_price = Integer.parseInt(request.getParameter("unit_price"));
		int quantity =  Integer.parseInt(request.getParameter("quantity"));
		String remarks = request.getParameter("remarks");
		
		
		//1-2
		//1-6
		//1-8
		//1-9
		//1-11
		//1-12
		//1-13
		
		
	    HttpSession session = request.getSession();
	    session.setAttribute("sale_date", sale_date);
	    session.setAttribute("staff_id", staff);
	    session.setAttribute("category_id", category);
	    session.setAttribute("product_name", product_name);
	    session.setAttribute("unit_price", unit_price);
	    session.setAttribute("quantity", quantity);
	    session.setAttribute("remarks", remarks);
		
		response.sendRedirect(request.getContextPath() + "/S0011.html");
	}

}
