package com.esgov.jrw.jrwservice.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 描述:
 *
 * @author Yangjinming
 * @create 2018-04-16 下午3:47
 */
public enum StatusEnum implements IEnum {
    NORMAL("1","正常使用"), DELETE("0","逻辑删除");
    StatusEnum(String value,String desc){
        this.value = value;
        this.desc = desc;
    }
    private String value;
    private String desc;

    public void setValue(String value) {
        this.value = value;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }
    @JsonIgnore()
    public String getDesc(){
        return this.desc;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
