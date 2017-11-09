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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonObject;
import com.hns.iusp.base.BaseController;
import com.hns.iusp.common.bean.Article;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.constant.Constant;
import com.hns.iusp.common.service.ArticleService;
import com.hns.iusp.utils.BasicAjaxUtil;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;
import java.sql.Timestamp;
import  org.apache.ibatis.type.BlobTypeHandler;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleController extends BaseController {

    @Autowired
    private ArticleService articleService;

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
    @RequestMapping("/toListArticlePage")
    public String toListArticlePage(HttpServletRequest request, String type) {
        request.setAttribute("type", type);
        return "article/articleList.ftl";
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
    @RequestMapping("/queryArticleList")
    public String queryArticleList(HttpServletRequest request, Article article, Integer pageNumber, Integer pageSize) {
    	String stype = request.getParameter("gold");
    	String pvalue = request.getParameter("pvalue");
    	if("title".equals(stype)){
    		article.setTitle(pvalue);
    	}
    	if("author".equals(stype)){
    		article.setAuthor(pvalue);
    	}
    	request.setAttribute("type", article.getType());
    	QueryResult<Article> queryResult = articleService.queryArticleForPage(article, pageNumber, pageSize);
        request.setAttribute("queryResult", queryResult);

        // 查询参数回写
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("title", null != article ? article.getTitle() : "");
        pageParam.put("type", null != article ? article.getType() : "");
        request.setAttribute("pageParam", pageParam);
        return "admin/article/articleQueryList.ftl";
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
    @RequestMapping("/addArticle")
    public String addArticle(HttpServletRequest request, HttpServletResponse response, Article article) {

    	JsonObject jsonObject = new JsonObject();
    	jsonObject.addProperty("flag", false);

    	if (null == article) {
    		jsonObject.addProperty("message", "文章数据不存在，请核实");
    	} else {
    		SessionUser currentUser = (SessionUser)request.getSession().getAttribute(Constant.Session.SESSION_NAME);
    		if(currentUser == null){
    			jsonObject.addProperty("message", "请登录后操作");
    		}else{
    			article.setAuthor(currentUser.getUserName());
    			article.setSummary("Summary");	
    			int result = articleService.addArticle(article);
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
     * @param article
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/mdfArticleSearch")
    public String mdfArticleSearch(HttpServletRequest request, HttpServletResponse response, Article article) {
    	  Integer id = article.getId();
    	  article = articleService.findArticleById(id);
    	  String content =  new String(article.getContent()==null? "".getBytes():article.getContent());
    	  request.setAttribute("articlen", article);
    	  request.setAttribute("content", content);
          return "admin/article/mdfArticle.ftl";
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
    @RequestMapping("/deleteArticle")
    public String deleteArticle(HttpServletRequest request, HttpServletResponse response, String ids) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        if (StringUtil.isEmpty(ids)) {
            jsonObject.addProperty("message", "请选择需要删除的数据");
        } else {
            String[] idArr = ids.split(",");
            try {
                for (String id : idArr) {
                    articleService.deleteArticleById(Integer.parseInt(id));
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
    @RequestMapping("/addArticleForward")
    public String addArticleForward(HttpServletRequest request, HttpServletResponse response, String type) {
    	  request.setAttribute("type", type);
          return "admin/article/insertArticle.ftl";
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
    @RequestMapping("/mdfArticle")
    public String mdfArticle(HttpServletRequest request, HttpServletResponse response, Article article) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);
        Integer id = article.getId();
        Article barticle = articleService.findArticleById(id);
        barticle.setContent(article.getContent());
        barticle.setTitle(article.getTitle());
        int i = articleService.updateArticle(barticle);
        if (i>0) {
        	jsonObject.addProperty("flag", true);
            jsonObject.addProperty("message", "修改成功");
        } else {
        	jsonObject.addProperty("flag", true);
            jsonObject.addProperty("message", "修改失败");
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
    @RequestMapping("/searchArticleForward")
    public String searchArticleForward(HttpServletRequest request, HttpServletResponse response, String type) {
  	  request.setAttribute("type", type);
      return "admin/article/articleLst.ftl";
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
    @RequestMapping("/reviewArticle")
    public String reviewArticle(HttpServletRequest request, HttpServletResponse response, Article article) {
    	  //request.setAttribute("narticle", article);
    	  String content =  new String(article.getContent()==null? "".getBytes():article.getContent());
    	 // article.setCreateTime(  new Timestamp(System.currentTimeMillis()));
    	  request.setAttribute("content", content);

    	return "admin/article/article.ftl";
    }   
}
