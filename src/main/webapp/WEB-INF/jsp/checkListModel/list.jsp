<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>
<div class="container bg-white thumbnail box">
  <div class="col-md-12">

    <form id="checkListModel" class="form-horizontal" role="form"
          action="javascript:void(0)" autocomplete="on">
      <div class="form-group text-center bg-primary">
        <div class="col-md-12">
          <h3>Selecione o modelo de CheckList</h3>
          <p>Escolha o modelo de CheckList Referente a aceitação que você deseja fazer.</p>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-condensed">
          <thead>
          <tr>
            <th class="col-md-9">Descrição</th>
            <th class="col-md-2">Tecnologia</th>
            <th class="col-md-1"></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="checkListModel" items="${checkListModels}">
            <tr>
              <td>${checkListModel.description}</td>
              <td>${checkListModel.technology}</td>
              <td><button
                      type="button"
                      class="btn btn-sm btn-primary"
                      onclick='window.location="<c:url value="/checkList/form/models/${checkListModel.id}"/>"'>Selecionar</button></td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
      </div>
      <c:forEach var="error" items="${errors}">
        ${error.category} - ${error.message}<br />
      </c:forEach>
    </form>
  </div>
</div>

<%@include file="../footer.jsp" %>
