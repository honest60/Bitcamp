<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.w3-btn {width:150px;}
</style>
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

	</head>


	
	
<script>

function printTime() {

              var clock = document.getElementById("clock");            // 출력할 장소 선택

              var now = new Date();                                                  // 현재시간

              var nowTime = (now.getMonth()+1) + "월 " + now.getDate() + "일 " + now.getHours() + "시 " + now.getMinutes() + "분";
              clock.innerHTML = nowTime;           // 현재시간을 출력
              setTimeout("printTime()",1000);         // setTimeout(“실행할함수”,시간) 시간은1초의 경우 1000
}

window.onload = function() {                         // 페이지가 로딩되면 실행
              printTime();

}

</script>
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
								<li class="menu-item current-menu-item"><a href="login.do?m=error">추천 코디</a></li>
								<li class="menu-item"><a href="login.do?m=error">나만의 코디</a></li>
								<li class="menu-item"><a href="login.do?m=error">오늘의 코디 Tip </a></li>
							</c:when>
							<c:otherwise>
								<li class="menu-item current-menu-item"><a href="cody.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">추천 코디</a></li>
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
						<span>추천 코디</span>
					</div>
				</div>
                <div class="fullwidth-block">
					<div class="container"s>
						<div class="row">							
							<div class="sidebar">
								<div class="widget">

                                        <br/>
                                        <h3 class="location">
                                            <div style="text-align:center">${REG_NAME}  <small class="date"><span id="clock"> </span></small><br/><br/>맑지만 아침에 매우 추워요. 마스크 챙기세요!</div></h3>
									  <div class="w3-panel w3-topbar w3-bottombar w3-leftbar w3-rightbar w3-border-white">
                                    <br/><div style="text-align:center">
                                        비슷한 날씨에 내가 입었던 코디</div><br/>
        <c:if test='${!empty list}'>
		<c:forEach var='item' items='${list}'>
                                <small class="date">${item.getTDATE()}</small>
                ${item.getTOP()}, ${item.getBOTTOM()}, ${item.getTOUTER()}, ${item.getETC()} ${item.getFEELING()}<button class="w3-button w3-black w3-round-xlarge">사진 보기</button>
                                    <br/>
                                    </c:forEach></c:if>
                                    <br/><br/>
                                       <br/><div style="text-align:center">
                                        비슷한 날씨에 다른 사람이 입었던 코디</div><br/>
        <c:if test='${!empty list2}'>
		<c:forEach var='item2' items='${list2}'>
                                <small class="date">${item2.getTDATE()}</small>
                ${item2.getTOP()}, ${item2.getBOTTOM()}, ${item2.getTOUTER()}, ${item2.getETC()} ${item2.getFEELING()}
                                    <br/>
                                    </c:forEach></c:if>
                                    <br/><br/>
                                    </div>
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

					<p class="colophon">Copyright 2014 Company name. Designed by Themezy. All rights reserved</p>
				</div>
			</footer> <!-- .site-footer -->
		</div>
		
		<script src="js/jquery-1.11.1.min.js"></script>
		<script src="js/plugins.js"></script>
		<script src="js/app.js"></script>

		
	</body>

</html>