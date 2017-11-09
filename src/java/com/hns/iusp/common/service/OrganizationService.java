/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: OrganizationService.java
 * Author:   Tommy Xu
 * Date:     Nov 16, 2014 11:37:52 AM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.common.service;

import java.util.List;

import com.hns.iusp.common.bean.Organization;
import com.hns.iusp.common.bean.OrganizationTree;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface OrganizationService {
    
    List<String> queryPresidentOrgCodeByUserName(String userName);
    
    List<Organization> queryPresidentOrgByUserName(String userName);

    List<Organization> queryOrgListByOrgType(String orgType);
    
    List<Organization> queryOrgListByParentCode(String parentCode);
    
    List<OrganizationTree> queryOrgTreeListByParentCode(String parentCode);
}
