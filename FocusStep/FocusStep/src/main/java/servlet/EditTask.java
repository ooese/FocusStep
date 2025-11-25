package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import dao.TasksDAO;
import model.Task;
import util.TaskUtils;

/**
 * Servlet implementation class PostTask
 */
@WebServlet("/EditTask")
public class EditTask extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        // ログインチェックはフィルターで行うため不要

        // 今日のタスクを共通メソッドで取得
        List<Task> todayTasks = util.TaskUtils.getTodayTasks(session);
        request.setAttribute("todayTasks", todayTasks);

        // postTask.jsp にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/postTask.jsp");
        dispatcher.forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int userId = (Integer) session.getAttribute("userId");

		try {
			// フォームからタスク情報取得
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			String status = request.getParameter("status");
			int priority = Integer.parseInt(request.getParameter("priority"));
			Date schedule = Date.valueOf(request.getParameter("schedule")); // yyyy-MM-dd
			Time startTime = Time.valueOf(request.getParameter("startTime") + ":00"); // HH:mm
			int target = Integer.parseInt(request.getParameter("target"));
			int actual = 0;
			Time reminderTime = Time.valueOf(request.getParameter("reminderTime") + ":00");

			// 必須チェック
			if (title != null && !title.isEmpty() &&
					schedule != null && startTime != null && reminderTime != null) {

				Task task = new Task(userId, title, description, status,
						priority, schedule, startTime, target, actual, reminderTime);

				TasksDAO dao = new TasksDAO();
				boolean result = dao.create(task);

				if (result) {
					request.setAttribute("successMsg", "タスクを登録しました");
				} else {
					request.setAttribute("errorMsg", "タスクの登録に失敗しました");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", "入力値の形式が正しくありません");
		}
		// 追加後に今日のタスク一覧を再取得
		List<Task> todayTasks = TaskUtils.getTodayTasks(session);
		request.setAttribute("todayTasks", todayTasks);

		//タスク追加画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/editTask.jsp");
		dispatcher.forward(request, response);
	}
}
