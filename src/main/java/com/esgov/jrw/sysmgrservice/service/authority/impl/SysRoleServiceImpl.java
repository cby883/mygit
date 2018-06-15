package com.esgov.jrw.sysmgrservice.service.authority.impl;

import com.esgov.jrw.sysmgrservice.common.execption.ServiceException;
import com.esgov.jrw.sysmgrservice.common.util.TimeUtil;
import com.esgov.jrw.sysmgrservice.common.util.UUIDUtil;
import com.esgov.jrw.sysmgrservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.dao.authority.*;
import com.esgov.jrw.sysmgrservice.entity.authority.SysRole;
import com.esgov.jrw.sysmgrservice.entity.enums.StatusEnum;
import com.esgov.jrw.sysmgrservice.service.authority.SysRoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author xiaohancheng
 * @create 2018-03-28 20:00
 * @desc 角色管理服务类
 **/
@Service
public class SysRoleServiceImpl implements SysRoleService {
    @Autowired
    private SysRoleDao sysRoleDao;
    @Autowired
    private SysOrgRoleMapDao sysOrgRoleMapDao;
    @Autowired
    private SysRoleUserMapDao sysRoleUserMapDao;
    @Autowired
    private SysRoleMenuButtonMappDao sysRoleMenuButtonMapDao;
    @Override
    public ServiceResponse save(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(TimeUtil.getCurrentDate());
        sysRole.setStatus(StatusEnum.NORMAL);
        sysRole.setId(UUIDUtil.getUUID());
        sysRoleDao.insert(sysRole);
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse delete(@PathVariable("id") String id) {
        sysRoleDao.deleteById(id);
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse deletAll(@RequestParam("delete[]") String[] delete) {
        for (int i = 0; i < delete.length; i++) {
            sysRoleDao.deleteById(delete[i]);
        }
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse update(@RequestBody SysRole sysRole) {
        sysRole.setUpdateTime(TimeUtil.getCurrentDate());
        sysRoleDao.updateById(sysRole);
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse get(@PathVariable("id") String id) {
        System.out.println(id);
        SysRole sysRole = sysRoleDao.selectById(id);
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS, sysRole);
    }

    @Override
    public ServiceResponse queryPage(SysRole sysRole, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SysRole> sysRoleList = sysRoleDao.queryPageBySelective(sysRole);
        if (sysRoleList == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(new PageInfo(sysRoleList));
    }

    @Override
    public ServiceResponse enableRole(String id) {
        try {
            //更改角色使用状态
            SysRole role = sysRoleDao.selectById(id);
            role.setIsUsed("1");
            sysRoleDao.updateById(role);
            //更改机构角色关联关系使用状态
            sysOrgRoleMapDao.updateUsedStatusByRoleId(id, "1");
            //更改角色用户关联关系使用状态
            sysRoleUserMapDao.updateUsedStatusByRoleId(id, "1");
            //更改角色、菜单按钮关联关系使用状态
            sysRoleMenuButtonMapDao.updateUsedStatusByRoleId(id, "1");
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse disableRole(String id) {
        try {
            //更改角色使用状态
            SysRole role = sysRoleDao.selectById(id);
            role.setIsUsed("0");
            sysRoleDao.updateById(role);
            //更改机构角色关联关系使用状态
            sysOrgRoleMapDao.updateUsedStatusByRoleId(id, "0");
            //更改角色用户关联关系使用状态
            sysRoleUserMapDao.updateUsedStatusByRoleId(id, "0");
            //更改角色、菜单按钮关联关系使用状态
            sysRoleMenuButtonMapDao.updateUsedStatusByRoleId(id, "0");
        } catch (Exception e) {
            throw new ServiceException(e);
        }
        return ServiceResponse.createSuccess();
    }
    @Override
    public ServiceResponse getOnlyRole(String name){
        SysRole sysRole = sysRoleDao.getOnlyRole(name);
        return ServiceResponse.createSuccessByData(sysRole);
    }
}
