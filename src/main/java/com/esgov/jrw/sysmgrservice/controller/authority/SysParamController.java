package com.esgov.jrw.sysmgrservice.controller.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.common.execption.ServiceException;
import com.esgov.jrw.sysmgrservice.entity.authority.SysParam;
import com.esgov.jrw.sysmgrservice.entity.authority.SysParamType;
import com.esgov.jrw.sysmgrservice.service.authority.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 描述: 通用参数配置service
 *
 * @author Yangjinming
 * @create 2018-04-02 下午5:35
 */
@RestController
public class SysParamController {
    private static final String BASE_PATH_PARAMS="/sysmgr/service/sysparam/params";
    private static final String BASE_PATH_PARAMS_TYPE="/sysmgr/service/sysparam/paramstype";

    @Autowired
    SysParamService sysParamService;

    @GetMapping(value = BASE_PATH_PARAMS+"/{id}")
    public ServiceResponse getParamById(@PathVariable("id")String id){
        return sysParamService.getParamById(id);
    }

    @PostMapping(value = BASE_PATH_PARAMS_TYPE)
    public ServiceResponse addParamType(@RequestBody SysParamType sysParamType) throws ServiceException {
        return sysParamService.addParamType(sysParamType);
    }

    @PutMapping(value = BASE_PATH_PARAMS_TYPE)
    public ServiceResponse updateParamType(@RequestBody SysParamType sysParamType) throws ServiceException{
        return sysParamService.updateParamType(sysParamType);
    }

    @GetMapping(value = BASE_PATH_PARAMS_TYPE+"/{id}")
    public ServiceResponse getParamTypeById(@PathVariable("id") String id) throws ServiceException{
        return sysParamService.getParamTypeById(id);

    }

    @DeleteMapping(value = BASE_PATH_PARAMS_TYPE+"/{typeId}")
    public  ServiceResponse deleteParamType(@PathVariable("typeId") String typeId) throws ServiceException{
        return sysParamService.deleteParamType(typeId);

    }

    @PostMapping(value = BASE_PATH_PARAMS+"/{paramTypeId}")
    public  ServiceResponse saveParam(@RequestBody SysParam sysParam,@PathVariable("paramTypeId") String paramTypeId){
        return sysParamService.saveParam(sysParam,paramTypeId);

    }

    @PutMapping(value = BASE_PATH_PARAMS+"/{paramTypeId}")
    public  ServiceResponse updateParam(@RequestBody SysParam sysParam,@PathVariable("paramTypeId")String paramTypeId){
        return sysParamService.updateParam(sysParam,paramTypeId);

    }

    @DeleteMapping(value = BASE_PATH_PARAMS+"/{paramId}")
    public ServiceResponse deleteParam(@PathVariable("paramId") String paramId){
        return sysParamService.deleteParam(paramId);

    }

    @GetMapping(value = BASE_PATH_PARAMS)
    public ServiceResponse getAllSysParam(){
        return sysParamService.getAllSysParam();

    }

    @GetMapping(value = BASE_PATH_PARAMS_TYPE)
    public ServiceResponse getAllSysParamTypes(){
        return sysParamService.getAllSysParamTypes();

    }

    @PostMapping(value = BASE_PATH_PARAMS_TYPE+"/{currentPage}/{pageSize}")
    public ServiceResponse queryParamTypePage(@RequestBody SysParamType sysParamType, @PathVariable("currentPage") int currentPage, @PathVariable("pageSize") int pageSize){
        return sysParamService.queryParamTypePage(sysParamType,currentPage,pageSize);

    }

    @PostMapping(value = BASE_PATH_PARAMS+"/type/{currentPage}/{pageSize}")
    public ServiceResponse queryParamPage(@RequestParam("typeId") String typeId,  @PathVariable("currentPage") int currentPage, @PathVariable("pageSize")int pageSize ){
        return sysParamService.queryParamPage(typeId,currentPage,pageSize);

    }

    @GetMapping(value = BASE_PATH_PARAMS+"/typecode/{paramTypeCode}")
    public ServiceResponse getParamsByTypeCode(@PathVariable("paramTypeCode") String paramTypeCode){
        return sysParamService.getParamsByTypeCode(paramTypeCode);

    }

    @GetMapping(value = BASE_PATH_PARAMS+"/typecode/{paramTypeCode}/code/{code}")
    public ServiceResponse getParamByTypeCodeAndCode(@PathVariable("paramTypeCode") String paramTypeCode,@PathVariable("code")  String code){
        return sysParamService.getParamByTypeCodeAndCode(paramTypeCode,code);
    }
}
