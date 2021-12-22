package com.aplan.exception.custom;

import com.aplan.common.Oauth2ResponseCode;
import lombok.Data;


/**
 * 自定义异常类
 */
@Data
public class Oauth2Exception extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    protected Integer code;

    /**
     * 错误信息
     */
    protected String message;

    public Oauth2Exception() {
        super();
    }

    public Oauth2Exception(String errorMsg) {
        super(errorMsg);
        this.code = 30000;
        this.message = errorMsg;
    }

    public Oauth2Exception(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Oauth2Exception(Oauth2ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }

    public Oauth2Exception(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }


    /**
     * 添加数据异常
     * @return
     */
    public static Oauth2Exception saveException(){
        return new Oauth2Exception(Oauth2ResponseCode.SAVE_DATA_EXCEPTION);
    }


    /**
     * 更新数据异常
     * @return
     */
    public static Oauth2Exception updateException(){
        return new Oauth2Exception(Oauth2ResponseCode.UPDATE_DATA_EXCEPTION);
    }


    /**
     * 删除数据异常
     * @return
     */
    public static Oauth2Exception removeException(){
        return new Oauth2Exception(Oauth2ResponseCode.REMOVE_DATA_EXCEPTION);
    }


    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
    
}
