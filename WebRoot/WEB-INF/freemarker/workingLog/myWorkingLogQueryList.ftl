<#import "/common/page.ftl" as pg>
<#--此处为获取表格数据的url--> 
<#assign requestUrl = '${base}/workingLog/queryMyWorkingLogList.htm' />
<div style="padding-top:50px;">
<table  border="1"  class="ctab">
    <tr align="center" class="tt">
    	<td>部门属性</td>
    	<td>部门</td>
    	<td>工号</td>
    	<td>姓名</td>
    	<td>汇报类型</td>
    	<td>日志时间</td>
    	<td>提交时间</td>
    	<#if pageParam.logStatus!='02'>
	    	<td>评分</td>
	    	<td>审核人</td>
    	</#if>
    	<td>&nbsp;</td>
     </tr>
    <#if !queryResult?? || !queryResult.datas?? || queryResult.datas?size <= 0>
	<tr>
		<td colspan="<#if pageParam.logStatus!='02'>10<#else>8</#if>" style="color: red;text-align:center">
			暂无数据显示
		</td>
	</tr>
	<#else>
		<#list queryResult.datas as data>
		    <tr align="center" style="font-size:14px;" height="28px;">
		    	<td>${sessionUser.orgTypeName}</td>
			    <td>${sessionUser.orgName}</td>
			    <td>${sessionUser.userName}</td>
			    <td>${sessionUser.realName}</td>
			    <td>
			    	<#if data.logType == '01'>
			    		日报
			    	<#elseif data.logType == '02'>
			    		周报
			    	<#elseif data.logType == '03'>
			    		月报
			    	</#if>
			    </td>
			    <td>
			    	<#if data.logType =='02'>
			    		第${data.week}周
			    	<#else>
			    		${data.logTime}
			    	</#if>
			    </td>
			    <td>${data.fillTime?string("yyyy-MM-dd HH:mm:ss")}</td>
			    <#if pageParam.logStatus!='02'>
			    <td>${data.score}</td>
			    <td>${data.approverName}</td>
			    </#if>
			    <td class="look">
			    	<#if data.logStatus!='04'>
			    		<span><a href="${base}/workingLog/viewWorkingLog.htm?id=${data.id}">查看</a></span>
			    	<#else>
			    		<span><a href="${base}/workingLog/<#if data.logType == '01'>daily<#elseif data.logType == '02'>weekly<#elseif data.logType == '03'>monthly</#if>.htm?id=${data.id}">编辑</a></span>
			    	</#if>
			    </td>
		    </tr>
	   </#list>
	</#if>
</table>
<#if queryResult?? && queryResult.datas?? && queryResult.datas?size gt 0>
	<#--分页栏的宏调用--> 
	<@pg.pager queryResult requestUrl pageParam 9 'logGrid'/>
</#if>
</div>