package com.esgov.jrw.sysmgrservice.service.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysRole;

/**
 * 描述: 角色管理service
 *
 * @author xiaohc
 * @create 2018/3/28 19:37
 */
public interface SysRoleService {
    ServiceResponse save(SysRole sysRole);
    ServiceResponse delete(String id);
    ServiceResponse deletAll(String[] delete);
    ServiceResponse update(SysRole sysRole);
    ServiceResponse get( String id);
    ServiceResponse queryPage( SysRole sysRole, int currentPage,int pageSize);
    ServiceResponse enableRole(String id);
    ServiceResponse disableRole(String id);
    ServiceResponse getOnlyRole(String name);
}
