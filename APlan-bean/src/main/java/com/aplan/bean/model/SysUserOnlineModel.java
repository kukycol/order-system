package com.aplan.bean.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("sys_user_online")
public class SysUserOnlineModel {

    @TableId(type = IdType.ASSIGN_UUID)
    private String onlineId;
    private String token;
    private String userId;
    private String logId;
    private Integer status;

}
