package br.com.timbrasil.operations.controllers;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.timbrasil.operations.models.User;

@Named
@SessionScoped
public class UserSession {

	private User user;

	public User getUser() {
		return user;
	}

	public boolean isLogged() {
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}

	public void setUser(User user) {
		this.user = user;
	}

}
