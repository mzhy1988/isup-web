/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: ArticleServiceImpl.java
 * Author:   Tommy Xu
 * Date:     Nov 10, 2014 11:35:52 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hns.iusp.common.bean.Article;
import com.hns.iusp.common.dao.ArticleDao;
import com.hns.iusp.common.service.ArticleService;
import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.ArticleService#addArticle(com.hns.iusp.common.bean.Article)
     */
    @Override
    public int addArticle(Article article) {
        return articleDao.addArticle(article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.ArticleService#deleteArticleById(java.lang.Integer)
     */
    @Override
    public int deleteArticleById(Integer id) {
        return articleDao.deleteArticleById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.ArticleService#findArticleById(java.lang.Integer)
     */
    @Override
    public Article findArticleById(Integer id) {
        return articleDao.findArticleById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.ArticleService#queryArticleForPage(com.hns.iusp.common.bean.Article,
     *      java.lang.Integer, java.lang.Integer)
     */
    @Override
    public QueryResult<Article> queryArticleForPage(Article article, Integer pageNumber, Integer pageSize) {

        QueryParam<Article> param = new QueryParam<Article>();
        param.setQueryParam(article);
        param.setPageNumber(pageNumber);
        param.setPageSize((null == pageNumber || pageNumber == 0) ? 10 : pageSize);

        return articleDao.queryArticleForPage(param);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.ArticleService#updateArticle(com.hns.iusp.common.bean.Article)
     */
    @Override
    public int updateArticle(Article article) {
        return articleDao.updateArticle(article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.ArticleService#queryArticleByLimit(int)
     */
    @Override
    public List<Article> queryArticleByLimit(String type,int size) {
        return articleDao.queryArticleByLimit(type,size);
    }
}