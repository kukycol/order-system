package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "用户搜索请求bean")
public class UserRequest extends PageRequestParam {

    @ApiModelProperty(value = "账号或者昵称")
    private String alias;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "部门主键")
    private String departId;

    @ApiModelProperty(value = "启用，启用0，禁用1")
    private Integer enabled;

    @ApiModelProperty(value = "性别，女0，男1")
    private Integer sex;

}