package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import model.Task;

public class TasksDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:h2:tcp://localhost/~/desktop/H2/FocusStep";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	//findAllの定義
	public List<Task> findAll() {
		List<Task> taskList = new ArrayList<>();

		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//SELECT文の準備
			String sql = "SELECT TASK_ID,USER_ID,TITLE,DESCRIPTION,STATUS,PRIORITY,SCHEDULED_DATE,START_TIME,TARGET_DURATION_MIN,ACTUAL_DURATION_MIN,REMINDER_TIME FROM TASKS ORDER BY TASK_ID ASC";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			//				,IS_NEXT_LOCKED,NEXT_TASK_ID

			//SELECT文を実行
			ResultSet rs = pStmt.executeQuery();

			//SELECT文の結果をArrayListに格納
			while (rs.next()) {
				int taskId = rs.getInt("TASK_ID");
				; //ID
				int userId = rs.getInt("USER_ID");
				; //ユーザーID
				String title = rs.getString("TITLE");
				; //タスク名
				String description = rs.getString("DESCRIPTION");
				; //詳細
				String status = rs.getString("STATUS");
				; //タスクの状態
				int priority = rs.getInt("PRIORITY");
				; //優先順位
				Date schedule = rs.getDate("SCHEDULED_DATE");
				; //実行予定日
				Time startTime = rs.getTime("START_TIME");
				; //実行開始時間
				int target = rs.getInt("TARGET_DURATION_MIN");
				; //目標時間（分）
				int actual = rs.getInt("ACTUAL_DURATION_MIN");
				; //実質時間（分）
				Time reminderTime = rs.getTime("REMINDER_TIME");
				; //リマインダー通知時間
				//					boolean nextLocked = rs.getBoolean("IS_NEXT_LOCKED");; //次のタスクをロックするか
				//					int nextTask = rs.getInt("NEXT_TASK_ID");; //次に表示するタスクID（連携）

				Task task = new Task(taskId, userId, title, description, status, priority, schedule, startTime, target,
						actual, reminderTime);
				taskList.add(task);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return taskList;
	}

	public boolean create(Task task) {
		//JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JCBDドライバを読み込めませんでした");
		}

		//データベース接続
		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {

			//INSERT文の準備（task_idは自動連番なので指定しなくてよい）
			String sql = "INSERT INTO TASKS(USER_ID, TITLE, DESCRIPTION, STATUS, PRIORITY, SCHEDULED_DATE, START_TIME, TARGET_DURATION_MIN, ACTUAL_DURATION_MIN, REMINDER_TIME) VALUES(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			//INSERT文中の「？」に使用する値を設定してSQL文を完成
			pStmt.setInt(1, task.getUserId());
			pStmt.setString(2, task.getTitle());
			pStmt.setString(3, task.getDescription());
			pStmt.setString(4, task.getStatus());
			pStmt.setInt(5, task.getPriority());
			pStmt.setDate(6, task.getSchedule());
			pStmt.setTime(7, task.getStartTime());
			pStmt.setInt(8, task.getTarget());
			pStmt.setInt(9, task.getActual());
			pStmt.setTime(10, task.getReminderTime());

			//INSET文を実行（resultには追加された行数が代入される）
			int result = pStmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// 今日のタスク（ユーザーID指定）
	public List<Task> findByUser(int userId) {
		List<Task> taskList = new ArrayList<>(); // 最初から空リスト
		// JDBCドライバを読み込む
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		// データベース接続
		String sql = "SELECT TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS, PRIORITY, "
				+ "SCHEDULED_DATE, START_TIME, TARGET_DURATION_MIN, ACTUAL_DURATION_MIN, REMINDER_TIME "
				+ "FROM TASKS WHERE USER_ID = ? ORDER BY TASK_ID ASC";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Task task = new Task(
						rs.getInt("TASK_ID"),
						rs.getInt("USER_ID"),
						rs.getString("TITLE"),
						rs.getString("DESCRIPTION"),
						rs.getString("STATUS"),
						rs.getInt("PRIORITY"),
						rs.getDate("SCHEDULED_DATE"),
						rs.getTime("START_TIME"),
						rs.getInt("TARGET_DURATION_MIN"),
						rs.getInt("ACTUAL_DURATION_MIN"),
						rs.getTime("REMINDER_TIME"));
				taskList.add(task);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return taskList; // ←ここは絶対に null にしない！
	}

	public List<Task> findTodayTasksByUserId(int userId) {
		List<Task> taskList = new ArrayList<>();

		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}

		String sql = "SELECT TASK_ID, USER_ID, TITLE, DESCRIPTION, STATUS, PRIORITY, "
				+ "SCHEDULED_DATE, START_TIME, TARGET_DURATION_MIN, ACTUAL_DURATION_MIN, REMINDER_TIME "
				+ "FROM TASKS "
				+ "WHERE USER_ID = ? AND SCHEDULED_DATE = CURRENT_DATE() "
				+ "ORDER BY START_TIME ASC";

		try (Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
				PreparedStatement pStmt = conn.prepareStatement(sql)) {

			pStmt.setInt(1, userId);

			ResultSet rs = pStmt.executeQuery();

			while (rs.next()) {
				Task task = new Task(
						rs.getInt("TASK_ID"),
						rs.getInt("USER_ID"),
						rs.getString("TITLE"),
						rs.getString("DESCRIPTION"),
						rs.getString("STATUS"),
						rs.getInt("PRIORITY"),
						rs.getDate("SCHEDULED_DATE"),
						rs.getTime("START_TIME"),
						rs.getInt("TARGET_DURATION_MIN"),
						rs.getInt("ACTUAL_DURATION_MIN"),
						rs.getTime("REMINDER_TIME"));
				taskList.add(task);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null; // ここは error 時のみ null
		}

		return taskList;
	}	
	public void updateActualTime(int taskId, int actualMinutes) {
	    String sql = "UPDATE TASKS SET ACTUAL_DURATION_MIN = ? WHERE task_id = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	        pstmt.setInt(1, actualMinutes);
	        pstmt.setInt(2, taskId);
	        pstmt.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	// 共通して DB に接続するメソッド
	private Connection getConnection() throws SQLException {
	    try {
	        Class.forName("org.h2.Driver");
	    } catch (ClassNotFoundException e) {
	        throw new IllegalStateException("JDBCドライバを読み込めませんでした");
	    }

	    return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
	}
	public void updateStatus(int taskId, String status) {
	    String sql = "UPDATE TASKS SET STATUS = ? WHERE task_id = ?";

	    try (Connection conn = getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, status);
	        stmt.setInt(2, taskId);

	        stmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
}
