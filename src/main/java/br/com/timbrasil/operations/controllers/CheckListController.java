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
        this(null,null,null,null);
    }

    @Get("/checkList/form/models")
    public void formCheckListModal(){
        result.include("checkListModels", checkListModelDao.listActive(true));
    }

    @Get("/checkList/form/models/{checkListModel.id}")
    public void formWorkOrder(CheckListModel checkListModel){
        checkListModel = checkListModelDao.find(checkListModel);
        List<WorkOrder> workOrders = workOrderDao.listTechnology(checkListModel.getTechnology());

        result.include("checkListModel",checkListModel);
        result.include("workOrders",workOrders);
    }

    @Get("/checkList/form/workOrder/{workOrder.id}/model/{checkListModel.id}")
    public void form(WorkOrder workOrder, CheckListModel checkListModel){
    }



}
