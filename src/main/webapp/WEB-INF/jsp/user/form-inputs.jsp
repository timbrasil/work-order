<%--@elvariable id="cpassword" type="java.lang.String"--%>
<%--@elvariable id="user" type="br.com.timbrasil.operations.models.User"--%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<input type="hidden" id="id" name="user.id" value="${user.id}">

<div class="form-group">
	<div class="col-md-12">
		<label for="name" class="control-label">Nome Completo:</label>
		<div class="templatemo-input-icon-container">
			<input type="text" class="form-control" id="name" name="user.name"
                   style="text-transform: capitalize"
				   placeholder="Digite seu nome aqui" spellcheck="false"
				   value="${user.name}" required>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-12">
		<label for="email" class="control-label">Email:</label>
		<div class="templatemo-input-icon-container">
			<input type="email" class="form-control" id="email" name="user.email"
                   style="text-transform: lowercase"
				   placeholder="Digite seu email aqui" spellcheck="false"
				   value="${user.email}" required>
		</div>
	</div>
</div>


<div class="form-group">
    <div class="col-md-12">
        <label for="register" class="control-label">Matrícula:</label>
        <div class="templatemo-input-icon-container">
            <input type="text" class="form-control" id="register" name="user.register"
                   style="text-transform: uppercase"
                   placeholder="Ex.: F8012345" spellcheck="false"
                   value="${user.register}" required>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="password" class="control-label">Senha:</label>
        <div class="templatemo-input-icon-container">
            <input type="password" class="form-control" id="password" name="user.password"
                   placeholder="Digite sua senha" spellcheck="false"
                   value="${user.password}" required>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="cpassword" class="control-label">Confirme a senha:</label>
        <div class="templatemo-input-icon-container">
            <input type="password" class="form-control" id="cpassword" name="cpassword"
                   placeholder="Digite sua senha" spellcheck="false"
                   value="${cpassword}" required>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="region" class="control-label">Selecione sua região:</label>
        <div class="templatemo-input-icon-container">
            <select name="user.region" id="region" class="form-control">
                <option value="null" selected>Selecione uma região</option>
                <%--@elvariable id="regions" type="java.util.List<br.com.timbrasil.operations.models.region>"--%>
                <c:forEach items="${regions}" var="region">
                    <option value="${region}" ${region == user.region ? 'selected' : ''}>${region.nome}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="area" class="control-label">Selecione sua area:</label>
        <div class="templatemo-input-icon-container">
            <select name="user.area" id="area" class="form-control">
                <option value="null" selected>Selecione uma area</option>
                <%--@elvariable id="areas" type="java.util.List<br.com.timbrasil.operations.models.area>"--%>
                <c:forEach items="${areas}" var="area">
                    <option value="${area}" ${area == user.area ? 'selected' : ''}>${area}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>