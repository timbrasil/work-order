package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.daos.CheckListModelDao;
import br.com.timbrasil.operations.daos.WorkOrderDao;
import br.com.timbrasil.operations.models.CheckListModel;
import br.com.timbrasil.operations.models.WorkOrder;

import javax.inject.Inject;
import java.util.List;

@Controller
public class CheckListController {

    private final Result result;
    private Validator validator;
    //DAOs
    private CheckListModelDao checkListModelDao;
    private WorkOrderDao workOrderDao;

    @Inject
    public CheckListController(Result result, Validator validator, CheckListModelDao checkListModelDao, WorkOrderDao workOrderDao) {
        this.result = result;
        this.validator = validator;
        this.checkListModelDao = checkListModelDao;
        this.workOrderDao = workOrderDao;
    }

    @Deprecated
    public CheckListController() {
        this(null, null, null, null);
    }

    @Get("/checkList/form")
    public void form(CheckListModel checkListModel, WorkOrder workOrder){
        if(workOrder.getId()==0){
            result.forwardTo(WorkOrderController.class).list();
        }
        if(checkListModel.getId()==0){
            result.forwardTo(CheckListModelController.class).list(true);
        }
    }



}
