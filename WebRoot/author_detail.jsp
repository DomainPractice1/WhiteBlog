<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="assets/img/favicon.ico">
<title>LaRead - Author Detail</title>
<!-- Bootstrap core CSS -->
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
<link href='assets/css/Raleway.css' rel='stylesheet' type='text/css'>
<link href='assets/css/googleapis.css' rel='stylesheet' type='text/css'>
<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="assets/js/shiv.js"></script>
<script src="assets/js/respond.js"></script>
<![endif]-->
</head>
<body>
	<aside class="navmenu">
	<div class="post-titles">
		<div class="tag-title">
			<div class="container">
				<p class="tags" id="post-titles">
					<a data-filter=".pt-fashion" href="#" id="att">我关注的</a> <a
						data-filter=".pt-culture" href="#" id="fan">关注我的</a>
				</p>
			</div>
		</div>
		<button type="button" class="remove-navbar">
			<i class="fa fa-times"></i>
		</button>
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
								<button type="button" class="navbar-toggle push-navbar" data-navbar-type="default">
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
							<a class="modal-form" style="margin-right:10px"
						href="showcreat-Username-${sessionScope.loginUser.username}.html">${sessionScope.loginUser.username}</a>
								<a href="#" data-toggle="modal" data-target="#logout-form" class="modal-form">
								<i class="fa fa-power-off"></i>
							</a>
						</c:otherwise>
					</c:choose>
					<button type="button" class="navbar-toggle collapsed menu-collapse" data-toggle="collapse" data-target="#main-nav">
						<span class="sr-only">Toggle navigation</span>
						<i class="fa fa-plus"></i>
					</button>
					<div class="collapse navbar-collapse" id="main-nav">
						<ul class="nav navbar-nav">
							<li><a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">我的主页</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</header>

		<section class="post-fluid">
		<div class="container-fluid">
			<div class="row laread-author-detail">
				<div class="author-picture" style="width:216px;height:220px;">
					<img src="assets/img/img-49.jpg" alt="" />
				</div>
				<div class="author-subdetail">
					<h2>Hi，${sessionScope.loginUser.username}</h2>
					<p class="info-small">职业：${job} | 性别：${sex}</p>
					<p class="info-small">已关注的：${attentionAcount} |
						被关注的：${fansAcount}</p>
					<p class="author-bio">
					<ul class="laread-list">
						<li class="bar-tags">
							<p style="color:grey;font-size:16px;">我的爱好：</p>						
							 <s:iterator
								value="#session.supertype" var="tag">
								<a
									href="findBlogByTagAction-id-<s:property value="#tag.supertypeId" />.html"><s:property
										value="#tag.supertypeName" /></a>
							</s:iterator>							 
						</li>
					</ul>
					</p>
					<p class="info-small">
						<span><i class="fa fa-map-marker"></i>位置：${country}
							${province} ${city}</span>																
						<span><i class="fa fa-paper-plane"></i> 原创数：
							${originalcount} </span> <span><i class="fa fa-twitter"></i>转发数：
							${forwardcount}</span>
					</p>
                  
					<a tabindex="0"   data-toggle="modal"  data-target="#modifyInformation"><button type="button"
						class="btn btn-golden btn-golden-hover btn-rounded" >修改信息</button></a>
				</div>
			</div>

			<div class="row author-article-list">
				<div class="article-list-box">
					<div class="article-type clearfix" role="tablist">
						<ul>
							<li role="presentation" class="active"><a href="#lastest"
								id="lastest-tab" role="tab" data-toggle="tab"
								aria-controls="lastest" aria-expanded="true">我的收藏</a></li>
							<li role="presentation"><a href="#popular" role="tab"
								id="popular-tab" data-toggle="tab" aria-controls="popular">相关推荐</a>
							</li>
						</ul>
					</div>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="lastest"
							aria-labelledBy="lastest-tab">
							<ul class="article-list">
							<s:iterator value="#session.collection" var="collectionBlog">
									<li>
										<div class="media clearfix">
											<div class="media-right">
												<a href="deleteCollect-blogid-${collectionBlog.blogId}.html" class="article-number hidden-xs">delete</a>
											</div>
											<div class="media-body">
												<h4 class="media-heading">
													<a href="content-strBlogId-${collectionBlog.blogId}.html">${collectionBlog.title}</a>
												</h4>
												<p>${collectionBlog.content}<a href="#" class="more">[...]</a>
												</p>
												<div class="article-info">
													<span class="visible-xs-inline">${collectionBlog.time}</span><a>发布时间
														${collectionBlog.time}</a>  •  <a
														href="ShowCommentList-id-${collectionBlog.blogId}.html">评论(${collectionBlog.commentnumber})</a>  •  <a>浏览次数
														${collectionBlog.viewnumber}</a>
												</div>
											</div>
										</div>
									</li>
								</s:iterator>	
							</ul>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="popular"
							aria-labelledBy="popular-tab">
							<ul class="article-list">
							<s:iterator value="#session.related" var="relatedBlog">
									<li>
										<div class="media clearfix">
											<div class="media-right">
												<a href="#" class="article-number hidden-xs">L</a>
											</div>
											<div class="media-body">											
												<h4 class="media-heading">												
													<a href="content-strBlogId-${relatedBlog.blogId}.html">${relatedBlog.title}</a>
												</h4>
												<p>${relatedBlog.content}<a href="#" class="more">[...]</a>
												</p>
												<div class="article-info">
													<span class="visible-xs-inline">${relatedBlog.time}</span><a>发布时间
														${relatedBlog.time}</a>  •  <a
														href="ShowCommentList-id-${relatedBlog.blogId}">评论(${relatedBlog.commentnumber})</a>  •  <a>浏览次数
														${relatedBlog.viewnumber}</a>
												</div>												 
											</div>
										</div>
									</li>
								</s:iterator>
								
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		</section>

	<!-- modifyInformation Modal -->
	<div class="modal   fade" id="modifyInformation" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content" id="login-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title"><i class="fa fa-unlock-alt"></i>修改个人信息</h4>
				</div>
				<div class="modal-header">
					<form action="modifyInformation.php" method="post">
						<div class="form-group">
							<input class="form-control" onchange="checkPassword()" placeholder="Username" name="username" value="${sessionScope.loginUser.username }">
						</div>
						<div class="form-group">
							<input id="p1" class="form-control" onchange="checkPassword()" type="password" placeholder="Password" name="password" value="${sessioinScope.loginUser.password }">
						</div> 
						<div class="form-group">
							<input id="p2" class="form-control" onchange="checkPassword()" type="password" placeholder="Password Again" name="${sessioinScope.loginUser.password }" value="${sessioinScope.loginUser.password}">
						</div>
						<div class="form-group">
							<input class="form-control" placeholder="Job Name" name="job" value="${job}"> 
						</div>
						<div class="form-group">
							<select class="form-control " name="provinceId"  onchange="changeCity(this)">
								<c:forEach var="province" items="${pl}">
									<option class="form-control" value="${province.provinceId}" >${province.provinceName}</option>		
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<select id="cityList" class="form-control" name="cityId">
								<%-- <c:forEach var="city" items="${cl }">
									<option class="form-control" value="${city.cityId}">${cityname}</option>
								</c:forEach> --%>
							</select>
						</div>
						<div class="form-group">
							<select class="form-control" name="hobbyId">
								<c:forEach var="hobby" items="${hl}">
									<option class="form-control" value="${hobby.supertypeId}">${hobby.supertypeName}</option>
								</c:forEach>
							</select>
						</div>
						<div id="warn" style="visibility:hidden;">
							<font color="red">密码输入不一致</font>
						</div>
						<div class="linkbox" id="submit">
							<button type="submit"  class="btn btn-golden btn-signin">确认修改</button>
						</div>
					</form>
				</div> 
			</div> 
		</div>
	</div>
<!-- modifyInformation modal finish-->

		<!-- /.container -->

		<footer class="container-fluid footer">
		<div class="container text-center">
			<div class="footer-logo">
				<img src="assets/img/logo-black.png" alt="">
			</div>
			<p class="laread-motto">Designed for Read.</p>
			<div class="laread-social">
				<a href="#" class="fa fa-twitter"></a> <a href="#"
					class="fa fa-facebook"></a> <a href="#" class="fa fa-pinterest"></a>
			</div>
		</div>
		</footer>
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
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<script src="assets/js/jquery.ellipsis.min.js"></script>
	<script src="assets/js/jquery.dotdotdot.min.js"></script>
	<script src="assets/js/jquery.colorbox-min.js"></script>
	<script src="assets/js/jquery.nicescroll.min.js"></script>
	<script src="assets/js/masonry.js"></script>
	<script src="assets/js/viewportchecker.js"></script>
	<script src="assets/js/calendar.js"></script>
	<script src="assets/js/jquery.touchSwipe.min.js"></script>
	<script src="assets/js/script.js"></script>	
	
	<script type="text/javascript">
		function checkPassword(){
			
			var p1 = document.getElementById("p1");
			var p2 = document.getElementById("p2");
			if(p1.value != p2.value){
				document.getElementById("submit").style.visibility = 'hidden';
				document.getElementById("warn").style.visibility = 'visible';
			} 
			else {
				document.getElementById("submit").style.visibility = 'visible';
				document.getElementById("warn").style.visibility = 'hidden';
			} 
		}
	</script>
	
	<script type="text/javascript">
		$.ajax({
			url:"loadCity.action?i=1",
			type:"POST",
			dataType:"json",
			success:function(data){
				$.each(data,function(i, list){
				var tr;
				tr = '<option class="form-control" value="' + list.cityId + ' ">' + list.cityname + '</option>';
				$("#cityList").append(tr);
			})
			}
		});
	</script>
	<script type="text/javascript">
		function changeCity(t){
			$("#cityList").empty(); 
			$.ajax({
				url: "loadCity.action?i=" + t.value,
				method:"POST",
				dataType:"json",
				success:function(data){ 
						$.each(data,function(i, list){
						var tr;
						tr = '<option class="form-control" value="' + list.cityId + '">' + list.cityname + '</option>';
						$("#cityList").append(tr);
					});
				}
			});
		}
	</script>
	<script type="text/javacript">
		 
	</script>
	<script>
  		$(function () { $('#modifyInformation').modal('hide')});
  		$("#myModal").modal().css({
        "margin-top": function () {
        	return - ($(this).height() / 2);}
        });
  		$(function() {$('#myModal2').modal('hide')});
	</script>
	<script> 	
	function delete_row(delete_id){
		if(confirm("确定要取消关注？")){
			$.ajax({
				url:"deleteAttentionAction-userID-" + delete_id + ".html",
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data == -1){
						$("li").remove("#user-"+delete_id);
						window.alert("取消关注成功");
					}else{
						window.alert("取消关注失败");
					}
				}
			})
		}
	}
	</script>
	<script type="text/javascript">		
		$("#att").click(function(){
			$("#slideform").empty();
			$.ajax({
				url:"getaf.php",
				type:"POST",
				dataType:"json",
				success:function(data){
				$.each(data.attentionlist,function(i,list){
							onclick_str="onclick=\"delete_row("+list.userId+")\"";
							var _tr='<li class="pt-fashion" id="user-'+list.userId+'"><div><h5><i class="fa fa-file-text-o"></i> <a href="showUserdetailAction-attentionUserid-'+ list.userId +'.html">'+ list.username +'</a><a href="#"'+ onclick_str +'>&nbsp &nbsp &nbsp &nbsp取消关注</a></h5></div></li>';							                  		
                       		 $("#slideform").append(_tr);
                    })
				}
			})	
		});
	</script>
	<script type="text/javascript">		
		$("#fan").click(function(){
			$("#slideform").empty();
			$.ajax({
				url:"getaf.php",
				type:"POST",
				dataType:"json",
				success:function(data){
                $.each(data.fanslist,function(i,list){
							var _tr='<li class="pt-culture" id="user-'+list.userId+'"><div><h5><i class="fa fa-file-text-o"></i> <a href="showUserdetailAction-attentionUserid-'+ list.userId +'.html">'+ list.username +'</a></h5></div></li>';							                  		
                       		 $("#slideform").append(_tr);
                    })
				}
			})	
		});
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
</body>
</html>
