<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<sec:authentication property="principal" var="loginDTO"/>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="Dashboard">
  <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">
<title>${member.m_name}(@${member.m_id})님 | 버즈비 </title>

<!-- 추가한 링크 start-->
<link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
<link rel='stylesheet' href='${pageContext.request.contextPath}/lib/bootstrap/css/twitter-bootstrap.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/octicons/4.4.0/font/octicons.min.css'>
<!-- 추가한 링크 end -->
<link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cham.css">
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/hyeong.css'>
<link rel='stylesheet' href='${pageContext.request.contextPath}/css/yuma.css'>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">

<script src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js" ></script>
<!-- jQuery CDN-->
</head>

<body>
   <header>      
      <div class="head">      
         <div class="head-part">                  
            <a class="logo" id="modal-img" href="<c:url value='/'/>">
            <p class="logo-head">Buz</p>
              <p class="logo-tail">bee</p>
            <img class="head-right" src="${pageContext.request.contextPath}/img/fi.png">
            </a>
            <a id="head-sm-left" class="head-head" href="notice"><i class="octicon octicon-bell" id="head-detail"></i>알림</a>
            <a id="head-sm-left2" class="head-tail" href="setting"><i class="glyphicon glyphicon-cog" id="head-detail"></i>설정</a>
            <nav class="nav">      
               <ul>
               <li>
               <form action="search" method="post">
                  <input type="text" class="search-bar" id="search-bar" name="search" placeholder=" 버즈비 검색"/>
                      <button class="search" id="search" onclick="submit"><i class="octicon octicon-search navbar-search-icon"></i></button>
                   </form>                                   
      <!-- slide-menu start -->
         <div onclick="history.back();" class="page_cover"></div>
         <div id="slideMenu">
            <div class="slideHeader">
               <a href="<c:url value='/${member.m_id}'/>">   
                  <div class="text-head">${member.m_name}</div>
               </a>
               <div class="text-tail">${member.m_id}</div>
            </div>
            <div class="slideList">
                  <p class="modalMension" id="slideText" onclick="myFunction()">멘션 알림</p>
                  <p class="modalBuzers" id="slideText" onclick="myFunction()">버저스 추천</p>
                  <p class="modalOption" id="slideText" onclick="myFunction()">설정</p>
                  <p class="modalLogout" id="slideText" onclick="myFunction()">로그아웃</p>
            </div>
            <a href="<c:url value='/${member.m_id}'/>">
             <div onclick="history.back();" class="menuClose"></div>
             </a>
         </div>
      <!-- slide-menu end -->   
         <button class="buzing-button" id="myBtn">버징하기</button>
      
      
      <!-- The bugzing Modal start-->
             <div id="myModal" class="modal">             
               <!-- Modal content -->
               <div class="modal-content">
                 <div class="modal-header" style='background:#ffe180'>
                    <!-- 닫기(x) 버튼 -->
                    <button type="button" class="close" data-dismiss="modal">×</button>
                    <!-- header title -->
                    <h3 class="modal-title text-center" style='font-weight:bold;color:black'>새로운 버징 작성</h3>
                </div>                    
                <div class="modal-body">
                     <textarea class='form-control' id='modal-text' style='font-size:1.5rem' rows='5' placeholder='무슨 일이 일어나고 있나요?'></textarea>
                     <div class="img-area-modal"></div>
                </div>                     
                <div class="modal-footer">
                   <button class="replace">파일 업로드</button>  
                  <input type='file' value="파일 업로드" class="fileButton" id='file' multiple='multiple' onchange='readURL(this, ".img-area-modal")'/>
                    <span id='modal-textCount'>0</span>/140&nbsp;
                    <button class="modal-button disabled" id='modal-sendBtn' data-dismiss="modal">버징하기</button>
                </div>
               </div>       
             </div>
             <script>
               $(function(){
                    $('#modal').on('hidden.bs.modal', function (e) {                                              
                       uploadFiles = [];
                    });
                    $("#myBtn").click(function(){
                       $("#appended").remove();   
                       $(".img-area-main").hide();
                       $('.foo').remove();                       
                       uploadFiles = [];
                       $('#modal').modal();
                        $('#modal-text').focus();
                    })
                })
             </script>
      <!-- The bugzing Modal end-->      
         
               </li>
               </ul>               
            </nav>                     
         </div>   
      </div>
   </header>   
   
   <div class="myPage">
      <div class="main-size">
         <img class="main-img" src="${pageContext.request.contextPath}/img/${headerImg}">
      </div>   
      
      <div class="main-bar">
         <div class="hidden-part">            
            <div class="hidden-profile">
               <a class="hidden-a" onclick="myFunction()">
                   <img src="${pageContext.request.contextPath}/img/${profile}" class="hidden-img">      
               </a>               
               <div>
                  <div class="hidden-text">
                     <a href="<c:url value='/${member.m_id}'/>" class="text-head">${member.m_name}</a>               
                  </div>                        
                  <div class="hidden-text">
                     <a href="" class="text-tail">@${member.m_id}</a>                           
                  </div>
               </div>                     
            </div>            
         </div>
         <div class="second-menu">   
            <ul>
               <li class="second-li">
                  <a href="<c:url value='/${member.m_id}'/>">
                  <span class="menu-title">버징</span>   
                  <span class="sidebar-tail" id='buzzingCount'>${etc.buzzes}</span>            
                  </a>    
               </li>
               <li>
                  <a href="following">
                  <span class="menu-title">팔로잉</span> 
                  <span class="sidebar-tail">${etc.following}</span>
                  </a>
               </li>               
               <li style='border-bottom:2px solid black'>
                  <a href="follow">              
                  <span class="menu-title">팔로워</span>
                  <span class="sidebar-tail">${etc.follower}</span>
                  </a>
                </li>               
               <li>
                  <a href="like">
                  <span class="menu-title">좋아요</span>
                  <span class="sidebar-tail" id="side-likes">${etc.likes}</span>
                  </a>
               </li>
            </ul>               
          </div>   
      </div>
   </div>
   
   <div class="body origin" id="body-md">
     <div class="sidebar-padding" id="sidebar-md">
        <div class="profile-location">
         <div class="profile-size">
            <p>              
             <img src="${pageContext.request.contextPath}/img/${profile}" class="img-circle">              
            </p>
            <div class="introduce">
             <p class="intro-header">${loginDTO.m_name}</p>
             <p>@${loginDTO.username}</p>
             <p>${loginDTO.m_pr}</p>
            </div>
         </div>         
      </div>
     </div>
   </div>
           
     <section id="main-content">
         <section class="wrapper site-min-height nomargin" id='section-body'>
        <div id='content-area'>
         <c:forEach var="item" items="${list}">
            <div class="container${item.count }">
               <div class='header-fol'>
                  <div class="bio">
                     <a href="../${item.m_id }"><img src="${pageContext.request.contextPath}/img/${item.header_name }"
                        alt="background" class="bg" style="height: 106px;width:300px"></a>
                  </div>
   
                  <div class="avatarcontainer">
                     <!-- 프로필 사진 이미지  -->
                     <a href="../${item.m_id }"><img src="${pageContext.request.contextPath}/img/${item.profile_name}"
                        alt="avatar" class="avatar"></a>
                  </div>
               </div>
   
               <div class="content2">
                  <div class="data">
                     <ul class="ulF">
                        <a href="../${item.m_id }"><B><li style="font-size:1.8em;">${item.m_name}</li></B></a>
                        <a href="../${item.m_id }"><li style="color:gray; font-size:1.5em; margin-top: -10px;">@${item.m_id }</li><br/></a>
                        <li class="ellipsis-multi" style="font-size:1.5em;">${item.m_pr } </li><br/>
                        <div class="fwing"><b>팔로우</b></div>
                     </ul>
                  </div>
               </div>
               </div>
         </c:forEach>
         </div>
         </section>
      </section>
      
      <div class="main">
      <!-- disabla 관련 추가 start-->
      <div id="disableDiv" style="display:none"></div>
        <button class="topButton" type="button" onclick="$('html').animate({scrollTop : 0})"></button>
     </div>
</body>   
<!-- js placed at the end of the document so the pages load faster -->

<script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
<script
   src="https://code.jquery.com/jquery-1.9.0.js"
   integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
   crossorigin="anonymous"></script>
<!--common script for all pages-->
<script src="${pageContext.request.contextPath}/lib/modal.js"></script>
<script src="${pageContext.request.contextPath}/lib/profile.js"></script>
<script src="${pageContext.request.contextPath}/lib/moon.js"></script>

<!--script for this page-->

</html>