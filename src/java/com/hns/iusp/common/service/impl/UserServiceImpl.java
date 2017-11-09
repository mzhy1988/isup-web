/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: UserServiceImpl.java
 * Author:   dong
 * Date:     Nov 6, 2014 11:34:57 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.service.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hns.iusp.common.bean.LoginUser;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Teacher;
import com.hns.iusp.common.bean.TeacherPositionOrg;
import com.hns.iusp.common.bean.User;
import com.hns.iusp.common.constant.Constant;
import com.hns.iusp.common.dao.OrganizationDao;
import com.hns.iusp.common.dao.UserDao;
import com.hns.iusp.common.service.UserService;
import com.hns.iusp.utils.ExcelUtil;
import com.hns.iusp.utils.StringUtil;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrganizationDao organizationDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findUserById(java.lang.Integer)
     */
    @Override
    public User findUserById(Integer id) {
        return userDao.findUserById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findUserByUserNameAndPwd(java.lang.String, java.lang.String)
     */
    @Override
    public User findUserByUserNameAndPwd(String userName, String password) {
        return userDao.findUserByUserNameAndPwd(userName, password);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findSessionUserByUserName(java.lang.String)
     */
    @Override
    public SessionUser findSessionUserByUserName(String userName, String roleCode) {

        SessionUser user = null;

        if (Constant.Role.ROLECODE_STUDENT.equals(roleCode)) {
            user = userDao.findStudentInfoByUserName(userName);
        } else if (Constant.Role.ROLECODE_TEACHER.equals(roleCode)) {
            user = userDao.findTeacherInfoByUserName(userName);
        } else {
            user = new SessionUser();
            user.setUserName(userName);
            user.setRoleCode(roleCode);
        }

        return user;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#analysisDataFromExcel(java.io.InputStream, java.lang.String)
     */
    @Override
    public List<String> analysisDataFromExcel(InputStream inputStream, String deptCode) {
        List<String> errorList = new ArrayList<String>();

        try {
            if (null == inputStream || inputStream.available() == 0) {// 非空校验
                errorList.add("文件为空！请重新上传！");
            } else {

                ExcelUtil excelUtil = ExcelUtil.getExcelInstance();
                HSSFSheet sheet = excelUtil.getFirstSheet(inputStream);
                // 行对象
                HSSFRow row = null;
                // 总行数 ,行是从零开始，所以要 +1
                int totalCount = sheet.getLastRowNum() + 1;
                if (totalCount == 0) {
                    errorList.add("文件为空！请重新上传！");
                } else if (totalCount == 1) {
                    errorList.add("导入模板中没有录入用户信息，请录入后再导入，谢谢！");
                } else {
                    for (int rowIndex = 1; rowIndex < totalCount; rowIndex++) {

                        // new a user
                        User user = new User();
                        // 获取行对象
                        row = sheet.getRow(rowIndex);

                        if (null == row || row.getLastCellNum() < 5) {
                            // errorList.add(this.generateMessage(rowIndex) + "行无数据或列数错误!");
                        } else {

                            String realName = excelUtil.getStringCellValue(row.getCell(1));
                            String userName = excelUtil.getStringCellValue(row.getCell(2));
                            String orgCode = excelUtil.getStringCellValue(row.getCell(3));
                            String positionName = excelUtil.getStringCellValue(row.getCell(4));
                            String positionCode = "00" + excelUtil.getStringCellValue(row.getCell(5));
                            String isManager = excelUtil.getStringCellValue(row.getCell(6));
                            String telephone = excelUtil.getStringCellValue(row.getCell(7));

                            user.setUserName(userName);
                            user.setPassword(StringUtil.md5(userName));
                            user.setRoleCode(Constant.Role.ROLECODE_TEACHER);
                            userDao.addUser(user);

                            userDao.addTeacherInfo(userName, realName, telephone);

                            TeacherPositionOrg positionOrg = new TeacherPositionOrg();
                            positionOrg.setIsManager(StringUtil.isEmpty(isManager) ? "N" : isManager);
                            positionOrg.setOrgCode(orgCode);
                            positionOrg.setPositionCode(positionCode);
                            positionOrg.setPositionName(positionName);
                            positionOrg.setStaffNo(userName);

                            organizationDao.addTeacherPositionOrg(positionOrg);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#modifyPassword(com.hns.iusp.common.bean.User)
     */
    @Override
    public int modifyPassword(User user) {
        return userDao.modifyPassword(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#modifyTeacherInfo(com.hns.iusp.common.bean.User)
     */
    @Override
    public int modifyTeacherInfo(Teacher user) {
        return userDao.modifyTeacherInfo(user);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findTeacherByUserName(java.lang.String)
     */
    @Override
    public Teacher findTeacherByUserName(String userName) {
        return userDao.findTeacherByUserName(userName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findTeacherById(java.lang.Integer)
     */
    @Override
    public Teacher findTeacherById(Integer id) {
        return userDao.findTeacherById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#addLoginUser(com.hns.iusp.common.bean.LoginUser)
     */
    @Override
    public int addLoginUser(LoginUser loginUser) {

        LoginUser tmpUser = userDao.findLoginUserByUserName(loginUser.getUserName());

        int result = 0;
        if (tmpUser == null) {
            result = userDao.addLoginUser(loginUser);
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findLoginUserByUserName(java.lang.String)
     */
    @Override
    public LoginUser findLoginUserByUserName(String userName) {
        return userDao.findLoginUserByUserName(userName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#findLoginUserCountByOrgCode(java.lang.String)
     */
    @Override
    public int findLoginUserCountByOrgCode(String dept, List<String> depts) {
        return userDao.findLoginUserCountByOrgCode(dept, depts);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.common.service.UserService#deleteLoginUserByUserName(java.lang.String)
     */
    @Override
    public int deleteLoginUserByUserName(String userName) {
        return userDao.deleteLoginUserByUserName(userName);
    }

}
