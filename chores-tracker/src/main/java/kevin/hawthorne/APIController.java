package kevin.hawthorne;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kevin.hawthorne.model.Child;
import kevin.hawthorne.model.ChildDAO;
import kevin.hawthorne.model.Task;
import kevin.hawthorne.model.TaskDAO;

@CrossOrigin(origins = "*")
@RestController
public class APIController {
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired ChildDAO childDAO;

	@RequestMapping (path = "/tasks", method = RequestMethod.GET)
	public List<Task> getAllTasks() {
		return taskDAO.getAllTasks();
	}
	
	@RequestMapping (path = "/tasks", method = RequestMethod.POST)
	public ResponseEntity<Void> addTask(@RequestParam String taskName) {
		
		int taskId = taskDAO.addTask(taskName);
		
		return ResponseEntity.created(URI.create("/tasks/" + taskId)).build();
	}
	
	@RequestMapping (path = "/tasks/{taskid}", method = RequestMethod.GET)
	public Task getTask(int taskId) {
		return taskDAO.getTaskById(taskId);
	}
	
	
	@RequestMapping (path = "/tasks/{taskid}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(int taskId) {
		
		Task task = taskDAO.getTaskById(taskId);
		
		if (task != null) {
			taskDAO.deleteTask(taskId);
			return ResponseEntity.ok(task);
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@RequestMapping (path = "/children", method = RequestMethod.GET)
	public List<Child> getAllChildren() {
		
		return childDAO.getAllChildren();
	}
	
	
	
	
	
	
}
