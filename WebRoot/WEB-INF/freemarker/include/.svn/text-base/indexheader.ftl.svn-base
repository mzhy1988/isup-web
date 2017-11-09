<div class="headerwrap">
    <div class="header wrap clearfix">
       <!--  <span class="menu"><a href="javascript:void(0);" >网站地图（待开发）</a></span> --> <span class="menu"><a href="javascript:void(0);">校内通讯录（待开发）</a></span>
    </div>
</div>
<div class="form">
    
<div class="wrap-con">
        <div class="navwrap clearfix">
            <div class="logowrap fL">
            	<a href="${base}/index.htm"><img src="${resRoot}/images/navlogo.png" alt="" width="460"></a>
          </div>
            <div class="nav">
            	<span class="">
            		<a href="${base}/index.htm" >首页</a>
            	</span>
           	 	<span>
              		<a href="javascript:void(0);">学生服务指南</a>
          	</span>
            	<span>
              		<a href="javascript:void(0);">教工服务指南</a>
          	</span>
            	<span class="last">
                  	<a href="javascript:void(0);">单位服务指南</a>
          	</span>
          </div>
        </div>
        <div class="userwrap clearfix">
          <form id="login-form" action="loginAjax" method="post">
            <div class="aside">
            <p>&nbsp;</p>
                <div class="t">
                	<span class="t-bg"><a class="login-con" href="">登录说明</a></span>
                	<span class="t-bg"><a class="forget-par" href="">忘记密码?</a></span>
                	<h3>用户登录</h3>
              </div>
                <div class="login" id="loginPanel">
                	<#include "/include/loginInfo.ftl"/>
                </div>
            </div>
            </form>
            <div class="article">
                <div class="menu clearfix menu1">
               	  <h2 class="w2">常用软件下载</h2>
                </div>
                <div class="download clearfix">
                	<#if softList?? && softList?size &gt; 0>
	                    <ul class="download-l">
	                    	<#list softList as data>
		           				<li>
		                        	<a href="${base}/${data.downaddr}" target="_blank">
		                       			<#if data.downName?length lt 12>   
		                       				${data.downName}
										<#else> 
										     ${data.downName[0..12]}... 
										</#if>
		                            </a>
		                        </li>
	                        </#list>
	                  	</ul>
                  	</#if>
                    <div class="download-r">
                        <img src="${resRoot}/images/download1.png">
                    </div>
                </div>
            </div> 
        </div>
    </div>
</div>