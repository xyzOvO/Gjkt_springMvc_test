package com.xyz66.web.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.support.ApplicationObjectSupport;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractHandlerMapping implements HandlerMapping {
    protected int order;
    
    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) {
        final HandlerMethod handlerMethod = getHandlerInternal(request);
        HandlerExecutionChain executionChain = new HandlerExecutionChain(handlerMethod);
        // todo：拦截器
        return executionChain;
    }
    
    protected abstract HandlerMethod getHandlerInternal(HttpServletRequest request);
    
    protected void setOrder(int order) {
        this.order = order;
    }
}