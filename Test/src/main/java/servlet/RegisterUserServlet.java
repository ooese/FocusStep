package servlet;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.UserAccountsDAO;
import model.UserAccount;

/**
 * Servlet implementation class RegisterUserServlet
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/registerUser.jsp");
				dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        String sei = request.getParameter("nameSei");
        String mei = request.getParameter("nameMei");
        String seiKana = request.getParameter("nameSeiKana");
        String meiKana = request.getParameter("nameMeiKana");
        // Get the birthdate as a string
        String birthStr = request.getParameter("birth");
        LocalDate birth = LocalDate.parse(birthStr); // Parse to LocalDate
        String phone = request.getParameter("phone");
        String mail = request.getParameter("mail");

        // --- 修正箇所: 初期パスワードの生成と設定 ---
        // 初期パスワードを生成 (誕生日 YYYYMMDD 形式)
        String initialPassword = birth.toString().replace("-", ""); // YYYY-MM-DD から YYYYMMDD へ
        
        UserAccountsDAO dao = new UserAccountsDAO();
        String userId = dao.generateNextUserId();

        // UserAccountオブジェクトを生成し、initialPasswordを設定
        // UserAccount(userId, password, sei, mei, seiKana, meiKana, birth, phone, mail)
        UserAccount user = new UserAccount(userId, initialPassword, sei, mei, seiKana, meiKana, birth, phone, mail);

        boolean success = dao.insert(user);
        // --------------------------------------------------

        response.setContentType("text/html; charset=UTF-8");
        if (success) {
            response.getWriter().println("<h2>ユーザー登録完了</h2>");
            response.getWriter().println("<p>ユーザーID：" + userId + "</p>");
            response.getWriter().println("<p>初期パスワード：" + initialPassword + "</p>"); // 変数initialPasswordを使用
        } else {
            response.getWriter().println("<h2>登録に失敗しました。</h2>");
        }
    }
}
