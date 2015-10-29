<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="../header.jsp" %>

<div class="container bg-white thumbnail box">
    <div class="col-md-12">

        <form id="checkListAdd" class="form-horizontal" role="form"
              action="javascript:void(0)" autocomplete="on">
            <div class="form-group text-center bg-primary">
                <div class="col-md-12">
                    <h3 style="margin-top: 3px; margin-bottom: 0">Cadastro de CheckList</h3>
                </div>
            </div>
            <%@include file="form-inputs.jsp" %>
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

<div class="modal fade" id="confirmationModal">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">Confirmação dos dados</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-12 text-center">
                        <h3><b>Status atual: </b><span class="text-success" id="successMessageModal">WorkOrder Aceita</span></h3>
                    </div>
                    <div class="col-md-12">
                        <div class="col-md-4 thumbnail-mini text-center">
                            <h1>OK</h1>
                            <div class="alert-success"><h3 id="countOK"></h3></div>
                        </div>
                        <div class="col-md-4 thumbnail-mini text-center">
                            <h1>NOK</h1>
                            <div class="alert-danger"><h3 id="countNOK"></h3></div>
                        </div>
                        <div class="col-md-4 thumbnail-mini text-center">
                            <h1>NA</h1>
                            <div class="alert-warning"><h3 id="countNA"></h3></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancelar</button>
                <button type="button" id="salvaButtonModal" class="btn btn-primary">Salvar Alteraçõess</button>
                <button type="button" id="aceitaButtonModal" class="btn btn-success pull-right">Aceitar</button>
                <button type="button" id="rejeitadaButtonModal" class="btn btn-danger pull-right">Rejeitar</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script src="<c:url value="/assets/js/workOrder.js"/>"></script>
<script>
    $("#cadastrar").on("click",function(){
        validate.checkAll('checkListAdd');

        if(!validate.isValidate()){
            validate.showModelAlert();
            return false;
        }

        var countOK = 0;
        var countNOK = 0;
        var countNA = 0;
        $('input[type=radio]:checked', '#checkListAdd').each(function(){
            switch ($(this).val()){
                case 'OK':
                    countOK++;
                    break;
                case 'NOK':
                    countNOK++;
                    break;
                case 'NA':
                    countNA++;
                    break;
            }
        });
        $("#countOK").html(countOK);
        $("#countNOK").html(countNOK);
        $("#countNA").html(countNA);
        var modal = $("#confirmationModal").modal();
        modal.show();
        if(countNOK>0){
            $("#successMessageModal").html("WorkOrder Rejeitada").removeClass('text-success').addClass('text-danger');
            $("#aceitaButtonModal").hide();
            $("#rejeitadaButtonModal").show();
        }
        else{
            $("#successMessageModal").html("WorkOrder Aceita").removeClass('text-danger').addClass('text-success');
            $("#aceitaButtonModal").show();
            $("#rejeitadaButtonModal").hide();
        }
    });

    $("#aceitaButtonModal").on("click",function(){
        $("#statusWorkOrder").val('ACCEPTED');
        sendData();
    });

    $("#rejeitadaButtonModal").on("click",function(){
        $("#statusWorkOrder").val('REJECTED');
        sendData();
    });

    $("#salvaButtonModal").on("click",function(){
        $("#statusWorkOrder").val('WORKING');
        sendData();
    });

    function sendData(){
        $("#confirmationModal").modal('hide');
        workOrder.saveCheckList(
                '<c:url value="/workOrder/checkList/save"/>',
                '<c:url value="/workOrder/${workOrder.id}"/>',
                'POST',
                'checkListAdd'
        );
    }
</script>
<%@include file="../footer.jsp" %>