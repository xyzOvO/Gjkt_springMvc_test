package com.xyz66.web;

import com.xyz66.web.context.WebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.ObjectUtils;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public abstract class AbstractDispatcherServletInitializer implements WebApplicationInitializer {


    protected static final String DEFAULT_SERVLET_NAME = "dispatcher";

    protected static final String DEFAULT_FILTERS_NAME = "filter";

    @Override
    public void onStartUp(ServletContext servletContext) {

        final String servletName = getServletName();
        final AnnotationConfigApplicationContext rootContext = createRootApplicationContext();

        if (!rootContext.isActive()) {
            rootContext.refresh();// 刷新
        }

        // 放入servletContext上下文后续 DispatcherServlet 需要获取设置父容器
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, rootContext);
        // 创建web ioc放入DispatcherServlet
        final WebApplicationContext servletApplicationContext = createServletApplicationContext();
        final DispatcherServlet dispatcherServlet = new DispatcherServlet(servletApplicationContext);

        final ServletRegistration.Dynamic dynamic = servletContext.addServlet(servletName, dispatcherServlet);

        // 配置文件信息
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping(getServletMappings());
        final MultipartConfigElement multipartConfigElement = new MultipartConfigElement(null, 500000, 500000, 5);
        dynamic.setMultipartConfig(multipartConfigElement);
        // 添加过滤器
        final Filter[] filters = getServletFilters();
        if (!ObjectUtils.isEmpty(filters)) {
            for (Filter filter : filters) {
                servletContext.addFilter(getFilterName(), filter);
            }
        }

    }


    // 
    protected abstract WebApplicationContext createServletApplicationContext();

    protected abstract AnnotationConfigApplicationContext createRootApplicationContext();


    private String getServletName() {
        return DEFAULT_SERVLET_NAME;
    }

    // 过滤器
    private String getFilterName() {
        return DEFAULT_FILTERS_NAME;
    }

    // 获取包扫描配置类
    protected abstract Class<?>[] getRootConfigClasses();

    protected abstract Class<?>[] getServletConfigClasses();

    // 映射器
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    protected Filter[] getServletFilters() {
        return null;
    }


}
