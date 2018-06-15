package com.esgov.jrw.sysmgrservice.service.authority.impl;

import com.esgov.jrw.sysmgrservice.common.execption.ServiceException;
import com.esgov.jrw.sysmgrservice.common.util.TimeUtil;
import com.esgov.jrw.sysmgrservice.common.util.UUIDUtil;
import com.esgov.jrw.sysmgrservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.sysmgrservice.common.dto.ServiceResponse;
import com.esgov.jrw.sysmgrservice.common.dto.ZTreeNode;
import com.esgov.jrw.sysmgrservice.dao.authority.SysMenuDao;
import com.esgov.jrw.sysmgrservice.entity.authority.SysMenu;
import com.esgov.jrw.sysmgrservice.entity.enums.StatusEnum;
import com.esgov.jrw.sysmgrservice.service.authority.SysMenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 菜单管理的服务实现类
 * Created by HZX on 2018/4/2.
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuDao sysMenuDao;

    @Override
    public ServiceResponse save(SysMenu sysMenu) {
        if (sysMenu == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        int isExist =sysMenuDao.isExistMenu(sysMenu); //判断菜单是否已存在
        if(isExist>0){
            return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }
        sysMenu.setCreateTime(TimeUtil.getCurrentDate());
        sysMenu.setIsUsed("1");
        sysMenu.setStatus(StatusEnum.NORMAL);
        sysMenu.setId(UUIDUtil.getUUID());
        int insertCode=sysMenuDao.insert(sysMenu); //插入
        if(insertCode<1){ //插入失败
            return ServiceResponse.createError();
        }
        return  ServiceResponse.createSuccess();
    }

    /**
     * 删除菜单
     * @param id 菜单id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse delete(String id) {
        //删除菜单
        int deleteCode=sysMenuDao.deleteById(id);
        if(deleteCode<1){
            return ServiceResponse.createError();
        }
        //TODO 删除菜单按钮关联关系
        //TODO 删除角色、菜单、按钮关联关系
        return  ServiceResponse.createSuccess();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse deleteAll(String[] delete) {
        Integer deleteCount = sysMenuDao.deleteBatchIds(Arrays.asList(delete));
        if (deleteCount<delete.length) {
            throw new ServiceException(ResponseCode.ERROR);
        }
        //TODO 删除菜单按钮关联关系
        //TODO 删除角色、菜单、按钮关联关系
        return  ServiceResponse.createSuccess();
    }

    /**
     * 更新菜单
     * @param sysMenu
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ServiceResponse update(SysMenu sysMenu) {
        if (sysMenu == null) { //保存实体为null
            return ServiceResponse.createByResponseCode(ResponseCode.PARAM_NULL);
        }
        int isExist =sysMenuDao.isExistMenu(sysMenu); //当前菜单是否存在
        if(isExist>0){
            return ServiceResponse.createByResponseCode(ResponseCode.EXIST);
        }
        Date date = TimeUtil.getCurrentDate();
        sysMenu.setUpdateTime(date);
        int updateCode = sysMenuDao.updateById(sysMenu); //根据id更新人口
        if (updateCode<1){
            return ServiceResponse.createError();
        }
        return ServiceResponse.createSuccess();
    }

    /**
     * 根据id获取菜单
     * @param id
     * @return
     */
    @Override
    public ServiceResponse get(String id) {
        SysMenu sysMenu = sysMenuDao.selectById(id);
        if(sysMenu == null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS, sysMenu);
    }

    /**
     * 分页查询
     * @param sysMenu
     * @param currentPage 当前页
     * @param pageSize 每页条数
     * @return
     */
    @Override
    public ServiceResponse queryPage(SysMenu sysMenu, int currentPage, int pageSize) {
        PageHelper.startPage(currentPage, pageSize);
        List<SysMenu> sysMenuList =sysMenuDao.queryPage(sysMenu);
        if(sysMenuList==null){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS, new PageInfo(sysMenuList));

    }

    /**
     * 获取机构树所有节点
     *
     * @return
     */
    @Override
    public ServiceResponse getTree(){
        List<Map<String, Object>> menuDaoTree = sysMenuDao.getTree();
        List<ZTreeNode> zTreeNodes= treeNodesWrapper(menuDaoTree);
        if (zTreeNodes.size()<0){
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        return ServiceResponse.createSuccessByData(zTreeNodes);
    }

    @Override
    public ServiceResponse getSubMenu(String parentId) {
        List<SysMenu> menuList = new ArrayList<>();
        menuList =getSubMenuList(parentId);
        if (menuList == null) {
            return ServiceResponse.createByResponseCode(ResponseCode.NOT_EXIST);
        }
        if (menuList.size()==1) {
            return ServiceResponse.createBySuccessMsgData("子菜单不存在",menuList);
        }
        return ServiceResponse.createSuccessByData(menuList);
    }

    private List<SysMenu> getSubMenuList(String parentId){
        List<SysMenu> menuList = new ArrayList<>();
        List<SysMenu> childMenuList = sysMenuDao.getSubMenu(parentId);
        if (childMenuList == null) {
            return menuList;
        }
        menuList.addAll(childMenuList);
        return menuList;
    }

    /**
     * 获取所有的菜单数据
     * @return
     */
    @Override
    public ServiceResponse getAllMenu() {
        List<SysMenu> allMenuList = sysMenuDao.getAllMenu();
        return ServiceResponse.createByResponseCodeData(ResponseCode.GET_SUCCESS, allMenuList);
    }

    /**
     * 树节点包装
     * @param menuDaoTree
     * @return
     */
    private List<ZTreeNode> treeNodesWrapper(List<Map<String, Object>> menuDaoTree){
        List<ZTreeNode> zTreeNodes=new ArrayList<>();
        if(null!=menuDaoTree && !menuDaoTree.isEmpty()){
            for(Map<String,Object> data: menuDaoTree){
                ZTreeNode node = ZTreeNode.getInstance((String)data.get("id"), (String)data.get("parent_id"), (String)data.get("name"));
                zTreeNodes.add(node);
            }
        }
        return zTreeNodes;
    }
}
