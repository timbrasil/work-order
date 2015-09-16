<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Centro de operações</title>
<link rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/css/bootstrap-theme.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/assets/css/main.css"/>"/>
<link rel="stylesheet"
	href="<c:url value="/assets/css/login.css"/>"/>
</head>

<body class="bg-image">
	<div class="container">
		<div class="col-md-12">
			<form class="form-horizontal templatemo-login-form" role="form"
				action="<c:url value="/login/autenticate"/>" method="POST">
				<div class="row">
					<div class="col-md-12" style="margin: auto; text-align:center">
						<img id="logo" src="images/almox_logo_darkbg.png">
					</div>
				</div>
				<div class="row" style="text-align: center">
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-12">
								<label for="user.email" class="control-label">E-mail</label>
								<div class="inner-addon left-addon">
								    <i class="glyphicon glyphicon-envelope"></i>
								    <input type="email" class="form-control" id="user.email"
										placeholder="Digite seu email aqui" name="user.email" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label for="user.password" class="control-label">Senha</label>
								<div class="inner-addon left-addon">
								    <i class="glyphicon glyphicon-lock"></i>
								    <input type="password" class="form-control" 
								    	placeholder="Digite sua senha" 
										id="user.password" name="user.password"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<input type="submit" value="ENTRAR"
									class="btn btn-warning">
							</div>
						</div>
						<c:if test="${not empty errors}">
							<div class="col-md-12 alert-danger list-group-item-danger">
								<c:forEach var="error" items="${errors}">
						            ${error.category} - ${error.message} <br />
								</c:forEach>
							</div>
						</c:if>
						<div class="form-group">
							<div class="col-md-6">
								<a href="#" class="text-center btn btn-link">Esqueci a
									senha</a>
							</div>
							<div class="col-md-6">
								<a href='<c:url value="/user/form" />' class="text-center btn btn-link">Cadastre-se</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

	<script src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
	<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
</body>
</html>