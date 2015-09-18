<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<div class="form-group">
    <div class="col-md-3">
        <label for="ticketId" class="control-label">TicketID:</label>
        <input type="number" class="form-control" id="ticketId"
               placeholder="TicketID" spellcheck="false"
               value="${workOrder.ticketId}" required>
    </div>
    <div class="col-md-3">
        <label for="atributionDate" class="control-label">Data de atribuição do Ticket:</label>
        <input type="date" class="form-control" id="atributionDate"
               placeholder="dd/mm/aaaa" spellcheck="false"
               value="${logStatus.atribution}" required>
    </div>
    <div class="col-md-12"></div>
    <div class="col-md-3">
        <label for="site" class="control-label">Site:</label>
        <input type="text" class="form-control" id="site"
               style="text-transform: uppercase"
               placeholder="Digite o Site aqui" spellcheck="false"
               size="12" maxlength="12"
               value="${site.name}" required>
    </div>
    <div class="col-md-3">
        <label for="technology" class="control-label">Selecione a Tecnologia:</label>
        <select id="technology" class="form-control" required>
            <option value="null" selected>Selecione uma Tecnologia</option>
            <c:forEach items="${technologys}" var="technology">
                <option value="${technology}">${technology.nome}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-12"></div>
    <div class="col-md-3">
        <label for="city" class="control-label">Cidade:</label>
        <select id="city" class="form-control" required>
            <option value="null" selected>Selecione uma cidade</option>
            <c:forEach items="${cities}" var="city">
                <option value="${city}">${city.name}</option>
            </c:forEach>
        </select>
    </div>
    <div class="col-md-6">
        <label for="address" class="control-label">Endereço:</label>
        <input type="text" class="form-control" id="address"
               style="text-transform: capitalize"
               placeholder="Digite o endereço do site aqui" spellcheck="false"
               value="${address.street}" required>
    </div>
    <div class="col-md-12">
        <label class="control-label">Tipo de aceitação:</label>
        <div class="checkbox">
            <c:forEach items="${types}" var="type">
                <div class="checkbox checkbox-inline">
                    <label for="${type.name}">
                        <input type="checkbox" id="${type.name}" name="type"  value="${type.name}"> ${type.description}
                    </label>
                </div>
            </c:forEach>
        </div>
    </div>
</div>