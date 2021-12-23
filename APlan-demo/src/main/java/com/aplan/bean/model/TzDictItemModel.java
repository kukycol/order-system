package com.aplan.bean.model;

import java.util.Date;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Builder;
import lombok.experimental.Tolerate;


//get、set、toString多合一主键
@Data
//mybatis-plus表名注解（当实体类名称不是通过表名驼峰转换而已就使用）
@TableName("tz_dict_item")
//swagger在线接口文档表名注解
@ApiModel(value = "tz_dict_item")
//构造器模式注解
@Builder
public class TzDictItemModel implements Serializable {
    private static final long serialVersionUID = 379640290490179944L;


    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典项")
    private String dictItemId;

    @TableField(value = "`dict_id`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典主键")
    private String dictId;

    @TableField(value = "`dict_item_text`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典项文本")
    private String dictItemText;

    @TableField(value = "`dict_item_value`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典项值")
    private Integer dictItemValue;

    @TableField(value = "`dict_item_remark`")
    @ApiModelProperty(value = "字典项描述")
    private String dictItemRemark;

    @TableField(value = "`dict_item_sort`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典项排序")
    private Integer dictItemSort;

    @TableField(value = "`dict_item_status`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典项状态，0正常，1禁用")
    private Integer dictItemStatus;

    @TableField(value = "`create_by`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建人")
    private String createBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "`create_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "创建日期")
    private Date createTime;

    @TableField(value = "`update_by`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "更新人")
    private String updateBy;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField(value = "`update_time`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;

    //搭配@Builder注解使用
    @Tolerate
    public TzDictItemModel() {
    }

}