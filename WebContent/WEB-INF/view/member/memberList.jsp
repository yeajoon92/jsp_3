<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<c:import url="../../../temp/bootStrap.jsp"></c:import>
<title>Insert title here</title>
</head>
<body>
<c:import url="../../../temp/header.jsp"></c:import>

	<div class="container-fluid">
		<div class="row">
			<c:choose>
				<c:when test="${not empty member}">
					<!-- 로그인 후 -->
					<h3>${member.id}님 환영합니다</h3> <!-- id를 쓸 수 있는 이유: Getter&Setter가 있기 때문 -->
					<a href="./memberLogout.do">Logout</a>
					<a href="./memberMypage.do">MyPage</a>					
				</c:when>
				
				<c:otherwise>
					<!-- 로그인 전 -->
					<a href="./memberLogin.do">Login</a>
					<a href="./memberJoin.do">Join</a>
				</c:otherwise>
			</c:choose>
			
		</div>
	</div>


<c:import url="../../../temp/footer.jsp"></c:import>
</body>
</html>