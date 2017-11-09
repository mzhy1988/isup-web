/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: ArticleController.java
 * Author:   Tommy Xu
 * Date:     Nov 11, 2014 12:17:12 AM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.admin.controller;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.hns.iusp.admin.bean.SchoolTermBean;
import com.hns.iusp.admin.service.SchoolTermService;
import com.hns.iusp.base.BaseController;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.constant.Constant;
import com.hns.iusp.common.service.SoftwareService;
import com.hns.iusp.utils.BasicAjaxUtil;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;


/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/admin/term")
public class SchoolTermController extends BaseController {

    @Autowired
    private SchoolTermService schoolTermService;

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param article
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/addSchoolTerm")
    public String addSchoolTerm(HttpServletRequest request, HttpServletResponse response, SchoolTermBean bean) {

    	JsonObject jsonObject = new JsonObject();
    	jsonObject.addProperty("flag", false);

    	if (null == bean) {
    		jsonObject.addProperty("message", "文章数据不存在，请核实");
    	} else {
    		SessionUser currentUser = (SessionUser)request.getSession().getAttribute(Constant.Session.SESSION_NAME);
    		if(currentUser == null){
    			jsonObject.addProperty("message", "请登录后操作");
    		}else{
    			int result = schoolTermService.addSchoolTerm(bean);
    			if (result > 0) {
    				jsonObject.addProperty("flag", true);
    				jsonObject.addProperty("message", "添加成功！");
    			} else {
    				jsonObject.addProperty("message", "系统异常");
    			}
    		}
    	}

    	BasicAjaxUtil.writeJson(response, jsonObject.toString());

    	return null;
    }
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param response
     * @param ids
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/deleteSchoolTerm")
    public String deleteSchoolTerm(HttpServletRequest request, HttpServletResponse response, String ids) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        if (StringUtil.isEmpty(ids)) {
            jsonObject.addProperty("message", "请选择需要删除的数据");
        } else {
            String[] idArr = ids.split(",");
            try {
                for (String id : idArr) {
                	schoolTermService.deleteSchoolTermById(Integer.parseInt(id));
                }
                jsonObject.addProperty("flag", true);
                jsonObject.addProperty("message", "删除成功");
            } catch (RuntimeException e) {
                jsonObject.addProperty("message", "系统异常：" + e.getMessage());
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param article
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/addSoftwareForward")
    public String addSoftwareForward(HttpServletRequest request, HttpServletResponse response,String type) {
    	 request.setAttribute("type", type);  //add wangxuan 2014-11-25 
          return "admin/termmanger/insertTerm.ftl";
    }
    
       /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param article
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/searchSoftwareForward")
    public String searchSoftwareForward(HttpServletRequest request, HttpServletResponse response) {
      return "admin/software/softD.ftl";
    } 
}
