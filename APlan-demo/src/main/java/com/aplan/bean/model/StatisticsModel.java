package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("sys_statistics")
@ApiModel(value = "员工评分记录model")
public class StatisticsModel {

    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "员工评分记录主键")
    private String statisticsId;


    @TableField(value = "`user_id`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "被评用户主键")
    private String userId;


    @TableField(value = "`score_user_id`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "评用户主键")
    private String scoreUserId;


    @TableField(value = "`score1`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "条件1分数")
    private Integer score1;


    @TableField(value = "`score2`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "条件2分数")
    private Integer score2;


    @TableField(value = "`score3`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "条件3分数")
    private Integer score3;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

}
