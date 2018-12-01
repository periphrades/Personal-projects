package kevin.hawthorne.model;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
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
	public List<DayTask> getChildDayTasks(Child user) {
		List<DayTask> dayTasks = new ArrayList<DayTask>();
		String sql = "SELECT user_day_task.taskid, task_name, last_date_loaded, completed, note "
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
		String sql = "SELECT user_week_task.taskid, task_name, last_monday_loaded, times_per_week, "
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
		LocalDate today = LocalDate.now();
		String sql = "INSERT INTO user_day_task (userid, taskid, completed, last_date_loaded, note) "
				+ "VALUES (?, ?, false, ?, ?)";
		
		template.update(sql, user.getUserid(), task.getTaskId(), today, note);
	}
	
	@Override
	public void addChildWeekTask(Task task, Child user, String note, int timesPerWeek) {
		LocalDate mostRecentMonday = LocalDate.now().with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
		String sql = "INSERT INTO user_week_task (userid, taskid, times_per_week, last_monday_loaded, note) "
				+ "VALUES (?, ?, ?, ?, ?)";
		
		template.update(sql, user.getUserid(), task.getTaskId(), timesPerWeek, mostRecentMonday, note);
	}
	
// need to remove day & week tasks from Child

	private DayTask mapRowSetToDayTask(SqlRowSet result, Child user) {
		DayTask dayTask = new DayTask();
		
		dayTask.setTaskId(result.getInt("taskid"));
		dayTask.setTaskName(result.getString("task_name"));
		dayTask.setTaskName(result.getString("note"));
		
		LocalDate today = LocalDate.now();
		if ( (result.getDate("last_date_loaded").toLocalDate()).isBefore(today) ) {
			dayTask.setCompleted(false);
			
			// update last_date_loaded on database to today
			String sql = "UPDATE user_day_task SET last_date_loaded = " + today + "WHERE userid = ? AND taskid = ?";
			template.update(sql, user.getUserid(), dayTask.getTaskId());
			
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
		weekTask.setTaskName(result.getString("note"));
		
		LocalDate mostRecentMonday = LocalDate.now().with(WeekFields.of(Locale.US).dayOfWeek(), 2L);
		
		if ( (result.getDate("last_monday_loaded").toLocalDate()).isBefore(mostRecentMonday) ) {
			weekTask.setTimesDoneThisWeek(0);
			
			// update last_date_loaded on database to today
			String sql = "UPDATE user_week_task SET last_monday_loaded = " + mostRecentMonday 
					+ "WHERE userid = ? AND taskid = ?";
					template.update(sql, user.getUserid(), weekTask.getTaskId());
					
		} else {
			weekTask.setTimesDoneThisWeek(result.getInt("times_done_in_week"));
		}
		
		return weekTask;
	}
	
	
}
