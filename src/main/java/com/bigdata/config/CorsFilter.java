package com.bigdata.config;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Desciption 处理跨域请求
 * Create By  li.bo
 * CreateTime 2018/1/31 14:53
 * UpdateTime 2018/1/31 14:53
 */
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        String uri = request.getRequestURI().substring(request.getContextPath().length());
        // 登录、身份校验时，直接请求
        if (request.getRequestURI().contains("/cors") || request.getRequestURI().contains("/login") || request.getRequestURI().contains("oauth/token")) {
            chain.doFilter(req, res);
        } else if (request.getRequestURI().contains("/rest")) {// 浏览器的非登录请求，统一进行跨域处理

            String method = request.getMethod();
            String realUri = uri.substring(5);

            String authorization = request.getHeader("Authorization") == null ? "" : request.getHeader("Authorization");

            // 判断是否跨域的请求
            if ("OPTIONS".equals(method) || !authorization.startsWith("bearer ")) {
                RequestDispatcher rd = request.getRequestDispatcher("/cors");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(realUri);
                rd.forward(request, response);
            }
        } else {// 跨域请求转发，直接请求
            chain.doFilter(req, res);
        }
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}
