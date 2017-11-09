<ul id="menulist">
	<#if sessionUser.orgCode != '99' && sessionUser.orgCode != '98' && sessionUser.orgCode != '97'>
		<li id="menuli">
			<div class="fundiv">
				<span style="background-image:url(${resRoot}/images/write.png); background-repeat:no-repeat; padding-left:30px;" id="bg1">写工作汇报</span>
			</div>
			<ul class="divli">
				<a href="${base}/workingLog/daily.htm"><li class="cli">写日报</li></a>
				<a href="${base}/workingLog/weekly.htm"><li class="cli">写周报</li></a>
				<a href="${base}/workingLog/monthly.htm"><li class="cli" >写月报</li></a>
				<a href="${base}/workingLog/draftBox.htm"><li  style="background-color:#def0fd; padding-left:55px;padding-top:10px;" >草稿箱(<span id="draftCount">0</span>)</li></a>
			</ul>
		</li>
		<div style="background-color:#FFF; height:1px; "></div>
	</#if>
	<li id="menuli" >
		<div class="fundiv">
			<span style="background-image:url(${resRoot}/images/search.png); background-repeat:no-repeat; padding-left:30px;" id="bg2">查看工作汇报</span>
		</div>
		<ul class="divli">
			<a href="${base}/workingLog/workingLogs.htm?logType=01&logStatus=03"><li class="cli">查看日报(<span id="approvedDailyAllCount">0</span>)</li></a>
			<a href="${base}/workingLog/workingLogs.htm?logType=02&logStatus=03"><li class="cli">查看周报(<span id="approvedWeeklyAllCount">0</span>)</li></a>
			<a href="${base}/workingLog/workingLogs.htm?logType=03&logStatus=03"><li class="cli">查看月报(<span id="approvedMonthAllCount">0</span>)</li></a>
			<#if sessionUser.orgCode != '99' && sessionUser.orgCode != '98' && sessionUser.orgCode != '97'>
				<a href="${base}/workingLog/myWorkingLog.htm?logStatus=03"><li class="cli">查看我的</li></a>
			</#if>
		</ul>
	</li>
	<div style="background-color:#FFF; height:1px"></div>
	<li id="menuli" >
		<div class="fundiv">
		   <span style="background-image:url(${resRoot}/images/todo.png); background-repeat:no-repeat; padding-left:30px;" id="bg3">待审核工作汇报(<label id="approvingLogAllCount">0</label>)</span>
		</div>
		<ul class="divli" >
			<li class="cli"><a href="${base}/workingLog/workingLogs.htm?logType=01&logStatus=02">日报(<span id="checkDayCount">0</span>)</a></li>
			<li class="cli"><a href="${base}/workingLog/workingLogs.htm?logType=02&logStatus=02">周报(<span id="checkDeekCount">0</span>)</a></li>
			<li class="cli"><a href="${base}/workingLog/workingLogs.htm?logType=03&logStatus=02">月报(<span id="checkMonthCount">0</span>)</a></li>
			<#if sessionUser.orgCode != '99' && sessionUser.orgCode != '98' && sessionUser.orgCode != '97'>
				<a href="${base}/workingLog/myWorkingLog.htm?logStatus=02"><li class="cli">我的待审核(<span id="approvingLogCount">0</span>)</li></a>
			</#if>
		</ul>
	</li>
	<div style="background-color:#FFF; height:1px"></div>
	
	<#if sessionUser.orgCode != '99' && sessionUser.orgCode != '98' && sessionUser.orgCode != '97'>
		<li id="menuli" >
			<div class="fundiv">
				<span style="background-image:url(${resRoot}/images/back.png); background-repeat:no-repeat; padding-left:30px;"  id="bg4">退回(<label id="returnLogCount">0</label>)</span>
			</div>
			<ul class="divli" >
				<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=04">日报(<span id="retDayCount">0</span>)</a></li>
				<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=02&logStatus=04">周报(<span id="retDeekCount">0</span>)</a></li>
				<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=03&logStatus=04">月报(<span id="retMonthCount">0</span>)</a></li>
			</ul>
		</li>
		<div style="background-color:#FFF; height:1px"></div>
	</#if>
	
	<#if sessionUser.orgCode == '99' || sessionUser.orgCode == '98' || sessionUser.orgCode == '97' || sessionUser.isManager='Y'>
		<li id="menuli" >
			<div class="fundiv">
				<span style="background-image:url(${resRoot}/images/set.png); background-repeat:no-repeat; padding-left:30px;" id="bg5">设置</span>
			</div>
			<ul class="divli" >
				<a><li class="cli">定时审核</li></a>
				<a><li class="cli">默认评语</li></a>
			</ul>
		</li>
	</#if>
</ul>
<script type="text/javascript" language="javascript">
	$(function(){		
		$(".fundiv").click(function(){
		    $(this).parent().find("ul").slideToggle();
		});	
		$("#menulist li").click(function(){
		 var bg=$('div span',this).attr('style');
	 	  if(bg.indexOf('down')<0){
			$('div span',this).attr('style',"background-image:url(${resRoot}/images/down.png); background-repeat:no-repeat; padding-left:30px;");
		  }
		  else if(bg.indexOf('down')>=0){
			  if($('div span',this).attr('id')=='bg1'){
				  $('div span',this).attr('style','background-image:url(${resRoot}/images/write.png); background-repeat:no-repeat; padding-left:30px;');
			  }
			  if($('div span',this).attr('id')=='bg2'){
				  $('div span',this).attr('style','background-image:url(${resRoot}/images/search.png); background-repeat:no-repeat; padding-left:30px;');
			  }
			  if($('div span',this).attr('id')=='bg3'){
				  $('div span',this).attr('style','background-image:url(${resRoot}/images/todo.png); background-repeat:no-repeat; padding-left:30px;');
			  }
			  if($('div span',this).attr('id')=='bg4'){
				  $('div span',this).attr('style','background-image:url(${resRoot}/images/back.png); background-repeat:no-repeat; padding-left:30px;');
			  }
			  if($('div span',this).attr('id')=='bg5'){
				  $('div span',this).attr('style','background-image:url(${resRoot}/images/set.png); background-repeat:no-repeat; padding-left:30px;');
			  }
		  }
		});
		
		$("#menulist li").hover(function(){
		    $(this).addClass('lihov');
		},function(){
			  $(this).removeClass('lihov');
		});
		<!-- 王璇  我把此代码放下面了因为此段代码下的逻辑代码不管用 -->
		workingLog.queryMyLogCount();
		workingLog.queryWorkingLogCount();
		workingLog.queryMyCheckLogCount();
	});
 </script>