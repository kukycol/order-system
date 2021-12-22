package com.aplan.bean.request;

import com.aplan.bean.param.PageRequestParam;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "权限搜索请求bean")
public class RuleRequest extends PageRequestParam {

    @ApiModelProperty(value = "名称")
    private String ruleName;


}