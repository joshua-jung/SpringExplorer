package me.jungor.sb2.web;

import lombok.extern.slf4j.Slf4j;
import me.jungor.common.constant.BaseRestCode;
import me.jungor.common.exception.BaseRunTimeException;
import me.jungor.sb2.web.rest.RestResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Controller 层下所有异常统一处理，可根据异常类型细分
 *
 * Created by joshua on 2018/5/17.
 */

@RestControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @ExceptionHandler(BaseRunTimeException.class)
    public Object customHandler(BaseRunTimeException e) {
        log.error(e.toString(), e);
        return new RestResponseEntity(e.getCode());
    }

    @ExceptionHandler(BindException.class)
    public Object bindExceptionHandler(BindException e) {
        log.error(e.toString(), e);
        return new RestResponseEntity(BaseRestCode.S400);
    }

    @ExceptionHandler(Exception.class)
    public Object allHandler(Exception e) {
        log.error(e.toString(), e);
        return new RestResponseEntity(BaseRestCode.S500);
    }

}
