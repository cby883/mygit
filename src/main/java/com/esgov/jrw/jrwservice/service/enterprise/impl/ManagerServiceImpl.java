package com.esgov.jrw.jrwservice.service.enterprise.impl;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.dao.enterprise.EnterpriseDao;
import com.esgov.jrw.jrwservice.entity.enterprise.Enterprise;
import com.esgov.jrw.jrwservice.service.enterprise.ManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ManagerServiceImpl implements ManagerService{
    private static final Logger logger=LoggerFactory.getLogger(ManagerServiceImpl.class);

    @Autowired
    private EnterpriseDao enterpriseDao;
    @Override
    public ServiceResponse updateStatus(String enterprise_id, String status) {
        return null;
    }

    @Override
    public ServiceResponse<List<Enterprise>> selectAll(String flag) {
        return null;
    }

    @Override
    public ServiceResponse<Enterprise> selectById(String enterprise_id) {
        return null;
    }
}
