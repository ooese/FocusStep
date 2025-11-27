package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.TasksDAO;

/**
 * Servlet implementation class StartTask
 */
@WebServlet("/StartTask")
public class StartTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartTask() {
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
		int taskId = Integer.parseInt(request.getParameter("taskId"));
        String startTimeStr = request.getParameter("startTime");

        java.sql.Timestamp startTime = java.sql.Timestamp.valueOf(
            startTimeStr.replace("T", " ").replace("Z", "")
        );

        TasksDAO dao = new TasksDAO();
        dao.updateStartTime(taskId, startTime);

        response.getWriter().write("OK");
	}

}
