
package com.aplan.aspect;

import com.aplan.annotation.ControllerSetter;
import com.aplan.utils.BeanSetterUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author lgh
 */
@Aspect
@Component
public class BeanSetterAspect {


    @Around("@annotation(controllerSetter)")
    public Object around(ProceedingJoinPoint joinPoint, ControllerSetter controllerSetter) throws Throwable {
        // 请求数据
        Object[] args = joinPoint.getArgs();
        BeanSetterUtil.removeAddress(args[0]);

        // 响应数据
        Object proceed = joinPoint.proceed();
        return proceed;
    }


}
