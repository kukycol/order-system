package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("sys_path_white")
@ApiModel(value = "路径白名单")
public class SysPathWhiteModel {

    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键")
    private String id;

    @TableField(value = "`path`")
    @ApiModelProperty(value = "路径")
    private String path;

    @TableField(value = "`remark`")
    @ApiModelProperty(value = "备注")
    private String remark;

}