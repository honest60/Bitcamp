/* 로그인  */
$(document).ready(function(){
   $('#loginBtn').click(function(){
      var username = $("#username").val();
      var password = $("#password").val();
      var me = $("#remember_me").prop('checked');
      
      var request = $.ajax({
         url : "login_check",
         type : "post",
         data : {
            username : username,
            password : password,
            'remember-me' : me
         },
         dataType : 'text',
         success : function(data) {
            if(data.trim() != 'error') location.href='../buzbee';
            else alert('로그인 정보가 일치하지 않습니다');
         }
      });
   });
});


/* email 중복 체크 */

var chk_email = false;
var chk_password = false; 
   
function emailChk(){
   var m_email= $("#m_email").val();
   var re = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
   
   if(re.test(m_email)== false){
      alert("이메일 형식이 올바르지 않습니다.");
      event.stopPropagation();
   }
   
   $.ajax({
      url : "checkEmail",
      type : "post",
      data : {m_email : m_email},
      success : function(result) {
         
         if(result == 1){
            $("#chkEmail").html("사용중인 이메일 입니다.");
            chk_email = false;
         }else{
            $("#chkEmail").html("");
            chk_email = true;
         }
      }
   })
}


function pwChk(){
   var m_password1 = $("#m_password1").val();
   var m_password2 = $("#m_password2").val();
   
   if(m_password1 != m_password2){
      $("#chkPass").html("비밀번호가 서로 일치하지 않습니다.");
      chk_password = false;
   }else{
      $("#chkPass").html("");
      chk_password = true;
   }
}

function checkForm() {
   if(chk_email && chk_password) return true;
   return false;
} 


/* 비밀번호 찾기  */
function pwS(){
   var m_id = $("#m_id").val();
   var m_email = $("#m_email").val();
   
   $.ajax({
      url : "find_pw",
      type : "post",
      data : {
         m_id : $("#m_id").val(),
         m_email : $("#m_email").val()
      },
      success : function(result) {
         alert(result);
         if(result == '이메일로 임시 비밀번호를 발송하였습니다.') location.href='login';
         
      }
   })
}

$('#password').keypress(function(event){
    if ( event.which == 13 ) {
        $('#loginBtn').click();
        return false;
    }
});
