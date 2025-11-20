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

import dao.TasksDAO;
import model.Account;
import model.Task;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//ログインしているか確認するためセッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		Account loginAccount = (Account)session.getAttribute("loginAccount");
		int userId = (Integer) session.getAttribute("userId");
		
		if(loginAccount == null) { //ログインしてない場合
			//リダイレクト
			response.sendRedirect("index.jsp");
		} else { //ログイン済みの場合
			// タスク一覧を取得
	        TasksDAO dao = new TasksDAO();
	        List<Task> taskList = dao.findTodayTasksByUserId(userId);
	        
	        // リクエストスコープにセット
	        request.setAttribute("taskList", taskList);	        

			//Mainへフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}