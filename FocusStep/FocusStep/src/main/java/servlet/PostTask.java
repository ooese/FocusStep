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

import model.Account;
import model.GetTaskListLogic;
import model.PostTaskLogic;
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
		//リクエストパラメーターの取得
				request.setCharacterEncoding("UTF-8");
				String text = request.getParameter("text");
				
				//入力値チェック
				if(text != null && text.length() !=0) {
					//セッションスコープに保存されたユーザー情報を取得
					HttpSession session = request.getSession();
					Account loginAccount = (Account)session.getAttribute("loginAccount");
					
					//つぶやきを作成してつぶやきリストに追加
					Task task = new Task(loginAccount.getNameMei(),text);
					PostTaskLogic postMutterLogic = new PostTaskLogic();
					postMutterLogic.execute(task);
				}else {
					//エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "タスクが入力されていません");
				}
				//タスクリストを取得して、リクエストスコープに保存
				GetTaskListLogic getMutterListLogic = new GetTaskListLogic();
				List<Task> mutterList = getMutterListLogic.execute();
				request.setAttribute("taskList", taskList);
				
				
				//メイン画面にフォワード
				RequestDispatcher dispatcher =
						request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
				dispatcher.forward(request, response);
	}

}
