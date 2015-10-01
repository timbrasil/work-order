<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>
<div class="container bg-white thumbnail box">
  <div class="col-md-12">

    <form id="checkListModel" class="form-horizontal" role="form"
          action="javascript:void(0)" autocomplete="on">
      <div class="form-group text-center bg-primary">
        <div class="col-md-12">
          <h3>Selecione uma WO abaixo para inserir o CheckList</h3>
          <p>Abaixo estão listadas as WOs com status WORKING.</p>
        </div>
      </div>

      <div class="table-responsive">
        <table class="table table-striped table-condensed">
          <thead>
          <tr>
            <th class="col-md-2">TicketId</th>
            <th class="col-md-3">Site</th>
            <th class="col-md-3">Tecnologia da Aceitação</th>
            <th class="col-md-3">Tipo de WO</th>
            <th class="col-md-1"></th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="workOrder" items="${workOrders}">
            <tr>
              <td>${workOrder.ticketId}</td>
              <td>${workOrder.site.name}</td>
              <td>${workOrder.technology}</td>
              <td>
                <c:forEach var="workOrderType" items="${workOrder.typeWorkOrders}">
                  ${workOrderType.description},
                </c:forEach>
              </td>
              <td>
                <button
                      type="button"
                      class="btn btn-sm btn-primary"
                      onclick='window.location="<c:url value="/checkList/form/workOrder/${workOrder.id}/model/${checkListModel.id}"/>"'>Selecionar</button>
              </td>
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
