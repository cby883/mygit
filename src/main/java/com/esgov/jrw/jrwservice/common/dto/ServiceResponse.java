package com.esgov.jrw.jrwservice.common.dto;
import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 描述: 统一返回
 *
 * @author Yangjinming
 * @create 2018/3/26 21:31
 */
public class ServiceResponse<T> implements Serializable {
    private static final long serialVersionUID = -4012170306337511959L;
    /**
     * 返回状态 0成功
     * -1 错误
     */
    private int code;
    /**
     * 访问描述
     */
    private String desc;

    /**
     * 返回数据
     */
    private T data;

    public ServiceResponse(){

    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private ServiceResponse(int code){
        this.code = code;
    }

    private ServiceResponse(int code, T data){
        this.code = code;
        this.data = data;
    }

    private ServiceResponse(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private ServiceResponse(int code, String desc, T data){
        this.code= code;
        this.desc = desc;
        this.data = data;
    }

    /**
     * 在序列化时由于该方法调用了这个属性会影响 顾需要使之不在json序列化结果当中
     *
     * @return
     */
    @JsonIgnore
    public boolean isSuccess(){
        return this.code == ResponseCode.SUCCESS.getCode();
    }

    /**
     * 默认操作成功返回（含默认成功返回码和描述）
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createSuccess(){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg());
    }

    /**
     * 默认操作错误返回（含默认错误返回码和描述）
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createError(){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg());
    }


    /**
     * 返回成功
     * 根据描述和数据
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createBySuccessMsgData(String msg,T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    /**
     * 返回失败
     * 根据描述和数据
     *
     * @param msg
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createByErrorMsgData(String msg,T data){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),msg,data);
    }

    /**
     * 返回失败
     * 根据数据
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createErrorByData(T data){
        return new ServiceResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getMsg(),data);
    }

    /**
     * 返回成功
     * 根据数据
     *
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createSuccessByData(T data){
        return new ServiceResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getMsg(),data);
    }

    /**
     * 返回自定义对应响应Code和DATA
     *
     * @param responseCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createByResponseCodeData(ResponseCode responseCode,T data){
        return new ServiceResponse<T>(responseCode.getCode(),responseCode.getMsg(),data);
    }
    /**
     * 返回自定义对应响应Code 无DATA数据
     *
     * @param responseCode
     * @param <T>
     * @return
     */
    public static <T> ServiceResponse<T> createByResponseCode(ResponseCode responseCode){
        return new ServiceResponse<T>(responseCode.getCode(),responseCode.getMsg());
    }

    /**
     * 根据code和msg返回
     *
     * @param
     * @return
     */
    public static <T>  ServiceResponse<T> createByCodeMsg(int code, String msg){
        return new ServiceResponse<T>(code,msg);
    }

}
