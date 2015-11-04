<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%--@elvariable id="workOrder" type="br.com.timbrasil.operations.models.WorkOrder"--%>
<%--@elvariable id="itemsCheckList" type="br.com.timbrasil.operations.models.ItemCheckList"--%>

<div class="form-horizontal">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>Informações da WO</strong>
            </div>
            <div class="panel-body">
                <div>
                    <input type="hidden" class="form-control input-sm" id="workOrderId"
                           name="workOrder.id"
                           value="${workOrder.id}">
                </div>
                <div class="col-md-2">
                    <label for="ticketId" class="control-label">TicketID:</label>
                    <input type="text" class="form-control input-sm" id="ticketId"
                           spellcheck="false" name="workOrder.ticketId"
                           value="${workOrder.ticketId}" disabled>
                </div>
                <div class="col-md-2">
                    <label for="site" class="control-label">Site:</label>
                    <input type="text" class="form-control input-sm" id="site"
                           spellcheck="false" name="workOrder.site.name"
                           value="${workOrder.site.name}" disabled>
                </div>
                <div class="col-md-2">
                    <label for="cidade" class="control-label">Cidade:</label>
                    <input type="text" class="form-control input-sm" id="cidade"
                           spellcheck="false" name="workOrder.site.address.city.name"
                           value="${workOrder.site.address.city.name}" disabled>
                </div>
                <div class="col-md-2">
                    <label for="technology" class="control-label">Tecnologia:</label>
                    <input type="text" class="form-control input-sm" id="technology"
                           spellcheck="false" name="workOrder.technology"
                           value="${workOrder.technology}"  disabled>
                </div>
                <div class="col-md-2">
                    <label for="atributionDate" class="control-label">Data de Atribuição:</label>
                    <input type="text" class="form-control input-sm" id="atributionDate"
                           spellcheck="false" name="workOrder.atribution"
                           value="<fmt:formatDate value="${workOrder.atribution.time}" type="date" />"
                           disabled>
                    <input type="hidden"
                           name="workOrder.atribution"
                           value="<fmt:formatDate value="${workOrder.atribution.time}" type="date" />">
                </div>
                <div class="col-md-12"></div>
                <div class="col-md-2">
                    <label for="executionDate" class="control-label">Data de Execução:</label>
                    <input type="text" class="form-control input-sm date" id="executionDate"
                           spellcheck="false" name="logStatus.execution"
                           value="<fmt:formatDate value="${workOrder.lastLogStatusWithCheckList.execution.time}" type="date" />"
                           required>
                </div>
                <div class="col-md-2">
                    <label for="radioSamplingTrue" class="control-label">Amostragem:</label>
                    <form id="bootstrapSwitch_sampling">
                        <input type="checkbox" id="radioSamplingTrue" <c:if test="${workOrder.lastLogStatusWithCheckList.checkList.sampling}">checked</c:if> name="checkList.sampling">
                    </form>
                    <script>
                        $("[name='checkList.sampling']").bootstrapSwitch({
                            size:'small',
                            onText: 'SIM',
                            offText: 'NÃO',
                            onColor: 'success',
                            offColor: 'danger'
                        });
                    </script>
                </div>
                <select style="visibility: hidden" disabled class="form-control input-sm" id="statusWorkOrder" name="logStatus.status" required title="Operação">
                    <option value="null">Selecione uma opção</option>
                    <%--@elvariable id="statusWorkOrder" type="br.com.timbrasil.operations.models.StatusWorkOrder"--%>
                    <c:forEach var="status" items="${statusWorkOrder}">
                        <c:if test="${status!='CREATE'&&status!='REATRIBUTION'}">
                            <option value="${status}">${status.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
    </div>
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>CheckList</strong>
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th class="col-sm-1">Nº</th>
                            <th class="col-sm-5 text-center">Descrição do Item</th>
                            <th class="col-sm-2">
                                <%--@elvariable id="answersItemCheckList" type="java.util.List<br.com.timbrasil.operations.models.answeritemchecklist>"--%>
                                <c:forEach var="answerItemCheckList" items="${answersItemCheckList}">
                                    <div class="col-sm-4 text-center">${answerItemCheckList}</div>
                                </c:forEach>
                            </th>
                            <th class="col-sm-4 text-center">Justificativa</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach var="itemCheckList" varStatus="itemsCheckListLoop" items="${itemsCheckList}">
                            <tr>
                                <td>${itemCheckList.dirId}</td>
                                <td>${itemCheckList.description}<input type="hidden" name="checkList.answers[${itemCheckList.id}].itemCheckList.id" value="${itemCheckList.id}"></td>
                                <td>
                                    <c:forEach var="answerItemCheckList" items="${answersItemCheckList}">
                                        <div class="col-sm-4">
                                            <input
                                                    type="radio"
                                                    class="form-control"
                                                    name="checkList.answers[${itemCheckList.id}].answer"
                                                    value="${answerItemCheckList}" required
                                                    <c:if test="${workOrder.lastLogStatusWithCheckList.checkList.getAnswerByItemCheckList(itemCheckList).answer!=null}">
                                                        <c:if test="${answerItemCheckList == workOrder.lastLogStatusWithCheckList.checkList.getAnswerByItemCheckList(itemCheckList).answer}">checked</c:if>
                                                    </c:if>
                                                    <c:if test="${workOrder.lastLogStatusWithCheckList.checkList.getAnswerByItemCheckList(itemCheckList).answer==null}">
                                                        <c:if test="${answerItemCheckList=='NOK'}">checked</c:if>
                                                    </c:if>
                                                    title="${answerItemCheckList}"/></div>
                                    </c:forEach>
                                </td>
                                <td>
                                    <label for="justificativa${itemCheckList.id}" class="hidden">Justificativa</label>
                                    <textarea
                                            id="justificativa${itemCheckList.id}"
                                            class="form-control text-area"
                                            name="checkList.answers[${itemCheckList.id}].justification"
                                            style="height: 17px"
                                            <c:if test="${workOrder.lastLogStatusWithCheckList.checkList.getAnswerByItemCheckList(itemCheckList).answer!=null}">
                                                <c:if test="${workOrder.lastLogStatusWithCheckList.checkList.getAnswerByItemCheckList(itemCheckList).answer!='NOK'}">disabled</c:if>
                                            </c:if>
                                            required>${workOrder.lastLogStatusWithCheckList.checkList.getAnswerByItemCheckList(itemCheckList).justification}</textarea></td>
                            </tr>
                            <script>
                                $("input[name='checkList.answers[${itemCheckList.id}].answer']").on("change",function(){
                                    if($(this).val()!='NOK'){
                                        $("#justificativa${itemCheckList.id}").prop('disabled',true);
                                    }
                                    else{
                                        $("#justificativa${itemCheckList.id}").prop('disabled',false);
                                    }
                                });
                            </script>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
