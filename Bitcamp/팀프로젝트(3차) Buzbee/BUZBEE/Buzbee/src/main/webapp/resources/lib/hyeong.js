/**/
$(document).on({
    mouseenter: function () {
			$(this).addClass("btn-danger-Hyeong");
				$('.following').hide();
				$('.unfollow').show();
    },
    mouseleave: function () {
        //stuff to do on mouse leave
			$(this).removeClass("btn-danger-Hyeong");
				$('.unfollow').hide();
				$('.following').show();
    }	
}, ".btn-following"); //pass the element as an argument to .on

// follow/unfollow button handling
function following(index){
	if ($('.btn-Hyeong:eq('+index+')').hasClass('btn-following')) {
		// successful unfollow
		$('.btn-Hyeong:eq('+index+')').removeClass('btn-following');
		$('.btn-Hyeong:eq('+index+')').removeClass('btn-danger-Hyeong');
		$('.btn-Hyeong:eq('+index+')').addClass('btn-primary-Hyeong');
		$('.following:eq('+index+')').hide();
		$('.unfollow:eq('+index+')').hide();
		$('.follow:eq('+index+')').show();
		
		var request = $.ajax({url:baseUrl + '/ajax/unfollowing', method:"GET", data:{b_no:$('#modal-bno').val()}, dataType:"html", 
		  success: function () {},
	      error: function (jqXHR) {
	        alert(jqXHR.status);
	        alert(jqXHR.statusText);
	        alert(jqXHR.responseText);
	        alert(jqXHR.readyState);
	      }});
		request.done(function(data){
		});
	} else {	
		// successful follow
		$('.btn-Hyeong:eq('+index+')').addClass('btn-following');
		$('.btn-Hyeong:eq('+index+')').removeClass('btn-primary-Hyeong');
		$('.follow:eq('+index+')').hide();
		$('.following:eq('+index+')').show();
		
		
		var request = $.ajax({url:baseUrl + '/ajax/following', method:"GET", data:{b_no:$('#modal-bno').val()}, dataType:"html", 
		  success: function () {},
	      error: function (jqXHR) {
	        alert(jqXHR.status);
	        alert(jqXHR.statusText);
	        alert(jqXHR.responseText);
	        alert(jqXHR.readyState);
	      }});
		request.done(function(data){
		});
	}
	event.stopPropagation();
}

function follow(m_no) {
	var request = $.ajax({url:baseUrl + '/ajax/followingRecommend', method:"GET", data:{m_no:m_no}, dataType:"html", 
		success: function () {
			$('#recommend' + m_no).remove();
		},
		error: function (jqXHR) {
			alert(jqXHR.status);
			alert(jqXHR.statusText);
			alert(jqXHR.responseText);
			alert(jqXHR.readyState);
		}});
	request.done(function(data){
	});
}

//��Ʈ �׼�

var like_button = $('.like-button');
if (like_button) {
    like_button.addEventListener("click", doLikeButton);
}

function doLikeButton(e) {
    toggleButton(e.target);
}

/*��Ʈ�� Ŭ��*/

$('#rebuz-modal').click(function(){
  $(this).toggleClass('-checked');
});

