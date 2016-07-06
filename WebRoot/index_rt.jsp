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
						<a class="navbar-brand" href="index_rt.jsp"><img height="64" src="assets/img/logo-light.png" alt=""></a>
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
								<button type="button" class="navbar-toggle push-navbar-undo" data-navbar-type="default" onclick="location.href='showMailList.action'">
									<i class="fa fa-envelope"></i>
								</button>						
							</div>
							<a class="modal-form" style="margin-right:10px">${sessionScope.loginUser.username}</a>
							
								<!-- <div class="get-post-titles" style="margin-right:10px">					
								<button type="button" class="navbar-toggle push-navbar" data-navbar-type="default">
									<i class="fa fa-power-off"></i>
								</button>						
								</div> -->
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
												<div>
													<a href="#" class="quick-read qr-only-phone"><i class="fa fa-eye"></i></a>
												</div>
												<h3><a href="content.action?id=${blog.blogId}">${blog.title}</a></h3>
												<p>${blog.content}<a href="#" class="more">[...]</a></p>
											</div>
											<div class="post-item-info clearfix">
												<div class="pull-left">
													<span>${blog.time}</span> By <a href="#">${blog.username}</a>
												</div>
												<div class="pull-right post-item-social">
													<c:choose>
														<c:when test="${sessionScope.loginUser!=null && sessionScope.loginUser.username==blog.username}">
															<a href="showBlogToModify.action?blogId=${blog.blogId}"><div class="modify"></div></a>
														</c:when>
													</c:choose>
													<!-- 游客是不能点赞和分享的 -->	
													<c:choose>	
														<c:when test="${sessionScope.loginUser != null}">
															<a href="#" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="<a href='#' id='facebook${blog.blogId}' onclick='shareFacebook(this)'><i class='fa fa-facebook'></i></a><a href='#' id='twitter${blog.blogId}' onclick='shareTwitter(this)'><i class='fa fa-twitter'></i></a>" class="pis-share"><i class="fa fa-share-alt"></i></a>
															<a href="#" id="like${blog.blogId}" class="post-like" onclick="myF(this)"><i  class="fa fa-heart" ></i><span>${blog.likenumber}</span></a>
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

						<form action="searchArticle.action" class="laread-form search-form">
							<div class="input"><input type="text" class="form-control" placeholder="Search..." name="searchText"></div>
							<button type="submit" class="btn btn-link"><i class="fa fa-search"></i></button>
						</form>

						<ul class="laread-list">
							<li class="title">CATEGORY</li>
							<li><a href="#">Branding</a><i class="line"></i></li>
							<li><a href="#">Design (48)</a><i class="line"></i></li>
							<li><a href="#">Photography</a><i class="line"></i></li>
							<li><a href="#">Inspiration</a><i class="line"></i></li>
							<li><a href="#">Life</a><i class="line"></i></li>
							<li><a href="#">City</a><i class="line"></i></li>
						</ul>

						<ul class="laread-list">
							<li class="title">RECENT POSTS</li>
							<li><a href="#">The Nature of My Inspiration</a><i class="date">28 June</i></li>
							<li><a href="#">Sam Feldt - Show Me Love</a><i class="date">27 June</i></li>
							<li><a href="#">Do You Love Coffee?</a><i class="date">25 June</i></li>
							<li><a href="#">The Game Before The Game</a><i class="date">23 June</i></li>
							<li><a href="#">Long Live The Kings</a><i class="date">22 June</i></li>
						</ul>

						<ul class="laread-list">
							<li class="title">All TAGS</li>
							<li class="bar-tags">
								<s:iterator value="#session.allTags" var="tag">
									<a href="findBlogByTagAction.action?id=<s:property value="#tag.typeId" />"><s:property value="#tag.typename" /></a>
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
				
			</div>
		</footer>
	</div>

	<div id="quick-read" class="qr-white-theme">
		<div class="quick-read-head">
			<div class="container">
				<a href="#" class="qr-logo"></a>
				<div class="qr-tops">
					<a href="#" class="qr-search-close"><i class="fa fa-times"></i></a>
					<a href="#" class="qr-search"><i class="fa fa-search"></i></a>
					<a href="#" class="qr-change"><i class="fa fa-adjust"></i></a>
					<a href="#" class="qr-close"><i class="fa fa-times"></i></a>
				</div>
				<form class="qr-search-form">
					<input type="text" placeholder="Search LaRead">
				</form>
			</div>
		</div>
		<div class="quick-dialog">
			<div class="quick-body">
				<div class="container">
					<div class="col-md-8 col-md-offset-2">
						<div class="qr-content post-item-paragraph">

							<article>
								<h2>A Nice Street Cafe in London</h2>

								<p>Consectetur adipiscing elit. Vivamus nec mauris pulvinar leo dignissim sollicitudin eleifend eget velit. Nunc sed dolor enim, vitae sodales diam. Mauris fermentum fringilla lorem, in rutrum massa sodales et. Praesent mollis sodales est, eget fringilla libero sagittis eget. Nunc gravida varius risus ac luctus. Mauris ornare eros sed libero euismod ornare. Nulla id sem a mauris egestas pulvinar vitae non dui. Cras odio tortor, feugiat nec sagittis sed, laoreet ut mauris. In hac habitasse platea dictumst.</p>

								<p>What if instead your website used machine learning to build itself, and then rebuilt as necessary, based on data it was gathering about how it was being used? That's what The Grid is aiming to do. After you add content such as pictures, text, the stuff everyone enjoys interacting with your obligation to design...</p>

								<h4>The Truth about Teens and Privacy</h4>

								<p>Social media has introduced a new dimension to the well-worn fights over private space and personal expression. Teens do not want their parents to view their online profiles or look over their shoulder when theyâre chatting with friends. Parents are no longer simply worried about what their children wear out of the house but what they photograph themselves wearing in their bedroom to post online. Interactions that were previously invisible to adults suddenly have traces, prompting parents to fret over.</p>

								<h4>Here are some of the ways you may be already being hacked:</h4>

								<ul class="in-list">
									<li>Everyone makes mistakes</li>
									<li>You can control only your behavior</li>
									<li>Good habits create discipline</li>
									<li>Remember the <u>big picture</u></li>
									<li>Everyone learns differently</li>
									<li>Focus on the Benefits, Not the Difficulties</li>
									<li>Traditions are bonding opportunities</li>
								</ul>

								<p>This is not a comprehensive list. Rather, it is a snapshot in time of real-life events that are happening right now. In the future, we will likely read this list and laugh at all the things I failed to envision.</p>
								<p class="with-img">
									<a href="assets/img/banner-85-1.jpg" data-fluidbox-qr><img src="assets/img/banner-85.jpg" alt=""></a>
									<span class="img-caption">Walk through the Forest</span>
								</p>
								<p>Elit try-hard consectetur, dolore voluptate minim distillery. Bespoke Cosby sweater pug street art et keytar. Nihil fish whatever trust fund, dreamcatcher in fingerstache squid seitan accusamus. Organic Wes Anderson High Life setruhe authentic iPhone, aute art party hashtag fixie church-key art veniam Tumblr polaroid. DIY polaroid vinyl, sustainable hella scenester accusamus fanny pack. Ut Neutra enim pariatur cornhole actually Banksy, tote bag fugiat ad accusamus. Incididunt fixie normcore fingerstache. Freegan proident literally brunch before they sold out.
								</p>

								<p>Readymade fugiat narwhal, typewriter VHS aute stumptown hoodie irure put a bird on it. Fashion axe raw denim brunch put a bird on it voluptate Truffaut. Bitters PBR&amp;B nulla Odd Future swag leggings. Banh mi Wes Anderson butcher letterpress skateboard quis. Chambray hella retro viral Cosby sweater photo booth. Schlitz elit Cosby sweater, Blue Bottle non chambray chia. Single-origin coffee pickled.</p>

								<h5>Blockquote</h5>

								<p>Do officia aliqua, pop-up ut et occupy sriracha. YOLO meggings PBR sartorial mollit, Schlitz assumenda vero kitsch plaid post-ironic PBR&amp;B keffiyeh. Cosby sweater wolf YOLO Austin bespoke, American Apparel crucifix paleo flexitarian. Aliquip bitters food truck, incididunt tofu accusamus magna nesciunt typewriter drinking vinegar Shoreditch try-hard you probably havenât heard of them labore. </p>

								<blockquote>
									<p><i>âThe Muppets Take Manhattanâ</i><br />
									This movie was a disappointment. The Muppets do not take Manhattan at all. They merely visit it.<br />
									<span>âÂ No stars.</span></p>
								</blockquote>

								<p>Do officia aliqua, pop-up ut et occupy sriracha. YOLO meggings PBR sartorial mollit, Schlitz assumenda vero kitsch plaid post-ironic PBR&amp;B keffiyeh. Cosby sweater wolf YOLO Austin bespoke, American Apparel crucifix paleo flexitarian. Aliquip bitters food truck, incididunt tofu accusamus.</p>
							</article>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="quick-read-bottom">
			<p class="qr-info">By <a href="#">Daniele Zedda</a>Â Â Â â¢Â Â Â 18 February</p>
			<div class="qr-nav">
				<a href="#" class="qr-prev">â PREV POST</a>
				<a href="#" class="qr-share" tabindex="0" role="button" data-toggle="popover" data-placement="top" data-trigger="focus" data-content="<a href='#'><i class='fa fa-facebook'></i></a><a href='#'><i class='fa fa-twitter'></i></a>"><i class="fa fa-share-alt"></i></a>
				<a href="#" class="qr-comment"><i class="fa fa-comment"></i></a>
				<a href="#" class="qr-like"><i class="fa fa-heart"></i> 34</a>
				<a href="#" class="qr-next">NEXT POST â</a>
			</div>
		</div>
		<div class="quick-read-bottom qr-bottom-2 hide">
			<div class="qr-nav">
				<a href="#" class="qr-prev">â PREV POST</a>
				<p class="qr-info">By <a href="#">Daniele Zedda</a>   â¢   18 February</p>
				<a href="#" class="qr-next">NEXT POST â</a>
				<a href="#" class="qr-like"><i class="fa fa-heart"></i> 34</a>
				<div class="qr-sharebox">
					<span>Share on</span>
					<a href='#'><i class='fa fa-facebook'></i></a>
					<a href='#'><i class='fa fa-twitter'></i></a>
				</div>
			</div>
		</div>
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
					<form action="login.action" method="post">
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
					<form action="register.action" method="post">
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
					<form action="logout.action" method="post">					
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
		var strId = "";
		function shareFacebook(t)
		{
			strId = t.id.subString(8, t.id.length);
			window.open("https://www.facebook.com/dialog/share?app_id=15...http://localhost:8080/WhiteBlog/content.action?id=" + strId, "_blank", "width=400, height=400");
		}
	</script>
	<script type="text/javascript">
		var strId = "";
		function shareTwitter(t)
		{
			strId = t.id.substring(7, t.id.length);
			window.open("https://twitter.com/intent/tweet?text=I\'m here at WhiteBlog!!Explore via  http://localhost:8080/whiteblog/content.action?id=" + strId, "_blank", "width=400, height=400");
		}
	</script>
	<script type="text/javascript">
		var strId = "";
		function myF(t)
		{
			strId = t.id.substring(4, t.id.length);
			var actionName = "clickLike.action?id=";
			actionName += strId;
			$.ajax({
				url:actionName,
				type:"POST",
				dataType:"json",
				success:function(data){
					if(data == "success")
						alert("点赞成功");
					else
						alert("取消点赞");
				}
			});
		}
	</script>
	<script type="text/javascript">		
		$("#notice").click(function(){
			$("#slideform").empty();
			$.ajax({
				url:"notice.action",
				type:"POST",
				dataType:"json",
				success:function(data){
				$.each(data,function(i,list){  
                       		var _tr = '<li class="pt-culture pt-art"><div><h5><i>' + list.noticeId + '</i><a>' + list.content + '</a>' +
						'</h5><div class="post-subinfo"></div></div></li>'
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
				url:"checkNotice.action",
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
				url:"deleteBlog.action?id="+delete_id,
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
				url:"changeDeleteList.action",
				type:"POST",
				dataType:"json",
				success:function(data){
				$.each(data, function(i, list){
					var color_str = ""
					if(classes.indexOf("view-blog") >= 0) {
						$(".Edit_qp").html("删除文章");
						actionStr = "content.action?id="+list.blogId;
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
