package controller;

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

import Bean.sales;
import Service.Sales;
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
		

		ArrayList<sales> salelist = null;

		
		try (Connection con = Db.open()) {
			Sales sv = new Sales();
			salelist = sv.select();

			sales sl = new sales(Date.valueOf("2020-01-28"),2,2,"ika",500,1,"そこそこおいしい");
			
			sv.update(sl, 4);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}
		for(sales sale : salelist) {
			System.out.println(sale.getTrade_name());
		}
		
		//request.getRequestDispatcher("/jsp/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
