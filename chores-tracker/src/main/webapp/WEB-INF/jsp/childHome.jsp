<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:import url="/WEB-INF/jsp/header.jsp" />

<h1>Child Page</h1>

<h2>Day Tasks</h2>
<c:forEach var = "dayTask" items="${user.dayTasks}">

	<p>${dayTask.taskName} - completed: ${dayTask.completed}</p>

</c:forEach>

<h2>Week Tasks</h2>

<c:forEach var = "weekTask" items="${user.weekTasks}">

	<p>${weekTask.taskName} - completed: ${weekTask.completed}</p>

</c:forEach>

<c:import url="/WEB-INF/jsp/footer.jsp" />