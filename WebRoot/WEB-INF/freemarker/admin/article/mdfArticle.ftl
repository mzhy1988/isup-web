 <#include "/admin/article/header.ftl"/>
     <div class="rightdiv" >
     <#if articlen.type ="01">
	  <p class="pdro">最新热点>修改最新热点</p>
	 </#if>
	 <#if articlen.type ="02">
	  <p class="pdro">通知公告>修改通知公告</p>
	 </#if>
	 <#if articlen.type ="03">
	  <p class="pdro">办事指南>修改办事指南</p>
	 </#if>

    <hr  width="750px;" style="border:1px solid #D8D8D8; margin-left:5px; border-bottom:0px; border-left:0px;" >
<p>&nbsp;</p>
<form>
<div style=" margin-left:20px; margin-right:50px; font-size:15px; color:#666; display:inline;">
<span>标题：</span>
    <input  type="text" name="title" id="title" style="border:1px solid #D8D8D8; height:30px; width:400px;" value="${articlen.title}"/>
</div>
    <div style=" margin-left:10px; margin-top:10px;">
    <p></p>
     <textarea id="editor" style="width:750px;height:400px;" name="content">${content}</textarea>
     <input  type="text" name="id" id="id" style="visibility:hidden" value="${articlen.id}"/>
    
    <div style="margin-left:350px; margin-top:10px;">
         <button onclick="check()" class="a_demo_one">
         &nbsp;&nbsp;修&nbsp;改&nbsp;&nbsp;
         </button>
    </div>
</form>
</div>
</div>
</div>
</div>
<#include "/admin/article/footer.ftl"/>
<script type="text/javascript">
   
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor');
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getContent() {
       return  UE.getEditor('editor').getContent();
    }

     function check(){
    	var title = $('#title').val();
     	var content = getContent();
     	if(title==""){ 
         	 alert('请填写标题！');
         	 return false;
         }
     	if(content==""){ 
       	   alert('请填写内容！');
       	   return false;
         }
         
		$.ajax({
			url: '${base}/admin/article/mdfArticle.htm',
			type: 'POST',
			async: false,
			dataType: "json",
			data: {"title" : title,"content":content,"id":${articlen.id}},
			success: function(data){
				if(data.flag){
					alert(data.message);
					window.location.reload();
				} else {
					alert(data.message);
					return false;
				}
			},
			error: function(){
				alert("对不起，您的操作失败，请重新操作！");
				return false;
			}
		});	
     }
</script>


</body>