<ul id="menulist">
	<li id="menuli">
		<div class="fundiv">
			<span style="background-image:url(${resRoot}/images/write.png); background-repeat:no-repeat; padding-left:30px;" id="bg1">写工作汇报</span>
		</div> 
		<ul class="divli">
			<a href="${base}/workingLog/daily.htm"><li style="background-color:#def0fd; padding-left:55px;padding-top:10px;">写日报</li></a>
			<a href="${base}/workingLog/weekly.htm"><li class="cli">写周报</li></a>
			<a href="${base}/workingLog/monthly.htm"><li class="cli">写月报</li></a>
			<a href="${base}/workingLog/draftBox.htm"><li class="cli">草稿箱(<span id="draftCount">0</span>)</li></a>
		</ul>
	</li>
	<div style="background-color:#FFF; height:1px; "></div>
	<li id="menuli" >
		<div class="fundiv">
		   <span style="background-image:url(${resRoot}/images/search.png); background-repeat:no-repeat; padding-left:30px;" id="bg1">查看工作汇报</span>
		</div>
		<ul class="divli" >
			<a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=03"><li class="cli">查看日报(<span id="approvedDailyCount">0</span>)</li></a>
			<a href="${base}/workingLog/myWorkingLog.htm?logType=02&logStatus=03"><li class="cli">查看周报(<span id="approvedWeeklyCount">0</span>)</li></a>
			<a href="${base}/workingLog/myWorkingLog.htm?logType=03&logStatus=03"><li class="cli">查看月报(<span id="approvedMonthCount">0</span>)</li></a>
		</ul>
	</li>
	<div style="background-color:#FFF; height:1px"></div>
	<li id="menuli" >
		<div class="fundiv">
		   <span style="background-image:url(${resRoot}/images/todo.png); background-repeat:no-repeat; padding-left:30px;" id="bg3">待审核工作汇报(<label id="approvingLogCount">0</label>)</span>
		</div>
		<ul class="divli" >
			<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=02">日报</a></li>
			<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=02&logStatus=02">周报</a></li>
			<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=03&logStatus=02">月报</a></li>
		</ul>
	</li>
	<div style="background-color:#FFF; height:1px"></div>
	<li id="menuli" >
		<div class="fundiv">
			<span style="background-image:url(${resRoot}/images/back.png); background-repeat:no-repeat; padding-left:30px;"  id="bg4">退回(<label id="returnLogCount">0</label>)</span>
		</div>
		<ul class="divli" >
			<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=01&logStatus=04">日报</a></li>
			<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=02&logStatus=04">周报</a></li>
			<li class="cli"><a href="${base}/workingLog/myWorkingLog.htm?logType=03&logStatus=04">月报</a></li>
		</ul>
	</li>
<ul>
<script type="text/javascript" language="javascript">
	workingLog.queryMyLogCount();
	$(function(){
		$(".fundiv").click(function(){
		    $(this).parent().find("ul").slideToggle();
		});
		
		$("#menulist li").click(function(){
		  if($('div span',this).attr('style')!="background-image:url(${resRoot}/images/down.png); background-repeat:no-repeat; padding-left:30px;"){
			$('div span',this).attr('style',"background-image:url(${resRoot}/images/down.png); background-repeat:no-repeat; padding-left:30px;");
		  }
		  else if($('div span',this).attr('id')=='bg1'){
			  $('div span',this).attr('style','background-image:url(${resRoot}/images/write.png); background-repeat:no-repeat; padding-left:30px;');
		  }else if($('div span',this).attr('id')=='bg2'){
			  $('div span',this).attr('style','background-image:url(${resRoot}/images/search.png); background-repeat:no-repeat; padding-left:30px;');
		  }
		  else if($('div span',this).attr('id')=='bg3'){
			  $('div span',this).attr('style','background-image:url(${resRoot}/images/todo.png); background-repeat:no-repeat; padding-left:30px;');
		  }
		  else if($('div span',this).attr('id')=='bg4'){
			  $('div span',this).attr('style','background-image:url(${resRoot}/images/back.png); background-repeat:no-repeat; padding-left:30px;');
		  }
		  else if($('div span',this).attr('id')=='bg5'){
			  $('div span',this).attr('style','background-image:url(${resRoot}/images/set.png); background-repeat:no-repeat; padding-left:30px;');
		  }
		});
		
		$("#menulist li").hover(function(){
		    $(this).addClass('lihov');
		},function(){
			  $(this).removeClass('lihov');
		});
	});
			
</script>
