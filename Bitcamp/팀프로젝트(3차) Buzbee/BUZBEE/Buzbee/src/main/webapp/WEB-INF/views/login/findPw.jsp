<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			<div id="login">
			<div id="right_content">
			<h1><b style="color:#404040">Buz<span style="color:rgb(251, 208, 67)">bee</span></b></h1>
			<div>
			<h2>비밀번호 찾기</h2>
				<div></div>
				<div>
					<div>
						<div><input type="text" id="m_id" name="m_id" placeholder="아이디" required></div>
						<div></div>
					</div>
				</div>
					<div>
					<div>
						<div><input  type="email" id="m_email" name="m_email" placeholder="이메일 주소" required></div>
						<div></div>
					</div>
					<div>
						<span style="color:red;" id="chk"></span>
					</div>
				</div>
					<div>
						<input type="submit" value="찾기" id="findBtn" name = "findBtn" onclick ="pwS()" style="background-color:#fbd043; color:#525252; width:20%; font-weight:bold"/> 
						<input type="button" value="취소" onclick="history.go(-1);" style="background-color:#fbd043; color:#525252; width:20%; font-weight:bold"/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>