<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp" %>

<div class="container bg-white thumbnail box">
  <div class="col-md-12">

    <form id="checkListAdd" class="form-horizontal" role="form"
          action="javascript:void(0)" autocomplete="on">
      <div class="form-group text-center bg-primary">
        <div class="col-md-12">
          <h3>Cadastro de CheckList</h3>
          <p>Preencha o CheckList abaixo</p>
        </div>
      </div>
      <%@include file="addCheckListForm-inputs.jsp" %>
      <div class="form-group">
        <div class="col-xs-12">
          <input type="submit" id="cadastrar" value="Cadastrar" class="btn btn-success col-xs-12">
        </div>
      </div>

      <c:forEach var="error" items="${errors}">
        ${error.category} - ${error.message}<br />
      </c:forEach>
    </form>
  </div>
</div>
<%@include file="../footer.jsp" %>