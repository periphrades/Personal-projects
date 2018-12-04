package kevin.hawthorne.model;

import java.time.LocalDate;
import java.util.List;

public class Child extends User {
	
	private List<DayTask> dayTasks;
	
	private List<WeekTask> weekTasks;
	
	private LocalDate lastDateLoaded;
	

	public Child() {
		super("child");
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


	public LocalDate getLastDateLoaded() {
		return lastDateLoaded;
	}


	public void setLastDateLoaded(LocalDate lastDateLoaded) {
		this.lastDateLoaded = lastDateLoaded;
	}
	
	
	
}
