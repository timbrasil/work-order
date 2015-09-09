<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centro de operações</title>
<link rel="stylesheet"
	href="<c:url value='/assets/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/assets/css/bootstrap-theme.min.css'/>">
</head>

<body>

	<div class="container">
		<form method="post">
			<h1>Login</h1>
			<input type="text" placeholder="Email" /> <input type="password"
				placeholder="Senha" />
			<button type="submit">Login</button>
		</form>
	</div>
	
	<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
	<script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
</body>
</html>