package br.com.timbrasil.operations.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.annotations.Public;
import br.com.timbrasil.operations.daos.UserDao;
import br.com.timbrasil.operations.models.User;

@Controller
public class LoginController {
	
	private final UserDao userDao;
	private final UserSession userSession;
	private final Validator validator;
	private final Result result;
	private User user;
	
	@Inject
	public LoginController(UserDao userDao,UserSession userSession, Validator validator, Result result) {
		this.userSession = userSession;
		this.userDao = userDao;
		this.validator = validator;
		this.result = result;
	}
	
	@Deprecated
	public LoginController() {
		this(null,null,null,null);
	}

	@Public
	@Path("/login")
	public void login(){
		
	}
	
	@Public
	@Post
	public void autenticate(User user){
		this.user = userDao.find(user);
		if(this.user == null){
			validator.add(new SimpleMessage("Erro", "Email e/ou Senha inválidos"));
            validator.onErrorUsePageOf(this).login();
        }
        this.userSession.setUser(this.user);
        result.redirectTo(HomeController.class).index();
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

}
