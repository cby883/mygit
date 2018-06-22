package com.esgov.jrw.jrwservice.entity.authority;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.esgov.jrw.jrwservice.entity.enums.StatusEnum;

import java.util.Date;

public class SysOrg {
    private String id;
    @TableField(strategy=FieldStrategy.NOT_NULL)
    private String name;

    private String orgType;

    @TableField(strategy=FieldStrategy.NOT_NULL)
    private String remark;

    private String parentId;

    private String isUsed;

    @TableLogic
    private StatusEnum status;

    private Date createTime;

    private String creator;

    private String updateUser;

    private Date updateTime;

    @TableField(exist=false)
    private String orgTypeValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType == null ? null : orgType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed == null ? null : isUsed.trim();
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
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

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOrgTypeValue() {
        return orgTypeValue;
    }

    public void setOrgTypeValue(String orgTypeValue) {
        this.orgTypeValue = orgTypeValue;
    }
}