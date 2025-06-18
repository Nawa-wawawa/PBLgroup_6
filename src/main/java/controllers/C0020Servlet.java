package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class C0020Servlet
 */
@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public C0020Servlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
        // ダミー売上データ（円単位）
        int yearlyTotal = 12000000;      // 全体年間売上
        int yearlyUser = 3500000;        // ユーザー年間売上
        int monthlyTotal = 1100000;      // 全体月間売上
        int monthlyUser = 280000;        // ユーザー月間売上

        // 売上前年比（％）
        double prevYearCompareTotal = 8.5;    // +8.5%
        double prevYearCompareUser = -3.2;    // -3.2%
        double prevMonthCompareTotal = 2.0;   // +2.0%
        double prevMonthCompareUser = 5.1;    // +5.1%

        // カテゴリー別売上割合（5カテゴリ）
        List<String> categoryLabels = List.of("食品", "衣類", "家電", "雑貨", "書籍");
        List<Integer> categoryData = List.of(4800000, 3200000, 1800000, 1200000, 1000000);

        // リクエスト属性にセット
        request.setAttribute("yearlyTotal", yearlyTotal);
        request.setAttribute("yearlyUser", yearlyUser);
        request.setAttribute("monthlyTotal", monthlyTotal);
        request.setAttribute("monthlyUser", monthlyUser);
        request.setAttribute("prevYearCompareTotal", prevYearCompareTotal);
        request.setAttribute("prevYearCompareUser", prevYearCompareUser);
        request.setAttribute("prevMonthCompareTotal", prevMonthCompareTotal);
        request.setAttribute("prevMonthCompareUser", prevMonthCompareUser);
        request.setAttribute("categoryLabels", categoryLabels);
        request.setAttribute("categoryData", categoryData);

       
		request.getRequestDispatcher("/WEB-INF/jsp/C0020.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
