package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

import java.util.Date;


//get、set、toString多合一主键
@Data
//mybatis-plus表名注解（当实体类名称不是通过表名驼峰转换而已就使用）
@TableName("sys_param")
//swagger在线接口文档表名注解
@ApiModel(value = "参数Model")
//构造器模式注解
@Builder
public class SysParamModel {


    @TableId(type = IdType.AUTO)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "参数")
    private String id;

    @TableField(value = "`param_key`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "参数键名")
    private String paramKey;

    @TableField(value = "`param_remark`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "参数备注")
    private String paramRemark;

    @TableField(value = "`param_value`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "参数value")
    private String paramValue;

    @TableField(value = "`param_name`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "参数名称")
    private String paramName;

    @TableField(value = "`is_system`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "是不是系统参数")
    private Integer isSystem;

    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    //搭配@Builder注解使用
    @Tolerate
    public SysParamModel() {
    }

}