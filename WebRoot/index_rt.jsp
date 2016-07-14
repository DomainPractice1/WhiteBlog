<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- <html lang="en"> -->
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="assets/img/favicon.ico">
<title>White Blog</title>

<!-- Bootstrap core CSS -->
<link href="assets/css/bootstrap.mi
n.css" rel="stylesheet">
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
<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">



</head>
<body onload="timedCount()">
	<s:action name="ShowBlogList"/>
	<s:action name="preparationAction" />
	<div class="page-loader">
		<div class="loader-in">Loading...</div>
		<div class="loader-out">Loading...</div>
	</div>
	
	<!-- 文章列表 -->
	<aside class="navmenu-quan">
		<div class="post-titles">
			<div class="tag-title">
				<div class="container">
					<p class="tags" id="post-titles">
						<a class="Edit_qp selected" href="#">删除文章</a>
					</p>
				</div>
			</div>
			<button type="button" class="remove-navbar"><i class="fa fa-times"></i></button>
			<ul class="post-title-list clearfix view-blog" id="delete_vision">
			
			</ul>
		</div>
	</aside>
	
	<aside class="navmenu">
		<div class="post-titles">
			<div class="tag-title">
				<div class="container">
					<p class="tags" id="post-titles">
						<a class="tags" href="publish.jsp"><img src="assets/img/write.png"/></a>
					</p>
				</div>
			</div>
			<button type="button" class="remove-navbar"><i class="fa fa-times"></i></button>
			<ul id="slideform" class="post-title-list clearfix">
			</ul>
		</div>
	</aside>
	
	<div class="canvas">
		<div class="canvas-overlay"></div>
		<header>
			<nav class="navbar navbar-fixed-top nav-down navbar-laread">
				<div class="container">
					<div class="navbar-header">
						<a class="navbar-brand" href="./index_rt.html"><img height="64" src="assets/img/logo-light.png" alt=""></a> 
					</div>							
					<c:choose>
						<c:when test="${sessionScope.loginUser == null}">
							<a href="#" data-toggle="modal" data-target="#login-form" class="modal-form">
								<i class="fa fa-user"></i>
							</a>									
						</c:when>
						<c:otherwise>
							<div class="get-post-titles" style="margin-left:20px">
								<button  type="button" class="close_qp navbar-toggle push-navbar-full" data-navbar-type="article">
									<i class="fa fa-bars"></i>
								</button>
							</div>		
							<div class="get-post-titles">					
								<button id="notice" type="button" class="navbar-toggle push-navbar" data-navbar-type="default">
									<i id="checkicon" class="fa fa-bell-o"></i>
								</button>						
							</div>
							<div class="get-post-titles" style="margin-right:10px">					
								<button type="button" class="navbar-toggle push-navbar-undo" data-navbar-type="default" onclick="location.href='showMailList.php'">
									<i class="fa fa-envelope"></i>
								</button>						
							</div>
							<a class="modal-form" style="margin-right:10px">${sessionScope.loginUser.username}</a>
								<a href="#" data-toggle="modal" data-target="#logout-form" class="modal-form">
								<i class="fa fa-power-off"></i>
							</a>
						</c:otherwise>
					</c:choose>
					<button type="button" class="navbar-toggle collapsed menu-collapse" data-toggle="collapse" data-target="#main-nav">
						<span class="sr-only">Toggle navigation</span>
						<i class="fa fa-plus"></i>
					</button>
				</div>
			</nav>
		</header>

		<div class="container">
			<div class="head-text">
				<h1>White Blog</h1>
				<p class="lead-text">Blog. Designed for Read.</p>
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<div class="post-fluid post-medium-vertical">
					<s:iterator value = "#session.blogList" var = "blog">
						<div class="container-fluid post-default">
							<div class="container-medium">
								<div class="row post-items">
									<div class="col-md-12">
										<div class="post-item">
											<div class="post-item-paragraph"> 
												<h3><a href="content-strBlogId-${blog.blogId}.html">${blog.title}</a></h3>
												<p>${blog.content}<a href="#" class="more">[...]</a></p>
											</div>
											<div class="post-item-info clearfix">
												<div class="pull-left">
													<span>${blog.time}</span> By <a href="#">${blog.username}</a>
												</div>
												<div class="pull-right post-item-social">
												<!-- 游客是不能修改、转发、点赞和分享的 -->
												<c:choose>													
														<c:when test="${sessionScope.loginUser != null}">
														<c:choose>
															<c:when test="${sessionScope.loginUser.username==blog.username}">
																<%-- <a href="showBlogToModify.action?blogId=${blog.blogId}"><div class="modify" title="编辑博客"></div></a> --%>
																<a href="showBlogToModify-strBlogId-${blog.blogId}.html"><div class="modify" title="编辑博客"></div></a>
															</c:when>
															<c:otherwise>
																<label style="display:none;">${blog.blogId}</label>
																<label style="display:none;">${blog.title}</label>
																<a href="javascript:void(0)" onclick="update(this);"><div class="forward" title="转发到自己的博客"></div></a>
															</c:otherwise>													
														</c:choose>																									
														<a href="#" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="<a href='#' id='facebook${blog.blogId}' onclick='shareFacebook(this)'><i class='fa fa-facebook'></i></a><a href='#' id='twitter${blog.blogId}' onclick='shareTwitter(this)'><i class='fa fa-twitter'></i></a>" class="pis-share"><i class="fa fa-share-alt"></i></a>
														<c:if test="${likeitList.isLike=='1'}">
																<a href="#" id="like${blog.blogId}" class="post-liked" onclick="myF(this)"><i  class="fa fa-heart" title="点赞"></i><span>${blog.likenumber}</span></a>
														</c:if>
														<c:if test="${likeitLike.isLike!='1' }">
																<a href="#" id="like${blog.blogId}" class="post-like" onclick="myF(this)"><i  class="fa fa-heart" ></i><span>${blog.likenumber}</span></a>
														</c:if> 
														</c:when>														
												</c:choose>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</s:iterator>						


						<div class="container-fluid post-video">
							
						</div><div class="container-fluid post-sound">
							
						</div>
					</div>
				</div>
				<aside class="col-md-4">

					<div class="laread-right">

						<form action="searchArticle.php" class="laread-form search-form">
							<div class="input"><input type="text" class="form-control" placeholder="Search..." name="searchText"></div>
							<button type="submit" class="btn btn-link"><i class="fa fa-search"></i></button>
						</form>

						<ul class="laread-list">							
							<li class="title">热门文章</li>
 							<s:iterator value="#session.topblog" var="blog">
 								<li>
 								<a href="content-strBlogId-${blog.blogId}.html">${blog.title}</a>
 								<i class="line"></i>
 								</li>
 							</s:iterator>				
						</ul>

						<ul class="laread-list">
							<li class="title">热门用户</li>
							<s:iterator value="#session.topuser" var="user">
 								<li>
 								<a href="#">${user.username}</a>
 								<i class="line"></i>
 								</li>
 							</s:iterator>
						</ul>

						<ul class="laread-list">
							<li class="title">Super-TAGS</li>
							<li class="bar-tags">
								<s:iterator value="#session.allSuperTags" var="stag">
									<a href="findBlogByTagSuperAction-strBlogId-<s:property value="#stag.supertypeId" />.html"><s:property value="#stag.supertypeName"/></a>
								</s:iterator>
							</li>
						</ul>
						<ul class="laread-list">
							<li class="title">Sub-TAGS</li>
							<li class="bar-tags"> 
								<s:iterator value="#session.allTags" var="tag">
									<a href="findBlogByTagAction-strBlogId-<s:property value="#tag.typeId" />.html"><s:property value="#tag.typename" /></a>
								</s:iterator>
							</li>
						</ul>
					</div>

				</aside>
			</div>
		</div>

		<footer class="container-fluid footer">
			<div class="container text-center">
				<div class="footer-logo"><img src="assets/img/logo-black.png" alt=""></div>
				<p class="laread-motto">Designed for Read.</p>
 				<div class="laread-social">
					<a href="#" class="fa fa-twitter"></a>
					<a href="#" class="fa fa-facebook"></a>
					<a href="#" class="fa fa-pinterest"></a>
				</div>
			</div>
		</footer>
	</div>

	

	<!-- Login Modal -->
	<div class="modal leread-modal fade" id="login-form" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="login-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><i class="fa fa-unlock-alt"></i>LaRead Sign In</h4>
				</div>
				<div class="modal-body">
					<form action="login.php" method="post">
						<s:token></s:token>
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Username" name="userform.username">
						</div>
						<div class="form-group">
							<input type="password" class="form-control" placeholder="Password" name="userform.password">
						</div>
						<div class="linkbox">
							<a href="#">Forgot password ?</a>
							<span>No account ? <a href="#" id="register-btn" data-toggle="modal" data-target="#register-form">Sign Up.</a></span>
							<!-- <span class="form-warning"><i class="fa fa-exclamation"></i>Fill the require.</span> -->
						</div>
						<div class="linkbox">
							<label><input type="checkbox"><span>Remember me</span><i class="fa"></i></label>
							<button type="submit" class="btn btn-golden btn-signin">SIGN IN</button>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<div class="provider">
						<span>Sign In With</span>
						<a href="#"><i class="fa fa-facebook"></i></a>
						<a href="#"><i class="fa fa-twitter"></i></a>
					</div>
				</div>
			</div>
			<div class="modal-content" id="register-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><i class="fa fa-lock"></i>LaRead Sign Up</h4>
				</div>
				<div class="modal-body">
					<form action="register.php" method="post">
						<!-- <div class="form-group">
							<input class="form-control" placeholder="Name">
						</div> -->
						<div class="form-group">
							<input class="form-control" placeholder="Username" name="userform.username">
						</div>
						<!-- <div class="form-group">
							<input class="form-control" placeholder="Email">
						</div> -->
						<div class="form-group">
							<input class="form-control" type="password" placeholder="Password" name="userform.password">
						</div>
						<div class="linkbox">
							<span>Already got account? <a href="#" id="login-btn" data-target="#login-form">Sign In.</a></span>
						</div>
						<div class="linkbox">
							<label><input type="checkbox"><span>Remember me</span><i class="fa"></i></label>
							<button type="submit" class="btn btn-golden btn-signin">SIGN UP</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- logout -->
	<div class="modal leread-modal fade" id="logout-form" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="login-content">
				<div class="modal-body">
					<!-- <form action="logout.action" method="post">		 -->
					<form action="logout.php" method="post">				
						<div class="modal-body">
							确认登出当前账户么？
         				</div>
						<div class="modal-footer">
            				<button type="button" class="btn btn-default" data-dismiss="modal">关闭 </button>
            				<button type="submit" class="btn btn-primary">确定</button>
        				</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	
	<!-- forward-form -->
	<div class="modal leread-modal fade" id="forward-form" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="login-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><i class="fa fa-unlock-alt"></i>转发到我的博客</h4>
				</div>
				<div class="modal-body">
					<form action="forward.php" method="post">
					<label style="margin-bottom:5px;">我的博客标题</label>						
						<div class="form-group">								
								<div style="display:inline">[转发]</div>
								<div>
								<input id="blog_id" type="hidden" name="blog.blogId"/>
								<input id="blog_title" type="text" class="form-control" style="display:inline" name="blog.title" /></div>																					
						</div>																
						<div class="linkbox" style="float:right;padding-bottom:10px;padding-top:6px;">							
							<button type="submit" class="btn btn-golden btn-signin">确认</button>
						</div>
					</form>
				</div>				
			</div>			
		</div>
	</div>
	
	<!-- Bootstrap core JavaScript
	================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jasny-bootstrap.min.js"></script>
	<script src="assets/js/prettify.js"></script>
	<script src="assets/js/lang-css.js"></script>
	<script src="assets/js/jquery.blueimp-gallery.min.js"></script>
	<script src="assets/js/imagesloaded.js"></script>
	<script src="assets/js/masonry.js"></script>
	<script src="assets/js/viewportchecker.js"></script>
	<script src="assets/js/jquery.dotdotdot.min.js"></script>
	<script src="assets/js/jquery.colorbox-min.js"></script>
	<script src="assets/js/jquery.nicescroll.min.js"></script>
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<script src="assets/js/jquery.ellipsis.min.js"></script>
	<script src="assets/js/calendar.js"></script>
	<script src="assets/js/jquery.touchSwipe.min.js"></script>
	<script src="assets/js/script.js"></script>
	
	<script type="text/javascript">
		function update(obj){
			var labels=$(obj).parent().find('label');
			$('#blog_id').val(labels.eq(0).text());
			$('#blog_title').val(labels.eq(1).text());
			$('#forward-form').modal('show');
		}
	</script>

	<script type="text/javascript">
		var strId = "";
		function shareTwitter(t)
		{
			strId = t.id.substring(7, t.id.length);		
			window.open('https://twitter.com/intent/tweet?text=I\'m here at whiteblog http://localhost:8080/whiteBlog/content.php-id=' + strId,"_blank","width=500px;height=500px;");
		}
	</script>
	<script type="text/javascript">
		var strId = "";
		var tmp = "";
		function myF(t)
		{
			tmp = t.id;
			strId = t.id.substring(4, t.id.length);
			var actionName = "clickLike-id-";
			actionName += strId+".html";
			$.ajax({
				url:actionName,
				type:"POST",
				dataType:"json",
				success:function(data){
					var lc = document.getElementById(tmp).lastChild;
					var num = parseInt(lc.innerHTML); 
					if(data == "success"){
						lc.innerHTML = num + 1;
						alert("点赞成功");
					}	
					else{
						lc.innerHTML = num - 1;
						alert("取消点赞");
					}
				}
			});
		}
	</script>
	<script type="text/javascript">		
		$("#notice").click(function(){
			$("#slideform").empty();
			$.ajax({
				url:"notice.php",
				type:"POST",
				dataType:"json",
				success:function(data){
				$.each(data,function(i,list){
							var _tr;
							if(list.blogId == 0){
								_tr = '<li class="pt-culture pt-art"><div><h5><i>' + list.noticeId + '</i><a href="showMailList.action">' + list.content + '</a>' +
								'</h5><div class="post-subinfo"></div></div></li>'	
							}else{
								_tr = '<li class="pt-culture pt-art"><div><h5><i>' + list.noticeId + '</i><a href="content.action?id=' + list.blogId + '">' + list.content + '</a>' +
								'</h5><div class="post-subinfo"></div></div></li>'
							}  
                       		
                       		 $("#slideform").append(_tr);
                    })
				}
			})	
		});
	</script>
	
	
	
	<script type="text/javascript">
		var t
		function timedCount()
		{
			$.ajax({
				url:"checkNotice.php",
				type:"POST",
				datatype:"json",
				success:function(data){
					if(data == "new"){
						$("#checkicon").attr("class","fa fa-bell fa-spin");
					}else{
						$("#checkicon").attr("class","fa fa-bell-o")
					}
				}
			})
			t=setTimeout("timedCount()",10000)
		}
	</script>

	<script type ="text/javascript">
	function delete_row(delete_id){
		if(confirm("确定要删除？")){
			$.ajax({
				url:"deleteBlog-id-"+delete_id+".html",
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data == -1){
						$("li").remove("#blog-"+delete_id);
						//$(delete_id).parent().parent().parent().remove();
						window.alert("删除成功");
					}else{
						window.alert("删除失败");
					}
				}
			})
			//$("li").remove("#"+delete_id);
		}
	}
	</script>
	<script type ="text/javascript">
		$(".Edit_qp").click(function(){
			$("#delete_vision").empty();
			$("#delete_vision").toggleClass("delete-blog");
			$("#delete_vision").toggleClass("view-blog");
			var classes = $("#delete_vision").attr("class");
			var actionStr = "#";
			var delete_icon = "";
			var onclick_str = "";
			if(classes.indexOf("view-blog") >= 0) {
				delete_icon = "delete_icon fa fa-file-text-o";
			}else{
				delete_icon = "delete_icon fa fa-times";
	// 			onclick_str = "onclick=\"delete_row(this)\"";
			}
			$.ajax({
				url:"changeDeleteList.php",
				type:"POST",
				dataType:"json",
				success:function(data){
				$.each(data, function(i, list){
					var color_str = ""
					if(classes.indexOf("view-blog") >= 0) {
						$(".Edit_qp").html("删除文章");
						actionStr = "content-strBlogId-"+list.blogId+".html";
						color_str="color:#ffffff";
					}else {
						$(".Edit_qp").html("返回");
						onclick_str="onclick=\"delete_row("+list.blogId+")\"";
						color_str="color:#FF4500";
					}
					var _tr = '<li class="pt-fashion pt-culture" id="blog-'+list.blogId+'"><div class="container"><h5><i class="'+delete_icon+'" style='+color_str+'></i>'+
					'<a class="delete_qp" href="'+actionStr+'"'+onclick_str+'>'+list.title+'</a></h5><div class="post-subinfo">'+
					'<span>'+list.time+'</span>   •   <span>2 Comments</span></div></div></li>';
					$("#delete_vision").append(_tr);				
				})
				}
			})
			var canvasHeight = $('.canvas').outerHeight();
			$('.navmenu-quan').height(canvasHeight);
			$('.post-title-list > li > div').toggleClass('container');
		})
	</script>
</body>
</html>
