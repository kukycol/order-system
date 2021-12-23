package com.aplan.bean.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * VO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnlineUserVo {
    private String token;
    private String userId;
    private String departName;
    private String positionName;
    private String alias;
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;         //登陆时间
    private String username;
    private String ipv4;
    private String browserName;
}

