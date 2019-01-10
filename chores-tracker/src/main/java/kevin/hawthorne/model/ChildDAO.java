package kevin.hawthorne.model;

import java.util.List;

public interface ChildDAO {

	public List<DayTask> getChildDayTasks(Child user);

	public List<WeekTask> getChildWeekTasks(Child user);

	public void addChildWeekTask(Task task, Child user, String note, int timesPerWeek);

	public void addChildDayTask(Task task, Child user, String note);

	public List<Child> getAllChildren();

	public void updateLastDateLoaded(Child child);
	
	
}
