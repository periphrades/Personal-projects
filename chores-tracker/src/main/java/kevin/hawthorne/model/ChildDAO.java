package kevin.hawthorne.model;

import java.util.List;

public interface ChildDAO {

	public List<DayTask> getChildDayTasks(Child user);

	public List<WeekTask> getChildWeekTasks(Child user);

	void addChildWeekTask(Task task, Child user, String note, int timesPerWeek);

	void addChildDayTask(Task task, Child user, String note);
	
	
}
