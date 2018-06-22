package com.esgov.jrw.jrwservice.service.authority;


import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.common.execption.ServiceException;
import com.esgov.jrw.jrwservice.entity.authority.SysOrg;

/**
 * 描述: 机构管理service
 *
 * @author Yangjinming
 * @create 2018/3/26 22:55
 */
public interface SysOrgService {
    ServiceResponse save(SysOrg SysOrg) throws ServiceException;
    ServiceResponse delete(String id);
    ServiceResponse deleteAll(String[] delete);
    ServiceResponse update(SysOrg sysOrg);
    ServiceResponse get(String id);
    ServiceResponse queryPage(SysOrg sysOrg,  int currentPage,int pageSize);
    ServiceResponse getTree();
    ServiceResponse orgChildList( String id);
    ServiceResponse getAuthorityTree( String userDataAuthority, String id);
    ServiceResponse enableOrg( String id);
    ServiceResponse disableOrg( String id);
}
