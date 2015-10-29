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
                        <th></th>
                        <th class="col-md-2">TicketId</th>
                        <th class="col-md-3">Site</th>
                        <th class="col-md-3">Tecnologia da Aceitação</th>
                        <th class="col-md-3">Tipo de WO</th>
                        <th class="col-md-3">Status</th>
                        <th class="col-md-2"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <%--@elvariable id="workOrders" type="java.util.List<br.com.timbrasil.operations.models.WorkOrder>"--%>
                    <c:forEach var="workOrder" items="${workOrders}">
                        <tr>
                            <td><a href="<c:url value="/workOrder/${workOrder.id}"/>" ><span class="glyphicon glyphicon-eye-open"></span></a></td>
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
                                    <button
                                            type="button"
                                            class="btn btn-sm btn-success"
                                            onclick='reatributionDate(${workOrder.id})'><span class="glyphicon glyphicon-plus"></span> Reatribuir</button>
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
                    '<c:url value="/workOrder"/>',
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
