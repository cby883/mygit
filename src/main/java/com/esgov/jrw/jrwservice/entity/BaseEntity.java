package com.esgov.jrw.jrwservice.entity;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.esgov.jrw.jrwservice.entity.enums.StatusEnum;

import java.util.Date;

public class BaseEntity {

    private String id;
    @TableLogic
    private String status;
    private String creator;
    private Date createTime;
    private String updateUser;
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
