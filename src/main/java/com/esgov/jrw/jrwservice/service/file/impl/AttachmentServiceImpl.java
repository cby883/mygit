package com.esgov.jrw.jrwservice.service.file.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.dao.file.AttachmentDao;
import com.esgov.jrw.jrwservice.entity.file.Attachment;
import com.esgov.jrw.jrwservice.service.file.AttachmentService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    @Autowired
    private AttachmentDao attachmentDao;

    @Override
    public ServiceResponse save(Attachment entity) {
        try {
            attachmentDao.insert(entity);
        } catch (Exception e) {
            logger.error(e.toString(),e.fillInStackTrace());
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse delete(String id) {
        try {
            attachmentDao.deleteById(id);
        } catch (Exception e) {
            logger.error(e.toString(),e.fillInStackTrace());
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccess();
    }

    @Override
    public ServiceResponse findByTarget(String targetType, String targetId, String targetFlag) {

        Wrapper<Attachment> wp = new EntityWrapper<Attachment>() ;
        wp.eq("target_type",targetType);
        wp.eq("target_id",targetId);
        if(StringUtils.isNotBlank(targetFlag)){
            wp.eq("target_flag",targetFlag);
        }
        List<Attachment> attachmentList = null ;
        try {
            attachmentList = attachmentDao.selectList(wp);
        } catch (Exception e) {
            logger.error(e.toString(),e.fillInStackTrace());
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccessByData(attachmentList);
    }
}
