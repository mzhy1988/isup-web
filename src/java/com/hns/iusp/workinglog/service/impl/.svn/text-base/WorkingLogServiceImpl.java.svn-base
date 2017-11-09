/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: WorkingLogServiceImpl.java
 * Author:   Tommy Xu
 * Date:     Nov 12, 2014 10:39:16 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.workinglog.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hns.iusp.common.bean.Organization;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.constant.Constant;
import com.hns.iusp.common.dao.OrganizationDao;
import com.hns.iusp.utils.QueryParam;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;
import com.hns.iusp.workinglog.bean.WorkingLog;
import com.hns.iusp.workinglog.dao.WorkingLogDao;
import com.hns.iusp.workinglog.service.WorkingLogService;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class WorkingLogServiceImpl implements WorkingLogService {

    @Autowired
    private WorkingLogDao workingLogDao;

    @Autowired
    private OrganizationDao organizationDao;

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#addWorkingLog(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int addWorkingLog(WorkingLog log) {

        WorkingLog tempLog1 = null;
        if (Constant.WorkingLog.LOG_TYPE_WEEK.equals(log.getLogType())) {
            tempLog1 = workingLogDao.findWorkingLogByLogWeekTime(log.getYear(), log.getTerm(), log.getWeek(),log.getStaffNo());
        } else {
            tempLog1 = workingLogDao.findWorkingLogByLogTime(log.getLogTime(),log.getStaffNo());
        }
        if (null != tempLog1
                && (Constant.WorkingLog.LOG_STATUS_SUBMITED.equals(tempLog1.getLogStatus()) || Constant.WorkingLog.LOG_STATUS_SUCCESS
                        .equals(tempLog1.getLogStatus()))) {
            throw new RuntimeException("该日志已提交");
        }

        int result = 0;
        if (null == log.getId() || log.getId() <= 0) {
            result = workingLogDao.addWorkingLog(log);
        } else {
            WorkingLog tempLog = workingLogDao.findWorkingLogById(log.getId());
            if (null == tempLog) {
                result = workingLogDao.addWorkingLog(log);
            } else {
                if (Constant.WorkingLog.LOG_STATUS_DRAFT.equals(tempLog.getLogStatus())
                        || Constant.WorkingLog.LOG_STATUS_FAIL.equals(tempLog.getLogStatus())) {
                    log.setId(tempLog.getId());
                    result = workingLogDao.updateWorkingLog(log);
                } else {
                    throw new RuntimeException("该日志已提交");
                }
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#saveDraft(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int saveDraft(WorkingLog log) {

        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_DRAFT);

        int result = 0;
        if (null == log.getId() || log.getId() <= 0) {
            result = workingLogDao.addWorkingLog(log);
        } else {
            WorkingLog tempLog = workingLogDao.findWorkingLogById(log.getId());
            if (null == tempLog) {
                result = workingLogDao.addWorkingLog(log);
            } else {
                log.setId(tempLog.getId());
                result = workingLogDao.updateWorkingLog(log);
            }
        }

        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#updateWorkingLog(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int updateWorkingLog(WorkingLog log) {
        return workingLogDao.updateWorkingLog(log);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#findWorkingLogById(java.lang.Integer)
     */
    @Override
    public WorkingLog findWorkingLogById(Integer id) {
        return workingLogDao.findWorkingLogById(id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#findMyWorkingLogCount(com.hns.iusp.workinglog.bean.WorkingLog)
     */
    @Override
    public int findMyWorkingLogCount(WorkingLog log) {
        return workingLogDao.findMyWorkingLogCount(log);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#queryMyWorkingLogForPage(com.hns.iusp.workinglog.bean.WorkingLog,
     *      java.lang.Integer, java.lang.Integer)
     */
    @Override
    public QueryResult<WorkingLog> queryMyWorkingLogForPage(WorkingLog log, Integer pageNumber, Integer pageSize) {
        QueryParam<WorkingLog> param = new QueryParam<WorkingLog>();
        param.setQueryParam(log);
        param.setPageNumber(pageNumber);
        param.setPageSize((null == pageNumber || pageNumber == 0) ? 10 : pageSize);
        return workingLogDao.queryMyWorkingLogForPage(param);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#findWorkingLogCount(com.hns.iusp.workinglog.bean.WorkingLog,
     *      com.hns.iusp.common.bean.SessionUser)
     */
    @Override
    public int findWorkingLogCount(WorkingLog log, SessionUser user) {

        List<String> depts = new ArrayList<String>();

        // 部门负责人和副职可以看到本部门人员工作日志
        if (Constant.Teacher.POSITION_BMFZR.equals(user.getPositionCode())
                || Constant.Teacher.POSITION_BMFZ.equals(user.getPositionCode())) {
            depts.add(user.getOrgCode());
        }
        // 副院长 可以看到他分管部门的人员工作汇报
        else if (Constant.Teacher.POSITION_FYZ.equals(user.getPositionCode())) {
            if (StringUtil.isEmpty(log.getDeptCode())) {
                // 查询分管部门编码
                depts = organizationDao.queryPresidentOrgCodeByUserName(user.getUserName());
            } else {
                depts.add(log.getDeptCode());
            }
        }

        int logCount = 0;
        // 院长和书记可以看到所有人的工作汇报
        if (Constant.Teacher.POSITION_YZ.equals(user.getPositionCode())
                || Constant.Teacher.POSITION_SJ.equals(user.getPositionCode())) {
            if (!StringUtil.isEmpty(log.getDeptCode())) {
                depts.add(log.getDeptCode());
                logCount = workingLogDao.findWorkingLogCount(log, depts, user.getOrgCode());
            } else {
                logCount = workingLogDao.findWorkingLogCount(log);
            }
        } else {
            logCount = workingLogDao.findWorkingLogCount(log, depts, user.getOrgCode());
        }

        return logCount;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hns.iusp.workinglog.service.WorkingLogService#queryWorkingLogForPage(com.hns.iusp.utils.QueryParam,
     *      com.hns.iusp.common.bean.SessionUser)
     */
    @Override
    public QueryResult<WorkingLog> queryWorkingLogForPage(WorkingLog log, Integer pageNumber, Integer pageSize,
            SessionUser user) {

        List<String> depts = new ArrayList<String>();

        // 部门负责人和副职可以看到本部门人员工作日志
        if (Constant.Teacher.POSITION_BMFZR.equals(user.getPositionCode())
                || Constant.Teacher.POSITION_BMFZ.equals(user.getPositionCode())) {
            depts.add(user.getOrgCode());
        }
        // 副院长 可以看到他分管部门的人员工作汇报
        else if (Constant.Teacher.POSITION_FYZ.equals(user.getPositionCode())) {
            if (StringUtil.isEmpty(log.getDeptCode())) {
                // 查询分管部门编码
                depts = organizationDao.queryPresidentOrgCodeByUserName(user.getUserName());
            } else {
                depts.add(log.getDeptCode());
            }
        }

        log.setApprover(user.getUserName());
        QueryParam<WorkingLog> param = new QueryParam<WorkingLog>();
        param.setQueryParam(log);
        param.setPageNumber(pageNumber);
        param.setPageSize((null == pageNumber || pageNumber == 0) ? 10 : pageSize);

        QueryResult<WorkingLog> logs = null;

        if (Constant.Teacher.POSITION_YZ.equals(user.getPositionCode())
                || Constant.Teacher.POSITION_SJ.equals(user.getPositionCode())) {
            if (!StringUtil.isEmpty(log.getDeptCode())) {
                depts.add(log.getDeptCode());
                logs = workingLogDao.queryWorkingLogForPage(param, depts, user.getOrgCode());
            } else {
                logs = workingLogDao.queryWorkingLogForPage(param);
            }
        } else {
            logs = workingLogDao.queryWorkingLogForPage(param, depts, user.getOrgCode());
        }

        List<WorkingLog> logList = logs.getDatas();
        if (null != logList && !logList.isEmpty()) {
            for (WorkingLog workingLog : logList) {

                Organization o = organizationDao.findOrgInfoByStaffNo(workingLog.getStaffNo());
                workingLog.setStaffOrg(o);

                // 部门副职如果为负责人则允许审批
                if (Constant.Teacher.POSITION_BMFZR.equals(user.getPositionCode())
                        || (Constant.Teacher.POSITION_BMFZ.equals(user.getPositionCode()) && "Y".equals(user
                                .getIsManager()))) {
                    workingLog.setIsApprove("Y");
                }
                // 副院长 可以看到他分管部门的人员工作汇报
                else if (Constant.Teacher.POSITION_FYZ.equals(user.getPositionCode())) {
                    // 查询分管部门编码
                    if (depts.contains(o.getOrgCode())) {
                        workingLog.setIsApprove("Y");
                    }
                }
                // 院长和书记可以看到所有人的工作汇报
                else if (Constant.Teacher.POSITION_YZ.equals(user.getPositionCode())
                        || Constant.Teacher.POSITION_SJ.equals(user.getPositionCode())) {
                    workingLog.setIsApprove("Y");
                }
            }
        }

        return logs;
    }

    /* (non-Javadoc)
     * @see com.hns.iusp.workinglog.service.WorkingLogService#findWorkingLogByLogTime(java.lang.String, java.lang.String)
     */
    @Override
    public WorkingLog findWorkingLogByLogTime(String logTime, String userName) {
        return workingLogDao.findWorkingLogByLogTime(logTime, userName);
    }

    /* (non-Javadoc)
     * @see com.hns.iusp.workinglog.service.WorkingLogService#findWorkingLogByLogWeekTime(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public WorkingLog findWorkingLogByLogWeekTime(String year, String term, String week, String userName) {
        return workingLogDao.findWorkingLogByLogWeekTime(year, term, week, userName);
    }

    //start add by wangxuan 2014-11-23 删除草稿
	@Override
	public int deleteDraftById(Integer id) {
		return workingLogDao.deleteWorkingLogById(id);
		
	}
	
	@Override
	public int findMyWorkingLogCountBySpr(WorkingLog log) {
		// TODO Auto-generated method stub
		   return workingLogDao.findMyWorkingLogCountBySpr(log);
	}
}