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
<style>
.button {
  background-color: navy /* Green */
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
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
							<li class="menu-item"><a href="login.do?m=form&city=">로그인</a></li>
							</c:when>
							<c:otherwise>
							<li class="menu-item"><a href="login.do?m=out">로그아웃</a></li>
							</c:otherwise>
							</c:choose>
							<c:choose>
							<c:when test="${empty MEM_NUM}">
								<li class="menu-item"><a href="login.do?m=error">추천 코디</a></li>
								<li class="menu-item"><a href="login.do?m=error">나만의 코디</a></li>
								<li class="menu-item current-menu-item"><a href="login.do?m=error">오늘의 코디 Tip </a></li>
							</c:when>
							<c:otherwise>
								<li class="menu-item"><a href="cody.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">추천 코디</a></li>
								<li class="menu-item"><a href="savePhoto.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">나만의 코디</a></li>
								<li class="menu-item current-menu-item"><a href="reply.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">오늘의 코디Tip </a></li>
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
						<a href="index.do">Home</a>
						<span>오늘의 코디 Tip</span>
					</div>
				</div>
               
				<div class="fullwidth-block">
					<div class="container">
                        <div class="filter">
							<div class="country filter-control">
								<label for="">지역구</label>
								<button class="button">${REG_NAME}</button>
							<!-- 	<span class="select control">
									<select name="" id="">
										<option value="">서울시 </option>
                                        <option value="">경기도 </option>
                                        <option value="">인천시 </option>
                                        <option value="">강원도 </option>
                                        <option value="">충청북도 </option>
                                        <option value="">충청남도 </option>
                                        <option value="">대전광역시 </option>
                                        <option value="">경상북도 </option>
                                        <option value="">경상남도 </option>
                                        <option value="">대구광역시 </option>
                                        <option value="">전라북도 </option>
                                        <option value="">전라남도 </option>
                                        <option value="">광주광역시 </option>
                                        <option value="">울산광역시 </option>
                                        <option value="">부산광역시 </option>
                                        <option value="">제주특별시 </option>             
									</select>
								</span> -->
							</div>							  
						</div>          
                        
                        
                        
                        
                        <div class="replylist"> 
                           <form action="#" class="contact-form">
                           <textarea name="" disabled><c:if test='${!empty list}'>
                           <c:forEach var='item' items='${list}'>${item.getCOMMENTC()}   -   (익명의 회원   ${item.getMEM_NUM()})
                           </c:forEach></c:if>
								</textarea>								
							</form>                           
                        </div>
                        
       
						<div class="replylist">
							<h2 class="section-title">한줄평</h2>						
							<form name="f" action="reply.do" class="contact-form">								
								<textarea name="story" placeholder="오늘 날씨는 어떤가요? 한마디 남겨주세요! (ex : 패딩 입고 걸으니 땀나요)"></textarea>
								<div class="text-right">
									<input type="submit" value="제출">
									<input type="hidden" name="MEM_NUM" value=${param.MEM_NUM}>
									<input type="hidden" name="REGNAME" value=${param.REGNAME}>
								</div>
							</form>

						</div>
					</div>
				</div>
				
			</main> <!-- .main-content -->

			<footer class="site-footer">
				<div class="container">
					<div class="row">
						<div class="col-md-8">
							<form action="#" class="subscribe-form">
								
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

					<p class="colophon">Copyright 2018 WeatherFairy. Designed by Themezy. All rights reserved</p>
				</div>
			</footer> <!-- .site-footer -->
		</div>
		
		<script src="js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&amp;language=en"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>
		
	</body>

</html>