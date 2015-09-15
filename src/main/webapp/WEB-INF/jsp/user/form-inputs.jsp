<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="form-group">
	<label for="user.name">Nome:</label>
	<div class="input-group">
		<input type="text" name="user.name" id="user.name"
			value="${user.name}" />
	</div>
</div>

<div class="form-group">
	<label for="user.email">Email:</label>
	<div class="input-group">
		<input type="text" name="user.email" id="user.email"
			value="${user.email}" />
	</div>
</div>

<div class="form-group">
	<label for="user.register">Matricula:</label>
	<div class="input-group">
		<input type="text" name="user.register" id="user.register"
			value="${user.register}" />
	</div>
</div>

<div class="form-group">
	<label for="user.password">Senha:</label>
	<div class="input-group">
		<input type="password" name="user.password" id="user.password"
			value="${user.password}" />
	</div>
</div>

<div class="form-group">
	<label for="user.cpassword">Confirme a senha:</label>
	<div class="input-group">
		<input type="password" name="user.password" id="user.password"
			value="${user.password}" />
	</div>
</div>

<div class="form-group">
	<label for="user.region">Região:</label>
	<div class="input-group">
		<select>
			<option value="TSP">TSP</option>
		</select>
	</div>
</div>

<div class="form-group">
	<label for="user.area">Area:</label>
	<div class="input-group">
		<select>
			<option value="CNO">CNO</option>
		</select>
	</div>
</div>