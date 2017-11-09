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
import com.hns.iusp.base.BaseController;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Software;
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
@RequestMapping("/admin/software")
public class SoftwareController extends BaseController {

    @Autowired
    private SoftwareService softwareService;

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param type
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/toListSoftwarePage")
    public String toListSoftwarePage(HttpServletRequest request, String type) {
        request.setAttribute("type", type);
        return "software/articleList.ftl";
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
    @RequestMapping("/querySoftwareList")
    public String querySoftwareList(HttpServletRequest request, Software software, Integer pageNumber, Integer pageSize) {
    	String stype = request.getParameter("gold");
    	String pvalue =  request.getParameter("pvalue");
    	if("downName".equals(stype)){
    		software.setDownName(pvalue);
    	}
    	if("uploader".equals(stype)){
    		software.setUploader(pvalue);
    	}
    	QueryResult<Software> queryResult = softwareService.querySoftwareForPage(software, pageNumber, pageSize);
        request.setAttribute("queryResult", queryResult);

        // 查询参数回写
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("downName", null != software ? software.getDownName() : "");
        pageParam.put("uploader", null != software ? software.getUploader() : "");
        request.setAttribute("pageParam", pageParam);
        return "admin/software/softwareQueryList.ftl";
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
    @RequestMapping("/addSoftware")
    public String addSoftware(HttpServletRequest request, HttpServletResponse response, Software software) {

    	JsonObject jsonObject = new JsonObject();
    	jsonObject.addProperty("flag", false);

    	if (null == software) {
    		jsonObject.addProperty("message", "文章数据不存在，请核实");
    	} else {
    		SessionUser currentUser = (SessionUser)request.getSession().getAttribute(Constant.Session.SESSION_NAME);
    		if(currentUser == null){
    			jsonObject.addProperty("message", "请登录后操作");
    		}else{
    			software.setUploader(currentUser.getUserName());
    			int result = softwareService.addSoftware(software);
    			if (result > 0) {
    				jsonObject.addProperty("flag", true);
    				jsonObject.addProperty("message", "添加成功！");
    			} else {
    				jsonObject.addProperty("message", "系统异常，新增文章失败");
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
    @RequestMapping("/deleteSoftware")
    public String deleteArticle(HttpServletRequest request, HttpServletResponse response, String ids) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        if (StringUtil.isEmpty(ids)) {
            jsonObject.addProperty("message", "请选择需要删除的数据");
        } else {
            String[] idArr = ids.split(",");
            try {
                for (String id : idArr) {
                	softwareService.deleteSoftwareById(Integer.parseInt(id));
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
    public String addSoftwareForward(HttpServletRequest request, HttpServletResponse response) {
          return "admin/software/insertSoft.ftl";
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
    public String searchSoftwareForward(HttpServletRequest request, HttpServletResponse response,String type) {
    	 request.setAttribute("type", type);// add wangxuan 2014-11-25 
      return "admin/software/softD.ftl";
    } 
    
    @RequestMapping("/upload")  
    public String upload(@RequestParam("downName") String downName,@RequestParam("descinfo") String descinfo,@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response, Software software) throws IOException {  
  
    	
   
			PrintWriter out = response.getWriter();
 
    	JsonObject jsonObject = new JsonObject();
    	System.out.println("开始");  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        System.out.println(sdf.format(new Date()));
        SessionUser currentUser = (SessionUser)request.getSession().getAttribute(Constant.Session.SESSION_NAME);
        String user = "";
        if(currentUser == null){
        	  return "index.ftl";
        }else{
        	user = currentUser.getUserName();
        }
        String path = request.getSession().getServletContext().getRealPath("upload")+"//"+user+"//"+sdf.format(new Date());  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }   
        //保存  
        try {  
            file.transferTo(targetFile);
            software.setDownaddr("upload"+"/"+user+"/"+sdf.format(new Date())+fileName);
            software.setUploader(user);
            softwareService.addSoftware(software);
            jsonObject.addProperty("success", true);
            jsonObject.addProperty("message", "上传成功！");
        } catch (Exception e) {  
        	 jsonObject.addProperty("message", "系统异常，新增失败");
             e.printStackTrace();  
        }  
        
		//modify by wangtao 2014-11-23
        //BasicAjaxUtil.writeJson(response, jsonObject.toString());
        //return null;
        //  return "admin/software/softD.ftl";
 		return "admin/software/loadWaiting.ftl";//添加上传等待进度条界面。
 		//end modify by wangtao 2014-11-23
    }
}
