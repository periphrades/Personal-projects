package kevin.hawthorne.model;

public class DayTask extends Task {
	
	private boolean completed;
	
	private String note;
	

	public boolean isCompleted() {
		return completed;
	}
	
	public void setCompleted(boolean complete) {
		this.completed = complete;
	}
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
