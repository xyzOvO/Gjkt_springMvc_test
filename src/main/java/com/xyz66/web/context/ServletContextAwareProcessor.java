package com.xyz66.web.context;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;

/**
 * ServletContextAwareProcessor，用于处理 ServletContextAware 的bean
 * 
 */
public class ServletContextAwareProcessor implements BeanPostProcessor {

    private ServletContext servletContext;

    private ServletConfig servletConfig;


    public ServletContextAwareProcessor(ServletContext servletContext,ServletConfig servletConfig){
        this.servletConfig = servletConfig;
        this.servletContext = servletContext;
    }
    public ServletContextAwareProcessor(ServletContext servletContext){
        this(servletContext,null);
    }
    public ServletContextAwareProcessor(ServletConfig servletConfig){
        this(null,servletConfig);
    }

    public ServletConfig getServletConfig() {
        return servletConfig;
    }

    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 用于处理 ServletContextAware 的bean
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (getServletContext() != null && bean instanceof ServletContextAware) {
            // 设置ServletContext
            ((ServletContextAware) bean).setServletContext(getServletContext());
        }
        if (getServletConfig() != null && bean instanceof ServletConfigAware) {
            // 设置ServletConfig
            ((ServletConfigAware) bean).setServletConfig(getServletConfig());
        }
        return bean;
    }
}