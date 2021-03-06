package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
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
public class WorkOrderController {

    private Result result;
    private Validator validator;
    //DAOs
    private TypeWorkOrderDao typeWorkOrderDao;
    private CityDao cityDao;
    private SiteDao siteDao;
    private LogStatusDao logStatusDao;
    private WorkOrderDao workOrderDao;

    @Inject
    public WorkOrderController(Validator validator, TypeWorkOrderDao typeWorkOrderDao, CityDao cityDao, SiteDao siteDao, LogStatusDao logStatusDao, WorkOrderDao workOrderDao, Result result) {
        this.validator = validator;
        this.typeWorkOrderDao = typeWorkOrderDao;
        this.cityDao = cityDao;
        this.siteDao = siteDao;
        this.logStatusDao = logStatusDao;
        this.workOrderDao = workOrderDao;
        this.result = result;
    }

    @Deprecated
    public WorkOrderController() {
    }

    @Get("/workOrder")
    public void list(){
        result.include("workOrders",workOrderDao.list());
    }

    @Get("/workOrder/{workOrder.id}")
    public void detail(WorkOrder workOrder){
        workOrder = workOrderDao.find(workOrder);
        result.include("workOrder",workOrder);
    }

    @Get("/workOrder/add/form")
    public void form(){
        result.include("typeWorkOrders",typeWorkOrderDao.list());
        result.include("cities",cityDao.list());
        result.include("technologys", Technology.values());
    }

    @Post("/workOrder/add/save")
    public void save(WorkOrder workOrder,Site site,City city,Address address,List<TypeWorkOrder> typeWorkOrder,LogStatus logStatus){
        Map<String, Object> aMap = new HashMap<String, Object>();
        System.out.println(typeWorkOrder);
        try {
            WorkOrder workOrderSearch = workOrderDao.find(workOrder);
            if(workOrderSearch!=null){
                aMap.put("status", false);
                aMap.put("error", "WorkOrder já cadastrada!");
                return;
            }

            //Valida data de atribuição.
            if(workOrder.getAtribution().compareTo(Calendar.getInstance())>0){
                aMap.put("status",false);
                aMap.put("error", "A data de atribuição não pode ser superior a data atual!");
                return;
            }

            //Valida se existe pelo menos uma tecnologia
            if(workOrder.getTechnologies().size()==0){
                aMap.put("status",false);
                aMap.put("error", "É obrigatório informar pelo menos uma tecnologia para a WorkOrder!");
                return;
            }

            //Valida se existe pelo menos um tipo de aceitação selecionado.
            if(typeWorkOrder==null){
                aMap.put("status",false);
                aMap.put("error", "É obrigatório informar pelo menos um tipo de aceitação!");
                return;
            }

            //Site
            site = siteDao.findOrPersist(site,city,address);

            //LogStatus
            logStatus.setStatus(StatusWorkOrder.CREATE);
            logStatus.setExecution(Calendar.getInstance());
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
        } catch (NullPointerException e) {
            aMap.put("status", false);
            aMap.put("error", "Existe campos vazios!");
        } finally {
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
    }

    @Get("/workOrder/edit/{workOrder.id}")
    public void formUpdate(WorkOrder workOrder){
        workOrder = workOrderDao.find(workOrder);
        //Verifica se WO existe.
        if(workOrder==null){
            result.redirectTo(this).list();
        }
        else{
            //Verifica se a WO já possui um checklist atrelado. Caso possua, não deixa editar.
            if(workOrder.getLastLogStatus().getStatus()==StatusWorkOrder.CREATE){
                result.include("workOrder", workOrder);
                result.include("typeWorkOrders",typeWorkOrderDao.list());
                result.include("cities",cityDao.list());
                result.include("technologys", Technology.values());
            }
            else{
                result.redirectTo(this).detail(workOrder);
            }
        }
    }

    @Post("/workOrder/edit/save")
    public void update(WorkOrder workOrder,Site site,City city,Address address,List<TypeWorkOrder> typeWorkOrder,LogStatus logStatus){
        Map<String, Object> aMap = new HashMap<String, Object>();
        try {
            WorkOrder workOrderSearch = workOrderDao.find(workOrder);
            if(workOrderSearch==null){
                aMap.put("status", false);
                aMap.put("error", "WorkOrder não encontrada!");
                return;
            }

            //Verifica se a WO já possui um checklist atrelado. Caso possua, não deixa editar.
            if(workOrderSearch.getLastLogStatus().getStatus()!=StatusWorkOrder.CREATE){
                result.redirectTo(this).detail(workOrder);
            }

            //Valida se existe pelo menos uma tecnologia
            if(workOrder.getTechnologies()==null){
                aMap.put("status",false);
                aMap.put("error", "É obrigatório informar pelo menos uma tecnologia para a WorkOrder!");
                return;
            }

            //Valida se existe pelo menos um tipo de aceitação selecionado.
            if(typeWorkOrder==null){
                aMap.put("status",false);
                aMap.put("error", "É obrigatório informar pelo menos um tipo de aceitação!");
                return;
            }

            //Site
            site = siteDao.findOrPersist(site,city,address);

            //WorkOrder
            workOrderSearch.setTicketId(workOrder.getTicketId());
            workOrderSearch.setTechnologies(workOrder.getTechnologies());
            workOrderSearch.setTypeWorkOrders(typeWorkOrder);
            workOrderSearch.setSite(site);
            workOrderDao.update(workOrderSearch);

            if(validator.hasErrors()) {
                aMap.put("status",false);
                aMap.put("errors",validator.getErrors());
            }
            else {
                aMap.put("status",true);
                aMap.put("dados",workOrder);
            }
        } catch (NullPointerException e) {
            aMap.put("status",false);
            aMap.put("error", "Existe dados não preenchidos!");
            e.printStackTrace();
            System.out.println("Catch:\n");
            System.out.println(workOrder);
            System.out.println(site);
            System.out.println(city);
            System.out.println(address);
            System.out.println(typeWorkOrder);
            System.out.println(logStatus);
        } finally {
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
    }

    @Post("/workOrder/{workOrder.id}/reatribution")
    public void reatribution(WorkOrder workOrder, LogStatus logStatus){
        Map<String, Object> aMap = new HashMap<String, Object>();
        try{
            workOrder = workOrderDao.find(workOrder.getId());
            //Caso WorkOrder não seja encontrada.
            if(workOrder.getId()==0){
                aMap.put("status",false);
                aMap.put("error", "WorkOrder não encontrada!");
                result.use(json()).withoutRoot().from(aMap).recursive().serialize();
                return;
            }
            //Caso o Status da WorkOrder não seja Rejeitado.
            if(workOrder.getLastLogStatus().getStatus()!=StatusWorkOrder.REJECTED){
                aMap.put("status",false);
                aMap.put("error", "O status atual da WorkOrder deve ser " + StatusWorkOrder.REJECTED.toString() + "!");
                result.use(json()).withoutRoot().from(aMap).recursive().serialize();
                return;
            }
            //Valida logStatus.execution
            if(logStatus.getExecution().compareTo(Calendar.getInstance())>0){
                aMap.put("status",false);
                aMap.put("error", "A data de reatribuição não pode ser superior a data atual!");
                result.use(json()).withoutRoot().from(aMap).recursive().serialize();
                return;
            }
            if(logStatus.getExecution().compareTo(workOrder.getLastLogStatus().getExecution())<=0){
                aMap.put("status",false);
                aMap.put("error", "A data de reatribuição deve ser maior que a data de rejeição!");
                result.use(json()).withoutRoot().from(aMap).recursive().serialize();
                return;
            }

            logStatus.setStatus(StatusWorkOrder.REATRIBUTION);
            logStatusDao.save(logStatus);

            workOrder.pushLogStatus(logStatus);
            workOrderDao.save(workOrder);

            aMap.put("status", true);
            aMap.put("data", workOrder);
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
        catch (NullPointerException npe){
            aMap.put("status",false);
            aMap.put("error","Existe dados não preenchidos!");
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
    }
}
