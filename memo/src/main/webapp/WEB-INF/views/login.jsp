<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, user-scalable=no">
<link href="${pageContext.request.contextPath}/resources/sign_in.css" rel="stylesheet" />
<title>Insert title here</title>
</head>
<body>
	<!-- 패스워드기때문에 post방식 사용하자 -->
	<div class="wrap">
		<div class="login_content">
			<div class="logo_sec">
				<h3>로그인</h3>
			</div>
			<form class="login_sec" action="input_password" method="post">
				<input type="text" placeholder="아이디" class="login_input" required="required" name="id" />
				<input type="password" placeholder="패스워드" class="login_input" required="required" name="password" />
				<input type="submit" class="login_btn" value="확인" />
			</form>
			<a href="/newmemo/join">가입</a>
		</div>
	</div>
	
</body>
</html>