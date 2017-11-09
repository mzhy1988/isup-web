/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: LoginController.java
 * Author:   dong
 * Date:     Nov 4, 2014 7:43:00 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.hns.iusp.base.BaseController;
import com.hns.iusp.common.bean.Article;
import com.hns.iusp.common.bean.LoginUser;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Software;
import com.hns.iusp.common.bean.User;
import com.hns.iusp.common.constant.Constant;
import com.hns.iusp.common.service.ArticleService;
import com.hns.iusp.common.service.SoftwareService;
import com.hns.iusp.common.service.UserService;
import com.hns.iusp.filter.AuthFilter;
import com.hns.iusp.utils.BasicAjaxUtil;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;
import com.hns.iusp.workinglog.bean.WorkingLog;
import com.hns.iusp.workinglog.service.WorkingLogService;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
public class HomeController extends BaseController {

    /**
     * LOG对象
     */
    private static final Logger logger = Logger.getLogger(AuthFilter.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private SoftwareService softwareService;
    
    @Autowired
    private WorkingLogService workingLogService;
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {

        List<Article> newsList = articleService.queryArticleByLimit("01", 2);
        List<Article> announceList = articleService.queryArticleByLimit("02", 5);
        List<Article> znList = articleService.queryArticleByLimit("03", 5);
        //List<Software> softList = softwareService.querySoftwareByLimit(5);
        List<Software> sfList = softwareService.querySoftwareByLimit("01", 5);        
        request.setAttribute("newsList", newsList);
        request.setAttribute("announceList", announceList);
        request.setAttribute("znList", znList);
        request.setAttribute("softList", sfList);

        SessionUser sessionUser = this.getSessionUser(request);
        if (null != sessionUser) {
            if (Constant.Role.ROLECODE_ADMIN.equals(sessionUser.getRoleCode())) {
                return "redirect:admin.htm";
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                WorkingLog nowLog = workingLogService
                        .findWorkingLogByLogTime(sdf.format(new Date()), this.getUserName(request));
                request.setAttribute("nowDailySubmited", null == nowLog ? "N" : "Y");

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, -1);
                WorkingLog lastLog = workingLogService
                        .findWorkingLogByLogTime(sdf.format(cal.getTime()), this.getUserName(request));
                request.setAttribute("lastDailySubmited", null == lastLog ? "N" : "Y");
                
                request.setAttribute("sessionCount", userService.findLoginUserCountByOrgCode(null,null));
                return "ucenter/ucenter.ftl";
            }
        }

        return "index.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈登录校验〉
     * 
     * @param request <code>HttpServletRequest</code>对象
     * @param response <code>HttpServletResponse</code>对象
     * @return 跳转路径
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        // 校验用户名
        String userName = request.getParameter("userName");
        // 校验密码
        String userPwd = request.getParameter("password");

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPwd)) {
            jsonObject.addProperty("message", "用户名或 密码不能为空！");
        } else {
            // 查找登录信息
            User user = userService.findUserByUserNameAndPwd(userName, StringUtil.md5(userPwd));

            if (null == user) {
                jsonObject.addProperty("message", "用户名和密码不匹配！");
            } else {
                SessionUser currentUser = userService.findSessionUserByUserName(userName, user.getRoleCode());
                if (null == currentUser) {
                    jsonObject.addProperty("message", "组织数据不全，请联系运维人员处理！");
                } else {
                    currentUser.setRoleCode(user.getRoleCode());
                    logger.info("用户：" + currentUser.getUserName() + "-登入系统!");
                    request.getSession().setAttribute(Constant.Session.SESSION_NAME, currentUser);
                    LoginUser loginUser = new LoginUser();
                    loginUser.setOrgCode(currentUser.getOrgCode());
                    loginUser.setUserName(currentUser.getUserName());
                    loginUser.setRealName(currentUser.getRealName());
                    userService.addLoginUser(loginUser);
                    jsonObject.addProperty("flag", true);
                }
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 
     * 功能描述: 注销
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/indexLogout")
    public String indexLogout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute("sessionUser");
        return "include/loginInfo.ftl";
    }

    /**
     * 功能描述: 注销
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        return "redirect:index.htm";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/loadLoginInfo")
    public String loadLoginInfo() {
        return "include/loginInfo.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈登录校验〉
     * 
     * @param request <code>HttpServletRequest</code>对象
     * @param response <code>HttpServletResponse</code>对象
     * @return 跳转路径
     */
    @RequestMapping("/login1")
    public String login1(HttpServletRequest request, HttpServletResponse response) {

        String forwardPath = "forward:index.htm";

        // 校验用户名
        String userName = request.getParameter("userName");
        // 校验密码
        String userPwd = request.getParameter("password");

        if (StringUtil.isEmpty(userName) || StringUtil.isEmpty(userPwd)) {
            request.setAttribute("errorCode", "unInvaid");
            request.setAttribute("userName", userName);
            logger.warn("登录：用户名或 密码不能为空！");
            return forwardPath;
        }

        // 查找登录信息
        User user = userService.findUserByUserNameAndPwd(userName, userPwd);

        if (null == user) {
            request.setAttribute("errorCode", "notMatch");
            request.setAttribute("userName", userName);
            logger.warn("登录：用户名和密码不匹配！UserName:" + userName);
            return forwardPath;
        } else {
            SessionUser currentUser = new SessionUser();
            logger.info("用户：" + currentUser.getUserName() + "-登入系统!");
            request.getSession().setAttribute(Constant.Session.SESSION_NAME, currentUser);
            this.redirectUrl("index.htm", response);
        }

        return null;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/ucenter")
    public String toUCenterPage(HttpServletRequest request) {
        List<Article> newsList = articleService.queryArticleByLimit("01", 2);
        List<Article> announceList = articleService.queryArticleByLimit("02", 5);
        List<Article> znList = articleService.queryArticleByLimit("03", 5);
        List<Software> softList = softwareService.querySoftwareByLimit("01",5);
        request.setAttribute("newsList", newsList);
        request.setAttribute("announceList", announceList);
        request.setAttribute("znList", znList);
        request.setAttribute("softList", softList);

        return "ucenter/ucenter.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param response
     * @param id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/article")
    public String findArticleById(HttpServletRequest request, HttpServletResponse response, String id) {

        if (StringUtil.isEmpty(id) || !StringUtil.isNumeric(id)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }

        Article article = articleService.findArticleById(Integer.parseInt(id));
        request.setAttribute("article", article);
  	    String content =  new String(article.getContent()==null? "".getBytes():article.getContent());
	    request.setAttribute("content", content);
        return "home/article.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/hot")
    public String toNewsArticlePage(HttpServletRequest request) {
        request.setAttribute("type", "01");
        return "home/articleList.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/announcement")
    public String toAnnouncementArticlePage(HttpServletRequest request) {
        request.setAttribute("type", "02");
        return "home/articleList.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/guide")
    public String toGuideArticlePage(HttpServletRequest request) {
        request.setAttribute("type", "03");
        return "home/articleList.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param article
     * @param pageNumber
     * @param pageSize
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/queryHomeArticleList")
    public String queryArticleList(HttpServletRequest request, Article article, Integer pageNumber, Integer pageSize) {

        QueryResult<Article> queryResult = articleService.queryArticleForPage(article, pageNumber, pageSize);
        request.setAttribute("queryResult", queryResult);

        // 查询参数回写
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("title", null != article ? article.getTitle() : "");
        pageParam.put("type", null != article ? article.getType() : "");
        request.setAttribute("pageParam", pageParam);

        return "home/articleQueryList.ftl";
    }

    /**
     * 
     * 测试直接进入发布后台
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, HttpServletResponse response) {
        // request.getSession().removeAttribute("sessionUser");
        return "admin/admin.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param response
     * @param id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/download")
    public String download(HttpServletRequest request, HttpServletResponse response, String id) {

        if (StringUtil.isEmpty(id) || !StringUtil.isNumeric(id)) {
            return null;
        }

        Software soft = softwareService.findSoftwareById(Integer.parseInt(id));
        if (null == soft) {
            return null;
        }

        String remoteUrl = request.getSession().getServletContext().getRealPath("upload") + soft.getDownaddr();

        try {

            String filename = URLEncoder.encode(soft.getDownName() + "." + StringUtil.getFileExt(soft.getDownaddr()),
                    "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/x-download; charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);

            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;

            try {
                // 获取网络输入流
                bis = new BufferedInputStream(new FileInputStream(remoteUrl));
                // 建立文件
                bos = new BufferedOutputStream(response.getOutputStream());

                int bytesRead;
                byte[] buff = new byte[1024];

                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }
            } finally {
                if (bis != null) {
                    bis.close();
                }

                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            }

        } catch (IOException e) {
        }

        return null;
    }

    @RequestMapping("importUser")
    public String importUser(HttpServletRequest request) {

        try {
            FileInputStream inputStream = new FileInputStream("C:\\user.xls");
            userService.analysisDataFromExcel(inputStream, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;

    }
}
