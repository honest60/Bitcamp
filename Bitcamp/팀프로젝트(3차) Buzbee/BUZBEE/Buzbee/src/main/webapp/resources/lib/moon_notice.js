/**
 *  board
 */
$(document).ready(function() {
	 $('#append-content').click(function(){
	  	$('#append-content').prop("rows", 5);
	 	$('#content-footer').show();
	 	event.stopPropagation();
	 });
	 
	 $('#content-modal').on('hidden.bs.modal', function (e) {
		$('.ajax-appended-content').html("");
		$('#content-footer').hide();
     	$('#append-content').prop("rows", 1);
     	$('#append-content').val('');
     	$('#modal-sendBtn-content').attr("x", "btn btn-search-bar disabled");
     	$('#modal-textCount-content').html(0);
     	$('.twitter-card-text').css('font-size', '3rem');
     	$('#modal-bno').remove();
     	$('.twitter-card').css('background-color', 'white');
     	$('.content-modal').eq(0)
     	.removeClass('fa-heart')
     	.removeClass('liked')
     	.removeClass('liked-shaked')
     	.addClass('fa-heart-o');
     	index = 0;
     	uploadFiles = [];
     });
	
	 $("#append-content").keyup(function(){
		if($("#append-content").val().trim().length > 0 || uploadFiles.length != 0) $('#modal-sendBtn-content').attr("class", "btn btn-search-bar");
		else $('#modal-sendBtn-content').attr("class", "btn btn-search-bar disabled");
		$('#modal-textCount-content').html($("#append-content").val().length);
		if($("#append-content").val().length > 140) {
			$('#modal-sendBtn-content').attr("class", "btn btn-search-bar disabled");
			$('#modal-textCount-content').css("color", "red");
		} else if($("#append-content").val().length == 0) {
			$('#modal-sendBtn-content').attr("class", "btn btn-search-bar disabled");
		} else {
			$('#modal-textCount-content').css("color", "#31708f");
		}
	}); 
	 
	$("#modal-text").keyup(function(){
		if($("#modal-text").val().trim().length > 0 || uploadFiles.length != 0) $('#modal-sendBtn').attr("class", "btn btn-search-bar");
		else $('#modal-sendBtn').attr("class", "btn btn-search-bar disabled");
		$('#modal-textCount').html($("#modal-text").val().length);
		if($("#modal-text").val().length > 140) {
			$('#modal-sendBtn').attr("class", "btn btn-search-bar disabled");
			$('#modal-textCount').css("color", "red");
		} else if($("#modal-text").val().length == 0) {
			$('#modal-sendBtn').attr("class", "btn btn-search-bar disabled");
		} else {
			$('#modal-textCount').css("color", "#31708f");
		}
	});
	 
	$(document).click(function(e){
		if(!$(e.target).is('#search2')) {
			if($(e.target).is('#buzzing')) {
				buzzing('#search2');
				return;
			}
			else if($(e.target).is('#modal-sendBtn')) {buzzing('#modal-text');}	
			else if($(e.target).is('#modal-sendBtn-content')) {buzzing('#append-content');e.stopPropagation();}	
			else if($(e.target).is('#file')) {return;}
			if($("#search2").val().trim().length == 0 && uploadFiles.length == 0) {
				$("#search2").prop("rows", 1);
				$("#search2").val("");
				$("#appended").remove();
				$(".foo").remove();
				uploadFiles = [];
			}
		}
	});
	
	$("#search2").keyup(function(){
		var buzzing = $("#search2").val();
		
		if($("#search2").val().trim().length > 0 || uploadFiles.length != 0) $('#buzzing').attr("class", "btn btn-search-bar");
		else $('#buzzing').attr("class", "btn btn-search-bar disabled");
		$('#textCount').html($("#search2").val().length);
		if($("#search2").val().length > 140) {
			$('#buzzing').attr("class", "btn btn-search-bar disabled");
			$('#textCount').css("color", "red");
		} else {
			$('#textCount').css("color", "#31708f");
		}
	});	
	
	$("#search2").click(function(){
		if($("#search2").prop("rows") < 5) {
			$("#inputarea").append("<div id='appended'>"+
							           "<div id='underBar' style='text-align:right'><input type='file' id='file' multiple='multiple' onchange='readURL(this, \".img-area-main\")'/><span id='textCount'>"+ $("#search2").val().length +"</span>/140&nbsp;&nbsp;<button id='buzzing' class='btn btn-search-bar disabled'>버징하기</button></div>"+
								   "</div>");
		}
		$("#search2").prop("rows", 5);
		if($("#search2").val().length > 140) $('#textCount').css("color", "red");
		else if($("#search2").val().trim().length > 0 || uploadFiles.length != 0) {
			$('#buzzing').attr("class", "btn btn-search-bar");										
		}
	});
});
 
function upload() { 	
	for(var i=0; i < uploadFiles.length; i++){
		var fileData = new FormData();
		fileData.append("files", uploadFiles[i], uploadFiles[i].name);
							
		$.ajax({
			contentType: false,
			processData: false,			
			url:baseUrl + '/ajax/uploadFiles',
			enctype:"multipart/form-data",
			type:"post",
		    async: false,
			data: fileData,						
			error:function(jqXHR){	
		        alert(jqXHR.status);
		        alert(jqXHR.statusText);
		        alert(jqXHR.responseText);
		        alert(jqXHR.readyState);
			},
			success:function(msg){
				savedFileNo.push(msg);
			}
		});
	}
	uploadFiles = [];
}
 
function readURL(input, area) {
	var fileTypes = ['jpg', 'jpeg', 'png', 'gif', 'mp4'];  //acceptable file types
	
    if (input.files && input.files[0]) {
        if(input.files.length > 1) {
        	for(var i = 0; i < input.files.length; i++) {
        		var extension = input.files[i].name.split('.').pop().toLowerCase();
        		if(!fileTypes.includes(extension)) {
        			alert("'jpg', 'jpeg', 'png', 'gif', 'mp4' 형식만 업로드 가능합니다");
        			return;
        		}
        		
        		if(extension == 'mp4') {
        			alert('동영상은 여러 개 또는 다른 미디어와 같이 업로드할 수 없습니다');
        			return;
        		}
        		
            	if(uploadFiles.length == 4) {
            		alert('최대 4개의 파일까지 업로드 가능합니다');
            		return;
            	}
            	
            	if(input.files[i].size > 5242880 && extension != 'mp4') {
            		alert('사진은 5MB를 넘을 수 없습니다');
            		return;
            	} else if(input.files[i].size > 52428800 && extension == 'mp4') {
            		alert('동영상은 50MB를 넘을 수 없습니다');
            		return;
            	}
            	
                $(area).append("<img class='foo' src='" + URL.createObjectURL(input.files[i]) + "'onclick='this.remove();uploadFiles.splice(this, 1)'/>");
                $('.foo').css("display", "block");
                $('#file').css("clear", "both");
                $('#file').css("padding-top", "5px");
                
            	uploadFiles.push(input.files[i]);
            	$('#buzzing').attr("class", "btn btn-search-bar");
        	}
        } else {
        	var extension = input.files[0].name.split('.').pop().toLowerCase();
    		if(!fileTypes.includes(extension)) {
    			alert("'jpg', 'jpeg', 'png', 'gif', 'mp4' 형식만 업로드 가능합니다.");
    			return;
    		}
        	
        	if(uploadFiles.length == 4) {
        		alert('최대 4개의 파일까지 업로드 가능합니다');
        		return;
        	}
        	
        	if(uploadFiles.length >= 1 && extension == 'mp4') {
        		alert('동영상은 여러 개 또는 다른 미디어와 같이 업로드할 수 없습니다');
        		return;
        	}
        	
        	if(input.files[0].size > 5242880 && extension != 'mp4') {
        		alert('사진은 5MB를 넘을 수 없습니다');
        		return;
        	} else if(input.files[0].size > 52428800 && extension == 'mp4') {
        		alert('동영상은 50MB를 넘을 수 없습니다');
        		return;
        	}
        	
            $(area).append("<img class='foo' src='" + URL.createObjectURL(input.files[0]) + "' onclick='this.remove();uploadFiles.splice(this, 1)'/>");
            $('.foo').css("display", "block");
            $('#file').css("clear", "both");
            $('#file').css("padding-top", "5px");
            
	        uploadFiles.push(input.files[0]);
	        $('#buzzing').attr("class", "btn btn-search-bar");
        }
	}
}

function showNewBuzzing() {
	$(".media.lists.new").css("display", "block");
	$("#newBuzzing").remove();
	newBuzzingCount=0;
	$('title').text('버즈비');
}

function buzzing(id) {
	if($('#textCount').css("color") == 'rgb(255, 0, 0)' || $('#modal-textCount').css("color") == 'rgb(255, 0, 0)' || $('#modal-textCount-content').css("color") == 'rgb(255, 0, 0)') {
		alert('글자 수 제한을 초과했습니다');
		return;
	}
	if($(id).val().trim() == '' && uploadFiles.length == 0) return;
	
	if(id == '#append-content') {
		var toWhom = $('.to-whom').text();
		toWhom = toWhom.substring(0, toWhom.indexOf('님'));
		sendMessageReply(id, $('#modal-bno').val(), toWhom);
	} else sendMessage(id);
	
	$("#search2").prop("rows", 1);
	$("#search2").val("");
	$("#appended").remove();
	$(".foo").remove();
	$(id).val('');
}

function sideControl(e, boardNo, paramURL, paramSide, paramBoard){
	var request = $.ajax({url:baseUrl + "/" + paramURL, async:false, method:"GET", data:{b_no:boardNo}, dataType:"html", 
	  success: function () {},
      error: function (jqXHR) {
        alert(jqXHR.status);
        alert(jqXHR.statusText);
        alert(jqXHR.responseText);
        alert(jqXHR.readyState);
      }});
	request.done(function(data){
		if(data == 'true') {
			if(paramSide != null) $(paramSide).text(parseInt($(paramSide).text(), 10) + 1);
			$(paramBoard + boardNo).text(parseInt($(paramBoard + boardNo).text(), 10) + 1);
		} else {
			if(paramSide != null) $(paramSide).text(parseInt($(paramSide).text(), 10) - 1);
			$(paramBoard + boardNo).text(parseInt($(paramBoard + boardNo).text(), 10) - 1);
		}
	});
	colorBoard(boardNo);
	e.stopPropagation();
}

function sideControlModal(e, boardNo, paramURL, paramSide, paramBoard, idx){
	var request = $.ajax({url:baseUrl + "/" + paramURL, async:false, method:"GET", data:{b_no:boardNo}, dataType:"html", 
	  success: function () {},
      error: function (jqXHR) {
        alert(jqXHR.status);
        alert(jqXHR.statusText);
        alert(jqXHR.responseText);
        alert(jqXHR.readyState);
      }});
	request.done(function(data){
		if(data == 'true') {
			$(paramSide).text(parseInt($(paramSide).text(), 10) + 1);
			$(paramBoard).eq(idx).text(parseInt($(paramBoard).eq(idx).text(), 10) + 1);
		} else {
			$(paramSide).text(parseInt($(paramSide).text(), 10) - 1);
			$(paramBoard).eq(idx).text(parseInt($(paramBoard).eq(idx).text(), 10) - 1);
		}
	});
	colorModal(idx);
	e.stopPropagation();
}

function contentPopUp(b_no) {
	$('#content-modal').modal();
	 
	var request = $.ajax({url:baseUrl + "/ajax/content", method:"GET", data:{b_no:b_no}, dataType:"json", 
	success: function () {},
	error: function (jqXHR) {
		alert(jqXHR.status);
		alert(jqXHR.statusText);
		alert(jqXHR.responseText);
		alert(jqXHR.readyState);
    }});
	
	request.done(function(data){
		for(var i in data) {
			$(".modal-bno").eq(i).val(data[i].b_no);
			$(".twitter-card-user-name").eq(i).html(data[i].m_name + "<br/><a href='' class='content-modal-id'></a>");
			$(".content-modal-id").eq(i).html("@" + data[i].m_id);
			$(".content-modal-id").eq(i).attr("href", data[i].m_id);
			$(".twitter-card-text").eq(i).html(data[i].b_content);
			$(".twitter-card-text").eq(i).val("@" + data[i].m_id + " ");
			$(".content-date").eq(i).text(data[i].b_rdate);
			$(".twitter-card-user-img").eq(i).attr("src", baseUrl + "/img/" + data[i].img_profile);
			$('.rebuzCount-modal').eq(i).html(data[i].b_rebuz);
			$('.likeCount-modal').eq(i).html(data[i].b_like);
			$('.btn-Hyeong').eq(i).hide();
			if(data[i].board_like) {
				$('.content-modal').eq(i).removeClass('fa-heart-o')
				.addClass('fa-heart')
				.addClass('liked')
				.addClass('liked-shaked');
			}
			
			if(data[i].b_no == b_no) {
				$('#content-modal').append('<input type="hidden" id="modal-bno" value="'+b_no+'">');
				$('.to-whom').html('<a href="'+data[i].m_id+'">@' + data[i].m_id + '</a>님에게 보내는 답글');
				$(".twitter-card")[i].scrollIntoView(true);
				$('.btn-Hyeong').eq(i).show();
				$('.twitter-card').eq(i).css('background-color', 'GhostWhite');
			} else {
				$('.twitter-card-text').eq(i).css('font-size', '2rem');
			}

			if(username == data[i].m_id) $('.btn-Hyeong.btn-primary-Hyeong.btn-follow').eq(i).hide();
			
			if(data[i].following) {
				$('.btn-Hyeong').eq(i).addClass('btn-following');
				$('.follow').eq(i).hide();
				$('.following').eq(i).show();
			}
			
			if(data[i].f_saveFiles != "") {
				$(".twitter-card-text").eq(i).append("<br/>");
				for(var j = 0; j < data[i].f_saveFiles.length; j++) {
					var ext = data[i].f_saveFiles[j].substring(data[i].f_saveFiles[j].lastIndexOf(".") + 1);
					if(ext == 'mp4') $(".twitter-card-text").eq(i).append('<video src="'+baseUrl+'/store/'+data[i].f_saveFiles[j]+'" class="videoOne" controls></video>');
					else {
						if(data[i].f_saveFiles.length == 1) $(".twitter-card-text").eq(i).append('<img src="'+baseUrl+'/store/'+data[i].f_saveFiles[j]+'" class="imgOne" onclick="doImgPop(this.src, event);"/>');
						else $(".twitter-card-text").eq(i).append('<img src="'+baseUrl+'/store/'+data[i].f_saveFiles[j]+'" class="imgList" style="width:49.1%" onclick="doImgPop(this.src, event);"/>');
					}
				}
			}

			if(i < data.length - 1) {
				index = index + 1;
				var html = '<div class="twitter-card" onclick="contentZoom('+index+')">' + 
							  '<input type="hidden" class="modal-bno"/>' +
						      '<div class="twitter-card-header">' +
						        '<img class="twitter-card-user-img img-circle" src=""/>'+
						        '<span class="twitter-card-user-name"></span>'+
								'<button class="btn-Hyeong btn-primary-Hyeong btn-follow" id="followBtn" onclick="following('+index+')">'+
									'<span class="follow"><i class="fa fa-user-plus"></i> 팔로우</span>'+
									'<span class="unfollow">언팔로우</span>'+
									'<span class="following">팔로잉</span>'+
								'</button>'+
						        '<span class="twitter-card-user-name-2"></span>'+
						      '</div>'+
						      '<div class="twitter-card-text" style="white-space: pre-line;">'+
						      '</div>'+
						         '<small class="content-date"></small>'+
						      '<div class="twitter-card-content">'+
						      '<div class="twitter-card-icons">'+
						          '<a class="twitter-card-icon comment tooltips">'+
						            '<span>댓글</span>'+
						            '<i class="fa fa-comment-o"></i>'+
						          '</a>'+
						          '<a class="twitter-card-icon retweet tooltips">'+
						            '<span>리버징</span>'+
						            '<i id="rebuz-modal" class="fa fa-retweet"></i>'+
						            '	<font class="rebuzCount-modal"></font>'+
						          '</a>'+
						          '<a class="twitter-card-icon heart tooltips" >'+
						            '<span>좋아요</span>'+
						            '<i class="fa fa-heart-o like-button content-modal" onclick=\'sideControlModal(event, $(".modal-bno").eq('+index+').val(), "ajax/likes", "#side-likes", ".likeCount-modal", '+index+');\'></i>'+
						            '	<font class="likeCount-modal"></font>'+
						          '</a>'+
						          '<a class="twitter-card-icon message tooltips">'+
						            '<span>메세지</span>'+
						            '<i class="fa fa-envelope"></i>'+
						          '</a>'+
						      '</div>'+
						   '</div>';
				$('.ajax-appended-content').append(html);
			}
		}
	});
}

function contentZoom(idx) {
	$('.twitter-card').css('background-color', 'white');
	$('.twitter-card').eq(idx).css('background-color', 'GhostWhite');
	$('.twitter-card-text').css('font-size', '2rem');
	$('.twitter-card-text').eq(idx).css('font-size', '3rem');
	$('.to-whom').html('<a href="'+$('.content-modal-id').eq(idx).attr("href")+'">' + $('.content-modal-id').eq(idx).html() + '<a/>님에게 보내는 답글');
	$('.btn-Hyeong').hide();
	if('@' + username != $('.content-modal-id').eq(idx).text()) $('.btn-Hyeong').eq(idx).show();
}

let isEnd = false;

$(function(){
     $(window).scroll(function(){
          let $window = $(this);
          let scrollTop = $window.scrollTop();
          let windowHeight = $window.height();
          let documentHeight = $(document).height();

          console.log("documentHeight:" + documentHeight + " | scrollTop:" +
                         scrollTop + " | windowHeight: " + windowHeight );

          // scrollbar의 thumb가 바닥 전 30px까지 도달 하면 리스트를 가져온다.
          if( scrollTop + windowHeight + 150 > documentHeight ) {
        	  fetchList(startNo);
        	  startNo += 1;
          }
     })
})

function fetchList(startNo){
     if(isEnd == true) {return;}
     // 방명록 리스트를 가져올 때 시작 번호
     // renderList 함수에서 html 코드를 보면 <li> 태그에 data-no 속성이 있는 것을 알 수 있다.
     // ajax에서는 data- 속성의 값을 가져오기 위해 data() 함수를 제공.
	
     $.ajax({
          url:baseUrl + "/ajax/notice?page=" + startNo + "&newBuzzings=" + newBuzzings,
          type: "GET",
          async: false,
          dataType: "json",
          success: function(result){
              // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
              // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
              var length = result.length;
              if( length < 20 ){
                   isEnd = true;
              }

              $.each(result, function(index, vo){
                   renderList(vo);
              });
          }
     });
}

function renderList(vo){
// 리스트 html을 정의
	var heartClass;
	var isAdmin = '<li onclick="deleteBoard('+vo.b_no+')">게시물 삭제</li>';
	if(username != vo.m_id) isAdmin = '<li onclick="blockUser(\''+vo.m_id+'\', 0)">유저 차단</li><li>게시물 가리기</li>';
	
	if(vo.board_like) heartClass = 'fa fa-heart like-button liked liked-shaked';
	else heartClass = 'fa fa-heart-o like-button';
	
    var html = '<div class="media lists new" id="lists">'+
			       '<a class="media-left" href="#fake">'+
			           '<img alt="" class="content-profile media-object img-circle" src="resources/img/'+vo.img_profile+'">'+
			       '</a>'+
			       '<div class="media-body" onclick="contentPopUp('+vo.b_no+')">'+
			            '<h4 class="media-heading">' + vo.m_name + ' <a href="' + vo.m_id + '">@' + vo.m_id + '</a><span class="board-date"> · '+vo.b_rdate+'</span></h4><span id="content-'+vo.b_no+'" style="white-space: pre-line;">' + vo.b_content + '<br/><br/></span>'+
			            '<div class="twitter-card-content-board">'+
					     '<div class="twitter-card-icons-board">'+
					     	'<a href="#" class="twitter-card-icon comment tooltips">'+
					            '<i class="fa fa-comment-o"></i>'+
					        '</a>'+
					        '<a class="twitter-card-icon retweet tooltips" id="board-rebuz'+vo.b_no+'">'+
					            '<i id="rebuz-modal" class="fa fa-retweet" onclick="sideControl(event, '+vo.b_no+', \'ajax/rebuz\', null, \'#rebuzCount\')"></i>'+
					            '<font class="font-bug" id="rebuzCount'+vo.b_no+'">'+vo.b_rebuz+'</font>'+
					        '</a>'+
					        '<a class="twitter-card-icon heart tooltips" id="board-likes'+vo.b_no+'">'+
					            '<i class="'+heartClass+'" id="heart'+vo.b_no+'" onclick="sideControl(event, '+vo.b_no+', \'ajax/likes\', \'#side-likes\', \'#likeCount\')"></i>'+
					            '<font class="font-bug" id="likeCount'+vo.b_no+'">'+vo.b_like+'</font>'+
					        '</a>'+
					        '<li class="navi_set glyphicon glyphicon-option-horizontal topnav" onclick="dropMenu('+boardNo+')">'+  
							   '<ul class="subnav" id="subnav'+boardNo+'">'+
         			      	     isAdmin+
							   '</ul>'+  
							'</li>'+
					    '</div>'+
			       '</div>'+
			    '</div>';
  $("#buz").append(html);
}

// 이미지 클릭시 원본 크기로 팝업 보기
function doImgPop(img, e){ 
	img1= new Image();
	img1.src=(img);
	$('#modal-img').prop("src", img1.src);
	$('#image-modal').modal();
	e.stopPropagation();
} 

$(window).scroll(function(){ 
     var num = $(this).scrollTop(); 
     if( num > 549 ){  		    	  
         $("#middle-bar").css("position","fixed");
         $("#middle-back").css("position","fixed");
         $("#col-sm-12").css("position","fixed");
         $("#middle-bar").css("top", "45px");
         $("#middle-back").css("top", "50px");
         $(".midBtn").css("margin-top", "4px");
         $("#col-sm-12").css("top", "50px");
         $("#col-sm-12").css("z-index", "3");
         $(".profile-bar").css("padding-left", "60px");
         $(".profile-bar").css("margin-right", "70px");
         $("#middle-back").css("background-color", "white");
      	 $("#proflieImg").hide();
      	 $('.profile-content').css("margin-top", "50px");
      	 $('#sidebar2').css("margin-top", "60px");
         $('.sm-profile').show();
         $('.profile-follow-Btn').css('margin-left','580px');
         $('.profile-follow-Btn').css('margin-top','13px');
     }else{	 		
    	 $("#middle-bar").css("position","static");
         $("#middle-back").css("position","static");
         $("#col-sm-12").css("position","static");
         $("#middle-bar").css("top", "");
         $("#middle-back").css("top", "");
         $(".midBtn").css("margin-top", "-10px");
         $(".profile-bar").css("padding-left", "");
         $(".profile-bar").css("margin-right", "40px");
         $('.profile-content').css("margin-top", "");
         $("#proflieImg").show();
         $('#sidebar2').css("margin-top", "-150px");
         $('#middle-back>*').hide();
         $('.profile-follow-Btn').css('margin-left','0');
         $('.profile-follow-Btn').css('margin-top','3px');
     } 		            	     
});

function colorBoard(bno) {
 	 if($('#heart' + bno).hasClass('fa-heart-o')) {
		 $('#heart' + bno).removeClass('fa-heart-o')
		 .addClass('fa-heart')
		 .addClass('liked')
		 .addClass('liked-shaked');
	 } else {
		 $('#heart' + bno).removeClass('fa-heart')
		 .removeClass('liked')
		 .removeClass('liked-shaked')
		 .addClass('fa-heart-o');
	 }
}

function colorModal(indexModal) {
	  var b_no = $('.modal-bno').eq(indexModal).val();

	  if($('.content-modal').eq(indexModal).hasClass('fa-heart-o')) {
		 $('.content-modal').eq(indexModal).removeClass('fa-heart-o')
		 .addClass('fa-heart')
		 .addClass('liked')
		 .addClass('liked-shaked');
		 $('#likeCount' + b_no).text(parseInt($('#likeCount' + b_no).text(), 10) + 1);
	 } else {
		 $('.content-modal').eq(indexModal).removeClass('fa-heart')
		 .removeClass('liked')
		 .removeClass('liked-shaked')
		 .addClass('fa-heart-o');
		 $('#likeCount' + b_no).text(parseInt($('#likeCount' + b_no).text(), 10) - 1);
	 }
   	 
   	 colorBoard(b_no);
}

function isFollowing() {
	var request = $.ajax({url:baseUrl + '/ajax/followingProfile', method:"GET", data:{m_no:'${member.m_no}'}, dataType:"html", 
	  success: function () {},
      error: function (jqXHR) {
        alert(jqXHR.status);
        alert(jqXHR.statusText);
        alert(jqXHR.responseText);
        alert(jqXHR.readyState);
      }});
	request.done(function(data){
		if(data > 0) $('.profile-follow-Btn.btn.btn-primary').text('팔로잉');
	});
}

function dropMenu(idx) {
	if($('#subnav' + idx).css("display") == 'none') {
		$('#subnav' + idx).slideDown('normal').show();
	} else {
		$('#subnav' + idx).slideUp('normal');
	}
	
	event.stopPropagation();
}

function deleteBoard(idx) {
	var con = confirm('정말 게시물을 삭제하시겠습니까?\n한 번 삭제한 게시물은 복구할 수 없습니다.');
	
	if(con) {
		var request = $.ajax({url:baseUrl + '/ajax/deleteBoard', method:"GET", data:{b_no : idx}, dataType:"html", 
			success: function () {},
			error: function (jqXHR) {
				alert(jqXHR.status);
				alert(jqXHR.statusText);
				alert(jqXHR.responseText);
				alert(jqXHR.readyState);
			}});
		request.done(function(data){
			$('#lists' + idx).hide();
		});
	}
}

function blockUser(user, idx) {
	var con = confirm("@" + user + '님을 차단하시겠습니까?');
	
	if(con) {
		var request = $.ajax({url:baseUrl + '/ajax/blockUser', method:"GET", data:{m_id : user, m_no : idx}, dataType:"html", 
			success: function () {},
			error: function (jqXHR) {
				alert(jqXHR.status);
				alert(jqXHR.statusText);
				alert(jqXHR.responseText);
				alert(jqXHR.readyState);
			}});
		request.done(function(data){
			alert("@" + user + "님을 차단했습니다.\n설정에서 차단 해제가 가능합니다.");
		});
	}
}
