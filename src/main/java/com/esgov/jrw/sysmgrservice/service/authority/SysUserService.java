package com.esgov.jrw.sysmgrservice.service.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysUser;
import org.springframework.web.bind.annotation.*;

/**
 * Created by HZX on 2018/3/27.
 * user manage interface
 */

public interface SysUserService {
    ServiceResponse save(SysUser sysUser);
    ServiceResponse delete(String id);
    ServiceResponse deletAll(String[] delete);
    ServiceResponse update(SysUser sysUser);
    ServiceResponse get( String id);
    ServiceResponse queryPage(SysUser sysUser,int currentPage , int pageSize);

    /**
     * get user by loginName
     * @param loginName
     * @return
     */

    ServiceResponse<SysUser> getUserByLoginName(String loginName);

    /**
     * enable user
     * @param id
     */

    ServiceResponse enabledUser(@PathVariable("id") String id) ;

    /**
     * disable user
     * @param id
     */

    ServiceResponse disabledUser(@PathVariable("id") String id) ;
}