package br.com.timbrasil.operations.interceptor;

import javax.inject.Inject;

import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.BeforeCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.timbrasil.operations.annotations.Public;
import br.com.timbrasil.operations.controllers.LoginController;
import br.com.timbrasil.operations.controllers.UserSession;

@Intercepts
public class AuthorizationInterceptor {
	
	private final UserSession userInSession;
	private final Result result;
	private final ControllerMethod controllerMethod;
	
	/**
	 * CDI only
	 */
	@Deprecated
	public AuthorizationInterceptor() {
		this(null,null,null);
	}

	@Inject
	public AuthorizationInterceptor(UserSession userInSession, Result result, ControllerMethod controllerMethod) {
		this.controllerMethod = controllerMethod;
		this.userInSession = userInSession;
		this.result = result;
	}
	
	@BeforeCall
	public void before(){
		if(!userInSession.isLogged()){
			result.redirectTo(LoginController.class).login();
			return;
		}
	}
	
	@Accepts
	public boolean accepts(){
		return !controllerMethod.containsAnnotation(Public.class);
	}

}
