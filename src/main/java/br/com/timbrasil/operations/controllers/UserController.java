package br.com.timbrasil.operations.controllers;

import static br.com.caelum.vraptor.view.Results.json;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.annotations.Public;
import br.com.timbrasil.operations.daos.UserDao;
import br.com.timbrasil.operations.models.Area;
import br.com.timbrasil.operations.models.Region;
import br.com.timbrasil.operations.models.User;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
	
	private final UserSession userSession;
	private final Result result;
	private final Validator validator;
	private final UserDao dao;
	private User user;
	private String cpassword;
	
	@Inject
	public UserController(UserSession userSession, Result result, UserDao dao, Validator validator) {
		this.validator = validator;
		this.dao = dao;
		this.userSession = userSession;
		this.result = result;
	}

	@Deprecated
	public UserController() {
		this(null,null,null,null);
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
	public void save(@Valid User user, String cpassword){
		validator.onErrorUsePageOf(this).form();
		
		dao.save(user);

		Map<String, Object> aMap = new HashMap<String, Object>();
		if(validator.hasErrors()) {
			aMap.put("status",false);
			aMap.put("errors",validator.getErrors());
		}
		else {
			aMap.put("status",true);
			aMap.put("dados",user);
		}


//		result.include("sucess","Usuario cadastrado com sucesso");
		result.use(json()).withoutRoot().from(aMap).recursive().serialize();
		
//		result.forwardTo(this).form();
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
