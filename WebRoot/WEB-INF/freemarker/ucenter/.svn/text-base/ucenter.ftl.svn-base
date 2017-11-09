<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
 
    <meta name="description" content="南航金城学院信息户服务平台" />
    <meta name="keywords" content="南航金城学院信息户服务平台" />
    <!-- IE8 兼容 -->
	<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
    <link href="${resRoot}/css/base.css"  rel="stylesheet" type="text/css" />
    <link rel="stylesheet" type="text/css" href="${resRoot}/css/day.css" />
    <title>南航金城学院信息化服务平台</title>
   
    <script type="text/javascript" src="${resRoot}/js/jquery/jquery.js"></script>
    <script type="text/javascript" src="${resRoot}/js/workingLog/workingLog.js"></script>
    <script type="text/javascript" src="${resRoot}/js/login/login.js"></script>
    <script type="text/javascript" language="javascript" src="${resRoot}/js/utils/iuspUtil.js"></script>
    
    <script type="text/javascript" language="javascript">
			$(function(){
				workingLog.init('${resRoot}','${base}');
				login.setOverallVeriable('${resRoot}','${base}');
				if('${sessionUser.positionCode}' != '006'){
					workingLog.queryHintWorkingLogCount();
				} 
				workingLog.queryHintMyLogCount();
			});
	</script>
</head>
<body>

<#include "/include/header.ftl"/>

<div class="colum-wrap2 clearfix" >
<div style="width:1000px; margin:0px auto 0px auto; font-family:'微软雅黑';">
<div class="leftdiv">
	<p class="pdro" style="color:#666;margin-top:10px">个人信息</p>
    <hr  width="94%;" style="border:1px solid #D8D8D8; margin-left:3px; border-bottom:0px; border-left:0px;" >
	<div style="width:90%;min-height:25px;line-height:25px;margin:10px auto;font-size:12px">
		<img src="${resRoot}/images/people.png" alt=""/>
		<br/>
		<#if sessionUser.roleCode == '1'>
			姓　名：${sessionUser.realName}<br/>
			学　号：${sessionUser.userName}
			<br/>
			职　位：${sessionUser.positionName}
			<br/>
			院　系：${sessionUser.orgName}
		<#elseif sessionUser.roleCode == '2'>
			姓　名：${sessionUser.realName}<br/>
			工　号：${sessionUser.userName}
			<br/>
			职　位：${sessionUser.positionName}
			<br/>
			部　门：${sessionUser.orgName}
		<#elseif sessionUser.roleCode == '3'>
			用户名：${sessionUser.userName}
			<br/>
			角　色：管理员
		</#if>
	</div>
	<hr  width="94%;" style="border:1px solid #D8D8D8; margin-left:3px; border-bottom:0px; border-left:0px;" >
	<div style="width:90%;height:50px;line-height:48px;margin:5px auto;" >
	
		<a href="${base}/user/modifyBasicInfo.htm"><font style="color:#4ebdfc;">个人资料</font></a>&nbsp;&nbsp;
		<a href="${base}/user/changePwd.htm"><font style="color:#4ebdfc;">修改密码</font></a>
	</div>
	<hr  width="94%;" style="border:1px solid #D8D8D8; margin-left:3px; border-bottom:0px; border-left:0px;" >
   <!-- 王涛2014-11-23-->
	<div style="width:90%;min-height:25px;line-height:25px;margin:10px auto;font-size:12px">
		<span >在线人数：<font style="color:red;">${sessionCount}</font>人</span></br>
		<#include "/ucenter/orgTree.ftl"/>
	</div>
</div>

<div class="rightdiv" style="font-size:12px">
	<p class="pdro" style="padding-left:13px;margin-top:8px">首页</p>
    <hr  width="730px;" style="border:1px solid #D8D8D8; border-bottom:0px; border-left:0px;" >
    <div class="hint" >
    	提醒事项
    </div>
     <#if sessionUser.positionCode == '006'>
     	  <div style="width:95%;height:35px;line-height:35px;border-bottom:1px #EEE solid;margin:5px auto;">
		      <div style="width:45.5%;" class="approvaling-bg">
		    	<span style="margin-left:25px;"><a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=02" style="color:green">您的工作日志等待审核（<span style="color:red"><span id="hintapprovingLogCount">0</span>条</span>）</a></span>
		      </div>
	    	  <#if lastDailySubmited == 'N'>
		    	  <div style="width:45.5%;" class="warning-right" >
			  		<span style="margin-left:15px;color:green">您昨天工作日报未提交</span>
			      </div>
		      <#else>
		      	 <div style="width:45.5%;" class="approvaled-right">
			     	<span style="margin-left:25px;color:green">您昨天的工作日报已提交</span>
			     </div>
		       </#if>
	     </div>
    <#elseif sessionUser.positionCode == '004' || sessionUser.positionCode == '005'>
	     <div style="width:95%;height:35px;line-height:35px;border-bottom:1px #EEE solid;margin:5px auto;">
		      <div style="width:50%;" class="approvaling-bg">
		      	  <span style="margin-left:25px;"><a href="${base}/workingLog/workingLogs.htm?logType=01&logStatus=02" style="color:green">等待您审核的工作日志（<span style="color:red"><span id="hintapprovingLogAllCount">0</span> 条</span>）</a></span>
		      </div>
		      
		      <#if lastDailySubmited == 'N'>
		    	  <div style="width:45.5%;" class="warning-right" >
			    		<span style="margin-left:25px;color:green">您昨天的工作日报未提交</span>
			      </div>
		      <#else>
		      	 <div style="width:45.5%;" class="approvaled-right">
			    		<span style="margin-left:25px;color:green">您昨天工作日报已提交</span>
			      </div>
		       </#if>
	     </div>
    </#if>
    
     <#if sessionUser.positionCode != '001' && sessionUser.positionCode != '002' && sessionUser.positionCode != '003'>
     	  <div style="width:95%;height:35px;line-height:35px;border-bottom:1px #EEE solid;margin:5px auto;">
		      <div style="width:50%;" class="approvaled-bg">
		      	  <span style="margin-left:25px"><a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=03" style="color:green">您的工作日志已通过审核（<span style="color:red"><span id="hintapprovedCount">0</span> 条</span>）</a></span>
		      </div>
		      
		      <#if nowDailySubmited == 'N'>
		    	  <div style="width:45.5%;" class="warning-right" >
			    		<span style="margin-left:25px;color:green">您今日工作日报未提交</span>
			      </div>
		      <#else>
		      	<div style="width:45.5%;" class="approvaled-right">
			    		<span style="margin-left:25px;color:green">您今日工作日报已提交</span>
			      </div>
		       </#if>
	     </div>
	     
	     <div style="width:95%;height:35px;line-height:35px;border-bottom:1px #EEE solid;margin:5px auto;">
		      <div style="width:40%;" class="return-bg">
		    		<span style="margin-left:25px;"><a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=04" style="color:green">退回工作日志（<span style="color:red"><span id="hintreturnLogCount">0</span> 条</span>）</a></span>
		      </div>
		      
		      <#if nowWeeklySubmited != 'Y'>
		    	  <div style="width:45.5%;" class="warning-right" >
			    		<span style="margin-left:25px;color:green">您本周工作日报未提交</span>
			      </div>
		      <#else>
		      	 <div style="width:45.5%;" class="approvaled-right">
			    		<span style="margin-left:25px;color:green">您本周工作日报已提交</span>
			      </div>
		       </#if>
	     </div>
	 <#else>
	 	  <div style="width:95%;height:35px;line-height:35px;border-bottom:1px #EEE solid;margin:5px auto;">
		 	  	<div style="width:40%;" class="approvaling-bg">
		    		<span style="margin-left:25px;"><a href="${base}/workingLog/workingLogs.htm?logType=01&logStatus=02" style="color:green">等待您审核的工作日志（<span style="color:red"><span id="approvingLogAllCount">0</span> 条</span>）</a></span>
		      	</div>
	       </div>
	       <div style="width:95%;height:35px;line-height:35px;border-bottom:1px #EEE solid;margin:5px auto;">
			 	 <div style="width:40%;" class="approvaled-bg">
			    		<span style="margin-left:25px;"><a href="${base}/workingLog/workingLogs.htm?logType=01&logStatus=03" style="color:green">您已审批通过的工作日志（<span style="color:red"><span id="approvedAllCount">0</span> 条</span>）</a></span>
			     </div>
	      </div>
     </#if>
     <!-- -->
    <div class="colum" style="width:94%;margin:0 auto;font-size:12px">
        <div class="hot" style="width:330px;margin-top:20px"">
            <h2 class="t">最新热点</h2>
            <#if newsList?? && newsList?size &gt; 0>
            <ul class="hot-list">
              <#list newsList as data>
	              <li class="clearfix">
	                    <div class="hot-l">
	                        <span class="num   num1 " style="font-size:11px">${data.createTime?string("dd")}</span><span class="date" style="font-size:11px">${data.createTime?string("yyyy-MM")}</span>
	                    </div>
	                    <div class="hot-r">
	                        <p class="title">
	                        	<a href="${base}/article.htm?id=${data.id}" sub-line="1" target="_blank">
	                        		<#if data.title?length lt 15>   
			               				${data.title}
									<#else> 
									     ${data.title[0..15]}... 
									</#if>  
	                        	</a>
	                        </p>
	                        <div class="des" sub-line="2">
		                        <#if data.summary?length lt 15>   
		               				${data.summary}
								<#else> 
								     ${data.summary[0..15]}... 
								</#if>                       
	                      	</div>
	                    </div>
	               </li>
              </#list>
            </ul>
          	<div style="margin-top: 13px;padding-left: 7px;">
           		 <a href="${base}/hot.htm" tppabs="" style="color: #E74F26;font-family: '宋体';">详细 ></a>
          	</div>
          </#if>
        </div> 
         <!-- 前面规定图标，修改人:wangtao,修改时间：2014-11-24 -->
         <div class="hot" style="width:330px;float:right;margin-top:20px"">
          <h2 class="t">常用软件下载</h2>
                      <#if softList?? && softList?size &gt; 0>
            <ul class="hot-list">
              <#list softList as data>
	              <li class="clearfix">
	                    <div class="hot-l">
	                        <span class="num   num1 " style="font-size:11px">${data.createTime?string("dd")}</span><span class="date" style="font-size:11px">${data.createTime?string("yyyy-MM")}</span>
	                    </div>
	                    <div class="hot-r">
	                        <p class="title">
	                        	<a href="${base}/${data.downaddr}" sub-line="1" target="_blank">
	                        		<#if data.downName?length lt 15>   
			               				${data.downName}
									<#else> 
									     ${data.downName[0..15]}... 
									</#if>  
	                        	</a>
	                        </p>
	                        <div class="des" sub-line="2">
		                        <#if data.summary?length lt 15>   
		               				${data.descinfo}
								<#else> 
								     ${data.descinfo[0..15]}... 
								</#if>                       
	                      	</div>
	                    </div>
	               </li>
              </#list>
            </ul>
          	<div style="margin-top: 13px;padding-left: 7px;">
           		 <a href="${base}/hot.htm" tppabs="" style="color: #E74F26;font-family: '宋体';">详细 ></a>
          	</div>
          </#if>
        </div>
       <div style="clear:both;width:100%"></div>
        <div class="notice" style="width:330px;float:left;margin-left:0;margin-top:20px">
           <h2 class="t">通知公告</h2>
            <#if announceList?? && announceList?size &gt; 0>
            <ul class="notice-list">
              <#list announceList as data>
            	 <li>
                 	<span class="time">${data.createTime?string("yyyy.MM")}</span>
                 	<a href="${base}/article.htm?id=${data.id}" style="margin-left:10px" tppabs="" sub-line="1" target="_blank">
                 		<#if data.title?length lt 15>   
               				${data.title}
						<#else> 
						     ${data.title[0..15]}... 
						</#if>  
                 	</a>
                 </li>
              </#list>
          </ul>
       	  <div style="margin-top: 15px;padding-left: 7px;">
       		<a href="${base}/announcement.htm"  style="color: #E74F26;font-family: '宋体';">详细 ></a>
       	  </div>   
       	  </#if>
        </div>
         <div class="notice" style="width:330px;float:right;margin-top:20px">
           <h2 class="t">办事指南</h2>
            <#if znList?? && znList?size &gt; 0>
            <ul class="notice-list">
              <#list znList as data>
            	 <li>
                 	<span class="time">${data.createTime?string("yyyy.MM")}</span>
                 	<a href="${base}/article.htm?id=${data.id}" style="margin-left:10px" tppabs="" sub-line="1" target="_blank">
                 		<#if data.title?length lt 15>   
               				${data.title}
						<#else> 
						     ${data.title[0..15]}... 
						</#if>  
                 	</a>
                 </li>
              </#list>
          </ul>
       	  <div style="margin-top: 15px;padding-left: 7px;">
       		<a href="${base}/guide.htm"  style="color: #E74F26;font-family: '宋体';">详细 ></a>
       	  </div>   
       	  </#if>
           
        </div>
  	</div>
  	</div>
	<div style="clear:both"></div>
</div>
</div>
</div>

<#include "/include/ucenterFooter.ftl"/>
</body>