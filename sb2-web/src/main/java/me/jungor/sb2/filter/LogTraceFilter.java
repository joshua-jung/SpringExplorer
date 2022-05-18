package me.jungor.sb2.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.UUID;

/**
 * 通过 TRACE_ID 串联日志
 *
 * Created by joshua on 2018/5/9.
 */
@Slf4j
@WebFilter(filterName="logTraceFilter",urlPatterns="/*")
public class LogTraceFilter implements Filter {

    private static final String MDC_TRACE_KEY = "TRACE_ID";

    public void init(FilterConfig filterConfig) {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        MDC.put(MDC_TRACE_KEY, UUID.randomUUID().toString().replace("-", ""));

        chain.doFilter(request, response);

        MDC.clear();  // 即使处理 request 异常，也能执行到
    }

    public void destroy() {
    }

}
