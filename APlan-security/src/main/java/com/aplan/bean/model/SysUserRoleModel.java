package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
@TableName("sys_user_role")
@ApiModel(value = "sys_user_role")
public class SysUserRoleModel implements Serializable {
    private static final long serialVersionUID = -52118991280306644L;

    @TableField(value = "`role_id`")    
    @ApiModelProperty(value = "${column.comment}")
    private String roleId;

    @TableField(value = "`user_id`")    
    @ApiModelProperty(value = "${column.comment}")
    private String userId;



}