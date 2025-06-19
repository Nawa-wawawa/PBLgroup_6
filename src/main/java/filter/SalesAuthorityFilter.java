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

@WebFilter(urlPatterns = {"/S0022.html", "/S0023.html", "/S0024.html", "/S0025.html"})
public class SalesAuthorityFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        
        if (session == null || session.getAttribute("user") == null) {
            // セッションがないか、ユーザー情報がない場合はログアウト
            res.sendRedirect("/LogoutServlet");
            return;
        }

        // ユーザー情報取得（Userオブジェクトがある前提）
        Object userObj = session.getAttribute("user");
        if (!(userObj instanceof beans.accounts)) {
            res.sendRedirect("/LogoutServlet");
            return;
        }

        beans.accounts user = (beans.accounts) userObj;
        int authority = user.getAuthority();

        // 売上登録権限があるかどうか（bitの1桁目が1かどうか）
        // 1 または 3 (2進数: 01 or 11) のとき許可
        if (authority == 1 || authority == 3) {
            chain.doFilter(request, response); // 通過
        } else {
            // 権限がない場合はログアウト
            res.sendRedirect("/LogoutServlet");
        }
    }

    public void init(FilterConfig fConfig) throws ServletException {}
    public void destroy() {}
}
