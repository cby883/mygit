package com.esgov.jrw.sysmgrservice.service.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.entity.authority.SysMenu;

/**
 * 菜单管理
 * Created by HZX on 2018/4/2.
 */
public interface SysMenuService {
    ServiceResponse save(SysMenu sysMenu);
    ServiceResponse delete( String id);
    ServiceResponse deleteAll(String[] delete);
    ServiceResponse update(SysMenu sysMenu);
    ServiceResponse get( String id);
    ServiceResponse queryPage(SysMenu sysMenu,int currentPage , int pageSize);
    ServiceResponse getTree();
    /**
     *
     * 获取父菜单下的所有子菜单
     * @param parentId
     * @return
     */
    ServiceResponse getSubMenu(String parentId)  ;
    /**
     *
     * 获取所有的菜单
     * @return
     */
    ServiceResponse getAllMenu()  ;
}
