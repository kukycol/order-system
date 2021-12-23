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

import java.util.List;


//get、set、toString多合一主键
@Data
//mybatis-plus表名注解（当实体类名称不是通过表名驼峰转换而已就使用）
@TableName("tz_dict")
//swagger在线接口文档表名注解
@ApiModel(value = "tz_dict")
//构造器模式注解
@Builder
public class TzDictModel implements Serializable {
    private static final long serialVersionUID = -65545156694956169L;


    @TableId(type = IdType.ASSIGN_UUID)
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典")
    private String dictId;

    @TableField(value = "`dict_name`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @TableField(value = "`dict_code`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典类型")
    private String dictCode;

    @TableField(value = "`dict_remark`")
    @ApiModelProperty(value = "字典描述")
    private String dictRemark;

    @TableField(value = "`is_system`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "系统字典，0是，1不是")
    private Integer isSystem;

    @TableField(value = "`dict_state`")
    //非空校验，只针对唯一性校验的字段
    @ApiModelProperty(value = "字典状态，0正常，1禁用")
    private Integer dictState;

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

    @TableField(exist = false)
    private List<TzDictItemModel> dictItemModels;

    //搭配@Builder注解使用
    @Tolerate
    public TzDictModel() {
    }

}