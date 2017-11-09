/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: TeacherOrg.java
 * Author:   dong
 * Date:     Nov 7, 2014 12:46:37 AM
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
 * @author dong
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class TeacherOrg {

    private Integer id;

    private String orgCode;

    private String staffCode;

    private String positionCode;

    private String pisitionName;

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
     * @return the staffCode
     */
    public String getStaffCode() {
        return staffCode;
    }

    /**
     * @param staffCode the staffCode to set
     */
    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
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
     * @return the pisitionName
     */
    public String getPisitionName() {
        return pisitionName;
    }

    /**
     * @param pisitionName the pisitionName to set
     */
    public void setPisitionName(String pisitionName) {
        this.pisitionName = pisitionName;
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
