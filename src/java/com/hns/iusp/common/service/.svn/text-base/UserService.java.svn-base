package com.hns.iusp.common.service;

import java.io.InputStream;
import java.util.List;

import com.hns.iusp.common.bean.LoginUser;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Teacher;
import com.hns.iusp.common.bean.User;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface UserService {
    
    User findUserById(Integer id);

    User findUserByUserNameAndPwd(String userName, String password);
    
    SessionUser findSessionUserByUserName(String userName, String roleCode);
    
    List<String> analysisDataFromExcel(InputStream inputStream, String deptCode);
    
    int modifyPassword(User user);
    
    int modifyTeacherInfo(Teacher user);
    
    Teacher findTeacherByUserName(String userName);
    
    Teacher findTeacherById(Integer id);
    
    LoginUser findLoginUserByUserName(String userName);
    
    int addLoginUser(LoginUser loginUser);
    
    int findLoginUserCountByOrgCode(String dept,List<String> depts);
    
    int deleteLoginUserByUserName(String userName);
}
