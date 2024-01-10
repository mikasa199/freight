package com.yang.freight.filter;

import com.alibaba.fastjson.JSON;
import com.yang.freight.common.BaseContext;
import com.yang.freight.common.Return;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description:
 * @author：杨超
 * @date: 2023/12/27
 * @Copyright：
 */
@WebFilter(filterName = "LoginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(LoginCheckFilter.class);

    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        logger.info("拦截到请求：{}",request.getRequestURL());

        //1. 获取本次请求的URI
        String uri = request.getRequestURI();

        //定义不需要处理的请求路径
        String[] urls = new String[]{
                "/driver",
                "/driver/logon",
                "/driver/sendMsg",
                "/driver/logon/code",
                "/driver/login",
                "/boss",
                "/boss/logon",
                "/boss/sendMsg",
                "/boss/logon/code",
                "/boss/login",
                "/frontend/**"
        };

        //2. 判断本次请求是否需要处理
        boolean check = check(urls, uri);

        //3. 如果不需要处理直接放行
        if (check) {
            logger.info("本次请求不需要处理");
            filterChain.doFilter(request,response);
            return;
        }

        //4.1 判断后台教师登录状态，如果已登录，则直接放行
        if (null != request.getSession().getAttribute("boss")) {
            logger.info("老板已登录");

            Long bossId = (Long)request.getSession().getAttribute("boss");
            BaseContext.setCurrentId(bossId);

            filterChain.doFilter(request,response);
            return;
        }

        //4.2 判断前台学生登录状态，如果已登录，则直接放行
        if (null != request.getSession().getAttribute("driver")) {
            logger.info("司机已登录");

            Long driverId = (Long)request.getSession().getAttribute("driver");
            BaseContext.setCurrentId(driverId);

            filterChain.doFilter(request,response);
            return;
        }

        //5. 如果未登陆则返回未登录结果,通过输出流的方式向客户端页面响应数据
        //"NOTLOGIN" 要与request.js中的响应拦截器的msg一致
        logger.info("用户未登录，需要先登录");
        response.getWriter().write(JSON.toJSONString(Return.error("NOTLOGIN")));
        return;
    }

    /**
     * 路径匹配，检查本次请求是否需要放行
     * @param urls 不需拦截的url
     * @param requestURI 请求的url
     * @return
     */
    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }

}
