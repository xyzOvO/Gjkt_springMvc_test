package com.xyz66.web;

import com.xyz66.web.context.WebApplicationContext;
import com.xyz66.web.handler.HandlerMapping;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.expression.spel.ast.BeanReference;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * @author Gjkt
 * @description
 * @since 2024/1/14 10:35
 */
public class DispatcherServlet extends BaseHttpServlet {

    private List<HandlerMapping> handlerMappings = new ArrayList<>();
    private Properties defaultStrategies;// 
    public static final String DEFAULT_STRATEGIES_PATH = "DispatcherServlet.properties";// 默认配置文件路径



    public DispatcherServlet(ApplicationContext webApplicationContext) {
        super(webApplicationContext);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("任意请求分发成功！");// 测试
    }
    
    @Override
    protected void onRefresh(ApplicationContext webApplicationContext) {
        System.out.println("刷新DispatcherServlet");
        initHandlerMapping(webApplicationContext);// 传进来一个容器
//        initHandlerMethodAdapter(webApplicationContext);
//        initHandlerException(webApplicationContext);
    }

    private void initHandlerMapping(ApplicationContext webApplicationContext) {
        // 从容器中拿
        final Map<String, HandlerMapping> map = BeanFactoryUtils.beansOfTypeIncludingAncestors(webApplicationContext, HandlerMapping.class, true, false);
        if (!ObjectUtils.isEmpty(map)){
            this.handlerMappings = new ArrayList<>(map.values());
        }else {
            // 则从默认配置文件中拿
            this.handlerMappings.addAll(getDefaultStrategies(webApplicationContext,HandlerMapping.class));
        }
//        this.handlerMappings.sort(Comparator.comparingInt(Ordered::getOrder));
    }

    /**
     * 默认配置文件中获取        
     */
    protected <T> List<T> getDefaultStrategies(ApplicationContext context, Class<T> strategyInterface) {
        if (defaultStrategies == null) {
            try {
                // 根据路径获取资源
                ClassPathResource resource = new ClassPathResource(DEFAULT_STRATEGIES_PATH, DispatcherServlet.class);
                defaultStrategies = PropertiesLoaderUtils.loadProperties(resource);
            } catch (IOException ex) {
                throw new IllegalStateException("Could not load '" + DEFAULT_STRATEGIES_PATH + "': " + ex.getMessage());
            }
        }

        String key = strategyInterface.getName();
        String value = defaultStrategies.getProperty(key);// 当前的绝对路径名
        if (value != null) {
            String[] classNames = StringUtils.commaDelimitedListToStringArray(value);
            List<T> strategies = new ArrayList<>(classNames.length);
            for (String className : classNames) {
                try {
                    Class<?> clazz = ClassUtils.forName(className, DispatcherServlet.class.getClassLoader());
                    Object strategy = createDefaultStrategy(context, clazz);
                    strategies.add((T) strategy);
                } catch (ClassNotFoundException ex) {
                    throw new BeanInitializationException(
                            "Could not find DispatcherServlet's default strategy class [" + className +
                                    "] for interface [" + key + "]", ex);
                } catch (LinkageError err) {
                    throw new BeanInitializationException(
                            "Unresolvable class definition for DispatcherServlet's default strategy class [" +
                                    className + "] for interface [" + key + "]", err);
                }
            }
            return strategies;
        } else {
            return Collections.emptyList();
        }
    }
    protected Object createDefaultStrategy(ApplicationContext context, Class<?> clazz) {
        return context.getAutowireCapableBeanFactory().createBean(clazz);
    }
}
