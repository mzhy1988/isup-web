/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: UserDaoImpl.java
 * Author:   dong
 * Date:     Nov 4, 2014 11:37:31 PM
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
import com.hns.iusp.common.bean.LoginUser;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Teacher;
import com.hns.iusp.common.bean.User;
import com.hns.iusp.common.dao.UserDao;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class UserDaoImpl extends BaseDao implements UserDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findUserById(java.lang.Integer)
     */
    @Override
    public User findUserById(Integer id) {

        User user = new User();
        user.setId(id);
        return this.getSqlSession().selectOne("user.findUserById", user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findUserByUserNameAndPwd(java.lang.String, java.lang.String)
     */
    @Override
    public User findUserByUserNameAndPwd(String userName, String password) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);
        paramMap.put("password", password);

        return this.getSqlSession().selectOne("user.findUserByUserNameAndPwd", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findStudentInfoByUserName(java.lang.String)
     */
    @Override
    public SessionUser findStudentInfoByUserName(String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);
        return this.getSqlSession().selectOne("user.findStudentInfoByUserName", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findTeacherInfoByUserName(java.lang.String)
     */
    @Override
    public SessionUser findTeacherInfoByUserName(String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);
        return this.getSqlSession().selectOne("user.findTeacherInfoByUserName", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#addUser(com.hns.iusp.common.bean.User)
     */
    @Override
    public int addUser(User user) {
        return this.getSqlSession().insert("user.addUser", user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#addTeacherInfo(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public int addTeacherInfo(String userName, String realName, String telephone) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);
        paramMap.put("realName", realName);
        paramMap.put("telephone", telephone);

        return this.getSqlSession().insert("user.addTeacherInfo", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#modifyPassword(com.hns.iusp.common.bean.User)
     */
    @Override
    public int modifyPassword(User user) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", user.getUserName());
        paramMap.put("password", user.getPassword());

        return this.getSqlSession().update("user.changePassword", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#modifyTeacherInfo(com.hns.iusp.common.bean.User)
     */
    @Override
    public int modifyTeacherInfo(Teacher user) {
        return this.getSqlSession().update("user.modifyTeacherInfo", user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findTeacherByUserName(java.lang.String)
     */
    @Override
    public Teacher findTeacherByUserName(String userName) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);

        return this.getSqlSession().selectOne("user.findTeacherByUserName", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findTeacherById(java.lang.String)
     */
    @Override
    public Teacher findTeacherById(Integer id) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);

        return this.getSqlSession().selectOne("user.findTeacherById", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findTeachersByOrgCode(java.lang.String)
     */
    @Override
    public List<Teacher> findTeachersByOrgCode(String orgCode) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("orgCode", orgCode);

        return this.getSqlSession().selectList("user.findTeacherInfoByOrgCode", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#addLoginUser(com.hns.iusp.common.bean.LoginUser)
     */
    @Override
    public int addLoginUser(LoginUser loginUser) {
        return this.getSqlSession().insert("user.addLoginUser", loginUser);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findLoginUserByUserName(java.lang.String)
     */
    @Override
    public LoginUser findLoginUserByUserName(String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);
        return this.getSqlSession().selectOne("user.findLoginUserByUserName", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#findLoginUserCountByOrgCode(java.lang.String)
     */
    @Override
    public int findLoginUserCountByOrgCode(String dept,List<String> depts) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dept", dept);
        paramMap.put("depts", depts);
        return this.getSqlSession().selectOne("user.findLoginUserCountByOrgCode", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.dao.UserDao#deleteLoginUserByUserName(java.lang.String)
     */
    @Override
    public int deleteLoginUserByUserName(String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userName", userName);
        return this.getSqlSession().delete("user.deleteLoginUserByUserName", paramMap);
    }
}
