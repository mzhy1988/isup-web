/*
 * Copyright (C), 2002-2013, 苏宁易购电子商务有限公司
 * FileName: BaseController.java
 * Author:   12061772
 * Date:     2013-7-22 上午9:33:11
 * Description: Controller基类     
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.base;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.utils.StringUtil;

/**
 * 〈Controller基类〉
 * 
 * @author 12061772
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BaseController {

    protected static final String FORWARD_PATH = "forward:";

    /**
     * 
     * 功能描述: <br>
     * 〈转化表单中时间格式〉
     * 
     * @param request
     * @param binder
     * @throws Exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        binder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
            /**
             * Sets the property value by parsing a given String. May raise java.lang.IllegalArgumentException if either
             * the String is badly formatted or if this kind of property can't be expressed as text.
             * 
             * @param text The string to be parsed.
             */
            public void setAsText(String value) {
                Date parsedDate = null;
                if (StringUtil.isEmpty(value)) {
                    setValue(null);
                } else if (10 == value.length()) {// 年月日
                    try {
                        parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(value);
                        setValue(new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (19 == value.length()) {// 到秒
                    try {
                        parsedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
                        setValue(new Timestamp(parsedDate.getTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else {
                    setValue(null);
                }
            }
        });
    }

    /**
     * 功能描述: <br>
     * 〈重定向链接〉
     * 
     * @param url 链接
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected void redirectUrl(String url, HttpServletResponse response) {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String getUserName(HttpServletRequest request) {
        SessionUser user = (SessionUser) request.getSession().getAttribute("sessionUser");
        return null == user ? null : user.getUserName();
    }

    protected SessionUser getSessionUser(HttpServletRequest request) {
        SessionUser user = (SessionUser) request.getSession().getAttribute("sessionUser");
        return user;
    }

}