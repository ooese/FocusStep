package servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class LoginServlet
 */
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
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/index.jsp");
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		String loginId = request.getParameter("loginId");
		String pass = request.getParameter("pass");
		
		//ログイン処理の実行
		Login login = new Login(loginId,pass);
//		LoginLogic bo = new LoginLogic();
//		boolean result = bo.execute(login);
//		
//		//ログイン処理の成否によって処理を分岐
//		if (result) {//ログイン成功時
//			//セッションスコープにユーザーIDを保存
//			HttpSession session = request.getSession();
//			session.setAttribute("loginId",loginId);
//			//フォワード
//			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
//			dispatcher.forward(request,response);
//			} else {//ログイン失敗時
//				//リダイレクト
//				response.sendRedirect("LoginServlet");
		
		//ログイン後にフルネームを保存
	    AccountsDAO dao = new AccountsDAO();
	    Account account = dao.findByLogin(login);

	    if (account != null) { // ログイン成功
	        HttpSession session = request.getSession();

	        // フルネーム作成
	        String fullName = account.getNameSei() + " " + account.getNameMei();

	        // セッションに保存
	        session.setAttribute("fullName", fullName);

	        // メインページへフォワード
	        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
	        dispatcher.forward(request, response);

	    } else { // ログイン失敗
	        response.sendRedirect("LoginServlet");
	    }
	}
}
