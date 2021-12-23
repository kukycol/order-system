package com.aplan.controller.login;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.aplan.annotation.SysLog;
import com.aplan.common.Oauth2Response;
import com.aplan.bean.dto.SysUserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "登录登出接口组")
@RestController
@RequestMapping("/sys/user")
public class UserLoginController {


    @Autowired
    @Qualifier("consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;


    @ApiOperation(value = "系统登录")
    @SysLog("系统登录")
    @PostMapping("/login")
    public Object login(
            @ApiParam(value = "用户dto",required = true) @RequestBody SysUserDto sysUserModel) {

        String loginUrl = "http://localhost:8986/oauth/token";

        //json请求体
        Map<String, Object> map = new HashMap<>();
        map.put("client_id", "client_password");
        map.put("client_secret", "123456");
        map.put("grant_type", "password");
        map.put("username", sysUserModel.getUsername());
        map.put("password", sysUserModel.getPassword());
        String post = HttpUtil.post(loginUrl, map);
        Object parse = JSONObject.parse(post);
        return parse;
    }


    @ApiOperation(value = "系统登出")
    @GetMapping("/logout")
    public Oauth2Response logout(
            @ApiParam(value = "请求数据",required = true)HttpServletRequest request) {
        Oauth2Response result = new Oauth2Response();
        result.setCode(20000);
        String authorization = request.getHeader("Authorization");
        String access_token = authorization.substring(7);
        SecurityContextHolder.clearContext();
        if (consumerTokenServices.revokeToken(access_token)) {
            result.setMessage("注销成功！");
            return result;
        }
        result.setMessage("注销失败！");
        return result;

    }

}
