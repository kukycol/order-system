package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "职位搜索请求bean")
public class PositionRequest extends PageRequestParam {

    @ApiModelProperty(value = "职位名称")
    private String positionName;

    @ApiModelProperty(value = "职位状态")
    private Integer positionStatus;

}