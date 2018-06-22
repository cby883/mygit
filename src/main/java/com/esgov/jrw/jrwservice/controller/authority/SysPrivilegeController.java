package com.esgov.jrw.jrwservice.controller.authority;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.service.authority.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created by HZX on 2018/4/2.
 */
@RestController
public class SysPrivilegeController {
    private static final String BASE_PATH="/sysmgr/service/privilege";
    @Autowired
    PrivilegeService privilegeService;

    @RequestMapping(value = BASE_PATH+"/menu",method = RequestMethod.POST)
    ServiceResponse getMenuPrivileges(@RequestBody HashMap<String,String> paramMap){
        return privilegeService.getMenuPrivileges(paramMap);
    }

}
