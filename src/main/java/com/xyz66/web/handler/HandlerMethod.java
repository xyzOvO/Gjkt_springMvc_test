package com.xyz66.web.handler;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;

/**
 * @author Gjkt
 * @description 包装对应的路径映射方法
 * @since 2024/1/15 11:10
 */
public class HandlerMethod {
    private Object bean;
    
    private Class type;
    
    private Method method;
    
    private MethodParameter[] parameters = new MethodParameter[0];
}
