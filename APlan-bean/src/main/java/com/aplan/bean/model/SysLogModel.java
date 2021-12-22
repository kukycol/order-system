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
@TableName("sys_log")
//swagger在线接口文档表名注解
@ApiModel(value = "sys_log")
//构造器模式注解
@Builder
public class SysLogModel implements Serializable {
    private static final long serialVersionUID = -65545156694956169L;


    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "操作记录")
    private String logId;

    @TableField(value = "`username`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "用户名")
    private String username;

    @TableField(value = "`path`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "请求地址")
    private String path;

    @TableField(value = "`device_name`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "设备名称")
    private String deviceName;

    @TableField(value = "`browser_name`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "设备名称")
    private String browserName;

    @TableField(value = "`method`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "请求方法")
    private String method;

    @TableField(value = "`user_id`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "用户主键")
    private String userId;

    @TableField(value = "`token`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "访问令牌")
    private String token;

    @TableField(value = "`operation`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "用户操作")
    private String operation;

    @TableField(value = "`params`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "请求参数")
    private Object params;

    @TableField(value = "`time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "执行时长(毫秒)")
    private Long time;

    @TableField(value = "`ip`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "IP地址")
    private String ip;

    @TableField(value = "`result`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "请求返回的结果")
    private Object result;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(exist = false)
    private String startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(exist = false)
    private String endTime;


    //搭配@Builder注解使用
    @Tolerate
    public SysLogModel() {
    }

}