package com.esgov.jrw.jrwservice.service.enterprise;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.entity.enterprise.Enterprise;
import org.springframework.stereotype.Service;

import java.util.List;


/*
    后台管理员服务接口
 */
@Service
public interface ManagerService {
    //更新企业状态
    public ServiceResponse updateStatus(String enterprise_id,String status);
    //分页查询企业信息
    public ServiceResponse<List<Enterprise>> selectAll(String flag);
    //通过Id查询企业信息
    public ServiceResponse<Enterprise> selectById(String enterprise_id);

}
