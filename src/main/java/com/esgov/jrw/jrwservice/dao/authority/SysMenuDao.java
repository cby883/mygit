package com.esgov.jrw.jrwservice.dao.authority;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.jrwservice.entity.authority.SysMenu;

import java.util.List;
import java.util.Map;

/**
 * 菜单管理的dao
 * Created by HZX on 2018/4/2.
 */
public interface SysMenuDao extends BaseMapper<SysMenu> {
    List<SysMenu>  getMenuListByParentId(String menuId);

    /**
     * 通过具体参数分页查询
     * @param sysMenu 菜单实体
     * @return
     */
    List<SysMenu> queryPage(SysMenu sysMenu);

    /**
     * 判断菜单是否存在
     * 1.菜单不可重复;2.本身数据id除外;3.平台是同一个
     * @param sysMenu
     * @return
     */
    int isExistMenu(SysMenu sysMenu);

    /**
     * 获取所有树节点
     *
     * @return
     */
    List<Map<String,Object>> getTree();

    /**
     * 根据父id获取子菜单list
     * @param parentId
     * @return
     */
    List<SysMenu> getSubMenu(String parentId);

    /**
     * 获取所有菜单list
     * @return
     */
    List<SysMenu> getAllMenu();
}
