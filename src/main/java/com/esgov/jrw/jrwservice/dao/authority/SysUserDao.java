package com.esgov.jrw.jrwservice.dao.authority;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.jrwservice.entity.authority.SysUser;

import java.util.List;

/**
 * Created by HZX on 2018/3/28.
 * 用户管理 自定义dao接口
 */
public interface SysUserDao extends BaseMapper<SysUser> {
    /**
     * 分页查询
     * @return
     */
    List<SysUser> queryPage(SysUser sysUser);

    /**
     *
     * 通过登录名获取user
     * @param loginName
     * @return
     */
    SysUser getUserByLoginName(String loginName);

    /**
     * 根据id逻辑删除用户
     * @param id
     */
    void updateStatusById(String id);

    /**
     * 判断用户是否存在
     * 1.账号不可重复;2.本身数据id除外
     * @param sysUser
     * @return
     */
    int isExistUser(SysUser sysUser);

}
