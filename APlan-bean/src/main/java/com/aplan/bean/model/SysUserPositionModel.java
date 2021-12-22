package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@TableName("sys_user_position")
@ApiModel(value = "用户职位中间表")
public class SysUserPositionModel  {

    @TableField(value = "`user_position`")
    @ApiModelProperty(value = "用户职位")
    private String roleId;

    @TableField(value = "`user_id`")    
    @ApiModelProperty(value = "用户主键")
    private String userId;

    @TableField(value = "`position_id`")
    @ApiModelProperty(value = "职位主键")
    private String positionId;


}