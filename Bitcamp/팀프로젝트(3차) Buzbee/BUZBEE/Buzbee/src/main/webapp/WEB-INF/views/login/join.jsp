<%@page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta id="viewport" name="viewport" content="width=device-width, initial-scale=1">
		<title>Buzbee</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bn.css">
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/join.js"></script>
		<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	</head>

	<body>
		<div id="container">
			<div id="left">
			 	<!--  <img src="img/logo.jpg" style="opacity:0.2">-->
			</div>
		<div id="right">
			<div id="right_content">
			<h1><b style="color:#404040">Buz<span style="color:rgb(251, 208, 67)">bee</span></b></h1>
			<div>
			<form role="form" id="form" method="post" action="join" onsubmit='return checkForm()'>
				<h2>지금 버즈비에 가입하세요!</h2>
				<div>
				<div>
					<input type="text" id="m_name" name="m_name" placeholder="닉네임" required maxlength="8">
				</div>	
				<div></div>
				</div>
			 
			<div>
				<div>
					<div>
						<input type="email" id="m_email" name="m_email" placeholder="이메일 주소" onfocusout="emailChk()" required>
					</div>
					<div></div>
				</div>
			</div>
	
			<div>
				<div>
					<div><input type="password" id="m_password1" name="m_password" onkeyup="pwChk();"placeholder="비밀번호" maxlength="15" required></div>
					<div></div>
				</div>
			</div>
			
			<div>
				<div>
					<div><input type="password" id="m_password2" onkeyup="pwChk();" placeholder="비밀번호 재입력" maxlength="15" required></div>
					<div></div>
				</div>
			</div>	
				
			<div>
				<span style="color:red;" id="chkEmail"></span>
				<br/>
				<span style="color:red;" id="chkPass"></span>
				<div><input type="submit" value="가입" id="submit" style="background-color:#fbd043; color:#525252; font-weight:bold" ></div>
			</div>

			<div id="agree"><p>가입하면 버즈비(Buzbee)의 <a>약관</a>, <a>데이터 정책</a> 및 <a>쿠키 정책</a>에 동의하게 됩니다.</p></div>
		</form>
		</div>
		</div>
		<div id="right_login"><p>계정이 있으신가요? <a href="${pageContext.request.contextPath}/m/login">로그인</a></p></div>
			</div>
		</div>
	</body>
</html>
