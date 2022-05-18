package me.jungor.sb2.web;

import lombok.extern.slf4j.Slf4j;
import me.jungor.sb2.web.rest.RestResponseEntity;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Reference：https://my.oschina.net/u/1757225/blog/1543715
 *
 * Created by joshua on 2018/6/8.
 */
@RestControllerAdvice
@Slf4j
public class JsonResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter returnType,
                                  MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if(!(o instanceof RestResponseEntity)) {
            return new RestResponseEntity(o);
        }
        return o;
    }
}