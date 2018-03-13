package com.bigdata.security;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desciption 角色权限拦截（暂未处理）
 * Create By  li.bo
 * CreateTime 2018/3/13 10:28
 * UpdateTime 2018/3/13 10:28
 */
public class RolePermissionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        System.out.println(">>>RolePermissionInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");

        return true;// 只有返回true才会继续向下执行，返回false取消当前请求

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,

                           ModelAndView modelAndView) throws Exception {

        System.out.println(">>>RolePermissionInterceptor>>>>>>>请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)

            throws Exception {

        System.out.println(">>>RolePermissionInterceptor>>>>>>>在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");

    }

}
