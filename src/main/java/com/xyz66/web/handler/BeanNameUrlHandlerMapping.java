package com.xyz66.web.handler;

import javax.servlet.http.HttpServletRequest;

/**  
* @author Gjkt
* @description 不提供实现，只说明有这个场景
* @since 2024/1/15 11:25  
*/public class BeanNameUrlHandlerMapping extends AbstractHandlerMapping{
    @Override
    protected HandlerMethod getHandlerInternal(HttpServletRequest request) {
        return null;
    }
}
