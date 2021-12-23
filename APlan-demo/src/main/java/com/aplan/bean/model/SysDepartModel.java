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

import java.util.Date;


//get、set、toString多合一主键
@Data
//mybatis-plus表名注解（当实体类名称不是通过表名驼峰转换而已就使用）
@TableName("sys_depart")
//swagger在线接口文档表名注解
@ApiModel(value = "部门Model")
//构造器模式注解
@Builder
public class SysDepartModel  {


    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "部门")
    private String departId;

    @TableField(value = "`depart_name`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "部门名称")
    private String departName;

    @TableField(value = "`create_by`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建人")
    private String create_by;

    @TableField(value = "`parent_id`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "父级部门")
    private String parentId;

    @TableField(value = "`depart_status`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "部门状态")
    private Integer departStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    //搭配@Builder注解使用
    @Tolerate
    public SysDepartModel() {
    }

}