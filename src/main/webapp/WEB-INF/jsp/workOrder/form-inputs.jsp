<%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
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
                    <input type="hidden" id="workOrderId" name="workOrder.id"
                           value="${workOrder.id}">
                </div>
                <div class="col-md-12">
                    <label for="atributionDate" class="control-label">Data de atribuição:</label>
                    <input type="text" class="form-control input-sm date" id="atributionDate"
                           spellcheck="false" name="workOrder.atribution"
                           value="<fmt:formatDate value="${workOrder.atribution.time}" type="date" />"
                           <c:if test="${workOrder.atribution!=null}">disabled</c:if>
                           required>
                </div>
                <div>
                    <div class="col-md-12">
                        <label class="control-label">Tecnologia sendo implantada:</label>
                    </div>
                    <div id="tecnologyArea">
                        <c:if test="${workOrder.technologies!=null}">
                            <c:forEach items="${workOrder.technologies}" var="technology" varStatus="loop">
                                <div class="col-md-12 technologyParent" style="margin-bottom: 10px">
                                    <div class="input-group">
                                        <label style="display: none;">Tecnologia da Work Order</label>
                                        <select title="technology" class="form-control input-sm tecnologySelect" name="workOrder.technologies[${loop.count}]" required>
                                            <option value="null" selected>Selecione uma Tecnologia</option>
                                                <%--@elvariable id="technologys" type="java.util.List<br.com.timbrasil.operations.models.Technology>"--%>
                                                <%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
                                            <c:forEach items="${technologys}" var="technology_param">
                                                <option value="${technology_param}" <c:if test="${technology_param==technology}">selected</c:if> >${technology_param.nome}</option>
                                            </c:forEach>
                                        </select>
                                        <div class="input-group-btn input-sm" style="padding: 0 0 0 0">
                                            <button type="button" class="btn btn-danger technologyDelButton" style="height: 31px; width: 31px; padding: 2px 0 0 0"><span style="font-size: 16pt" class="glyphicon glyphicon-remove"></span></button>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                    <div class="col-md-12" style="margin-top: 5px;">
                        <div class="pull-right">
                            <button type="button" class="btn btn-primary btn-sm" onclick="addTechnology()"><span class="glyphicon glyphicon-plus"></span> ADICIONAR TECNOLOGIA</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-12">
                    <label class="control-label">Tipo de aceitação:</label>
                    <div class="checkbox">
                        <%--@elvariable id="typeWorkOrders" type="java.util.List<br.com.timbrasil.operations.models.TypeWorkOrder>"--%>
                        <c:forEach items="${typeWorkOrders}" var="type" varStatus="loop">
                            <div class="checkbox checkbox-inline">
                                <label for="${type.id}">
                                    <input 
                                            type="checkbox" 
                                            id="${type.id}" 
                                            name="typeWorkOrder[${loop.count}].id"
                                            value="${type.id}"
                                            <c:if test="${workOrder.hasTypeWorkOrder(type)}">checked</c:if> /> ${type.description}
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
                           size="12" maxlength="12" autocomplete="off"
                           value="${workOrder.site.name}" required>
                </div>
                <div class="col-md-12">
                    <label for="SiteTechnology" class="control-label">Tecnologia do Site:</label>
                    <select id="SiteTechnology" class="form-control input-sm" name="site.technology" <c:if test="${workOrder.site != null}">disabled</c:if> required>
                        <option value="null" selected>Selecione uma Tecnologia</option>
                        <%--@elvariable id="technologys" type="java.util.List<br.com.timbrasil.operations.models.Technology>"--%>
                        <c:forEach items="${technologys}" var="technology">
                            <option value="${technology}" <c:if test="${workOrder.site.technology==technology}">selected</c:if> >${technology.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-12">
                    <label for="city" class="control-label">Cidade:</label>
                    <select id="city" class="form-control input-sm" name="city.id" <c:if test="${workOrder.site != null}">disabled</c:if> required>
                        <option value="null" selected>Selecione uma cidade</option>
                        <%--@elvariable id="cities" type="java.util.List<br.com.timbrasil.operations.models.City>"--%>
                        <c:forEach items="${cities}" var="city">
                            <option value="${city.id}" <c:if test="${workOrder.site.address.city==city}" >selected</c:if> >${city.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-12">
                    <label for="address" class="control-label">Endereço:</label>
                    <input type="text" class="form-control input-sm" id="address"
                           style="text-transform: capitalize"
                           spellcheck="false" name="address.street"
                           value="${workOrder.site.address.street}"
                           <c:if test="${workOrder.site != null}">disabled</c:if>
                           required>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var site = $("#site");
    site.on("keyup",function(){
        searchSite($(this));
    });
    site.on("change",function(){
        searchSite($(this));
    });
    function searchSite(inputElement){
        var technology = $("#SiteTechnology");
        var city = $("#city");
        var address = $("#address");
        if(inputElement.val().length==0){
            technology.prop("disabled",false);
            technology.val("null");
            city.prop("disabled",false);
            city.val("null");
            address.prop("disabled",false);
            address.val("");
            return 0;
        }
        $.ajax({
            url: '<c:url value="/site/name"/>',
            type: "GET",
            data: {'site.name':inputElement.val()},
            datatype: "JSON",
            success: function (response) {
                if(response.status){
                    /** @namespace response.data.technology */
                    /** @namespace response.data.address */
                    /** @namespace response.data.address.city */
                    /** @namespace response.data.address.street */
                    technology.val(response.data.technology);
                    technology.prop("disabled",true);
                    city.val(response.data.address.city.id);
                    city.prop("disabled",true);
                    address.val(response.data.address.street);
                    address.prop("disabled",true);
                }
                else{
                    technology.prop("disabled",false);
                    city.prop("disabled",false);
                    address.prop("disabled",false);
                }

            }
        });
    }
</script>
