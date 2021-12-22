package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "角色搜索请求bean")
public class RoleRequest extends PageRequestParam {

    @ApiModelProperty(value = "角色昵称")
    private String roleRemark;

    @ApiModelProperty(value = "角色备注")
    private String role;


}