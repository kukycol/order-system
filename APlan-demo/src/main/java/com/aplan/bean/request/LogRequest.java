package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "定时任务搜索请求bean")
public class LogRequest extends PageRequestParam {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "请求地址")
    private String path;

    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @ApiModelProperty(value = "设备名称")
    private String browserName;

    @ApiModelProperty(value = "请求方法")
    private String method;

}