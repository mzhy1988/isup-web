/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: WorkingLogDao.java
 * Author:   Tommy Xu
 * Date:     Nov 12, 2014 9:48:39 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.workinglog.dao;

import java.util.List;

import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.workinglog.bean.WorkingLog;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface WorkingLogDao {

    int addWorkingLog(WorkingLog log);

    WorkingLog findWorkingLogByLogTime(String logTime, String userName);

    WorkingLog findWorkingLogByLogWeekTime(String year, String term, String week, String userName);

    WorkingLog findWorkingLogById(Integer id);

    int updateWorkingLog(WorkingLog log);

    int findMyWorkingLogCount(WorkingLog log);
    
    int deleteWorkingLogById(Integer id); //add by wangxuan 2014-11-23 删除草稿

    QueryResult<WorkingLog> queryMyWorkingLogForPage(QueryParam<WorkingLog> param);

    int findWorkingLogCount(WorkingLog log, List<String> depts,String currDept);
    
    int findWorkingLogCount(WorkingLog log);

    QueryResult<WorkingLog> queryWorkingLogForPage(QueryParam<WorkingLog> param, List<String> depts,String currDept);
    
    QueryResult<WorkingLog> queryWorkingLogForPage(QueryParam<WorkingLog> param);
   
    int findMyWorkingLogCountBySpr(WorkingLog log);
}
