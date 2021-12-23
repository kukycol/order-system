package com.aplan.bean.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserOnlineDto {

    private String username;
    private String alias;
    private String departName;
    private String positionName;
    private String ipv4;
    private String browserName;
    private String token;
    private Date createTime;

}
