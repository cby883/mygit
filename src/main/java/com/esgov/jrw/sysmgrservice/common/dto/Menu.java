/**
 *
 */
package com.esgov.jrw.sysmgrservice.common.dto;

import com.esgov.jrw.sysmgrservice.entity.authority.SysMenu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wuchuang
 *
 * 2016年10月8日 下午3:11:57
 *
 *
 */
public class Menu implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3943339640050905847L;

	private String id;
	private String parentId;
	private String name;
	private String url;
	private String icon;
	private Boolean isExternalLink;
	private List<Menu> subs = new ArrayList<Menu>();


	public Menu() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getIsExternalLink() {
		return isExternalLink;
	}

	public void setIsExternalLink(Boolean isExternalLink) {
		this.isExternalLink = isExternalLink;
	}

	public List<Menu> getSubs() {
		return subs;
	}

	public void addSub(Menu sub) {
		this.subs.add(sub);
	}

	public static Menu transformer(SysMenu sysMenu){
		Menu menu = null;
		if(null != sysMenu){
			menu = new Menu();
			menu.setId(sysMenu.getId());
			menu.setName(sysMenu.getName());
			menu.setParentId(sysMenu.getParentId());
			menu.setUrl(sysMenu.getUrl());
			menu.setIsExternalLink(Boolean.getBoolean(sysMenu.getIsExternalLink()));
		}
		return menu;
	}
}
