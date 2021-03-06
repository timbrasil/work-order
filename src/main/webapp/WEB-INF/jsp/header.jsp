<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>Centro de operações</title>
    <link rel="icon" href="<c:url value="/assets/images/favicon.ico"/>">

    <!-- bootstrap -->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
    <!--<link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-theme.min.css"/>">-->
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-switch.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-datepicker.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/assets/css/main.css"/> ">
</head>

<body class="bg-image">

<!-- INICIO NAV (alterar pra include)-->

<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#menu" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<c:url value="/"/> ">Centro de Operações TIM</a>
        </div>

        <div class="collapse navbar-collapse" id="menu">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Aceitação<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="<c:url value="/workOrder/add/form"/> "><span class="glyphicon glyphicon-plus-sign"></span> Cadastro de WorkOrder</a></li>
                        <li><a href="<c:url value="/workOrder"/> "><span class="glyphicon glyphicon-list-alt"></span> Visualizar WorkOrders</a></li>
                        <li role="separator" class="divider"></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href='<c:url value="/logout" />'><span class="glyphicon glyphicon-off"></span> Sair</a></li>
            </ul>

        </div>
    </div>
</nav>

<%@include file="modalAlert.jsp" %>

<script src="<c:url value="/assets/js/jquery-2.1.4.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap-switch.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap-datepicker.min.js"/>"></script>
<script src="<c:url value="/assets/js/bootstrap-datepicker.pt-BR.min.js"/>"></script>
<script src="<c:url value="/assets/js/main.js"/>"></script>


<!-- FINAL NAV -->