/**
 * 
 */
$(document).ready(function() { 
	 		var ids = [];
	 		console.log($('#col-sm-content').text());
	 		var strs = $('#col-sm-content').text().split("@");
	 		for(var i = 0; i < strs.length; i++) {
	 			var id = strs[i].split(" ", 1);
	 			ids.push(id);
	 			console.log(id);
	 		}
	 		
			$(document).click(function(e){
				$("#modal-text").focus(); 
				
				if($(e.target).is('#buzzing')) {
					buzzing('#search2');
					return;
				}
				else if($(e.target).is('#modal-sendBtn')) {buzzing('#modal-text');}
				else if($(e.target).is('#modal-sendBtn-content')) {buzzing('#append-content');e.stopPropagation();}
				else if($(e.target).is('#file')) {return;}
			});		
			
			/*  extends start*/
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
			/*  extends end*/
});

	//resize start//
	$( window ).resize(function() {	   		 
			var windowWidth = $( window ).width(); 	
			if(windowWidth < 450) {
	 			//창 가로 크기가 500 미만일 경우 
	 			$(window).scroll(function(){  //스크롤하면 아래 코드 실행
		 		      var num = $(this).scrollTop();  // 스크롤값
		 		      var $el = $('.show-on-scroll');
		 		      if( num > 100 ){  // 스크롤을 40이상 했을 때	 		    	  
		 		          $(".main-bar").css("position","fixed");
		 		       	  $(".main-bar").css("top", "45px");
		 		       	  $el.addClass('shown');
		 		       	  $(".img-circle").css("visibility","hidden");  
		 		       	  $(".hidden-part").css("visibility","visible");
		 		      }else{	 		    	  
		 		          $(".main-bar").css("position","static");
		 		          $(".main-bar").css("top", "300px");	
		 		          $el.removeClass('shown');
		 		          $(".img-circle").css("visibility","visible");
		 		          $(".hidden-part").css("visibility","hidden");
		 		      } 		            	     
		 		});		 			
	 		} else if(windowWidth <900){
	 			//창 가로 크기가 900 미만일 경우 
	 			$(window).scroll(function(){  //스크롤하면 아래 코드 실행
		 		      var num = $(this).scrollTop();  // 스크롤값
		 		      var $el = $('.show-on-scroll');
		 		      if( num > 400 ){  // 스크롤을 90이상 했을 때	 		    	  
		 		          $(".main-bar").css("position","fixed");
		 		       	  $(".main-bar").css("top", "45px");
		 		       	  $el.addClass('shown');
		 		       	  $(".img-circle").css("visibility","hidden");
		 		       	  $(".hidden-part").css("visibility","visible");
		 		      }else{	 		    	  
		 		          $(".main-bar").css("position","static");
		 		          $(".main-bar").css("top", "300px");	
		 		          $el.removeClass('shown');
		 		          $(".img-circle").css("visibility","visible");
		 		          $(".hidden-part").css("visibility","hidden");
		 		      } 		            	     
		 		});		 			
	 		} else {
	 			$(window).scroll(function(){  //스크롤하면 아래 코드 실행
		 		      var num = $(this).scrollTop();  // 스크롤값
		 		      var $el = $('.show-on-scroll');
		 		      if( num > 400 ){  // 스크롤을 200이상 했을 때	 		    	  
		 		          $(".main-bar").css("position","fixed");
		 		       	  $(".main-bar").css("top", "45px");
		 		       	  $el.addClass('shown');
		 		       	  $(".img-circle").css("visibility","hidden");
		 		       	  $(".hidden-part").css("visibility","visible!important");
		 		      }else{	 		    	  
		 		          $(".main-bar").css("position","static");
		 		          $(".main-bar").css("top", "300px");	
		 		          $el.removeClass('shown');
		 		          $(".img-circle").css("visibility","visible");
		 		          $(".hidden-part").css("visibility","hidden");
		 		      } 		            	     
		 		});
	 		} 			
	});
// resize end //
	
// 접속 시 창 size인식 start //
		$(function(){	 		
	 		var windowWidth = $( window ).width(); 	
	 		if(windowWidth < 450) {
	 			//창 가로 크기가 500 미만일 경우 
	 			$(window).scroll(function(){  //스크롤하면 아래 코드 실행
		 		      var num = $(this).scrollTop();  // 스크롤값
		 		      var $el = $('.show-on-scroll');
		 		      if( num > 100 ){  // 스크롤을 40이상 했을 때	 		    	  
		 		          $(".main-bar").css("position","fixed");
		 		       	  $(".main-bar").css("top", "45px");
		 		       	  $el.addClass('shown');
		 		       	  $(".img-circle").css("visibility","hidden");
		 		       	  $(".hidden-part").css("visibility","visible");
		 		      }else{	 		    	  
		 		          $(".main-bar").css("position","static");
		 		          $(".main-bar").css("top", "300px");	
		 		          $el.removeClass('shown');
		 		          $(".img-circle").css("visibility","visible");
		 		          $(".hidden-part").css("visibility","hidden");
		 		      } 		            	     
		 		});		 			
	 		} else if(windowWidth <900){
	 			//창 가로 크기가 900 미만일 경우 
	 			$(window).scroll(function(){  //스크롤하면 아래 코드 실행
		 		      var num = $(this).scrollTop();  // 스크롤값
		 		      var $el = $('.show-on-scroll');
		 		      if( num > 400 ){  // 스크롤을 90이상 했을 때	 		    	  
		 		          $(".main-bar").css("position","fixed");
		 		       	  $(".main-bar").css("top", "45px");
		 		       	  $el.addClass('shown');
		 		       	  $(".img-circle").css("visibility","hidden");
		 		       	  $(".hidden-part").css("visibility","visible");
		 		      }else{	 		    	  
		 		          $(".main-bar").css("position","static");
		 		          $(".main-bar").css("top", "300px");	
		 		          $el.removeClass('shown');
		 		          $(".img-circle").css("visibility","visible");
		 		          $(".hidden-part").css("visibility","hidden");
		 		      } 		            	     
		 		});		 			
	 		} else {
	 			$(window).scroll(function(){  //스크롤하면 아래 코드 실행
		 		      var num = $(this).scrollTop();  // 스크롤값
		 		      var $el = $('.show-on-scroll');
		 		      if( num > 400 ){  // 스크롤을 200이상 했을 때	 		    	  
		 		          $(".main-bar").css("position","fixed");
		 		       	  $(".main-bar").css("top", "45px");
		 		       	  $el.addClass('shown');
		 		       	  $(".img-circle").css("visibility","hidden");
		 		       	  $(".hidden-part").css("visibility","visible!important");
		 		       	  $(".hidden-part").css("visibility","visible");
		 		      }else{	 		    	  
		 		          $(".main-bar").css("position","static");
		 		          $(".main-bar").css("top", "300px");	
		 		          $el.removeClass('shown');
		 		          $(".img-circle").css("visibility","visible");
		 		          $(".hidden-part").css("visibility","hidden");
		 		      } 		            	     
		 		});
	 		} 			
	 }); 	
	// 접속 시 창 size인식 end //
	
//파일 관련 start//
function upload() { 	
		for(var i=0; i < uploadFiles.length; i++){
		var fileData = new FormData();
		fileData.append("files", uploadFiles[i], uploadFiles[i].name);
		$.ajax({
			contentType: false,
			processData: false,			
			url:'ajax/uploadFiles',
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
                
            	uploadFiles.push(input.files[i]);
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
            
	        uploadFiles.push(input.files[0]);
        }
	}
}
//파일 관련 end//

	function showNewBuzzing() {
		$(".media.lists.new").css("display", "block");
		$("#newBuzzing").remove();
		newBuzzingCount=0;
		$('title').text('버즈비');
	}
	
	function buzzing(id) {
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

	function likes(e, boardNo){
		var request = $.ajax({url:"ajax/likes", method:"GET", data:{b_no:boardNo}, dataType:"html", 
		  success: function () {},
	      error: function (jqXHR) {
	        alert(jqXHR.status);
	        alert(jqXHR.statusText);
	        alert(jqXHR.responseText);
	        alert(jqXHR.readyState);
	      }});
		request.done(function(data){
			if(data == 'true') {
				$("#side-likes").html(parseInt($("#side-likes").html(), 10) + 1);
				$("#board-likes" + boardNo).html(parseInt($("#board-likes" + boardNo).html(), 10) + 1);
			} else {
				$("#side-likes").html(parseInt($("#side-likes").html(), 10) - 1);
				$("#board-likes" + boardNo).html(parseInt($("#board-likes" + boardNo).html(), 10) - 1);
			}
		});
		e.stopPropagation();
	}
// 추가 부분
	function sideControl(e, boardNo, paramURL, paramSide, paramBoard){
		var request = $.ajax({url:paramURL, async:false, method:"GET", data:{b_no:boardNo}, dataType:"html", 
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
	
	function sideControlModal(e, boardNo, paramURL, paramSide, paramBoard, idx){
		var request = $.ajax({url:paramURL, async:false, method:"GET", data:{b_no:boardNo}, dataType:"html", 
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
	
// 무한 스크롤 start //
	$(function(){        	  
		var windowWidth = $( window ).width(); 
        $(window).scroll(function(){            	
             let $window = $(this);                  
             let scrollTop = $window.scrollTop();
             let windowHeight = $window.height();
             let documentHeight = $(document).height();
             console.log("documentHeight:" + documentHeight + " | scrollTop:" +
                            scrollTop + " | windowHeight: " + windowHeight );
             
             if( scrollTop > 400) {
                 $(".introduce").css("top","211px");
                 $(".main").css("top","620px");
                 if (windowWidth >700){
                    $(".trend").css("top","603px");
                 } 
              }else {
                 $(".introduce").css("top","253px");
                 $(".main").css("top","660px");
                 if (windowWidth >700){
                    $(".trend").css("top","643px");
                 } 
              }
             // scrollbar의 thumb가 바닥 전 30px까지 도달 하면 리스트를 가져온다.
             if( scrollTop + windowHeight + 150 > documentHeight ){
                  fetchList(startNo);
                  startNo += 1;                       
             }else {
            	 return;
             }
        })            
    })
    
    function fetchList(startNo){
        if(isEnd == true){ return; }
        // 방명록 리스트를 가져올 때 시작 번호
        // renderList 함수에서 html 코드를 보면 <li> 태그에 data-no 속성이 있는 것을 알 수 있다.
        // ajax에서는 data- 속성의 값을 가져오기 위해 data() 함수를 제공.             
        
        $.ajax({
             url:"ajax/profile?page=" + startNo + "&newBuzzings=" + newBuzzings + "&id=" + id,
             type: "GET",
             async: false,
             dataType: "json",
             success: function(result){
             // 컨트롤러에서 가져온 방명록 리스트는 result.data에 담겨오도록 했다.
             // 남은 데이터가 5개 이하일 경우 무한 스크롤 종료
                  var length = result.length;
                  if( length < 50 ){
                        isEnd = true;
                  }
                  
                  $.each(result, function(index, vo){
                        renderList(vo);
                  });
             }
        });
    }
// 무한 스크롤 end //
	
    function renderList(vo){
    // 리스트 html을 정의
         let html =
         '<div class="content">'+
         '<a href="#fake">'+
         '<img alt="" class="content-img" src="resources/img/'+vo.img_profile+'">'+
         ' </a>'+
         '<div class="content-line" onclick="contentPopUp('+vo.b_no+')">'+
         '<div>'+
         '<ul>'+
         '<li class="content-head">' + vo.m_name + '</li>'+
         '<li class="content-tail"><a class="font-color" href="' + vo.m_id + '">@' + vo.m_id + '</a><span class="board-date"> · '+ vo.b_rdate +'</span></li>'+
         '</ul>'+
         '</div>'+
         '<span><p class="content-left">' + vo.b_content + '</p></span>'+
         '<br>'+
         '<div id="icon-padding" class="twitter-card-content-board">'+
         '<div class="twitter-card-icons-board">'+
         '<a id="content-icon" class="twitter-card-icon comment tooltips">'+
         '<i class="fa fa-comment-o"></i>'+
         '</a>'+
         '<a id="content-icon" class="twitter-card-icon heart tooltips" id="board-likes'+vo.b_no+'">'+
         '<i class="fa fa-heart-o like-button" id="heart'+vo.b_no+'" onclick="sideControl(event, '+vo.b_no+', "ajax/likes", "#side-likes", "#likeCount")"></i>'+
         '<font id="likeCount'+vo.b_no+'">'+vo.b_like+'</font>'+
         '</a>'+
         '<a id="content-icon" class="twitter-card-icon message tooltips" class="dropdown-toggle" data-toggle="dropdown">'+
         '<i class="glyphicon glyphicon-option-horizontal"></i>'+
         '</a>'+
         '</div>'+
         '</div>'+
         '</div>'+
         '</div>';
         $("#col-sm-content").append(html);              
    }
// content start
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
    			$(".twitter-card-user-name").eq(i).html(data[i].m_name + "<a href='' class='content-modal-id'></a>");
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
    					if(ext == 'mp4') $(".twitter-card-text").eq(i).append('<video src="store/'+data[i].f_saveFiles[j]+'" class="videoOne" controls></video>');
    					else {
    						if(data[i].f_saveFiles.length == 1) $(".twitter-card-text").eq(i).append('<img src="store/'+data[i].f_saveFiles[j]+'" class="imgOne" onclick="doImgPop(this.src, event);"/>');
    						else $(".twitter-card-text").eq(i).append('<img src="store/'+data[i].f_saveFiles[j]+'" class="imgList" style="width:49.1%" onclick="doImgPop(this.src, event);"/>');
    					}
    				}
    			}

    			if(i < data.length - 1) {
    				index = index + 1;
    				var html = '<div class="twitter-card" onclick="contentZoom('+index+')">' + 
    							  '<input type="hidden" class="modal-bno"/>' +
    						      '<div class="twitter-card-header">' +
    						        '<img class="twitter-card-user-img content-img" src=""/>'+
    						        '<span class="twitter-card-user-name"></span>'+
    								'<button style="margin-top: auto;" class="btn-Hyeong btn-primary-Hyeong btn-follow" id="followBtn" onclick="following('+index+')">'+
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
    						          '<a class="twitter-card-icon heart tooltips" >'+
    						            '<span>좋아요</span>'+
    						            '<i class="fa fa-heart-o like-button content-modal" onclick=\'sideControlModal(event, $(".modal-bno").eq('+index+').val(), "ajax/likes", "#side-likes", ".likeCount-modal", '+index+');\'></i>'+
    						            '	<font class="likeCount-modal"></font>'+
    						          '</a>'+
    						          '<span class="glyphicon glyphicon-option-horizontal">'+
    						          '</span>'+
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
// content end
    
    
// 슬라이드 메뉴 start //
$(".hidden-a").click(function () {
    $("#slideMenu,.page_cover,html").addClass("open");
    window.location.hash = "#open";
});

window.onhashchange = function () {
    if (location.hash != "#open") {
        $("#slideMenu,.page_cover,html").removeClass("open");
    }
};
// 슬라이드 메뉴 end //
 