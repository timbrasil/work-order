package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.com.timbrasil.operations.daos.CheckListModelDao;
import br.com.timbrasil.operations.daos.ItemCheckListDao;
import br.com.timbrasil.operations.models.CheckListModel;
import br.com.timbrasil.operations.models.ItemCheckList;
import br.com.timbrasil.operations.models.Technology;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static br.com.caelum.vraptor.view.Results.json;

@Controller
public class CheckListModelController {

    private final Result result;
    private Validator validator;
    //DAOs
    private CheckListModelDao checkListModelDao;
    private ItemCheckListDao itemCheckListDao;

    @Inject
    public CheckListModelController(Result result, Validator validator, CheckListModelDao checkListModelDao, ItemCheckListDao itemCheckListDao) {
        this.result = result;
        this.validator = validator;
        this.checkListModelDao = checkListModelDao;
        this.itemCheckListDao = itemCheckListDao;
    }

    @Deprecated
    public CheckListModelController() {
        this(null,null,null,null);
    }

    @Get
    public void form(){
        result.include("technologys", Technology.values());
    }

    @Get("/checkListModel")
    public void list(Boolean active){
        if(active==null){
            result.include("checkListModels", checkListModelDao.list());
        }
        else{
            result.include("checkListModels", checkListModelDao.listActive(active));
        }
    }

    @Post
    public void save(CheckListModel checkListModel, List<ItemCheckList> itemsCheckList){
        Map<String, Object> aMap = new HashMap<String, Object>();
        try{
            System.out.println(itemsCheckList);

            itemCheckListDao.save(itemsCheckList);

            checkListModel.setItemsCheckList(itemsCheckList);
            checkListModel.setActive(true);

            checkListModelDao.save(checkListModel);

            if(validator.hasErrors()) {
                aMap.put("status",false);
                aMap.put("error",validator.getErrors());
            }
            else {
                aMap.put("status",true);
                aMap.put("dados",checkListModel);
            }

            if(checkListModel.getId()==0){
                aMap.replace("status", false);
            }
        }
        catch (Exception e){
            aMap.put("status",false);
            aMap.put("error", e.getCause());
        }
        finally {
            result.use(json()).withoutRoot().from(aMap).recursive().serialize();
        }
    }

}
