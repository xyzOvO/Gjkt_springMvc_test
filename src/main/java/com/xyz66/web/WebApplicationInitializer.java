package com.xyz66.web;

import javax.servlet.ServletContext;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/14 10:32
 */
public interface WebApplicationInitializer {
    void onStartUp(ServletContext servletContext);
}
