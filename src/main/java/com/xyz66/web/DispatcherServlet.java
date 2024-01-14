package com.xyz66.web;

import com.xyz66.web.context.WebApplicationContext;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/14 10:35
 */
public class DispatcherServlet extends BaseHttpServlet {


    public DispatcherServlet(ApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("任意请求分发成功！");// 测试
    }
    
    @Override
    protected void onRefresh(ApplicationContext applicationContext) {
        System.out.println("刷新DispatcherServlet");
    }
}
