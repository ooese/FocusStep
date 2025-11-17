package model;

import java.util.List;

import dao.TasksDAO;

public class GetTaskListLogic {
	public List<Task> execute(){
		TasksDAO dao = new TasksDAO();
		List<Task> mutterList = dao.findAll();
		return mutterList;
	}
}

