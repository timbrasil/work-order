<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="form-horizontal">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>Informações da WO</strong>
            </div>
            <div class="panel-body">
                <div class="col-md-2">
                    <label for="ticketId" class="control-label">TicketID:</label>
                    <input type="text" class="form-control input-sm" id="ticketId"
                           spellcheck="false" name="workOrder.ticketId"
                           value="${workOrder.ticketId}" disabled>
                </div>
                <div class="col-md-2">
                    <label for="atributionDate" class="control-label">Data de Atribuição:</label>
                    <input type="text" class="form-control input-sm" id="atributionDate"
                           spellcheck="false" name="logStatus.atribution"
                           value="<fmt:formatDate value="${workOrder.lastLogStatus.atribution.time}" type="date" />"
                            disabled>
                </div>
                <div class="col-md-2">
                    <label for="technology" class="control-label">Tecnologia:</label>
                    <input type="text" class="form-control input-sm" id="technology"
                           spellcheck="false" name="workOrder.technology"
                           value="${workOrder.technology}"  disabled>
                </div>
                <div class="col-md-2">
                    <label for="executionDate" class="control-label">Data de Execução:</label>
                    <input type="text" class="form-control input-sm date" id="executionDate"
                           spellcheck="false" name="workOrder.lastLogStatus.execution"
                           value="<fmt:formatDate value="${workOrder.lastLogStatus.execution.time}" type="date" />"
                           required>
                </div>
                <div class="col-md-2">
                    <label for="statusWorkOrder" class="control-label">Status:</label>
                    <select class="form-control" id="statusWorkOrder" name="workOrder.lastLogStatus.status">
                        <c:forEach var="acepption" items="${statusAcception}">
                            <option value="${acepption}">${acepption.nome}</option>
                        </c:forEach>
                    </select>
                </div>
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
                                <c:forEach var="answerItemCheckList" items="${answersItemCheckList}">
                                    <div class="col-sm-4 text-center">${answerItemCheckList}</div>
                                </c:forEach>
                            </th>
                            <th class="col-sm-4 text-center">Justificativa</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="itemCheckList" varStatus="itemsCheckListLoop" items="${checkListModel.itemsCheckList}">
                            <tr>
                                <td>${itemCheckList.dirId}</td>
                                <td>${itemCheckList.description}<input type="hidden" name="answerItemCheckList[${itemCheckList.id}].itemCheckList.id" value="${itemCheckList.id}"></td>
                                <td>
                                    <c:forEach var="answerItemCheckList" items="${answersItemCheckList}">
                                        <div class="col-sm-4"><input type="radio" class="form-control" name="answerItemChecklist[${itemCheckList.id}].answersItemChecklist" value="${answerItemCheckList}"></div>
                                    </c:forEach>
                                </td>
                                <td><textarea class="form-control text-area" name="answerItemChecklist[${itemCheckList.id}].justification" style="height: 17px"></textarea></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
