/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: WorkingLogDaoImpl.java
 * Author:   Tommy Xu
 * Date:     Nov 12, 2014 10:03:11 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.workinglog.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.hns.iusp.base.BaseDao;
import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;
import com.hns.iusp.workinglog.bean.WorkingLog;
import com.hns.iusp.workinglog.dao.WorkingLogDao;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
public class WorkingLogDaoImpl extends BaseDao implements WorkingLogDao {

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#addWorkingLog(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int addWorkingLog(WorkingLog log) {
        return this.getSqlSession().insert("workingLog.addWorkingLog", log);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#findWorkingLogByLogTime(java.lang.String)
     */
    @Override
    public WorkingLog findWorkingLogByLogTime(String logTime, String userName) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("logTime", logTime);
        paramMap.put("userName", userName);

        WorkingLog log = null;
        List<WorkingLog> list = this.getSqlSession().selectList("workingLog.findWorkingLogByLogTime", paramMap);
        if (null != list && !list.isEmpty()) {
            log = list.get(0);
        }

        return log;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#findWorkingLogByLogWeekTime(java.lang.String, java.lang.String,
     *      java.lang.String)
     */
    @Override
    public WorkingLog findWorkingLogByLogWeekTime(String year, String term, String week, String userName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("year", year);
        paramMap.put("term", term);
        paramMap.put("week", week);
        paramMap.put("userName", userName);

        WorkingLog log = null;
        List<WorkingLog> list = this.getSqlSession().selectList("workingLog.findWorkingLogByLogWeekTime", paramMap);
        if (null != list && !list.isEmpty()) {
            log = list.get(0);
        }

        return log;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#findWorkingLogById(java.lang.Integer)
     */
    @Override
    public WorkingLog findWorkingLogById(Integer id) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", id);

        return this.getSqlSession().selectOne("workingLog.findWorkingLogById", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#updateWorkingLog(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int updateWorkingLog(WorkingLog log) {
        return this.getSqlSession().update("workingLog.updateWorkingLog", log);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#queryMyWorkingLogForPage(com.hns.iusp.utils.QueryParam)
     */
    @Override
    public QueryResult<WorkingLog> queryMyWorkingLogForPage(QueryParam<WorkingLog> param) {

        WorkingLog queryParamObj = param.getQueryParam();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", StringUtil.trim(queryParamObj.getStaffNo()));
        paramMap.put("logType", StringUtil.trim(queryParamObj.getLogType()));
        paramMap.put("logStatus", StringUtil.trim(queryParamObj.getLogStatus()));

        Integer count = this.getSqlSession().selectOne("workingLog.findMyWorkingLogCount", paramMap);

        QueryResult<WorkingLog> queryResult = new QueryResult<WorkingLog>(count, param.getPageSize(), param
                .getPageNumber());

        if (count == 0) {
            queryResult.setPageCount(0);
        }

        List<WorkingLog> list = null;

        // 查询外部系统信息
        if (count > 0) {
            paramMap.put("startIndex", queryResult.getIndexNumber());
            paramMap.put("endIndex", queryResult.getPageNumber() * queryResult.getPageSize());
            list = this.getSqlSession().selectList("workingLog.queryMyWorkingLogForPage", paramMap);
            queryResult.setDatas(list);
        }

        return queryResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#findMyWorkingLogCount(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int findMyWorkingLogCount(WorkingLog log) {

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", StringUtil.trim(log.getStaffNo()));
        paramMap.put("logType", StringUtil.trim(log.getLogType()));
        paramMap.put("logStatus", StringUtil.trim(log.getLogStatus()));

        return this.getSqlSession().selectOne("workingLog.findMyWorkingLogCount", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#findWorkingLogCount(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int findWorkingLogCount(WorkingLog log, List<String> depts, String currDept) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", StringUtil.trim(log.getApprover()));
        paramMap.put("logType", StringUtil.trim(log.getLogType()));
        paramMap.put("logStatus", StringUtil.trim(log.getLogStatus()));
        paramMap.put("staffNo", StringUtil.trim(log.getStaffNo()));
        paramMap.put("staffName", StringUtil.trim(log.getStaffName()));
        paramMap.put("depts", depts);
        paramMap.put("currDept", currDept);

        return this.getSqlSession().selectOne("workingLog.findWorkingLogCount", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#findWorkingLogCount(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int findWorkingLogCount(WorkingLog queryParamObj) {
        
        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", StringUtil.trim(queryParamObj.getApprover()));
        paramMap.put("logType", StringUtil.trim(queryParamObj.getLogType()));
        paramMap.put("logStatus", StringUtil.trim(queryParamObj.getLogStatus()));
        paramMap.put("staffNo", StringUtil.trim(queryParamObj.getStaffNo()));
        paramMap.put("staffName", StringUtil.trim(queryParamObj.getStaffName()));

        return this.getSqlSession().selectOne("workingLog.findWorkingLog1Count", paramMap);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#queryWorkingLogForPage(com.hns.iusp.utils.QueryParam)
     */
    @Override
    public QueryResult<WorkingLog> queryWorkingLogForPage(QueryParam<WorkingLog> param, List<String> depts,
            String currDept) {

        WorkingLog queryParamObj = param.getQueryParam();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", StringUtil.trim(queryParamObj.getApprover()));
        paramMap.put("logType", StringUtil.trim(queryParamObj.getLogType()));
        paramMap.put("logStatus", StringUtil.trim(queryParamObj.getLogStatus()));
        paramMap.put("staffNo", StringUtil.trim(queryParamObj.getStaffNo()));
        paramMap.put("staffName", StringUtil.trim(queryParamObj.getStaffName()));
        paramMap.put("depts", depts);
        paramMap.put("currDept", currDept);

        Integer count = this.getSqlSession().selectOne("workingLog.findWorkingLogCount", paramMap);

        QueryResult<WorkingLog> queryResult = new QueryResult<WorkingLog>(count, param.getPageSize(), param
                .getPageNumber());

        if (count == 0) {
            queryResult.setPageCount(0);
        }

        List<WorkingLog> list = null;

        // 查询外部系统信息
        if (count > 0) {
            paramMap.put("startIndex", queryResult.getIndexNumber());
            paramMap.put("endIndex", queryResult.getPageNumber() * queryResult.getPageSize());
            list = this.getSqlSession().selectList("workingLog.queryWorkingLogForPage", paramMap);
            queryResult.setDatas(list);
        }

        return queryResult;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.dao.WorkingLogDao#queryWorkingLogForPage(com.hns.iusp.utils.QueryParam)
     */
    @Override
    public QueryResult<WorkingLog> queryWorkingLogForPage(QueryParam<WorkingLog> param) {

        WorkingLog queryParamObj = param.getQueryParam();

        Map<String, Object> paramMap = new HashMap<String, Object>();

        paramMap.put("userName", StringUtil.trim(queryParamObj.getApprover()));
        paramMap.put("logType", StringUtil.trim(queryParamObj.getLogType()));
        paramMap.put("logStatus", StringUtil.trim(queryParamObj.getLogStatus()));
        paramMap.put("staffNo", StringUtil.trim(queryParamObj.getStaffNo()));
        paramMap.put("staffName", StringUtil.trim(queryParamObj.getStaffName()));

        Integer count = this.getSqlSession().selectOne("workingLog.findWorkingLog1Count", paramMap);

        QueryResult<WorkingLog> queryResult = new QueryResult<WorkingLog>(count, param.getPageSize(), param
                .getPageNumber());

        if (count == 0) {
            queryResult.setPageCount(0);
        }

        List<WorkingLog> list = null;

        // 查询外部系统信息
        if (count > 0) {
            paramMap.put("startIndex", queryResult.getIndexNumber());
            paramMap.put("endIndex", queryResult.getPageNumber() * queryResult.getPageSize());
            list = this.getSqlSession().selectList("workingLog.queryWorkingLog1ForPage", paramMap);
            queryResult.setDatas(list);
        }

        return queryResult;
    }

    //add by wangxuan 2014-11-23 删除草稿
	@Override
	public int deleteWorkingLogById(Integer id) {
		// TODO Auto-generated method stub
		
		return this.getSqlSession().delete("workingLog.deleteWorkingLogById", id);
	}
	
    @Override
    public int findMyWorkingLogCountBySpr(WorkingLog log) {
      // TODO Auto-generated method stub

          Map<String, Object> paramMap = new HashMap<String, Object>();

          paramMap.put("userName", StringUtil.trim(log.getStaffNo()));
          paramMap.put("logType", StringUtil.trim(log.getLogType()));
          paramMap.put("logStatus", StringUtil.trim(log.getLogStatus()));

          return this.getSqlSession().selectOne("workingLog.findMyWorkingLogCountBySpr", paramMap);
    }
}
