package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "参数搜索请求bean")
public class ParamRequest extends PageRequestParam {

    @ApiModelProperty(value = "参数名称")
    private String paramName;

    @ApiModelProperty(value = "参数键名")
    private String paramKey;

    @ApiModelProperty(value = "是不是系统参数")
    private Integer isSystem;

}