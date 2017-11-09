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
					workingLog.deptInit();
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
				<span class="pdro">工作汇报><#if logStatus=='02'>待审核<#elseif logStatus=='03'>已审核</#if></span>
				<input type="hidden" id="logStatus" value="${logStatus}"/>
				<input type="hidden" id="logType" value="${logType}"/>
		    </div>
		    <hr  width="765px;" style="border:1px solid #D8D8D8; margin-left:5px; border-bottom:0px; border-left:0px;">
		    </span>
		    
		   <table width="760" border="1px" bordercolor="#D8D8D8"  style="float:left; margin-top:10px; margin-left:10px;">
		 	  <tr>
			    	<td width="30px;" style="border-left:hidden;border-top:hidden;border-width:1px; border-bottom:1px solid #D8D8D8;">
			        </td>
			    	<td style="font-size:15px; color:#666;border:1px #D8D8D8 solid; border-bottom:0;border-bottom:hidden;height:30px;text-align:center">	
			    		<#if logType=='01'>日报<#elseif logType=='02'>周报<#elseif logType=='03'>月报</#if>
			    	</td>
			    	<td width="650px;" style="border-right:hidden;border-top:hidden;border-width:1px; border-bottom:1px solid #D8D8D8;">
						
						<div style="float:right; margin-left:5px;">
				            <input type="text" id="staffNo" value="查询工号" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999;vertical-align:bottom;" size="10">
				            <input type="text" id="staffName" value="查询姓名" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color:#999999;vertical-align:bottom;" size="10">
				            <input id="deptSearch" value=" 查　询 " type="submit" style=" padding:4px; border:none; background-color:#00a0ef; color:#FFF; height:30px">
			            </div>
							<!--部门负责人或者部门副职直接查询自己部门教职工的日志-->
						<#if sessionUser.positionCode='001' ||  sessionUser.positionCode='002'>
							<div style="margin-left:10px; float:right;" >         		
								<select style="height:27px; border:1px solid #CCC;" id="deptCode">
									<option value="">查询部门</option>
					            </select>  
				            </div>
				            
				        	<div style="margin-left:5px; float:right; " >         		
								<select style="height:27px; border:1px solid #CCC;" id="orgType" onchange="workingLog.queryOrgsByOrgType(this)">
									<option value="">查询部门属性</option>
					            	<option value="01">领导层</option>
					                <option value="02">行政部门</option>
					                <option value="03">直属部门</option>
					                <option value="04">教学部门</option>
					            </select> 
				 			</div>
			            </#if>
			            <#if sessionUser.positionCode='003'>
			            	<div style="margin-left:5px; float:right; " >         		
								<select style="height:27px; border:1px solid #CCC;" id="deptCode">
									<#list depts as data>
					            		<option value="${data.orgCode}">${data.orgName}</option>
					                </#list>
					            </select> 
				 			</div>
			            </#if>
			            <span  style="float:right;margin-left:50px; margin-top:3px;">查询条件</span>
			        </td>
			    </tr>
		    </table>
		    
		    <div id="logGrid" class="grid"></div>
		</div>
	</div>
</div>
<#include "/include/footer.ftl"/>
</body>