<%@page contentType="text/html;charset=utf-8"%>

<!doctype html>

<html>
	<head>
		<meta charset="utf-8">
		<meta id="viewport" name="viewport" content="width=device-width, initial-scale=1">
		<title>Buzbee</title>
		<link rel="shortcut icon" href="${pageContext.request.contextPath}/img/web.ico" type="image/x-icon">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bn.css">
	</head>

	<body>
		<div id="container">
		<div id="login">
			<div id="right_content">
			<a href='m/logout'><h1><b style="color:#404040">Buz<span style="color:rgb(251, 208, 67)">bee</span></b></h1></a>
				<div>
				<form role="form" id="form" method="post" action="/buzbee/">
					<h2> 이메일 인증 후 사용하실 수 있습니다. </h2>
					<p> 인증메일을 받지 못하셨나요?</p>
					<div>
						<input type="email" id="m_email" name="m_email" value="${member.m_email}" readonly style=width:50%;> 
						<input type="hidden" id="m_name" name="m_name" value="${member.m_name}"> 
						<input type="hidden" id="m_approval" name="m_approval" value="${member.m_approval}">
						<input type="submit" value ="재전송" name ="submit" id="submit" style="background-color:#fbd043; color:#525252; width:20%; font-weight:bold"/>
					</div>
					
				</form>
				</div>			
			</div>
		</div>
		</div>
	</body>
</html>
