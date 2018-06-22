package com.esgov.jrw.jrwservice.dao.authority;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.jrwservice.entity.authority.SysOrg;

import java.util.List;
import java.util.Map;

/**
 * 描述: 机构管理DAO
 *
 * @author Yangjinming
 * @create 2018-03-28 上午10:02
 */
public interface SysOrgDao  extends BaseMapper<SysOrg> {
    /**
     * 检查是否存在
     *
     * @param sysOrg
     * @return
     */
    int checkOrgName(SysOrg sysOrg);

    /**
     * 通过具体参数分页查询
     *
     * @param sysOrg
     * @return
     */
    List<SysOrg> queryPageBySelective(SysOrg sysOrg);

    /**
     * 获取所有树节点
     *
     * @return
     */
    List<Map<String,Object>> getTree();

    /**
     * 获取子机构
     *
     * @param id
     * @return
     */
    List<SysOrg> getChildOrg(String id);

}
