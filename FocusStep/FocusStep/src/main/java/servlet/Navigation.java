package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Navigation
 */
@WebServlet("/Navigation")
public class Navigation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Navigation() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// パラメータで画面を判定
        String action = request.getParameter("action");
        String path = null;

        switch (action) {
            case "post":path = "WEB-INF/jsp/postTask.jsp";
                break;
            case "edit": path = "WEB-INF/jsp/editTask.jsp";
                break;
            case "review":path = "WEB-INF/jsp/reviewToday.jsp";
                break;
            case "prepare":path = "WEB-INF/jsp/prepareTomorrow.jsp";
                break;
            default:path = "WEB-INF/jsp/error.jsp"; // 未定義のアクション
                break;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
