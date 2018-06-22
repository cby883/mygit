package com.esgov.jrw.jrwservice.service.authority.impl;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.jrwservice.common.execption.ServiceException;
import com.esgov.jrw.jrwservice.common.util.StringUtilExt;
import com.esgov.jrw.jrwservice.dao.authority.SysRoleMenuButtonMappDao;
import com.esgov.jrw.jrwservice.entity.authority.SysRoleMenuButtonMapp;
import com.esgov.jrw.jrwservice.service.authority.SysRoleMenuButtonMappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018-04-17 下午2:12
 */
@Service
public class SysRoleMenuButtonMappServiceImpl implements SysRoleMenuButtonMappService {
    @Autowired
    SysRoleMenuButtonMappDao sysRoleMenuButtonMappDao;

    /**
     *
     * 获取角色、菜单、按钮关联信息
     * @param roleId
     */
    @Override
    public ServiceResponse getRoleMenuButtonMapps(String roleId){
        List<SysRoleMenuButtonMapp> roleMenuButtonMapps = sysRoleMenuButtonMappDao.getRoleMenuButtonMapps(roleId,null);
        if (roleMenuButtonMapps==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(roleMenuButtonMapps);
    }

    /**
     * 根据一个或多个RoleId 批量关联菜单按钮
     *
     * @param roleId
     * @param addMenus
     * @param delMenus
     * @return
     * @throws
     */
    @Override
    public ServiceResponse mapRoleMenuButton(String roleId, List<HashMap<String, String>> addMenus, String delMenus) throws ServiceException{
        String[] roleIds = StringUtilExt.split(roleId,",");
        String[] delMenusArr = StringUtilExt.split(delMenus,",");
        if(roleIds.length == 1){
            return getMapRoleMenuButton(roleId,addMenus,delMenusArr);
        }
        return batchMapRoleMenuButton(roleIds,addMenus);
    }

    /**
     * 根据RoleId 关联 菜单
     * @param roleId
     * @param addMenus
     * @param delMenus
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    ServiceResponse getMapRoleMenuButton(String roleId,
                                  List<HashMap<String, String>> addMenus, String[] delMenus)
            throws ServiceException {
        try {
            //关联菜单
            for(HashMap<String,String> menu : addMenus){
                String menuId = menu.get("menuId");
                String addButton = menu.get("addButtons");
                String delButton = menu.get("delButtons");
                List<SysRoleMenuButtonMapp> mapps = sysRoleMenuButtonMappDao.getRoleMenuButtonMapps(roleId, menuId);
                //若该菜单还未关联，先关联菜单
                if(null == mapps || mapps.isEmpty()){
                    SysRoleMenuButtonMapp mapp = SysRoleMenuButtonMapp.getInstance(roleId, menuId, null, "1");
                    mapp.setCreateTime(new Date());
                    sysRoleMenuButtonMappDao.insert(mapp);
                }
                //关联新的按钮
                String[] addButtons = StringUtilExt.split(addButton, ",");
                if(null != addButtons){
                    for(String buttonId : addButtons){
                        SysRoleMenuButtonMapp mapp = SysRoleMenuButtonMapp.getInstance(roleId, menuId, buttonId, "1");
                        mapp.setCreateTime(new Date());
                        sysRoleMenuButtonMappDao.insert(mapp);
                    }
                }
                //取消关联按钮
                String[] delButtons = StringUtilExt.split(delButton, ",");
                if(null != delButtons){
                    for(String buttonId : delButtons){
                         sysRoleMenuButtonMappDao.deleteRoleMenuButtonMapp(roleId, menuId, buttonId);
                    }
                }
            }
            //取消关联菜单
            if(null != delMenus){
                for(String menuId : delMenus){
                    sysRoleMenuButtonMappDao.deleteByRoleIdAndMenuId(roleId, menuId);
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 根据多个roleid批量关联菜单
     * @param roleIds
     * @param addMenus
     * @return
     * @throws ServiceException
     */
    @Transactional(rollbackFor = Exception.class)
    ServiceResponse batchMapRoleMenuButton(String[] roleIds,
                                       List<HashMap<String, String>> addMenus) throws ServiceException {
        try {
            for(String roleId : roleIds){
                //关联菜单
                for(HashMap<String,String> menu : addMenus){
                    String menuId = menu.get("menuId");
                    String addButtons = menu.get("addButtons");
                    List<SysRoleMenuButtonMapp> mapps = sysRoleMenuButtonMappDao.getRoleMenuButtonMapps(roleId, menuId);
                    //若该菜单还未关联，先关联菜单
                    if(null == mapps || mapps.isEmpty()){
                        SysRoleMenuButtonMapp mapp = SysRoleMenuButtonMapp.getInstance(roleId, menuId, null, "1");
                        mapp.setCreateTime(new Date());
                        sysRoleMenuButtonMappDao.insert(mapp);
                    }
                    List<String> relatedButtons = new ArrayList<String>();
                    for(SysRoleMenuButtonMapp mapp : mapps){
                        relatedButtons.add(mapp.getButtonId());
                    }
                    //关联按钮
                    String[] addButtonIds = StringUtilExt.split(addButtons, ",");
                    if(null != addButtonIds){
                        for(String buttonId : addButtonIds){
                            if(!relatedButtons.contains(buttonId)){
                                SysRoleMenuButtonMapp mapp = SysRoleMenuButtonMapp.getInstance(roleId, menuId, buttonId, "1");
                                mapp.setCreateTime(new Date());
                                sysRoleMenuButtonMappDao.insert(mapp);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return ServiceResponse.createSuccess();
    }
}