package com.esgov.jrw.sysmgrservice.dao.authority;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.sysmgrservice.entity.authority.SysRole;

import java.util.List;

/**
 * @author xiaohancheng
 * @create 2018-03-28 20:23
 * @desc
 **/
public interface SysRoleDao  extends BaseMapper<SysRole> {
    List<SysRole> queryPageBySelective(SysRole sysRole);
    SysRole getOnlyRole(String name);
}
