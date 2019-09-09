<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, user-scalable=no">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/admin.css" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/reset.css" />
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fontawesome-free-5.10.1-web/css/all.min.css" />
 
 <script src="https://code.jquery.com/jquery-3.4.1.js"></script>
 <script src="${pageContext.request.contextPath}/resources/memoLIst.js"></script>

<title>Insert title here</title>
</head>

<body>
 <header class="top_nav">
 	<a href="/memo_list"><i class="far fa-clipboard fa-fw"></i> MEMO LIST </a>
 </header>
 
 <div class="content">
 	<div class="button_section">
 		<a href="javascript:funcBoo()" class="new_row">새로 생성하기</a>
 	</div>
 	
 	<div class="list_section">
 		<ul> </ul>
 	</div>
 </div>

</body>
</html>