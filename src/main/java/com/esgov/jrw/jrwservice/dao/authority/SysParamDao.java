package com.esgov.jrw.jrwservice.dao.authority;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.jrwservice.entity.authority.SysParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述: 通用参数配置
 *
 * @author Yangjinming
 * @create 2018-04-02 上午9:42
 */
public interface SysParamDao extends BaseMapper<SysParam> {

    int checkByCode(SysParam sysParam);

    /**
     * 根据参数类型TypeID获取参数
     *
     * @param typeId
     * @return
     */
    List<SysParam> getParamsByTypeId(String typeId);

    /**
     * 根据字典类型ID分页查询参数字典
     *
     * @param typeId
     * @return
     */
    List<SysParam> queryPageBySelective(String typeId);

    /**
     * 根据参数类型code查询参数字典值
     *
     * @param typeCode
     * @return
     */
    List<SysParam> getParamsByTypeCode(String typeCode);

    /**
     * 根据参数类型Code和参数code查询对应参数信息
     *
     * @param paramTypeCode
     * @param paramCode
     * @return
     */
    SysParam getParamByTypeCodeAndCode(@Param("paramTypeCode") String paramTypeCode, @Param("paramCode") String paramCode);
    /**
     * 获取所有参数字典
     * @return
     */
    List<SysParam> getAllSysParam();

}
