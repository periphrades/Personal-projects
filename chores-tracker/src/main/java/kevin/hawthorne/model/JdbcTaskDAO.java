package kevin.hawthorne.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcTaskDAO implements TaskDAO {

	private JdbcTemplate template;
	
	@Autowired
	public JdbcTaskDAO(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List<Task> getAllTasks() {
		List<Task> allTasks = new ArrayList<Task>();
		
		String sql = "SELECT taskid, task_name FROM tasks ORDER BY task_name ASC";

		SqlRowSet results = template.queryForRowSet(sql);
		
		while(results.next()) {
			Task task = mapRowSetToTask(results);
			allTasks.add(task);
		}
		
		return allTasks;
	}
	
	@Override
	public int addTask(String taskName) {
		String sql = "INSERT INTO tasks (task_name) VALUES (?) RETURNING taskid";
		
		SqlRowSet result = template.queryForRowSet(sql, taskName);
		result.next();
		int taskId = result.getInt("taskid");
		return taskId;
	}
	
	@Override
	public void deleteTask(int taskId) {
		String sql = "DELETE FROM tasks WHERE taskid = ?";
		
		template.update(sql, taskId);
	}
	
	@Override
	public Task getTaskById(int taskId) {
		Task task = null;
		
		String sql = "SELECT taskid, task_name FROM tasks WHERE taskid = ?";
		
		SqlRowSet result = template.queryForRowSet(sql, taskId);
		
		while(result.next()) {
			task = mapRowSetToTask(result);
		}
		
		return task;
	}
	

	private Task mapRowSetToTask(SqlRowSet results) {
		
		Task task = new Task();
		task.setTaskId(results.getInt("taskid"));
		task.setTaskName(results.getString("task_name"));
		
		return task;
	}
	

}
