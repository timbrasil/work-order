<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="form-horizontal">
    <div class="col-md-12">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>Informações do Modelo do CheckList</strong>
            </div>
            <div class="panel-body">
                <div class="col-md-3">
                    <label for="CheckListModelTechnology" class="control-label">Tecnologia do CheckList:</label>
                    <select id="CheckListModelTechnology" class="form-control input-sm" name="checkListModel.technology" required>
                        <option value="null" selected>Selecione uma Tecnologia</option>
                        <c:forEach items="${technologys}" var="technology">
                            <option value="${technology}">${technology.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-md-12">
                    <label for="CheckListModelDescription" class="control-label">Descreva para qual tipo de aceitação esse modelo se destina:</label>
                    <textarea
                            id="CheckListModelDescription"
                            class="form-control text-area input-sm"
                            spellcheck="true"
                            name="checkListModel.description"
                            required></textarea>
                </div>
            </div>
        </div>
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <strong>Itens verificados no CheckList</strong>
            </div>
            <div class="panel-body">
                <div class="col-sm-12" id="placeModelItemsCheckList">

                </div>
                <div class="col-md-1 pull-right" style="margin-top: 20px;">
                    <button class="btn btn-primary" type="button" id="AddModelItemsCheckList">
                        <span class="glyphicon glyphicon-plus"></span> Add
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>

