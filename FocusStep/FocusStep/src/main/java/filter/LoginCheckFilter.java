package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Account;

public class LoginCheckFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // フィルター初期化処理（必要なければ空でOK）
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false); // false: 新しいセッションを作らない
        Account loginAccount = (session != null) ? (Account) session.getAttribute("loginAccount") : null;

        if (loginAccount == null) {
            // ログインしていなければログイン画面にリダイレクト
            res.sendRedirect(req.getContextPath() + "/LoginServlet");
            return; // 処理をここで止める
        }

        // ログイン済みの場合は次の処理に進む
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // フィルター破棄処理（必要なければ空でOK）
    }
}
