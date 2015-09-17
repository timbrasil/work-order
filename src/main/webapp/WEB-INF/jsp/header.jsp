<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <title>Centro de operações</title>

  <!-- bootstrap -->
  <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap.min.css"/>">
  <link rel="stylesheet" href="<c:url value="/assets/css/bootstrap-theme.min.css"/>">
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
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">WO - Aceitação<span class="caret"></span></a>
            <ul class="dropdown-menu">
                  <li><a href="#"><span class="glyphicon glyphicon-plus-sign"></span> Adicionar Aceitação</a></li>
	              <li><a href="#"><span class="glyphicon glyphicon-th-list"></span> Cadastrar CheckList</a></li>
				  <li role="separator" class="divider"></li>
	              <li><a href="#"><span class="glyphicon glyphicon-search"></span> Consultar Aceitação</a></li>
	              <li><a href="#"><span class="glyphicon glyphicon-ok"></span> Finalizar Aceitação</a></li>
            </ul>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
          <li><a href='<c:url value="/logout" />'><span class="glyphicon glyphicon-off"></span> Sair</a></li>
        </ul>
        
      </div>
    </div>
  </nav>

  <!-- FINAL NAV -->