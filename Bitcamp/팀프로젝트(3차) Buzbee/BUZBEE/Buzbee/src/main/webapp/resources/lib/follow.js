/* 팔로잉 마우스 이벤트 */
$(document).ready(function(){
   $('.fwing').mouseover(function(){
      $('.fwing').text('언팔로우');
   });
   $('.fwing').mouseout(function(){
      $('.fwing').text('팔로잉');
   })
});


/* 팔로잉 마우스 클릭시 */

$(document).ready(function(){
   $('.fwing').on('click', function(){
      $('.fwing').css('background-color','#fbd043');
      $('.fwing').text('팔로우');
      $('.fwing').unbind();
   })
   $('.fwing').on('click', function(){
      $('.fwing').text('팔로우');
   })
})