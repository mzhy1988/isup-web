package com.hns.iusp.common.dao;

import java.util.List;

import com.hns.iusp.common.bean.LoginUser;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Teacher;
import com.hns.iusp.common.bean.User;

public interface UserDao {

    User findUserById(Integer id);

    User findUserByUserNameAndPwd(String userName, String password);

    SessionUser findTeacherInfoByUserName(String userName);

    SessionUser findStudentInfoByUserName(String userName);

    int addUser(User user);

    int addTeacherInfo(String userName, String realName, String telephone);

    int modifyPassword(User user);

    int modifyTeacherInfo(Teacher user);

    Teacher findTeacherByUserName(String userName);
    
    Teacher findTeacherById(Integer id);

    List<Teacher> findTeachersByOrgCode(String userName);

    LoginUser findLoginUserByUserName(String userName);

    int addLoginUser(LoginUser loginUser);

    int findLoginUserCountByOrgCode(String dept,List<String> depts);
    
    int deleteLoginUserByUserName(String userName);
}
