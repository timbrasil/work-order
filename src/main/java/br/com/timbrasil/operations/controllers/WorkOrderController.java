package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.daos.CityDao;
import br.com.timbrasil.operations.daos.TypeWorkOrderDao;
import br.com.timbrasil.operations.models.Technology;
import br.com.timbrasil.operations.models.TypeWorkOrder;

import javax.inject.Inject;

@Controller
public class WorkOrderController {

    private final Result result;
    private final Validator validator;
    private TypeWorkOrderDao typeWorkOrderDao;
    private CityDao cityDao;

    @Inject
    public WorkOrderController(Result result, Validator validator, TypeWorkOrderDao typeWorkOrderDao,CityDao cityDao) {
        this.result = result;
        this.validator = validator;
        this.typeWorkOrderDao = typeWorkOrderDao;
        this.cityDao = cityDao;
    }

    @Deprecated
    public WorkOrderController() {
        this(null,null,null,null);
    }

    @Get
    public void form(){

        result.include("types",typeWorkOrderDao.list());
        result.include("cities",cityDao.list());
        result.include("technologys", Technology.values());
    }
}
