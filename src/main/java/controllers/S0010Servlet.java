package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.categories;
import services.Categories;
import utils.Db;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010Servlet")
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
//		ArrayList<sales> salelist = null;
		ArrayList<categories> categorylist = null;

		
		try (Connection con = Db.open()) {
			Categories ct = new Categories();
			categorylist = ct.select();
//			Sales sl = new Sales();
//			salelist = sl.select();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		
	    request.setAttribute("today", today);
	    request.setAttribute("categorylist", categorylist);
	  //request.setAttribute("saccountlist", accountlist);
	    
	    
		
		request.getRequestDispatcher("/jsp/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/S0011Servlet");
	}

}
