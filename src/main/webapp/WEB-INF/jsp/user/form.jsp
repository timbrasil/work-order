<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Centro de operações</title>
    <link rel="icon" href="<c:url value="/assets/images/favicon.ico"/>" >
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
              action="javascript:void(0)" autocomplete="on">
            <div class="form-group" style="text-align: center">
                <div class="col-md-12">
                    <h1 class="margin-bottom-15">Cadastro</h1>
                    <p>Preencha os dados abaixo para se cadastrar no sistema.</p>
                </div>
            </div>
            <%@include file="form-inputs.jsp" %>
            <div class="form-group">
                <div class="col-xs-12">
                    <input type="submit" id="cadastrar" value="Cadastrar" class="btn btn-success col-xs-12">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-12">
                    <a class="btn btn-link" href="<c:url value="/login"/>">Voltar para página de login</a>
                </div>
            </div>

            <%--@elvariable id="errors" type="java.util.List"--%>
            <c:forEach var="error" items="${errors}">
                ${error.category} - ${error.message}<br />
            </c:forEach>
        </form>
    </div>
</div>

<%@include file="../modalAlert.jsp" %>

<script src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/js/user.js"/>"></script>

<script>
    $("#cadastrar").on("click",function(){
        user.save(
                '<c:url value="/user/save"/>',
                $("#name").val(),
                $("#email").val(),
                $("#register").val(),
                $("#password").val(),
                $("#cpassword").val(),
                $("#region").val(),
                $("#area").val(),
                '<c:url value="/login"/>',
                'POST');
    })
</script>
</body>
</html>