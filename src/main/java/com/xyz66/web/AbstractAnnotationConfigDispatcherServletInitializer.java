package com.xyz66.web;

import com.xyz66.web.context.AnnotationConfigWebApplicationContext;
import com.xyz66.web.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

public abstract class AbstractAnnotationConfigDispatcherServletInitializer extends AbstractDispatcherServletInitializer {

    protected abstract Class<?>[] getRootConfigClasses();

    protected abstract Class<?>[] getServletConfigClasses();


    public WebApplicationContext createServletApplicationContext() {
        Class<?>[] configClasses = getServletConfigClasses();
        if (!ObjectUtils.isEmpty(configClasses)) {
            final AnnotationConfigWebApplicationContext serverContext = new AnnotationConfigWebApplicationContext();
            serverContext.register(configClasses);
            return serverContext;
        } else {
            return null;
        }
    }

    public AnnotationConfigApplicationContext createRootApplicationContext() {
        Class<?>[] configClasses = getRootConfigClasses();
        if (!ObjectUtils.isEmpty(configClasses)) {
            AnnotationConfigApplicationContext rootContext = new AnnotationConfigApplicationContext();
            rootContext.register(configClasses);
            return rootContext;
        } else {
            return null;
        }
    }
}
