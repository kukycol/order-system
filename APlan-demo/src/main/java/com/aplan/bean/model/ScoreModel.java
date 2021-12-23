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
@TableName("sys_score")
@ApiModel(value = "评分model")
public class ScoreModel {

    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "评分条件主键")
    private String scoreId;


    @TableField(value = "`score_content`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "评分条件内容")
    private String scoreContent;


    @TableField(value = "`score_type`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "评分类型")
    private Integer scoreType;


    @TableField(value = "`score_max`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "评分最大分数")
    private Integer scoreMax;

    @TableField(value = "`score`",exist = false)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "评分分数")
    private Integer score;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

}
