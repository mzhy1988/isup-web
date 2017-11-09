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

import com.hns.iusp.common.bean.Software;
import com.hns.iusp.common.dao.SoftwareDao;
import com.hns.iusp.common.service.SoftwareService;
import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author mazy
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class SoftwareServiceImpl implements SoftwareService {

    @Autowired
    private SoftwareDao softwareDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.fileService#addfile(com.hns.iusp.common.bean.file)
     */
    @Override
    public int addSoftware(Software software) {
        return softwareDao.addSoftware(software);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.fileService#deletefileById(java.lang.Integer)
     */
    @Override
    public int deleteSoftwareById(Integer id) {
        return softwareDao.deleteSoftwareById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.fileService#findfileById(java.lang.Integer)
     */
    @Override
    public Software findSoftwareById(Integer id) {
        return softwareDao.findSoftwareById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.fileService#queryfileForPage(com.hns.iusp.common.bean.file,
     *      java.lang.Integer, java.lang.Integer)
     */
    @Override
    public QueryResult<Software> querySoftwareForPage(Software file, Integer pageNumber, Integer pageSize) {

        QueryParam<Software> param = new QueryParam<Software>();
        param.setQueryParam(file);
        param.setPageNumber(pageNumber);
        param.setPageSize((null == pageNumber || pageNumber == 0) ? 10 : pageSize);

        return softwareDao.querySoftwareForPage(param);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.fileService#updatefile(com.hns.iusp.common.bean.file)
     */
    @Override
    public int updateSoftware(Software file) {
        return softwareDao.updateSoftware(file);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.fileService#queryfileByLimit(int)
     */
    @Override
    public List<Software> querySoftwareByLimit(String type,int size) {
        return softwareDao.querySoftwareByLimit(type, size);
    }
}