package com.esgov.jrw.jrwservice.service.file;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.entity.file.Attachment;

public interface AttachmentService {
    public ServiceResponse save(Attachment entity);

    public ServiceResponse delete(String id);

    public ServiceResponse findByTarget(String targetType, String targetId, String targetFlag);

    public ServiceResponse get(String id);
}
