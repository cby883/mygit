package com.esgov.jrw.sysmgrservice.service.authority;

import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.common.execption.ServiceException;

import java.util.HashMap;
import java.util.List;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018-04-17 下午3:39
 */
public interface SysRoleMenuButtonMappService {
    ServiceResponse getRoleMenuButtonMapps(String roleId);

    ServiceResponse mapRoleMenuButton(String roleId, List<HashMap<String, String>> addMenus, String delMenus) throws ServiceException;
}
