package com.aplan.bean.model;

import lombok.Data;


@Data
public class SysUserUpdatePassword  {


    /**
     * 旧密码
     */
    private String oldPassword;

    /**
     * 新密码
     */
    private String newPassword;

    /**
     * 确认密码
     */
    private String confirmPassword;



}