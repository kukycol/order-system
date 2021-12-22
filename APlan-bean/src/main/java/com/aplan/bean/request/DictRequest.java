package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;



@Data
@ApiModel(value = "字典搜索请求bean")
public class DictRequest extends PageRequestParam {

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典描述")
    private String dictRemark;

    @ApiModelProperty(value = "系统字典，0是，1不是")
    private Integer isSystem;

    @ApiModelProperty(value = "字典状态，0正常，1禁用")
    private Integer dictState;


}