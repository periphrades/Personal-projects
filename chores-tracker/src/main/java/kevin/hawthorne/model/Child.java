package kevin.hawthorne.model;

import java.util.List;

public class Child extends User {
	
	private List<DayTask> dayTasks;
	
	private List<WeekTask> weekTasks;
	

	public Child(String status) {
		super(status);
	}
	

	public List<DayTask> getDayTasks() {
		return dayTasks;
	}

	public void setDayTasks(List<DayTask> dayTasks) {
		this.dayTasks = dayTasks;
	}

	public List<WeekTask> getWeekTasks() {
		return weekTasks;
	}

	public void setWeekTasks(List<WeekTask> weekTasks) {
		this.weekTasks = weekTasks;
	}
	
	
	
}
