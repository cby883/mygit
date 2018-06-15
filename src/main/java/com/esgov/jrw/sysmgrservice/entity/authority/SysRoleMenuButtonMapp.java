package com.esgov.jrw.sysmgrservice.entity.authority;

import com.esgov.jrw.sysmgrservice.entity.enums.StatusEnum;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author your name
 * @since 2018-04-17
 */
public class SysRoleMenuButtonMapp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 菜单id
     */
    private String menuId;
    /**
     * 按钮id
     */
    private String buttonId;
    /**
     * 使用状态
     */
    private String used;
    /**
     * 状态
     */
    private StatusEnum status;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新人
     */
    private String updateUser;
    /**
     * 更新时间
     */
    private Date updateTime;

    public static SysRoleMenuButtonMapp getInstance(String roleId, String menuId, String buttonId, String used) {
        SysRoleMenuButtonMapp sysRoleMenuButtonMapp = new SysRoleMenuButtonMapp();
        sysRoleMenuButtonMapp.setRoleId(roleId);
        sysRoleMenuButtonMapp.setMenuId(menuId);
        sysRoleMenuButtonMapp.setButtonId(buttonId);
        sysRoleMenuButtonMapp.setUsed(used);
        return sysRoleMenuButtonMapp;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getButtonId() {
        return buttonId;
    }

    public void setButtonId(String buttonId) {
        this.buttonId = buttonId;
    }

    public String getUsed() {
        return used;
    }

    public void setUsed(String used) {
        this.used = used;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
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

    @Override
    public String toString() {
        return "SysRoleMenuButtonMapp{" +
        ", id=" + id +
        ", roleId=" + roleId +
        ", menuId=" + menuId +
        ", buttonId=" + buttonId +
        ", used=" + used +
        ", status=" + status +
        ", creator=" + creator +
        ", createTime=" + createTime +
        ", updateUser=" + updateUser +
        ", updateTime=" + updateTime +
        "}";
    }
}
