<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="assets/img/favicon.ico">
<title>White Blog - 博文内容</title>
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
<!-- Custom styles for this template -->
<link href="assets/css/style.css" rel="stylesheet">
<link type="text/css" rel="stylesheet" href="assets/styles/shCoreDefault.css"></link>
<link type="text/css" rel="stylesheet" href="assets/styles/shCore.css"></link>
<script type="text/javascript" src="assets/scripts/shCore.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushCpp.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushCSharp.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushCss.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushJava.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushJScript.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushPhp.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushPython.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushSql.js" ></script>
<script type="text/javascript" src="assets/scripts/shBrushXml.js" ></script>
<script type="text/javascript">SyntaxHighlighter.all();</script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body onload="timedCount()">
	<s:action name="ShowCommentList"/>
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

		

		<section class="post-fluid">
			<div class="container-fluid">
				<div class="container">
					<div class="row post-items">
						<div class="col-md-2">
							<div class="post-item-short">
								<span class="small-text">Publish at</span>
								<span class="small-text">${req.blog.time}</span>
							</div>
							<br>
							 
								<ul id="tags" class="laread-list">
									<li class="title">Tags</li>
									<li class="bar-tags">
										<s:iterator value="bt" var="tag">
											<a href="findBlogByTagAction.action?id=<s:property value="#tag.typeId" />"><s:property value="#tag.typename"/></a>
										</s:iterator>
										<s:iterator value="sbt" var="stag">
											<a href="findBlogByTagSuperAction.action?id=<s:property value="#stag.supertypeId" />"><s:property value="#stag.supertypeName"/></a>
										</s:iterator>
									</li>
								</ul>
						</div>
						
						<div class="col-md-10 ">
							<div class="post-item">
								<div class="post-item-paragraph">
									<h2><a href="#" class="quick-read"><i class="fa fa-envelop"></i></a>${req.blog.title}</h2>
									<p class="post-item-two-column">																		
									${req.blog.content}	
									</p>
								</div>
								<div class="post-item-info no-border clearfix">
																																	
								<div class="post-item-social">
								
										
									<s:form class="form-horizontal" role="form" action="sendMessage" method="post"> 
													
															<!-- 模态框（Modal） -->
															<div class="modal leread-modal form-horizontal fade" id="myModal" tabindex="-1"
																role="dialog" aria-labelledby="myModalLabel" 
																aria-hidden="true">
																<div class="modal-dialog">
																	<div class="modal-content">
																		<div class="modal-header">
																			<button type="button" class="close"
																				data-dismiss="modal" aria-hidden="true">×</button>
																			<!-- 模态框的标题部分 -->
																			
																			<h4  class="modal-title" id="myModalLabel" ><i class="fa fa-envelope"></i>私信我吧</h4>
																			<input style="display:none;" class="form-control" aria-hidden="true"  readonly="true" value="${req.blog.blogId}" name="id" class="modal-title" id="id" /> 
																		</div>
																		<!-- 模态框的内容部分 -->
																		<div class="form-group modal-header">
																			<textarea class="form-control" id="mesContent" name="mesContent"
																				placeholder="说点什么"></textarea>
																			<!-- <div class="modal-body">按下 ESC 按钮退出。</div> -->
																		</div>
																		<div class="modal-footer">
																			<button type="submit" class="btn btn-golden  btn-signin">
																				发送 </button>
												                     <!--  	</a>  -->
																		</div>
																	</div>
																	<!-- /.modal-content -->
																</div>
																<!-- /.modal-dialog -->
															</div>
															<!-- /.modal 模态框结束-->															
															</s:form> 
										<c:choose>
											<c:when test="${sessionScope.loginUser != null}">
												<div class="pull-right post-item-social" >
													<a  tabindex="0"   data-toggle="modal"  data-target="#myModal2"> <div class="tag" ></div>  </a>
													<a href="#" tabindex="0" role="button" data-toggle="popover" data-trigger="focus" data-placement="top" data-content="<a href='#' id='facebook${req.blog.blogId}' onclick='shareFacebook(this)'><i class='fa fa-facebook'></i></a><a href='#' id='twitter${req.blog.blogId}' onclick='shareTwitter(this)'><i class='fa fa-twitter'></i></a>" class="pis-share"><i class="fa fa-share-alt"></i></a>
													<a data-toggle="tooltip" title="like" href="#" id="like${req.blog.blogId}" onclick="myF(this)" class="post-like qr-like"><i class="fa fa-heart"></i><span>${req.blog.likenumber}</span></a>
												</div>
											</c:when>	
										</c:choose>

																																		
												 <s:form class="form-horizontal" role="form" action="addTags" method="post"> 
													
															<!-- 模态框（Modal） -->
															<div class="modal  form-horizontal fade" id="myModal2" tabindex="-1"
																role="dialog" aria-labelledby="myModalLabel" 
																aria-hidden="true">
																<div class="modal-dialog">
																	<div class="modal-content">
																		<div class="modal-header" id="header2">
																			<button type="button" class="close"
																				data-dismiss="modal" aria-hidden="true">×</button>
																			<!-- 模态框的标题部分 -->
																			
																			 <div class="tag"></div>
																			<div><h4  class="modal-title" id="myModalLabel2" >更改标签</h4></div>
																		
																		<div class="modal-header">
																			<select class="dropdwon-menu" name="supertypeId">
																				<c:forEach var="stags" items="${ast}">
																					<option style="width:160px" value="${stags.supertypeId}">${stags.supertypeName}</option>
																				</c:forEach>
																			</select>
																		</div>
																			<textarea class="form-control" id="mesContent2" name="mesContent2"  
																				placeholder="新建标签,在这写上新的标签吧"></textarea>																		 

																	 		
																			 <!-- 不用管下面这行 -->
																			<input style="display:none;" class="form-control" aria-hidden="true"  readonly="true" value="${req.blog.userId}" name="id" class="modal-title" id="id" />
																			<input style="display:none;" class="form-control" aria-hidden="true"  readonly="true" value="${req.blog.blogId}" name="bid" class="modal-title" id="bid" />  
																		</div>
																		
																		<!-- 模态框的内容部分 -->
																		<div class="modal-header">
																			<c:forEach var="tag" items="${btl}">
																				<p class="btn btn-default btn-grey btn-outline" id="${tag.typename}" onclick="getTypename(id)">${tag.typename}</p> 
																			</c:forEach>
																		</div>
																	
																		<div class="modal-footer">
																			<button type="button" class="btn btn-default btn-grey btn-outline" 
																				data-dismiss="modal">关闭</button>
																			<button type="submit" class="btn btn-primary btn-grey btn-outline">添加</button>
												                     <!--  	</a>  -->
																		</div>

																		
																	</div>
																	<!-- /.modal-content -->
																</div>
																<!-- /.modal-dialog -->
															</div>
															<!-- /.modal 模态框结束-->
												</s:form> 																																																		
									</div>
								</div>
							</div>

							
							<div class="author-box">
								<div class="author">
									<a class="author-photo" href="#"><img src="assets/img/profil_photo-04.png" alt=""></a>
									<div class="author-body">
										<h4 class="author-name">作者：${req.username}</h4>
										<h4 class="author-name">${sessionScope.loginUser.username}</h4>
										<a href="#">view all post</a>
									</div>
									<c:choose>
										<c:when test="${sessionScope.loginUser!=null }">
											<div class="author-connection">
												<a  data-toggle="modal"	data-target="#myModal"><div class="mails"> </div></a> 
											</div>
										</c:when>
									</c:choose>		
								</div>
							</div>

							<div class="comment-box">
								
								<div class="comment-tab">
									<a href="#" class="comment-info">Comments (28)</a>
									<i class="i">|</i>
									<a href="#" class="comment-info"><i class="fa fa-comments"></i> Show all</a>
								</div>

								<div class="comment-block">
									<s:iterator value = "#session.commentList" var = "comment">
										<div class="comment-item">
											<a class="comment-photo" href="#">
												<img src="assets/img/profil_photo-05.png" alt="" />
											</a>
											<div class="comment-body">
												<h6 class="comment-heading"> ${comment.username} <span class="comment-date">${comment.time}</span></h6>
												<p class="comment-text">${comment.content} </p>
												<a href="#" class="comment-reply"><i class="reply-icon"></i> Reply</a>
											</div>
										</div>
									</s:iterator>

									<div class="comment-form main-comment-form">
										<form action="PostComment.action" method="post">
											<textarea class="comment-textarea" placeholder="Leave a comment..." name = "commentform.content"></textarea>
											<div class="at-focus">
												<button class="comment-submit">Post Comment</button>
											</div>
										</form>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>

		<footer class="container-fluid footer">
			<div class="container text-center">
				<div class="footer-logo"><img src="assets/img/logo-black.png" alt=""></div>
				<p class="laread-motto">Designed for Read.</p>
<!-- 				<div class="laread-social">
					<a href="#" class="fa fa-twitter"></a>
					<a href="#" class="fa fa-facebook"></a>
					<a href="#" class="fa fa-pinterest"></a>
				</div> -->
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
			<p class="qr-info">By <a href="#">Daniele Zedda</a></p>
			
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
	<script src="assets/js/jquery.ellipsis.min.js"></script>
	<script src="assets/js/isotope.pkgd.min.js"></script>
	<script src="assets/js/jquery.dotdotdot.min.js"></script>
	<script src="assets/js/jquery.colorbox-min.js"></script>
	<script src="assets/js/jquery.nicescroll.min.js"></script>
	<script src="assets/js/viewportchecker.js"></script>
	<script src="assets/js/calendar.js"></script>
	<script src="assets/js/jquery.touchSwipe.min.js"></script>
	<script src="assets/js/script.js"></script>
	<script type="text/javascript">
		var strId = "";
		function shareTwitter(t)
		{
			strId = t.id.substring(7, t.id.length);
			window.open('https://twitter.com/intent/tweet?text=I\'m here at whiteblog http://localhost:8080/whiteBlog/content.action?id=' + strId,"_blank","width=500px;height=500px;");
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
	<script>
  		$(function () { $('#myModal').modal('hide')});
  		$("#myModal").modal().css({
        "margin-top": function () {
        	return - ($(this).height() / 2);}
        });
  		$(function() {$('#myModal2').modal('hide')});
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
	
 	<script type="text/javascript">  
		var i = "df"; var t = "";
        function getTypename(id){
	    	i = document.getElementById(id).innerText;
	    	document.getElementById("mesContent2").value = i;
	    } 
	</script>	
</body>
	
</html>
