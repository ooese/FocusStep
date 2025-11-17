package model;

import dao.TasksDAO;

public class PostTaskLogic {
		public void execute(Task task) {
			TasksDAO dao = new TasksDAO(); //DAOを利用してつぶやきを投稿
			dao.create(task);
		}
}