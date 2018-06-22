package com.esgov.jrw.jrwservice.service.authority.impl;

import com.esgov.jrw.jrwservice.common.execption.ServiceException;
import com.esgov.jrw.jrwservice.common.util.StringUtilExt;
import com.esgov.jrw.jrwservice.common.util.TimeUtil;
import com.esgov.jrw.jrwservice.common.util.UUIDUtil;
import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.dao.authority.SysParamDao;
import com.esgov.jrw.jrwservice.dao.authority.SysParamTypeDao;
import com.esgov.jrw.jrwservice.entity.authority.SysParam;
import com.esgov.jrw.jrwservice.entity.authority.SysParamType;
import com.esgov.jrw.jrwservice.entity.enums.StatusEnum;
import com.esgov.jrw.jrwservice.service.authority.SysParamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述: 通用参数配置
 *
 * @author Yangjinming
 * @create 2018-04-02 上午9:28
 */
@Service
public class SysParamServiceImpl implements SysParamService {
    @Autowired
    private SysParamDao sysParamDao;
    @Autowired
    private SysParamTypeDao sysParamTypeDao;
    /**
     * 根据ID查询参数字典数据
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public ServiceResponse<SysParam> getParamById(String id){
        if (StringUtilExt.isEmpty(id)) {
           return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,sysParamDao.selectById(id));
    }

    /**
     * 保存参数类型
     *
     * @param sysParamType
     * @return
     */
    @Override
    public ServiceResponse addParamType(SysParamType sysParamType) throws ServiceException {
        if (sysParamType == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        if(sysParamType.getCode()==null){
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        int isExist =sysParamTypeDao.checkByTypeCode(sysParamType);
        if(isExist>0){
            return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }
        sysParamType.setCreateTime(TimeUtil.getCurrentDate());
        sysParamType.setStatus(StatusEnum.NORMAL);
        sysParamType.setId(UUIDUtil.getUUID());
        int insertCode=sysParamTypeDao.insert(sysParamType);
        if(insertCode<1){
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 修改参数类型
     *
     * @param sysParamType
     * @return
     */
    @Override
    public ServiceResponse updateParamType(SysParamType sysParamType) throws ServiceException{
        if (sysParamType == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        sysParamType.setUpdateTime(TimeUtil.getCurrentDate());
        int updateCode = sysParamTypeDao.updateById(sysParamType);
        if (updateCode>0){
            return ServiceResponse.createSuccess();
        }
        return ServiceResponse.createError();
    }

    /**
     * 根据ID获取参数类型
     *
     * @param id
     * @return
     * @throws ServiceException
     */
    @Override
    public ServiceResponse getParamTypeById(String id) throws ServiceException{
        if(StringUtilExt.isEmpty(id)){
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        SysParamType sysParamType = sysParamTypeDao.selectById(id);
        if (sysParamType == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(sysParamType);
    }

    /**
     * 根据TypeID删除参数类型以及对应的参数
     *
     * @param typeId
     * @return
     * @throws ServiceException
     */
    @Override
    public ServiceResponse deleteParamType(String typeId) throws ServiceException{
        if(sysParamTypeDao.selectById(typeId)==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        List<SysParam> params = sysParamDao.getParamsByTypeId(typeId);
        for (SysParam param : params) {
            int deleteParamCode = sysParamDao.deleteById(param.getId());
            if(deleteParamCode<1)
            {
                throw new ServiceException(ResponseCode.ERROR);
            }
        }
        int deletParamTypeCode = sysParamTypeDao.deleteById(typeId);
        if(deletParamTypeCode<1)
        {
            throw new ServiceException(ResponseCode.ERROR);
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 保存参数字典
     *
     * @param sysParam
     * @param paramTypeId
     * @return
     */
    @Override
    public ServiceResponse saveParam(SysParam sysParam, String paramTypeId){
        if(sysParam==null){
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        SysParamType sysParamType = sysParamTypeDao.selectById(paramTypeId);
        sysParam.setParamTypeCode(sysParamType.getCode());
        int checkCode=sysParamDao.checkByCode(sysParam);
        if(checkCode>0){
            return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }
        sysParam.setParamTypeId(paramTypeId);
        sysParam.setCreateTime(TimeUtil.getCurrentDate());
        sysParam.setId(UUIDUtil.getUUID());
        int insertCode= sysParamDao.insert(sysParam);
        if(insertCode<1){
            return ServiceResponse.createError();
        }
        return  ServiceResponse.createSuccess();
    }

    /**
     * 修改参数字典
     *
     * @param sysParam
     * @param paramTypeId
     * @return
     */
    @Override
    public ServiceResponse updateParam(SysParam sysParam, String paramTypeId){
        if(sysParam==null){
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        SysParamType sysParamType = sysParamTypeDao.selectById(paramTypeId);
        if (sysParamType ==null ){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        sysParam.setParamTypeId(paramTypeId);
        sysParam.setParamTypeCode(sysParamType.getCode());
        sysParam.setUpdateTime(TimeUtil.getCurrentDate());
        int updateCode=sysParamDao.updateById(sysParam);
        if (updateCode<1){
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccess();
    }

    /**
     *
     * 删除参数字典
     *
     * @param paramId
     * @return
     */
    @Override
    public ServiceResponse deleteParam(String paramId){
        //删除机构
        int deleteCode=sysParamDao.deleteById(paramId);
        if(deleteCode<1){
            return ServiceResponse.createError();
        }
        return  ServiceResponse.createSuccess();
    }

    /**
     * 获取所有参数字典
     *
     * @return
     */
    @Override
    public ServiceResponse getAllSysParam(){
        List<SysParam> sysParamList = sysParamDao.getAllSysParam();
        if (sysParamList==null){
            return ServiceResponse.createByResponseCode(ResponseCode.GET_FAIL);
        }
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,sysParamList);
     }

    /**
     * 获取所有参数类型
     *
     * @return
     */
    @Override
    public ServiceResponse getAllSysParamTypes(){
         List<SysParamType> sysParamTypeList = sysParamTypeDao.getAllSysParamTypes();
         if (sysParamTypeList==null){
             return ServiceResponse.createByResponseCode(ResponseCode.GET_FAIL);
         }
         return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,sysParamTypeList);
     }

    /**
     * 分页查询参数类型
     *
     * @param sysParamType
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public ServiceResponse queryParamTypePage(SysParamType sysParamType,int currentPage,int pageSize){
         PageHelper.startPage(currentPage, pageSize);
         List<SysParamType> sysParamTypeList =sysParamTypeDao.queryPageBySelective(sysParamType);
         if(sysParamTypeList==null){
             return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
         }
         return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,new PageInfo(sysParamTypeList));
     }

    /**
     * 根据字典类型ID分页查询参数字典
     *
     * @param typeId
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public ServiceResponse queryParamPage(String typeId, int currentPage, int pageSize){
         PageHelper.startPage(currentPage,pageSize);
         List<SysParam> sysParamList =sysParamDao.queryPageBySelective(typeId);
         if(sysParamList==null){
             return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
         }
         return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,new PageInfo(sysParamList));
     }

    /**
     *
     * 根据参数类型code查询参数字典值
     *
     * @param paramTypeCode
     * @return
     */
    @Override
    public ServiceResponse getParamsByTypeCode(String paramTypeCode){
         List<SysParam> sysParamList =sysParamDao.getParamsByTypeCode(paramTypeCode);
         if(sysParamList==null){
             return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
         }
         return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,sysParamList);
     }

    /**
     * 根据参数类型Code和参数code查询对应参数信息
     *
     * @param paramTypeCode
     * @param code
     * @return
     */
    @Override
    public ServiceResponse getParamByTypeCodeAndCode(String paramTypeCode, String code){
         SysParam sysParam = sysParamDao.getParamByTypeCodeAndCode(paramTypeCode,code);
         if(sysParam==null){
             return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
         }
         return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,sysParam);
     }
}
