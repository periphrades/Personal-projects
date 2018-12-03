package kevin.hawthorne;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import kevin.hawthorne.model.JdbcTaskDAO;
import kevin.hawthorne.model.Task;
import kevin.hawthorne.model.TaskDAO;

public class JDBCTaskDAOIntegrationTest extends DAOIntegrationTest {

	private DataSource dataSource = super.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);
	private TaskDAO taskDAO;
	
	@Before
	public void setup() {
		taskDAO = new JdbcTaskDAO(dataSource);
	}
	
	@Test
	public void verifyNewTask() {
		
		String taskName = "testing123alphbeta";
		int taskId = taskDAO.addTask(taskName);
		System.out.println(taskId);
		
		List<Task> allTasks = taskDAO.getAllTasks();
		
		boolean contains = false;
		
		for (Task task : allTasks) {
			if ( task.getTaskName().equals(taskName) ) {
				contains = true;
			}
		}
		
		Assert.assertTrue(contains);
		
	}
	
	
	@Test
	public void verifyDeleteTask() {
		
		String sql = "SELECT nextval('tasks_taskid_seq')";
		SqlRowSet result = template.queryForRowSet(sql);
		result.next();
		int taskId = result.getInt("nextval");
		String taskName = "testing123alphbeta";
		
		sql = "INSERT INTO tasks (taskid, task_name) VALUES (?, ?)";
		
		template.update(sql, taskId, taskName);
		
		taskDAO.deleteTask(taskId);
		
		List<Task> allTasks = taskDAO.getAllTasks();
		
		boolean contains = false;
		
		for (Task task : allTasks) {
			if ( task.getTaskName().equals(taskName) ) {
				contains = true;
			}
		}
		
		Assert.assertFalse(contains);

	}
	
}
