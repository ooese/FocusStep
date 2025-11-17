package model;

import dao.TasksDAO;

public class AddTaskLogic {
		public void execute(Task task) {
			TasksDAO dao = new TasksDAO(); //DAOを利用してつぶやきを投稿
			dao.create(task);
		}
}
