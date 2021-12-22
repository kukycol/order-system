package com.aplan.common;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一响应类
 * @param <T>
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Oauth2Response<T> {

    /**
     * 编码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 对象数据
     */
    private T data;

    /**
     * 数据总条数
     */
    private Long total;


    /**
     * 异常
     * @param code
     * @param message
     */
    public Oauth2Response(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    /**
     * 添加、更新、删除
     * @param responseCode
     */
    public Oauth2Response(Oauth2ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }


    /**
     * 查询单个对象数据，列表数据
     * @param responseCode
     * @param data
     */
    public Oauth2Response(Oauth2ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }


    /**
     * 分页
     * @param responseCode
     * @param data
     * @param total
     */
    public Oauth2Response(Oauth2ResponseCode responseCode, T data, Long total) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
        this.total = total;
    }

    /**
     * 分页查询成功
     * @return
     */
    public static Oauth2Response pageQuerySuccess(Object data, Long total){
        return new Oauth2Response(Oauth2ResponseCode.QUERY_SUCCESS,data,total);
    }

    /**
     * 查询成功
     * @return
     */
    public static Oauth2Response querySuccess(Object data){
        return new Oauth2Response(Oauth2ResponseCode.QUERY_SUCCESS,data);
    }

    /**
     * 查询失败
     * @return
     */
    public static Oauth2Response queryFail(){
        return new Oauth2Response(Oauth2ResponseCode.QUERY_FAILED);
    }

    /**
     * 添加成功
     * @return
     */
    public static Oauth2Response saveSuccess(){
        return new Oauth2Response(Oauth2ResponseCode.SAVE_SUCCESS);
    }

    /**
     * 添加失败
     * @return
     */
    public static Oauth2Response saveFail(){
        return new Oauth2Response(Oauth2ResponseCode.SAVE_FAILED);
    }

    /**
     * 更新成功
     * @return
     */
    public static Oauth2Response updateSuccess(){
        return new Oauth2Response(Oauth2ResponseCode.UPDATE_SUCCESS);
    }

    /**
     * 更新失败
     * @return
     */
    public static Oauth2Response updateFail(){
        return new Oauth2Response(Oauth2ResponseCode.UPDATE_FAILED);
    }

    /**
     * 删除成功
     * @return
     */
    public static Oauth2Response removeSuccess(){
        return new Oauth2Response(Oauth2ResponseCode.REMOVE_SUCCESS);
    }

    /**
     * 删除失败
     * @return
     */
    public static Oauth2Response removeFail(){
        return new Oauth2Response(Oauth2ResponseCode.REMOVE_FAILED);
    }

    /**
     * 数据异常
     * @return
     */
    public static Oauth2Response error(){
        return new Oauth2Response(Oauth2ResponseCode.DEMO_EXCEPTION);
    }

    /**
     * 数据已存在
     * @return
     */
    public static Oauth2Response dataExist(String message){
        return new Oauth2Response(30000,"数据："+message+"，已存在");
    }

    /**
     * 数据已存在
     * @return
     */
    public static Oauth2Response dataNotExist(String message){
        return new Oauth2Response(30000,"数据："+message+"，不存在");
    }


}

