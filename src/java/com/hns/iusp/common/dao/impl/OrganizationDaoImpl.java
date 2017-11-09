/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: OrganizationDaoImpl.java
 * Author:   Tommy Xu
 * Date:     Nov 16, 2014 11:19:46 AM
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
import com.hns.iusp.common.bean.Organization;
import com.hns.iusp.common.bean.TeacherPositionOrg;
import com.hns.iusp.common.dao.OrganizationDao;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class OrganizationDaoImpl extends BaseDao implements OrganizationDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.OrganizationDao#queryPresidentOrgCodeByUserName(java.lang.String)
     */
    @Override
    public List<String> queryPresidentOrgCodeByUserName(String userName) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);

        return this.getSqlSession().selectList("org.queryPresidentOrgCodeByUserName", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.OrganizationDao#queryPresidentOrgByUserName(java.lang.String)
     */
    @Override
    public List<Organization> queryPresidentOrgByUserName(String userName) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);

        return this.getSqlSession().selectList("org.queryPresidentOrgByUserName", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.OrganizationDao#queryOrgListByOrgType(java.lang.String)
     */
    @Override
    public List<Organization> queryOrgListByOrgType(String orgType) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgType", orgType);

        return this.getSqlSession().selectList("org.queryOrgListByOrgType", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.OrganizationDao#queryOrgListByParentCode(java.lang.String)
     */
    @Override
    public List<Organization> queryOrgListByParentCode(String parentCode) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("parentCode", parentCode);
        return this.getSqlSession().selectList("org.queryOrgListByParentCode", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.OrganizationDao#findOrgInfoByStaffNo(java.lang.String)
     */
    @Override
    public Organization findOrgInfoByStaffNo(String staffNo) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("staffNo", staffNo);

        return this.getSqlSession().selectOne("org.queryOrgInfoByStaffNo", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.OrganizationDao#addTeacherPositionOrg(com.hns.iusp.common.bean.TeacherPositionOrg)
     */
    @Override
    public int addTeacherPositionOrg(TeacherPositionOrg positionOrg) {
        return this.getSqlSession().insert("org.addTeacherPositionOrg", positionOrg);
    }

}