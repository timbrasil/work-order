package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.daos.WorkOrderDao;

import javax.inject.Inject;

@Controller
public class CheckListController {

    private final Result result;
    private Validator validator;
    //DAOs
    private WorkOrderDao workOrderDao;

    @Inject
    public CheckListController(Result result, Validator validator, WorkOrderDao workOrderDao) {
        this.result = result;
        this.validator = validator;
        this.workOrderDao = workOrderDao;
    }

    @Deprecated
    public CheckListController() {
        this(null, null, null);
    }
}
