<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	 	<meta name="description" content="南航金城学院信息户服务平台" />
	    <meta name="keywords" content="南航金城学院信息户服务平台" />
	    <!-- IE8 兼容 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
	    <link href="${resRoot}/css/base.css"  rel="stylesheet" type="text/css" />
	    <link href="${resRoot}/css/day.css" rel="stylesheet" type="text/css" />
	    <link href="${resRoot}/css/page.css" rel="stylesheet" type="text/css" />
	    <title>南航金城学院信息化服务平台 - 工作日志</title>
	    <script type="text/javascript" src="${resRoot}/js/jquery/jquery.js"></script>
	    <script type="text/javascript" src="${resRoot}/js/workingLog/workingLog.js"></script>
	    <script type="text/javascript" language="javascript" src="${resRoot}/js/utils/iuspUtil.js"></script>
	     <script language="javascript" type="text/javascript" src="${resRoot}/js/utils/My97DatePicker/WdatePicker.js"></script>
	     <script type="text/javascript" language="javascript" src="${resRoot}/js/utils/page.js"></script>
	    
	    <script type="text/javascript" language="javascript">
				$(function(){
					workingLog.init('${resRoot}','${base}');
					workingLog.myWorkingListInit();
				});
		 </script>
	</head>
<body>


<#include "/include/header.ftl"/>

<div class="colum-wrap clearfix" >
	<div style="width:1000px; margin:0px auto 0px auto; font-family:'微软雅黑';">
		<div class="leftdiv"> 
			<#if sessionUser.positionCode = '006'>
				<#include "/workingLog/menu.ftl"/>
			<#else>
				<#include "/workingLog/deptmenu.ftl"/>
			</#if>
		</div>
		<div class="rightdiv" >
			<div style="margin-top:10px;padding-left:5px">
				<span class="pdro">工作汇报>我的<#if logStatus=='02'>待审核<#elseif logStatus=='03'>已审核<#elseif logStatus=='04'>退回</#if></span>
				<input type="hidden" id="logStatus" value="${logStatus}"/>
				<#if sessionUser.positionCode != '006'>
					<input type="hidden" id="logType" value="01"/>
				<#else>
					<input type="hidden" id="logType" value="${logType}"/>
				</#if>
		    </div>
		    <hr  width="765px;" style="border:1px solid #D8D8D8; margin-left:5px; border-bottom:0px; border-left:0px;">
		    </span>
		    
		    <table id="pTabDiv" width="760" border="1px" bordercolor="#D8D8D8"  style="float:left; margin-top:10px; margin-left:10px;">
			    <tr>
			    	<td width="30px;" style="border-left:hidden;border-top:hidden;border-width:1px; border-bottom:1px solid #D8D8D8;">
			        </td>
			        <#if sessionUser.positionCode != '006'>
			    	<td mark="tab" logType="01" class="ptab">	
			    		日报
			    	</td>
			    	<td mark="tab" logType="02" class="ptabn">	
			    		周报
			    	</td>
			    	<td mark="tab" logType="03" class="ptabn">	
			    		月报
			    	</td>
			    	<td width="470px;" style="border-right:hidden;border-top:hidden;border-width:1px; border-bottom:1px solid #D8D8D8;text-align:right">
			        </td>
			        <#else>
			        	<td style="font-size:15px; color:#666;border:1px #D8D8D8 solid; border-bottom:0;border-bottom:hidden;height:30px;text-align:center">	
			    		<#if logType=='01'>日报<#elseif logType=='02'>周报<#elseif logType=='03'>月报</#if>
			    		<td width="670px;" style="border-right:hidden;border-top:hidden;border-width:1px; border-bottom:1px solid #D8D8D8;text-align:right">
			    		</td>
					</#if>
			    </tr>
			    </table>
		    
		    <div id="logGrid" class="grid"></div>
		</div>
	</div>
</div>
<#include "/include/footer.ftl"/>
</body>