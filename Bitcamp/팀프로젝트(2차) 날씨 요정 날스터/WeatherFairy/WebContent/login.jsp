<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1">
		
		<title>Weather Fairy Nalster</title>

		<!-- Loading third party fonts -->
		<link href="http://fonts.googleapis.com/css?family=Roboto:300,400,700|" rel="stylesheet" type="text/css">
		<link href="fonts/font-awesome.min.css" rel="stylesheet" type="text/css">

		<!-- Loading main css file -->
		<link rel="stylesheet" href="style.css">
		
		<!--[if lt IE 9]>
		<script src="js/ie-support/html5.js"></script>
		<script src="js/ie-support/respond.js"></script>
		<![endif]-->
		<script>
		   function check()
		   {
		      if(f.email.value == "")
			  {
			      alert("아이디를 입력하셔야 합니다.");
				  f.email.focus();
				  return false;
			  }
			  if(f.pwd.value == "")
			  {
			      alert("비밀번호를 입력하셔야 합니다.");
				  f.pwd.focus();
				  return false;
			  }
			  f.submit();
		   }
		</script>

	</head>


	<body>
		
		<div class="site-content">
			<div class="site-header">
				<div class="container">
					<a href="index.do" class="branding">
						<img src="images/logo3.png" alt="" class="logo">
								<div class="logo-type">
							<h1 class="site-title">Weather Fairy Nalster</h1>
								</div>
					</a>

					<!-- Default snippet for navigation -->
					<div class="main-navigation">
						<button type="button" class="menu-toggle"><i class="fa fa-bars"></i></button>
						<ul class="menu">
							<li class="menu-item"><a href="index.do">Home</a></li>
							<c:choose>
			  				<c:when test="${empty email}">
							<li class="menu-item current-menu-item"><a href="login.do?m=form&city=">로그인</a></li>
							</c:when>
							<c:otherwise>
							<li class="menu-item"><a href="login.do?m=out">로그아웃</a></li>
							</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${empty MEM_NUM}">
								<li class="menu-item"><a href="login.do?m=error">추천 코디</a></li>
								<li class="menu-item"><a href="login.do?m=error">나만의 코디 뿜뿜</a></li>
								<li class="menu-item"><a href="login.do?m=error">생생한 오늘 코디 Tip </a></li>
							</c:when>
							<c:otherwise>
								<li class="menu-item"><a href="cody.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">추천 코디</a></li>
								<li class="menu-item"><a href="savePhoto.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">나만의 코디</a></li>
								<li class="menu-item"><a href="reply.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">오늘의 코디Tip </a></li>
							</c:otherwise>
							</c:choose>
						</ul> <!-- .menu -->
					</div> <!-- .main-navigation -->

					<div class="mobile-navigation"></div>

				</div>
			</div> <!-- .site-header -->

			<main class="main-content">
				<div class="container">
					<div class="breadcrumb">
						<a href="index.html">Home</a>
						<span>로그인</span>
					</div>
				</div>
				

				<div class="fullwidth-block">
					<div class="container">
						<div class="row">							
							<div class="sidebar">
								<div class="widget">
									<h3 class="widget-title">로그인</h3>
                                    <form name="f" action="login.do?m=check" method="post">
                                    <input type="text" name="email" placeholder="Username" required><br>
                                    <input type="password" name="pwd" placeholder="Password" required><br>
                                    <input type="submit" name="login" class="login login-submit" onclick="check()" value="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;login&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;">
                                    </form>
									<ul class="arrow-list">
										<li><a href="login.do?m=join">회원가입</a>&nbsp;&nbsp;&nbsp;<a href="#">비밀번호 찾기</a></li>																			
									</ul>
								</div>
								<div class="widget top-rated">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</main> <!-- .main-content -->

			<footer class="site-footer">
				<div class="container">
					<div class="row">
						<div class="col-md-8">
							<form action="login.do?m=check" class="subscribe-form">
								
							</form>
						</div>
						<div class="col-md-3 col-md-offset-1">
							<div class="social-links">
								<a href="#"><i class="fa fa-facebook"></i></a>
								<a href="#"><i class="fa fa-twitter"></i></a>
								<a href="#"><i class="fa fa-google-plus"></i></a>
								<a href="#"><i class="fa fa-pinterest"></i></a>
							</div>
						</div>
					</div>

					<p class="colophon">Copyright 2014 Company name. Designed by Themezy. All rights reserved</p>
				</div>
			</footer> <!-- .site-footer -->
		</div>
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>	
	</body>

</html>