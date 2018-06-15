package com.esgov.jrw.sysmgrservice.dao.authority;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.sysmgrservice.entity.authority.SysParamType;

import java.util.List;

/**
 * 描述: 通用配置参数类型
 *
 * @author Yangjinming
 * @create 2018-04-02 上午10:37
 */
public interface SysParamTypeDao extends BaseMapper<SysParamType> {
    /**
     * 检查是否存在同样的code
     *
     * @param sysParamType
     * @return
     */
    int checkByTypeCode(SysParamType  sysParamType);



    /**
     * 分页查询字典类型
     * @param sysParamType
     * @return
     */
    List<SysParamType> queryPageBySelective(SysParamType sysParamType);

    /**
     * 获取所有参数类型
     *
     * @return
     */
    List<SysParamType> getAllSysParamTypes();

}
