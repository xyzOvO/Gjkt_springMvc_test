package com.xyz66.web.handler;

/**
 * @author Gjkt
 * @description 包装HandlerMethod ，用于添加拦截器功能
 * @since 2024/1/15 11:12
 */
public class HandlerExecutionChain {
    private final HandlerMethod handlerMethod;

    public HandlerExecutionChain(HandlerMethod handlerMethod) {
        this.handlerMethod = handlerMethod;
    }
}
