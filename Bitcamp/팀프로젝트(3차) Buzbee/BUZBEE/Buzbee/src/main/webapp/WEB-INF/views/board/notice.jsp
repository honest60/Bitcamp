<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<sec:authentication property="principal" var="loginDTO"/>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>버즈비</title>
  
  <!-- Favicons -->
  <link href="${pageContext.request.contextPath}/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css" rel="stylesheet">
  
  <!--가져온 거 -->
  <link rel='stylesheet' href='${pageContext.request.contextPath}/lib/bootstrap/css/twitter-bootstrap.css'>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/octicons/4.4.0/font/octicons.min.css'>
  <link rel='stylesheet' href='${pageContext.request.contextPath}/css/hyeong.css'>
  <link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">
  
  <!--external css-->
  <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
  <!-- Custom styles for this template -->
  <link href="${pageContext.request.contextPath}/css/moon.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
  <link href="${pageContext.request.contextPath}/css/style-responsive.css" rel="stylesheet">
  <!-- jQuery CDN-->
  <script
   src="https://code.jquery.com/jquery-1.9.0.js"
   integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
   crossorigin="anonymous"></script>
  <!-- Web socket CDN -->
  <script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
  
  <!-- js placed at the end of the document so the pages load faster -->
  <script src="${pageContext.request.contextPath}/lib/jquery/jquery.min.js" ></script>
  <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
  <script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
  <!--common script for all pages-->
  <!--script for this page-->
  
  <script>
    var getUrl = window.location;
    var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
 	var newBuzzingCount = 0;
 	var newBuzzings = 0;
 	var startNo = 2;
 	var uploadFiles = new Array();
 	var savedFileNo = new Array();
 	var username = "${loginDTO.username}";
    var index = 0;
 	 
	//Web Socket js 
	//웹소켓을 지정한 url로 연결한다.
	let sock = new SockJS("<c:url value='/echo/echo'/>");
	sock.onmessage = onMessage;
	sock.onclose = onClose;
	
	function sendMessage(id) {
	   var msg = "${loginDTO.m_name}:" + $(id).val() + "[FILES]";
	   if(uploadFiles.length != 0) {
		   upload();
		   for(var i = 0; i < savedFileNo.length; i++)  msg += savedFileNo[i] + "#";
	   }
	   sock.send(msg);
	   savedFileNo = [];
	   $("#buzzingCount").html(parseInt($("#buzzingCount").html()) + 1);
	}
	
	function sendMessageReply(id, b_no, toWhom) {
	   var msg = "${loginDTO.m_name}:" + toWhom +' ' + $(id).val() + "[FILES]";
	   if(uploadFiles.length != 0) {
		   upload();
		   for(var i = 0; i < savedFileNo.length; i++)  msg += savedFileNo[i] + "#";
	   }
	   sock.send('#' + b_no + '$' + msg);
	   savedFileNo = [];
	   $("#buzzingCount").html(parseInt($("#buzzingCount").html()) + 1);
	}
	
	// 서버로부터 메시지를 받았을 때
	function onMessage(msg) {
		$("#empty-buzzing").remove();
		var data = msg.data;
		id = data.substring(0, data.indexOf("/"));
		name = data.substring(data.indexOf("/") + 1, data.indexOf(":"));
	    msg = data.substring(data.indexOf(":") + 1, data.lastIndexOf("$"));
	    
		if(msg.search("@" + id) == -1) return;
			
		var files = data.substring(data.lastIndexOf("$") + 1).split("#");
	    boardNo = files[0];
		newBuzzingCount += 1;
		newBuzzings += 1;
		
		var request = $.ajax({url:baseUrl  + "/ajax/getProfile", async:false, method:"GET", data:{m_id:id}, dataType:"text", 
		  success: function () {},
	      error: function (jqXHR) {
	        alert(jqXHR.status);
	        alert(jqXHR.statusText);
	        alert(jqXHR.responseText);
	        alert(jqXHR.readyState);
	      }});
		 request.done(function(data){
		 	profile = data;
	 	 });
		 
		 var isAdmin = '<li onclick="deleteBoard('+boardNo+')">게시물 삭제</li>';
		 if(username != id) isAdmin = '<li onclick="blockUser(\''+id+'\', 0)">유저 차단</li><li>게시물 가리기</li>';
		 
	 	 var htmlNew = '<div id="newBuzzing" onclick="showNewBuzzing()" style="display:none">새로운 버징 '+ newBuzzingCount +'개 보기</div>';
  		 var html = '<div class="media lists new" id="lists'+boardNo+'" style="display:none">'+
				       '<a class="media-left" href="#fake">'+
				           '<img alt="" class="content-profile media-object img-circle" src="${pageContext.request.contextPath}/img/'+profile+'">'+
				       '</a>'+
				       '<div class="media-body" onclick="contentPopUp('+boardNo+')">'+
				            '<h4 class="media-heading">' + name + ' <a href="' + id + '">@' + id + '</a><span class="board-date"> · 방금</span></h4><span id="content-'+boardNo+'" style="white-space: pre-line;">' + msg + '<br/><br/></span>'+
				            '<div class="twitter-card-content-board">'+
						     '<div class="twitter-card-icons-board">'+
						     	'<a class="twitter-card-icon comment tooltips">'+
						            '<i class="fa fa-comment-o"></i>'+
						        '</a>'+
						        '<a class="twitter-card-icon heart tooltips" id="board-likes'+boardNo+'">'+
						            '<i class="fa fa-heart-o like-button" id="heart'+boardNo+'" onclick="sideControl(event, '+boardNo+', \'ajax/likes\', \'#side-likes\', \'#likeCount\')"></i>'+
						            '<font class="font-bug" id="likeCount'+boardNo+'">0</font>'+
						        '</a>'+
						        '<li class="navi_set glyphicon glyphicon-option-horizontal topnav" onclick="dropMenu('+boardNo+')">'+  
								   '<ul class="subnav" id="subnav'+boardNo+'">'+
                			      	  isAdmin+
								   '</ul>'+  
								'</li>'+
						    '</div>'+
				       '</div>'+
				       '</div>'+
				  '</div>';
		$("#buz").prepend(html);
		$("#newBuzzing").remove();
		$("#buz").prepend(htmlNew);
		
		$('title').text('('+newBuzzingCount+') 버즈비');
		
		if(files[1] != "") {
			for(var i = 1; i < files.length; i++) {
				if(files[i].substring(files[i].lastIndexOf(".") + 1) == 'mp4') {
					$("#content-" + boardNo).append('<video src="'+baseUrl+'/store/'+files[i]+'" class="videoOne" controls></video>');
				} else {
					if(files.length == 2) $("#content-" + boardNo).append('<img src='+baseUrl+'"/store/'+files[i]+'" class="imgOne" onclick="doImgPop(this.src, event);">');
					else $("#content-" + boardNo).append('<img src="$'+baseUrl+'/store/'+files[i]+'" class="imgList" onclick="doImgPop(this.src, event);">');
				}
			}	
		}

		if(newBuzzingCount == 1 && !(id == "${loginDTO.username}")) {
			$("#newBuzzing").slideDown("slow");
			$('title').text('(1) 버즈비');
		} else {
			if(id == "${loginDTO.username}") showNewBuzzing();
			else {
				$("#newBuzzing").css("display", "block");
			}
		}
	}

	// 서버와 연결을 끊었을 때
	function onClose(evt) {
		$("#buz").append("서버 오류");
	}
</script>
<script src='${pageContext.request.contextPath}/lib/moon_notice.js'></script>
</head>

<body>
  <div class='layer'>
    <div id='header-back'></div>
  <span class='content'>
  <section id="container">
    <!-- *********************************************************************************************************************************************************
        TOP BAR CONTENT & NOTIFICATIONS
        *********************************************************************************************************************************************************** -->
    <!--header start-->
    <!-- Fixed top navbar -->
	<nav class="navbar navbar-toggleable-md fixed-top" id='board-header'>
	  	<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
	        <span class="navbar-toggler-icon"></span>
	    </button>
	    
	<div id='top-logo'>
	<!--logo start-->
	    <a href="${pageContext.request.contextPath}" class="logo"><b style="color:#404040">Buz<span style="color:rgb(251, 208, 67)">bee</span></b></a>
	<!--logo end-->
	</div>
	
	<!--  버즈비 로고 -->
	   	<a href='${pageContext.request.contextPath}'><div id="logo"><img src="${pageContext.request.contextPath}/img/fi.png"></div></a>
	<!--  버즈비 로고 -->
	
	  <div class="collapse navbar-collapse container" id='header-notice'>
	    <!-- Navbar navigation links -->
	    <ul class="navbar-nav mr-auto" id='menus'>
	      <li class="nav-item active">
	      <li class="nav-item">
	        <a class="nav-link" href=""><i class="octicon octicon-bell"></i>  알림</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-link" href="setting"><i class="glyphicon glyphicon-cog"></i> 설정</a>
	      </li>
	    </ul>
	    <!-- END: Navbar navigation links -->
	
	    <!-- 검색창 -->
	    <form class="navbar-form" role="search" action=''>
	      <div class="input-group">
	        <input type="text" class="form-control input-search" placeholder="버즈비 검색" name="srch-term" id="srch-term">
	        <div class="input-group-btn">
	          <button class="btn btn-default btn-search" type="submit"><i class="octicon octicon-search navbar-search-icon"></i></button>
	        </div>
	      </div>
	    </form>
	    <!-- END: 검색창 -->
	    
	    <!-- 버징 버튼 -->
	    <button class="btn btn-search-bar" id='buzzingBtn'>버징하기</button>
	  </div>
	</nav>
	
    <!-- Modal -->
	<div class="modal fade" id="buzzingModal" >
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <!-- header -->
	      <div class="modal-header" style='background:#ffe180'>
	        <!-- 닫기(x) 버튼 -->
 <button type="button" class="close" data-dismiss="modal">×</button>
	        <!-- header title -->
	        <h3 class="modal-title text-center" style='font-weight:bold;color:black'>새로운 버징 작성</h3>
	      </div>
	      <!-- body -->
	      <div class="modal-body">
	      	<textarea class='form-control' id='modal-text' style='font-size:1.5rem' rows='5' placeholder='무슨 일이 일어나고 있나요?'></textarea>
	      	<div class="img-area-modal" style='display:flex;background-color:#fff3e6;margin-top:5px'></div>
	      </div>
	      <!-- Footer -->
	      <div class="modal-footer">
	      	<input type='file' id='file' multiple='multiple' onchange='readURL(this, ".img-area-modal")'/>
	        <span id='modal-textCount'>0</span>/140&nbsp;
	        <button class="btn btn-search-bar disabled" id='modal-sendBtn' data-dismiss="modal">버징하기</button>
	      </div>
	    </div>
	  </div>
	</div>
	<script>
		$(function(){
	        $('#buzzingModal').on('hidden.bs.modal', function (e) {
	        	$('#modal-text').val('');
	        	$(".img-area-main").show();
	        	$('#modal-sendBtn').attr("class", "btn btn-search-bar disabled");
	        	$('#modal-textCount').html(0);
	        	uploadFiles = [];
	        });
	        $("#buzzingBtn").click(function(){
	        	$("#appended").remove();
	        	$(".img-area-main").hide();
	        	$('.foo').remove();
	        	$('#search2').attr("rows", "1");
	        	$('#search2').val('');
	        	uploadFiles = [];
	        	$('#buzzingModal').modal();
	            $('#modal-text').focus();
	        })
	    })
    </script>
	<!-- Modal -->

    <!--header end-->
    <!-- **********************************************************************************************************************************************************
        MAIN SIDEBAR MENU
        *********************************************************************************************************************************************************** -->
    <!--sidebar start-->
    
     <aside>
      <div id="sidebar" class="nav-collapse" tabindex='1'>
        <!-- sidebar menu start-->
        <ul class="sidebar-menu" id="nav-accordion">
          <p class="centered"><a href="../${loginDTO.username}"><img src="${pageContext.request.contextPath}/img/${profile}" alt='프사없음' class="img-circle" width='180'></a></p>
          <h5 class="centered" style="color:#404040; font-weight:bold">${loginDTO.m_name}</h5>
          <a href='${loginDTO.username}'><h5 class="centered" style="color:#b3b3b3">@${loginDTO.username}</h5></a>
          <li class="mt">
            <a href="${loginDTO.username}">
              <i class="fa fa-Buzzing"></i>
              <span style="color:#404040; font-weight:bold">버징</span>
              <span class="label label-theme pull-right mail-info" id='buzzingCount'>${etc.buzzes}</span>
              </a>
          </li>
          
          <li class="mt">
            <a href="following">
              <i class="fa fa-Buzzing"></i>
              <span style="color:#404040; font-weight:bold">팔로잉</span>
              <span class="label label-theme pull-right mail-info">${etc.following}</span>
              </a>
          </li>
          
           <li class="mt">
            <a href="follow">
              <i class="fa fa-Buzzing"></i>
              <span style="color:#404040; font-weight:bold">팔로워</span>
              <span class="label label-theme pull-right mail-info">${etc.follower}</span>
              </a>
          </li>
          
           <li class="mt">
            <a href="like">
              <i class="fa fa-Buzzing"></i>
              <span style="color:#404040; font-weight:bold">좋아요</span>
              <span class="label label-theme pull-right mail-info" id='side-likes'>${etc.likes}</span>
              </a>
          </li>          

          <li class="mt">
            <a href="<c:url value="../m/logout"/>">
              <i class="fa fa-Buzzing"></i>
              <span style="color:#404040; font-weight:bold">로그아웃</span>
              </a>
          </li>
        <!-- sidebar menu end-->
      </div>
           </center>
    </aside>
    <!--sidebar end-->
    <!-- **********************************************************************************************************************************************************
        MAIN 
        *********************************************************************************************************************************************************** -->
    <!--ë ´ì ©-->

    <section id="main-content">
      <section class="wrapper site-min-height" id='section-body'>
      <div id='content-area'>
      <div class="col-lg-9" id='timeline'>
         <div class="panel panel-info" id='content-border'>
            <div class="panel-heading" id='input-form' style='padding:13px;padding-left:15px;font-size:2rem;color:black;font-weight:bold;background-color:#fff3e6'>
               <span class='octicon octicon-bell'></span>알림
            </div>
            <div class="panel-body" id='buz'>
            <c:if test='${empty buzzing}'>
               <div id='empty-buzzing' class='text-center'>알림이 없습니다</div>
            </c:if>
            <c:forEach items='${buzzing}' var='buzz'>
               <div class="media lists" id='lists${buzz.b_no}'>
                  <a class="media-left" href="${buzz.m_id}">
                     <img alt="profile-image" class="content-profile media-object img-circle" src="${pageContext.request.contextPath}/img/${buzz.img_profile}">
                  </a>
                  <div class="media-body" onclick='contentPopUp(${buzz.b_no})'>
                     <h4 class="media-heading">${buzz.m_name} <a href='${buzz.m_id}'>@${buzz.m_id}</a><span class='board-date'> · ${buzz.b_rdate}</span></h4>
                     <span style="white-space: pre-line;">${buzz.b_content}</span><br/><br/>
                     	<c:if test='${not empty buzz.f_saveFiles}'>
                     		<c:if test='${buzz.f_saveFiles.size() == 1}'>
                     			<c:if test='${buzz.f_saveFiles.get(0).endsWith("mp4")}'>
                     				<video src="${pageContext.request.contextPath}/store/${buzz.f_saveFiles.get(0)}" class='videoOne' controls></video>
                     			</c:if>
                     			<c:if test='${!buzz.f_saveFiles.get(0).endsWith("mp4")}'>
                     				<img src="${pageContext.request.contextPath}/store/${buzz.f_saveFiles.get(0)}" class='imgOne' onclick='doImgPop(this.src, event);'>
                     			</c:if>
                     		</c:if>
                     		<c:if test='${buzz.f_saveFiles.size() != 1}'>
                     		<c:forEach items='${buzz.f_saveFiles}' var='file'>
                     			<img src="${pageContext.request.contextPath}/store/${file}" class='imgList' onclick='doImgPop(this.src, event);'>
                     		</c:forEach>
                     		</c:if>
                     	</c:if>
	                     <div class="twitter-card-content-board">
					     <div class="twitter-card-icons-board">
					     	<a class="twitter-card-icon comment tooltips">
					            <i class="fa fa-comment-o"></i>
					        </a>
					        <a class="twitter-card-icon heart tooltips" id='board-likes${buzz.b_no}'>
					        	<c:if test='${buzz.board_like}'>
					           		<i class="fa fa-heart like-button liked liked-shaked" id='heart${buzz.b_no}' onclick='sideControl(event, ${buzz.b_no}, "ajax/likes", "#side-likes", "#likeCount")'></i>
					            </c:if>
					            <c:if test='${!buzz.board_like}'>
					           		<i class="fa fa-heart-o like-button" id='heart${buzz.b_no}' onclick='sideControl(event, ${buzz.b_no}, "ajax/likes", "#side-likes", "#likeCount")'></i>
					            </c:if>
					            <font id='likeCount${buzz.b_no}'>${buzz.b_like}</font>
					        </a> 
			            	<li class="navi_set glyphicon glyphicon-option-horizontal topnav" onclick="dropMenu(${buzz.b_no})">  
							   <ul class="subnav" id='subnav${buzz.b_no}'>
							   	  <c:if test='${loginDTO.m_no == buzz.m_no}'>  
							      	<li onclick="deleteBoard(${buzz.b_no})">게시물 삭제</li>
							      </c:if>  
							      <c:if test='${loginDTO.m_no != buzz.m_no}'> 
							      	<li onclick="blockUser('${buzz.m_id}', ${buzz.m_no})">유저 차단</li>
							      	<li>게시물 가리기</li>
							      </c:if>
							   </ul>  
							</li>
					    </div>
				      	</div>
	              </div>
               </div>
            </c:forEach>
            <div id='ajax-appended'></div>
            </div>
         </div>
         </div>
      </div>
      
	<!-- Content Modal -->
	<div class="modal fade" id="content-modal"  data-toggle='modal'>
      <button type="button" class="close" data-dismiss="modal" id='content-modal-btn'>×</button>
      <div class='modal-area'>
		  <div class="twitter-card" onclick='contentZoom(0)'>
	      <input type='hidden' class='modal-bno'/>
	      <div class="twitter-card-header">
	        <img class="twitter-card-user-img img-circle" src=""/>
	        <span class="twitter-card-user-name"></span>
			<button class="btn-Hyeong btn-primary-Hyeong btn-follow" id='followBtn' onclick='following(0)'>
				<span class="follow"><i class="fa fa-user-plus"></i> 팔로우</span>
				<span class="unfollow">언팔로우</span>
				<span class="following">팔로잉</span>
			</button>
	        <span class="twitter-card-user-name-2"></span>
	      </div>
	
	      <div class="twitter-card-text" style="white-space: pre-line;">
	      </div>
	         <small class='content-date'></small>
	      <div class="twitter-card-content">
	      <div class="twitter-card-icons">
	          <a href="#" class="twitter-card-icon comment tooltips">
	            <span>댓글</span>
	            <i class="fa fa-comment-o"></i>
	          </a>
	          <a class="twitter-card-icon heart tooltips" >
	            <span>좋아요</span>
	            <i class="fa fa-heart-o like-button content-modal" onclick='sideControlModal(event, $(".modal-bno").eq(0).val(), "ajax/likes", "#side-likes", ".likeCount-modal", 0);'></i>
	            <font class='likeCount-modal'></font>
	          </a>
	          <a class="twitter-card-icon message tooltips">
	            <span>메세지</span>
	            <i class="fa fa-envelope"></i>
	          </a>
	      </div>
	      </div>
	  </div>
      <div class='ajax-appended-content'></div>
	  <div class='to-whom'></div>
      <div class="twitter-card-comment"></div>
		<div class="twitter-footer">
			<img class="twitter-small-avatar" src="${pageContext.request.contextPath}/img/${profile}">
			<textarea class='form-control' id='append-content' rows='1' placeholder='내 답글을 버징합니다'></textarea>
	    </div>
	    <div class="modal-footer" id='content-footer'>
			<div class="img-area-modal" style='display:flex;background-color:white;margin-top:5px;margin-left:10px;margin-bottom:10px;margin-right:10px;border-radius:10px'></div>
    		<input type='file' id='file' multiple='multiple' onchange='readURL(this, ".img-area-modal")'/>
        	<span id='modal-textCount-content'>0</span>/140&nbsp;
        	<button class="btn btn-search-bar disabled" id='modal-sendBtn-content' data-dismiss="modal">버징하기</button>
        </div>
        </div>
	</div>
	<!-- Content Modal -->

	<!-- Image Modal -->
	<div class="modal fade" data-toggle='modal' id="image-modal" onclick='$(this).modal("hide");'>
       <!-- body -->
       <div class="modal-body">
             <img id='modal-img' src='' style='display: block; margin: 0px auto;max-width:950px;max-height:700px'/>
       </div>
	</div>
	<!-- Image Modal -->

      <div class="col-xs-3" id='side-nav'>
         <div class="panel panel-default panel-custom">
            <div class="panel-heading">
               <h3 class="panel-title">
                  팔로우 추천
                  <small>who follows you</small>
               </h3>
            </div>
            <div class="panel-body">
            <c:if test='${empty recommend}'>
            	<div style='text-align:center'>추천 사용자가 없습니다.</div>
            </c:if>
            <c:forEach items='${recommend}' var='member'>
               <div class="media recommend-body" id='recommend${member.m_no}'>
                  <div class="media-left">
                     <img src="${pageContext.request.contextPath}/img/${member.profile_name}" alt="" class="media-object img-circle recommend">
                  </div>
                  <div class="media-body">
                     <h4 class="media-heading">${member.m_name}</h4>
                     <a href='${member.m_id}'>@${member.m_id}</a>
                     <a href="javascript:follow(${member.m_no})" class="btn btn-default btn-xs">
                        +
                        <span class="glyphicon glyphicon-user"></span>
                        Follow
                     </a>
                  </div>
               </div>
            </c:forEach>
            </div>
         </div>
         
         <!--  트렌드 창 -->
         <div class="col-xs-34" id='side-nav'>
         <div class="panel panel-default panel-custom">
            <div class="panel-heading">
               <h3 class="panel-title">
                 버즈비 트렌드
                 <small>Buzbee Trend</small>
               </h3>
            </div>
            <div class="panel-body">
            	<c:if test='${not empty trends}'>
	            	<c:forEach items='${trends}' var='trend'>
	                	<a class='trend-content'>#${trend.t_word}</a><br>
	                	<span class='trend-count'>${trend.t_count} 버징</span><br>
	            	</c:forEach>
            	</c:if>
            	<c:if test='${empty trends}'>
            		현재 분석된 트렌드가 없습니다
            	</c:if>
            </div>
         </div>
         <!--  트렌드 창 -->
         
         <div class="well well-sm">
            <ul class="list-inline">
               <li>© 2019 Buzbee</li>
               <li><a href="#">About</a></li>
            </ul>
         </div>
      </div>        
        </div>
        <button class="topButton" type="button" onclick="$('html').animate({scrollTop : 0})"></button>
      </section>
      <!-- /wrapper -->
    </section>
    <!-- /MAIN ë ´ì © -->
    <!--main content end-->
	
  </section>
  <span class="blank"></span>
  </span>
  </div>
  <script src="${pageContext.request.contextPath}/lib/hyeong.js"></script>
</body>

</html>