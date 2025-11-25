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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//ログインチェックはフィルターで行うので不要

		// 今日のタスクを共通メソッドで取得
		List<Task> todayTasks = util.TaskUtils.getTodayTasks(session);
		request.setAttribute("todayTasks", todayTasks);

		// ★ 今やること（nextTask）
        Task nextTaskToDo = null;

        if (todayTasks != null && !todayTasks.isEmpty()) {
            int lowestPriority = Integer.MAX_VALUE;

            for (Task t : todayTasks) {
                String status = t.getStatus();

                // ★ 完了を除外（日本語に合わせる）
                if (!"完了".equals(status) && t.getPriority() < lowestPriority) {
                    nextTaskToDo = t;
                    lowestPriority = t.getPriority();
                }
            }
        }

        // ★ JSPへ渡す
        request.setAttribute("nextTask", nextTaskToDo);
        
		// main.jsp にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ここでは処理がなければ doGet に委譲
		doGet(request, response);
	}
}