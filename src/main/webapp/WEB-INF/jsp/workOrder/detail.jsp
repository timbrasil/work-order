<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
<%@include file="../header.jsp" %>

<div class="container bg-white thumbnail box">
    <div class="col-md-12">
        <div class="form-horizontal">
            <div class="form-group text-center bg-primary">
                <div class="col-md-2 bg-white">
                    <a href="<c:url value="/workOrder/edit/${workOrder.id}"></c:url>"><button class="btn btn-default col-xs-12" <c:if test="${workOrder.lastLogStatus.status!='CREATE'}">disabled</c:if>><span class="glyphicon glyphicon-edit"></span> EDITAR</button></a>
                </div>
                <div class="col-md-8">
                    <a style="padding: 0 0 0 0; margin-top: 5px;" class="btn btn-primary" href="<c:url value="/workOrder"></c:url>"><h3 style="margin-top: 0; margin-bottom: 0;"><b>Work Order</b></h3></a>
                </div>
                <c:if test="${workOrder.lastLogStatus.status!='ACCEPTED'&&workOrder.lastLogStatus.status!='REJECTED'}">
                    <div class="col-md-2 bg-white">
                        <button class="btn btn-warning col-xs-12"
                                <c:if test="${workOrder.lastLogStatus.status=='CREATE'}">disabled</c:if>
                                onclick="window.location.href = '<c:url value="/workOrder/${workOrder.id}/checkList" />' "
                                ><span class="glyphicon glyphicon-eye-open"></span> TRABALHANDO</button>
                    </div>
                </c:if>
                <c:if test="${workOrder.lastLogStatus.status=='ACCEPTED'}">
                    <div class="col-md-2 bg-white">
                        <button class="btn btn-success col-xs-12"
                                onclick="window.location.href = '<c:url value="/workOrder/${workOrder.id}/checkList" />' "
                                ><span class="glyphicon glyphicon-eye-open"></span> FINALIZADA</button>
                    </div>
                </c:if>
                <c:if test="${workOrder.lastLogStatus.status=='REJECTED'}">
                    <div class="col-md-2 bg-white">
                        <button class="btn btn-danger col-xs-12"
                                onclick="window.location.href = '<c:url value="/workOrder/${workOrder.id}/checkList" />' "
                                ><span class="glyphicon glyphicon-eye-open"></span> REJEITADA</button>
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
        <div class="col-md-2 thumbnail-mini">
            <b>Cidade:</b> ${workOrder.site.address.city.name}
        </div>
        <div class="col-md-8 thumbnail-mini">
            <b>Endereço:</b> ${workOrder.site.address.street}
        </div>
        <div class="col-md-12" style="margin-top: 20px">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title text-center"><b>Histórico da Work Order</b></h3>
                </div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th class="col-md-3 text-center">Data de Execução</th>
                                    <th class="col-md-3 text-center">Status</th>
                                    <th class="col-md-3 text-center">Amostragem</th>
                                    <th class="col-md-3 text-center">CheckList</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${workOrder.logStatus}" var="logStatus" varStatus="loopStatus">
                                    <tr>
                                        <td class="col-md-3 text-center"><fmt:formatDate value="${logStatus.execution.time}" type="date" /></td>
                                        <td class="col-md-3 text-center">${logStatus.status.name}</td>
                                        <td class="col-md-3 text-center">
                                            <c:if test="${logStatus.checkList!=null}">
                                                <c:if test="${logStatus.checkList.sampling}">Sim</c:if>
                                                <c:if test="${not logStatus.checkList.sampling}">Não</c:if>
                                            </c:if>
                                            <c:if test="${logStatus.checkList==null}">
                                                -
                                            </c:if>
                                        </td>
                                        <td class="col-md-3">
                                            <c:if test="${logStatus.checkList!=null}">
                                                <button
                                                        style="margin-left: 43%"
                                                        type="button"
                                                        class="btn btn-primary btn-sm"
                                                        onclick="window.location.href = '<c:url value="/workOrder/${workOrder.id}/checkList/${loopStatus.index+1}"/>'">
                                                    <span class="glyphicon glyphicon-eye-open"></span>
                                                </button>
                                            </c:if>
                                        </td>
                                    </tr>                                    
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <div class="pull-right col-md-2">
                        <c:if test="${workOrder.lastLogStatus.status=='CREATE'||workOrder.lastLogStatus.status=='REATRIBUTION'}">
                            <button
                                    type="button"
                                    class="btn btn-sm btn-default"
                                    onclick='window.location="<c:url value="/workOrder/${workOrder.id}/checkList/add"/>"'><span class="glyphicon glyphicon-plus"></span> Adicionar CheckList</button>
                        </c:if>
                        <c:if test="${workOrder.lastLogStatus.status=='WORKING'}">
                            <button
                                    type="button"
                                    class="btn btn-sm btn-default"
                                    onclick='window.location="<c:url value="/workOrder/${workOrder.id}/checkList/add"/>"'><span class="glyphicon glyphicon-edit"></span> Editar CheckList</button>
                        </c:if>
                        <c:if test="${workOrder.lastLogStatus.status=='REJECTED'}">
                            <button
                                    type="button"
                                    class="btn btn-sm btn-default"
                                    onclick='reatributionDate(${workOrder.id})'><span class="glyphicon glyphicon-retweet"></span> Reatribuir WorkOrder</button>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="reatributionModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Reatribuição de WorkOrder</h4>
            </div>
            <div class="modal-body">
                <form id="atributionDateForm">
                    <label for="atributionDate" class="control-label">Data de Reatribuição da WO:</label>
                    <input type="text" class="form-control input-sm date" id="atributionDate"
                           spellcheck="false" name="logStatus.execution">
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-success" id="btnAtributionDateConfirm">Confirmar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script>
    function reatributionDate(id){
        var modal = $("#reatributionModal").modal();
        modal.show();
        var confirmButton = $("#btnAtributionDateConfirm");
        confirmButton.unbind("click");
        confirmButton.on("click",function(){
            sendReatribution(
                    '<c:url value="/workOrder"/>/'+id+'/reatribution',
                    '<c:url value="/workOrder"/>/'+id,
                    'POST',
                    'atributionDateForm');
        })
    }
    function sendReatribution(url,redirect,type,formId){
        validate.checkAll(formId);

        if(!validate.isValidate()){
            $("#reatributionModal").modal('hide');
            validate.showModelAlert();
            return false;
        }

        var serialized = $("#"+formId).serialize();

        $.ajax({
            url: url,
            type: type,
            async: false,
            datatype: "JSON",
            data: serialized,
            success: function (response) {
                if(response.status==true){
                    $("#reatributionModal").modal('hide');
                    show.success(
                            'WorkOrder reatribuida',
                            'A WorkOrder '+response.status.ticketId+' foi reatribuida com sucesso!',
                            window.location = redirect
                    );
                }
                else{
                    $("#reatributionModal").modal('hide');
                    show.error('Occoreu um erro ao tentar executar a operação',response.error);
                }
            }
        });
    }
</script>

<%@include file="../footer.jsp" %>