<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/fontawesome-free-5.10.1-web/fontawesome-free-5.10.1-web/css/all.min.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/reset.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/pages.css" />
<title>Insert title here</title>
</head>
<body>
	 <header class="top_nav">
      <a href="/"><i class="fas fa-user-friends"></i> 색상선택</a>
      <a href="/histories"><i class="fas fa-clipboard-list"></i> 히스토리</a>
    </header>
    
    <div class="content">
     <ul>
      ${colors}
     </ul>
    </div>

</body>
</html>