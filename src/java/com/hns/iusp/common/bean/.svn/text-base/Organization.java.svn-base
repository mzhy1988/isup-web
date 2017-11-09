/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: Organization.java
 * Author:   dong
 * Date:     Nov 7, 2014 12:44:59 AM
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
public class Organization {

    private Integer id;
    private String orgCode;
    private String orgName;
    private String orgDesc;
    private String parentCode;
    private String orgType;
    private String orgTypeName;

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
     * @return the orgDesc
     */
    public String getOrgDesc() {
        return orgDesc;
    }

    /**
     * @param orgDesc the orgDesc to set
     */
    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    /**
     * @return the parentCode
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * @param parentCode the parentCode to set
     */
    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
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
}