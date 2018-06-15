package com.esgov.jrw.sysmgrservice.entity.authority;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.esgov.jrw.sysmgrservice.entity.enums.StatusEnum;

import java.util.Date;

public class SysParam {
    private String id;

    private Date createTime;

    private String creator;
    @TableLogic
    private StatusEnum status;

    private Date updateTime;

    private String updateUser;

    private String code;

    private String isdef;

    private String paramTypeCode;

    private String remark;

    private Integer seq;

    private String value;

    private String paramTypeId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getIsdef() {
        return isdef;
    }

    public void setIsdef(String isdef) {
        this.isdef = isdef == null ? null : isdef.trim();
    }

    public String getParamTypeCode() {
        return paramTypeCode;
    }

    public void setParamTypeCode(String paramTypeCode) {
        this.paramTypeCode = paramTypeCode == null ? null : paramTypeCode.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value == null ? null : value.trim();
    }

    public String getParamTypeId() {
        return paramTypeId;
    }

    public void setParamTypeId(String paramTypeId) {
        this.paramTypeId = paramTypeId == null ? null : paramTypeId.trim();
    }
}