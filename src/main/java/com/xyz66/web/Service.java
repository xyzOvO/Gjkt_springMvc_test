package com.xyz66.web;

import com.xyz66.web.context.ServletContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/13 9:50
 */
@Component// 声明为组件，ioc拿不到bean
public class Service implements ServletContextAware {
    /**
     * 在填充普通bean属性之后但在初始化之前调用
     * 类似于initializingbean的afterpropertiesset或自定义init方法的回调
     */
    @Override
    public void setServletContext(ServletContext servletContext) {
        System.out.println("get ServletContext,获取到ServletContext");
    }
}
