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
public class WorkOrderController {

    private final Result result;
    private final Validator validator;
    //DAOs
    private TypeWorkOrderDao typeWorkOrderDao;
    private CityDao cityDao;
    private SiteDao siteDao;
    private AddressDao addressDao;
    private LogStatusDao logStatusDao;
    private LogAcceptionDao logAcceptionDao;
    private WorkOrderDao workOrderDao;
    private CheckListDao checkListDao;
    private ItemCheckListDao itemCheckListDao;

    @Inject
    public WorkOrderController(Validator validator, TypeWorkOrderDao typeWorkOrderDao, CityDao cityDao, SiteDao siteDao, AddressDao addressDao, LogStatusDao logStatusDao, LogAcceptionDao logAcceptionDao, WorkOrderDao workOrderDao, CheckListDao checkListDao, ItemCheckListDao itemCheckListDao, Result result) {
        this.validator = validator;
        this.typeWorkOrderDao = typeWorkOrderDao;
        this.cityDao = cityDao;
        this.siteDao = siteDao;
        this.addressDao = addressDao;
        this.logStatusDao = logStatusDao;
        this.logAcceptionDao = logAcceptionDao;
        this.workOrderDao = workOrderDao;
        this.checkListDao = checkListDao;
        this.itemCheckListDao = itemCheckListDao;
        this.result = result;
    }

    @Deprecated
    public WorkOrderController() {
        this(null, null, null, null, null, null, null, null, null, null,null);
    }

    @Get
    public void form(){
        result.include("typeWorkOrders",typeWorkOrderDao.list());
        result.include("cities",cityDao.list());
        result.include("technologys", Technology.values());
    }

    @Get("/workOrder")
    public void list(){
        result.include("workOrders",workOrderDao.list());
    }

    @Get("/workOrder/{workOrder.id}/checkList/add")
    public void addCheckListForm(WorkOrder workOrder){
        try{
            workOrder = workOrderDao.find(workOrder.getId());
            if(workOrder.getId()==0){
                result.redirectTo(this).list();
            }
        }
        catch (NullPointerException e){
            result.redirectTo(this).list();
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
    public void addCheckListSave(WorkOrder workOrder, LogStatus logStatus, CheckList checkList, LogAcception logAcception){
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

    @Post
    public void save(WorkOrder workOrder,Site site,City city,Address address,List<TypeWorkOrder> typeWorkOrder,LogStatus logStatus){
        Map<String, Object> aMap = new HashMap<String, Object>();
        try {
            //Site
            if(siteDao.find(site)==null){
                //City
                city = cityDao.find(city);

                //Address
                address.setCity(city);
                addressDao.save(address);

                site.setAddress(address);
                siteDao.save(site);
            }
            else{
                site = siteDao.find(site);
            }

            //LogStatus
            logStatus.setStatus(StatusWorkOrder.WORKING);
            logStatusDao.save(logStatus);

            //WorkOrder
            workOrder.setSite(site);
            workOrder.pushLogStatus(logStatus);
            workOrder.setTypeWorkOrders(typeWorkOrder);

            workOrderDao.save(workOrder);

            if(validator.hasErrors()) {
                aMap.put("status",false);
                aMap.put("errors",validator.getErrors());
            }
            else {
                aMap.put("status",true);
                aMap.put("dados",workOrder);
            }
        } catch (Exception e) {
            aMap.put("status",false);
            aMap.put("error",e.getMessage());
        } finally {
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }

    }
}
