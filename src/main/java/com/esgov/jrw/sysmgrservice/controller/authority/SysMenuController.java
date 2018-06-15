package com.esgov.jrw.sysmgrservice.controller.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysMenu;
import com.esgov.jrw.sysmgrservice.service.authority.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HZX on 2018/4/2.
 */
@RestController
public class SysMenuController {
    private static final String BASE_PATH="/sysmgr/service/sysmenu/menus";
    @Autowired
    SysMenuService sysMenuService;
    @RequestMapping(value = BASE_PATH,method = RequestMethod.POST)
    ServiceResponse save(@RequestBody SysMenu sysMenu){
        return sysMenuService.save(sysMenu);
    }
    @RequestMapping(value = BASE_PATH+"/{id}",method = RequestMethod.DELETE)
    ServiceResponse delete(@PathVariable("id")String id){
        return sysMenuService.delete(id);
    }
    @RequestMapping(value = BASE_PATH+"/batch",method = RequestMethod.POST)
    ServiceResponse deletAll(@RequestParam(value = "delete[]")String[] delete){
        return sysMenuService.deleteAll(delete);
    }
    @RequestMapping(value = BASE_PATH,method = RequestMethod.PUT)
    ServiceResponse update(@RequestBody SysMenu sysMenu){
        return sysMenuService.update(sysMenu);
    }
    @RequestMapping(value = BASE_PATH+"/{id}",method = RequestMethod.GET)
    ServiceResponse get(@PathVariable("id")String id){
        return sysMenuService.get(id);
    }
    @RequestMapping(value =  BASE_PATH+"/{currentPage}/{pageSize}",method = RequestMethod.POST)
    ServiceResponse queryPage(@RequestBody SysMenu sysMenu, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return sysMenuService.queryPage(sysMenu, currentPage, pageSize);
    }
    @GetMapping(value = "/trees")
    ServiceResponse getTree(){
        return sysMenuService.getTree();
    }

    @GetMapping(value = "/childs/{id}")
    ServiceResponse menuChildList(@PathVariable("id") String id){
        return sysMenuService.getSubMenu(id);
    }
    @GetMapping(value = "/all")
    ServiceResponse getAllMenu(){
        return sysMenuService.getAllMenu();
    }
}
