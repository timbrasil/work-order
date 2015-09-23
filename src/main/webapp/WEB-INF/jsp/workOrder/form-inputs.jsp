<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="form-horizontal">
    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>Informações da WO</strong>
            </div>
            <div class="panel-body">
                <div class="col-md-12">
                    <label for="ticketId" class="control-label">TicketID:</label>
                    <input type="number" class="form-control input-sm" id="ticketId"
                           spellcheck="false" name="workOrder.ticketId"
                           value="${workOrder.ticketId}" required>
                </div>
                <div class="col-md-12">
                    <label for="atributionDate" class="control-label">Data de atribuição:</label>
                    <input type="date" class="form-control input-sm" id="atributionDate"
                           spellcheck="false" name="logStatus.atribution"
                           value="${logStatus.atribution}" required>
                </div>
                <div class="col-md-12">
                    <label class="control-label">Tipo de aceitação:</label>
                    <div class="checkbox">
                        <c:forEach items="${typeWorkOrders}" var="type">
                            <div class="checkbox checkbox-inline">
                                <label for="${type.id}">
                                    <input type="checkbox" id="${type.id}" name="typeWorkOrder.id"  value="${type.id}"> ${type.description}
                                </label>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-6">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>Informações do Site</strong>
            </div>
            <div class="panel-body">
                <div class="col-md-12">
                    <label for="site" class="control-label">Nome do Site:</label>
                    <input type="text" class="form-control input-sm" id="site"
                           style="text-transform: uppercase"
                           spellcheck="false" name="site.name"
                           size="12" maxlength="12"
                           value="${site.name}" required>
                </div>
                <div class="col-md-12">
                    <label for="technology" class="control-label">Tecnologia:</label>
                    <select id="technology" class="form-control input-sm" name="site.technology" required>
                        <option value="null" selected>Selecione uma Tecnologia</option>
                        <c:forEach items="${technologys}" var="technology">
                            <option value="${technology}">${technology.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-12">
                    <label for="city" class="control-label">Cidade:</label>
                    <select id="city" class="form-control input-sm" name="city.id" required>
                        <option value="null" selected>Selecione uma cidade</option>
                        <c:forEach items="${cities}" var="city">
                            <option value="${city.id}">${city.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-12">
                    <label for="address" class="control-label">Endereço:</label>
                    <input type="text" class="form-control input-sm" id="address"
                           style="text-transform: capitalize"
                           spellcheck="false" name="address.street"
                           value="${address.street}" required>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $("#site").on("keyup",function(){
        $.ajax({
            url: '<c:url value="/site/name"/>',
            type: "GET",
            data: {'site.name':$(this).val()},
            datatype: "JSON",
            success: function (response) {
                var technology = $("#technology");
                var city = $("#city");
                var address = $("#address");
                if(response.status){
                    technology.val(response.data.technology);
                    technology.prop("disabled",true);
                    city.val(response.data.address.city.id);
                    city.prop("disabled",true);
                    address.val(response.data.address.street);
                    address.prop("disabled",true);
                }
                else{
                    technology.prop("disabled",false);
                    technology.val("null");
                    city.prop("disabled",false);
                    city.val("null");
                    address.prop("disabled",false);
                    address.val("");
                }

            }
        });
    })
</script>