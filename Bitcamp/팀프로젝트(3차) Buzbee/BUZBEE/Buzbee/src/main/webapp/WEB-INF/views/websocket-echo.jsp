<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	
	<!-- jQuery CDN-->
	<script
	  src="https://code.jquery.com/jquery-1.9.0.js"
	  integrity="sha256-TXsBwvYEO87oOjPQ9ifcb7wn3IrrW91dhj6EMEtRLvM="
	  crossorigin="anonymous"></script>
	
	<!-- Web socket CDN -->
	<script src="http://cdn.sockjs.org/sockjs-0.3.4.js"></script>
	
	<!-- Web Socket js -->
	<script type="text/javascript">
		$(document).ready(function() {
			$("#sendBtn").click(function() {
				if($("#message").val().trim() == '') return;
				sendMessage();
				$('#message').val('')
			});
		
			$("#message").keydown(function(key) {
				if($("#message").val().trim() == '') return;
				if(key.keyCode == 13) {// 엔터
			       sendMessage();
			       $('#message').val('')
				}
	  		});
		});
		
		// 웹소켓을 지정한 url로 연결한다.
		let sock = new SockJS("<c:url value="/echo"/>");
		sock.onmessage = onMessage;
		sock.onclose = onClose;
		
		// 메시지 전송
		function sendMessage() {
	       sock.send($("#message").val());
		}
		
		// 서버로부터 메시지를 받았을 때
		function onMessage(msg) {
			var data = msg.data;
			$("#data").append(data + "<br/>");
		}
		
		// 서버와 연결을 끊었을 때
		function onClose(evt) {
			$("#data").append("연결 끊김");
		}
	</script>
</head>
<body>
	<input type="text" id="message" />
	<input type="button" id="sendBtn" value="전송" />
	<sec:authorize access='isAnonymous()'>
		<input type="submit" value="로그인" onclick='location.href="login"'/> 
	</sec:authorize>
	<sec:authorize access='isAuthenticated()'>
		<input type="submit" value="로그아웃" onclick='location.href="logout"' /> 
	</sec:authorize>
	<div id="data"></div>
	<div id="content">
		<br/>
		<c:forEach items='${list}' var='item'>
			<table style='border-top:1px solid black;'>
				<tr>
					<th>검색어</th>
					<td>${item.query}</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${item.hit}</td>
				</tr>				
			</table>
		</c:forEach>
	</div>
</body>
</html>

