package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import services.SalesService;
import utils.Db;

@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public C0020Servlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try (Connection conn = Db.open()) {

            LocalDate today = LocalDate.now();
            LocalDate startYear = today.withDayOfYear(1);
            LocalDate startMonth = today.withDayOfMonth(1);
            LocalDate prevYearStart = startYear.minusYears(1);
            LocalDate prevYearEnd = startYear.minusDays(1);
            LocalDate prevMonthStart = startMonth.minusMonths(1);
            LocalDate prevMonthEnd = startMonth.minusDays(1);

            SalesService salesService = new SalesService(conn);

            // 全体売上とユーザー数
            int yearlyTotal = salesService.getTotalSales(startYear, today);
            int yearlyUserCount = salesService.getUniqueUserCount(startYear, today);
            int monthlyTotal = salesService.getTotalSales(startMonth, today);
            int monthlyUserCount = salesService.getUniqueUserCount(startMonth, today);

            int prevYearTotal = salesService.getTotalSales(prevYearStart, prevYearEnd);
            int prevYearUserCount = salesService.getUniqueUserCount(prevYearStart, prevYearEnd);
            int prevMonthTotal = salesService.getTotalSales(prevMonthStart, prevMonthEnd);
            int prevMonthUserCount = salesService.getUniqueUserCount(prevMonthStart, prevMonthEnd);

            double prevYearCompareTotal = salesService.calcPercentChange(yearlyTotal, prevYearTotal);
            double prevYearCompareUser = salesService.calcPercentChange(yearlyUserCount, prevYearUserCount);
            double prevMonthCompareTotal = salesService.calcPercentChange(monthlyTotal, prevMonthTotal);
            double prevMonthCompareUser = salesService.calcPercentChange(monthlyUserCount, prevMonthUserCount);

            // カテゴリ別売上
            Map<Integer, String> categoryIdToName = Map.of(
                2, "食品",
                3, "衣類",
                4, "家電",
                5, "雑貨",
                6, "書籍"
            );

            List<String> categoryLabels = categoryIdToName.values().stream().collect(Collectors.toList());
            List<Integer> categoryData = salesService.getCategorySums(startYear, today, categoryIdToName.keySet());

            Map<String, List<Integer>> monthlyCategoryData = new LinkedHashMap<>();
            for (Map.Entry<Integer, String> entry : categoryIdToName.entrySet()) {
                List<Integer> monthlySums = salesService.getMonthlyCategorySums(today.getYear(), entry.getKey());
                monthlyCategoryData.put(entry.getValue(), monthlySums);
            }

            // あなたの売上（ログインユーザー）
            HttpSession session = request.getSession(false);
            int accountId = 0;
            if (session != null) {
                accounts user = (accounts) session.getAttribute("user");
                if (user != null) {
                    accountId = user.getAccount_id();
                }
            }

            int yearlyUserTotal = salesService.getUserSales(startYear, today, accountId);
            int monthlyUserTotal = salesService.getUserSales(startMonth, today, accountId);

            // あなたの売上 - 前年・前月比較
            int prevYearUserTotal = salesService.getUserSales(prevYearStart, prevYearEnd, accountId);
            int prevMonthUserTotal = salesService.getUserSales(prevMonthStart, prevMonthEnd, accountId);

            double prevYearCompareUserTotal = salesService.calcPercentChange(yearlyUserTotal, prevYearUserTotal);
            double prevMonthCompareUserTotal = salesService.calcPercentChange(monthlyUserTotal, prevMonthUserTotal);

            // JSPに渡す
            request.setAttribute("yearlyTotal", yearlyTotal);
            request.setAttribute("yearlyUserCount", yearlyUserCount);
            request.setAttribute("monthlyTotal", monthlyTotal);
            request.setAttribute("monthlyUserCount", monthlyUserCount);
            request.setAttribute("prevYearCompareTotal", prevYearCompareTotal);
            request.setAttribute("prevYearCompareUser", prevYearCompareUser);
            request.setAttribute("prevMonthCompareTotal", prevMonthCompareTotal);
            request.setAttribute("prevMonthCompareUser", prevMonthCompareUser);

            request.setAttribute("categoryLabels", categoryLabels);
            request.setAttribute("categoryData", categoryData);
            request.setAttribute("monthlyCategoryData", monthlyCategoryData);

            request.setAttribute("yearlyUserTotal", yearlyUserTotal);
            request.setAttribute("monthlyUserTotal", monthlyUserTotal);
            request.setAttribute("prevYearCompareUserTotal", prevYearCompareUserTotal);
            request.setAttribute("prevMonthCompareUserTotal", prevMonthCompareUserTotal);

            request.getRequestDispatcher("/WEB-INF/jsp/C0020.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
