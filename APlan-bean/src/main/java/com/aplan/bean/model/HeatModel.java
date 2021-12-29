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
@TableName("sys_heat")
@ApiModel(value = "热度model")
public class HeatModel {

    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度主键")
    private String id;

    @TableField(value = "`index`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "排名")
    private Integer index;

    @TableField(value = "`heat_number`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度")
    private long heatNumber;

    @TableField(value = "`type`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度类型")
    private String type;

    @TableField(value = "`url`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度地址")
    private String url;

    @TableField(value = "`platform_type`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "平台，0百度，1微博")
    private Integer platformType;

    @TableField(value = "`tag`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度标签")
    private Integer tag;

    @TableField(value = "`title`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度标题")
    private String title;

    @TableField(value = "`desc`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "热度描述")
    private String desc;

    @TableField(value = "`img`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "图片")
    private String img;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "`update_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "更新")
    private Date updateTime;

}
