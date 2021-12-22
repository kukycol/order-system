package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "定时任务搜索请求bean")
public class OnlineRequest extends PageRequestParam {

    @ApiModelProperty(value = "登录名")
    private String username;

    @ApiModelProperty(value = "ip地址")
    private String ipv4;

}