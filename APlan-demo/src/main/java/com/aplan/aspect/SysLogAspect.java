
package com.aplan.aspect;

import cn.hutool.core.date.SystemClock;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aplan.annotation.SysLog;
import com.aplan.bean.model.SysLogModel;
import com.aplan.bean.model.SysUserModel;
import com.aplan.bean.model.SysUserOnlineModel;
import com.aplan.mapper.SysUserMapper;
import com.aplan.mapper.SysUserOnlineMapper;
import com.aplan.service.monitor.log.SysLogService;
import com.aplan.utils.IPHelper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 日志记录AOP
 */
@Aspect
@Component
public class SysLogAspect {

    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserOnlineMapper sysUserOnlineMapper;


    @Around("@annotation(sysLog)")
    public Object around(ProceedingJoinPoint joinPoint, SysLog sysLog) {
        // 接口开始请求的时间
        long beginTime = SystemClock.now();

        //接口响应的数据
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        // 接口结束请求的时间
        long time = SystemClock.now() - beginTime;

        // 请求数据
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String requestURI = request.getRequestURI();

        // 记录bean
        SysLogModel sysLogEntity = new SysLogModel();
        sysLogEntity.setPath(requestURI); // 请求地址

        // 操作名称
        if (sysLog != null) {
            sysLogEntity.setOperation(sysLog.value());
        }

        // 获取浏览器对象
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取浏览器信息
        Browser browser = userAgent.getBrowser();
        // 获取操作系统信息
        OperatingSystem os = userAgent.getOperatingSystem();
        // 使用的浏览器
        sysLogEntity.setBrowserName(browser.getName());
        // 使用的设备名称
        sysLogEntity.setDeviceName(os.getName());

        // 请求方式
        switch (request.getMethod()) {
            case "GET":
                sysLogEntity.setMethod("查询");
                break;
            case "POST":
                sysLogEntity.setMethod("新增");
                break;
            case "PUT":
                sysLogEntity.setMethod("更新");
                break;
            case "DELETE":
                sysLogEntity.setMethod("删除");
                break;
        }

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        if (args.length > 0) {
            String params = JSON.toJSONString(args[0]);
            sysLogEntity.setParams(params);
        }

        //设置IP地址
        sysLogEntity.setIp(IPHelper.getIpAddr());

        // 操作接口的信息
        SysUserModel sysUserModel = null;
        String access_token = null;
        if (!requestURI.equals("/sys/user/login")){
            SysUserModel principal = (SysUserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            sysLogEntity.setUsername(principal.getUsername());
            sysLogEntity.setUserId(principal.getUserId());
        }else {
            Object params = sysLogEntity.getParams();
            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(result));
            JSONObject jsonObject2 = JSONObject.parseObject(String.valueOf(params));
            Integer code = jsonObject.getInteger("code");
            if (code==20000){
                access_token = jsonObject.getString("access_token");
                sysLogEntity.setUsername(jsonObject2.getString("username"));
                sysUserModel = sysUserMapper.selectOne(new LambdaQueryWrapper<SysUserModel>().eq(SysUserModel::getUsername, sysLogEntity.getUsername()));
                sysLogEntity.setUserId(sysUserModel.getUserId());
                sysLogEntity.setMethod("登录成功");
                sysLogEntity.setToken(access_token);
            }else {
                if (StringUtils.isNotBlank(jsonObject2.getString("username"))){
                    sysLogEntity.setUsername(jsonObject2.getString("username"));
                }else {
                    sysLogEntity.setUsername("未知");
                }
                sysLogEntity.setMethod("登录失败");
            }
        }

        // 接口请求响应时间
        sysLogEntity.setTime(time);
        // 日志记录时间
        sysLogEntity.setCreateTime(new Date());
        // 响应值
        sysLogEntity.setResult(JSONObject.toJSONString(result));
        // 保存操作日志
        sysLogService.save(sysLogEntity);

        if ("登录成功".equals(sysLogEntity.getMethod())){
            SysUserOnlineModel sysUserOnlineModel = new SysUserOnlineModel();
            // 用户主键
            sysUserOnlineModel.setUserId(sysUserModel.getUserId());
            // 日志主键
            sysUserOnlineModel.setLogId(sysLogEntity.getLogId());
            // token
            sysUserOnlineModel.setToken(access_token);
            // 在线状态
            sysUserOnlineModel.setStatus(0);
            // 保存在线用户记录
            sysUserOnlineMapper.insert(sysUserOnlineModel);
        }
        return result;
    }

}
