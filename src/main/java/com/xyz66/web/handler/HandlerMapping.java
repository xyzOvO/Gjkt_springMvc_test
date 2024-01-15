package com.xyz66.web.handler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Gjkt
 * @description 获取映射器
 * @since 2024/1/15 11:13
 */
public interface HandlerMapping {
    HandlerExecutionChain getHandler(HttpServletRequest request);
}
