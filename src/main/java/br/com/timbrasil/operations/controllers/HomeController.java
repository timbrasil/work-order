package br.com.timbrasil.operations.controllers;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Path;

@Controller
public class HomeController
{

   @Inject
   private Result result;

   @Path("/")
   public void index()
   {
   }
}