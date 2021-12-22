package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "定时任务搜索请求bean")
public class JobRequest extends PageRequestParam {

    @ApiModelProperty(value = "任务名称")
    private String jobName;

}