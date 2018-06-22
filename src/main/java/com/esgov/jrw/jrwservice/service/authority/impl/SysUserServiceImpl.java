package com.esgov.jrw.jrwservice.service.authority.impl;


import com.esgov.jrw.jrwservice.common.util.TimeUtil;
import com.esgov.jrw.jrwservice.common.util.UUIDUtil;
import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.dao.authority.SysUserDao;
import com.esgov.jrw.jrwservice.dao.authority.SysRoleUserMapDao;
import com.esgov.jrw.jrwservice.entity.authority.SysUser;
import com.esgov.jrw.jrwservice.entity.enums.StatusEnum;
import com.esgov.jrw.jrwservice.service.authority.SysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by HZX on 2018/3/26.
 * 用户管理的实现类
 */

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    private SysUserDao sysUserDao;

    @Autowired
    private SysRoleUserMapDao sysRoleUserMapDao;

    @Override
    public ServiceResponse save(SysUser sysUser) {
        Date date = TimeUtil.getCurrentDate();
        sysUser.setCreateTime(date);
        sysUser.setUpdateTime(date);
        sysUser.setStatus(StatusEnum.NORMAL);
        sysUser.setId(UUIDUtil.getUUID());
        if (sysUser == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        int isExist =sysUserDao.isExistUser(sysUser);
        if(isExist>0){
            return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }

        int insertCode=sysUserDao.insert(sysUser);
        if(insertCode<1){
            return ServiceResponse.createError();
        }
        return  ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse delete(String id) {
        SysUser user = sysUserDao.selectById(id);
        user.setStatus(StatusEnum.DELETE);
        sysUserDao.updateById(user);
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse deletAll(String[] delete) {
        for(int i=0;i<delete.length;i++){
            sysUserDao.updateStatusById(delete[i]);
        }
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse update(SysUser sysUser) {
        if (sysUser == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        int isExist =sysUserDao.isExistUser(sysUser);
        if(isExist>0){
            return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }
        Date date = TimeUtil.getCurrentDate();
        sysUser.setUpdateTime(date);
        int updateCode = sysUserDao.updateById(sysUser);
        if (updateCode<1){
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse get(String id){
        SysUser user = sysUserDao.selectById(id);
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,user);
   }

    @Override
    public ServiceResponse queryPage(SysUser sysUser, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SysUser> sysUserList =sysUserDao.queryPage(sysUser);
        if(sysUserList==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(new PageInfo(sysUserList));
    }

    @Override
    public ServiceResponse<SysUser> getUserByLoginName(String loginName) {
        SysUser user = sysUserDao.getUserByLoginName(loginName);
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,user);
    }

    @Override
    public ServiceResponse enabledUser(String id) {
        //更改用户使用状态
        SysUser user = sysUserDao.selectById(id);
        user.setIsUsed("1");
        sysUserDao.updateById(user);
        //更改用户角色关联信息使用状态
        sysRoleUserMapDao.updateUsedStatusByUserId(id,"1");
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse disabledUser(String id) {
        //更改用户使用状态
        SysUser user = sysUserDao.selectById(id);
        user.setIsUsed("0");
        sysUserDao.updateById(user);
        //更改用户角色关联信息使用状态
        sysRoleUserMapDao.updateUsedStatusByUserId(id,"0");
        return ServiceResponse.createSuccess();
    }
}
