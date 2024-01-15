package com.xyz66.web;

import com.xyz66.web.context.AbstractRefreshableWebApplicationContext;
import com.xyz66.web.context.WebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.http.HttpServlet;

/**
 * 模板设计模式
 */
public abstract class BaseHttpServlet extends HttpServlet {

    // 子容器
    private ApplicationContext webApplicationContext;

    public BaseHttpServlet(ApplicationContext webApplicationContext) {
        this.webApplicationContext = webApplicationContext;
    }

    @Override
    public void init(){
        initServletContext();
    }

    private void initServletContext() {
        // 获取父容器
        ApplicationContext rootContext = (ApplicationContext) getServletContext().getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        AbstractRefreshableWebApplicationContext cwc = null;
        // 在springboot场景下会根据当前存在类创建不同ioc,在boot下直接不管
        if (this.webApplicationContext != null) {
            // 需要转换
            if (!(this.webApplicationContext instanceof AnnotationConfigApplicationContext)){
                cwc = (AbstractRefreshableWebApplicationContext) this.webApplicationContext;
                // 设置父容器
                if (cwc.getParent() == null) {
                    cwc.setParent(rootContext);
                }
                // 容器刷新
                if (!cwc.isActive()) {
                    // 刷新
                    cwc.refresh();
                }
                // 配置上下文
                cwc.setServletConfig(getServletConfig());
                cwc.setServletContext(getServletContext());
            }

            onRefresh(webApplicationContext);
        }
    }

    protected abstract void onRefresh(ApplicationContext webApplicationContext);
}
