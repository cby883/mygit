package com.esgov.jrw.jrwservice.service.authority.impl;


import com.esgov.jrw.jrwservice.common.execption.ServiceException;
import com.esgov.jrw.jrwservice.common.util.StringUtilExt;
import com.esgov.jrw.jrwservice.common.util.TimeUtil;
import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import com.esgov.jrw.jrwservice.common.dto.ZTreeNode;
import com.esgov.jrw.jrwservice.dao.authority.SysOrgDao;
import com.esgov.jrw.jrwservice.dao.authority.SysOrgRoleMapDao;
import com.esgov.jrw.jrwservice.entity.authority.SysOrg;
import com.esgov.jrw.jrwservice.entity.enums.StatusEnum;
import com.esgov.jrw.jrwservice.service.authority.SysOrgService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 描述: 机构管理服务
 *
 * @author Yangjinming
 * @create 2018/3/27 8:21
 */
@Service
public class SysOrgServiceImpl implements SysOrgService {
    @Autowired
    SysOrgDao sysOrgDao;
    @Autowired
    SysOrgRoleMapDao sysOrgRoleMapDao;

    /**
     * 保存
     *
     * @param sysOrg
     * @return
     */
    @Override
    public ServiceResponse save(SysOrg sysOrg) throws ServiceException {
        if (sysOrg == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        int isExist =sysOrgDao.checkOrgName(sysOrg);
        if(isExist>0){
         return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }
        sysOrg.setCreateTime(TimeUtil.getCurrentDate());
        sysOrg.setIsUsed("1");
        sysOrg.setStatus(StatusEnum.NORMAL);
        //sysOrg.setId(UUIDUtil.getUUID());
        int insertCode=sysOrgDao.insert(sysOrg);
        if(insertCode<1){
            return ServiceResponse.createError();
        }
        return  ServiceResponse.createSuccess();
    }

    /**
     * 删除
     *
     * @param id 机构id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse delete(String id){
        //删除机构
        int deleteCode=sysOrgDao.deleteById(id);
        if(deleteCode<1){
            throw new ServiceException(ResponseCode.ERROR);
        }
        //删除机构角色关联关系
        int deleteRoleCode = sysOrgRoleMapDao.deleteByOrgId(id);

        return  ServiceResponse.createSuccess();
    }

    /**
     * 批量删除
     *
     * @param delete id数组
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse deleteAll(String[] delete) throws ServiceException{
        //批量删除机构
        int deleteOrgBatch = sysOrgDao.deleteBatchIds(Arrays.asList(delete));
        if(deleteOrgBatch<delete.length){
            throw new ServiceException(ResponseCode.ERROR);
        }
        // 批量删除机构角色关系
        int deleteRoleBatch = sysOrgRoleMapDao.deleteBatchByOrgId(delete);

        return ServiceResponse.createSuccess();
    }

    /**
     * 更新
     *
     * @param sysOrg
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse update(SysOrg sysOrg) throws ServiceException{
        if (sysOrg == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        sysOrg.setUpdateTime(TimeUtil.getCurrentDate());
        int updateCode = sysOrgDao.updateById(sysOrg);
        if (updateCode<1){
            throw new ServiceException(ResponseCode.ERROR);
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 通过机构id获取机构信息
     *
     * @param id
     * @return
     */
    @Override
    public ServiceResponse get(String id){
        SysOrg sysOrg = sysOrgDao.selectById(id);
        if(sysOrg == null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,sysOrg);
    }

    /**
     * 分页查询
     *
     * @param sysOrg 查询条件
     * @param currentPage 当前页
     * @param pageSize 页码
     * @return
     */
    @Override
    public ServiceResponse queryPage(SysOrg sysOrg,int currentPage, int pageSize){
        PageHelper.startPage(currentPage, pageSize);
        List<SysOrg> sysOrgList =sysOrgDao.queryPageBySelective(sysOrg);
        if(sysOrgList==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS,new PageInfo(sysOrgList));
    }

    /**
     * 获取机构树所有节点
     *
     * @return
     */
    @Override
    public ServiceResponse getTree(){
        List<Map<String, Object>> orgDaoTree = sysOrgDao.getTree();
        List<ZTreeNode> zTreeNodes= treeNodesWrapper(orgDaoTree);
        if (zTreeNodes.size()<0){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(zTreeNodes);
    }
    /**
     * 查询子机构列表
     *
     * @param id
     * @return
     */
    @Override
    public ServiceResponse orgChildList(String id){
        List<SysOrg> orgList = new ArrayList<>();
        orgList =getOrgChildList(id);
        if (orgList == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        if (orgList.size()==1) {
            return ServiceResponse.createBySuccessMsgData("子机构不存在",orgList);
        }
        return ServiceResponse.createSuccessByData(orgList);
    }

    /**
     * 获取机构的拥有的权力
     *
     * @param userDataAuthority unlimit currentDepartment childrenDepartment
     * @param id
     * @return
     */
    @Override
    public ServiceResponse getAuthorityTree(String userDataAuthority,String id){
        List<ZTreeNode> tree = new ArrayList<>();
        if (StringUtilExt.equals("unlimit",userDataAuthority)) {
            //获得所有机构
            tree = treeNodesWrapper(sysOrgDao.getTree());
        }
        else if(StringUtilExt.equals("currentDepartment",userDataAuthority)){
            //获得当前用户所在机构
            SysOrg sysOrg = sysOrgDao.selectById(id);
            tree.add(ZTreeNode.getInstance(sysOrg.getId(),null,sysOrg.getName()));
        }else if(StringUtilExt.equals("childrenDepartment",userDataAuthority) ){
            //获得当前用户所在机构及其子机构
            List<SysOrg> orgChildList = getOrgChildList(id);
            for (SysOrg sysOrg : orgChildList) {
                tree.add(ZTreeNode.getInstance(sysOrg.getId(),sysOrg.getParentId(),sysOrg.getName()));
            }
        }
        return ServiceResponse.createSuccessByData(tree);
    }

    /**
     * 启用
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse enableOrg(String id){
        SysOrg sysOrg= sysOrgDao.selectById(id);
        if(sysOrg==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        sysOrg.setIsUsed("1");
        int updateOrgCode = sysOrgDao.updateById(sysOrg);
        if (updateOrgCode<1) {
            throw new ServiceException(ResponseCode.ERROR);
         }
         sysOrgRoleMapDao.updateUsedStatusByOrgId(id,"1");
        return ServiceResponse.createSuccess();
    }

    /**
     * 禁用
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse disableOrg(String id){
        SysOrg sysOrg= sysOrgDao.selectById(id);
        if(sysOrg==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        sysOrg.setIsUsed("0");
        int updateOrgCode = sysOrgDao.updateById(sysOrg);
        if (updateOrgCode<1) {
            throw new ServiceException(ResponseCode.ERROR);
        }
        sysOrgRoleMapDao.updateUsedStatusByOrgId(id,"0");
        return ServiceResponse.createSuccess();
    }

    /**
     * 树节点包装
     * @param orgDaoTree
     * @return
     */
    private List<ZTreeNode> treeNodesWrapper(List<Map<String, Object>> orgDaoTree){
        List<ZTreeNode> zTreeNodes=new ArrayList<>();
        if(null!=orgDaoTree && !orgDaoTree.isEmpty()){
            for(Map<String,Object> data: orgDaoTree){
                ZTreeNode node = ZTreeNode.getInstance((String)data.get("id"), (String)data.get("parent_id"), (String)data.get("name"));
                zTreeNodes.add(node);
            }
        }
        return zTreeNodes;
    }

    /**
     * 获得子树
     * @param orgId
     * @return
     */
    private List<SysOrg> getOrgChildList(String orgId){
        List<SysOrg> orgList = new ArrayList<>();
        SysOrg sysOrg = sysOrgDao.selectById(orgId);
        if (sysOrg == null) {
            return null;
        }
        orgList.add(sysOrg);
        List<SysOrg> childOrgList = sysOrgDao.getChildOrg(orgId);
        if (childOrgList == null) {
            return orgList;
        }
        orgList.addAll(childOrgList);
        return orgList;
    }
}
