package com.esgov.jrw.sysmgrservice.dao.authority;

import org.apache.ibatis.annotations.Param;

/**
 * Created by HZX on 2018/3/30.
 */
public interface SysRoleUserMapDao {
    /**
     * 根据id逻辑删除用户
     *
     * @param userId 用户id
     * @param used   用户角色使用状态
     */
    void updateUsedStatusByUserId(@Param("id") String userId, @Param("used") String used);

    /**
     * 通过角色id更改关联信息使用状态
     * @param roleId 角色id
     * @param used 用户角色使用状态
     */
    void updateUsedStatusByRoleId(@Param("roleId") String roleId,@Param("used") String used);
}
