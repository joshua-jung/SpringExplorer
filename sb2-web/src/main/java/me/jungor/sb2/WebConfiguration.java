package me.jungor.sb2;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.OptionalValidatorFactoryBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by joshua on 29/03/2018.
 */
@Configuration
@Slf4j
public class WebConfiguration {


    /**
     * 启用过滤器方法一
     * Filter 的子类自动加入过滤器调用链
     */
//    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

    /**
     * 启用过滤器方法二
     * 加入过滤器调用链的同时，标注更多属性
     *
     * @return
     */
//    @Bean
    public FilterRegistrationBean filterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
//        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }

    public class MyFilter implements Filter {
        public void destroy() {
            // TODO Auto-generated method stub
        }

        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {
            // TODO Auto-generated method stub
            HttpServletRequest request = (HttpServletRequest) srequest;
            log.info("Here is MyFilter, url is {}", request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }

        public void init(FilterConfig arg0) throws ServletException {
            // TODO Auto-generated method stub
        }
    }

//    Filter Configuration End



}
