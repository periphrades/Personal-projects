<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>

	<head>
		<title>Chores Home</title>
	</head>
	
	<body>
	
		<h2>Chores</h2>
		
		<p>User: ${user.name}</p>
		<p>Status: ${user.status}</p>
 		<c:if test="${user.status == 'child'}">
			<p>Last Date Loaded: ${user.lastDateLoaded}</p>
 		</c:if>