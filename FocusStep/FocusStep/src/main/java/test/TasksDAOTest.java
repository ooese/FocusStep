package test;

import java.sql.Date;
import java.util.List;

import dao.TasksDAO;
import model.Task;
	
public class TasksDAOTest {
		public static void main(String[] args) {
		testFindTaskOK(); //タスクが取得できた場合のテスト
		testFindTaskNG(); //タスクが取得できない場合のテスト
		}
		public static void testFindTaskOK() {
			TasksDAO dao = new TasksDAO();
			List<Task> result = dao.findAll();
			Task task = result.get(0);

	        if (task.getTaskId() == 1 &&
	            task.getUserId() == 1 &&
	            task.getTitle().equals("朝のメールチェック") &&
	            task.getDescription().equals("受信メールの確認と返信") &&
	            task.getStatus().equals("completed") &&
	            task.getPriority() == 1 &&
	            task.getSchedule().equals(Date.valueOf("2025-11-13")) &&
	            "08:30:00".equals(task.getStartTime().toString()) && 
	            task.getTarget() == 30 &&
	            task.getActual() == 25 &&
	            "08:15:00".equals(task.getReminderTime().toString())) {

	            System.out.println("testFindTaskOK:成功しました");
	        } else {
	            System.out.println("testFindTaskOK:失敗しました");
	        }
	    }
		
		
		public static void testFindTaskNG() {
			TasksDAO dao = new TasksDAO();
			List<Task> result = dao.findAll();
			Task task = result.get(0);
			
			if (task.getTaskId() == 0) {
				System.out.println("testFindTaskNG:成功しました（データ0件）");
			} else {
				System.out.println("testFindTaskNG:失敗しました（データが存在する）");
			}
		}
	}