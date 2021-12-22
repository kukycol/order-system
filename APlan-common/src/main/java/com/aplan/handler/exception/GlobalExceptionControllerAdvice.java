package com.aplan.handler.exception;

import com.aplan.common.Oauth2Response;
import com.aplan.exception.custom.Oauth2Exception;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * @author lgsh
 * @Description:
 * @date 2021/10/25 14:03
 */
@Controller
@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionControllerAdvice.class);


    /**
     * 未知异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Oauth2Response exceptionHandler(Exception e) {
        logger.error("未知异常！原因是:", e);
        return new Oauth2Response(30000, e.getMessage());
    }


    /**
     * 参数效验异常处理器
     *
     * @param e 参数验证异常
     * @return ResponseInfo
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Oauth2Response parameterExceptionHandler(MethodArgumentNotValidException e) {
        logger.error("参数效验异常处理器！原因是:", e);

        // 获取异常信息
        BindingResult exceptions = e.getBindingResult();
        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                return new Oauth2Response(30000, fieldError.getDefaultMessage());
            }
        }
        return new Oauth2Response();
    }


    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Oauth2Exception.class)
    @ResponseBody
    public Oauth2Response exceptionHandler(Oauth2Exception e) {
        logger.error("处理自定义的业务异常！原因是:", e.getMessage());
        return new Oauth2Response(30000,e.getMessage());
    }


    // 处理空指针的异常
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public Oauth2Response exceptionHandler(NullPointerException e) {
        logger.error("发生空指针异常！原因是:", e);
        return Oauth2Response.error();
    }


    /**
     * 数据唯一性报错
     * @param e
     * @return
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Oauth2Response exceptionHandler(DuplicateKeyException e) {
        e.printStackTrace();
        if (e.getMessage().contains("java.sql.SQLIntegrityConstraintViolationException")){
            return new Oauth2Response(30000,"SQL完整性约束违背异常,数据已存在！");
        }
        return new Oauth2Response(30000,e.getMessage());
    }



}
