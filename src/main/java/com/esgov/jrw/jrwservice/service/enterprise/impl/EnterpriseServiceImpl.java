package com.esgov.jrw.jrwservice.service.enterprise.impl;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.dao.enterprise.EnterpriseDao;
import com.esgov.jrw.jrwservice.dao.enterprise.SysUserDao;
import com.esgov.jrw.jrwservice.entity.enterprise.Enterprise;
import com.esgov.jrw.jrwservice.entity.enterprise.SysUser;
import com.esgov.jrw.jrwservice.service.enterprise.EnterpriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


public class EnterpriseServiceImpl implements EnterpriseService {
    private static final Logger logger =LoggerFactory.getLogger(EnterpriseServiceImpl.class);

    @Autowired
    private EnterpriseDao enterpriseDao;
    @Autowired
    private SysUserDao sysUserDao;
    @Override
    public ServiceResponse<Enterprise> hasRegistry(String creditCode) {
        return null;
    }

    @Override
    public ServiceResponse registry(String pictureCode, String validateCode, Enterprise enterprise, SysUser sys_user) {
        return null;
    }

    @Override
    public ServiceResponse loginByMobile(String mobile, String validateCode) {
        return null;
    }

    @Override
    public ServiceResponse loginByUser(String creditCode, String password) {
        return null;
    }

    @Override
    public ServiceResponse resetPwd(String mobile, String newPassword, String validate) {
        return null;
    }

    @Override
    public ServiceResponse<Enterprise> searchById(String enterprise_id) {
        return null;
    }

    @Override
    public ServiceResponse modifyPwd(String creditCode, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public ServiceResponse updateEnterprise(Enterprise enterprise) {
        return null;
    }
}
