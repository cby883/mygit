package com.esgov.jrw.jrwservice.service.authority;

import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;

/**
 * 描述:
 *
 * @author Yjm
 * @create 2018-04-10 上午11:47
 */
public interface SysOrgRoleMapService {
    ServiceResponse getRelatedRoleId(String orgId);

    ServiceResponse mapOrgRole(String orgId, String addRoleIds, String delRoleIds);

    ServiceResponse getRelatedOrgId(String roleId);

    ServiceResponse mapRoleOrg(String roleId, String addOrgIds, String delOrgIds);
}
