package com.esgov.jrw.jrwservice.dao.enterprise;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.esgov.jrw.jrwservice.entity.enterprise.Enterprise;
public interface EnterpriseDao extends BaseMapper<Enterprise> {
    public void get();
}
