package kevin.hawthorne.model;

import java.util.List;

public interface TaskDAO {

	public List<Task> getAllTasks();

	void addTask(String taskName);

	void deleteTask(int taskId);
	

	
}
