<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>
	<div class="container">
		<h2>Cadastro</h2>
		
		<form method="POST" action="<c:url value = "/user/save"/>">
			<%@include file="form-inputs.jsp" %>
			<button type="submit" class="btn"></button>
		</form>
	</div>
<%@include file="../footer.jsp" %>