/*
 * Copyright (C), 2013-2014, 南京华内斯信息技术有限公司
 * FileName: WorkingLogController.java
 * Author:   Tommy Xu
 * Date:     Nov 12, 2014 10:44:17 PM
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.hns.iusp.workinglog.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.hns.iusp.base.BaseController;
import com.hns.iusp.common.bean.Article;
import com.hns.iusp.common.bean.Organization;
import com.hns.iusp.common.bean.SessionUser;
import com.hns.iusp.common.bean.Software;
import com.hns.iusp.common.constant.Constant;
import com.hns.iusp.common.service.ArticleService;
import com.hns.iusp.common.service.OrganizationService;
import com.hns.iusp.common.service.SoftwareService;
import com.hns.iusp.common.service.UserService;
import com.hns.iusp.utils.BasicAjaxUtil;
import com.hns.iusp.utils.DateUtil;
import com.hns.iusp.utils.QueryResult;
import com.hns.iusp.utils.StringUtil;
import com.hns.iusp.workinglog.bean.WorkingLog;
import com.hns.iusp.workinglog.service.WorkingLogService;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 * 
 * @author Tommy Xu
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Controller
@RequestMapping("/workingLog")
public class WorkingLogController extends BaseController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private WorkingLogService workingLogService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private SoftwareService softwareService;

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/index")
    public String toWorkingLogPage(HttpServletRequest request) {
        List<Article> newsList = articleService.queryArticleByLimit("01", 2);
        List<Article> announceList = articleService.queryArticleByLimit("02", 5);
        List<Article> znList = articleService.queryArticleByLimit("03", 5);
        List<Software> softList = softwareService.querySoftwareByLimit("01",5);
        request.setAttribute("newsList", newsList);
        request.setAttribute("announceList", announceList);
        request.setAttribute("znList", znList);
        request.setAttribute("softList", softList);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        WorkingLog nowLog = workingLogService
                .findWorkingLogByLogTime(sdf.format(new Date()), this.getUserName(request));
        request.setAttribute("nowDailySubmited", null == nowLog ? "N" : "Y");

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        WorkingLog lastLog = workingLogService
                .findWorkingLogByLogTime(sdf.format(cal.getTime()), this.getUserName(request));
        request.setAttribute("lastDailySubmited", null == lastLog ? "N" : "Y");

        return "workingLog/workingLog.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈跳转到日报填写界面〉
     * 
     * @param request
     * @param response
     * @param id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/daily")
    public String toAddDailyPage(HttpServletRequest request, HttpServletResponse response, String id) {

        if (!StringUtil.isEmpty(id) && !StringUtil.isNumeric(id)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }

        WorkingLog log = new WorkingLog();
        if (!StringUtil.isEmpty(id)) {
            log = workingLogService.findWorkingLogById(Integer.parseInt(id));
        }

        request.setAttribute("log", log);
        return "workingLog/daily.ftl";
    }

    @RequestMapping("/weekly")
    public String toAddWeeklyPage(HttpServletRequest request, HttpServletResponse response, String id) {

        if (!StringUtil.isEmpty(id) && !StringUtil.isNumeric(id)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }

        WorkingLog log = new WorkingLog();
        if (!StringUtil.isEmpty(id)) {
            log = workingLogService.findWorkingLogById(Integer.parseInt(id));
        }

        // 获取周数
        int weekCount = StringUtil.getWeekDateDiff(DateUtil.parseStrToDate(Constant.Weekly.BEGIN_TIME), DateUtil
                .parseStrToDate(Constant.Weekly.END_TIME));
        request.setAttribute("weekCount", weekCount);
        request.setAttribute("year", Constant.Weekly.YEAR);
        request.setAttribute("term", Constant.Weekly.TERM);

        request.setAttribute("log", log);
        return "workingLog/weekly.ftl";
    }

    @RequestMapping("/monthly")
    public String toAddMonthLyPage(HttpServletRequest request, HttpServletResponse response, String id) {

        if (!StringUtil.isEmpty(id) && !StringUtil.isNumeric(id)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }

        WorkingLog log = new WorkingLog();
        if (!StringUtil.isEmpty(id)) {
            log = workingLogService.findWorkingLogById(Integer.parseInt(id));
        }

        request.setAttribute("log", log);
        return "workingLog/monthly.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈保存草稿〉
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/saveDraft")
    public String saveDraft(HttpServletRequest request, HttpServletResponse response, WorkingLog workingLog) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", false);
        if (null == workingLog) {
            jsonObj.addProperty("message", "无提交数据");
        } else {
            workingLog.setStaffNo(this.getUserName(request));
            int result = workingLogService.saveDraft(workingLog);
            if (result > 0) {
                jsonObj.addProperty("flag", true);
            } else {
                jsonObj.addProperty("message", "草稿保存失败");
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }
    /**
     * 功能描述: <br>
     * 〈删除草稿〉
     * 
     * @author wangxuan
     * @param request
     * @param response
     * @param ids 工作日志的id
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
   @RequestMapping("/deleteDraftById")
    public String deleteDraft(HttpServletRequest request, HttpServletResponse response, String ids) {

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("flag", false);

        if (StringUtil.isEmpty(ids)) {
            jsonObject.addProperty("message", "请选择需要删除的数据");
        } else {
            String[] idArr = ids.split(",");
            try {
                for (String id : idArr) {
                	workingLogService.deleteDraftById(Integer.parseInt(id));
                }
                jsonObject.addProperty("flag", true);
                jsonObject.addProperty("message", "删除成功");
            } catch (RuntimeException e) {
                jsonObject.addProperty("message", "系统异常：" + e.getMessage());
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObject.toString());

        return null;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param response
     * @param workingLog
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/submitWorkingLog")
    public String submitWorkingLog(HttpServletRequest request, HttpServletResponse response, WorkingLog workingLog) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", false);
        if (null == workingLog) {
            jsonObj.addProperty("message", "无提交数据");
        } else {
            try {
                workingLog.setStaffNo(this.getUserName(request));
                workingLog.setStaffName(this.getSessionUser(request).getRealName());
                int result = workingLogService.addWorkingLog(workingLog);
                if (result > 0) {
                    jsonObj.addProperty("flag", true);
                } else {
                    jsonObj.addProperty("message", "提交失败");
                }
            } catch (RuntimeException e) {
                e.printStackTrace();
                jsonObj.addProperty("message", e.getMessage());
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/draftBox")
    public String toDraftListPage(HttpServletRequest request) {
        return "workingLog/draftList.ftl";
    }

    @RequestMapping("/myWorkingLog")
    public String myWorkingLog(HttpServletRequest request, HttpServletResponse response, String logType,
            String logStatus) {

        String positionCode = this.getUserName(request);

        if (Constant.Teacher.POSITION_JZG.equals(positionCode)) {
            if (StringUtil.isEmpty(logType) || StringUtil.isEmpty(logStatus)) {
                this.redirectUrl(request.getContextPath() + "/index.htm", response);
                return null;
            }
        } else {
            if (StringUtil.isEmpty(logStatus)) {
                this.redirectUrl(request.getContextPath() + "/index.htm", response);
                return null;
            }
        }

        request.setAttribute("logType", logType);
        request.setAttribute("logStatus", logStatus);
        return "workingLog/myWorkingLogList.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param log
     * @param pageNumber
     * @param pageSize
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/queryMyWorkingLogList")
    public String queryMyWorkingLogList(HttpServletRequest request, WorkingLog log, Integer pageNumber, Integer pageSize) {

        if (null == log) {
            throw new RuntimeException("参数不存在");
        }

        log.setStaffNo(this.getUserName(request));

        QueryResult<WorkingLog> queryResult = workingLogService.queryMyWorkingLogForPage(log, pageNumber, pageSize);
        request.setAttribute("queryResult", queryResult);

        // 查询参数回写
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("logType", null != log ? log.getLogType() : "");
        pageParam.put("logStatus", null != log ? log.getLogStatus() : "");
        request.setAttribute("pageParam", pageParam);

        if (Constant.WorkingLog.LOG_STATUS_DRAFT.equals(log.getLogStatus())) {
            return "workingLog/draftQueryList.ftl";
        } else {
            return "workingLog/myWorkingLogQueryList.ftl";
        }
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param response
     * @param workingLog
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("queryMyLogCount")
    public String queryMyLogCount(HttpServletRequest request, HttpServletResponse response) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", true);

        WorkingLog log = new WorkingLog();
        // draftCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_DRAFT);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("draftCount", workingLogService.findMyWorkingLogCount(log));

        log = new WorkingLog();
        // approvedDailyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUCCESS);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_DAILY);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("approvedDailyCount", workingLogService.findMyWorkingLogCount(log));

        log = new WorkingLog();
        // approvedWeeklyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUCCESS);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_WEEK);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("approvedWeeklyCount", workingLogService.findMyWorkingLogCount(log));

        log = new WorkingLog();
        // approvedMonthCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUCCESS);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_MONTH);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("approvedMonthCount", workingLogService.findMyWorkingLogCount(log));

        log = new WorkingLog();
        // approvingLogCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("approvingLogCount", workingLogService.findMyWorkingLogCount(log));

        log = new WorkingLog();
        // returnLogCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_FAIL);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("returnLogCount", workingLogService.findMyWorkingLogCount(log));

        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }

    @RequestMapping("queryWorkingLogCount")
    public String queryWorkingLogCount(HttpServletRequest request, HttpServletResponse response) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", true);

        SessionUser user = this.getSessionUser(request);

        WorkingLog log = new WorkingLog();

        log = new WorkingLog();
        // approvedDailyAllCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUCCESS);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_DAILY);
        log.setApprover(this.getUserName(request));
        jsonObj.addProperty("approvedDailyAllCount", workingLogService.findWorkingLogCount(log, user));

        log = new WorkingLog();
        // approvedWeeklyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUCCESS);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_WEEK);
        log.setApprover(this.getUserName(request));
        jsonObj.addProperty("approvedWeeklyAllCount", workingLogService.findWorkingLogCount(log, user));

        log = new WorkingLog();
        // approvedMonthCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUCCESS);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_MONTH);
        log.setApprover(this.getUserName(request));
        jsonObj.addProperty("approvedMonthAllCount", workingLogService.findWorkingLogCount(log, user));

        log = new WorkingLog();
        // approvingLogCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setApprover(this.getUserName(request));
        jsonObj.addProperty("approvingLogAllCount", workingLogService.findWorkingLogCount(log, user));

        log = new WorkingLog();
        // returnLogCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_FAIL);
        log.setApprover(this.getUserName(request));
        jsonObj.addProperty("returnLogAllCount", workingLogService.findWorkingLogCount(log, user));

        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }

    @RequestMapping("/viewWorkingLog")
    public String viewWorkingLog(HttpServletRequest request, HttpServletResponse response, String id) {

        if (StringUtil.isEmpty(id) || !StringUtil.isNumeric(id)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }
        WorkingLog log = workingLogService.findWorkingLogById(Integer.parseInt(id));
        SessionUser user = new SessionUser();
        if (null != log) {
            user = userService.findSessionUserByUserName(log.getStaffNo(), Constant.Role.ROLECODE_TEACHER);
        }
        request.setAttribute("user", user);

        request.setAttribute("log", log);

        return "workingLog/viewWorkingLog.ftl";
    }

    @RequestMapping("/approveWorkingLog")
    public String approveWorkingLog(HttpServletRequest request, HttpServletResponse response, String id) {

        if (StringUtil.isEmpty(id) || !StringUtil.isNumeric(id)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }
        WorkingLog log = workingLogService.findWorkingLogById(Integer.parseInt(id));
        SessionUser user = new SessionUser();
        if (null != log) {
            user = userService.findSessionUserByUserName(log.getStaffNo(), Constant.Role.ROLECODE_TEACHER);
        }
        request.setAttribute("user", user);
        request.setAttribute("log", log);

        return "workingLog/approveWorkingLog.ftl";
    }

    @RequestMapping("/submitApproveWorkingLog")
    public String submitApproveWorkingLog(HttpServletRequest request, HttpServletResponse response,
            WorkingLog workingLog) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", false);
        if (null == workingLog) {
            jsonObj.addProperty("message", "无提交数据");
        } else {
            try {
                workingLog.setApprover(this.getUserName(request));
                workingLog.setApproverName(this.getSessionUser(request).getRealName());
                if (Constant.WorkingLog.LOG_STATUS_FAIL.equals(workingLog.getLogStatus())) {
                    workingLog.setScore(null);
                }
                int result = workingLogService.updateWorkingLog(workingLog);
                if (result > 0) {
                    jsonObj.addProperty("flag", true);
                } else {
                    jsonObj.addProperty("message", "提交失败");
                }
            } catch (RuntimeException e) {
                jsonObj.addProperty("message", e.getMessage());
            }
        }

        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }

    // -------------------------------------------------------部门负责人、院长、书记、副院长等-----------------------------------------------------------
    @RequestMapping("/workingLogs")
    public String toWorkingLogListPage(HttpServletRequest request, HttpServletResponse response, String logType,
            String logStatus) {

        if (StringUtil.isEmpty(logType) || StringUtil.isEmpty(logStatus)) {
            this.redirectUrl(request.getContextPath() + "/index.htm", response);
            return null;
        }

        List<Organization> depts = null;
        SessionUser user = this.getSessionUser(request);
        if (Constant.Teacher.POSITION_FYZ.equals(user.getPositionCode())) {
            // 查询分管部门编码
            depts = organizationService.queryPresidentOrgByUserName(user.getUserName());
        }

        request.setAttribute("depts", depts);

        request.setAttribute("logType", logType);
        request.setAttribute("logStatus", logStatus);
        return "workingLog/workingLogList.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param log
     * @param pageNumber
     * @param pageSize
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/queryWorkingLogList")
    public String queryWorkingLogList(HttpServletRequest request, WorkingLog log, Integer pageNumber, Integer pageSize) {

        if (null == log) {
            throw new RuntimeException("参数不存在");
        }

        QueryResult<WorkingLog> queryResult = workingLogService.queryWorkingLogForPage(log, pageNumber, pageSize, this
                .getSessionUser(request));
        request.setAttribute("queryResult", queryResult);

        // 查询参数回写
        Map<String, Object> pageParam = new HashMap<String, Object>();
        pageParam.put("logType", null != log ? log.getLogType() : "");
        pageParam.put("logStatus", null != log ? log.getLogStatus() : "");
        pageParam.put("staffNo", null != log ? log.getLogStatus() : "");
        pageParam.put("staffName", null != log ? log.getStaffName() : "");
        pageParam.put("deptCode", null != log ? log.getDeptCode() : "");
        request.setAttribute("pageParam", pageParam);

        return "workingLog/workingLogQueryList.ftl";
    }

    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param orgType
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("/queryOrgsByOrgType")
    public String queryOrgsByOrgType(HttpServletRequest request, HttpServletResponse response, String orgType) {

        List<Organization> orgList = null;
        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", true);

        if (!StringUtil.isEmpty(orgType)) {
            orgList = organizationService.queryOrgListByOrgType(orgType);
        } else {
            orgList = new ArrayList<Organization>();
        }

        Gson gson = new Gson();
        jsonObj.addProperty("data", gson.toJson(orgList));

        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }
    
    /**
     * 功能描述: <br>
     * 〈功能详细描述〉
     * 
     * @param request
     * @param response
     * @param workingLog
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    @RequestMapping("queryMyCheckLogCount")
    public String queryMyCheckLogCount(HttpServletRequest request, HttpServletResponse response) {

        JsonObject jsonObj = new JsonObject();
        jsonObj.addProperty("flag", true);
        
        //待审核下的数量
        WorkingLog log = new WorkingLog();
        // draftCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_DAILY);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("dailyCheckCount", workingLogService.findMyWorkingLogCountBySpr(log));
        
        log = new WorkingLog();
        // approvedDailyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_WEEK);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("weekCheckyCount", workingLogService.findMyWorkingLogCountBySpr(log));
        
        log = new WorkingLog();
        // approvedWeeklyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setLogType(Constant.WorkingLog.LOG_TYPE_MONTH);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("monthCheckCount", workingLogService.findMyWorkingLogCountBySpr(log));
        
        //退回菜单下的数量
        
        // draftCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setLogType(Constant.WorkingLog.LOG_STATUS_FAIL);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("returnDailyCheckCount", workingLogService.findMyWorkingLogCount(log));
        
        log = new WorkingLog();
        // approvedDailyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setLogType(Constant.WorkingLog.LOG_STATUS_FAIL);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("returnWeekCheckyCount", workingLogService.findMyWorkingLogCount(log));
        
        log = new WorkingLog();
        // approvedWeeklyCount
        log.setLogStatus(Constant.WorkingLog.LOG_STATUS_SUBMITED);
        log.setLogType(Constant.WorkingLog.LOG_STATUS_FAIL);
        log.setStaffNo(this.getUserName(request));
        jsonObj.addProperty("returnMonthCheckCount", workingLogService.findMyWorkingLogCount(log));
       
        
        BasicAjaxUtil.writeJson(response, jsonObj.toString());

        return null;
    }

}
