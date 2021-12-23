package com.aplan.bean.dto;

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
import java.util.List;

@Data
@ApiModel(value = "评分dto")
public class ScoreDto {


    @ApiModelProperty(value = "评分条件内容")
    private String scoreContent;

    @ApiModelProperty(value = "评分类型")
    private Integer scoreType;

    @ApiModelProperty(value = "最大评分分数")
    private Integer scoreMax;


}
