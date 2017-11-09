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

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.hns.iusp.base.BaseController;
import com.hns.iusp.common.bean.OrganizationTree;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Teacher;
import com.hns.iusp.common.bean.User;
import com.hns.iusp.common.service.OrganizationService;
import com.hns.iusp.common.service.UserService;
import com.hns.iusp.utils.BasicAjaxUtil;
import com.hns.iusp.utils.StringUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/changePwd")
    public String toChangePwdPage(HttpServletRequest request) {
        return "ucenter/changePwd.ftl";
    }

    @RequestMapping("/changePassword")
    public String changePassword(HttpServletRequest request, HttpServletResponse response, String oldPassword,
            String newPassword) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        SessionUser sessionUser = this.getSessionUser(request);
        if (StringUtil.isEmpty(oldPassword) || StringUtil.isEmpty(newPassword)) {
            jsonObject.addProperty("message", "原密码或新密码不能为空！");
        } else {
            User tempUser = userService
                    .findUserByUserNameAndPwd(sessionUser.getUserName(), StringUtil.md5(oldPassword));
            if (null == tempUser) {
                jsonObject.addProperty("message", "原密码不正确！");
            } else {
                User user = new User();
                user.setUserName(sessionUser.getUserName());
                user.setPassword(StringUtil.md5(newPassword));
                int result = userService.modifyPassword(user);
                if (result <= 0) {
                    jsonObject.addProperty("message", "密码修改失败，联系运维人员处理！");
                } else {
                    jsonObject.addProperty("flag", true);
                }
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    @RequestMapping("/modifyBasicInfo")
    public String modifyUserBasicInfo(HttpServletRequest request) {
        request.setAttribute("teacher", userService.findTeacherByUserName(this.getUserName(request)));
        return "ucenter/modifyBasicInfo.ftl";
    }

    @RequestMapping("/changeTeacherInfo")
    public String changeTeacherInfo(HttpServletRequest request, HttpServletResponse response, Teacher teacher) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        SessionUser sessionUser = this.getSessionUser(request);
        if (null == teacher) {
            jsonObject.addProperty("message", "参数为空！");
        } else {
            teacher.setUserName(sessionUser.getUserName());
            int result = userService.modifyTeacherInfo(teacher);
            if (result <= 0) {
                jsonObject.addProperty("message", "基本信息修改失败，联系运维人员处理！");
            } else {
                jsonObject.addProperty("flag", true);
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/orgQuery")
    public String orgQuery(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        if (StringUtil.isEmpty(id)) {
            id = "00";
        }

        List<OrganizationTree> treeList = organizationService.queryOrgTreeListByParentCode(id);
        BasicAjaxUtil.writeJsonObj(response, treeList);
        
        return null;
    }
    
    @RequestMapping("/findTeacher")
    public String findTeacher(HttpServletRequest request, HttpServletResponse response) {

        String id = request.getParameter("id");
        Teacher teacher = userService.findTeacherById(Integer.parseInt(id));
        BasicAjaxUtil.writeJsonObj(response, teacher);
        
        return null;
    }
}
