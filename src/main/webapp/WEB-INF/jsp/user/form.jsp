<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<div class="container">
		<h2>Cadastro</h2>
		
		<form role="form" method="POST" action="<c:url value = "/user/save"/>">
			<%@include file="form-inputs.jsp" %>
			<input type="submit" value="Cadastrar" class="btn"/>
		</form>
	</div>