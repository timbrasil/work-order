package br.com.timbrasil.operations.controllers;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.timbrasil.operations.models.User;

@Named
@SessionScoped
public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private User user;

	public boolean isLogged() {
		return user != null;
	}
	
	public String getEmail(){
		return this.user.getEmail();
	}
	
	public void logIn(User user){
		this.user = user;
	}
	
	public void logOut(){
		this.user = null;
	}

}
