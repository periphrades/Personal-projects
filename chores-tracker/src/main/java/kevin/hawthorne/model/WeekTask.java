package kevin.hawthorne.model;

public class WeekTask extends Task {
	
	private int timesPerWeek;
	
	private int timesDoneThisWeek;

	private String note;
	
	public boolean isCompleted() {
		return (timesDoneThisWeek >= timesPerWeek);
	}

	public int getTimesPerWeek() {
		return timesPerWeek;
	}

	public void setTimesPerWeek(int timesPerWeek) {
		this.timesPerWeek = timesPerWeek;
	}

	public int getTimesDoneThisWeek() {
		return timesDoneThisWeek;
	}

	public void setTimesDoneThisWeek(int timesDoneThisWeek) {
		this.timesDoneThisWeek = timesDoneThisWeek;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
