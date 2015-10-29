package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.daos.*;
import br.com.timbrasil.operations.models.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.caelum.vraptor.view.Results.json;

@Controller
public class CheckListController {

    private Result result;
    private Validator validator;
    //DAOs
    private WorkOrderDao workOrderDao;
    private LogAcceptionDao logAcceptionDao;
    private LogStatusDao logStatusDao;
    private CheckListDao checkListDao;
    private ItemCheckListDao itemCheckListDao;

    @Inject
    public CheckListController(Result result, Validator validator, WorkOrderDao workOrderDao, LogAcceptionDao logAcceptionDao, LogStatusDao logStatusDao, CheckListDao checkListDao, ItemCheckListDao itemCheckListDao) {
        this.result = result;
        this.validator = validator;
        this.workOrderDao = workOrderDao;
        this.logAcceptionDao = logAcceptionDao;
        this.logStatusDao = logStatusDao;
        this.checkListDao = checkListDao;
        this.itemCheckListDao = itemCheckListDao;
    }

    @Deprecated
    public CheckListController() {
    }

    @Get("/workOrder/{workOrder.id}/checkList/add")
    public void form(WorkOrder workOrder){
        try{
            workOrder = workOrderDao.find(workOrder.getId());
            if(workOrder.getId()==0){
                result.redirectTo(WorkOrderController.class).list();
            }
        }
        catch (NullPointerException e){
            result.redirectTo(WorkOrderController.class).list();
        }
        finally {
            List<ItemCheckList> itemsCheckList = itemCheckListDao.listByTechnologyAndActive(workOrder.getTechnology(),true);
            System.out.println(itemsCheckList);
            result.include("workOrder",workOrder);
            result.include("itemsCheckList", itemsCheckList);
            result.include("answersItemCheckList",AnswersItemChecklist.values());
            result.include("statusAcception",StatusAcception.values());
        }
    }

    @Post("/workOrder/checkList/save")
    public void save(WorkOrder workOrder, LogStatus logStatus, CheckList checkList, LogAcception logAcception){
        Map<String, Object> aMap = new HashMap<String, Object>();
        try{
            logStatusDao.save(logStatus);

            checkListDao.save(checkList);

            logAcception.setDate(logStatus.getExecution());
            logAcception.setStatus(StatusAcception.valueOf(logStatus.getStatus().toString()));
            logAcception.setCheckList(checkList);
            logAcceptionDao.save(logAcception);

            workOrder = workOrderDao.find(workOrder.getId());
            workOrder.pushLogStatus(logStatus);
            workOrder.pushLogAcception(logAcception);
            workOrderDao.save(workOrder);

            if(validator.hasErrors()) {
                aMap.put("status",false);
                aMap.put("errors",validator.getErrors());
            }
            else {
                aMap.put("status",true);
                aMap.put("dados",workOrder);
            }
        }
        catch (Exception e) {
            aMap.put("status",false);
            aMap.put("error",e);
        } finally {
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
    }
}
