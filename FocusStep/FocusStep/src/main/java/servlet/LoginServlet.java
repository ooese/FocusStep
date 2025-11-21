package servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.AccountsDAO;
import model.Account;
import model.Login;
import model.Task;

/**
 * Servlet implementation class LoginServlet
 */

//LoginServletにしないと変数が被る

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//index.jspにフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String loginId = request.getParameter("loginId");
        String pass = request.getParameter("pass");

        AccountsDAO dao = new AccountsDAO();
        Account account = dao.findByLogin(new Login(loginId, pass));

        if (account != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginAccount", account);
            session.setAttribute("userId", account.getUserId());
            session.setAttribute("fullName", account.getNameSei() + " " + account.getNameMei());

            // 今日のタスクを共通メソッドで取得
            List<Task> todayTasks = util.TaskUtils.getTodayTasks(session);
            request.setAttribute("todayTasks", todayTasks);

            //Mainサーブレットにリダイレクト
            response.sendRedirect(request.getContextPath() + "/Main");
            
        } else {
            // ログイン失敗
            request.setAttribute("errorMsg", "ログインIDまたはパスワードが違います");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
            dispatcher.forward(request, response);
        }
    }
}
