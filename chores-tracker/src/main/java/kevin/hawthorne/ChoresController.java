package kevin.hawthorne;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import kevin.hawthorne.model.ChildDAO;
import kevin.hawthorne.model.DayTask;
import kevin.hawthorne.model.TaskDAO;
import kevin.hawthorne.model.User;
import kevin.hawthorne.model.UserDAO;


@Controller
@SessionAttributes("user")
public class ChoresController {
	
	@Autowired
	private ChildDAO childDAO;
	
	@Autowired
	private TaskDAO taskDAO;
	
	@Autowired
	private UserDAO userDAO;

	
	@RequestMapping (path = "/", method = RequestMethod.GET )
	public String displayLogin(ModelMap map) {
		
		if (map.get("user") != null) {
			return "redirect:/homepage";
		}
		
		return "loginPage";
	}
	
	@RequestMapping (path = "/login", method = RequestMethod.POST )
	public String postLoginForm(@RequestParam String name, @RequestParam String password, ModelMap map) {
		
		User user = userDAO.getUserByName(name);
		
		if (user != null) {
			if (user.getPassword().equals(password)) {
				map.addAttribute("user", user);
				return "redirect:/homepage";
			}
		}
		
		return "loginPage";
	}
	
	@RequestMapping (path = "/homepage", method = RequestMethod.GET )
	public String showHomePage(ModelMap map) {
		
		if (map.get("user") == null) {
			return "redirect:/";
		}
		
		// render different page for different user status?
		
		return "homepage";
	}
	
	
	
}
