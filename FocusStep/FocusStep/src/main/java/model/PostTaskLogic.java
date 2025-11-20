package model;

import dao.TasksDAO;

public class PostTaskLogic {
		public void execute(Task task) {
			TasksDAO dao = new TasksDAO(); //DAOを利用してタスクを追加
			dao.create(task);
		}
}