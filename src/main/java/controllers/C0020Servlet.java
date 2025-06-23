package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.accounts;
import utils.Db;

@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public C0020Servlet() {
        super();
    }

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

            int yearlyTotal = selectSum(conn, startYear, today);
            int yearlyUserCount = selectUniqueUsers(conn, startYear, today);
            int monthlyTotal = selectSum(conn, startMonth, today);
            int monthlyUserCount = selectUniqueUsers(conn, startMonth, today);

            int prevYearTotal = selectSum(conn, prevYearStart, prevYearEnd);
            int prevYearUserCount = selectUniqueUsers(conn, prevYearStart, prevYearEnd);
            int prevMonthTotal = selectSum(conn, prevMonthStart, prevMonthEnd);
            int prevMonthUserCount = selectUniqueUsers(conn, prevMonthStart, prevMonthEnd);

            double prevYearCompareTotal = calcPercentChange(yearlyTotal, prevYearTotal);
            double prevYearCompareUser = calcPercentChange(yearlyUserCount, prevYearUserCount);
            double prevMonthCompareTotal = calcPercentChange(monthlyTotal, prevMonthTotal);
            double prevMonthCompareUser = calcPercentChange(monthlyUserCount, prevMonthUserCount);

            Map<Integer, String> categoryIdToName = Map.of(
                2, "食品",
                3, "衣類",
                4, "家電",
                5, "雑貨",
                6, "書籍"
            );

            List<String> categoryLabels = categoryIdToName.values().stream().collect(Collectors.toList());
            List<Integer> categoryData = selectCategorySum(conn, startYear, today, categoryIdToName.keySet());

            Map<String, List<Integer>> monthlyCategoryData = new LinkedHashMap<>();
            for (Map.Entry<Integer, String> entry : categoryIdToName.entrySet()) {
                Integer catId = entry.getKey();
                String catName = entry.getValue();
                List<Integer> monthlySums = selectMonthlyCategorySum(conn, today.getYear(), catId);
                monthlyCategoryData.put(catName, monthlySums);
            }

            // ログインユーザーの売上取得
            HttpSession session = request.getSession(false);
            int accountId = 0; // 初期値

            if (session != null) {
                accounts user = (accounts) session.getAttribute("user");
                if (user != null) {
                    accountId = user.getAccount_id();
                }
            }

            int yearlyUserTotal = selectUserSum(conn, startYear, today, accountId);
            int monthlyUserTotal = selectUserSum(conn, startMonth, today, accountId);

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

            request.getRequestDispatcher("/WEB-INF/jsp/C0020.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private int selectSum(Connection conn, LocalDate start, LocalDate end) throws SQLException {
        String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    private int selectUniqueUsers(Connection conn, LocalDate start, LocalDate end) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT account_id) FROM sales WHERE sale_date BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }

    private double calcPercentChange(int current, int previous) {
        if (previous == 0) return 0.0;
        return ((double)(current - previous) / previous) * 100;
    }

    private List<Integer> selectCategorySum(Connection conn, LocalDate start, LocalDate end, 
            Iterable<Integer> categoryIds) throws SQLException {
        String sql = "SELECT category_id, SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ? GROUP BY category_id";
        Map<Integer, Integer> sums = new LinkedHashMap<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sums.put(rs.getInt(1), rs.getInt(2));
                }
            }
        }

        List<Integer> categoryIdList = StreamSupport.stream(categoryIds.spliterator(), false)
                                                    .collect(Collectors.toList());

        return categoryIdList.stream()
                .map(id -> sums.getOrDefault(id, 0))
                .collect(Collectors.toList());
    }

    private List<Integer> selectMonthlyCategorySum(Connection conn, int year, int categoryId) throws SQLException {
        List<Integer> monthlySums = new java.util.ArrayList<>();
        for (int month = 1; month <= 12; month++) {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
            String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE category_id = ? AND sale_date BETWEEN ? AND ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, categoryId);
                ps.setDate(2, Date.valueOf(start));
                ps.setDate(3, Date.valueOf(end));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        monthlySums.add(rs.getInt(1));
                    } else {
                        monthlySums.add(0);
                    }
                }
            }
        }
        return monthlySums;
    }

    private int selectUserSum(Connection conn, LocalDate start, LocalDate end, int accountId) throws SQLException {
        if (accountId == 0) {
            // ログインしていない、もしくはIDが0なら売上0
            return 0;
        }
        String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ? AND account_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            ps.setInt(3, accountId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
}
