package com.esgov.jrw.sysmgrservice.entity.authority;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.esgov.jrw.sysmgrservice.entity.enums.StatusEnum;

import java.util.Date;

public class SysMenu {
    private String id;

    private String name;

    @TableField(exist = false)
    private String parentName;

    private String url;

    private String parentId;

    private Integer orderBy;

    private String platForm;

    @TableLogic
    private StatusEnum status;

    private Date createTime;

    private String creator;

    private String updateUser;

    private Date updateTime;

    private String remark;

    private String isUsed;

    private String isExternalLink;

    private String icon;

    @TableField(exist = false)
    private String platFormValue;
    @TableField(exist = false)
    private String parentMenuName;
    @TableField(exist = false)
    private String isUsedValue;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public Integer getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(Integer orderBy) {
        this.orderBy = orderBy;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm == null ? null : platForm.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed == null ? null : isUsed.trim();
    }

    public String getIsExternalLink() {
        return isExternalLink;
    }

    public void setIsExternalLink(String isExternalLink) {
        this.isExternalLink = isExternalLink == null ? null : isExternalLink.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public String getPlatFormValue() {
        return platFormValue;
    }

    public void setPlatFormValue(String platFormValue) {
        this.platFormValue = platFormValue;
    }

    public String getParentMenuName() {
        return parentMenuName;
    }

    public void setParentMenuName(String parentMenuName) {
        this.parentMenuName = parentMenuName;
    }

    public String getIsUsedValue() {
        return isUsedValue;
    }

    public void setIsUsedValue(String isUsedValue) {
        this.isUsedValue = isUsedValue;
    }
}