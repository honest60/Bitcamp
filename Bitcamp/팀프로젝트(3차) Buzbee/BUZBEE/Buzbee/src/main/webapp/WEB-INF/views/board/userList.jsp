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

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />

<link rel="stylesheet" href="css/cham.css">
<link rel='stylesheet' href='css/hyeong.css'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/octicons/4.4.0/font/octicons.min.css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">

<script src="lib/jquery/jquery.min.js" ></script>
<!-- jQuery CDN-->
  
  <!-- Web socket CDN -->
  <script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
  
  <!-- Web Socket js -->
	<script type="text/javascript">			
		 var newBuzzingCount = 0;
		 var newBuzzings = 0;
		 var startNo = 2;
	 	 var uploadFiles = new Array();
	 	 var savedFileNo = new Array();
	 	 var id = "${loginDTO.username}";
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

	 	// 서버로부터 메시지를 받았을 때
	 	function onMessage(msg) {
	 		$("#empty-buzzing").remove();
	 		var data = msg.data;
	 		id = data.substring(0, data.indexOf("/"));
	 		name = data.substring(data.indexOf("/") + 1, data.indexOf(":"));
	 	    msg = data.substring(data.indexOf(":") + 1, data.lastIndexOf("$"));
	 		var files = data.substring(data.lastIndexOf("$") + 1).split("#");
	 	    boardNo = files[0];
	 		newBuzzingCount += 1;
	 		newBuzzings += 1;
	 		
	 		if(id != "${loginDTO.username}"){
	 			return;
	 		}
	 	
	 		var request = $.ajax({url:"ajax/getProfile", async:false, method:"GET", data:{m_id:id}, dataType:"text", 
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
	 			
	 		var html ='<div class="content">'+
			 		  '<a href="#fake">'+
			 		  '<img alt="" class="content-img" src="resources/img/'+profile+'">'+			 		 
			 		  '</a>'+  			  
	 				  '<div class="content-line" onclick="contentPopUp('+boardNo+')">'+  
	 				  '<div>'+	
	 				  '<ul>'+
	 				  '<li class="content-head">' + name + '</li>'+			 
	 				  '<li class="content-head"><a href="' + id + '">@' + id + '</a><span class="board-date"> · 방금</span></li> '+
	 				  '<ul>'+
	 				  '</div>'+	
	 				  '<div class="timeline-center">'+
	 				  '<span id="content-'+boardNo+'"><p class="content-left">' + msg + '</p><br/></span>'+	 
	 				  '</div>' + 
	 				  '<div class="twitter-card-content-board">'+
					     '<div class="twitter-card-icons-board">'+
					     	'<a class="twitter-card-icon comment tooltips">'+
					            '<i class="fa fa-comment-o"></i>'+
					        '</a>'+
					        '<a class="twitter-card-icon retweet tooltips" id="board-rebuz'+boardNo+'">'+
					            '<i id="rebuz-modal" class="fa fa-retweet" onclick="sideControl(event, '+boardNo+', \'ajax/rebuz\', null, \'#rebuzCount\')"></i>'+
					            '<font class="font-bug" id="rebuzCount'+boardNo+'">0</font>'+
					        '</a>'+
					        '<a class="twitter-card-icon heart tooltips" id="board-likes'+boardNo+'">'+
					            '<i class="fa fa-heart-o like-button" id="heart'+boardNo+'" onclick="sideControl(event, '+boardNo+', \'ajax/likes\', \'#side-likes\', \'#likeCount\')"></i>'+
					            '<font class="font-bug" id="likeCount'+boardNo+'">0</font>'+
					        '</a>'+
					        '<span class="glyphicon glyphicon-option-horizontal">'+
					        '</span>'+
					    '</div>'+
			          '</div>';
	 				  
	 		$("#buz").prepend(html);
	 		$("#newBuzzing").remove();
	 		
	 		if(files[1] != "") {
				for(var i = 1; i < files.length; i++) {
					if(files[i].substring(files[i].lastIndexOf(".") + 1) == 'mp4') {
						$("#content-" + boardNo).append('<video src="store/'+files[i]+'" class="videoOne" controls></video>');
					} else {
						if(files.length == 2) $("#content-" + boardNo).append('<img src="store/'+files[i]+'" class="imgOne" onclick="doImgPop(this.src, event);">');
						else $("#content-" + boardNo).append('<img src="store/'+files[i]+'" class="imgList" onclick="doImgPop(this.src, event);">');
					}
				}	
			}
	 		
	 		$('title').text('('+newBuzzingCount+') 버즈비');	 		
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
	 	
	 	let isEnd = false;
	</script>
</head>

<body>
	<header>		
		<div class="head">		
			<div class="head-part">						
				<a class="logo" id="modal-img" href="<c:url value='/'/>">
				<p class="logo-head">Buz</p>
		  		<p class="logo-tail">bee</p>
				<img class="head-right" src="img/fi.png">
				</a>				
				<a id="head-sm-left" class="head-left" href="#"><i class="octicon octicon-bell"></i>알림</a>
				<a id="head-sm-left2" class="head-right" href="#"><i class="glyphicon glyphicon-cog"></i>쪽지</a>
				<nav class="nav">		
					<ul>
					<li>
					<form action="search" method="post">
						<input type="text" class="search-bar" id="search-bar" name="search" placeholder=" 버즈비 검색"/>
		          		<button class="search" id="search" onclick="submit"><i class="octicon octicon-search navbar-search-icon"></i></button>
		          	</form>				          		       
						<a href="<c:url value='/${member.m_id}'/>">
							<button class="header-profile"></button>	
						</a>	
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
			    <div onclick="history.back();" class="menuClose"></div>
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
			<img class="main-img" src="img/Businessorganddeclutter-2048x500.jpg">
			<div class="profile-location">
				<div class="profile-size">
					<p>				  
					 <img src="https://myanimals.co.kr/wp-content/uploads/2018/09/russian-blue-cat.jpg" class="img-circle">				  
				   </p>
				</div>		   
			</div>
		</div>	
		
		<div class="main-bar">
			<div class="hidden-part">				
				<div class="hidden-profile">
					<a class="hidden-a" onclick="myFunction()">
						 <img src="https://myanimals.co.kr/wp-content/uploads/2018/09/russian-blue-cat.jpg" class="hidden-img">		
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
					<li class="search-menu">
						<a href="search">인기</a>
					</li>
			  		<li class="search-menu">
			  			<a href="newList">최신</a>
			  		</li>
			  		<li class="search-menu">
			  			<a href="userList">사용자</a>
			  		</li>
			  		<li class="search-menu">
			  			<a href="LikeList">좋아요</a>
			  		</li>
				
					<li class="menu-li">
						<a href="#">
						<button class="menu-edit">설정</button> 	
						</a>
					</li>
				</ul>					
			 </div>	
		</div>
	</div>
	<div class="subject">
		<ul class="subject-ul">
			<li class="subject-li">
				<p class="subject-p">인기있는</p>
			</li>
		</ul>
	</div>
	<div class="body" id="body-md">
	  <div class="sidebar-padding" id="sidebar-md">		  			
	  </div>
		
	  <div class="main">
			<!-- disabla 관련 추가 start-->
			<div id="disableDiv" style="display:none"></div>
			<!-- disabla 관련 추가 end-->		
			
			 <div id="col-sm-content" class="timeline">	
				<div>				
					<div id="buz">
					<c:if test="${empty buzzing}">
						<div id='empty-buzzing'>아직 작성한 버징이 없습니다. 새로운 버징을 작성해주세요!</div>
					</c:if>
					<c:forEach items="${buzzing}" var="buzz">
					   <div class="content">
						  <a href="#fake">
							 <img alt="" class="content-img" src="https://myanimals.co.kr/wp-content/uploads/2018/09/russian-blue-cat.jpg">
						  </a>
						  <div class="content-line" onclick="contentPopUp(${buzz.b_no})">
							 <div>
							 	<ul>
							 		<li class="content-head">${buzz.m_name}</li>
							 		<li class="content-head"><a href="${buzz.m_id}">@${buzz.m_id}</a></li>
							 	</ul>								 
							 </div>										  
							 <span><p class="content-left">${buzz.b_content}</p></span>
							 <br>
							 <c:if test='${not empty buzz.f_saveFiles}'>
	                     		<c:if test='${buzz.f_saveFiles.size() == 1}'>
	                     			<c:if test='${buzz.f_saveFiles.get(0).endsWith("mp4")}'>
	                     				<div class="timeline-center">
	                     					<video src="store/${buzz.f_saveFiles.get(0)}" class='videoOne' controls></video>
	                     				</div>	                     				
	                     			</c:if>
	                     			<c:if test='${!buzz.f_saveFiles.get(0).endsWith("mp4")}'>
	                     				<div class="timeline-center">
	                     					<img src="store/${buzz.f_saveFiles.get(0)}" class='imgOne' onclick='doImgPop(this.src, event);'>
	                     				</div>	                     				
	                     			</c:if>
		                     	</c:if>
	                     		<c:if test='${buzz.f_saveFiles.size() != 1}'>
	                     			<div class="timeline-center">
	                     		<c:forEach items='${buzz.f_saveFiles}' var='file'>	                     			
                     				<img src="store/${file}" class='imgList' onclick='doImgPop(this.src, event);'>	                     			                    			
	                     		</c:forEach>
	                     			</div>
	                     		</c:if>
	                     	 </c:if>
	                     	 
							 <div class="twitter-card-content-board" id="subject">
							     <div class="twitter-card-icons-board">
							     	<a class="twitter-card-icon comment tooltips">
							            <i class="fa fa-comment-o"></i>
							        </a>
							        <a class="twitter-card-icon retweet tooltips" id='board-rebuz${buzz.b_no}'>
							            <i id='rebuz-modal' class="fa fa-retweet" onclick='sideControl(event, ${buzz.b_no}, "ajax/rebuz", null, "#rebuzCount")'></i>
							            <font id='rebuzCount${buzz.b_no}'>${buzz.b_rebuz}</font>
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
							   	  </div>
						      </div>
						  </div>
					   </div>
					</c:forEach>
					</div>					
				</div>		
			  </div>
			  <button class="topButton" type="button" onclick="$('html').animate({scrollTop : 0})"></button>
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
		    <a class="twitter-card-icon retweet tooltips">
		      <span>리버징</span>
		      <i id='rebuz-modal' class="fa fa-retweet"></i>
		      <font class='rebuzCount-modal'></font> 
		    </a>
		    <a class="twitter-card-icon heart tooltips" >
		      <span>좋아요</span>
		      <i class="fa fa-heart-o like-button content-modal" onclick='sideControlModal(event, $(".modal-bno").eq(0).val(), "ajax/likes", "#side-likes", ".likeCount-modal", 0);'></i>
		           <font class='likeCount-modal'></font>
		         </a>
		         <span class="glyphicon glyphicon-option-horizontal">
		         </span>
		     </div>
		     </div>
		 </div>
		    <div class='ajax-appended-content'></div>
		 <div class='to-whom'></div>
		    <div class="twitter-card-comment"></div>
		<div class="twitter-footer">
			<img class="twitter-small-avatar" src="resources/img/${profile}">
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
	  	
	  <div class="trend">
		<h4 class="panel-title">
			버즈비 추천
			<br><small><a href="#">Refresh</a> â <a href="#">View all</a></small>
		</h4>
		<div class="buzbee-recommend">
			<div>				
				<img class="trend-img" src="http://placehold.it/32x32">
				<div>
					<h5>Nome e cognome</h5>
					<a href="#">
						+
						<span></span>
						Follow
					</a>
				</div>
			</div>
			<div>				
				<img class="trend-img" src="http://placehold.it/32x32">				
				<div>
					<h5>Nome e cognome</h5>
					<a href="#">
						+
						<span></span>
						Follow
					</a>
				</div>
			</div>
			<div>				
				<img class="trend-img" src="http://placehold.it/32x32">				
				<div>
					<h5>Nome e cognome</h5>
					<a href="#">
						+
						<span></span>
						Follow
					</a>
				</div>
			</div>
		</div>	
		<div>			
		   <h4 class="panel-title">
			  Trend Window  
			 <!-- <small><a href="#">Refresh</a> â   <a href="#">View all</a></small>-->
			 <br><small>트렌드 창 부분 설명</small>
		   </h4>
		   <div class="panel-body">
		     <a>창 길어지는 거 실험하기</a><br>
		     <a>창 길어지는 거 실험하기</a><br>
		     <a>창 길어지는 거 실험하기</a><br>	   
		   </div>
		</div>	 	
	  </div>
	</div>		
</body>	
<!-- js placed at the end of the document so the pages load faster -->

<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
<script
	src="https://code.jquery.com/jquery-1.9.0.js"
	integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
	crossorigin="anonymous"></script>
<!--common script for all pages-->
<script src="lib/modal.js"></script>
<script src="lib/profile.js"></script>
<!--script for this page-->

</html>
