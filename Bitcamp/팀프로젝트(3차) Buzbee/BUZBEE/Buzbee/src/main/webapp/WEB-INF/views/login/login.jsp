<%@page contentType="text/html;charset=utf-8"%>

<!doctype html>

<html>
	<head>
		<meta charset="utf-8">
		<meta id="viewport" name="viewport" content="width=device-width, initial-scale=1">
		<title>Buzbee 로그인</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bn.css">
		<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
	</head>

	<body>
		<div id="container">
		<div id="login">
			<div id="right_content">
			<a href='join'><h1><b style="color:#404040">Buz<span style="color:rgb(251, 208, 67)">bee</span></b></h1></a>
			<div>
				<h2>버즈비에 로그인하기</h2>
				<div></div>
				<div>
					<div>
						<div>
							<input type="text" id="username" name="username" placeholder="이메일 주소 또는 아이디" required>
						</div>
						<div></div>
					</div>
				</div>
	
				<div>
					<div>
						<div><input  type="password" id="password" name="password" placeholder="비밀번호" required></div>
						<div></div>
					</div>
				</div>
				<div><input id="remember_me" name="remember-me" type="checkbox" style="width:5%">자동로그인</div>
				
				<div>
					<div><button id='loginBtn'><b>로그인</b></button></div>
				</div>
				<br/>
				<div id="login_pass"><a href="findPw"><p>비밀번호를 잊으셨나요?</p></a></div>
			</div>			
		</div>
		<div id="login_join"><p>계정이 없으신가요? <a href="join">가입하기</a></p></div>
			</div>
		</div>
		<script type="text/javascript" src="${pageContext.request.contextPath}/lib/join.js"></script>
	</body>
</html>
