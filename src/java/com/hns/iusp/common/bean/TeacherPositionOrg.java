/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: TeacherPositionOrg.java
 * Author:   Tommy Xu
 * Date:     Nov 17, 2014 8:25:06 PM
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
public class TeacherPositionOrg {

    private Integer id;

    private String orgCode;

    private String staffNo;

    private String positionCode;

    private String positionName;

    private String isManager;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return the staffNo
     */
    public String getStaffNo() {
        return staffNo;
    }

    /**
     * @param staffNo the staffNo to set
     */
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
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
