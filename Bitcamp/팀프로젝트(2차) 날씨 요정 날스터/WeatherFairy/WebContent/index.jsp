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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>   
	</head>


	<body onload="f1()">
		
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
							<li class="menu-item current-menu-item"><a href="index.do">Home</a></li>
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
								<li class="menu-item"><a href="login.do?m=error">오늘의 코디 Tip</a></li>
							</c:when>
							<c:otherwise>
								<li class="menu-item"><a class="cody">추천 코디</a></li>
								<li class="menu-item"><a class="codyPhoto">나만의 코디</a></li>
								<li class="menu-item"><a class="tcody">오늘의 코디 Tip</a></li>
							</c:otherwise>
							</c:choose>
						</ul> <!-- .menu -->
					</div> <!-- .main-navigation -->

					<div class="mobile-navigation"></div>

				</div>
			</div> <!-- .site-header -->
            
			<div class="hero" data-bg-image="https://scontent-sin6-2.cdninstagram.com/vp/9c3150730ec00f2957423b0c9a0bf32d/5CA690D6/t51.2885-15/e35/41647027_167359637483373_5478755078389886783_n.jpg">
				<div class="container">
					<form action="#" class="find-location">
						<input id="i" type="text" placeholder="구나 시군 단위로 입력해주세요(ex:마포구,서대문구,세종시.. " required>
						<input type="submit" value="Find" onclick="f1()">
					</form>                    
				</div>
			</div>
			<div class="forecast-table">
				<div class="container">
					<div class="forecast-container">
						<div class="today forecast">
							<div class="forecast-header">
								<div class="day">Monday</div>
								<div class="date">6 Oct</div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="location"></div>
								<div class="degree">
									<div class="num"></div>
									<div class="forecast-icon">
										<img src="images/icons/icon-1.svg" alt="" width=200%>
									</div>
                                    <div class="mmnum">
                                    </div>
								</div>
								<span class="weatherId"></span>
								<span class="windId"></span>
							</div>
						</div>
						<div class="forecast">
							<div class="forecast-header">
								<div class="day1"></div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon1">
									<img src="images/icons/icon-3.svg" alt="" width=90%>
								</div>
								<div class="degree1"></div>
								<small class="s1"></small>
							</div>
						</div>
						<div class="forecast">
							<div class="forecast-header">
								<div class="day2"></div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon2">
									<img src="images/icons/icon-5.svg" alt="" width=90%>
								</div>
								<div class="degree2">23<sup>o</sup>C</div>
								<small class="s2"></small>
							</div>
						</div>
						<div class="forecast">
							<div class="forecast-header">
								<div class="day3"></div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon3">
									<img src="images/icons/icon-7.svg" alt="" width=90%>
								</div>
								<div class="degree3">23<sup>o</sup>C</div>
								<small class="s3"></small>
							</div>
						</div>
						<div class="forecast">
							<div class="forecast-header">
								<div class="day4"></div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon4">
									<img src="images/icons/icon-12.svg" alt="" width=90%>
								</div>
								<div class="degree4">23<sup>o</sup>C</div>
								<small class="s4"></small>
							</div>
						</div>
						<div class="forecast">
							<div class="forecast-header">
								<div class="day5"></div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon5">
									<img src="images/icons/icon-13.svg" alt="" width=90%>
								</div>
								<div class="degree5">23<sup>o</sup>C</div>
								<small class="s5"></small>
							</div>
						</div>
						<div class="forecast">
							<div class="forecast-header">
								<div class="day6"></div>
							</div> <!-- .forecast-header -->
							<div class="forecast-content">
								<div class="forecast-icon6">
									<img src="images/icons/icon-14.svg" alt="" width=90%>
								</div>
								<div class="degree6">23<sup>o</sup>C</div>
								<small class="s6"></small>
							</div>
						</div>
					</div>
				</div>
			</div>
			<main class="main-content">			

				<div class="fullwidth-block">
					
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
		<script>          
            function f1(){
            	var email = "${email}";
            	var MEM_NUM = "${MEM_NUM}"
                var aa = new Date()
                var year = aa.getFullYear()
                var month = aa.getMonth()+1
                var date = aa.getDate()
                var dayLabel = aa.getDay()
                
                if(month==1){
                    $(".date").html(date+" Jan "+year);
                }else if(month==2){
                    $(".date").html(date+" Feb "+year);
                }else if(month==3){
                    $(".date").html(date+" Mar "+year);
                }else if(month==4){
                    $(".date").html(date+" Apr "+year);
                }else if(month==5){
                    $(".date").html(date+" May "+year);
                }else if(month==6){
                    $(".date").html(date+" Jun "+year);
                }else if(month==7){
                    $(".date").html(date+" Jly "+year);
                }else if(month==8){
                    $(".date").html(date+" Aug "+year);
                }else if(month==9){
                    $(".date").html(date+" Sep "+year);
                }else if(month==10){
                    $(".date").html(date+" Oct "+year);
                }else if(month==11){
                    $(".date").html(date+" Nov "+year);
                }else{
                    $(".date").html(date+" Dec "+year);
                }
                if(dayLabel==1){
                    $(".day").html("Monday");
                }else if(dayLabel==2){
                    $(".day").html("Tuesday");
                }else if(dayLabel==3){
                    $(".day").html("Wendsday");
                }else if(dayLabel==4){
                    $(".day").html("Thursday");
                }else if(dayLabel==5){
                    $(".day").html("Friday");
                }else if(dayLabel==6){
                    $(".day").html("Saturday");
                }else{
                    $(".day").html("Sunday");
                }

                var city = $("#i").val(); 
                if(city==""){
                    var apiURI = "http://api.openweathermap.org/data/2.5/weather?q=Seoul&appid=d598aa5238a8e0b55e0b2b01a2527407";
                     var apiURI2 = "http://api.openweathermap.org/data/2.5/forecast?q=Seoul&appid=d598aa5238a8e0b55e0b2b01a2527407";
                     $(".location").html("");
                     $(".num").html("");
                     $(".weatherId").html("");
                     $(".windId").html("");
                     $(".mmnum").html("");
                     $(".location").html("서울");
                }else{
                    $(".num").html("");
                    $(".weatherId").html("");
                    $(".windId").html("");
                    $(".mmnum").html("");
                    var apiURI = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=d598aa5238a8e0b55e0b2b01a2527407";
                    var apiURI2 = "http://api.openweathermap.org/data/2.5/forecast?q="+city+"&appid=d598aa5238a8e0b55e0b2b01a2527407";
                    $(".location").html(city);              
                }
                $.ajax({
                    url: apiURI,
                    dataType: "json",
                    type: "GET",
                    async: "false",
                    success: function(resp) {
                        var tempM = document.getElementsByClassName("mmnum");
                        tempM[0].innerHTML+=Math.floor(resp.main.temp_min-273.15)+"℃"+"/"+Math.floor(resp.main.temp_max-273.15)+"℃";
                        var temp = document.getElementsByClassName("num");
                        temp[0].innerHTML+= Math.floor(resp.main.temp-273.15)+"℃";                    
                        var wind = document.getElementsByClassName("windId");
                        wind[0].innerHTML+= "<img src=images/icon-wind.png>"+resp.wind.speed+"m/s";
                        var weather = document.getElementsByClassName("weatherId");
                        if(resp.weather[0].main=="Rain"){
                            $(".forecast-icon").html("<img src=images/icons/icon-10.svg width=90>")
                            weather[0].innerHTML+= "<img src=images/icon-umberella.png>"+ resp.clouds.all+"%";
                        }else if(resp.weather[0].main=="Mist" | resp.weather[0].main=="Clouds" | resp.weather[0].main=="Haze"| resp.weather[0].main=="Fog" | resp.weather[0].main=="Drizzle" ){
                            $(".forecast-icon").html("<img src=images/icons/icon-7.svg width=90>")
                            weather[0].innerHTML+= "<img src=images/icon-umberella.png>"+ resp.clouds.all+"%";
                        }else if(resp.weather[0].main=="Clear"){
                            $(".forecast-icon").html("<img src=images/icons/icon-2.svg width=90>")
                            weather[0].innerHTML+= "<img src=images/icon-umberella.png>"+ resp.clouds.all+"%";
                        }else if(resp.weather[0].main=="Snow"){
                            $(".forecast-icon").html("<img src=images/icons/icon-14.svg width=90>")
                            weather[0].innerHTML+= "<img src=images/icon-umberella.png>"+ resp.clouds.all+"%";
                        }else{
                            weather[0].innerHTML+=resp.weather[0].main;
                        }
               			if(city==""){
               			 	var t="reply.do?m=form&DEGREEC="+Math.floor(resp.main.temp-273.15)+"&REGNAME=서울"+"&MEM_NUM="+MEM_NUM+"&EMAIL="+email+"&TRAIT="+resp.weather[0].main+"&WIND="+resp.wind.speed;
               			 	var c="cody.do?m=form&DEGREEC="+Math.floor(resp.main.temp-273.15)+"&REGNAME=서울"+"&MEM_NUM="+MEM_NUM+"&EMAIL="+email+"&TRAIT="+resp.weather[0].main+"&WIND="+resp.wind.speed;
               				var s="savePhoto.do?m=form&DEGREEC="+Math.floor(resp.main.temp-273.15)+"&REGNAME=서울"+"&MEM_NUM="+MEM_NUM+"&EMAIL="+email+"&TRAIT="+resp.weather[0].main+"&WIND="+resp.wind.speed;
               			 	$(".tcody").prop("href",t);
               				$(".cody").prop("href",c);
               				$(".codyPhoto").prop("href",s);
               			}else{              
                            var c="cody.do?m=form&DEGREEC="+Math.floor(resp.main.temp-273.15)+"&REGNAME="+city+"&MEM_NUM="+MEM_NUM+"&EMAIL="+email+"&TRAIT="+resp.weather[0].main+"&WIND="+resp.wind.speed;
                            var t="reply.do?m=form&DEGREEC="+Math.floor(resp.main.temp-273.15)+"&REGNAME="+city+"&MEM_NUM="+MEM_NUM+"&EMAIL="+eamil+"&TRAIT="+resp.weather[0].main+"&WIND="+resp.wind.speed;
                            var p="savePhoto.do?m=form&DEGREEC="+Math.floor(resp.main.temp-273.15)+"&REGNAME="+city+"&MEM_NUM="+MEM_NUM+"&EMAIL="+email+"&TRAIT="+resp.weather[0].main+"&WIND="+resp.wind.speed;
                            $(".tcody").prop("href",t);
               				$(".cody").prop("href",c);
               				$(".codyPhoto").prop("href",s);
               			}
                    }
                });
                $.ajax({
                    url: apiURI2,
                    dataType: "json",
                    type: "GET",
                    async: "false",
                    success: function(resp2) {
                        for (var i=0;i<6;i++ ){
                            var att = resp2.list[i].clouds.all;
                            var w = resp2.list[i].weather[0].main;
				            var date = resp2.list[i].dt_txt
                            var tempT = Math.floor(resp2.list[i].main.temp-273.15)+"℃";
				            var line = date.indexOf(" ");
                            var d = date.substring(line, 16);
                            var t = "day"+(i+1);
                            var tt = "degree"+(i+1);
                            var ww = "forecast-icon"+(i+1);
                            var ss = "s"+(i+1);
                            $("."+t).html("");
                            $("."+tt).html("");
                            $("."+ss).html("");
                            var timeT = document.getElementsByClassName(t);
                            timeT[0].innerHTML+=d;
                            var degT = document.getElementsByClassName(tt);
                            degT[0].innerHTML+= tempT;
                            var attT = document.getElementsByClassName(ss);
                            attT[0].innerHTML+= "<img src=images/1818.png> "+att+"%";
                            if(w=="Clouds" | w=="Haze" | w=="Fog" | w=="Drizzle"){
                                $("."+ww).html("<img src=images/icons/icon-7.svg width=100%>");
                            }else if(w=="Snow"){
                                $("."+ww).html("<img src=images/icons/icon-13.svg width=95%>");      
                            }else if(w=="Clear"){
                                $("."+ww).html("<img src=images/icons/icon-2.svg width=100%>");
                            }else if(w=="Rain"){
                                $("."+ww).html("<img src=images/icons/icon-9.svg width=100%>");
                            }
				        }      
                    },
                    error: function (jqXHR, exception) {
                    	if (jqXHR.status == 404) {
                    		alert("잘못된 지역 명칭입니다")
                    		window.location.reload();
                        }else{
                        	alert("ajax 호출오류");
                        	window.location.reload();
                        }
                    }
                });      
            }
        </script>
		
	</body>

</html>