package com.esgov.jrw.jrwservice.dao.authority;

import com.esgov.jrw.jrwservice.entity.authority.SysRoleMenuButtonMapp;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author your name
 * @since 2018-04-17
 */
public interface SysRoleMenuButtonMappDao extends BaseMapper<SysRoleMenuButtonMapp> {
    /**
     *
     * 通过角色id更改关联信息的使用状态
     * @param roleId
     * @param used
     */
    void updateUsedStatusByRoleId(@Param("roleId") String roleId, @Param("used")  String used);

    /**
     * 获取角色菜单按钮
     * @param roleId
     * @param menuId
     * @return
     */
    List<SysRoleMenuButtonMapp> getRoleMenuButtonMapps(@Param("roleId")String roleId,@Param("menuId")String menuId);

    /**
     * 删除角色菜单关联
     *
     * @param roleId
     * @param menuId
     * @param buttonId
     */
    void deleteRoleMenuButtonMapp(@Param("roleId")String roleId, @Param("menuId")String menuId, @Param("buttonId")String buttonId);

    /**
     * 删除角色菜单关联
     *
     * @param roleId
     * @param menuId
     */
    void deleteByRoleIdAndMenuId(@Param("roleId")String roleId, @Param("menuId")String menuId);
}
