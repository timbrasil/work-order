package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.daos.*;
import br.com.timbrasil.operations.models.*;

import javax.inject.Inject;
import java.util.Calendar;
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
    private LogStatusDao logStatusDao;
    private CheckListDao checkListDao;
    private ItemCheckListDao itemCheckListDao;

    @Inject
    public CheckListController(Result result, Validator validator, WorkOrderDao workOrderDao, LogStatusDao logStatusDao, CheckListDao checkListDao, ItemCheckListDao itemCheckListDao) {
        this.result = result;
        this.validator = validator;
        this.workOrderDao = workOrderDao;
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
            if(workOrder.getLastLogStatus().getStatus()!=StatusWorkOrder.CREATE&workOrder.getLastLogStatus().getStatus()!=StatusWorkOrder.REATRIBUTION&workOrder.getLastLogStatus().getStatus()!=StatusWorkOrder.WORKING){
                result.redirectTo(WorkOrderController.class).detail(workOrder);
            }
        }
        catch (NullPointerException e){
            result.redirectTo(WorkOrderController.class).list();
        }
        finally {
            List<ItemCheckList> itemsCheckList = itemCheckListDao.listByTechnologyAndActive(workOrder.getTechnologies(),true);
            result.include("workOrder",workOrder);
            result.include("itemsCheckList", itemsCheckList);
            result.include("answersItemCheckList",AnswersItemChecklist.values());
            result.include("statusWorkOrder",StatusWorkOrder.values());
        }
    }

    @Post("/workOrder/checkList/save")
    public void save(WorkOrder workOrder, LogStatus logStatus, CheckList checkList){
        Map<String, Object> aMap = new HashMap<String, Object>();
        try{
            workOrder = workOrderDao.find(workOrder.getId());
            if(workOrder==null){
                aMap.put("status",false);
                aMap.put("errors","WorkOrder não existe!");
                return;
            }

            if(workOrder.getLastLogStatus().getStatus()!=StatusWorkOrder.CREATE&workOrder.getLastLogStatus().getStatus()!= StatusWorkOrder.REATRIBUTION&workOrder.getLastLogStatus().getStatus()!=StatusWorkOrder.WORKING){
                aMap.put("status",false);
                aMap.put("error","WorkOrder deve estar no estado de CREATE, REATRIBUTION OU WORKING para salvar um checklist!");
                return;
            }

            if(logStatus.getExecution().compareTo(Calendar.getInstance())>0){
                aMap.put("status",false);
                aMap.put("error", "A data de reatribuição não pode ser superior a data atual!");
                result.use(json()).withoutRoot().from(aMap).recursive().serialize();
                return;
            }

            checkListDao.save(checkList);
            logStatus.setCheckList(checkList);
            logStatusDao.save(logStatus);

            workOrder.pushLogStatus(logStatus);
            workOrderDao.update(workOrder);

            if(validator.hasErrors()) {
                aMap.put("status",false);
                aMap.put("errors",validator.getErrors());
            }
            else {
                aMap.put("status",true);
                aMap.put("dados", workOrder);
            }
        }
        catch (Exception e) {
            aMap.put("status",false);
            aMap.put("error", e);
        } finally {
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
    }

    @Get
    @Path({"/workOrder/{workOrder.id}/checkList/{logStatusIndex}","/workOrder/{workOrder.id}/checkList"})
    public void detail(WorkOrder workOrder, int logStatusIndex){
        try{
            workOrder = workOrderDao.find(workOrder);
            logStatusIndex--; //Indice real é -1 do passado.
            LogStatus logStatus;
            if(logStatusIndex<0){
                logStatus = workOrder.getLastLogStatusWithCheckList();
            }
            else{
                logStatus = workOrder.getLogStatus(logStatusIndex);
            }
            if(logStatus==null||logStatus.getCheckList()==null){
                result.redirectTo(WorkOrderController.class).detail(workOrder);
            }
            result.include("workOrder",workOrder);
            result.include("logStatus",logStatus);
        }
        catch (NullPointerException e){
            result.redirectTo(WorkOrderController.class).list();
        }
    }

}
