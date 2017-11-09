/*
 * Copyright (C), 2002-2012, 苏宁易购电子商务有限公司
 * FileName: test.java
 * Author:   12110775
 * Date:     2013-11-22 上午1:41:51
 * Description: 用户访问权限过滤
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.utils.PropertiesValUtil;
import com.hns.iusp.utils.StringUtil;

/**
 * 用户访问权限过滤<br>
 * 〈功能详细描述〉
 * 
 * @author 12110775
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class AuthFilter implements Filter {

    /**
     * LOG对象
     */
    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    /**
     * 路径匹配对象
     */
    private PathMatcher pathMatcher = null;

    /**
     * 权限忽略校验路径
     */
    private String authExceptUrlPattern = null;

    /**
     * 登陆后忽略校验路径
     */
    private String loginAfterExceptUrlPattern = null;

    /**
     * Filter初始化资源
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        pathMatcher = new AntPathMatcher();
        authExceptUrlPattern = PropertiesValUtil.val("authExceptUrlPattern");
        loginAfterExceptUrlPattern = PropertiesValUtil.val("loginAfterExceptUrlPattern");
    }

    /**
     * Filter过滤权限
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse,
     *      javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
      
        // 获得url
        String url = req.getServletPath();
        if (StringUtil.isNotEmpty(url)) {
            url = url.trim();
        }

        if (StringUtil.isNotEmpty(url) && isInExceptUrlPattern(url)) {
            chain.doFilter(request, response);
            return;
        }

        SessionUser currentUser = (SessionUser) req.getSession().getAttribute("sessionUser");
        if (null != currentUser) {
            boolean flag = true;
            if (!isInExceptUrlPatternLoginAfter(url)) {
                // 根据用户权限鉴权
                // flag = StaticDataUtil.isUserHasFunction(currentUser, true, url, null);
            }

            if (flag) {
                chain.doFilter(request, response);
                logger.info("鉴权成功！");
            } else {
                request.setAttribute("errorReason", "对不起，您无权限操作此功能！");
                logger.info("对不起，您无权限操作此功能！");
                request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
                return;
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/index.htm");
        }
    }

    /**
     * 功能描述: <br>
     * 〈判断是否是例外的url〉
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private boolean isInExceptUrlPattern(String url) {
        if (StringUtil.isNotEmpty(authExceptUrlPattern)) {
            String[] exceptUrls = authExceptUrlPattern.split(";");
            for (String exceptUrl : exceptUrls) {
                if (pathMatcher.match(exceptUrl, url)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 功能描述: <br>
     * 〈判断是否是登录后例外的url〉
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    private boolean isInExceptUrlPatternLoginAfter(String url) {
        if (StringUtil.isNotEmpty(loginAfterExceptUrlPattern)) {
            String[] exceptUrls = loginAfterExceptUrlPattern.split(";");
            for (String exceptUrl : exceptUrls) {
                if (pathMatcher.match(exceptUrl, url)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Filter销毁资源
     * 
     * @see javax.servlet.Filter#destroy()
     */
    public void destroy() {

    }
}