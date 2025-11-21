package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

public class Task implements Serializable {
	private int taskId; //ID
	private int userId; //ユーザーID
	private String title; //タスク名
	private String description; //詳細
	private String status; //タスクの状態
	private int priority; //優先順位
	private Date schedule; //実行予定日
	private Time startTime; //実行開始時間
	private int target; //目標時間（分）
	private int actual; //実質時間（分）
	private Time reminderTime; //リマインダー通知時間
	//		private boolean nextLocked; //次のタスクをロックするか
	//		private int nextTask; //次に表示するタスクID（連携）

	//SELECT用　taskId有り
	public Task(int taskId, int userId, String title, String description, String status, int priority,
			Date schedule, Time startTime, int target, int actual, Time reminderTime) {
		//			boolean nextLocked, int nextTask

		this.taskId = taskId;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.schedule = schedule;
		this.startTime = startTime;
		this.target = target;
		this.actual = actual;
		this.reminderTime = reminderTime;
		//			this.nextLocked = nextLocked;
		//			this.nextTask = nextTask;
	}

	//Date・Timeフォーマット修正
	private String scheduleStr;
	private String startTimeStr;
	private String reminderTimeStr;

	public String getScheduleStr() {
		return scheduleStr;
	}

	public void setScheduleStr(String scheduleStr) {
		this.scheduleStr = scheduleStr;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getReminderTimeStr() {
		return reminderTimeStr;
	}

	public void setReminderTimeStr(String reminderTimeStr) {
		this.reminderTimeStr = reminderTimeStr;
	}

	
	//INSERT用　taskID無し
	public Task(int userId, String title, String description, String status, int priority, Date schedule,
			Time startTime, int target, int actual, Time reminderTime) {

		this.userId = userId;
		this.title = title;
		this.description = description;
		this.status = status;
		this.priority = priority;
		this.schedule = schedule;
		this.startTime = startTime;
		this.target = target;
		this.actual = actual;
		this.reminderTime = reminderTime;
	}

	public int getTaskId() {
		return taskId;
	}

	public int getUserId() {
		return userId;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getStatus() {
		return status;
	}

	public int getPriority() {
		return priority;
	}

	public Date getSchedule() {
		return schedule;
	}

	public Time getStartTime() {
		return startTime;
	}

	public int getTarget() {
		return target;
	}

	public int getActual() {
		return actual;
	}

	public Time getReminderTime() {
		return reminderTime;
	}

	//setTaskId を追加 → create() 後に採番 ID をセットできる
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	//		public boolean isNextLocked() {
	//			return nextLocked;
	//		}
	//
	//		public int getNextTask() {
	//			return nextTask;
	//		}

}