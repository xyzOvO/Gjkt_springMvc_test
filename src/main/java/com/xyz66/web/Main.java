package com.xyz66.web;

import com.xyz66.web.context.AnnotationConfigWebApplicationContext;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/13 9:49
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.refresh();

        System.out.println(context.getBean(Service.class));
    }
}
