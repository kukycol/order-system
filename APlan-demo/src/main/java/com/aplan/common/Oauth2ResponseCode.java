package com.aplan.common;


import lombok.Getter;

@Getter
public enum Oauth2ResponseCode {

    DEMO_EXCEPTION(30000, "数据异常"),
    FILE_NOT_NULL(30000, "文件不能为空"),
    REMOVE_DATA_EXCEPTION(30000, "删除数据异常"),
    SAVE_DATA_EXCEPTION(30000, "添加数据异常"),
    UPDATE_DATA_EXCEPTION(30000, "更新数据异常"),

    QUERY_SUCCESS(20000, "查询成功"),
    QUERY_FAILED(10000, "查询失败"),

    SAVE_SUCCESS(20000, "添加成功"),
    SAVE_FAILED(10000, "添加失败"),

    UPDATE_SUCCESS(20000, "更新成功"),
    UPDATE_FAILED(10000, "更新失败"),

    REMOVE_SUCCESS(20000, "删除成功"),
    REMOVE_FAILED(10000, "删除失败")

    ;


    private Integer code;
    private String message;

    Oauth2ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
