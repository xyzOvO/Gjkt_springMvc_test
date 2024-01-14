package com.xyz66.web;

import org.springframework.util.ReflectionUtils;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 对接Servlet 3.0的接口
 */
@HandlesTypes(WebApplicationInitializer.class)// 把这个类的下面所有子类传过来
public class WebServletContainerInitializer implements ServletContainerInitializer {


    @Override
    public void onStartup(Set<Class<?>> webAppInitializerClasses, ServletContext servletContext) throws ServletException {
        if(webAppInitializerClasses.size()!=0){
            final List<WebApplicationInitializer> initializers = new ArrayList<>(webAppInitializerClasses.size());
            // 排除接口和抽象类
            for (Class<?> webAppInitializerClass : webAppInitializerClasses) {
                if (!webAppInitializerClass.isInterface() && !Modifier.isAbstract(webAppInitializerClass.getModifiers()) &&
                        // 该接口必须实现WebApplicationInitializer接口
                        WebApplicationInitializer.class.isAssignableFrom(webAppInitializerClass)){
                    try {
                        initializers.add((WebApplicationInitializer)
                                ReflectionUtils.accessibleConstructor(webAppInitializerClass).newInstance());
                    } catch (Throwable e) {
                       e.printStackTrace();
                    }
                }
            }
            // 统一调用onStartUp方法
            for (WebApplicationInitializer initializer : initializers) {
                initializer.onStartUp(servletContext);
            }
        }
    }
}
