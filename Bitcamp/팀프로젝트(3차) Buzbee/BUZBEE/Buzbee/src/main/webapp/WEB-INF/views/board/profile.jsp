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

<link href="img/apple-touch-icon.png" rel="apple-touch-icon">

<link rel='stylesheet' href='lib/bootstrap/css/twitter-bootstrap.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/octicons/4.4.0/font/octicons.min.css'>
<link rel='stylesheet' href='css/hyeong.css'>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">
<link href="lib/font-awesome/css/font-awesome.css" rel="stylesheet" />
<link rel="stylesheet" href="css/cham.css">

<!--  script -->
<script
	src="https://code.jquery.com/jquery-1.9.0.js"
	integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
	crossorigin="anonymous"></script>
<script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
<script src="lib/jquery/jquery.min.js" ></script>
<script src="lib/bootstrap/js/bootstrap.min.js"></script>
<script class="include" type="text/javascript" src="lib/jquery.dcjqaccordion.2.7.js"></script>
<!--common script for all pages-->
 
	<script type="text/javascript">
		var getUrl = window.location;
	    var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];
	 	var newBuzzingCount = 0;
	 	var newBuzzings = 0;
	 	var startNo = 2;
	 	var uploadFiles = new Array();
	 	var savedFileNo = new Array();
	 	var username = "${loginDTO.username}";
	    var index = 0;
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
	 	   var m_no = "${member.m_no}";
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
	 				  '<li class="content-tail"><a class="font-color" href="' + id + '">@' + id + '</a><span class="board-date"> · 방금</span></li> '+
	 				  '<ul>'+
	 				  '</div>'+	
	 				  '<div class="timeline-center">'+
	 				  '<span id="content-'+boardNo+'"><p class="content-left">' + msg + '</p><br/></span>'+	 
	 				  '</div>' + 
	 				  '<div id="icon-padding" class="twitter-card-content-board">'+
					     '<div class="twitter-card-icons-board">'+
					     	'<a class="twitter-card-icon comment tooltips">'+
					            '<i class="fa fa-comment-o"></i>'+
					        '</a>'+
					        '<a class="twitter-card-icon heart tooltips" id="board-likes'+boardNo+'">'+
					            '<i class="fa fa-heart-o like-button" id="heart'+boardNo+'" onclick="sideControl(event, '+boardNo+', \'ajax/likes\', \'#side-likes\', \'#likeCount\')"></i>'+
					            '<font class="font-bug" id="likeCount'+boardNo+'">0</font>'+
					        '</a>'+
					        '<a class="twitter-card-icon message tooltips" class="dropdown-toggle" data-toggle="dropdown">'+
					        '<i class="glyphicon glyphicon-option-horizontal"></i>'+
					        '</a>'+
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
				<a id="head-sm-left" class="head-head" href="board/notice"><i class="octicon octicon-bell" id="head-detail"></i>알림</a>
				<a id="head-sm-left2" class="head-tail" href="board/setting"><i class="glyphicon glyphicon-cog" id="head-detail"></i>설정</a>
				<nav class="nav">		
					<ul>
					<li>
					<form action="search" method="post">
						<input type="text" class="search-bar" id="search-bar" name="search" placeholder=" 버즈비 검색"/>
		          		<button class="search" id="search" onclick="submit"><i id="icon-height" class="octicon octicon-search navbar-search-icon"></i></button>
		          	</form>				          		       
		<!-- slide-menu start -->
			<div onclick="history.back();" class="page_cover"></div>
			<div id="slideMenu">
				<div class="slideHeader">
					<a href="<c:url value='/${loginDTO.username}'/>">
						<img src="resources/img/${profile}" class="slide-img">   
						<div class="text-head">${loginDTO.m_name}</div>
					</a>
					<div class="text-tail">${loginDTO.username}</div>
				</div>
				<div class="slideList">
						<p class="modalMension" id="slideText">멘션 알림</p>
						<p class="modalOption" id="slideText">설정</p>
					<a href="<c:url value="m/logout"/>">
						<p class="modalLogout" id="slideText">로그아웃</p>
					</a>
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
				    <div class="modal-footer" id="modal-fontSize">
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
				        	$('.foo').remove();				        	
				        	uploadFiles = [];
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
			<img class="main-img" src="resources/img/${headerImg}">
		</div>	
		
		<div class="main-bar">
			<div class="hidden-part">				
				<div class="hidden-profile">
					<a class="hidden-a">
						 <img src="resources/img/${profile}" class="hidden-img">		
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
						<a href="board/following">
						<span class="menu-title">팔로잉</span> 
						<span class="sidebar-tail">${etc.following}</span>
						</a>
					</li>					
					<li>
						<a href="board/follow">				  
						<span class="menu-title">팔로워</span>
						<span class="sidebar-tail">${etc.follower}</span>
						</a>
					 </li>					
					<li>
						<a href="board/like">
						<span class="menu-title">좋아요</span>
						<span class="sidebar-tail" id="side-likes">${etc.likes}</span>
						</a>
					</li>
					<c:if test="${member.m_no != loginDTO.m_no}">
					<li class="menu-li">
						<a href="#">
							<button class="menu-edit">팔로잉</button> 	
						</a>
					</li>
					</c:if>
				</ul>					
			 </div>	
		</div>
	</div>
	
	<div class="subject">
		<ul class="subject-ul">
			<li class="subject-li">
				<p class="subject-p">내가 쓴 버징</p>
			</li>
		</ul>
	</div>
	<div class="body" id="body-md">
	  <div class="sidebar-padding" id="sidebar-md">
	  	<div class="profile-location">
			<div class="profile-size">
				<p>				  
				 <img src="resources/img/${profile}" class="img-circle">				  
			   </p>
			   <div class="introduce">
			    <p class="intro-header">${member.m_name}</p>
			    <p>@${member.m_id}</p>
			   	<p>${member.m_pr}</p>
			   </div>
			</div>		   
		</div>
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
							 <img alt="" class="content-img" src="resources/img/${profile}">
						  </a>
						  <div class="content-line" onclick="contentPopUp(${buzz.b_no})">
							 <div>
							 	<ul>
							 		<li class="content-head">${buzz.m_name}</li>
							 		<li class="content-tail"><a class="font-color" href="${buzz.m_id}">@${buzz.m_id}</a><span class='board-date'> · ${buzz.b_rdate}</span></li>
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
	                     	 
							 <div id="icon-padding" class="twitter-card-content-board">
							     <div class="twitter-card-icons-board">
							     	<a id="content-icon" class="twitter-card-icon comment tooltips">
							            <i class="fa fa-comment-o"></i>
							        </a>
							        <a id="content-icon" class="twitter-card-icon heart tooltips" id='board-likes${buzz.b_no}'>
							        	<c:if test='${buzz.board_like}'>
							           		<i class="fa fa-heart like-button liked liked-shaked" id='heart${buzz.b_no}' onclick='sideControl(event, ${buzz.b_no}, "ajax/likes", "#side-likes", "#likeCount")'></i>
							            </c:if>
							            <c:if test='${!buzz.board_like}'>
							           		<i class="fa fa-heart-o like-button" id='heart${buzz.b_no}' onclick='sideControl(event, ${buzz.b_no}, "ajax/likes", "#side-likes", "#likeCount")'></i>
							            </c:if>
							            <font id='likeCount${buzz.b_no}'>${buzz.b_like}</font>
							        </a>
							        <a id="content-icon" class="twitter-card-icon message tooltips" class="dropdown-toggle" data-toggle="dropdown">
						          		<i class="glyphicon glyphicon-option-horizontal"></i>
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
	  
<!-- content modal start -->
	<div class="modal fade" id="content-modal"  data-toggle='modal'>
      <button type="button" class="close" data-dismiss="modal" id='content-modal-btn'>×</button>
      <div class='modal-area'>
		  <div class="twitter-card" onclick='contentZoom(0)'>
	      <input type='hidden' class='modal-bno'/>
	      <div class="twitter-card-header">
	        <img class="twitter-card-user-img content-img" src=""/>
	        <span class="twitter-card-user-name"></span>
			<button style="margin-top: auto;" class="btn-Hyeong btn-primary-Hyeong btn-follow" id='followBtn' onclick='following(0)'>
				<span class="follow"><i class="fa fa-user-plus"></i> 팔로우</span>
				<span class="unfollow">언팔로우</span>
				<span class="following">팔로잉</span>
			</button>
	        <span class="twitter-card-user-name-2"></span>
	      </div>
	
	      <div class="twitter-card-text" id="content-margin" style="white-space: pre-line;">
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
	          <a class="twitter-card-icon message tooltips" class="dropdown-toggle" data-toggle="dropdown">
	          	<span>메세지</span>
	          	<i class="glyphicon glyphicon-option-horizontal"></i>
	          </a>
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
    		<input type='file' id='file' class='content-foot' multiple='multiple' onchange='readURL(this, ".img-area-modal")'/>
        	<span id='modal-textCount-content'>0</span>/140&nbsp;
        	<button class="btn btn-search-bar disabled" id='modal-sendBtn-content' data-dismiss="modal">버징하기</button>
        </div>
        </div>
	</div>
<!-- content modal end -->	  
	  <div class="trend">
		<div class="col-xs-34" id='side-nav'>
         <div class="panel panel-default panel-custom">
            <div class="panel-heading" style='padding:7px'>
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
                     <img src="resources/img/${member.profile_name}" alt="" class="media-object recommend-profile recommend">
                  </div>
                  <div class="media-body">
                     <h4 style="text-align:left">${member.m_name}</h4>
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
            <div class="panel-heading" style='padding:7px'>
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
		</div>	 	
	  </div>
</body>	
<link href="lib/bootstrap/css/bootstrap.css" rel="stylesheet">
<script src="lib/modal.js"></script>
<script src="lib/profile.js"></script>

</html>
