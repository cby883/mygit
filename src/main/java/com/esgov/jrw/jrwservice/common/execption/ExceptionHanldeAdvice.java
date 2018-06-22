package com.esgov.jrw.jrwservice.common.execption;


import com.esgov.jrw.jrwservice.common.dto.enums.ResponseCode;
import com.esgov.jrw.jrwservice.common.dto.ServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 描述: 统一异常处理
 *
 * @author Yangjinming
 * @create 2018-03-28 上午11:53
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHanldeAdvice {
    private static Logger logger = LoggerFactory.getLogger(ExceptionHanldeAdvice.class);

    /**
     *   获取所有Excpetion 进行处理
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ServiceResponse handleServiceException(Exception exception){
        if(exception instanceof ServiceException){
            //如果异常为自定义异常，即返回自定义处理code 和 描述
            ServiceException serviceException = (ServiceException) exception;
            return ServiceResponse.createByCodeMsg(serviceException.getCode(),serviceException.getDesc());
        }
        if(exception instanceof MethodArgumentNotValidException){
            //如果异常为请求参数不合法异常 即返回描述
            MethodArgumentNotValidException methodArgumentNotValidException =(MethodArgumentNotValidException)exception;
            List<ObjectError> errors = methodArgumentNotValidException.getBindingResult().getAllErrors();
            String tips = "参数不合法";
            if (errors.size() > 0) {
                tips = errors.get(0).getDefaultMessage();
            }
            return ServiceResponse.createByCodeMsg(ResponseCode.SUCCESS.getCode(),tips);
        }
        //其他异常，打印日志，并返回出错信息和出错code
        logger.error("服务器出错：：",exception);
        return ServiceResponse.createByCodeMsg(ResponseCode.ERROR.getCode(),"服务器出错");
    }
}
