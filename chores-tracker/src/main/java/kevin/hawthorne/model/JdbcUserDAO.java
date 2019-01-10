package kevin.hawthorne.model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDAO implements UserDAO {

	private JdbcTemplate template;
	
	@Autowired
	public JdbcUserDAO(DataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}
	
	@Override
	public User getUserByName(String name) {
		
		User user = null;
		
		String sql = "SELECT * FROM users WHERE user_name = ?";
		
		SqlRowSet result = template.queryForRowSet(sql, name);
		
		while (result.next()) {
			user = mapRowSetToUser(result);
		}
		
		return user;
	}


	private User mapRowSetToUser(SqlRowSet result) {
		User user = null;
		if ( result.getString("status").equals("parent") ) {
			user = new User("parent");
		} else if ( result.getString("status").equals("child") ) {
			user = new Child();
		}
		
		if (user == null) {
			return user;
		}
		
		user.setUserid( result.getInt("userid") );
		user.setName( result.getString("user_name") );
		user.setPassword( result.getString("passwd") );
		
		if (user.getStatus().equals("child")) {
			Child child = (Child) user;
			
			LocalDate date = result.getDate("last_date_loaded").toLocalDate();
			if (date == null) {
				child.setLastDateLoaded(LocalDate.now());
			} else {
				child.setLastDateLoaded(date);
			}
			
		}
		
		return user;
	}
	
}
