package com.aplan.exception.login;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lgsh
 * @Description:
 * @date 2021/10/25 11:58
 */


@Component
@Aspect
@Slf4j
public class AuthTokenAspect {



    @Around("execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        Map<String, Object> map = new HashMap<>();
        Object proceed = null;
        try {
            proceed = pjp.proceed();
        } catch (InvalidGrantException invalidGrantException) {
            String message = invalidGrantException.getMessage();
            map.put("code",30000);
            map.put("message","获取令牌失败，账号或者密码错误！");

            if (message.equals("User is disabled")){
                map.put("message","账号已被禁用");

            }
        }

        if (proceed != null) {
            ResponseEntity<OAuth2AccessToken> responseEntity = (ResponseEntity<OAuth2AccessToken>)proceed;
            OAuth2AccessToken body = responseEntity.getBody();
            if (responseEntity.getStatusCode().is2xxSuccessful()) {



                map.put("access_token",body.getValue());
                map.put("token_type",body.getTokenType());
                map.put("expires_in",body.getExpiresIn());
                map.put("code",20000);
                map.put("message","获取令牌成功");
            } else {
                log.error("error:{}", responseEntity.getStatusCode().toString());

                map.put("code",30000);
                map.put("message","获取令牌失败，账号或者密码错误！");
            }
        }
        return ResponseEntity.status(200).body(map);
    }
}
