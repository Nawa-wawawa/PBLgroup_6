package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DashboardService {
    private Connection conn;

    public DashboardService(Connection conn) {
        this.conn = conn;
    }

    public int getTotalSales(LocalDate start, LocalDate end) throws SQLException {
        String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public int getUniqueUserCount(LocalDate start, LocalDate end) throws SQLException {
        String sql = "SELECT COUNT(DISTINCT account_id) FROM sales WHERE sale_date BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return 0;
    }

    public double calcPercentChange(int current, int previous) {
        if (previous == 0) return 0.0;
        return ((double)(current - previous) / previous) * 100;
    }

    public List<Integer> getCategorySums(LocalDate start, LocalDate end, Iterable<Integer> categoryIds) throws SQLException {
        String sql = "SELECT category_id, SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ? GROUP BY category_id";
        Map<Integer, Integer> sums = new LinkedHashMap<>();

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(start));
            ps.setDate(2, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sums.put(rs.getInt("category_id"), rs.getInt(2));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (Integer id : categoryIds) {
            result.add(sums.getOrDefault(id, 0));
        }

        return result;
    }

    public List<Integer> getMonthlyCategorySums(int year, int categoryId) throws SQLException {
        List<Integer> result = new ArrayList<>();
        String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE category_id = ? AND sale_date BETWEEN ? AND ?";

        for (int month = 1; month <= 12; month++) {
            LocalDate start = LocalDate.of(year, month, 1);
            LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, categoryId);
                ps.setDate(2, Date.valueOf(start));
                ps.setDate(3, Date.valueOf(end));
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        result.add(rs.getInt(1));
                    } else {
                        result.add(0);
                    }
                }
            }
        }

        return result;
    }

    public int getUserSales(LocalDate start, LocalDate end, int accountId) throws SQLException {
        if (accountId == 0) return 0;

        String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE account_id = ? AND sale_date BETWEEN ? AND ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountId);
            ps.setDate(2, Date.valueOf(start));
            ps.setDate(3, Date.valueOf(end));
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getInt(1);
            }
        }

        return 0;
    }
}
