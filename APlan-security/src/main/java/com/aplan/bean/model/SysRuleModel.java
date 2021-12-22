package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
@TableName("sys_rule")
@ApiModel(value = "sys_rule")
public class SysRuleModel implements Serializable {
    private static final long serialVersionUID = -63773539610072643L;

    @TableId(type = IdType.ASSIGN_UUID)
    @ApiModelProperty(value = "主键")
    private String id;

    @TableField(value = "`rule_name`")    
    @ApiModelProperty(value = "名称")
    private String ruleName;

    @TableField(value = "`label`",exist = false)
    private String label;

    @TableField(value = "`rule_url`")    
    @ApiModelProperty(value = "链接")
    private String ruleUrl;

    @TableField(value = "`perms`")
    @ApiModelProperty(value = "权限")
    private String perms;

    @TableField(value = "`rule_remark`")    
    @ApiModelProperty(value = "备注")
    private String ruleRemark;

    @TableField(value = "`parent_id`")    
    @ApiModelProperty(value = "没有上级为0")
    private String parentId;

    @TableField(value = "`component_name`")
    @ApiModelProperty(value = "组件名称")
    private String componentName;

    @TableField(value = "`component_path`")
    @ApiModelProperty(value = "组件路径")
    private String componentPath;

    @TableField(value = "`rule_type`")    
    @ApiModelProperty(value = "0目录，1菜单，2按钮")
    private Integer ruleType;

    @TableField(value = "`rule_order`")    
    @ApiModelProperty(value = "菜单排序")
    private Integer ruleOrder;

    @TableField(value = "`rule_hide`")
    @ApiModelProperty(value = "菜单隐藏")
    private Integer ruleHide;

    @TableField(value = "`outside_menu`")
    @ApiModelProperty(value = "外链接")
    private Integer outsideMenu;

    @TableField(value = "`rule_level`")
    @ApiModelProperty(value = "等级")
    private Integer ruleLevel;

    @TableField(value = "`no_cache`")
    @ApiModelProperty(value = "页面缓存")
    private Integer noCache;

    @TableField(exist = false)
    private boolean disabled = true;

    @TableField(value = "`rule_icon`")    
    @ApiModelProperty(value = "菜单图标")
    private String ruleIcon;

    @TableField(value = "`rule_echo`")
    @ApiModelProperty(value = "上级回显")
    private String ruleEcho;

    @TableField(exist = false)
    private List<String> roleModelList;



    @TableField(exist = false)
    private boolean disabled2 = false;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @TableField(exist = false)
    public List<SysRuleModel> children;
}