package util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.HttpSession;

import dao.TasksDAO;
import model.Task;

public class TaskUtils {
	public static List<Task> getTodayTasks(HttpSession session) {
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null)
			return new ArrayList<>();

		TasksDAO dao = new TasksDAO();
		List<Task> allTasks = dao.findByUser(userId);
		if (allTasks == null)
			allTasks = new ArrayList<>();

		List<Task> todayTasks = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat stf = new SimpleDateFormat("HH:mm");
		String todayStr = sdf.format(new java.util.Date());

		for (Task task : allTasks) {
            if (task.getSchedule() == null) continue;

            // 今日のタスクかどうか
            if (!todayStr.equals(sdf.format(task.getSchedule()))) continue;

            // 表示用文字列をセット（null 安全な文字列化）
            task.setScheduleStr(sdf.format(task.getSchedule()));
            task.setStartTimeStr(task.getStartTime() != null ? stf.format(task.getStartTime()) : "-");
            task.setReminderTimeStr(task.getReminderTime() != null ? stf.format(task.getReminderTime()) : "-");

            todayTasks.add(task);
        }
		return todayTasks;
	}
}
