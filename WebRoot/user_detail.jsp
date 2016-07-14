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
					<a data-filter=".pt-fashion" href="#">他关注的</a> <a
						data-filter=".pt-culture" href="#">关注他的</a>
				</p>
			</div>
		</div>
		<button type="button" class="remove-navbar">
			<i class="fa fa-times"></i>
		</button>
		<ul class="post-title-list clearfix">
			<s:iterator value="#session.attention" var="attentionTag">
				<li class="pt-fashion">
					<div>
						<h5>
							<i class="fa fa-file-text-o"></i> <a
								href="showUserdetailAction-attentionUserid-${attentionTag.userId}.html">${attentionTag.username}</a>
						</h5>
					</div>
				</li>
			</s:iterator>
			<s:iterator value="#session.fans" var="fansTag">
				<li class="pt-culture">
					<div>
						<h5>
							<i class="fa fa-file-text-o"></i> <a
								href="showUserdetailAction-attentionUserid-${fansTag.userId}.html">
								${fansTag.username}</a>
						</h5>
					</div>
				</li>
			</s:iterator>
		</ul>
	</div>
	</aside>

	<div class="canvas">
		<div class="canvas-overlay"></div>
		<header> <nav
			class="navbar navbar-fixed-top nav-down navbar-laread">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="index_rt.jsp"><img height="64"
					src="assets/img/logo-light.png" alt=""></a>
			</div>
			<div class="get-post-titles">
				<button type="button" class="navbar-toggle push-navbar"
					data-navbar-type="default">
					<i class="fa fa-bars"></i>
				</button>
			</div>
			<button type="button" class="navbar-toggle collapsed menu-collapse"
				data-toggle="collapse" data-target="#main-nav">
				<span class="sr-only">Toggle navigation</span> <i class="fa fa-plus"></i>
			</button>
			<div class="collapse navbar-collapse" id="main-nav">
				<ul class="nav navbar-nav">
					<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"
						role="button" aria-expanded="false">${userName}的主页</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
		</nav> </header>

		<section class="post-fluid">
		<div class="container-fluid">
			<div class="row laread-author-detail">
				<div class="author-picture">
					<img src="assets/img/img-49.jpg" alt="" />
				</div>
				<div class="author-subdetail">
					
						<c:choose>
						    <c:when test="${ifAttention==0}">
							<h2>${userName}<a href="payAttentionAction-AttentionID-${userID}.html">（我要关注）
											</a></h2>
							</c:when>
							<c:otherwise>
							   <h2>${userName}<a href="deleteAttentionAction-userID-${userID}.html">（取消关注）
											</a></h2>
							</c:otherwise>
							</c:choose>

					<p class="info-small">职业：${job} | 性别：${sex}</p>
					<p class="info-small">已关注的：${attentionAcount} |
						被关注的：${fansAcount}</p>
					<p class="author-bio">
					<ul class="laread-list">
						<li class="bar-tags">
							<p style="color:grey;font-size:16px;">他的爱好：</p> <s:iterator
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
						<s:action name="showcreat" executeResult="false"></s:action>
						<s:action name="showforward" executeResult="false"></s:action>
						<span><i class="fa fa-paper-plane"></i> 原创数：
							${originalcount} </span> <span><i class="fa fa-twitter"></i>转发数：
							${forwardcount}</span>
					</p>
				</div>
			</div>

			<div class="row author-article-list">
				<div class="article-list-box">
					<div class="article-type clearfix" role="tablist">
						<ul>
							<li role="presentation" class="active"><a href="#lastest"
								id="lastest-tab" role="tab" data-toggle="tab"
								aria-controls="lastest" aria-expanded="true">他的文章</a></li>
						</ul>
					</div>
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="lastest"
							aria-labelledBy="lastest-tab">
							<ul class="article-list">
								<s:iterator value="#session.myblog" var="myblog">
									<li>
										<div class="media clearfix">
											<div class="media-right">
												<a href="#" class="article-number hidden-xs">L</a>
											</div>
											<div class="media-body">
												<h4 class="media-heading">
													<!-- <a href="#">${myblog.title}</a>-->
													<a href="content-id-${myblog.blogId}.html">${myblog.title}</a>
												</h4>
												<p>${myblog.title}<a href="#" class="more">[...]</a>
												</p>
												<div class="article-info">
													<span class="visible-xs-inline">${myblog.time}</span><a>发布时间
														${myblog.time}</a>  •  <a
														href="ShowCommentList-id-${myblog.blogId}.html">评论(${myblog.commentnumber})</a>  •  <a>浏览次数
														${myblog.viewnumber}</a>
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
</body>
</html>
