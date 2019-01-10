package kevin.hawthorne.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcChildDAO implements ChildDAO{

	private JdbcTemplate template;
	
	@Autowired
	public JdbcChildDAO(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public void updateLastDateLoaded(Child child) {
		
		LocalDate today = LocalDate.now();
		
		String sql = "UPDATE users SET last_date_loaded = ? WHERE userid = ?";
		
		template.update(sql, today, child.getUserid());
		
	}

	@Override
	public List<Child> getAllChildren() {
		
		List<Child> allChildren = new ArrayList<Child>();
		
		String sql = "SELECT userid, user_name FROM users WHERE status = 'child'";
		
		SqlRowSet results = template.queryForRowSet(sql);
		
		while (results.next()) {
			Child child = mapRowSetToChild(results);
			allChildren.add(child);
		}
		
		return allChildren;
	}
	

	@Override
	public List<DayTask> getChildDayTasks(Child user) {
		List<DayTask> dayTasks = new ArrayList<DayTask>();
		String sql = "SELECT user_day_task.taskid, task_name, completed, note "
				+ "FROM user_day_task JOIN tasks ON user_day_task.taskid = tasks.taskid WHERE userid = ?";
		
		SqlRowSet result = template.queryForRowSet(sql, user.getUserid());
		
		while (result.next()) {
			DayTask dayTask = mapRowSetToDayTask(result, user);
			dayTasks.add(dayTask);
		}
		
		return dayTasks;
	}
	
	@Override
	public List<WeekTask> getChildWeekTasks(Child user) {
		List<WeekTask> weekTasks = new ArrayList<WeekTask>();
		String sql = "SELECT user_week_task.taskid, task_name, times_per_week, "
				+ "times_done_in_week, note FROM user_week_task JOIN tasks ON "
				+ "user_week_task.taskid = tasks.taskid WHERE userid = ?";
		
		SqlRowSet result = template.queryForRowSet(sql, user.getUserid());
		
		while (result.next()) {
			WeekTask weekTask = mapRowSetToWeekTask(result, user);
			weekTasks.add(weekTask);
		}
		
		return weekTasks;
	}
	
	@Override
	public void addChildDayTask(Task task, Child user, String note) {

		String sql = "INSERT INTO user_day_task (userid, taskid, completed, note) "
				+ "VALUES (?, ?, false, ?)";
		
		template.update(sql, user.getUserid(), task.getTaskId(), note);
	}
	
	@Override
	public void addChildWeekTask(Task task, Child user, String note, int timesPerWeek) {
		
//		LocalDate mostRecentMonday = LocalDate.now().with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
		
		String sql = "INSERT INTO user_week_task (userid, taskid, times_per_week, note) "
				+ "VALUES (?, ?, ?, ?)";
		
		template.update(sql, user.getUserid(), task.getTaskId(), timesPerWeek, note);
	}
	

	private DayTask mapRowSetToDayTask(SqlRowSet result, Child user) {
		DayTask dayTask = new DayTask();
		
		dayTask.setTaskId(result.getInt("taskid"));
		dayTask.setTaskName(result.getString("task_name"));
		dayTask.setNote(result.getString("note"));
		
		LocalDate today = LocalDate.now();
		if ( (user.getLastDateLoaded()).isBefore(today) ) {
			dayTask.setCompleted(false);
		} else {
			dayTask.setCompleted(result.getBoolean("completed"));
		}
		
		return dayTask;
	}
	
	private WeekTask mapRowSetToWeekTask(SqlRowSet result, Child user) {
		WeekTask weekTask = new WeekTask();
		
		weekTask.setTaskId(result.getInt("taskid"));
		
		weekTask.setTaskName(result.getString("task_name"));

		
		weekTask.setTimesPerWeek(result.getInt("times_per_week"));
		weekTask.setNote(result.getString("note"));
		

		
		LocalDate mostRecentMonday = LocalDate.now().with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
		LocalDate mondayOfLastLoaded = user.getLastDateLoaded().with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
		
		if ( mondayOfLastLoaded.isBefore(mostRecentMonday) ) {
			weekTask.setTimesDoneThisWeek(0);
					
		} else {
			weekTask.setTimesDoneThisWeek(result.getInt("times_done_in_week"));
		}
		

		
		return weekTask;
	}
	
	
	private Child mapRowSetToChild(SqlRowSet results) {
		Child child = new Child();
		
		child.setUserid(results.getInt("userid"));
		child.setName(results.getString("user_name"));
		
		LocalDate date = results.getDate("last_date_loaded").toLocalDate();
		if (date != null) {
			
			child.setLastDateLoaded(date);
		}
		
		
		List<DayTask> dayTasks = getChildDayTasks(child);
		child.setDayTasks(dayTasks);
		
		List<WeekTask> weekTasks = getChildWeekTasks(child);
		child.setWeekTasks(weekTasks);
		
		return child;
	}
	
	
	
}
