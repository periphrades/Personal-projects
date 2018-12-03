package kevin.hawthorne.model;

import java.util.List;

public interface TaskDAO {

	public List<Task> getAllTasks();

	public int addTask(String taskName);

	public void deleteTask(int taskId);

	public Task getTaskById(int taskId);
	

	
}
