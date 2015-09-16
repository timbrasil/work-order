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
        <form class="form-horizontal templatemo-contact-form" role="form"
              action="<c:url value="/user/save"/>" method="POST" autocomplete="on">
            <div class="form-group" style="text-align: center">
                <div class="col-md-12">
                    <h1 class="margin-bottom-15">Cadastro</h1>
                    <p>Preencha os dados abaixo para se cadastrar no sistema.</p>
                </div>
            </div>
            <%@include file="form-inputs.jsp" %>
            <div class="form-group">
                <div class="col-md-12">
                    <input type="submit" value="Cadastrar" class="btn btn-success pull-right col-md-12">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <a class="btn btn-link" href="<c:url value="/login"/>">Voltar para página de login</a>
                </div>
            </div>
        </form>
    </div>
</div>

<script src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
</body>
</html>