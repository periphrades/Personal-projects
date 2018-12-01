<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>Chores Home</title>
	</head>
	
	<body>
	
		<h2>Chores</h2>
		
		<strong>Task name: </strong> <c:out value = "${testTask.taskName}" />
	
	</body>

</html>