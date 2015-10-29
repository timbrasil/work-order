<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
<%@include file="../header.jsp" %>

<div class="container bg-white thumbnail box">
    <div class="col-md-12">
        <div class="form-horizontal">
            <div class="form-group text-center bg-primary">
                <div class="col-xs-2 bg-white">
                    <a href="<c:url value="/workOrder/edit/${workOrder.id}"></c:url>"><button class="btn btn-default col-xs-12" <c:if test="${workOrder.lastLogAcception!=null}">disabled</c:if>><span class="glyphicon glyphicon-edit"></span> EDITAR</button></a>
                </div>
                <div class="col-md-8">
                    <h3 style="margin-top: 3px; margin-bottom: 0;">Work Order</h3>
                </div>
                <c:if test="${workOrder.lastLogStatus.status=='WORKING'}">
                    <div class="col-xs-2 bg-white">
                        <button class="btn btn-warning col-xs-12"><span class="glyphicon glyphicon-eye-open"></span> TRABALHANDO</button>
                    </div>
                </c:if>
                <c:if test="${workOrder.lastLogStatus.status=='ACCEPTED'}">
                    <div class="col-xs-2 bg-white">
                        <button class="btn btn-success col-xs-12"><span class="glyphicon glyphicon-eye-open"></span> FINALIZADA</button>
                    </div>
                </c:if>
                <c:if test="${workOrder.lastLogStatus.status=='REJECTED'}">
                    <div class="col-xs-2 bg-white">
                        <button class="btn btn-danger col-xs-12"><span class="glyphicon glyphicon-eye-open"></span> REJEITADA</button>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>TicketId:</b> ${workOrder.ticketId}
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>Tecnologia:</b> ${workOrder.technology}
        </div>
        <div class="col-md-3 thumbnail-mini">
            <b>Tipo de WO:</b>
            <c:forEach var="workOrderType" varStatus="workOrderLoop" items="${workOrder.typeWorkOrders}">
                ${workOrderType.description}<c:if test="${!workOrderLoop.last}">, </c:if>
            </c:forEach>
        </div>
        <div class="col-md-3 thumbnail-mini">
            <b>Data de atribuição:</b> <fmt:formatDate value="${workOrder.atribution.time}" type="date" />
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>Site:</b> ${workOrder.site.name}
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>DDD:</b> ${workOrder.site.address.city.ddd}
        </div>
        <div class="col-md-3 thumbnail-mini">
            <b>Cidade:</b> ${workOrder.site.address.city.name}
        </div>
        <div class="col-md-7 thumbnail-mini">
            <b>Endereço:</b> ${workOrder.site.address.street}
        </div>
        <div class="col-md-6" style="margin-top: 20px">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title text-center"><b>Histórico da Work Order</b></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th class="text-center">Data de Execução</th>
                                    <th class="text-center">Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${workOrder.logStatus}" var="logStatus">
                                    <tr>
                                        <td class="text-center"><fmt:formatDate value="${logStatus.execution.time}" type="date" /></td>
                                        <td class="text-center">${logStatus.status}</td>
                                    </tr>                                    
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    //Tabela de logStatus - TODO
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../footer.jsp" %>