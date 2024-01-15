package com.xyz66.web.handler;

import com.sun.corba.se.impl.presentation.rmi.ExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/15 11:17
 */
public class RequestMappingHandlerMapping extends AbstractHandlerMapping {

    // 根据路径返回Handler
    @Override
    protected HandlerMethod getHandlerInternal(HttpServletRequest request) {
        return null;
    }
}