<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>

<div class="container">
	<form method="post">
		<h1>Login</h1>
		<input type="text" placeholder="Email" />
		<input type="password" placeholder="Senha"/>
		<button type="submit">Login</button>
	</form>
</div>

<%@include file="../footer.jsp" %>