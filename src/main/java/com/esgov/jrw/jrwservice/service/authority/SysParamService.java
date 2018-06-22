package com.esgov.jrw.jrwservice.service.authority;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.common.execption.ServiceException;
import com.esgov.jrw.jrwservice.entity.authority.SysParam;
import com.esgov.jrw.jrwservice.entity.authority.SysParamType;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018-04-02 上午9:28
 */
public interface SysParamService {
    ServiceResponse<SysParam> getParamById(String id);

    ServiceResponse addParamType(SysParamType sysParamType) throws ServiceException;

    ServiceResponse updateParamType(SysParamType sysParamType) throws ServiceException;

    ServiceResponse getParamTypeById(String id) throws ServiceException;

    ServiceResponse deleteParamType(String typeId) throws ServiceException;

    ServiceResponse saveParam(SysParam sysParam, String paramTypeId);

    ServiceResponse updateParam(SysParam sysParam, String paramTypeId);

    ServiceResponse deleteParam(String paramId);

    ServiceResponse getAllSysParam();

    ServiceResponse getAllSysParamTypes();

    ServiceResponse queryParamTypePage(SysParamType sysParamType, int currentPage, int pageSize);

    ServiceResponse queryParamPage(String typeId, int currentPage, int pageSize);

    ServiceResponse getParamsByTypeCode(String paramTypeCode);

    ServiceResponse getParamByTypeCodeAndCode(String paramTypeCode, String code);
}
