package com.hns.iusp.utils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.service.UserService;

/**
 * 该类是 在线人数统计 作者： 王涛 时间：2014.11.23
 */
public class SessionCounter implements HttpSessionListener {

    public void sessionCreated(HttpSessionEvent event) {
        ServletContext ctx = event.getSession().getServletContext();
        ApplicationContext springCtx = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());  
        UserService userService = (UserService)(springCtx.getBean("userServiceImpl"));  
        ctx.setAttribute("numSessions", userService.findLoginUserCountByOrgCode(null,null));
    }

    public void sessionDestroyed(HttpSessionEvent event) {
        ServletContext ctx = event.getSession().getServletContext();
        ApplicationContext springCtx = WebApplicationContextUtils.getWebApplicationContext(event.getSession().getServletContext());  
        UserService userService = (UserService)(springCtx.getBean("userServiceImpl"));  
        ctx.setAttribute("numSessions", userService.findLoginUserCountByOrgCode(null,null));
        SessionUser sessionUser = (SessionUser) event.getSession().getAttribute("sessionUser");
        userService.deleteLoginUserByUserName(sessionUser.getUserName());
    }
}