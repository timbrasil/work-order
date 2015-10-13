<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../header.jsp" %>
<div class="container bg-white thumbnail box">
    <div class="col-md-12">

        <form id="checkListModel" class="form-horizontal" role="form"
              action="javascript:void(0)" autocomplete="on">
            <div class="form-group text-center bg-primary">
                <div class="col-md-12">
                    <h3>Work Orders</h3>
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
                        <th class="col-md-3">Status</th>
                        <th class="col-md-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="workOrder" items="${workOrders}">
                        <tr>
                            <td>${workOrder.ticketId}</td>
                            <td>${workOrder.site.name}</td>
                            <td>${workOrder.technology}</td>
                            <td>
                                <c:forEach var="workOrderType" varStatus="workOrderLoop" items="${workOrder.typeWorkOrders}">
                                    ${workOrderType.description}<c:if test="${!workOrderLoop.last}">, </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                ${workOrder.lastLogStatus.status}
                            </td>
                            <td>
                                <c:if test="${workOrder.lastLogStatus.status=='WORKING'}">
                                    <button
                                            type="button"
                                            class="btn btn-sm btn-success"
                                            onclick='window.location="<c:url value="/workOrder/${workOrder.id}/checkList/add"/>"'><span class="glyphicon glyphicon-plus"></span> CheckList</button>
                                </c:if>
                                <c:if test="${workOrder.lastLogStatus.status=='REJECTED'}">
                                    Botão em desenvolvimento
                                </c:if>
                                <c:if test="${workOrder.lastLogStatus.status=='ACCEPTED'}">
                                    Botão em desenvolvimento
                                </c:if>
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
