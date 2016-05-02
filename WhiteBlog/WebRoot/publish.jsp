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
		
  </head>
  <body>
  
  	<div class="canvas">
	<s:div cssClass="canvas-overlay"></s:div>
  		<header>
		<nav class="navbar navbar-fixed-top nav-down navbar-laread">
			<s:div cssClass="container">
				<s:div cssClass="navbar-header">
					<a cssClass="navbar-brand" href="index.jsp"><img height="64" src="assets/img/logo-light.png" alt=""></a>
				</s:div>				
				<a href="#" data-toggle="modal" data-target="#login-form" class="modal-form">
					<i class="fa fa-user"></i>
				</a>														
			</s:div>
		</nav>
		</header>  
		  
		<s:div cssClass="container">		
			<s:form action="publish" method="POST">	
				<s:div cssClass="head-text">				
					<h1>lyc</h1>
				</s:div>			
	      	<s:div>		 	            
		       <s:hidden name="userName" value="lyc"/>	       
	           <p>文章标题</p>                     
	           <s:textfield  cssClass="form-control" name="title" size="8"/>
	           <s:fielderror cssStyle="color:red;font-size:8pt;list-style-type:none;margin-left:0;"> 
	    		  <s:param>title</s:param>    
	   		   </s:fielderror>
	        </s:div>   
	        <s:div>                          
	          	<p>文章内容</p>             
	          	<s:textarea rows="20" cols="120" name="content" cssClass="form-control"></s:textarea>  
	          	<s:fielderror cssStyle="color:red;font-size:8pt;list-style-type:none;"> 
	    		  <s:param>content</s:param>    
	   		   </s:fielderror>                        
	      	</s:div>
	      	<s:div>
		       	<p>分类</p>
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
  </body>
</html>
 
