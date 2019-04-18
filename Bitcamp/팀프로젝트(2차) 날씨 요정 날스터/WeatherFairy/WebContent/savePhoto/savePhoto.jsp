<%@ page contentType="text/html; charset=utf-8"%>
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
		  var umaxChecked = 3;   
		  var utotalChecked = 0;  	
		  var dmaxChecked = 3;   
		  var dtotalChecked = 0; 
		  var omaxChecked = 3;   
		  var ototalChecked = 0; 
		  var emaxChecked = 3;   
		  var etotalChecked = 0; 	
		  var fmaxChecked = 1;   
		  var ftotalChecked = 0; 		
		  
		  function CountChecked(field) {
		      if (field.checked)
		    	  utotalChecked += 1;
		      else
		    	  utotalChecked -= 1;

		      if (utotalChecked > umaxChecked) {
		          alert ("상의는 최대 3개 까지만 가능합니다.");
		      field.checked = false;
		      utotalChecked -= 1;
		      }
		  }	  
		  function CountChecked2(field) {
		      if (field.checked)
		    	  dtotalChecked += 1;
		      else
		    	  dtotalChecked -= 1;

		      if (dtotalChecked > dmaxChecked) {
		          alert ("하의는 최대 3개 까지만 가능합니다.");
		      field.checked = false;
		      dtotalChecked -= 1;
		      }
		  }	  
		  function CountChecked3(field) {
		      if (field.checked)
		    	  ototalChecked += 1;
		      else
		    	  ototalChecked -= 1;

		      if (ototalChecked > omaxChecked) {
		          alert ("outer는 최대 3개 까지만 가능합니다.");
		      field.checked = false;
		      ototalChecked -= 1;
		      }
		  }		  
		  function CountChecked4(field) {
		      if (field.checked)
		    	  etotalChecked += 1;
		      else
		    	  etotalChecked -= 1;

		      if (etotalChecked > emaxChecked) {
		          alert ("etc 최대 3개 까지만 가능합니다.");
		      field.checked = false;
		      etotalChecked -= 1;
		      }		      
		  }  
		  function CountChecked5(field) {
		      if (field.checked)
		    	  ftotalChecked += 1;
		      else
		    	  ftotalChecked -= 1;

		      if (ftotalChecked > fmaxChecked) {
		          alert ("느낌평은 한개만 가능합니다.");
		      field.checked = false;
		      etotalChecked -= 1;
		      }		      
		  }
		  function save() {	  		  
			if(utotalChecked == 0){
				alert("상의 1개 이상 체크해주세요^^.")				
			}else if(dtotalChecked == 0){
				alert("하의  1개 이상 체크해주세요^^.")
			}else if(ototalChecked == 0){
				alert("outer  1개 이상 체크해주세요^^.")
			}else if(etotalChecked == 0){
				alert("etc  1개 이상 체크해주세요^^.")
			}else if(ftotalChecked == 0){
				alert("느낌평  1개 이상 체크해주세요^^.")
			} else {
				document.savePhoto.submit();			
				alert("저장완료^^")
			}			
		  }
		
		  function fileCheck(el) { 

			    if(!/\.(jpeg|jpg|png|gif|bmp)$/i.test(el.value)){ 
			        alert('이미지 파일만 업로드 가능합니다.'); 
			        el.value = ''; 
			        el.focus(); 
			    }
		  }

			</script>  
			
		</script>
		
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
								<li class="menu-item current-menu-item"><a href="login.do?m=error">나만의 코디</a></li>
								<li class="menu-item"><a href="login.do?m=error">오늘의 코디 Tip </a></li>
							</c:when>
							<c:otherwise>
								<li class="menu-item"><a href="cody.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${param.REGNAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">추천 코디</a></li>
								<li class="menu-item current-menu-item"><a href="savePhoto.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${REG_NAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">나만의 코디</a></li>
								<li class="menu-item"><a href="reply.do?m=form&DEGREEC=${param.DEGREEC}&REGNAME=${param.REGNAME}&MEM_NUM=${MEM_NUM}&EMAIL=${email}&WIND=${param.WIND}&TRAIT=${param.TRAIT}">오늘의 코디Tip </a></li>	
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
						<span>나만의 코디</span>
					</div>
				</div>
                
                <div class="fullwidth-block" style="text-align: center;">
					<div class="container">
						<div class="row">							
                            <form name="savePhoto" action="savePhoto.do?m=insert" method="post"
	                                     enctype="multipart/form-data">
							<div class="breadcrumb">							
                                <div class="widget">
									오늘의 코디를 남겨주세요^-^*
								</div>
                                <div class="widget">
									(중복선택가능)
								</div><br>
                                <div class="widget">
									상의
								</div>
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="민소매">민소매
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="반팔">반팔
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="긴팔">긴팔
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="셔츠">셔츠
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="블라우스">블라우스
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="남방">남방
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="조끼">조끼
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="니트">니트
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="가디건">가디건
                                <input onclick=CountChecked(this)  type="checkbox" name="up" value="원피스">원피스
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="후드">후드
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="목폴라">목폴라
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="히트텍">히트텍
                                <input onclick=CountChecked(this) type="checkbox" name="up" value="기타">기타
                                <div class="widget"><br>
									하의
								</div>                                
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="반바지">반바지
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="면바지">면바지
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="청바지">청바지
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="기모바지">기모바지
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="슬랙스">슬랙스
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="살색스타킹">살색스타킹
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="기모스타킹">기모스타킹
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="히트텍">히트텍
                                <input onclick=CountChecked2(this) type="checkbox" name="down" value="치마">치마
                                 <input onclick=CountChecked2(this) type="checkbox" name="down" value="치마">기타
                                <div class="widget"><br>
									아우터
								</div>
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="자켓">자켓
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="후드집업">후드집업
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="간절기 야상">간절기 야상
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="트랜치코트">트랜치코트
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="라이더자켓">라이더자켓
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="후리스">후리스
                                <input onclick=CountChecked3(this)  type="checkbox" name="outer" value="야구잠바">야구잠바
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="야상">야상
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="무스탕">무스탕
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="숏패딩">숏패딩
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="코트">코트
                                <input onclick=CountChecked3(this) type="checkbox" name="outer" value="롱패딩">롱패딩                        
                                <div class="widget"><br>
									ETC
								</div>                                
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="손풍기">손풍기
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="부채">부채
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="양산">양산
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="우산">우산
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="캡모자">캡모자
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="목도리">목도리
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="장갑">장갑
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="핫팩">핫팩
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="귀도리">귀도리
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="털모자">털모자
                                <input onclick=CountChecked4(this) type="checkbox" name="etc" value="수면양말">수면양말 
                                <div class="widget"><br>
									[ 오늘의 코디를 남겨주세요!! ]
								</div>
                                <input onclick=CountChecked5(this) type="checkbox" name="feeling" value="딱 좋았어요">딱 좋았어요
                                <input onclick=CountChecked5(this) type="checkbox" name="feeling" value="조금 더웠어요">조금 더웠어요
                                <input onclick=CountChecked5(this) type="checkbox" name="feeling" value="너무 더웠어요">너무 더웠어요
                                <input onclick=CountChecked5(this) type="checkbox" name="feeling" value="조금 추웠어요">조금 추웠어요
                                <input onclick=CountChecked5(this) type="checkbox" name="feeling" value="너무 추웠어요">너무 추웠어요
                                <br>
                                <tr>
								    <td align="center" width="20%">사진 업로드</td>
									<td align="center" width="80%">
									  <input type="file" name="filename" size="46" accept="image/*" onchange="fileCheck(this);" >
									  <input type="hidden" name="MEM_NUM" value=${MEM_NUM}>
									  <input type="hidden" name="DEGREEC" value=${param.DEGREEC}>
									</td>
								</tr><br>                               
                                <input type="button" value="저장하기" onclick="save()">
							</div>
                            </form> 
                            
                            <!-- test 추가 -->
                            <img src="<c:url value='/store/Tulips.jpg'/>" alt="original image" id="original-image" onclick="change()">                          
						</div>
					</div>
				</div>
			</main> <!-- .main-content -->

			<footer class="site-footer">
				<div class="container">
					<div class="row">					
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