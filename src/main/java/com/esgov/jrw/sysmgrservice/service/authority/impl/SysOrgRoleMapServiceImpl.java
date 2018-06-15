package com.esgov.jrw.sysmgrservice.service.authority.impl;

import com.esgov.jrw.sysmgrservice.common.util.StringUtilExt;
import com.esgov.jrw.sysmgrservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.dao.authority.SysOrgRoleMapDao;
import com.esgov.jrw.sysmgrservice.entity.authority.SysOrgRoleMap;
import com.esgov.jrw.sysmgrservice.entity.enums.StatusEnum;
import com.esgov.jrw.sysmgrservice.service.authority.SysOrgRoleMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 机构角色service
 *
 * @author Yjm
 * @create 2018-04-09 下午3:59
 */
@Service
public class SysOrgRoleMapServiceImpl implements SysOrgRoleMapService {
    @Autowired
    SysOrgRoleMapDao sysOrgRoleMapDao;

    /**
     *
     * 通过机构id获取相关联的角色
     *
     * @param orgId
     * @return
     */
    @Override
    public ServiceResponse getRelatedRoleId(String orgId){
        List<SysOrgRoleMap> roleMaps = sysOrgRoleMapDao.getRelatedRoleId(orgId);
        if (roleMaps==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(roleMaps);
    }

    /**
     * 根据机构Id批量关联角色（新增或删除）
     *
     * @param orgId
     * @param addRoleIds
     * @param delRoleIds
     * @return
     */
    @Override
    public ServiceResponse mapOrgRole(String orgId, String addRoleIds, String delRoleIds){
        String[] orgIds = StringUtilExt.split(orgId, ",");
        String[] addRoleIdArrays = StringUtilExt.split(addRoleIds, ",");
        String[] delRoleIdArrays = StringUtilExt.split(delRoleIds, ",");
        if (orgIds.length==1){
            return getMapOrgRole(orgId,addRoleIdArrays,delRoleIdArrays);
        }
        return getBatchMapOrgRole(orgIds,addRoleIdArrays);
    }

    /**
     * 对一个机构
     * 根据机构Id批量关联角色（新增或删除）
     * @param orgId
     * @param addRoleIdArrays
     * @param delRoleIdArrays
     * @return
     */
    private ServiceResponse getMapOrgRole(String orgId,String[] addRoleIdArrays,String[] delRoleIdArrays){
        //新增
        if (addRoleIdArrays!=null) {
            SysOrgRoleMap sysOrgRoleMap;
            for (String addRoleId : addRoleIdArrays) {
                sysOrgRoleMap = new SysOrgRoleMap();
                sysOrgRoleMap.setOrgId(orgId);
                sysOrgRoleMap.setRoleId(addRoleId);
                sysOrgRoleMap.setStatus(StatusEnum.NORMAL);
                SysOrgRoleMap isExist = sysOrgRoleMapDao.selectOne(sysOrgRoleMap);
                if (isExist == null){
                     sysOrgRoleMapDao.insert(sysOrgRoleMap);
                }
            }
        }
        if (delRoleIdArrays != null) {
            for (String delRoleId : delRoleIdArrays) {
                sysOrgRoleMapDao.deleteByRoleIdAndOrgId(orgId,delRoleId);
            }
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 批量对多个机构
     * 根据机构Id批量新增关联角色
     * @param orgIds
     * @param roleIds
     * @return
     */
    private ServiceResponse getBatchMapOrgRole(String[] orgIds,String[] roleIds){
        for (String orgId : orgIds) {
            //获取机构关联的角色
            List<SysOrgRoleMap> sysOrgRoleMapList = sysOrgRoleMapDao.getRelatedRoleId(orgId);
            ArrayList<String> relatedRoles = new ArrayList<>();
            for (SysOrgRoleMap sysOrgRoleMap : sysOrgRoleMapList) {
                relatedRoles.add(sysOrgRoleMap.getRoleId());
            }
            //关联新的角色
            for (String roleId : roleIds) {
                if(!relatedRoles.contains(roleId)){
                    SysOrgRoleMap sysOrgRoleMap =new SysOrgRoleMap();
                    sysOrgRoleMap.setOrgId(orgId);
                    sysOrgRoleMap.setRoleId(roleId);
                    sysOrgRoleMap.setStatus(StatusEnum.NORMAL);
                    sysOrgRoleMapDao.insert(sysOrgRoleMap);
                }
            }
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 根据角色Id获取相关联的机构
     *
     * @param roleId
     * @return
     */
    @Override
    public ServiceResponse getRelatedOrgId(String roleId){
        List<SysOrgRoleMap> orgMaps = sysOrgRoleMapDao.getRelatedOrgId(roleId);
        if (orgMaps==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(orgMaps);
    }

    /**
     * 根据角色Id批量关联机构（新增或删除）
     *
     * @param roleId
     * @param addOrgIds
     * @param delOrgIds
     * @return
     */
    @Override
    public ServiceResponse mapRoleOrg(String roleId, String addOrgIds, String delOrgIds){
        String[] roleIds = StringUtilExt.split(roleId, ",");
        String[] addOrgIdArrays = StringUtilExt.split(addOrgIds, ",");
        String[] delOrgIdArrays = StringUtilExt.split(delOrgIds, ",");
        if (roleIds.length==1){
            return getMapRoleOrg(roleId,addOrgIdArrays,delOrgIdArrays);
        }
        return getBatchMapRoleOrg(roleIds,addOrgIdArrays);
    }

    private ServiceResponse getMapRoleOrg(String roleId, String[] addOrgIdArrays, String[] delOrgIdArrays) {
        //新增
        if (addOrgIdArrays!=null) {
            SysOrgRoleMap sysOrgRoleMap;
            for (String addOrgId : addOrgIdArrays) {
                sysOrgRoleMap = new SysOrgRoleMap();
                sysOrgRoleMap.setRoleId(roleId);
                sysOrgRoleMap.setOrgId(addOrgId);
                sysOrgRoleMap.setStatus(StatusEnum.NORMAL);
                SysOrgRoleMap isExist = sysOrgRoleMapDao.selectOne(sysOrgRoleMap);
                if (isExist == null){
                    sysOrgRoleMap.setUsed("1");
                    sysOrgRoleMapDao.insert(sysOrgRoleMap);
                }
            }
        }
        if (delOrgIdArrays != null) {
            for (String delOrgId : delOrgIdArrays) {
                sysOrgRoleMapDao.deleteByRoleIdAndOrgId(delOrgId,roleId);
            }
        }

        return ServiceResponse.createSuccess();
    }

    private ServiceResponse getBatchMapRoleOrg(String[] roleIds, String[] orgIds) {
        for (String roleId : roleIds) {
            //获取机构关联的角色
            List<SysOrgRoleMap> sysOrgRoleMapList = sysOrgRoleMapDao.getRelatedOrgId(roleId);
            ArrayList<String> relatedOrgs = new ArrayList<>();
            for (SysOrgRoleMap sysOrgRoleMap : sysOrgRoleMapList) {
                relatedOrgs.add(sysOrgRoleMap.getOrgId());
            }
            //关联新的角色
            for (String orgId : orgIds) {
                if(!relatedOrgs.contains(orgId)){
                    SysOrgRoleMap sysOrgRoleMap =new SysOrgRoleMap();
                    sysOrgRoleMap.setOrgId(orgId);
                    sysOrgRoleMap.setRoleId(roleId);
                    sysOrgRoleMap.setStatus(StatusEnum.NORMAL);
                    sysOrgRoleMap.setUsed("1");
                    sysOrgRoleMapDao.insert(sysOrgRoleMap);
                }
            }
        }
        return ServiceResponse.createSuccess();
    }



}
