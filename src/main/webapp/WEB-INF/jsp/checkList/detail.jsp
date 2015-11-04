<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
<%--@elvariable id="logStatus" type="br.com.timbrasil.operations.models.LogStatus"--%>
<%@include file="../header.jsp" %>

<div class="container bg-white thumbnail box">
    <div class="col-md-12">
        <div class="form-horizontal">
            <div class="form-group text-center bg-primary">
                <div class="col-md-2 bg-white">
                    <a href="<c:url value="/workOrder/${workOrder.id}"/>"><button class="btn btn-default col-xs-12"><span class="glyphicon glyphicon-eye-open"></span> Work Order</button></a>
                </div>
                <div class="col-md-8">
                    <a style="padding: 0 0 0 0; margin-top: 5px;" class="btn btn-primary" href="<c:url value="/workOrder"/>"><h3 style="margin-top: 0; margin-bottom: 0;"><b>Work Order</b></h3></a>
                </div>
                <c:if test="${logStatus.status!='ACCEPTED'&&logStatus.status!='REJECTED'}">
                    <div class="col-md-2 bg-white">
                        <button class="btn btn-warning col-xs-12" disabled><span class="glyphicon glyphicon-eye-open"></span> TRABALHANDO</button>
                    </div>
                </c:if>
                <c:if test="${logStatus.status=='ACCEPTED'}">
                    <div class="col-md-2 bg-white">
                        <button class="btn btn-success col-xs-12" disabled><span class="glyphicon glyphicon-eye-open"></span> FINALIZADA</button>
                    </div>
                </c:if>
                <c:if test="${logStatus.status=='REJECTED'}">
                    <div class="col-md-2 bg-white">
                        <button class="btn btn-danger col-xs-12" disabled><span class="glyphicon glyphicon-eye-open"></span> REJEITADA</button>
                    </div>
                </c:if>
            </div>
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>TicketId:</b> ${workOrder.ticketId}
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>Tecnologia:</b>
            <c:forEach items="${workOrder.technologies}" var="technology" varStatus="loop">
                ${technology}<c:if test="${not loop.last}">, </c:if>
            </c:forEach>
        </div>
        <div class="col-md-3 thumbnail-mini">
            <b>Tipo de WO:</b>
            <c:forEach var="workOrderType" varStatus="workOrderLoop" items="${workOrder.typeWorkOrders}">
                ${workOrderType.description}<c:if test="${!workOrderLoop.last}">, </c:if>
            </c:forEach>
        </div>
        <div class="col-md-3 thumbnail-mini">
            <b>Data de execução:</b> <fmt:formatDate value="${logStatus.execution.time}" type="date" />
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>Site:</b> ${workOrder.site.name}
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>DDD:</b> ${workOrder.site.address.city.ddd}
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>Cidade:</b> ${workOrder.site.address.city.name}
        </div>
        <div class="col-md-6 thumbnail-mini">
            <b>Endereço:</b> ${workOrder.site.address.street}
        </div>
        <div class="col-md-2 thumbnail-mini">
            <b>Amostragem:</b> <c:if test="${logStatus.checkList.sampling}">Sim</c:if><c:if test="${not logStatus.checkList.sampling}">Não</c:if>
        </div>
        <div class="col-md-12">
            <div class="table-responsive">
                <table class="table table-condensed table-striped">
                    <thead>
                    <tr>
                        <th class="col-sm-1 text-center">Nº</th>
                        <th class="col-sm-5 text-center">Descrição do Item</th>
                        <th class="col-sm-2 text-center">Resposta</th>
                        <th class="col-sm-4 text-center">Justificativa</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${logStatus.checkList.answers}" var="answer">
                        <tr class="<c:if test="${answer.answer=='OK'}">text-success</c:if> <c:if test="${answer.answer=='NOK'}">text-danger</c:if>">
                            <td class="col-sm-1 text-center">${answer.itemCheckList.dirId}</td>
                            <td class="col-sm-5 text-center">${answer.itemCheckList.description}</td>
                            <td class="col-sm-2 text-center">${answer.answer}</td>
                            <td class="col-sm-4 text-center">${answer.justification}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="../footer.jsp" %>