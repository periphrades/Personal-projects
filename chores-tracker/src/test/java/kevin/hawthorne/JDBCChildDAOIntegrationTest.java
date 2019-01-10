package kevin.hawthorne;

import java.time.LocalDate;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import kevin.hawthorne.model.Child;
import kevin.hawthorne.model.ChildDAO;
import kevin.hawthorne.model.JdbcChildDAO;
import kevin.hawthorne.model.JdbcUserDAO;
import kevin.hawthorne.model.UserDAO;
import kevin.hawthorne.model.WeekTask;

public class JDBCChildDAOIntegrationTest extends DAOIntegrationTest {
	
	private DataSource dataSource = super.getDataSource();
	private JdbcTemplate template = new JdbcTemplate(dataSource);
	private ChildDAO childDAO;
	private Child child;
	private UserDAO userDAO;

	
	@Before
	public void setup() {
		childDAO = new JdbcChildDAO(dataSource);
		userDAO = new JdbcUserDAO(dataSource);
		
		String sql = "INSERT INTO users (user_name, status, last_date_loaded) VALUES ('testUserLorem879ZetaBlue', "
				+ "'child', '2018-12-28')";
		
		template.update(sql);
		
		child = (Child) userDAO.getUserByName("testUserLorem879ZetaBlue");

	}
	
	@Test
	public void verifyGetWeekTasks() {
		
		String sql = "INSERT INTO tasks (task_name) VALUES ('one two Ipse testTask texT') RETURNING taskid";
		
		SqlRowSet result = template.queryForRowSet(sql);
		result.next();
		int taskId = result.getInt("taskid");
		
		sql = "INSERT INTO user_week_task (userid, taskid, times_per_week, times_done_in_week) VALUES (?, ?, 3, 1)";
		
		template.update(sql, child.getUserid(), taskId);
		
		List<WeekTask> weekTasks = childDAO.getChildWeekTasks(child);
		
		Assert.assertTrue(weekTasks.size() == 1);
		
	}
	
	
	
	
}
