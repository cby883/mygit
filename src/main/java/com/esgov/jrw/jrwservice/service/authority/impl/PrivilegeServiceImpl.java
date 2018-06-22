package com.esgov.jrw.jrwservice.service.authority.impl;

import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.jrwservice.common.dto.Menu;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.dao.authority.SysMenuDao;
import com.esgov.jrw.jrwservice.entity.authority.SysMenu;
import com.esgov.jrw.jrwservice.service.authority.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HZX on 2018/4/2.
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    @Autowired
    SysMenuDao sysMenuDao;

    @Override
    public ServiceResponse getMenuPrivileges(HashMap<String, String> paramMap) {
        Integer level = Integer.parseInt(paramMap.get("level"));
        Menu menu = formMenuPrivilege(paramMap.get("menuId"),level);
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS, menu);
    }
    /**
     * 构建菜单权限信息
     * @param menuId
     * @param level
     * @return
     */
    private Menu formMenuPrivilege(String menuId,int level)   {
        Menu menu = null;
        SysMenu sysMenu = sysMenuDao.selectById(menuId);
        //先判断是否有访问此菜单的权限
        if( (null!=sysMenu) && (sysMenu.getIsUsed().equals("1"))){
            menu = Menu.transformer(sysMenuDao.selectById(menuId));
            //菜单层级大于0
            if(level > 0){
                List<SysMenu> sysSubs = sysMenuDao.getMenuListByParentId(menuId);
                if(null!=sysSubs && !sysSubs.isEmpty()){
                    for(SysMenu sysSub: sysSubs){
                        Menu sub = formMenuPrivilege(sysSub.getId(),level-1);
                        if(null!=sub){
                            menu.addSub(sub);
                        }
                    }
                }
            }
        }
        return menu;
    }

}
