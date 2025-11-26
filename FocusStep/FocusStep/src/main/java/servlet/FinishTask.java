package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TasksDAO;

/**
 * Servlet implementation class FinishTask
 */
@WebServlet("/FinishTask")
public class FinishTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FinishTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int taskId = Integer.parseInt(request.getParameter("taskId"));
        int actualMinutes = Integer.parseInt(request.getParameter("actualMinutes"));

        TasksDAO dao = new TasksDAO();
     // 実績時間を保存
        dao.updateActualTime(taskId, actualMinutes);
     // ステータスを「完了」に変更
        dao.updateStatus(taskId, "完了");

        response.setContentType("text/plain; charset=UTF-8");
        response.getWriter().write("success");
	}

}
