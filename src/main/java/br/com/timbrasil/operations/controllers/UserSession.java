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
