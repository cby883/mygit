package com.esgov.jrw.sysmgrservice.controller.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysRole;
import com.esgov.jrw.sysmgrservice.service.authority.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018-03-29 下午7:33
 */
@RestController
public class SysRoleController {
    private static final  String BASE_PATH="/sysmgr/service/sysrole/role";
    @Autowired
    SysRoleService sysRoleService;
    @RequestMapping(value = BASE_PATH,method = RequestMethod.POST)
    ServiceResponse save(@RequestBody SysRole sysRole){
        return sysRoleService.save(sysRole);
    }
    @RequestMapping(value = BASE_PATH+"/{id}",method = RequestMethod.DELETE)
    ServiceResponse delete(@PathVariable("id") String id){
       return  sysRoleService.delete(id);
    }
    @RequestMapping(value = BASE_PATH+"/batch",method = RequestMethod.POST)
    ServiceResponse deletAll(@RequestParam(value = "delete[]") String[] delete){
        return sysRoleService.deletAll(delete);
    }
    @RequestMapping(value = BASE_PATH,method = RequestMethod.PUT)
    ServiceResponse update(@RequestBody SysRole sysRole){
        return sysRoleService.update(sysRole);
    }
    @RequestMapping(value = BASE_PATH+"/{id}",method = RequestMethod.GET)
    ServiceResponse get(@PathVariable("id") String id){
       return  sysRoleService.get(id);
    }
    @RequestMapping(value = BASE_PATH+"/{currentPage}/{pageSize}",method = RequestMethod.POST)
    ServiceResponse queryPage(@RequestBody SysRole sysRole, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return sysRoleService.queryPage(sysRole,currentPage,pageSize);
    }
    @RequestMapping(value = BASE_PATH+"/used/{id}",method = RequestMethod.PUT)
    ServiceResponse enableRole(@PathVariable("id") String id){
        return sysRoleService.enableRole(id);
    }
    @RequestMapping(value = BASE_PATH+"/unused/{id}",method = RequestMethod.PUT)
    ServiceResponse disableRole(@PathVariable("id") String id){
        return sysRoleService.disableRole(id);
    }
    @RequestMapping(value = BASE_PATH+"/checkOnlyRole/{name}",method = RequestMethod.GET)
    ServiceResponse getOnlyRole(@PathVariable("name")  String name){
        return sysRoleService.getOnlyRole(name);
    }
}
