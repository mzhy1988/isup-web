/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: SessionUser.java
 * Author:   dong
 * Date:     Nov 4, 2014 7:13:09 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.bean;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SessionUser {

    private String userName;
    private String realName;
    private String roleCode;
    private String orgCode;
    private String orgName;
    private String orgType;
    private String orgTypeName;
    private String positionCode;
    private String positionName;
    private String isManager;

    /**
     * @return the roleCode
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * @param roleCode the roleCode to set
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    /**
     * @return the orgCode
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * @param orgCode the orgCode to set
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * @return the orgName
     */
    public String getOrgName() {
        return orgName;
    }

    /**
     * @param orgName the orgName to set
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    /**
     * @return the positionCode
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * @param positionCode the positionCode to set
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    /**
     * @return the positionName   
     */
    public String getPositionName() {
        return positionName;
    }

    /**
     * @param positionName the positionName to set
     */
    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the realName
     */
    public String getRealName() {
        return realName;
    }

    /**
     * @param realName the realName to set
     */
    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return the orgType
     */
    public String getOrgType() {
        return orgType;
    }

    /**
     * @param orgType the orgType to set
     */
    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    /**
     * @return the orgTypeName
     */
    public String getOrgTypeName() {
        if ("01".equals(orgType)) {
            orgTypeName = "领导层";
        } else if ("02".equals(orgType)) {
            orgTypeName = "行政部门";
        } else if ("03".equals(orgType)) {
            orgTypeName = "直属部门";
        } else if ("04".equals(orgType)) {
            orgTypeName = "教学部门";
        }

        return orgTypeName;
    }

    /**
     * @param orgTypeName the orgTypeName to set
     */
    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    /**
     * @return the isManager
     */
    public String getIsManager() {
        return isManager;
    }

    /**
     * @param isManager the isManager to set
     */
    public void setIsManager(String isManager) {
        this.isManager = isManager;
    }

}
