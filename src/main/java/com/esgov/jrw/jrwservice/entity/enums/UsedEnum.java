package com.esgov.jrw.jrwservice.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 描述: 是否禁用枚举
 *
 * @author Yangjinming
 * @create 2018-04-17 上午10:42
 */
public enum  UsedEnum implements IEnum {
    ENABLE("1","启用"), DISABLE("0","警用");
    UsedEnum(String value,String desc){
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
