package com.esgov.jrw.jrwservice.service.authority;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;

import java.util.HashMap;

/**
 * 获取登陆用户的权限
 * Created by HZX on 2018/4/2.
 */
public interface PrivilegeService {
    ServiceResponse getMenuPrivileges(HashMap<String,String> paramMap);
}
