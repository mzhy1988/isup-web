<#import "/common/page.ftl" as pg>
<#--此处为获取表格数据的url--> 
<#assign requestUrl = '${base}/admin/article/queryArticleList.htm' />
  <table cellpadding="0" cellspacing="0" class="tab1">
            <tr class="th1">
         	 	<th width="480" height="30">
          			&nbsp;&nbsp;
         	 	</th>
          		<th width="77">
          		<div align="center">
         	 		作者
         	 	</div>
          		</th>
          		<th width="92">
          		<div align="center">
          			发表时间
          		</div>
         		</th>
         	 	<th width="90" >
                <div align="center">
                	操作
                </div>
                </th>
          </tr>
	<#if !queryResult?? || !queryResult.datas?? || queryResult.datas?size <= 0 >
	<tr>
		<td colspan="4" style="color: red">
			暂无数据显示
		</td>
	</tr>
	<#else>
		<#list queryResult.datas as data>
			
     	  <tr class="contitle">
         		<td  height="40"><a target="_blank" href="${base}/article.htm?id=${data.id}">&nbsp;&nbsp;${data.title}</a></td>
                <td>
                     ${data.author}
                </td>
                <td>
                    ${data.createTime?string("yyyy-MM-dd")}
                </td>
                <td>
                	<div align="center" class="cz">
                        <a href="javascript:void(0);" onclick="return confirmDelete('${data.id}','${data.type}');">删除</a>|
                       
                        <a href="${base}/admin/article/mdfArticleSearch.htm?id=${data.id}" >修改</a>
                	  
                	 </div>
                </td>
          </tr>			
		</#list>
		
	</#if>
</table> 

<#if queryResult?? && queryResult.datas?? && queryResult.datas?size gt 0>
	<#--分页栏的宏调用--> 
	<@pg.pager queryResult requestUrl pageParam 9 'grid'/>
</#if>

<script type="text/javascript">
function confirmDelete(id,type){

       if (confirm("确认要删除？")) {
            $.ajax({
			url: '${base}/admin/article/deleteArticle.htm',
			type: 'POST',
			async: false,
			dataType: "json",
			data: {"ids":id},
			success: function(data){
				if(data.flag){
					alert(data.message);
					location.replace(location.href);
				} else {
					alert(data.message);
					location.replace(location.href);
				}
			},
			error: function(){
				alert("对不起，您的操作失败，请重新操作！");
				return false;
			}
		});
        }
}
</script>
