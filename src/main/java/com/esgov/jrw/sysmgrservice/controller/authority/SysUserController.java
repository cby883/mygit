package com.esgov.jrw.sysmgrservice.controller.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysUser;
import com.esgov.jrw.sysmgrservice.service.authority.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018-03-29 下午7:32
 */
@RestController
public class SysUserController {
    private static final String BASE_PATH="/sysmgr/service/sysuser/users";
    @Autowired
    SysUserService sysUserService;
    @RequestMapping(value = BASE_PATH,method = RequestMethod.POST)
    ServiceResponse save(@RequestBody SysUser sysUser){
        return sysUserService.save(sysUser);
    }
    @RequestMapping(value = BASE_PATH+"/{id}",method = RequestMethod.DELETE)
    ServiceResponse delete(@PathVariable("id")String id){
        return sysUserService.delete(id);
    }
    @RequestMapping(value = BASE_PATH+"/batch",method = RequestMethod.POST)
    ServiceResponse deletAll(@RequestParam(value = "delete[]")String[] delete){
        return sysUserService.deletAll(delete);
    }
    @RequestMapping(value = BASE_PATH,method = RequestMethod.PUT)
    ServiceResponse update(@RequestBody SysUser sysUser){
        return sysUserService.update(sysUser);
    }
    @RequestMapping(value = BASE_PATH+"/{id}",method = RequestMethod.GET)
    ServiceResponse get(@PathVariable("id")String id){
        return sysUserService.get(id);
    }
    @RequestMapping(value =  BASE_PATH+"/{currentPage}/{pageSize}",method = RequestMethod.POST)
    ServiceResponse queryPage(@RequestBody SysUser sysUser, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return sysUserService.queryPage(sysUser,currentPage,pageSize);
    }

    /**
     * get user by loginName
     * @param loginName
     * @return
     */
    @RequestMapping(value = BASE_PATH+"/loginName/{loginName}",method = RequestMethod.GET)
    ServiceResponse getUserByLoginName(@PathVariable("loginName")String loginName){
        return sysUserService.getUserByLoginName(loginName);
    }

    /**
     * enable user
     * @param id
     */
    @RequestMapping(value = BASE_PATH+"/used/{id}",method = RequestMethod.PUT)
    ServiceResponse enabledUser(@PathVariable("id")String id) {
        return sysUserService.enabledUser(id);
    }

    /**
     * disable user
     * @param id
     */
    @RequestMapping(value = BASE_PATH+"/unused/{id}",method = RequestMethod.PUT)
    ServiceResponse disabledUser(@PathVariable("id")String id) {
        return sysUserService.disabledUser(id);
    }
}
