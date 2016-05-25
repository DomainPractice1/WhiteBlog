<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
    
    <title>写博客</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"> 
	
	<link href="assets/css/bootstrap.min.css" rel="stylesheet">
	<!-- Font Awesome CSS -->
	<link href="assets/css/font-awesome.min.css" rel="stylesheet">
	<!-- Jasny CSS -->
	<link href="assets/css/jasny-bootstrap.min.css" rel="stylesheet">
	<!-- Animate CSS -->
	<link href="assets/css/animate.css" rel="stylesheet">
	<!-- Code CSS -->
	<link href="assets/css/tomorrow-night.css" rel="stylesheet" />
	<!-- Gallery CSS -->
	<link href="assets/css/bootstrap-gallery.css" rel="stylesheet">
	<!-- ColorBox CSS -->
	<link href="assets/css/colorbox.css" rel="stylesheet">
	<!-- Custom font -->
	<link href='assets/css/googleFont.css' rel='stylesheet' type='text/css'>
	<link href='assets/css/googleFont2.css' rel='stylesheet' type='text/css'>
	<!-- Custom styles for this template -->
	<link href="assets/css/style.css" rel="stylesheet">
	
	<link type="text/css" rel="stylesheet" href="MyEditor/xheditor_skin/default/ui.css" />
	<script type="text/javascript" src="MyEditor/JS/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="MyEditor/JS/xheditor-1.2.2.min.js"></script>		
	<script type="text/javascript" src="MyEditor/JS/zh-cn.js"></script>
				
	<style type="text/css">
		.btnCode {
		background:transparent url(assets/img/code.gif) no-repeat 16px 16px;
		background-position:2px 2px;
		}
	</style>
	<script type="text/javascript">		
		$(document).ready(function(){  
			var plugins={
				Code:{c:'btnCode',t:'插入代码',h:1,e:function(){
					var _this=this;
					var htmlCode='<div><select id="xheCodeType"><option value="html">HTML/XML</option><option value="js">Javascript</option><option value="css">CSS</option><option value="php">PHP</option><option value="java">Java</option><option value="py">Python</option><option value="pl">Perl</option><option value="rb">Ruby</option><option value="cs">C#</option><option value="c">C++/C</option><option value="vb">VB/ASP</option><option value="">其它</option></select></div><div><textarea id="xheCodeValue" wrap="soft" spellcheck="false" style="width:300px;height:100px;" /></div><div style="text-align:right;"><input type="button" id="xheSave" value="确定" /></div>';			
					var jCode=$(htmlCode),jType=$('#xheCodeType',jCode),jValue=$('#xheCodeValue',jCode),jSave=$('#xheSave',jCode);
					jSave.click(function(){
						_this.loadBookmark();						
						_this.pasteText('[code='+jType.val()+']\r\n'+jValue.val()+'\r\n[/code]');						
						_this.hidePanel();
						return false;	
					});
					_this.saveBookmark();
					_this.showDialog(jCode);
				}},		
			};
			$('#elm1').xheditor({
				plugins:plugins,
				tools:'full',				
				showBlocktag:false,
				forcePtag:false,
				shortcuts:{'ctrl+enter':submitForm},
				skin:'default',  
				html5Upload:false,
				upMultiple:'1',
                //upMultiple:false,  
                upImgUrl: "UploadFileServlet",  
                upImgExt: "jpg,jpeg,gif,bmp,png",  
                onUpload:insertUpload  
			});            
            //xbhEditor编辑器图片上传回调函数  
            function insertUpload(msg) {  
                var _msg = msg.toString();  
                var _picture_name = _msg.substring(_msg.lastIndexOf("/")+1);  
                var _picture_path = Substring(_msg);  
                var _str = "<input type='checkbox' name='_pictures' value='"+_picture_path+"' checked='checked' onclick='return false'/><label>"+_picture_name+"</label><br/>";  
                $("#elm1").append(_msg);  
                $("#uploadList").append(_str);  
            }  
            //处理服务器返回到回调函数的字符串内容,格式是JSON的数据格式.  
            function Substring(s){  
                return s.substring(s.substring(0,s.lastIndexOf("/")).lastIndexOf("/"),s.length);  
            }  
        });  
		function submitForm(){$('#frmDemo').submit();}
		//var document.getElementsByName("filedata");
	</script>	
  </head>
<body>
  <s:div class="canvas">
	<s:div cssClass="canvas-overlay"></s:div>
  		<header>
		<nav class="navbar navbar-fixed-top nav-down navbar-laread">
			<s:div cssClass="container">
				<s:div cssClass="navbar-header">
					<s:a cssClass="navbar-brand" href="index.jsp"><img height="64" src="assets/img/logo-light.png" alt=""></s:a>
				</s:div>				
				<s:a href="#" data-toggle="modal" data-target="#login-form" cssClass="modal-form">
					<i class="fa fa-user"></i>
				</s:a>														
			</s:div>
		</nav>
		</header>  
		  
		<s:div cssClass="container">		
			<s:form action="publish" method="POST" id="frmDemo" enctype="multipart/form-data" theme="simple">	
				<s:div cssClass="head-text">				
					<h1>${sessionScope.loginUser.username}</h1>
				</s:div>			
	      	<s:div>		 	            		       	      
	           <p>文章标题</p>                     
	           <s:textfield  cssClass="form-control" name="title" size="8"/>
	           <s:fielderror cssStyle="color:red;font-size:8pt;list-style-type:none;margin-left:0;"> 
	    		  <s:param>title</s:param>    
	   		   </s:fielderror>
	        </s:div>   
	        <s:div>                          
	          	<p>文章内容</p>             	          	 
	          	<s:textarea id="elm1" name="content" class="xheditor" rows="12" cols="80" style="width: 100%"></s:textarea>
	          	<s:fielderror cssStyle="color:red;font-size:8pt;list-style-type:none;"> 
	    		  <s:param>content</s:param>    
	   		   </s:fielderror>                        
	      	</s:div>	
	      	<s:div id="uploadList"></s:div>        		      	
	      	<s:div>
		       	<p>文章分类</p>
		       	<s:radio list="#{'0':'移动开发','1':'Web前端','2':'架构设计','3':'编程语言','4':'互联网','5':'数据库','6':'系统运维','7':'云计算','8':'研发管理','9':'综合'}" name="category"/>		      		     
	        </s:div>
	      	<s:div>
		       	<p>新建分类</p>
		       	<s:textfield cssClass="form-control" name="tags" size="8"/><s:label>（多个分类之间用“,”分隔）</s:label>
		       	<s:fielderror cssStyle="color:red;font-size:8pt;list-style-type:none;"> 
	    		  <s:param>tags</s:param>    
	   		   </s:fielderror>                  
	        </s:div>        
			<s:div> 
			<s:div cssStyle="padding-top:1%;margin:0 auto;">
	        <s:submit value="发表" cssClass="btn btn-grey btn-outline btn-rounded"/>      
	        </s:div>
	        <s:property value="hint"></s:property>
	        </s:div>                   
	   </s:form>		
		</s:div>		
	</s:div>  	   
  </body>
</html>