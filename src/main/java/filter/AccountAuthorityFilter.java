package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/C0030.html", "/C0031.html", "/S0042.html", "/S0043.html" , "/S0044.html"})
public class AccountAuthorityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            // セッションなし or ユーザ情報なしはログアウト
            res.sendRedirect("/Logout.html");
            return;
        }

        Object userObj = session.getAttribute("user");
        if (!(userObj instanceof beans.accounts)) {
            res.sendRedirect("/Logout.html");
            return;
        }

        beans.accounts user = (beans.accounts) userObj;
        int authority = user.getAuthority();

        // アカウント登録権限ビットのチェック（2 または 3 の場合のみ許可）
        if (authority == 2 || authority == 3) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("/Logout.html");
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {}

    @Override
    public void destroy() {}
}
