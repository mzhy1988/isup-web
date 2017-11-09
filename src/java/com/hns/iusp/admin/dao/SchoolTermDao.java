/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: ArticleDao.java
 * Author:   Tommy Xu
 * Date:     Nov 10, 2014 11:16:11 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.admin.dao;

import java.util.List;

import com.hns.iusp.admin.bean.SchoolTermBean;
import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * @author mazy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface SchoolTermDao {

    QueryResult<SchoolTermBean> querySchoolTermForPage(QueryParam<SchoolTermBean> param);

    int addSchoolTerm(SchoolTermBean bean);

    int updateSchoolTerm(SchoolTermBean bean);

    int deleteSchoolTermById(Integer id);

    SchoolTermBean findSchoolTermById(Integer id);
    
    List<SchoolTermBean> querySchoolTermByLimit(String type,int size);
}