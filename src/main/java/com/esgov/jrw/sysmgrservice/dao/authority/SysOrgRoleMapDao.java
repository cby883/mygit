package com.esgov.jrw.sysmgrservice.dao.authority;

import com.esgov.jrw.sysmgrservice.entity.authority.SysOrg;
import com.esgov.jrw.sysmgrservice.entity.authority.SysOrgRoleMap;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yjm
 * @since 2018-04-10
 */
public interface SysOrgRoleMapDao extends BaseMapper<SysOrgRoleMap> {
    /**
     *
     * 通过机构id获取相关联的角色
     * @param orgId
     * @return
     */
     List<SysOrgRoleMap> getRelatedRoleId(String orgId);

    /**
     * 通过角色id获取相关联的机构
     * @param roleId
     * @return
     */
     List<SysOrgRoleMap> getRelatedOrgId(String roleId);

    /**
     *
     * 获取角色关联的机构
     * @param roleId
     * @return
     */
    List<SysOrg> getRelatedOrgs(String roleId);

    /**
     *
     * 通过角色id更改关联关系状态
     * @param roleId
     * @param used
     */
    int updateUsedStatusByRoleId(@Param("roleId") String roleId, @Param("used")  String used);

    /**
     *
     * 通过机构id更改关联关系状态
     * @param orgId
     * @param used
     */
    int updateUsedStatusByOrgId(@Param("orgId")String orgId,@Param("used")String used);

    /**
     *
     * 通过机构id删除机构角色关联关系
     * @param orgId
     */
     int deleteByOrgId(String orgId);

    /**
     *
     * 通过角色id删除机构角色关联关系
     * @param roleId
     */
     int deleteByRoleId(String roleId);

    /**
     *
     * 删除机构与角色的关联
     * @param roleId
     * @param orgId
     */
    int deleteByRoleIdAndOrgId(@Param("orgId")String orgId,@Param("roleId") String roleId);

    int deleteBatchByOrgId(String[] orgIds);
}
