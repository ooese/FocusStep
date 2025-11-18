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
		private final String JDBC_URL = " jdbc:h2:tcp://localhost/~/desktop/H2/FocusStep";
		private final String DB_USER = "sa";
		private final String DB_PASS = "";

		
		public List<Task>findAll(){
			List<Task>taskList = new ArrayList<>();
			
			//JDBCドライバを読み込む
			try {
				Class.forName("org.h2.Driver");
			}catch(ClassNotFoundException e){
				throw new IllegalStateException("JDBCドライバを読み込めませんでした");
			}
			//データベース接続
			try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
				
				//SELECT文の準備
				String sql = "SELECT TASK_ID,USER_ID,TITLE,DESCRIPTION,STATUS,PRIORITY,SCHEDULED_DATE,START_TIME,TARGET_DURATION_MIN,ACTUAL_DURATION_MIN,REMINDER_TIME,IS_NEXT_LOCKED,NEXT_TASK_ID FROM TASKS ORDER BY ID DESC";
				PreparedStatement pStmt = conn.prepareStatement(sql);
				
				//SELECT文を実行
				ResultSet rs = pStmt.executeQuery();
				
				//SELECT文の結果をArrayListに格納
				while(rs.next()) {
					int taskId = rs.getInt("TASK_ID");; //ID
					int userId = rs.getInt("USER_ID");; //ユーザーID
					String title = rs.getString("TITLE");; //タスク名
					String description = rs.getString("DESCRIPTION");; //詳細
					String status = rs.getString("STATUS");; //タスクの状態
					int priority = rs.getInt("PRIORITY");; //優先順位
					Date schedule = rs.getDate("SCHEDULED_DATE");; //実行予定日
					Time startTime = rs.getTime("START_TIME");; //実行開始時間
					int target = rs.getInt("TARGET_DURATION_MIN");; //目標時間（分）
					int actual = rs.getInt("ACTUAL_DURATION_MIN");; //実質時間（分）
					Time reminderTime = rs.getTime("REMINDER_TIME");; //リマインダー通知時間
					boolean nextLocked = rs.getBoolean("IS_NEXT_LOCKED");; //次のタスクをロックするか
					int nextTask = rs.getInt("NEXT_TASK_ID");; //次に表示するタスクID（連携）
					 
					Task task = new Task(taskId,userId,title,description,status,priority,schedule,startTime,target,actual,reminderTime,nextLocked,nextTask);
					taskList.add(task);
				}
			}catch(SQLException e) {
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
			try(Connection conn = DriverManager.getConnection(JDBC_URL,DB_USER,DB_PASS)){
			
				//INSERT文の準備（task_idは自動連番なので指定しなくてよい）
				String sql = "INSERT INTO TASKS()VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				PreparedStatement pStmt = conn.prepareStatement(sql);
		
				//INSERT文中の「？」に使用する値を設定してSQL文を完成
				pStmt.setInt(1, task.getTaskId());
				pStmt.setInt(2, task.getUserId());
				pStmt.setString(3, task.getTitle());
				pStmt.setString(4, task.getDescription());
				pStmt.setString(5, task.getStatus());
				pStmt.setInt(6, task.getPriority());
				pStmt.setDate(7, task.getSchedule());
				pStmt.setTime(8, task.getStartTime());
				pStmt.setInt(9, task.getTarget());
				pStmt.setInt(10, task.getActual());
				pStmt.setTime(11, task.getReminderTime());
				pStmt.setBoolean(12, task.isNextLocked());
				pStmt.setInt(13, task.getNextTask());
				
				//INSET文を実行（resultには追加された行数が代入される）
				int result = pStmt.executeUpdate();
				if (result != 1) {
					return false;
				}
			}catch(SQLException e){
				e.printStackTrace();
				return false;
			}return true;
		}
	}

