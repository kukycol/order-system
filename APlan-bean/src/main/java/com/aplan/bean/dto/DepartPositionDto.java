package com.aplan.bean.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


//get、set、toString多合一主键
@Data
//mybatis-plus表名注解（当实体类名称不是通过表名驼峰转换而已就使用）
@TableName("sys_depart_position")
//swagger在线接口文档表名注解
@ApiModel(value = "部门职位DTO")
//构造器模式注解
@Builder
public class DepartPositionDto {


    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "职位")
    private String positionId;

    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "部门主键")
    private String departId;

    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "部门名称")
    private String departName;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @TableField(value = "`create_by`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建人")
    private String create_by;

    @TableField(value = "`position_name`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "职位名称")
    private String positionName;

    @TableField(value = "`position_status`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "职位状态")
    private Integer positionStatus;

    @TableField(value = "`position_sort`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "职位排序")
    private Integer positionSort;

    //搭配@Builder注解使用
    @Tolerate
    public DepartPositionDto() {
    }

}