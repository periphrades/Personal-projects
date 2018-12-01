<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>Chores</title>
	</head>
	
	<body>
	
		<h2>Chores</h2>
		
		<c:url var = "postLoginURL" value="/login" />
		<form action="${postLoginURL}" method = "POST" autocomplete="off" >
			<label for="name">Name: </label>
			<input type="text" name = "name" />
			<label for="password">Password: </label>
			<input type="text" name = "password" />
			<button type="submit">Log in</button>
		
		</form>
	
	</body>

</html>