package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("sys_rule_role")
@ApiModel(value = "sys_rule_role")
public class SysRuleRoleModel implements Serializable {
    private static final long serialVersionUID = -48148485169500917L;

    @TableField(value = "`rule_id`")    
    @ApiModelProperty(value = "${column.comment}")
    private String ruleId;

    @TableField(value = "`role_id`")    
    @ApiModelProperty(value = "${column.comment}")
    private String roleId;



}