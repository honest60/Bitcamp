<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
  <head>
    <title>Weather Fairy</title>	
    <style>
    $font-family:   "Roboto";
	$font-size:     14px;	
	$color-primary: #ABA194;
	
	* {
	    margin: 0;
	    padding: 0;
	    box-sizing: border-box;
	}
	
	body {
	    font-family: $font-family;
	    font-size: $font-size;
	    background-size: 200% 100% !important;

	    transform: translate3d(0, 0, 0);
	    background: linear-gradient(45deg, #99ccff 0%, #A2C7E5 70%);
	    height: 100vh
	}
	
	.user {
	    width: 90%;
	    max-width: 340px;
	    margin: 10vh auto;
	}
	
	.user__header {
	    text-align: center;
	    opacity: 0;
	    transform: translate3d(0, 500px, 0);
	    animation: arrive 400ms ease-in-out 0.6s forwards;
	}	
	
	.user__title {
	    font-size: $font-size;
	    margin-bottom: -10px;
	    font-weight: 500;
	    color: white;
	}
	
	.form {
	    margin-top: 40px;
	    border-radius: 6px;
	    overflow: hidden;
	    opacity: 0;
	    transform: translate3d(0, 500px, 0);
	    animation: arrive 400ms ease-in-out 0.8s forwards;
	}
	
	.form--no {
	    animation: NO 1s ease-in-out;
	    opacity: 1;
	    transform: translate3d(0, 0, 0);
	}
	
	.form__input {
	    display: block;
	    width: 100%;
	    padding: 20px;
	    font-family: $font-family;
	    -webkit-appearance: none;
	    border: 0;
	    outline: 0;
	    transition: 0.3s;
	    
	    &:focus {
	        background: darken(#fff, 3%);
	    }
	}
	
	.btn {
	    display: block;
	    width: 100%;
	    padding: 20px;
	    font-family: $font-family;
	    -webkit-appearance: none;
	    outline: 0;
	    border: 0;
	    color: white;
	    background: $color-primary;
	    transition: 0.3s;
	    
	    &:hover {
	        background: darken($color-primary, 5%);
	    }
	}
	
	@keyframes NO {
	  from, to {
	    -webkit-transform: translate3d(0, 0, 0);
	    transform: translate3d(0, 0, 0);
	  }
	
	  10%, 30%, 50%, 70%, 90% {
	    -webkit-transform: translate3d(-10px, 0, 0);
	    transform: translate3d(-10px, 0, 0);
	  }
	
	  20%, 40%, 60%, 80% {
	    -webkit-transform: translate3d(10px, 0, 0);
	    transform: translate3d(10px, 0, 0);
	  }
	}
	
	@keyframes arrive {
	    0% {
	        opacity: 0;
	        transform: translate3d(0, 50px, 0);
	    }
	    
	    100% {
	        opacity: 1;
	        transform: translate3d(0, 0, 0);
	    }
	}
	
	@keyframes move {
	    0% {
	        background-position: 0 0
	    }
	
	    50% {
	        background-position: 100% 0
	    }
	
	    100% {
	        background-position: 0 0
	    }
	}
    @import url('https://fonts.googleapis.com/css?family=Lato');
    .container ul{
      margin: 0;
      padding: 0;
    }
    ul li{
      color: #AAAAAA;
      display: block;
      position: relative;
      float: left;
      width: 50%;
      height: 60px;
    }

    ul li input[type=radio]{
      position: absolute;
      visibility: hidden;
    }
    ul li label{
      display: block;
      position: relative;
      font-size: 0.8em;
      padding: 2px 2px 2px 40px;
      margin: 17px;
      height: 50px;
      z-index: 9;
      cursor: pointer;
    }

    ul li:hover label{
        color: #FFFFFF;
    }

    ul li .check{
      display: block;
      position: absolute;
      border: 5px solid #AAAAAA;
      border-radius: 100%;
      height: 15px;
      width: 15px;
      top: 17px;
      left: 20px;
    }

    ul li:hover .check {
      border: 5px solid #FFFFFF;
    }

    ul li .check::before {
      display: block;
      position: absolute;
        content: '';
      height: 15px;
      width: 15px;
      margin: auto;
    }
    input[type=radio]:checked ~ .check {
      border: 5px solid #FFFFFF;
    }
    input[type=radio]:checked ~ .check::before{
      background: #FFFFFF;
    }
    input[type=radio]:checked ~ label{
      color: #FFFFFF;
    }
    </style>
    
    <script>
	    const button = document.querySelector('.btn')
		const form   = document.querySelector('.form')	
		button.addEventListener('click', function() {
		   form.classList.add('form--no') 
		});    
	    function check()
		   {
	    	  var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
			  var pw = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,20}$/;
			  var ag = /^(?=.*?[0-9]).{1,2}$/;
			  var nc = /^(?=.*?[ㄱ-ㅎ|ㅏ-ㅣ|가-힣|a-z|A-Z|0-9]).{3,8}$/;
			  var mr = /^(?=.*?[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]).{1,10}$/;
		      if(f.email.value == "" | f.pwd.value == ""| f.nickname.value == ""| f.gender.value == ""| f.age.value == ""| f.mem_region.value == ""| f.tendency.value == "")
			  {
			      alert("폼을 전부 입력하셔야 합니다.");
				  f.id.focus();
				  return false;
			  }
		      if(!re.test(f.email.value)){
				  alert("이메일형식이 잘못되었습니다");
				  return false;
		      }
		      if(!pw.test(f.pwd.value)){
		    	  alert("비밀번호는 8자이상20자이하 하나이상의 숫자 및 영문 대소문자를 포함해야 합니다");
		    	  return false;
		      }
		      if(!nc.test(f.nickname.value)){
		    	  alert("닉네임을 확인해 주세요");
		    	  return false;
		      }
		      if(f.gender.value!="남"&f.gender.value!="여"){
		    	  alert("성별을 제대로 적어주세요");
		    	  return false;
		      }
		      if(!ag.test(f.age.value)|f.age.value=="0"){
		    	  alert("잘못된 나이 형태입니다");
		    	  return false;
		      }
		      if(!mr.test(f.mem_region.value)){
		    	  alert("지역은 한글만 입력 가능합니다");
		    	  return false;
		      }
			  f.submit();
		   }		 
	</script>
  </head>
  <body onload="f.id.focus();">
     <div class="user">
	    <header class="user__header">
	        <img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/3219/logo.svg" alt="" />
	        <h1 class="user__title">Register</h1>
	    </header>
	    
	    <form class="form" name="f" action="login.do?m=joincheck" method="post">
	         <div class="form__group">
	            <input type="email" placeholder="Email" class="form__input" name="email"/>
	        </div>	        
	        <div class="form__group">
	            <input type="password" placeholder="Password" class="form__input" name="pwd"/>
	        </div>	    
	        <div class="form__group">
	            <input type="text" placeholder="Nickname(최소3글자 최대8글자)" class="form__input" name="nickname"/>
	        </div>	        
	        <div class="form__group">
	            <input type="text" placeholder="Gender(남/여 만 입력해주세요)" class="form__input" name="gender"/>
	        </div>	        
	        <div class="form__group">
	            <input type="text" placeholder="Age" class="form__input" name="age"/>
	        </div>	        
	        <div class="form__group">
	            <input type="text" placeholder="Region(지역은 시나 구단위로만 입력해주세요)" class="form__input" name="mem_region"/>
	        </div>
            <div class="form__group">
                <div class="container">
                <ul>
                <li>
                    <input type="radio" id="f-option" name="tendency" value="1">
                    <label for="f-option">추위를 잘탐</label>
                    <div class="check"></div>
                </li>
                <li>
                    <input type="radio" id="s-option" name="tendency" value="2">
                    <label for="s-option">더위를 잘탐</label>
                    <div class="check"><div class="inside"></div></div>
                </li>
                </ul>
                </div>
            </div>
	        <button class="btn" type="button" onclick="check()">Log-In</button>
	    </form>
	</div>
  </body>
</html> 

