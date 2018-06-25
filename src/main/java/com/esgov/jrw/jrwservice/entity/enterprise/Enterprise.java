package com.esgov.jrw.jrwservice.entity.enterprise;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.esgov.jrw.jrwservice.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;
@TableName(value = "en_enterprise")
public class Enterprise extends BaseEntity implements Serializable {

    private String name;

    private String shortName;

    private String desc;

    private String foundTime;

    private String www;

    private String orgCode;

    private String creditCode;

    private String regNo;

    private Integer regCapital;

    private String business;

    private String trade;

    private String province;

    private String city;

    private String disttrict;

    private String phone;

    private String contacts;

    private String contactsPost;

    private String contactsPhone;

    private String auditStatus;

    private String level;

    private String flag;

    private String fiType;

    private String fiMode;

    private String fiMinnum;

    private String fiMaxnum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getFoundTime() {
        return foundTime;
    }

    public void setFoundTime(String foundTime) {
        this.foundTime = foundTime == null ? null : foundTime.trim();
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www == null ? null : www.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode == null ? null : creditCode.trim();
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo == null ? null : regNo.trim();
    }

    public Integer getRegCapital() {
        return regCapital;
    }

    public void setRegCapital(Integer regCapital) {
        this.regCapital = regCapital;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade == null ? null : trade.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDisttrict() {
        return disttrict;
    }

    public void setDisttrict(String disttrict) {
        this.disttrict = disttrict == null ? null : disttrict.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getContactsPost() {
        return contactsPost;
    }

    public void setContactsPost(String contactsPost) {
        this.contactsPost = contactsPost == null ? null : contactsPost.trim();
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone == null ? null : contactsPhone.trim();
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus == null ? null : auditStatus.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public String getFiType() {
        return fiType;
    }

    public void setFiType(String fiType) {
        this.fiType = fiType == null ? null : fiType.trim();
    }

    public String getFiMode() {
        return fiMode;
    }

    public void setFiMode(String fiMode) {
        this.fiMode = fiMode == null ? null : fiMode.trim();
    }

    public String getFiMinnum() {
        return fiMinnum;
    }

    public void setFiMinnum(String fiMinnum) {
        this.fiMinnum = fiMinnum == null ? null : fiMinnum.trim();
    }

    public String getFiMaxnum() {
        return fiMaxnum;
    }

    public void setFiMaxnum(String fiMaxnum) {
        this.fiMaxnum = fiMaxnum == null ? null : fiMaxnum.trim();
    }

}