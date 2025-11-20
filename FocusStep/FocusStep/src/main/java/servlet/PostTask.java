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
import model.GetTaskListLogic;
import model.Task;

/**
 * Servlet implementation class PostTask
 */
@WebServlet("/PostTask")
public class PostTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostTask() {
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
		try {
		//リクエストパラメーターの取得
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		int userId = (Integer) session.getAttribute("userId");
		String title = request.getParameter("title");
	    String description = request.getParameter("description");
	    String status = request.getParameter("status");
	    
	 // --- 文字列取得 ---
        String scheduleStr = request.getParameter("schedule");
        String startTimeStr = request.getParameter("startTime");
        String reminderStr = request.getParameter("reminderTime");
        String priorityStr = request.getParameter("priority");
        String targetStr = request.getParameter("target");

        // --- 数値は安全にパースする ---
        int priority = (priorityStr == null || priorityStr.isEmpty()) ? 0 : Integer.parseInt(priorityStr);
        int target = (targetStr == null || targetStr.isEmpty()) ? 0 : Integer.parseInt(targetStr);
        int actual = 0;

        // --- 時刻と日付の変換 ---
        Date schedule = (scheduleStr == null || scheduleStr.isEmpty()) ? null : Date.valueOf(scheduleStr);
        Time startTime = (startTimeStr == null || startTimeStr.isEmpty()) ? null : Time.valueOf(startTimeStr + ":00");
        Time reminderTime = (reminderStr == null || reminderStr.isEmpty()) ? null : Time.valueOf(reminderStr + ":00");

	    
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

    // タスクリスト更新
    GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
    List<Task> taskList = getTaskListLogic.execute();
    request.setAttribute("taskList", taskList);
        
//	    Date schedule = Date.valueOf(request.getParameter("schedule")); // yyyy-MM-dd
//	    Time startTime = Time.valueOf(request.getParameter("startTime") + ":00"); // HH:mm:ss
//	    int target = Integer.parseInt(request.getParameter("target"));
//	    Time reminderTime = Time.valueOf(request.getParameter("reminderTime") + ":00");
// // HH:mm:ss
//	    int priority = Integer.parseInt(request.getParameter("priority"));
//	    int actual = 0;// 実質時間（actual）は初期値として 0 分に設定（後で更新される想定）
//
//		//入力値チェック（すべての必須項目が埋まっているか）
//		if(title!= null && !title.isEmpty() && 
//			schedule != null &&
//			startTime != null &&
//			reminderTime != null) {
//			//タスクを作成してタスクリストに追加
//			Task task = new Task(userId, title, description, status, priority ,schedule, startTime, target, actual, reminderTime);
			
//			PostTaskLogic postTaskLogic = new PostTaskLogic();
//			postTaskLogic.execute(task);
			
//			//DB登録処理
//			TasksDAO dao = new TasksDAO();
//            boolean result = dao.create(task);
//            
//            if (result) {
//                request.setAttribute("successMsg", "タスクを登録しました");
//            } else {
//                request.setAttribute("errorMsg", "タスクの登録に失敗しました");
//			}
//		}
//		} catch (Exception e) {
//	        e.printStackTrace();
//	        request.setAttribute("errorMsg", "入力値の形式が正しくありません");
//	    }

//	//タスクリストを取得して、リクエストスコープに保存
//	GetTaskListLogic getTaskListLogic = new GetTaskListLogic();
//	List<Task> taskList = getTaskListLogic.execute();
//	request.setAttribute("taskList", taskList);
	
	//タスク追加画面にフォワード
	RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/postTask.jsp");
	dispatcher.forward(request, response);
}}
