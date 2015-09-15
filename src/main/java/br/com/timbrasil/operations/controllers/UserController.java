package br.com.timbrasil.operations.controllers;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.timbrasil.operations.annotations.Public;
import br.com.timbrasil.operations.daos.UserDao;
import br.com.timbrasil.operations.models.Area;
import br.com.timbrasil.operations.models.Region;
import br.com.timbrasil.operations.models.User;

@Controller
public class UserController {
	
	private final UserSession userSession;
	private final Result result;
	private final UserDao dao;
	private User user;
	private String cpassword;
	
	@Inject
	public UserController(UserSession userSession, Result result, UserDao dao) {
		this.dao = dao;
		this.userSession = userSession;
		this.result = result;
	}

	@Deprecated
	public UserController() {
		this(null,null,null);
	}
	
	@Get
	@Public
	public void form(){
		if(userSession.isLogged()){
			result.redirectTo(HomeController.class).index();
		}
		result.include("regions" , Region.values());
		result.include("areas", Area.values());
	}
	
	@Post
	@Public
	public void save(User user, String cpassword){
		dao.save(user);
		
		result.redirectTo(LoginController.class).login();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}
	
}
