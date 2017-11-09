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
import com.hns.iusp.common.bean.Software;
import com.hns.iusp.common.dao.SoftwareDao;
import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author mazy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class SoftwareDaoImpl extends BaseDao implements SoftwareDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#addArticle(com.hns.iusp.common.bean.Article)
     */
    @Override
    public int addSoftware(Software software) {
        return this.getSqlSession().insert("software.addSoftware", software);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#deleteArticleById(java.lang.Integer)
     */
    @Override
    public int deleteSoftwareById(Integer id) {
        return this.getSqlSession().delete("software.deleteSoftwareById", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#findArticleById(java.lang.Integer)
     */
    @Override
    public Software findSoftwareById(Integer id) {
        return this.getSqlSession().selectOne("software.findSoftwareById", id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#queryArticleForPage(com.hns.iusp.utils.QueryParam)
     */
    @Override
    public QueryResult<Software> querySoftwareForPage(QueryParam<Software> queryParam) {

    	Software queryParamObj = queryParam.getQueryParam();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("downName", StringUtil.trim(queryParamObj.getDownName()));
        paramMap.put("uploader", queryParamObj.getUploader());

        Integer count = this.getSqlSession().selectOne("software.findSoftwareCount", paramMap);

        QueryResult<Software> queryResult = new QueryResult<Software>(count, queryParam.getPageSize(), queryParam
                .getPageNumber());

        if (count == 0) {
            queryResult.setPageCount(0);
        }

        List<Software> list = null;

        // 查询外部系统信息
        if (count > 0) {
            paramMap.put("startIndex", queryResult.getIndexNumber());
            paramMap.put("endIndex", queryResult.getPageNumber() * queryResult.getPageSize());
            list = this.getSqlSession().selectList("software.querySoftwareForPage", paramMap);
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
    public int updateSoftware(Software article) {
        return this.getSqlSession().update("software.updateSoftware", article);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.ArticleDao#queryArticleByLimit(int)
     */
    @Override
    public List<Software> querySoftwareByLimit(String type,int size) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("endIndex", size);
        return this.getSqlSession().selectList("software.querySoftwareByLimit", paramMap);
    }

}
