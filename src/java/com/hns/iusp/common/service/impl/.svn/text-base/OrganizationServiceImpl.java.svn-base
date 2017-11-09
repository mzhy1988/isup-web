/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: OrganizationServiceImpl.java
 * Author:   Tommy Xu
 * Date:     Nov 16, 2014 11:38:13 AM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hns.iusp.common.bean.LoginUser;
import com.hns.iusp.common.bean.Organization;
import com.hns.iusp.common.bean.OrganizationTree;
import com.hns.iusp.common.bean.Teacher;
import com.hns.iusp.common.bean.TreeFont;
import com.hns.iusp.common.dao.OrganizationDao;
import com.hns.iusp.common.dao.UserDao;
import com.hns.iusp.common.service.OrganizationService;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private UserDao userDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.OrganizationService#queryOrgListByOrgType(java.lang.String)
     */
    @Override
    public List<Organization> queryOrgListByOrgType(String orgType) {
        return organizationDao.queryOrgListByOrgType(orgType);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.OrganizationService#queryPresidentOrgByUserName(java.lang.String)
     */
    @Override
    public List<Organization> queryPresidentOrgByUserName(String userName) {
        return organizationDao.queryPresidentOrgByUserName(userName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.OrganizationService#queryPresidentOrgCodeByUserName(java.lang.String)
     */
    @Override
    public List<String> queryPresidentOrgCodeByUserName(String userName) {
        return organizationDao.queryPresidentOrgCodeByUserName(userName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.OrganizationService#queryOrgListByParentCode(java.lang.String)
     */
    @Override
    public List<Organization> queryOrgListByParentCode(String parentCode) {
        return organizationDao.queryOrgListByParentCode(parentCode);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.OrganizationService#queryOrgTreeListByParentCode(java.lang.String)
     */
    @Override
    public List<OrganizationTree> queryOrgTreeListByParentCode(String parentCode) {

        List<OrganizationTree> treeList = new ArrayList<OrganizationTree>();

        if (!"00".equals(parentCode) && !"9000".equals(parentCode) && !"9001".equals(parentCode)
                && !"9002".equals(parentCode) && !"9003".equals(parentCode)) {
            List<Teacher> teacherList = userDao.findTeachersByOrgCode(parentCode);
            if (null != teacherList && !teacherList.isEmpty()) {
                for (Teacher teacher : teacherList) {
                    OrganizationTree tree = new OrganizationTree();
                    tree.setId(teacher.getId() + "");
                    tree.setPid("");
                    LoginUser user = userDao.findLoginUserByUserName(teacher.getUserName());
                    if (null != user) {
                        TreeFont font = new TreeFont();
                        font.setColor("blue");
                        // tree.setName("<span style='color:blue;font-weight:bold'>" + teacher.getRealName() +
                        // "</span>");
                        tree.setFont(font);
                    }
                    tree.setName(teacher.getRealName());
                    tree.setParent(false);
                    treeList.add(tree);
                }
            }
        } else {
            List<Organization> orgList = organizationDao.queryOrgListByParentCode(parentCode);
            if (null != orgList && !orgList.isEmpty()) {
                for (Organization organization : orgList) {
                    OrganizationTree tree = new OrganizationTree();
                    tree.setId(organization.getOrgCode());
                    tree.setPid(organization.getParentCode());
                    List<String> depts = new ArrayList<String>();
                    if("00".equals(parentCode)){
                        List<Organization> orgs = organizationDao.queryOrgListByParentCode(organization.getOrgCode());
                        for (Organization org : orgs) {
                            depts.add(org.getOrgCode());
                        }
                    } else {
                        depts.add(organization.getOrgCode());
                    }
                    int loginCount = userDao.findLoginUserCountByOrgCode("Y", depts);
                    tree.setName(organization.getOrgName() + "(" + loginCount + ")");
                    tree.setParent(true);
                    treeList.add(tree);
                }
            }
        }

        return treeList;
    }

}
