package com.example.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableWebMvc
public class Config implements WebMvcConfigurer {

    @Autowired
    private LoggerFilter loggerFilter;

    @Autowired
    private AuthFilter authFilter;

    @Bean
    public FilterRegistrationBean<LoggerFilter> loggerFilterFilterRegistrationBean() {
        FilterRegistrationBean<LoggerFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(loggerFilter);
        bean.setOrder(2);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(authFilter);
        bean.setUrlPatterns(Arrays.asList("/private/*"));
        bean.setOrder(1);
        return bean;
    }

    @Autowired
    private MetricInterceptor metricInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(metricInterceptor)
                .addPathPatterns("/public/**");
    }
}
