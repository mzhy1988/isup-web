/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: ArticleDaoImpl.java
 * Author:   Tommy Xu
 * Date:     Nov 10, 2014 11:18:40 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hns.iusp.base.BaseDao;
import com.hns.iusp.common.bean.Article;
import com.hns.iusp.common.dao.ArticleDao;
import com.hns.iusp.utils.QueryParam;
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
@Component
public class ArticleDaoImpl extends BaseDao implements ArticleDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#addArticle(com.hns.iusp.common.bean.Article)
     */
    @Override
    public int addArticle(Article article) {
        return this.getSqlSession().insert("article.addArticle", article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#deleteArticleById(java.lang.Integer)
     */
    @Override
    public int deleteArticleById(Integer id) {
        return this.getSqlSession().delete("article.deleteArticleById", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#findArticleById(java.lang.Integer)
     */
    @Override
    public Article findArticleById(Integer id) {
        return this.getSqlSession().selectOne("article.findArticleById", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#queryArticleForPage(com.hns.iusp.utils.QueryParam)
     */
    @Override
    public QueryResult<Article> queryArticleForPage(QueryParam<Article> queryParam) {

        Article queryParamObj = queryParam.getQueryParam();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("title", StringUtil.trim(queryParamObj.getTitle()));
        paramMap.put("type", queryParamObj.getType());
        paramMap.put("author", StringUtil.trim(queryParamObj.getAuthor()));

        Integer count = this.getSqlSession().selectOne("article.findArticleCount", paramMap);

        QueryResult<Article> queryResult = new QueryResult<Article>(count, queryParam.getPageSize(), queryParam
                .getPageNumber());

        if (count == 0) {
            queryResult.setPageCount(0);
        }

        List<Article> list = null;

        // 查询外部系统信息
        if (count > 0) {
            paramMap.put("startIndex", queryResult.getIndexNumber());
            paramMap.put("endIndex", queryResult.getPageNumber() * queryResult.getPageSize());
            list = this.getSqlSession().selectList("article.queryArticleForPage", paramMap);
            queryResult.setDatas(list);
        }

        return queryResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#updateArticle(com.hns.iusp.common.bean.Article)
     */
    @Override
    public int updateArticle(Article article) {
        return this.getSqlSession().update("article.updateArticle", article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#queryArticleByLimit(int)
     */
    @Override
    public List<Article> queryArticleByLimit(String type,int size) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("type", type);
        paramMap.put("endIndex", size);
        return this.getSqlSession().selectList("article.queryArticleByLimit", paramMap);
    }

}
