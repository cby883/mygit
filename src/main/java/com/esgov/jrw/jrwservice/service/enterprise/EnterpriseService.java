package com.esgov.jrw.jrwservice.service.enterprise;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.entity.enterprise.Enterprise;
import com.esgov.jrw.jrwservice.entity.enterprise.SysUser;
import org.springframework.stereotype.Service;

/*
   门户网会员服务层接口
 */
@Service
public interface EnterpriseService {
    //判断是否已存在注册过的社会信用代码
    public ServiceResponse<Enterprise> hasRegistry(String creditCode);
    //页面注册
    public ServiceResponse registry(String pictureCode,String validateCode,Enterprise  enterprise,SysUser sys_user);
    //通过手机号登录
    public ServiceResponse loginByMobile(String mobile,String validateCode);
    //通过账号登录
    public ServiceResponse loginByUser(String creditCode,String password);
    //通过手机验证码方式找回密码
    public ServiceResponse resetPwd(String mobile,String newPassword,String validate);
    //通过企业id查看企业信息
    public ServiceResponse<Enterprise> searchById(String enterprise_id);
    //用户修改密码
    public ServiceResponse modifyPwd(String creditCode,String oldPassword,String newPassword);
    //更新企业信息
    public ServiceResponse updateEnterprise(Enterprise enterprise);


}
