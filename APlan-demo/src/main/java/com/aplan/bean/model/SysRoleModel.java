package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@TableName("sys_role")
@ApiModel(value = "sys_role")
public class SysRoleModel implements Serializable {
    private static final long serialVersionUID = 651310362659214311L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    @TableField(value = "`role_name`")    
    @ApiModelProperty(value = "角色")
    private String roleName;

    @TableField(value = "`role_remark`")    
    @ApiModelProperty(value = "角色昵称")
    private String roleRemark;

    @TableField(value = "`role`")    
    @ApiModelProperty(value = "${column.comment}")
    private String role;

    @TableField(exist = false)
    private List<String> ruleList;



}