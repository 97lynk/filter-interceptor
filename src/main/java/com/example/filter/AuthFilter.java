package com.example.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class AuthFilter implements Filter {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String apiKey = httpServletRequest.getHeader("api_key");

        if (apiKey == null) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("status", "ERROR");
            map.put("message", "Api key not found");
            ObjectMapper objectMapper = new ObjectMapper();
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(map));
            httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());

            logger.info("Api key not found");
            return;
        }

        logger.info("Found api key=" + apiKey);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
