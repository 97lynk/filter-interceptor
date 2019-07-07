package com.example.filter;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@Component
public class LoggerFilter implements Filter {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        logger.info("request url=" + httpServletRequest.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);

        logger.info("response status=" + httpServletResponse.getStatus());
    }

    @Override
    public void destroy() {

    }
}
