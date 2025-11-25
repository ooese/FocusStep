package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@WebServlet("/GetTask")
public class GetTask extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetTask() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        // ログインしていない場合はログイン画面へ
        if (userId == null) {
            response.sendRedirect("LoginServlet");
            return;
        }

        // DBからユーザーのタスクを取得
        TasksDAO dao = new TasksDAO();
        List<Task> allTasks = dao.findByUser(userId);

        // 今日の日付 (yyyy-MM-dd)
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String todayStr = sdf.format(new Date());

        // 今日のタスクを抽出
        List<Task> todayTasks = new ArrayList<>();
        for (Task task : allTasks) {

            // task.getSchedule() が java.sql.Date or java.util.Date のどちらでも対応
            String taskDate = sdf.format(task.getSchedule());

            if (todayStr.equals(taskDate)) {
                todayTasks.add(task);
            }
        }

        // JSP へ渡す
        request.setAttribute("taskList", todayTasks);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/jsp/editTask.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
