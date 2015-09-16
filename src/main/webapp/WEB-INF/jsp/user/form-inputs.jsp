<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="form-group">
	<div class="col-md-12">
		<label for="user.name" class="control-label">Nome Completo:</label>
		<div class="templatemo-input-icon-container">
			<input type="text" class="form-control" id="user.name" name="user.name"
				   placeholder="Digite seu nome aqui" spellcheck="false"
				   value="${user.name}" required>
		</div>
	</div>
</div>

<div class="form-group">
	<div class="col-md-12">
		<label for="user.email" class="control-label">Email:</label>
		<div class="templatemo-input-icon-container">
			<input type="email" class="form-control" id="user.email" name="user.email"
				   placeholder="Digite seu email aqui" spellcheck="false"
				   value="${user.email}" required>
		</div>
	</div>
</div>


<div class="form-group">
    <div class="col-md-12">
        <label for="user.register" class="control-label">Matrícula:</label>
        <div class="templatemo-input-icon-container">
            <input type="text" class="form-control" id="user.register" name="user.register"
                   placeholder="Ex.: F8012345" spellcheck="false"
                   value="${user.register}" required>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="user.password" class="control-label">Senha:</label>
        <div class="templatemo-input-icon-container">
            <input type="password" class="form-control" id="user.password" name="user.password"
                   placeholder="Digite sua senha" spellcheck="false"
                   value="${user.password}" required>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="user.cpassword" class="control-label">Confirme a senha:</label>
        <div class="templatemo-input-icon-container">
            <input type="password" class="form-control" id="user.cpassword" name="user.cpassword"
                   placeholder="Digite sua senha" spellcheck="false"
                   value="${user.cpassword}" required>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="user.region" class="control-label">Selecione sua região:</label>
        <div class="templatemo-input-icon-container">
            <select name="user.region" id="user.region" class="form-control">
                <c:forEach items="${regions}" var="region">
                    <option value="${region}">${region}</option>
                </c:forEach>
                <option value="0" selected>Selecione uma região</option>
            </select>
        </div>
    </div>
</div>

<div class="form-group">
    <div class="col-md-12">
        <label for="user.area" class="control-label">Selecione sua area:</label>
        <div class="templatemo-input-icon-container">
            <select name="user.area" id="user.area" class="form-control">
                <c:forEach items="${areas}" var="area">
                    <option value="${area}">${area}</option>
                </c:forEach>
                <option value="0" selected>Selecione uma area</option>
            </select>
        </div>
    </div>
</div>