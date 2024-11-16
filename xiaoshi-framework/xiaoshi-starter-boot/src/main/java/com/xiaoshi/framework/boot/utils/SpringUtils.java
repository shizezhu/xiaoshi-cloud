package com.xiaoshi.framework.boot.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;

/**
 * spring 工具类
 *
 * @author xiaoshi
 * @since 2024/11/14 下午8:42
 */
@Slf4j
public class SpringUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringUtils.context = context;
    }

    public static ApplicationContext getContext() {
        return SpringUtils.context;
    }

    /**
     * 检查 Bean 是否存在
     *
     * @param beanName Bean 的名称
     * @return 如果存在返回 true，否则返回 false
     * @author xiaoshi
     * @since 2024/11/14 下午8:28
     */
    public static boolean containsBean(String beanName) {
        return context != null && beanName != null && context.containsBean(beanName);
    }

    /**
     * 根据类型获取 Bean
     *
     * @param clazz Bean 的类型
     * @return Bean
     * @author xiaoshi
     * @since 2024/11/14 下午8:28
     */
    public static <T> T getBean(Class<T> clazz) {
        if (context == null || clazz == null) {
            return null;
        }
        return context.getBean(clazz);
    }

    /**
     * 根据beanId获取Bean
     *
     * @param beanId beanId
     * @return Bean
     * @author xiaoshi
     * @since 2024/11/14 下午8:28
     */
    public static <T> T getBean(String beanId) {
        if (context == null || beanId == null) {
            return null;
        }
        return (T) context.getBean(beanId);
    }

    /**
     * @param beanName Bean 的名称
     * @param clazz    Bean 的类型
     * @return
     * @author xiaoshi
     * @since 2024/11/14 下午8:33
     */

    public static <T> T getBean(String beanName, Class<T> clazz) {
        if (context == null || beanName == null || clazz == null) {
            return null;
        }
        return context.getBean(beanName, clazz);
    }

    /**
     * 发布事件
     *
     * @param event 事件
     * @author xiaoshi
     * @since 2024/11/14 下午8:37
     */
    public static void publishEvent(ApplicationEvent event) {
        if (context == null) {
            return;
        }
        context.publishEvent(event);
    }

    /**
     * 获取当前环境的所有激活 Profile
     *
     * @return 所有激活 Profile 数组
     * @author xiaoshi
     * @since 2024/11/14 下午8:33
     */
    public static String[] getActiveProfiles() {
        if (context == null) {
            return new String[0];
        }
        return context.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前环境
     *
     * @return 当前环境
     * @author xiaoshi
     * @since 2024/11/14 下午8:33
     */
    public static String getActiveProfile() {
        String[] activeProfiles = getActiveProfiles();
        if (activeProfiles.length == 0) {
            return null;
        }
        return activeProfiles[0];
    }

}
