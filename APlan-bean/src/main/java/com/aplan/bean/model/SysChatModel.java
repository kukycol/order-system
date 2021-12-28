package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


//get、set、toString多合一主键
@Data
//mybatis-plus表名注解（当实体类名称不是通过表名驼峰转换而已就使用）
@TableName("sys_chat_record")
//swagger在线接口文档表名注解
@ApiModel(value = "sys_chat_record")
//构造器模式注解
@Builder
public class SysChatModel  {


    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "操作记录")
    private String id;

    @TableField(value = "`key`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "键")
    private String key;

    @TableField(value = "`value`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "值")
    private String value;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;


    //搭配@Builder注解使用
    @Tolerate
    public SysChatModel() {
    }

}