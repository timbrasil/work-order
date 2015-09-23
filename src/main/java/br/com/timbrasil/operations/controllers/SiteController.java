package br.com.timbrasil.operations.controllers;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.com.timbrasil.operations.daos.SiteDao;
import br.com.timbrasil.operations.models.Site;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

import static br.com.caelum.vraptor.view.Results.json;

@Controller
public class SiteController {

    private SiteDao siteDao;
    private Result result;

    @Inject
    public SiteController(SiteDao siteDao, Result result) {
        this.siteDao = siteDao;
        this.result = result;
    }

    @Deprecated
    public SiteController() {
    }

    //JSON ONLY
    @Get
    public void name(Site site){
        site = siteDao.find(site);

        Map<String, Object> aMap = new HashMap<String, Object>();
        if(site==null){
            aMap.put("status",false);
            aMap.put("error","Site não encontrado");
        }
        else{
            aMap.put("status",true);
            aMap.put("data",site);
        }

        result.use(json()).withoutRoot().from(aMap).recursive().serialize();
    }


}
