package br.com.timbrasil.operations.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import javax.transaction.Transactional;

import br.com.timbrasil.operations.daos.ProductDao;
import br.com.timbrasil.operations.daos.CategoryDao;
import br.com.timbrasil.operations.models.Category;
import br.com.timbrasil.operations.models.Product;

@Controller
@Path("/products")
public class ProductController
{

   @Inject
   private ProductDao productDao;
   @Inject
   private CategoryDao categoryDao;
   @Inject
   private Validator validator;
   @Inject
   private Result result;

   @Get("/form")
   public void formAdd(Product product)
   {
      result.include("product", product);
      loadFormDependencies();
   }

   private void loadFormDependencies()
   {
      result.include("categories", categoryDao.all());
   }

   @Post("")
   @Transactional
   public void save(@Valid Product product)
   {
      validator.onErrorForwardTo(ProductController.class).formAdd(product);
      productDao.save(product);
      result.redirectTo(ProductController.class).list();
   }

   @Get("/{product.id}")
   public void formUpdate(Product product)
   {
      result.include("product", productDao.findById(product.getId()));
      loadFormDependencies();
   }

   @Get("")
   public void list()
   {
      result.include("list", productDao.all());
   }

   //just because get is easier here. Be my guest if you want to change.
   @Get("/remove/{id}")
   @Transactional
   public void remove(Integer id)
   {
      Product product = productDao.findById(id);
      productDao.remove(product);
      result.redirectTo(ProductController.class).list();
   }

   @Post("/{id}")
   @Transactional
   public void update(Integer id, @Valid Product product)
   {
      product.setId(id);
      validator.onErrorForwardTo(ProductController.class).formUpdate(product);

      productDao.update(product);
      result.redirectTo(ProductController.class).list();
   }
}
